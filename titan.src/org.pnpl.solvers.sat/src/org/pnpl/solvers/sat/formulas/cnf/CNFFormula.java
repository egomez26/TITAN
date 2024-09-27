package org.pnpl.solvers.sat.formulas.cnf;

import java.util.ArrayList;
import java.util.List;

import org.pnpl.solvers.sat.formulas.ClauseFeature;


public class CNFFormula {
	private List<CNFClause> clauses = new ArrayList<>();

	public CNFFormula(List<CNFClause> cls) {
		this.clauses.addAll(cls);
	}

	public CNFFormula(ClauseFeature clauseFeature) {
		this.clauses.add(new CNFClause(clauseFeature)); 
	}

	public CNFFormula() {		
	}

	public List<CNFClause> clauses() {
		return this.clauses;
	}

	public void addClause(List<ClauseFeature> clause) {
		this.clauses.add(new CNFClause(clause));		
	}
	
	@Override public String toString() {
		String result = "";
		int i = 1;
		for (CNFClause cl : this.clauses) {
			result += "("+cl.toString()+")"+ (i == this.clauses.size() ? "" : " and\n");
			i++;
		}
		return result;
	}

	public void add(CNFFormula cnf) {
		if (cnf.clauses!=null)
			for (CNFClause cl : cnf.clauses)
				this.clauses.add(cl);
	}

	public CNFFormula disjunction(CNFFormula l2) {
		CNFFormula disjunction = new CNFFormula();
		for (CNFClause c1 : this.clauses) {
			for (CNFClause c2 : l2.clauses) {
				List<ClauseFeature> features = new ArrayList<>();
				features.addAll(c1.getLiterals());
				features.addAll(c2.getLiterals());
				disjunction.addClause(features);
			}
		}
		return disjunction;
	}
}
