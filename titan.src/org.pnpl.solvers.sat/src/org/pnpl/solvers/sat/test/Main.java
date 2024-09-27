package org.pnpl.solvers.sat.test;

import java.util.Collection;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.specs.IProblem;
import org.pnpl.solvers.sat.features.impl.FeatureProvider;
import org.pnpl.solvers.sat.formulas.cnf.CNFClause;
import org.pnpl.solvers.sat.formulas.cnf.CNFFormula;
import org.pnpl.solvers.sat.issues.ValidationIssue;
import org.pnpl.solvers.sat.presenceConditions.ConditionParser;

public class Main {

	public static void main(String[] args) {
		FeatureProvider fp = new FeatureProvider("AnotherF", "Base","OtherF");
		fp.setValue("f1", true);
		fp.setValue("f2", false);
		fp.setValue("f3", true);
				
		//ConditionParser parser = new ConditionParser("((f1 or f2) and f3) and f3", fp);
		ConditionParser parser = new ConditionParser("not ("
				+ "(AnotherF and (not Base) and (not OtherF)) or "
				+ "((not AnotherF) and Base and (not OtherF)) or "
				+ "((not AnotherF) and (not Base) and OtherF)"
				+ ")", fp);
		Collection<ValidationIssue> errors = parser.parse();
		CNFFormula formula = parser.getAST().toCNF();
		System.out.println("errors: "+errors);
		System.out.println("CNF: \n"+formula+"\n");
		System.out.println("eval: "+parser.getResult());
		
		doSomeSAT(formula, fp);
	}

	private static void doSomeSAT(CNFFormula formula, FeatureProvider fp) {
		ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(3600); // 1 hour timeout

        for (CNFClause c : formula.clauses()) {
        	try {
				solver.addClause(c.toVecInt(fp.getFeatures()));
			} catch (ContradictionException e) {
				System.err.println("Contradiction");
				e.printStackTrace();
			}
        }
        
        IProblem problem = solver;
        try {
			if (problem.isSatisfiable()) {
				System.out.println("Problem is SAT");
				System.out.println("Model: ");
				int[] model = problem.findModel();
				for (int i = 0; i < model.length; i++) {
					System.out.println(" "+fp.getFeatures().get(Math.abs(model[i])-1)+" = "+
										 ((model[i] > 0) ? "true" : "false"));
				}
			}
		} catch (TimeoutException e) {
			System.err.println("Timeout!");
			e.printStackTrace();
		}
	}

}
