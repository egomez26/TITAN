package org.pnpl.analysis.markedgraph.analysis;

import java.util.List;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;

import PetriNets.PTArc;
import PetriNets.Place;
import PetriNets.TPArc;

/**
 * @author Elena Gomez-Martinez
 *
 */

public class AnalysisProducts extends AbstractAnalysisProducts {

	@Override
	protected boolean runProductAnalysis() {
		if (pn == null) return false;

		for(Place p : pn.getPlaces()) {
			List<TPArc> inputs = p.getInputs();
			if(inputs != null) {
				if(inputs.size() > 1) {
					return false;
				}				
			}	

			List<PTArc> outputs = p.getOutputs();
			if(outputs != null) {
				if(outputs.size() > 1) {
					return false;
				}				
			}
		}
		return true;
	}
}
