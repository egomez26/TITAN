package org.pnpl.analysis.analyzer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.pnpl.solvers.sat.features.impl.FeatureProvider;
import org.pnpl.solvers.sat.formulas.cnf.CNFClause;
import org.pnpl.solvers.sat.formulas.cnf.CNFFormula;
import org.pnpl.solvers.sat.issues.ValidationIssue;
import org.pnpl.solvers.sat.presenceConditions.ConditionParser;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

/**
 * Abstract class for the analysis of a PNPL
 * @author Elena Gomez-Martinez
 *
 */

public abstract class AbstractAnalysisPNPL extends AbstractAnalysis {
	protected Map<String, String> pclist = null;	
	protected String condition = "";

	protected abstract boolean buildCondition();

	@Override
	public boolean run() {
		boolean result = false;
		
		if (vh != null) {
			System.out.println("[pnpl] Starting analysis of product lines");

			extractPresenceConditions();
			result = buildCondition();
			
			if (result) {
				result = (condition == null || condition.trim().isEmpty());
					
				if (!result) {
					System.out.println("[pnpl] Condition: "+ condition);
					
					FeatureProvider fp = new FeatureProvider(getFeatures()); 
					ConditionParser parser = new ConditionParser(condition,fp);
					Collection<ValidationIssue> errors = parser.parse();			
					System.out.println("[pnpl] Evaluation: "+ parser.getResult());
					System.out.println("[pnpl] Errors: "+ errors);
					
					CNFFormula formula = parser.getAST().toCNF(); 
					//System.out.println("[pnpl] CNF: " + formula+"\n");
					
					result = !doSomeSAT(formula, fp);
				}
			}
		}				
	
		return result;
	}
	
	private void extractPresenceConditions () {
		pclist = vh.extractPresenceConditions();
	}

	private String[] getFeatures() {
		List<String> flist = vh.getFeatureModel().getFeatureOrderList();
		String[] features = new String[flist.size()];
		features = flist.toArray(features);
		return features;
	}

	private boolean doSomeSAT(CNFFormula formula, FeatureProvider fp) {
		ISolver solver = SolverFactory.newDefault();
		solver.setTimeout(3600); // 1 hour timeout

		for (CNFClause c : formula.clauses()) {
			try {
				solver.addClause(c.toVecInt(fp.getFeatures()));
			} catch (ContradictionException e) {
				System.err.println("[pnpl] Contradiction");
				return false;
			}
		}

		IProblem problem = solver;
		try {
			if (problem.isSatisfiable()) {
				System.out.println("[pnpl] Problem is SAT");
				System.out.println("[pnpl] Model: ");
				int[] model = problem.findModel();
				for (int i = 0; i < model.length; i++) {
					System.out.println(" "+fp.getFeatures().get(Math.abs(model[i])-1)+" = "+
							((model[i] > 0) ? "true" : "false"));
				}
				return model.length > 0;
			}
		} catch (TimeoutException e) {
			System.err.println("[pnpl] Timeout!");
			e.printStackTrace();
		}
		return false;
	}
}
