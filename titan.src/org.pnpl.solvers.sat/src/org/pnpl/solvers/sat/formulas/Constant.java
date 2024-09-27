package org.pnpl.solvers.sat.formulas;

import org.pnpl.solvers.sat.features.IFeatureProvider;
import org.pnpl.solvers.sat.formulas.cnf.CNFFormula;

public class Constant extends Literal {
	private String value;

	public Constant (String v) {
		this.value = v;
	}
	
	@Override
	public CNFFormula toCNF() {
		return null;
	}
	
	@Override
	public boolean eval(IFeatureProvider fp) {
		return "true".equals(value.toLowerCase());
	}

	@Override public String toString() {
		return value.toLowerCase();
	}
}
