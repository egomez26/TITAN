package org.pnpl.solvers.sat.formulas;

import org.pnpl.solvers.sat.features.IFeatureProvider;
import org.pnpl.solvers.sat.formulas.cnf.CNFFormula;
import org.pnpl.solvers.sat.presenceConditions.Operator;

public class NotFormula extends BoolFormula {
	private BoolFormula formula;
	public NotFormula(BoolFormula formula) {
		this.formula = formula;
	}
	@Override
	public CNFFormula toCNF() {
		if (formula instanceof ClauseFeature) {
			ClauseFeature cf = (ClauseFeature)formula;
			return new CNFFormula(cf.negate());
		}
		else if (formula instanceof AndFormula) {
			AndFormula af = (AndFormula)this.formula;
			return new OrFormula( new NotFormula(af.left()), new NotFormula(af.right())).toCNF(); 
		}
		else if (formula instanceof OrFormula) {
			OrFormula af = (OrFormula)this.formula;
			return new AndFormula( new NotFormula(af.left), new NotFormula(af.right)).toCNF();
		}
		else if (formula instanceof ImpliesFormula) {
			ImpliesFormula af = (ImpliesFormula)this.formula;
			return new AndFormula( af.left, new NotFormula( af.right)).toCNF();
		}
		else { // NOT
			NotFormula nf = (NotFormula)this.formula;
			return nf.formula.toCNF();
		}
		// no more cases!
	}
	
	@Override
	public boolean eval(IFeatureProvider fp) {
		return !this.formula.eval(fp);
	}
	
	public Operator toOperator() {
		return Operator.NOT;
	}
}
