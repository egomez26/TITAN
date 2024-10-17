package org.pnpl.export.cpntools.exporters;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.ovgu.featureide.fm.core.base.IConstraint;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.base.IFeatureStructure;

import PetriNets.*;
import variability.Expression;
import org.pnpl.analysis.helpers.ExpressionHelper;
import org.pnpl.export.exporters.AbstractExportPNPL;

public class ExportPNPL extends AbstractExportPNPL {
	private static String outputFile = "pnpl.cpn";
	private static String placeConfiguration = "CONFIG";
	private static String placeCurrent = "CURRENT";

	private static String AND = " andalso ";
	private static String OR = " orelse ";
	private static String NOT = "not ";
	private static String IMPLIES = "implies";
	private static String IFF = "iff";
	private static String TRUE = "true";
	private static String FALSE = "false";

	public static String placePrefix = "PL_";
	private static String featurePrefix = "FEAT_";
	private static String variablePrefix = "VAR_";
	private static String paramPrefix = "PARAM_";
	
	private String strFeatures = "";
		
	private BufferedOutputStream stream = null;
	@SuppressWarnings("unused")
	private File folder = null;
	
	@Override
	protected boolean export(File dstFolder, Monitor monitor) throws IOException {
		this.folder = dstFolder;
		this.exportedFileName = outputFile;	
		
		monitor.subTask("Loading...");
		generate(monitor);		
		
		return true;
	}
		
	private void generate(Monitor monitor) throws IOException {
		Display display = new Display();
		final Shell shell = new Shell(display);
		selectConfiguration(shell);		
		
		monitor.beginTask("PNPL to CPN", 100);
		
		try {
			stream = new BufferedOutputStream(new FileOutputStream(getExportedFile()));			
			
			generateHeader();
			monitor.worked(10);
			generateGlobBox("\t\t");
			monitor.worked(10);
			generatePage("\t\t");
			monitor.worked(10);
			generateInstances("\t\t");
			monitor.worked(10);
			generateOptions("\t\t");
			monitor.worked(10);
			generateBinders("\t\t");
			monitor.worked(10);
			generateMonitorBlock("\t\t");
			monitor.worked(10);
			generateIndexNode("\t\t");
			monitor.worked(10);
			generateFooter();

			stream.close(); 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		monitor.done();
		display.dispose();
	}

	private void writeFile(String line) throws IOException {
		stream.write(line.getBytes());
		stream.write(System.lineSeparator().getBytes());
	}

	// Header block
	private void generateHeader() throws IOException {
		writeFile("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
		writeFile("<!DOCTYPE workspaceElements PUBLIC \"-//CPN//DTD CPNXML 1.0//EN\" \"http://cpntools.org/DTD/6/cpn.dtd\">");
		writeFile("<workspaceElements>");
		writeFile("\t<generator tool=\"CPN Tools\" version=\"4.0.1\" format=\"6\"/>");
		writeFile("\t<cpnet>");
	}

	// Footer block
	private void generateFooter() throws IOException {
		writeFile("\t</cpnet>");
		writeFile("</workspaceElements>");
	}

	// GlobBox block
	private void generateGlobBox(String indent) throws IOException {
		writeFile(indent + "<globbox>");
		generateStandardValues(indent + "\t");		

		// Starting PNPL block
		writeFile(indent + "<block id=\"ID1413114063\">");				
		writeFile(indent + "\t<id>PNPL</id>");		

		generateColors(indent + "\t");

		// Functions for the guards
		generateFunctions(indent + "\t");

		// Ending the PNPL block
		writeFile(indent + "</block>");

		// Ending globbox
		writeFile(indent + "</globbox>");		
	}

	private void generateStandardValues(String indent) throws IOException {
		// Default types
		writeFile(indent + "<block id=\"ID1412310166\">");		
		writeFile(indent + "\t<id>Standard priorities</id>");
		writeFile(indent + "\t<ml id=\"ID1412310255\">val P_HIGH = 100;");
		writeFile(indent + "\t\t<layout>val P_HIGH = 100;</layout>");
		writeFile(indent + "\t</ml>");
		writeFile(indent + "\t<ml id=\"ID1412310292\">val P_NORMAL = 1000;");
		writeFile(indent + "\t\t<layout>val P_NORMAL = 1000;</layout>");
		writeFile(indent + "\t</ml>");
		writeFile(indent + "\t<ml id=\"ID1412310322\">val P_LOW = 10000;");
		writeFile(indent + "\t\t<layout>val P_LOW = 10000;</layout>");
		writeFile(indent + "\t</ml>");
		writeFile(indent + "</block>");

		writeFile(indent + "<block id=\"ID1\">");
		writeFile(indent + "\t<id>Standard declarations</id>");

		writeFile(indent + "\t<color id=\"ID85042\">");
		writeFile(indent + "\t\t<id>UNIT</id>");
		writeFile(indent + "\t\t<unit/>");
		writeFile(indent + "\t\t<layout>colset UNIT = unit;</layout>");
		writeFile(indent + "\t</color>");

		writeFile(indent + "\t<color id=\"ID4\">");
		writeFile(indent + "\t\t<id>BOOL</id>");
		writeFile(indent + "\t\t<bool/>");
		writeFile(indent + "\t</color>");

		writeFile(indent + "\t<color id=\"ID3\">");		
		writeFile(indent + "\t\t<id>INT</id>");		
		writeFile(indent + "\t\t<int/>");		
		writeFile(indent + "\t</color>");		

		writeFile(indent + "\t<color id=\"ID1412312409\">");		
		writeFile(indent + "\t\t<id>INTINF</id>");		
		writeFile(indent + "\t\t<intinf/>");		
		writeFile(indent + "\t\t<layout>colset INTINF = intinf;</layout>");		
		writeFile(indent + "\t</color>");		

		writeFile(indent + "\t<color id=\"ID1412312425\">");		
		writeFile(indent + "\t\t<id>TIME</id>");		
		writeFile(indent + "\t<time/>");		
		writeFile(indent + "\t\t<layout>colset TIME = time;</layout>");		
		writeFile(indent + "\t</color>");		

		writeFile(indent + "\t<color id=\"ID1412322990\">");		
		writeFile(indent + "\t\t<id>REAL</id>");		
		writeFile(indent + "\t\t<real/>");		
		writeFile(indent + "\t\t<layout>colset REAL = real;</layout>");		
		writeFile(indent + "\t</color>");		

		writeFile(indent + "\t<color id=\"ID5\">");		
		writeFile(indent + "\t\t<id>STRING</id>");		
		writeFile(indent + "\t\t<string/>");		
		writeFile(indent + "\t</color>");		

		writeFile(indent + "</block>");
	}

	private void generateColors(String indent) throws IOException {
		writeFile(indent + "\t<block id=\"ID1413113251\">");		
		writeFile(indent + "\t<id>COLORS</id>");		
		
		// TOKEN timed
		writeFile(indent + "\t\t<color id=\"ID1413129378\">");
		writeFile(indent + "\t\t<id>TOKEN</id>");	
		writeFile(indent + "\t\t\t<timed/>");
		writeFile(indent + "\t\t\t<int/>");		
		writeFile(indent + "\t\t<layout>colset TOKEN = int timed;</layout>");	
		writeFile(indent + "\t</color>");
		
		// Colors from features 		
		writeFile(indent + "\t<color id=\"ID1413113141\">");		
		writeFile(indent + "\t\t<id>FEATURE</id>");		
		writeFile(indent + "\t\t<alias>");		
		writeFile(indent + "\t\t\t<id>BOOL</id>");		
		writeFile(indent + "\t\t</alias>");		
		writeFile(indent + "\t\t<layout>colset FEATURE = BOOL;</layout>");		
		writeFile(indent + "\t</color>");

		// FM tree as a CONFIGURATION
		generateConfiguration(indent + "\t");		

		// Ending colors from features
		writeFile(indent + "\t</block>");		

		// Constants
		writeFile(indent + "\t<block id=\"ID1413113462\">");				
		writeFile(indent + "\t\t<id>CONSTANTS</id>");		
		writeFile(indent + "\t\t<ml id=\"ID1413113727\">val initial = 1;");		
		writeFile(indent + "\t\t\t<layout>val initial = 1;</layout>");		
		writeFile(indent + "\t\t</ml>");				
		writeFile(indent + "\t</block>");		

		// Variables
		writeFile(indent + "\t<block id=\"ID1413114454\">");				
		writeFile(indent + "\t\t<id>VARIABLES</id>");		

		writeFile(indent + "\t\t<var id=\"ID1412569443\">");
		writeFile(indent + "\t\t\t<type>");
		writeFile(indent + "\t\t\t\t<id>CONFIGURATION</id>");		
		writeFile(indent + "\t\t\t</type>");	
		writeFile(indent + "\t\t<id>d</id>");	
		writeFile(indent + "\t\t<layout>var d : CONFIGURATION;</layout>");		
		writeFile(indent + "\t</var>");		

		writeFile(indent + "\t\t<var id=\"ID1413114508\">");		
		writeFile(indent + "\t\t\t<type>");		
		writeFile(indent + "\t\t\t\t<id>CONFIGURATION</id>");		
		writeFile(indent + "\t\t\t</type>");		
		writeFile(indent + "\t\t<id>c</id>");		
		writeFile(indent + "\t\t<layout>var c : CONFIGURATION;</layout>");		
		writeFile(indent + "\t</var>");		

		generateVariables(indent + "\t\t");		
		writeFile(indent + "\t</block>");	
	}

	private void generateFunctions(String indent) throws IOException {
		writeFile(indent + "<block id=\"ID1413115140\">");
		writeFile(indent + "\t<id>FUNCTIONS</id>");

		writeFile(indent + "\t<ml id=\"FUN_IMPLIES\">fun " + IMPLIES + "(p,q) = not p orelse q;");
		writeFile(indent + "\t\t<layout>fun implies (p,q) = not p orelse q;</layout>");
		writeFile(indent + "\t</ml>");

		writeFile(indent + "\t<ml id=\"FUN_IFF\">fun " + IFF + "(p, q) = " + IMPLIES + "(p, q) andalso " + IMPLIES + "(q, p);");
		writeFile(indent + "\t\t<layout>fun iff (p, q) = ((not p orelse q) andalso (p orelse not q));</layout>");
		writeFile(indent + "\t</ml>");

		String strParam = getFeatureList(paramPrefix);
		String strValid = generateFunctionValidFromRoot(fm.getStructure().getRoot());

		// Valid each configuration
		writeFile(indent + "\t<ml id=\"FUN_ISVALID\">fun isValid (" + strParam + ") =  " + strValid + ";");
		writeFile(indent + "\t\t<layout>fun isValid ("+ strParam +") = " + strValid + ";</layout>");
		writeFile(indent + "\t</ml>");

		writeFile(indent + "</block>");		
	}

	private String getFeatureList(String prefix) {
		List<String> featureList = Lists.newArrayList();

		for(IFeature feat : fm.getFeatures()) {
			if (feat.getStructure() != fm.getStructure().getRoot())
				featureList.add(prefix + feat);
			else
				featureList.add(0,prefix + feat);
		}		

		String result = featureList.stream()
				.collect(Collectors.joining(","));
		return result;
	}

	private String generateFunctionValidFromRoot(IFeatureStructure parent) {
		String clause = generateFunctionValid(parent);

		String constraints = generateFeatureModelConstraints();
		if (!constraints.isEmpty())
			constraints = AND + constraints;
		clause = clause + constraints;
		return clause;
	}

	private String generateFunctionValid(IFeatureStructure parent) {
		String clause = "";

		if(parent != null) {
			if (parent.getChildrenCount() > 0) {
				List<String> childrenList = Lists.newArrayList();
				List<String> childrenClauses = Lists.newArrayList();

				for (IFeatureStructure child : parent.getChildren()) {
					String relationWithParent = "";

					if (parent.isAnd()) {
						if (child.isMandatory()) 
							relationWithParent = IFF + "(" + paramPrefix + child.getFeature().getName() + "," + paramPrefix + parent.getFeature().getName() + ")"; 
						else 
							relationWithParent = IMPLIES + "(" + paramPrefix + child.getFeature().getName() + "," + paramPrefix + parent.getFeature().getName() + ")";						

					} else if (parent.isOr() || parent.isAlternative()) {
						relationWithParent = paramPrefix + child.getFeature().getName();	
					}

					childrenList.add(relationWithParent);					
					String tmp = generateFunctionValid(child);
					if (!tmp.isEmpty())
						childrenClauses.add(tmp);
				}

				if (parent.isOr()) {
					clause = childrenList.stream()
							.collect(Collectors.joining(OR));
				} else if (parent.isAlternative())  {
					clause = "(" + exclusiveOR(paramPrefix + parent.getFeature().getName(),childrenList) + ")";
				} else if (parent.isAnd()) {
					clause =childrenList.stream()
							.collect(Collectors.joining(AND));
				}

				String childrenStr = childrenClauses.stream()
						.collect(Collectors.joining(AND));

				if (!childrenStr.isEmpty())
					clause = clause + AND + childrenStr;
				clause = "(" + clause + ")";
			}
		}

		return clause;
	}

	private String generateFeatureModelConstraints() {
		String constraint = "";
		ExpressionHelper helper = new ExpressionHelper();

		for(IConstraint ic : fm.getConstraints()) {
			Expression expr = helper.buildExpression(ic.getNode());
			if (!constraint.isEmpty())
				constraint = constraint + " and ";
			constraint = constraint + vrbHelper.extractExpression(fm, expr,true);		
			vrbHelper.extractExpression(fm, expr, true);
		}

		return formatPresenceCondition(constraint,false);
	}

	// p <-> q
	private String exclusiveOR (String parent, List<String> paramList) {
		String clause = "";

		for (String l : paramList) {
			String partialClause = "";

			for (String other : paramList) {
				if (l != other) {
					if (!partialClause.isEmpty()) 
						partialClause = clause + AND;
					partialClause = partialClause + NOT + other;  
				}
			}
			if (!partialClause.isEmpty())
				partialClause = partialClause + AND;
			partialClause = IFF + "(" + l + "," + partialClause + parent + ")";

			if (!clause.isEmpty())
				clause = clause + AND;
			clause = clause + partialClause;
		}
		return clause;
	}

	private void generateConfiguration(String indent) throws IOException {
		String record = "";

		writeFile(indent + "<color id=\"ID1413113171\">");		
		writeFile(indent + "\t<id>CONFIGURATION</id>");		
		writeFile(indent + "\t<record>");		

		for(IFeature feat : fm.getFeatures()) {
			if (feat.getStructure() != fm.getStructure().getRoot()) {
				String sFeature = featurePrefix + feat.getName();

				if (!record.isEmpty())
					record = record + " * \n\t\t";
				record = record + sFeature + " : FEATURE";

				writeFile(indent + "\t\t<recordfield>");			
				writeFile(indent + "\t\t\t<id>" + sFeature + "</id>");			
				writeFile(indent + "\t\t\t<id>FEATURE</id>");			
				writeFile(indent + "\t\t</recordfield>");

				if (!strFeatures.isEmpty())
					strFeatures = strFeatures + ",";
				strFeatures = strFeatures + "#" + featurePrefix + feat + " c";
			}
		}
		writeFile(indent + "\t</record>");		
		writeFile(indent + "\t<layout>colset CONFIGURATION = record " + record + ";</layout>");
		writeFile(indent + "</color>");
	}

	private void generateVariables(String indent) throws IOException {
		for(IFeature feat : fm.getFeatures()) {
			if (feat.getStructure() != fm.getStructure().getRoot()) {
				String variableName = variablePrefix + feat.getName();

				writeFile(indent + "<var id=\"" + variableName + "\">");			
				writeFile(indent + "\t<type>");			
				writeFile(indent + "\t\t<id>FEATURE</id>");			
				writeFile(indent + "\t</type>");			
				writeFile(indent + "\t<id>" + variablePrefix + feat + "</id>");			
				writeFile(indent + "\t<layout>var " + variableName + " : FEATURE;</layout>");			
				writeFile(indent + "</var>");
			}
		}
	}

	private void generatePage(String indent) throws IOException {		
		writeFile(indent + "<page id=\"ID6\">");		
		writeFile(indent + "\t<pageattr name=\"New Page\"/>");		

		generateCPN(indent + "\t");
		generateConstraints(indent + "\t");

		writeFile(indent + "</page>");		
	}

	private void generateConstraints(String indent) throws IOException {
		writeFile(indent + "<constraints/>");		
	}

	private void generateInstances(String indent) throws IOException {
		writeFile(indent + "<instances>");		
		writeFile(indent + "\t<instance id=\"ID2149\" page=\"ID6\"/>");		
		writeFile(indent + "</instances>");		
	}

	private void generateOptions(String indent) throws IOException {
		String[] stringOptions = {"realtimestamp", FALSE,
				"fair_be", FALSE,
				"global_fairness", FALSE,
				//"outputdirectory", "&lt;same as model&gt;",
				"extensions.10005.enable", TRUE,
				"extensions.10001.enable", TRUE,
				"extensions.10003.enable", TRUE,
				"extensions.10005.enable", TRUE,
				"extensions.10002.enable", TRUE,
				"extensions.10006.enable", TRUE,
				"repavg", TRUE,
				"repciavg", TRUE,
				"repcount", FALSE,
				"repfirstval", FALSE, 
				"replastval", FALSE,
				"repmax", TRUE,
				"repmin", TRUE,
				"repssquare", FALSE, 
				"repssqdev", FALSE, 
				"repstddev",TRUE,
				"repsum", FALSE,
				"repvariance", FALSE,
				"avg", TRUE,
				"ciavg", FALSE,
				"count", TRUE,
				"firstval", FALSE,
				"lastval", FALSE,
				"max", TRUE,
				"min", TRUE,
				"ssquare", FALSE,
				"ssqdev", FALSE,
				"stddev", FALSE,
				"sum", FALSE,
				"variance", FALSE,
				"firstupdate", FALSE,
				"interval", FALSE,
				"lastupdate", FALSE,
				"untimedavg", TRUE,
				"untimedciavg", FALSE,
				"untimedcount", TRUE,
				"untimedfirstval", FALSE,
				"untimedlastval", FALSE,
				"untimedmax", TRUE,
				"untimedmin", TRUE,
				"untimedssquare", FALSE,
				"untimedssqdev", FALSE,
				"untimedstddev", FALSE,
				"untimedsum", TRUE,
				"untimedvariance", FALSE};

		writeFile(indent + "<options>");

		for (int i=0; i < stringOptions.length; i += 2) {
			writeFile(indent + "\t<option name=\"" + stringOptions[i] + "\">");
			writeFile(indent + "\t\t<value>");
			writeFile(indent + "\t\t\t<boolean>" + stringOptions[i+1] + "</boolean>");
			writeFile(indent + "\t\t</value>");
			writeFile(indent + "\t</option>");
		}

		writeFile(indent + "\t<option name=\"outputdirectory\">");		
		writeFile(indent + "\t\t<value>");		
		writeFile(indent + "\t\t\t<text>\"&lt;same as model&gt;\"</text>");		
		writeFile(indent + "\t\t</value>");		
		writeFile(indent + "\t</option>");		
		writeFile(indent + "</options>");

	}

	private void generateBinders(String indent) throws IOException {
		writeFile(indent + "<binders>");		
		writeFile(indent + "\t<cpnbinder id=\"ID2222\" x=\"428\"\r\n" + 
				"               y=\"274\"\r\n" + 
				"               width=\"600\"\r\n" + 
				"               height=\"400\">");		
		writeFile(indent + "\t\t<sheets>");		
		writeFile(indent + "\t\t\t<cpnsheet id=\"ID2215\" panx=\"-6.000000\" pany=\"-5.000000\" zoom=\"1.000000\" instance=\"ID2149\">");		
		writeFile(indent + "\t\t\t\t<zorder>");		
		writeFile(indent + "\t\t\t\t\t<position value=\"0\"/>");		
		writeFile(indent + "\t\t\t\t</zorder>");		
		writeFile(indent + "\t\t\t</cpnsheet>");		
		writeFile(indent + "\t\t</sheets>");		
		writeFile(indent + "\t\t<zorder>");		
		writeFile(indent + "\t\t\t<position value=\"0\"/>");		
		writeFile(indent + "\t\t</zorder>");		
		writeFile(indent + "\t</cpnbinder>");		
		writeFile(indent + "</binders>");		
	}

	private void generateMonitorBlock(String indent) throws IOException {
		writeFile(indent + "<monitorblock name=\"Monitors\"/>");		
	}

	private void generateIndexNode(String indent) throws IOException {
		writeFile(indent + "<IndexNode expanded=\"false\">");		
		writeFile(indent + "</IndexNode>");		
	}

	private void generateCPN(String indent) throws IOException {
		writeFile(indent + "<!-- List of places -->");		
		for(Place p : pn.getPlaces()) {
			generatePlace(indent,p);
		}

		writeFile(indent + "<!-- Places for the configuration -->");
		String configurationMarking = generateConfigurationMarking(fm);
		generatePlace(indent,placeConfiguration,"CONFIGURATION",1, configurationMarking);
		generatePlace(indent,placeCurrent,"CONFIGURATION",1,configurationMarking);

		writeFile(indent + "<!-- List of transitions -->");		
		for(Transition t : pn.getTrans()) {
			generateTransition(indent,t);
		}

		if (configurationList.isEmpty()) {
			writeFile(indent + "<!-- Transitions for switching the configuration -->");		
			generateConfigurationSwitch(indent);			
		}
		
		writeFile(indent + "<!-- Transition for validing the configuration -->");		
		generateConfigurationValid(indent);

		writeFile(indent + "<!-- List of arcs -->");		
		for(Arc arc : pn.getArcs()) {
			generateArc(indent,arc);
		}

		if (configurationList.isEmpty()) {
			writeFile(indent + "<!-- Arcs for connecting switches -->");		
			generateSwitchingArcs(indent);
		}
		writeFile(indent + "<!-- Arc for validing configuration -->");		
		generateConfigurationValidArc(indent,configurationList.isEmpty());

		writeFile(indent + "<!-- Arc for passing to current configuration -->");		
		generateConfigurationArcs(indent);
	}

	private String generateConfigurationMarking(IFeatureModel fm) {
		String marking = "";

		if (configurationList.isEmpty()) {
			String m = "";

			for(IFeature feat : fm.getFeatures()) {
				if (feat.getStructure() != fm.getStructure().getRoot()) {
					if (!m.isEmpty()) 
						m = m + ", ";
					m = m + featurePrefix + feat.getName() + " = " + TRUE;
				}
			}
			if (!m.isEmpty()) 
				m = "1`{" + m + "}";
			marking = m;
		}
		else {
			for(Map<String,Boolean> config : configurationList) {
				String m = "";

				for(IFeature feat : fm.getFeatures()) {
					if (feat.getStructure() != fm.getStructure().getRoot()) {
						if (!m.isEmpty()) 
							m = m + ", ";

						if (config.containsKey(feat.getName()))
							m = m + featurePrefix + feat.getName() + " = " + TRUE;
						else
							m = m + featurePrefix + feat.getName() + " = " + FALSE;
					}
				}
				if (!m.isEmpty()) 
					m = "1`{" + m + "}";
				if (!marking.isEmpty()) 
					marking = marking + "++\n";
				marking = marking + m;
			}
		}
		return marking;
	}

	private void generateConfigurationValid(String indent) throws IOException {
		// Generate IsValid transition
		
		if (!this.isExportation) 
			generateTransition(indent,"isValid",0,"isValid(" + TRUE + "," + strFeatures + ") andalso false");
		else
			generateTransition(indent,"isValid",0,"isValid(" + TRUE + "," + strFeatures + ")");
	}

	private void generateConfigurationSwitch(String indent) throws IOException {
		for(IFeature feat : fm.getFeatures()) {
			if (feat.getStructure() != fm.getStructure().getRoot()) {
				if(!blockedFeatures.contains(feat.getName())) {
					if (!this.isExportation) 
						generateTransition(indent,"switch_" + feat.getName(),0,"false");
					else
						generateTransition(indent,"switch_" + feat.getName(),0,"true");
				}
			}
		}
	}

	private void generateSwitchingArcs(String indent) throws IOException {
		for(IFeature feat : fm.getFeatures()) {
			if (feat.getStructure() != fm.getStructure().getRoot()) {
				if(!blockedFeatures.contains(feat.getName())) {
					generateArc(indent,"PTARC_SWITCH_" + feat,placeConfiguration,"switch_" + feat,"PtoT", generateConfigurationSwitchArc(feat.getName(),false));
					generateArc(indent,"TPARC_SWITCH_" + feat,placeConfiguration,"switch_" + feat,"TtoP", generateConfigurationSwitchArc(feat.getName(),true));
				}
			}
		}
	}

	private void generateConfigurationValidArc(String indent, boolean bidirectional) throws IOException {
		if (bidirectional)
			generateArc(indent,"ARC_VALID",placeConfiguration,"isValid","BOTHDIR", "c");
		else
			generateArc(indent,"ARC_VALID",placeConfiguration,"isValid","PtoT", "c");
		generateArc(indent,"ARC_CURRENT",placeCurrent,"isValid","TtoP", "c");
		generateArc(indent,"ARC_CURRENT_D",placeCurrent,"isValid","PtoT", "d");
	}

	private String generateConfigurationSwitchArc(String feature, boolean switching) {
		String arcSwitch = "";

		for(IFeature feat : fm.getFeatures()) {
			if (feat.getStructure() != fm.getStructure().getRoot()) {
				if (!arcSwitch.isEmpty())
					arcSwitch = arcSwitch + ", ";

				if ((feature == feat.getName()) && switching)
					arcSwitch = arcSwitch + featurePrefix + feat.getName() + " = not " + variablePrefix + feat;
				else
					arcSwitch = arcSwitch + featurePrefix + feat.getName() + " = " + variablePrefix + feat;
			}
		}
		return "{" + arcSwitch + "}";
	}

	private void generateConfigurationArcs(String indent) throws IOException {
		for (Transition t : pn.getTrans()) {
			String name = t.getName().toString().replaceAll(" ", "_");
			generateArc(indent,"ARC_" + name,placeCurrent,name,"BOTHDIR", "c");
		}
	}

	private void generatePlace(String indent, Place p) throws IOException {
		if (p instanceof TimedPlace)
			generatePlace(indent,p.getName().toString().replaceAll(" ", "_"),"TOKEN",p.getMarking(),"1");
		else
			generatePlace(indent,p.getName().toString().replaceAll(" ", "_"),"INT",p.getMarking(),"1");
	}

	private void generatePlace(String indent, String name, String type, int marking, String initialMarking) throws IOException {
		writeFile(indent + "<place id=\"" + placePrefix + name + "\">");	
		writeFile(indent + "\t<posattr x=\"0.000000\" y=\"0.000000\"/>");		
		writeFile(indent + "\t<fillattr colour=\"White\" pattern=\"\" filled=\"false\"/>");		
		writeFile(indent + "\t<lineattr colour=\"Black\" thick=\"1\" type=\"Solid\"/>");		
		writeFile(indent + "\t<textattr colour=\"Black\" bold=\"false\"/>");		
		writeFile(indent + "\t<text>" + name + "</text>");		
		writeFile(indent + "\t<ellipse w=\"60.000000\" h=\"60.000000\"/>");		
		writeFile(indent + "\t<token x=\"-10.000000\" y=\"0.000000\"/>");

		writeFile(indent + "\t<marking x=\"0.000000\" y=\"0.000000\" hidden=\"false\">");		
		writeFile(indent + "\t\t<snap snap_id=\"0\" anchor.horizontal=\"0\" anchor.vertical=\"0\"/>");		
		writeFile(indent + "\t</marking>");


		writeFile(indent + "\t<type id=\"PL_T_" + name + "\">");		
		writeFile(indent + "\t\t<posattr x=\"-34.500000\" y=\"-31.000000\"/>");		
		writeFile(indent + "\t\t<fillattr colour=\"White\" pattern=\"Solid\" filled=\"false\"/>");		
		writeFile(indent + "\t\t<lineattr colour=\"Black\" thick=\"0\" type=\"Solid\"/>");		
		writeFile(indent + "\t\t<textattr colour=\"Black\" bold=\"false\"/>");		
		writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\">" + type + "</text>");		
		writeFile(indent + "\t</type>");

		writeFile(indent + "\t<initmark id=\"PL_M_" + name + "\">");		
		writeFile(indent + "\t\t<posattr x=\"-40.000000\" y=\"31.000000\"/>");		
		writeFile(indent + "\t\t<fillattr colour=\"White\" pattern=\"Solid\" filled=\"false\"/>");		
		writeFile(indent + "\t\t<lineattr colour=\"Black\" thick=\"0\" type=\"Solid\"/>");		
		writeFile(indent + "\t\t<textattr colour=\"Black\" bold=\"false\"/>");		

		if (marking > 0) {
			if (initialMarking =="1")
				writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\">" + String.valueOf(marking) + "`" + initialMarking + "</text>");
			else
				writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\">" + initialMarking + "</text>");
		}
		else {
			writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\"/>");
		}	
		writeFile(indent + "\t</initmark>");

		writeFile(indent + "</place>");

	}

	private void generateTransition(String indent, Transition t) throws IOException {
		String pc = pcList.get(t.getName());
		if (pc == null)
			pc = "";
		else 
			//pc = "n" + "&gt;" + "0" + AND + "(" + formatPresenceCondition(pc) + ")";
			pc = formatPresenceCondition(pc);
		generateTransition(indent, t.getName().toString().replaceAll(" ", "_"),t.getDelay(), pc);		
	}


	private String formatPresenceCondition(String pc) {		
		return formatPresenceCondition(pc, true);
	}

	private String formatPresenceCondition(String pc, boolean paramFunction) {		
		if (!pc.isEmpty()) {
			// Logic operators
			pc = pc.replaceAll(" and ", AND);
			pc = pc.replaceAll(" or ", OR);
			pc = pc.replace("(not ", "not (");

			for(IFeature feat : fm.getFeatures()) {
				if (feat.getStructure() != fm.getStructure().getRoot()) {
					if (paramFunction)
						pc = pc.replaceAll(feat.getName(), "#" + featurePrefix +feat.getName()+" c");
					else
						pc = pc.replaceAll(feat.getName(), paramPrefix +feat.getName());
				}
			}
		}
		return pc;
	}

	private void generateTransition(String indent, String name, Integer delay, String guard) throws IOException {
		writeFile(indent + "<trans id=\"TR_" + name + "\" explicit=\"false\">");

		writeFile(indent + "\t<posattr x=\"195.000000\" y=\"0.000000\"/>");		
		writeFile(indent + "\t<fillattr colour=\"White\" pattern=\"\" filled=\"false\"/>");		
		writeFile(indent + "\t<lineattr colour=\"Black\" thick=\"1\" type=\"Solid\"/>");		
		writeFile(indent + "\t<textattr colour=\"Black\" bold=\"false\"/>");		
		writeFile(indent + "\t<text>" + name + "</text>");		
		writeFile(indent + "\t<box w=\"96.000000\" h=\"28.000000\"/>");		
		writeFile(indent + "\t<binding x=\"7.200000\" y=\"-3.000000\"/>");		

		writeFile(indent + "\t<cond id=\"TR_C_" + name + "\">");		
		writeFile(indent + "\t\t<posattr x=\"138.000000\" y=\"25.000000\"/>");		
		writeFile(indent + "\t\t<fillattr colour=\"White\" pattern=\"Solid\" filled=\"false\"/>");				
		writeFile(indent + "\t\t<lineattr colour=\"Black\" thick=\"0\" type=\"Solid\"/>");		
		writeFile(indent + "\t\t<textattr colour=\"Black\" bold=\"false\"/>");		
		if (guard.isEmpty())
			writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\"/>");
		else
			writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\">[" + guard + "]</text>");

		writeFile(indent + "\t</cond>");

		writeFile(indent + "\t<time id=\"TR_T_" + name + "\">");		
		writeFile(indent + "\t\t<posattr x=\"257.500000\" y=\"25.000000\"/>");		
		writeFile(indent + "\t\t<fillattr colour=\"White\" pattern=\"Solid\" filled=\"false\"/>");		
		writeFile(indent + "\t\t<lineattr colour=\"Black\" thick=\"0\" type=\"Solid\"/>");		
		writeFile(indent + "\t\t<textattr colour=\"Black\" bold=\"false\"/>");		
		if (delay > 0)
			writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\">@+" + String.valueOf(delay) + "</text>");
		else
			writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\"/>");
		writeFile(indent + "\t</time>");		

		writeFile(indent + "\t<code id=\"TR_S_" + name + "\">");		
		writeFile(indent + "\t\t<posattr x=\"277.500000\" y=\"-46.000000\"/>");		
		writeFile(indent + "\t\t<fillattr colour=\"White\" pattern=\"Solid\" filled=\"false\"/>");		
		writeFile(indent + "\t\t<lineattr colour=\"Black\" thick=\"0\" type=\"Solid\"/>");		
		writeFile(indent + "\t\t<textattr colour=\"Black\" bold=\"false\"/>");		
		writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\"/>");		
		writeFile(indent + "\t</code>");

		writeFile(indent + "\t<priority id=\"TR_P_" + name + "\">");		
		writeFile(indent + "\t\t<posattr x=\"109.000000\" y=\"-25.000000\"/>");		
		writeFile(indent + "\t\t<fillattr colour=\"White\" pattern=\"Solid\" illed=\"false\"/>");		
		writeFile(indent + "\t\t<lineattr colour=\"Black\" thick=\"0\" type=\"Solid\"/>");		
		writeFile(indent + "\t\t<textattr colour=\"Black\" bold=\"false\"/>");		
		writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\"/>");		
		writeFile(indent + "\t</priority>");		

		writeFile(indent + "</trans>");		
	}

	private void generateArc(String indent, Arc arc) throws IOException {
		String name = arc.getName().toString().replaceAll(" ", "_");
		String place = "";
		String trans = "";
		String orientation = "";
		String pcPlace = null;

		if (arc instanceof TPArc) {
			orientation="TtoP";
			trans = ((TPArc) arc).getInput().getName().replaceAll(" ", "_");
			place = ((TPArc) arc).getOutput().getName().replaceAll(" ", "_");
			pcPlace = pcList.get(((TPArc) arc).getOutput().getName());
		} else {
			orientation="PtoT";
			trans = ((PTArc) arc).getOutput().getName().replaceAll(" ", "_");
			place = ((PTArc) arc).getInput().getName().replaceAll(" ", "_");			
			pcPlace = pcList.get(((PTArc) arc).getInput().getName());
		}

		String pc = pcList.get(arc.getName());
		String weight = String.valueOf(arc.getWeight()); 
		
		if (pc == null) {
			if (pcPlace == null)
				pc = weight + "`1";
			else
				pc = "if (" + formatPresenceCondition(pcPlace) + ") then " + weight + "`1 else 0`1";
		}
		else 
			pc = "if (" + formatPresenceCondition(pc) + ") then " + weight + "`1 else 0`1";

		generateArc(indent, name, place, trans, orientation,pc);
	}

	private void generateArc(String indent, String name, String place, String trans, String orientation, String guard) throws IOException {
		writeFile(indent + "<arc id=\"ARC_" + name + "\" orientation=\"" + orientation + "\" order=\"1\">");		

		writeFile(indent + "\t<posattr x=\"0.000000\" y=\"0.000000\"/>");		
		writeFile(indent + "\t<fillattr colour=\"White\" pattern=\"\" filled=\"false\"/>");		
		writeFile(indent + "\t<lineattr colour=\"Black\" thick=\"1\" type=\"Solid\"/>");		
		writeFile(indent + "\t<textattr colour=\"Black\" bold=\"false\"/>");		
		writeFile(indent + "\t<arrowattr headsize=\"1.200000\" currentcyckle=\"2\"/>");		
		writeFile(indent + "\t<transend idref=\"TR_" + trans + "\"/>");		
		writeFile(indent + "\t<placeend idref=\"PL_" + place + "\"/>");		

		writeFile(indent + "\t<annot id=\"ANN_" + name + "\">");		
		writeFile(indent + "\t\t<posattr x=\"89.000000\" y=\"11.000000\"/>");		
		writeFile(indent + "\t\t<fillattr colour=\"White\" pattern=\"Solid\" filled=\"false\"/>");		
		writeFile(indent + "\t\t<lineattr colour=\"Black\" thick=\"0\" type=\"Solid\"/>");		
		writeFile(indent + "\t\t<textattr colour=\"Black\" bold=\"false\"/>");		
		writeFile(indent + "\t\t<text tool=\"CPN Tools\" version=\"4.0.1\">" + guard + "</text>"); //arc weight		
		writeFile(indent + "\t</annot>");		

		writeFile(indent + "</arc>");
	}
}
