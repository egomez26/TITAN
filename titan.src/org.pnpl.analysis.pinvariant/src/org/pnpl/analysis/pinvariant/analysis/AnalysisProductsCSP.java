package org.pnpl.analysis.pinvariant.analysis;

import java.util.List;
import java.util.Set;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;
import org.pnpl.analysis.helpers.PetriNetsHelper;
import org.pnpl.analysis.invariant.analysis.InvariantCalculatorCSP;

public class AnalysisProductsCSP extends AbstractAnalysisProducts {
	@Override
	protected boolean runProductAnalysis() {
		if (pn == null) return false;
		
		System.out.println("[pnpl] P-Invariant with CSP");		
		
		PetriNetsHelper helper = new PetriNetsHelper();
		InvariantCalculatorCSP solver = new InvariantCalculatorCSP();
		
		Set<List<Integer>> calculated = solver.calculateInvariants(transposeMatrix(helper.getIncidenceMatrix(pn)));
		System.out.println("[pnpl] P-Invariant: The net has " + calculated.size());
		
		boolean hasInvariant = (calculated.size() > 0);
		if (hasInvariant) {
			for (List<Integer> inv : calculated) {
				System.out.println("[pnpl] P-Invariant: " + inv.toString());
			}	
		}
		return hasInvariant;
	}
	
	private static int[][] transposeMatrix(int[][] mat) {
		if (mat.length == 0) {
			return mat;
		}
		final int rows = mat.length;
		final int cols = mat[0].length;
		final int[][] matT = new int[cols][rows];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				matT[j][i] = mat[i][j];
			}
		}
		return matT;
	}
}
