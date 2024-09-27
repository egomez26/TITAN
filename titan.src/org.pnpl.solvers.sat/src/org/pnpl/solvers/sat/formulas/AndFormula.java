package org.pnpl.solvers.sat.formulas;

import java.util.List;

import org.pnpl.solvers.sat.features.IFeatureProvider;
import org.pnpl.solvers.sat.formulas.cnf.CNFClause;
import org.pnpl.solvers.sat.formulas.cnf.CNFFormula;
import org.pnpl.solvers.sat.presenceConditions.Operator;

public class AndFormula extends BinaryFormula{
		
	public AndFormula(BoolFormula l, BoolFormula r) {
		this.left = l;
		this.right = r;
	}

	@Override
	public CNFFormula toCNF() {
		List<CNFClause> clauses = this.left.toCNF().clauses();
		clauses.addAll(this.right.toCNF().clauses());
		return new CNFFormula(clauses);
	}

	@Override
	public boolean eval(IFeatureProvider fp) {
		return this.left.eval(fp) && this.right.eval(fp);
	}
	
	public Operator toOperator() {
		return Operator.AND;
	}

}
