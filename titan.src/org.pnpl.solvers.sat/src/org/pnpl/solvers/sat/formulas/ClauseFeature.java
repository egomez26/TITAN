package org.pnpl.solvers.sat.formulas;

import java.util.List;

import org.pnpl.solvers.sat.features.IFeature;
import org.pnpl.solvers.sat.features.IFeatureProvider;
import org.pnpl.solvers.sat.formulas.cnf.CNFFormula;

public class ClauseFeature extends Literal {

	private IFeature feat;
	private boolean negated;		
	
	public ClauseFeature(IFeature f) {
		this.feat = f;
		this.negated = false;
	}

	public ClauseFeature(IFeature f, boolean negated) {
		this.feat = f;
		this.negated = negated;
	}
	
	@Override public String toString() {
		if (this.feat==null) return "<none>";
		return this.negated ? "not "+this.feat.getName() : this.feat.getName();
	}

	public int toInteger(List<IFeature> dic) {
		return this.negated ? - (dic.indexOf(this.feat)+1) : (dic.indexOf(this.feat)+1);
	}

	public ClauseFeature negate() {
		this.negated = !this.negated;
		return this;
	}
	
	public boolean eval(IFeatureProvider fp) {
		if (this.feat==null) return false;
		boolean value = fp.getFeatureValue(this.feat.getName());
		return this.negated ? (! value ) : value;
	}	
	
	@Override
	public CNFFormula toCNF() {
		return new CNFFormula(this);
	}
	
	public String getName() {
		return this.feat.getName();
	}
	
	public boolean isNegated() {
		return this.negated;
	}
}
