package org.pnpl.analysis.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Maps;

import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.base.IFeatureStructure;

import org.pnpl.analysis.featureide.composer.FeatureModelProvider;
import org.pnpl.analysis.featureide.helper.EMFHandler;

import variability.*;
import PetriNets.*;

/**
 * Auxiliary functions to work with variability files
 * @author Elena Gomez-Martinez
 *
 */

public class VariabilityHelper {
	private static final String pn_extension = ".petrinets";
	private static final String fm_extension = ".xml";

	private EMFHandler emh = null;	
	private PetriNet pn = null;
	private IFeatureModel fm = null;
	private Variability vrb = null;

	private IFile pn_file = null;
	private IFile fm_file = null;

	private Map<String, String> pclist = null;
	private Map<String, Expression> pcExpression = null;

	public VariabilityHelper() {
		this.emh = new EMFHandler();
	}
	
	public VariabilityHelper(IFile vrbfile) {
		this.emh = new EMFHandler();
		load(vrbfile);
	}

	public Variability getVariability() {
		return this.vrb;
	}

	public PetriNet getPetriNet() {
		return this.pn;
	}

	public IFile getPetriNetFile() {
		return  pn_file;
	}

	public IFeatureModel getFeatureModel() {
		return this.fm;
	}

	public IFile getFeatureModelFile() {
		return fm_file;
	}

	public boolean load(IFile vrbfile) {
		String pnfile=""; 
		String fmfile="";

		Map<String, String> pclist = Maps.newHashMap();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(vrbfile.getContents(true), vrbfile.getCharset()));) {
			String line = reader.readLine();

			while (line != null) {
				if(line.indexOf("pn")>=0) {	
					pnfile = line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""));
				}else if (line.indexOf("fm")>=0) {
					fmfile = line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""));
				}else if (line.indexOf("PC for")>=0){					
					String pc = line.replace("PC for","");
					Integer posequal = pc.indexOf("="); 

					StringTokenizer tokens = new StringTokenizer(pc.substring(0, posequal).trim(), ",");					
					while(tokens.hasMoreTokens()){
						pclist.put(tokens.nextToken().trim(), pc.substring(posequal+1,pc.lastIndexOf(";")).trim());
					}
				}
				line = reader.readLine();			
			}

			return loadVariability(((IFile) vrbfile).getProject(),pnfile,fmfile,pclist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}

	private boolean loadVariability(IProject project, String pnfile, String fmfile, Map<String, String> pclist) {
		File file;

		System.out.println("[pnpl] Loading variability");
		vrb = VariabilityFactory.eINSTANCE.createVariability();

		file = processContainer(project, pnfile, pn_extension);
		if (file != null) {
			FileURI fileURI = VariabilityFactory.eINSTANCE.createFileURI();
			fileURI.setImportURI(file.getPath());
			vrb.setPetrinet(fileURI);
			Resource r = emh.loadModel(file, PetriNetsFactory.eINSTANCE.getEPackage());
			pn = (PetriNet) r.getContents().get(0);

			IWorkspace workspace= ResourcesPlugin.getWorkspace();
			IPath location= Path.fromOSString(file.getAbsolutePath());
			pn_file= workspace.getRoot().getFileForLocation(location);
		} else {
			return false;
		}

		file = processContainer(project, fmfile, fm_extension);
		if (file != null) {
			FileURI fileURI = VariabilityFactory.eINSTANCE.createFileURI();
			fileURI.setImportURI(file.getPath());
			vrb.setFeaturemodel(fileURI);

			IWorkspace workspace= ResourcesPlugin.getWorkspace();
			IPath location= Path.fromOSString(file.getAbsolutePath());
			fm_file= workspace.getRoot().getFileForLocation(location);

			FeatureModelProvider fmp = new FeatureModelProvider(fm_file);
			fm = fmp.getFeatureModel();
		} else {
			return false;
		}

		if (pn == null)
			return false;

		readPresenceConditions(pclist);
		return true;
	}

	private File processContainer(IContainer container, String filename, String ext) {
		try {
			IResource[] members = container.members();

			for (IResource res : members) {
				if (res instanceof IContainer) {
					processContainer((IContainer)res, filename,ext);
				} else if (res instanceof IFile) {
					if (res.getName().endsWith(ext)) {
						if (res.getName().equals(filename)) {
							return res.getLocation().toFile();
						}
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void readPresenceConditions(Map<String, String> pclist) {
		Set<String> mapKeys = pclist.keySet();
		for (String key : mapKeys) {
			String condition = pclist.get(key);
			EObject element = searchElement(key);

			if (element != null) {
				PresenceCondition pc = VariabilityFactory.eINSTANCE.createPresenceCondition();	
				ExpressionHelper eh = new ExpressionHelper();
				Expression expr = eh.buildExpression(condition);

				pc.getElements().add(element);
				pc.setExpression(expr);
				vrb.getPresencecondition().add(pc);
			}
		}
	}

	private EObject searchElement(String key) {
		for(Place place : pn.getPlaces()) {
			if (place.getName().equals(key)) {
				return (EObject) place;
			}
		}

		for(Transition trans : pn.getTrans()) {
			if (trans.getName().equals(key)) {
				return (EObject) trans;
			}
		}

		for(Arc a : pn.getArcs()) {
			if (a.getName().equals(key)) {
				return (EObject) a;
			}
		}
		return null;
	}

	// Serialize in a temporary XML file 
	public URI serializeToXML() throws XMLStreamException, IOException {
		if (vrb == null) return null;

		try (FileOutputStream fos = new FileOutputStream("d:\\temp\\test.xml")){
			XMLOutputFactory xmlOutFact = XMLOutputFactory.newInstance();
			XMLStreamWriter writer = xmlOutFact.createXMLStreamWriter(fos);
			writer.writeStartDocument();
			writer.writeStartElement("test");
			// write stuff
			writer.writeEndElement();
		}
		return null;
	}

	public Map<String, String> extractPresenceConditions () {
		return doExtractPresenceConditions(false);
	}
	
	public Map<String, String> extractPresenceConditions (boolean asFunction) {
		return doExtractPresenceConditions(asFunction);
	}
	
	private Map<String, String> doExtractPresenceConditions (boolean asFunction) {
		pclist = Maps.newHashMap();	
		pcExpression = Maps.newHashMap();	

		for(PresenceCondition pc : vrb.getPresencecondition()) {
			for (EObject elem : pc.getElements()) {
				String key = "";
				if (elem instanceof Place) {
					key = ((Place) elem).getName();
				} else if (elem instanceof Transition) {
					key = ((Transition) elem).getName();
				} else if (elem instanceof Arc) {
					key = ((Arc) elem).getName();				
				}
				if (key != "") {
					pclist.put(key, extractExpression (fm,pc.getExpression(),asFunction));					
				}
			}	
		}
		return pclist;
	}

	public Map<String, Expression> extractExpressionList () {
		pcExpression = Maps.newHashMap();	

		for(PresenceCondition pc : vrb.getPresencecondition()) {
			for (EObject elem : pc.getElements()) {
				String key = "";
				if (elem instanceof Place) {
					key = ((Place) elem).getName();
				} else if (elem instanceof Transition) {
					key = ((Transition) elem).getName();
				} else if (elem instanceof Arc) {
					key = ((Arc) elem).getName();				
				}
				if (key != "") {
					pcExpression.put(key, pc.getExpression());
				}
			}	
		}
		return pcExpression;
	}

	public String extractExpression (IFeatureModel fm, EObject expr, boolean asFunction) {
		String value = "";
		if (expr instanceof Feature) { 
			value = extractParents(fm,((Feature) expr).getFeature(), asFunction);
		} else if (expr instanceof UnaryExpression){
			value = "(not " + extractExpression(fm,((UnaryExpression) expr).getRight(), asFunction) + ")";
		} else if (expr instanceof BinaryExpression) {
			BinaryExpression bin = (BinaryExpression) expr;
			if(bin.getOperator() == BinaryOperator.IMPLIES) {
				if (asFunction) {
					value = bin.getOperator().getLiteral().toLowerCase() + "(" 
							+ extractExpression(fm,bin.getLeft(),asFunction) + ", " 
							+ extractExpression(fm,bin.getRight(),asFunction) + ")";
				} else {
					value = "(" + extractExpression(fm,bin.getLeft(),asFunction) + " " 
							+ bin.getOperator().getLiteral().toLowerCase() +  " " 
							+ extractExpression(fm,bin.getRight(),asFunction) + ")";
				}
			} else {
				value = "(" + extractExpression(fm,bin.getLeft(),asFunction) + " " 
						+ bin.getOperator().getLiteral().toLowerCase() +  " " 
						+ extractExpression(fm,bin.getRight(),asFunction) + ")";
			}
		}
		return value;
	}

	private String extractParents(IFeatureModel fm, String key, boolean asFunction) {
		String value = "";

		IFeature f = fm.getFeature(key);
		if (f != null) {
			value = key;

			IFeatureStructure parent = f.getStructure().getParent();
			if (parent != null && parent != fm.getStructure().getRoot()) {
				String pkey = parent.getFeature().getName();
				String pp = extractParents(fm,pkey, asFunction);
				if (parent.isAnd()) {
					if (!asFunction) {
						if(f.getStructure().isMandatory()) 
							value = "((" + value + " implies " + pp + ") and (" + pp + " implies " + value + "))";
						else
							value = "(" + value + " implies " + pp + ")";
					} else {
						if(f.getStructure().isMandatory()) 
							value = "(implies(" + value + " , " + pp + ") and implies(" + pp + "," + value + "))";
						else
							value = "implies(" + value + " , " + pp + ")";
					}		
				}
			}
		} else
			System.err.println("[pnpl] Feature " + key + " doesn't exist in the model");
		return value;
	}
}
