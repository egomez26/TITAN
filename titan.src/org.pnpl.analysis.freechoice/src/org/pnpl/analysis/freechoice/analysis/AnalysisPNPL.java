package org.pnpl.analysis.freechoice.analysis;

import java.util.List;

import org.pnpl.analysis.analyzer.AbstractAnalysisPNPL;
import org.pnpl.analysis.helpers.ClauseHelper;
import org.pnpl.analysis.helpers.PetriNetsHelper;

import PetriNets.PTArc;
import PetriNets.PetriNet;
import PetriNets.Place;
import PetriNets.Transition;

/**
 * @author Elena Gomez-Martinez
 *
 */

public class AnalysisPNPL extends AbstractAnalysisPNPL {
	private PetriNetsHelper pnhelper = new PetriNetsHelper();
	
	@Override
	protected boolean buildCondition() {
		condition = "";
		PetriNet pn = vh.getPetriNet();
		if (pn == null) return false;
		
		for (Place p : pn.getPlaces()) {
			List<Transition> outputs = pnhelper.getOutputTransitions(p);	
			if(outputs != null) {
				if (outputs.size()>1) {
					String pc = "";					
					for (Transition t : outputs) {
						List<PTArc> arcs = t.getInputs();
						if(arcs != null) {
							if (arcs.size()>1) {
								for(PTArc a : arcs) {
									Place input = a.getInput();
									boolean isEqual = p.equals(input);
									if(isEqual) 
										pc = ClauseHelper.conjunction(pc, pclist.get(p.getName()));
									else
										pc = ClauseHelper.conjunction(pc, ClauseHelper.negation(pclist.get(p.getName())));							
								}
								pc = ClauseHelper.conjunction(pc, pclist.get(t.getName()));
							}							
						}												
					}
					pc = ClauseHelper.conjunction(pc, pclist.get(p.getName()));
					condition = ClauseHelper.conjunction(condition, pc);
				}
			}
		}	
		return true;
	}
}
