package org.pnpl.analysis.iterators;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.featureide.core.IFeatureProject;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.localization.StringTable;
import de.ovgu.featureide.ui.actions.generator.ConfigurationBuilder;
import de.ovgu.featureide.ui.actions.generator.IConfigurationBuilderBasics.BuildType;
import de.ovgu.featureide.ui.actions.generator.IConfigurationBuilderBasics.OutputType;
import de.ovgu.featureide.ui.actions.generator.IConfigurationBuilderBasics.BuildOrder;

import org.pnpl.analysis.featureide.composer.FeatureModelProvider;
import org.pnpl.analysis.featureide.composer.PetriNetsComposer;
import org.pnpl.analysis.helpers.VariabilityHelper;

/**
 * Iterator over the product metamodels of a 150 Petri Net. 
 * It relies on FeatureIDE to improve performance.
 * It persists the products metamodels.
 * @author Elena Gomez-Martinez
 *
 */

public class PetriNetsIterator {
	protected IFile ecore;
	protected ConfigurationBuilder builder;
	protected long current = -1;
	protected IFile feature = null;
	protected IFeatureProject project = null;
	protected VariabilityHelper vh = null;
	
	// constructor for 150mm
	public PetriNetsIterator (VariabilityHelper vh) {
		this.vh = vh;
		this.ecore = vh.getPetriNetFile();
		this.feature = vh.getFeatureModelFile();
		
		this.project = new FeatureProjectWrapper(ecore,feature) ;	
		this.project.checkForProblems();
		
		// EGM: Adapting to FeatureIDE 3.11		
		this.builder = new ConfigurationBuilder(project, 
				BuildType.ALL_VALID, 
				OutputType.PRODUCT, 
				StringTable.ICPL, 2/*T*/,
				BuildOrder.DEFAULT, 
				false /* runTest*/,
				null /* featureName */,
				Integer.MAX_VALUE /* maxConfigs */,
				2 /*tOrder --> TInteraction*/);
		this.current = 0;
		//this.buildModels();
	}
	
	public void buildModels () {
		FeatureModelProvider fmp = new FeatureModelProvider(feature);
		IFeatureModel fm = fmp.getFeatureModel();
		
		FeatureConfigurationIterator featIter = new FeatureConfigurationIterator(fm);
		
		PetriNetsComposer pncomposer = new PetriNetsComposer(vh.getVariability());
		pncomposer.initialize(this.project);
		
		int i=0;
		Configuration cfg = featIter.next();
		while(cfg != null) {
			i++;			
			IFolder ifolder = ecore.getProject().getFolder(folder(i)); 
			pncomposer.buildConfiguration(ifolder, cfg, ecore.getProject().getFile(folder(i)).getName() + ".petrinets");
			cfg = featIter.next();
		}
	}
	
	public long configurationNumber() {
		return this.builder.configurationNumber;
	}
	
	public boolean hasNext () { 
		return this.current < this.builder.configurationNumber; 
	}
	
	public IFile next() {
		return hasNext()? readMetamodel(++current) : null;
	}	
	
	public IFile same() {
		return this.current<=this.builder.configurationNumber? readMetamodel(current) : null;
	}	

	public String path() {
		return this.current<=this.builder.configurationNumber? ecore.getProject().getFile(folder(current)).getFullPath().toOSString() : null;
	}	
	
	private IFile readMetamodel (long index) {
		IFile ifile = null;
		
		ifile = ecore.getProject().getFile(folder(index) + File.separator + "150mm.petrinets");
		while (!ifile.exists() || !ifile.isSynchronized(IResource.DEPTH_ZERO)) {
			try {
				ifile.refreshLocal(IResource.DEPTH_ZERO, null);			
			} 
			catch (CoreException e) { e.printStackTrace(); }
		}
		return ifile;
	}	

	private String folder (long index) {
		return "products" + File.separator + String.format("%05d", index);
	}
}
