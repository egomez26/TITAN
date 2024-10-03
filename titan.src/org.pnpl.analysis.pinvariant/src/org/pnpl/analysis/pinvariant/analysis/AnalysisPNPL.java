package org.pnpl.analysis.pinvariant.analysis;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.eclipse.collections.api.tuple.Pair;
import org.pnpl.analysis.analyzer.AbstractAnalysisPNPL;
import org.pnpl.analysis.helpers.PetriNetsHelper;
import org.pnpl.analysis.invariant.analysis.ConstraintCalculator;

import variability.Expression;
import PetriNets.PetriNet;
import PetriNets.Place;
import PetriNets.PTArc;
import PetriNets.TPArc;
import PetriNets.Transition;

/**
 * @author Elena Gomez-Martinez
 *
 */

public class AnalysisPNPL extends AbstractAnalysisPNPL {
	private int[][] incidence = null; 
	private Expression[][] features = null;

	private List<Place> listOfPlaces = null;
	private Map<String, List<String>> listOfInvariants = null;
	private Map<String, Expression> pcExpression = null;

	@Override
	protected boolean buildCondition() {
		condition = "";

		PetriNet pn = vh.getPetriNet();
		if (pn == null) return false;

		pcExpression = vh.extractExpressionList();
		generateLiftedIncidenceMatrix(pn);

		ConstraintCalculator cc = new ConstraintCalculator();
		Set<List<Integer>> calculated = cc.calculateInvariants(incidence, features, vh.getFeatureModel());

		if (calculated != null) {
			System.out.println("[pnpl] P-Invariant: The net has " + calculated.size());
			formatInvariants(calculated);
			return (calculated.size() > 0);
		}
		return false;
	}

	public Map<String, List<String>> getInvariants() {
		return listOfInvariants;
	}

	// Build two matrices: one with the incidence value 
	// and the other with their corresponding presence conditions
	private void generateLiftedIncidenceMatrix(PetriNet pn) {
		int max_row = pn.getTrans().size();
		int max_col = pn.getPlaces().size();

		incidence = new int[max_row][max_col];
		features = new Expression[max_row + 1][max_col + 1];

		PetriNetsHelper helper = new PetriNetsHelper();
		Table<Place, Transition, Pair<Expression,Integer>> table = helper.getIncidenceTableWithPCs(pn,pcExpression);

		int row = 0;
		int col = 0;
		for(Transition t : pn.getTrans()) {
			for (Place p : pn.getPlaces()) {
				if (table.contains(p, t)) {
					Pair<Expression,Integer> pair = table.get(p, t);
					incidence[row][col] = pair.getTwo();
					if (incidence[row][col] != 0) 
						features[row][col] =  pair.getOne();
				}
				col++;				
			}
			features[row][col] = pcExpression.get(t.getName());
			row++;			
			col=0;
		}

		listOfPlaces = Lists.newArrayList();
		for (Place p : pn.getPlaces()) {
			listOfPlaces.add(p);
			features[row][col] =  pcExpression.get(p.getName());
			col++;				
		}
	}

	private void formatInvariants (Set<List<Integer>> calculated) {
		int numInvariant = 0;		
		Map<String, List<String>> list = new HashMap<String, List<String>>();

		for (List<Integer> invariant : calculated) {
			System.out.println("[pnpl] P-Invariant: " + invariant.toString());

			int index = 0;
			List<Place> invariantPlaces = Lists.newArrayList();			
			for (Integer i : invariant) {
				if (i == 1) {
					invariantPlaces.add(listOfPlaces.get(index));					
				}
				index++;
			}

			List<String> listOfStrings = Lists.newArrayList();
			for (Place p : invariantPlaces) {				
				listOfStrings.add(p.getName());				

				for(PTArc ptA : p.getOutputs()) {
					Transition t = ptA.getOutput();

					for (TPArc tpA : t.getOutputs()) {
						if (invariantPlaces.contains(tpA.getOutput())) {
							listOfStrings.add(ptA.getName());
							listOfStrings.add(t.getName());
							listOfStrings.add(tpA.getName());
						}					
					}
				}
			}

			numInvariant++;
			list.put("P-invariant [" + String.format("%04d", numInvariant) + "]", listOfStrings);			
		}

		listOfInvariants = list.entrySet()
				.stream()
				.sorted(Map.Entry.<String, List<String>>comparingByKey())
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue, 
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		for (Map.Entry<String, List<String>> invariant : listOfInvariants.entrySet()) {
			System.out.println("[pnpl] " + invariant.toString());				
		}

//		// Refresh the invariant panel
//		InvariantPetriNetView view = InvariantPetriNetView.getInstance();
//		if (view != null) {
//			view.setListOfInvariants(listOfInvariants);
//			if(!view.isRunningViewAnalysis()) {
//				view.fillTable();
//			}
//		}
	}
}
