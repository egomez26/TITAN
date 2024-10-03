package org.pnpl.analysis.markedgraph.analysis;

import java.util.List;

import org.pnpl.analysis.analyzer.AbstractAnalysisPNPL;
import org.pnpl.analysis.helpers.ClauseHelper;

import com.google.common.collect.Lists;

import PetriNets.PTArc;
import PetriNets.PetriNet;
import PetriNets.Place;
import PetriNets.TPArc;

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

		for(Place p : pn.getPlaces()) {
			String pc = "";

			String pc_input = "";
			List<TPArc> inputs = p.getInputs();
			if (inputs != null) {
				if(inputs.size() > 1) {					
					List<String> list = Lists.newArrayList();
					for (TPArc a : inputs) {
						// PC of the arc
						String pc_arc = pclist.get(a.getName());	
						if(ClauseHelper.isBlank(pc_arc))
							// PC of the outgoing transition
							pc_arc = pclist.get(a.getInput().getName());	

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
			List<PTArc> outputs = p.getOutputs();
			if (outputs != null) {				
				if(outputs.size() > 1) {
					List<String> list = Lists.newArrayList();
					for (PTArc a : outputs) {
						String pc_arc = pclist.get(a.getName());	// PC of the arc
						if(ClauseHelper.isBlank(pc_arc))
							pc_arc = pclist.get(a.getOutput().getName());	// PC of the outgoing transition

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
				String pc_place = pclist.get(p.getName());
				if (!ClauseHelper.isBlank(pc_place)) {
					pc = ClauseHelper.conjunction(pc_place, pc);			
					pc = ClauseHelper.parenthesis(pc);
					pc_place = ClauseHelper.negation(pc_place);
					pc = ClauseHelper.disjunction(pc_place, pc);
				}
				pc = ClauseHelper.parenthesis(pc);
			}

			condition = ClauseHelper.conjunction(condition, pc);			
		}
		return true;
	}

}
