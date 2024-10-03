package org.pnpl.analysis.extendedfreechoice.analysis;

import java.util.List;

import org.pnpl.analysis.analyzer.AbstractAnalysisPNPL;
import org.pnpl.analysis.helpers.ClauseHelper;
import org.pnpl.analysis.helpers.PetriNetsHelper;

import com.google.common.collect.Lists;

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
		
		for (Transition t : pn.getTrans()) {
			List<Place> inputs = pnhelper.getInputPlaces(t);	
			if(inputs != null) {
				if (inputs.size()>1) {
					String pc = "";
					List<String> conflict = Lists.newArrayList();	
					
					for (Place p : inputs) {
						pc = ClauseHelper.conjunction(pc, pclist.get(p.getName()));
						for(PTArc arc : p.getOutputs()) {
							pc = ClauseHelper.conjunction(pc, pclist.get(arc.getOutput().getName()));
							String tmp = pclist.get(arc.getName());
							if(!ClauseHelper.isBlank(tmp))
								conflict.add(tmp);							
						}
					}
					
					String pcArcs = ClauseHelper.disjunctionOfConjunctionOneNegative(conflict);
					pc = ClauseHelper.conjunction(pc, pcArcs);
					condition = ClauseHelper.conjunction(condition, pc);
				}
			}
		}	
		return true;
	}
}
