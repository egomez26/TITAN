package org.pnpl.analysis.extendedfreechoice.analysis;

import java.util.List;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;
import org.pnpl.analysis.helpers.PetriNetsHelper;

import com.google.common.collect.Lists;

import PetriNets.Place;
import PetriNets.Transition;

/**
 * @author Elena Gomez-Martinez
 *
 */

public class AnalysisProducts extends AbstractAnalysisProducts {
	private PetriNetsHelper pnhelper = new PetriNetsHelper();

	@Override
	protected boolean runProductAnalysis() {
		if (pn == null) return false;

		for (Transition t : pn.getTrans()) {
			List<Place> inputs = pnhelper.getInputPlaces(t);

			if(inputs != null) {
				if (inputs.size()>1) {
					List<Transition> prev = Lists.newArrayList();	
					for (Place p : inputs) {
						if ((prev == null) || (prev.isEmpty()))
							prev = pnhelper.getOutputTransitions(p);
						else {
							if(!prev.equals(pnhelper.getOutputTransitions(p))) return false;
						}				
					}				
				}
			}			
		}
		return true;
	}
}
