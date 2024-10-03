package org.pnpl.analysis.tinvariant.analysis;


import java.util.List;
import java.util.Set;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;
import org.pnpl.analysis.helpers.PetriNetsHelper;
import org.pnpl.analysis.invariant.analysis.InvariantCalculatorCSP;

public class AnalysisProductsCSP extends AbstractAnalysisProducts {
	@Override
	protected boolean runProductAnalysis() {
		if (pn == null) return false;

		System.out.println("[pnpl] T-Invariant with CSP");		

		PetriNetsHelper helper = new PetriNetsHelper();
		InvariantCalculatorCSP solver = new InvariantCalculatorCSP();

		Set<List<Integer>> calculated = solver.calculateInvariants(helper.getIncidenceMatrix(pn));
		System.out.println("[pnpl] T-Invariant: The net has " + calculated.size());

		boolean hasInvariant = (calculated.size() > 0);
		if (hasInvariant) {
			for (List<Integer> inv : calculated) {
				System.out.println("[pnpl] T-Invariant: " + inv.toString());
			}	
		}
		return hasInvariant;
	}
}
