package org.pnpl.analysis.analyzer;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.pnpl.analysis.featureide.helper.EMFHandler;
import org.pnpl.analysis.iterators.PetriNetsIterator;

import PetriNets.PetriNet;
import PetriNets.PetriNetsFactory;

/**
 * Abstract class for the analysis of all derivable products from PNPL
 * @author Elena Gomez-Martinez
 *
 */

public abstract class AbstractAnalysisProducts extends AbstractAnalysis {
	protected PetriNet pn = null;
	protected IFile currentFile = null; 
	protected Integer productID = 0;

	private static boolean StrongSatisfaction = true;
	
	@Override
	public boolean run() {
		if (vh == null) return false;
		if ((vh.getPetriNetFile() == null) || (vh.getFeatureModelFile() == null)) return false;
		
		System.out.println("[pnpl] Starting analysis of each product");

		PetriNetsIterator iterator = new PetriNetsIterator(vh);
		iterator.buildModels();

		EMFHandler emh =  new EMFHandler();
		boolean result = true;
		int count = 0;
		while(iterator.hasNext()) {
			IFile ifile = iterator.next();
			if (ifile != null) {
				System.out.println("[pnpl] Analysing product: " + ifile.getFullPath());
				
				this.currentFile = ifile;
				this.productID = count; 
				
				Resource r = emh.loadModel(ifile.getRawLocation().makeAbsolute().toFile(), PetriNetsFactory.eINSTANCE.getEPackage());
				pn = (PetriNet) r.getContents().get(0);

				count ++;
				if(!runProductAnalysis()) result = false;
				pn = null;
				if (!StrongSatisfaction && !result) break;
			}
		}
		System.out.println("[pnpl] Number of products analyzed: " + count);
		return result;
	}

	protected abstract boolean runProductAnalysis();
}
