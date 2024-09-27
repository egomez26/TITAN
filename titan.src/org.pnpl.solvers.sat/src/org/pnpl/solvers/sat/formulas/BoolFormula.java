package org.pnpl.solvers.sat.formulas;

import org.pnpl.solvers.sat.features.IFeatureProvider;
import org.pnpl.solvers.sat.formulas.cnf.CNFFormula;
import org.pnpl.solvers.sat.presenceConditions.Operator;

public abstract class BoolFormula {
	public abstract CNFFormula toCNF();
	public abstract boolean eval(IFeatureProvider fp);
	public Operator toOperator() {
		return null;
	}
}
