package org.pnpl.analysis.featureide.helper;

import com.google.inject.Guice;
import com.google.inject.Injector;

import PetriNets.PetriNet;
import PetriNets.Place;
import PetriNets.Transition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.serializer.impl.Serializer;

import org.pnpl.model.variability.editor.PNPL_variabilityRuntimeModule;

public class EMFHandler {
	private ResourceSet rs;
	private Injector injector;

	public EMFHandler() {
		injector = Guice.createInjector(new PNPL_variabilityRuntimeModule());
		rs = (ResourceSet) injector.getInstance(XtextResourceSet.class);		
		((XtextResourceSet) rs).addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	}
	
	public Resource loadModel(File model, EPackage p) {		
		URI uri = URI.createFileURI(model.getPath());
		if (p!=null) {
			rs.getPackageRegistry().put(p.getNsURI(), p);
		}
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("petrinets",new XMIResourceFactoryImpl());		
		Resource resource = rs.getResource(uri, true);
		return resource;
	}
	
	public EPackage getEPackage(Resource ecoreModel) {
		return (EPackage) ecoreModel.getContents().get(0);
	}
	
	public File getFileWithExtension(File folder, String ext) {
		File[] ecores = folder.listFiles((current, name) -> name.endsWith(ext));
		if (ecores == null) return null;
		return ecores.length==0 ? null : ecores[0]; 
	}
	
	public ResourceSet getModelAt(File folder) {
		File ecore = this.getFileWithExtension(folder, ".ecore");
		Resource ecoreModel = loadModel(ecore, null);
		File xmi = this.getFileWithExtension(folder, ".xmi");
		if (xmi==null) {
			System.err.println("Found no xmi file at "+folder);
			return null;
		} 
		return loadModel(xmi, this.getEPackage(ecoreModel)).getResourceSet();
	}
	
	public int getModelSize(Resource m) {
		List<EObject> content = m.getContents();
		return content.size();
	}

	public int getModelSizeInFolder(File folder) {
		File ecore = this.getFileWithExtension(folder, ".ecore");
		Resource ecoreModel = loadModel(ecore, null);
		File xmi = this.getFileWithExtension(folder, ".xmi");
		if (xmi==null) {
			System.err.println("Found no xmi file at "+folder);
			return 0;
		} 
		Resource r = loadModel(xmi, this.getEPackage(ecoreModel));
		return this.getModelSize(r);
	}
	
	public Resource loadXtextModel(File model) {
		Resource resource = rs.createResource(URI.createPlatformResourceURI(model.getAbsolutePath(), true));

		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,new XMIResourceFactoryImpl());
		
		InputStream in;
		try {
			in = new FileInputStream(model);
			resource.load(in, rs.getLoadOptions());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
		return resource;
	}

	public void removeAllPNObjects(Resource rs, List<EObject> toDelete) {
		if (toDelete.size()==0) return;
		PetriNet pn = (PetriNet)rs.getContents().get(0);
		pn.getPlaces().removeAll(toDelete);
		pn.getTrans().removeAll(toDelete);
		pn.getArcs().removeAll(toDelete);
		
		for (Transition tr : pn.getTrans()) {
			for (EObject obj : toDelete) {
				if (tr.getInputs().contains(obj))  tr.getInputs().remove(obj);
				if (tr.getOutputs().contains(obj)) tr.getOutputs().remove(obj);
			}
		}
		
		for (Place pl : pn.getPlaces()) {
			for (EObject obj : toDelete) {
				if (pl.getInputs().contains(obj))  pl.getInputs().remove(obj);
				if (pl.getOutputs().contains(obj)) pl.getOutputs().remove(obj);
			}
		}
	}
	
	public String object2xtext (EObject eobj) {
		if (eobj == null)
			return "";
		Serializer serializer = injector.getInstance(Serializer.class);  
		return serializer.serialize(eobj);
	}
}
