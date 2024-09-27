package org.pnpl.solvers.sat.formulas.cnf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IVecInt;
import org.pnpl.solvers.sat.features.IFeature;
import org.pnpl.solvers.sat.formulas.ClauseFeature;

public class CNFClause {
	private List<ClauseFeature> featuresliterals = new ArrayList<>();
	
	public CNFClause(ClauseFeature ...cf) {
		this.featuresliterals.addAll(Arrays.asList(cf));
	}
	
	public List<ClauseFeature> getLiterals() {
		return this.featuresliterals;
	}
	
	public CNFClause(List<ClauseFeature> clause) {
		this.featuresliterals.addAll(clause);
	}

	public IVecInt toVecInt(List<IFeature> dict) {
		int[] intRep = new int[this.featuresliterals.size()];
		int idx = 0;
		for (ClauseFeature cf : this.featuresliterals) {
			intRep[idx++] = cf.toInteger(dict);
		}
		return new VecInt(intRep);
	}
	
	@Override public String toString() {
		String result = "";
		boolean first = true;
		for (ClauseFeature cf : this.featuresliterals) {
			if (!first) {
				result += " or ";
			} else first = false;
			result += cf.toString();
		}
		return result;
	}
}
