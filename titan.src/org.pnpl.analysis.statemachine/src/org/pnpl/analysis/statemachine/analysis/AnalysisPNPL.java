package org.pnpl.analysis.statemachine.analysis;

import java.util.List;

import org.pnpl.analysis.analyzer.AbstractAnalysisPNPL;
import org.pnpl.analysis.helpers.ClauseHelper;

import com.google.common.collect.Lists;

import PetriNets.PetriNet;
import PetriNets.PTArc;
import PetriNets.TPArc;
import PetriNets.Transition;

/**
 * @author Elena Gomez-Martinez
 *
 */

public class AnalysisPNPL extends AbstractAnalysisPNPL {

	@Override
	protected boolean buildCondition() {
		condition = "";
		PetriNet pn = vh.getPetriNet();
		if (pn == null) return false;

		for(Transition t : pn.getTrans()) {
			String pc = "";
			
			String pc_input = "";
			List<PTArc> inputs = t.getInputs();
			if(inputs != null) {
				if(inputs.size() > 1) {
					List<String> list = Lists.newArrayList();
					for (PTArc a : inputs) {						
						// PC of the arc
						String pc_arc = pclist.get(a.getName());	
						if(ClauseHelper.isBlank(pc_arc))
							// PC of the outgoing transition
							pc_arc = pclist.get(a.getOutput().getName());	
						
						if(!ClauseHelper.isBlank(pc_arc))
							list.add(pc_arc);		
					}
					// Some of the incoming arcs has no PC
					if (inputs.size() - list.size() > 0)						
						return false;
					
					pc_input = ClauseHelper.disjunctionOfConjunction(list);					
				}				
			}
			
			String pc_output = "";
			List<TPArc> outputs = t.getOutputs();
			if(outputs != null) {
				if(outputs.size() > 1) {
					List<String> list = Lists.newArrayList();
					for (TPArc a : outputs) {
						// PC of the arc
						String pc_arc = pclist.get(a.getName());	
						if(ClauseHelper.isBlank(pc_arc))
							// PC of the outgoing transition
							pc_arc = pclist.get(a.getInput().getName());	
						
						if(!ClauseHelper.isBlank(pc_arc))
							list.add(pc_arc);
					}
					
					// Some of the outgoing arcs has no PC
					if (outputs.size() - list.size() > 0) 
						return false;
					
					pc_output = ClauseHelper.disjunctionOfConjunction(list);
				}				
			}

			pc = ClauseHelper.conjunction(pc_input, pc_output);
						
			if(!ClauseHelper.isBlank(pc)) {
				String pc_trans = pclist.get(t.getName());
				if (!ClauseHelper.isBlank(pc_trans)) {
					pc = ClauseHelper.conjunction(pc_trans, pc);			
					pc = ClauseHelper.parenthesis(pc);
					pc_trans = ClauseHelper.negation(pc_trans);
					pc = ClauseHelper.disjunction(pc_trans, pc);
				}
				pc = ClauseHelper.parenthesis(pc);
			}
			condition = ClauseHelper.conjunction(condition, pc);		
		}
		return true;
	}

}
