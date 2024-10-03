package org.pnpl.analysis.invariant.analysis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jacop.constraints.*;
import org.jacop.core.*;
import org.jacop.search.*;

import com.google.common.collect.Lists;

public class InvariantCalculatorCSP {
	private static Store store;
	private List<IntVar> invariantsVars = null;
	private int lastCost;

	public InvariantCalculatorCSP() {
		store = new Store();
		
		invariantsVars = Lists.newArrayList();
	}

	public Set<List<Integer>> calculateInvariants(int[][] incidence) {
		insertIncidenceMatrix(incidence);		
		return searchSolutions();	
	}

	private void insertIncidenceMatrix(int[][] incidence) {
		IntVar[] y = insertEquations(incidence[0].length);		
				
		for (int row=0; row < incidence.length; row++) {				
			IntVar[] varRow = new IntVar[incidence[row].length];
			int[] auxRow = new int[incidence[row].length];
			
			varRow = y;
			for (int col=0; col < incidence[row].length; col++) {
				auxRow[col] = incidence[row][col];
			}
						
			store.impose(new LinearInt(varRow, auxRow, "==", 0));			
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
	
	private Set<List<Integer>> searchSolutions() {
		Set<List<Integer>> solutions = null;

		IntVar cost = new IntVar(store, "cost", 0, invariantsVars.size() * 10);
		store.impose(new SumInt(invariantsVars,"==", cost));		

		solutions = new HashSet<>();

		Set<List<Integer>> sol = searchInvariants(solutions, cost);
		if(sol != null)
			solutions.addAll(sol);
			
		return solutions;	
	}
	
	private Set<List<Integer>> searchInvariants(Set<List<Integer>> lastSolutions, IntVar cost) {
		Set<List<Integer>> invariant = null;
		boolean result;

		int level = store.level + 1;		
		store.setLevel(level);

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
	
	// format the solutions in a set of list
	private Set<List<Integer>> formatSearch(Search<IntVar> search) {
		search.getSolutionListener().printAllSolutions();		
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
}
