package org.pnpl.analysis.invariant.analysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.*;
import com.google.common.collect.Lists;

import org.jacop.constraints.*;
import org.jacop.core.*;
import org.jacop.search.*;
import org.pnpl.analysis.helpers.ExpressionHelper;

import de.ovgu.featureide.fm.core.base.IConstraint;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.base.IFeatureStructure;

import variability.BinaryExpression;
import variability.Expression;
import variability.Feature;
import variability.UnaryExpression;

/**
 * Translate the Lifted Incidence Matrix into a CSP and solve to find the solutions
 * @author Elena Gomez-Martinez
 *
 */

public class ConstraintCalculator {
	private static Store store;

	private Map<String, IntVar> featuresVars = null;	
	private List<IntVar> temporalVars = null;
	private List<IntVar> invariantsVars = null;

	private int lastIndex;
	private int lastCost;

	public ConstraintCalculator() {
		store = new Store();

		featuresVars = new HashMap<String, IntVar>();
		temporalVars = Lists.newArrayList();
		invariantsVars = Lists.newArrayList();

		lastIndex = 0;
		lastCost = -1;
	}
	
	public Set<List<String>> getInvariantList () {
		if (!invariantsVars.isEmpty())
			if(invariantsVars.size() > 0) {
				for(IntVar var : invariantsVars) {
					System.out.println(var.toString());
				}
			}
		
		return null;
	}
	
	public Set<List<Integer>> calculateInvariants(int[][] incidence, Expression[][] features, IFeatureModel fm) {	
		insertFeatureModel(fm);
		
		return searchSolutions(incidence,features);		
	}
	
	// Return the number of variables x
	private int getVariablesOnMatrix(Expression[][] features) {
		ExpressionHelper helper = new ExpressionHelper();

		int cont = 0;
		for (int row = 0; row < features.length; row++) {
			for (int col = 0; col < features[row].length; col++) {
				if (features[row][col]!= null) 
					cont += helper.getSizeNestedExpression(features[row][col]);
			}
		}
		return cont;
	}	

	// Insert the feature model structure into the store
	private void insertFeatureModel(IFeatureModel fm) {
		System.out.println("[pnpl] Invariant: Inserting Feature Model");		

		for(IFeature f : fm.getFeatures()) {
			String key = f.getName();
			featuresVars.put(key, new IntVar(store, key, 0, 1));
		}
		insertFeatureModelTreeFromRoot(fm.getStructure().getRoot());
		insertFeatureModelConstraints(fm);
	}	

	// Insert constraint from the feature model
	private void insertFeatureModelConstraints(IFeatureModel fm) {
		ExpressionHelper helper = new ExpressionHelper();

		for(IConstraint ic : fm.getConstraints()) {
			Expression expr = helper.buildExpression(ic.getNode());			

			int nested = helper.getSizeNestedExpression(expr);
			for (int i = 0; i < nested; i++) {
				IntVar v = new IntVar(store, "r_"+ic.getInternalId()+"_"+i, 0, 1);
				temporalVars.add(v);
			}
			IntVar variable = insertExpression(expr);
			store.impose(new XeqC(variable, 1));
		}
	}

	// Insert feature model
	private void insertFeatureModelTreeFromRoot(IFeatureStructure parent) {
		// root is always true
		store.impose(new XeqC(featuresVars.get(parent.getFeature().getName()), 1));		
		insertFeatureModelTree(parent);
	}

	// Insert constraint for each feature
	private void insertFeatureModelTree(IFeatureStructure parent) {
		if (parent != null) {
			if (parent.getChildrenCount() > 0) {
				String keyParent = parent.getFeature().getName(); 
				IntVar varParent = featuresVars.get(keyParent);
				List<IntVar> varChildren = Lists.newArrayList();

				for (IFeatureStructure child : parent.getChildren()) {
					String keyChild = child.getFeature().getName();

					if (featuresVars.containsKey(keyChild)) {
						IntVar varChild = featuresVars.get(keyChild);
						varChildren.add(varChild);

						if (parent.isRoot()) {
							if(parent.isAnd()) {
								if (child.isMandatory()) {
									store.impose(new XeqC(varChild, 1)); 
								}
							}
						} 
						else {
							if(parent.isAnd()) {
								if (child.isMandatory()) {
									store.impose(new XeqY(varParent, varChild));
								} else {
									store.impose(new IfThen(new XeqC(varParent, 0), new XeqC(varChild, 0)));								
								}
							} else if (parent.isOr() || parent.isAlternative() ) {
								store.impose(new IfThen(new XeqC(varParent, 0), new XeqC(varChild, 0)));
							}
						}
					}
					insertFeatureModelTree(child);
				}

				if (varChildren.size() > 0) {
					int limit = 0;					

					if (parent.isOr())
						limit = varChildren.size();
					else if (parent.isAlternative()) 
						limit = 1;

					if (limit > 0) {
						if(parent.isRoot()) {
							store.impose(new SumInt(varChildren, "==", new IntVar(store, 1, limit)));
						}
						else {
							store.impose(new IfThen(new XeqC(varParent, 1), new SumInt(varChildren, "==", new IntVar(store, 1, limit))));
						}							
					}					
				}
			}
		}
	}

	// create IntVars for all X in the feature matrix
	private void insertVariableList(int numVariables) {
		for(int i=0; i< numVariables; i++) {
			IntVar v = new IntVar(store, "x"+i, 0, 1);
			temporalVars.add(v);
		}
	}

	// insert the lifted incidence matrix
	private void insertIncidenceMatrix(int[][] incidence, Expression[][] features) {
		System.out.println("[pnpl] Invariant: Creating Lifted Matrix");

		IntVar[] y = insertEquations(incidence[0].length);

		int row;
		int col;
		for (row=0; row < incidence.length; row++) {				
			IntVar[] varRow = new IntVar[incidence[row].length];

			for (col=0; col < incidence[row].length; col++) {
				if(features[row][col] == null) 
					varRow[col] = y[col];
				else {
					IntVar variable = insertExpression(features[row][col]);
					if (variable == null) 
						varRow[col] = y[col];
					else {
						store.impose(new XmulYeqZ(variable, y[col], temporalVars.get(lastIndex))); 	
						varRow[col] = temporalVars.get(lastIndex);
						lastIndex ++;	
					}
				} 					
			}

			// Insert the present conditions stored at the end of each row
			IntVar pc = insertExpression(features[row][col]);
			if(pc == null) 
				store.impose(new LinearInt(varRow, incidence[row], "==", 0));	
			else {
				store.impose(new IfThenElse(new XeqC(pc,1), 
						new LinearInt(varRow, incidence[row], "==", 0), 
						new LinearInt(varRow, new int[incidence[row].length], "==", 0)));
			}			
		}

		// Insert the present conditions stored at the end of each column
		row = incidence.length;
		for (col=0; col < features[row].length; col++) {
			IntVar pc = insertExpression(features[row][col]);
			if(pc != null) {
				store.impose(new IfThen(new XeqC(pc,0), new XeqC(y[col],0)));
			}
		}
	}

	// Create IntVars for each equations from the incidence matrix
	private IntVar[] insertEquations(int numEquations) {		
		IntVar[] y = new IntVar[numEquations];

		for(int i=0; i<numEquations; i++) {
			IntVar v = new IntVar(store, "y"+i, 0, 1); 
			invariantsVars.add(v);
			y[i] = v;
		}
		// sum all equations > 0
		store.impose(new SumInt(y,">", new IntVar(store, 0, 0)));
		return y;
	}

	private IntVar insertExpression(Expression expr) {
		if(expr != null) {
			if (expr instanceof Feature) {
				String key = ((Feature) expr).getFeature();
				if (featuresVars.containsKey(key)) {					
					return featuresVars.get(key);
				}
			}
			else if (expr instanceof UnaryExpression) {
				UnaryExpression un = (UnaryExpression) expr;
				IntVar aux = insertExpression(un.getRight());
				store.impose(new XneqY(aux,temporalVars.get(lastIndex)));

				lastIndex ++; 
				return temporalVars.get(lastIndex - 1);
			}
			else if (expr instanceof BinaryExpression) {
				BinaryExpression bin = (BinaryExpression) expr;

				IntVar[] temp = new IntVar[3];				
				temp[0] = insertExpression(bin.getLeft());
				temp[1] = insertExpression(bin.getRight());
				temp[2] = temporalVars.get(lastIndex);

				switch(bin.getOperator()) {
				case AND:
					// a AND b --> a + b
					store.impose(new AndBoolSimple(temp[0], temp[1], temp[2]));

				case OR:
					// a OR b --> a + b					
					store.impose(new OrBoolSimple(temp[0], temp[1], temp[2]));

				case IMPLIES:
					// a --> b 
					store.impose(new IfThenBool(temp[0], temp[1], temp[2]));
				}
				lastIndex ++; 
				return temporalVars.get(lastIndex - 1);
			}
		}
		return null;	
	}

	private Set<List<Integer>> searchSolutions(int[][] incidence, Expression[][] features) {
		Set<List<Integer>> featureValues = null;
		Set<List<Integer>> solutions = null;

		List<IntVar> f =  featuresVars.values().stream().collect(toList());
		featureValues = searchFeatures(featureValues, f);

		if (featureValues != null) {
			insertVariableList(getVariablesOnMatrix(features));
			insertIncidenceMatrix(incidence, features);
			
			IntVar cost = new IntVar(store, "cost", 0, invariantsVars.size() * 10);
			store.impose(new SumInt(invariantsVars,"==", cost));		

			solutions = new HashSet<>();

			System.out.println("[pnpl] Invariant: Searching solutions");
			for (List<Integer> val : featureValues) {
				Set<List<Integer>> sol = searchInvariants(f, val, solutions, cost);
				if(sol != null)
					solutions.addAll(sol);
			}
		}			

		return solutions;	
	}

	// Search the valid configurations
	private Set<List<Integer>> searchFeatures(Set<List<Integer>> featValues, List<IntVar> f) {
		boolean result;

		int level = store.level + 1;
		store.setLevel(level);

		result = store.consistency();
		if (result) {
			Search<IntVar> search = new DepthFirstSearch<IntVar>(); 
			SelectChoicePoint<IntVar> select = 
					new SimpleSelect<IntVar>(f.toArray(new IntVar[f.size()]), 
							new SmallestMin<IntVar>(), 
							new SmallestDomain<IntVar>(), 
							new IndomainMin<IntVar>()); 
			search.setPrintInfo(false);
			search.setSelectChoicePoint(select);

			search.getSolutionListener().searchAll(true);
			search.getSolutionListener().recordSolutions(true);

			result = search.labeling(store, select);
			if (result) {
				featValues = formatSearch(search);
				System.out.println("[pnpl] Invariant: Number of valid configurations with invariants: " + featValues.size());
			}
		}

		store.removeLevel(level);
		store.setLevel(level-1);

		return featValues;
	}

	// Search invariants for a valid configuration
	private Set<List<Integer>> searchInvariants(List<IntVar> f, List<Integer> val, Set<List<Integer>> lastSolutions, IntVar cost) {
		Set<List<Integer>> invariant = null;
		boolean result;

		int level = store.level + 1;		
		store.setLevel(level);

		if(f!=null) {
			//f.size == val.size
			for (int i = 0; i < val.size(); i++) {
				store.impose(new XeqC(f.get(i),val.get(i)));				
			}
		}

		// we are look for different solutions 
		if((lastSolutions != null) && (!lastSolutions.isEmpty())) {
			for (List<Integer> s : lastSolutions) {
				PrimitiveConstraint[] c = new PrimitiveConstraint[s.size()];
				for (int i = 0; i < s.size(); i++) {
					c[i] = new XeqC(invariantsVars.get(i),s.get(i));
				}
				store.impose(new Not(new And(c)));
			}
		}

		if (lastCost > 0) 
			store.impose(new XlteqC(cost, lastCost));

		result= store.consistency();
		if(result) {
			Search<IntVar> search = new DepthFirstSearch<IntVar>(); 
			SelectChoicePoint<IntVar> select = 
					new SimpleSelect<IntVar>(invariantsVars.toArray(new IntVar[invariantsVars.size()]), 
							new SmallestMin<IntVar>(), 
							new SmallestDomain<IntVar>(), 
							new IndomainMin<IntVar>()); 
			search.setPrintInfo(false); 
			search.setSelectChoicePoint(select);			

			search.getSolutionListener().searchAll(true);
			search.getSolutionListener().recordSolutions(true);

			//result = search.labeling(store, select, cost);
			result = search.labeling(store, select);

			if (result) {
				if (search.getCostVariable() != null) 
					lastCost = search.getCostValue();
				invariant = formatSearch(search);
			}
		}	

		store.removeLevel(level);
		store.setLevel(level-1);

		return invariant;
	}

	// Search invariants for a valid configuration
	@SuppressWarnings("unused")
	private Set<List<Integer>> searchInvariantsWithSets(List<IntVar> f, List<Integer> val, Set<List<Integer>> lastSolutions, IntVar cost) {
		Set<List<Integer>> invariant = null;
		boolean result;

		int level = store.level + 1;		
		store.setLevel(level);

		if(f!=null) {
			//f.size == val.size
			for (int i = 0; i < val.size(); i++) {
				store.impose(new XeqC(f.get(i),val.get(i)));				
			}
		}

		// we are look for different solutions 
		if((lastSolutions != null) && (!lastSolutions.isEmpty())) {
			for (List<Integer> s : lastSolutions) {

				PrimitiveConstraint[] c = new PrimitiveConstraint[s.size()];
				for (int i = 0; i < s.size(); i++) {
					c[i] = new XeqC(invariantsVars.get(i),s.get(i));
				}
				store.impose(new Not(new And(c)));
			}
		}

		if (lastCost > 0) 
			store.impose(new XlteqC(cost, lastCost));

		result= store.consistency();
		if(result) {
			Search<IntVar> search = new DepthFirstSearch<IntVar>(); 
			SelectChoicePoint<IntVar> select = 
					new SimpleSelect<IntVar>(invariantsVars.toArray(new IntVar[invariantsVars.size()]), 
							new SmallestMin<IntVar>(), 
							new SmallestDomain<IntVar>(), 
							new IndomainMin<IntVar>()); 
			search.setPrintInfo(false); 
			search.setSelectChoicePoint(select);			

			search.getSolutionListener().searchAll(true);
			search.getSolutionListener().recordSolutions(true);

			result = search.labeling(store, select, cost);
			//result = search.labeling(store, select);

			if (result) {
				if (search.getCostVariable() != null) 
					lastCost = search.getCostValue();
				invariant = formatSearch(search);
			}
		}	

		store.removeLevel(level);
		store.setLevel(level-1);

		return invariant;
	}


	// format the solutions in a set of list
	private Set<List<Integer>> formatSearch(Search<IntVar> search) {
		//search.getSolutionListener().printAllSolutions();		
		//Set<List<Integer>> solutionsList = new LinkedHashSet<>();
		Set<List<Integer>> solutionsList = new HashSet<>();

		for (int i=1; i<=search.getSolutionListener().solutionsNo(); i++){
			List<Integer> newSol = Lists.newArrayList();

			for (int j=0; j<search.getSolution(i).length; j++) {
				Domain d = search.getSolution(i)[j];
				if (d.isNumeric()) 
					newSol.add(Integer.parseInt(d.toString()));	

			}
			if (newSol != null) 
				solutionsList.add(newSol);
		}
		return solutionsList;
	}

	@SuppressWarnings("unused")
	private Set<List<Integer>> searchAllSolutions() {
		List<IntVar> l = Lists.newArrayList();
		l.addAll(featuresVars.values().stream().collect(toList()));
		l.addAll(invariantsVars);

		Search<IntVar> search = new DepthFirstSearch<IntVar>(); 
		SelectChoicePoint<IntVar> select = 
				new SimpleSelect<IntVar>(l.toArray(new IntVar[l.size()]), 
						new SmallestMin<IntVar>(), 
						new SmallestDomain<IntVar>(), 
						new IndomainMin<IntVar>()); 

		search.setSelectChoicePoint(select);
		search.getSolutionListener().searchAll(true);
		search.getSolutionListener().recordSolutions(true);

		boolean result = search.labeling(store, select);

		if (result)
			return formatSearch(search);
		else 
			return null;	
	}

	@SuppressWarnings("unused")
	private static void printConstraints() {
		System.out.println( "Constraints: " + store.getConstraints().toString());
		for(Constraint c : store.getConstraints()) {
			System.out.println("*" + c.toString());
		}
	}
}