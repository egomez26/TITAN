package org.pnpl.analysis.freechoice.analysis;

import java.util.List;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;
import org.pnpl.analysis.helpers.PetriNetsHelper;

import PetriNets.PTArc;
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

		for (Place p : pn.getPlaces()) {
			List<Transition> outputs = pnhelper.getOutputTransitions(p);

			if(outputs != null) {
				if (outputs.size()>1) {
					for (Transition t : outputs) {
						List<PTArc> inputs = t.getInputs();
						if(inputs != null) {
							if(inputs.size() > 1) {
								return false;
							}				
						}								
					}				
				}
			}
		}
		return true;
	}
}
