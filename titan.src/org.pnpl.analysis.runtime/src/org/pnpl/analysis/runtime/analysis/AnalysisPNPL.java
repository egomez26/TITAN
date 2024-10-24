package org.pnpl.analysis.runtime.analysis;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import org.pnpl.analysis.analyzer.AbstractAnalysisPNPL;
import org.pnpl.analysis.runtime.helpers.TesslaHelper;
import org.pnpl.export.cpntools.exporters.ExportPNPL;
import org.pnpl.export.exporters.AbstractExport;
import org.pnpl.solvers.cpntools.handlers.ColouredPetriNetsHandler;
import org.pnpl.solvers.cpntools.handlers.Event;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;

import PetriNets.PetriNet;
import PetriNets.Place;
import PetriNets.Transition;

import de.uni_luebeck.isp.tessla.interpreter.JavaApi.Engine;

public class AnalysisPNPL extends AbstractAnalysisPNPL implements Closeable {
	private String cpnFileName;
	private String htmlTesslaPath = "\\report.html";
	private String prefix;
	private Shell shell = null;
	private TesslaHelper ts = null;
	
	public AnalysisPNPL() {
		Display display = Display.getCurrent();
		if (display == null)
	         display = Display.getDefault();
		shell = new Shell(display);
	}	
	
	@Override
	protected boolean buildCondition() {
		System.out.println("[pnpl] Starting Runtime Verification");
		if(!transformPNPLtoCPN()) return false;
		
		return runVerification();
	}
	
	private boolean transformPNPLtoCPN() {
		boolean result;
		
		AbstractExport exporter = (AbstractExport) new ExportPNPL();	
		exporter.isExportation = false;
		result = exporter.run(vrbFile);
		
		prefix = org.pnpl.export.cpntools.exporters.ExportPNPL.placePrefix;
		cpnFileName = exporter.getExportedFile();		
		return result;
	}
	
	private boolean runVerification() {
		File file = new File(cpnFileName);
		if (!(file.exists()))
			return false;
		
		try {
			ColouredPetriNetsHandler cpn = new ColouredPetriNetsHandler();
			cpn.prefixPlace = prefix;
			// 2.- Call CPNTools to simulate the net
			//int numSteps = 259200; // 3 days
			int numSteps = getSteps();
			Hashtable<String, Vector<Event>> res_sim = cpn.simulate(file, numSteps);
			System.out.println("[pnpl] Simulation result: " + res_sim.toString());

			// 3.- Call Tessla for the event traces
			String spec_str = getSpecification();
			System.out.println("[pnpl] Specification for Tessla Engine:\n" + spec_str);
			
			this.ts = new TesslaHelper();
			Engine eng = ts.generateTesslaEngine(spec_str);
			ts.executeTessla(res_sim, eng);

			// 4.- Visualization
			System.out.println("[pnpl] Report for " + vrbFile.getName());
			ts.exportHTML(file.getParent(), htmlTesslaPath);
			System.out.println("[pnpl] Ending runtime verification");
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private int getSteps() {
		int steps = 1;
		
		InputDialog dialog = new InputDialog(shell,"Selecting TeSSla configuration","Define the number of steps for the simulation","",null);
		if (dialog.open() == Window.OK) {
			steps = Integer.valueOf(dialog.getValue());
		}
		
		return steps;		
	}
	
	private String getSpecification() {
		String specification = "";
		
		MessageBox msgDlg =
				new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		msgDlg.setText("Selecting TeSSla configuration");
		msgDlg.setMessage("Would you like to use an existing Tessla specification file?");

		if (msgDlg.open() == SWT.YES) {			
			specification = loadTesslaConfiguration();
		} else {
			String in = selectElements() + "\r\n";
			String def = getDefinitions("functions") + "\r\n";
			String out = getDefinitions("outputs");
		
			specification = in + def + out;
		}

		return specification;
	}
	
	private String loadTesslaConfiguration() {
		String content="";
		try {
			FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		    dialog.setFilterExtensions(new String[] { "*.tessla", "*.*" });
		    String name = dialog.open();
		    
		    if ((name == null) || (name.length() == 0))
		    	return "";
		    
			content = com.google.common.io.Files.asCharSource(new File(name), Charsets.UTF_8).read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	private String getDefinitions(String str) {
		String definition = "";
		
		InputDialog dialog = new InputDialog(shell,"Definition of " + str,"Define the " + str + " to verify:","",null);
		if (dialog.open() == Window.OK) {
			definition = dialog.getValue();
		}
		return definition;
	}

	private String selectElements() {
		String strElements = "";
		
		// List of elements <-- from places and transitions
		PetriNet petriNet = vh.getPetriNet();

		List<String> elements = Lists.newArrayList();
		for(Place place : petriNet.getPlaces()) {
			elements.add(place.getName());
		}

		for(Transition transition : petriNet.getTrans()) {
			elements.add(transition.getName());
		}
		
		// Select elements to verify with Tessla
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(null, new LabelProvider());
		dialog.setMultipleSelection(false);
		dialog.setElements(elements.toArray());
		dialog.setTitle("Selecting elements to analyse"); 
		dialog.setMessage("List of elements of the PNPL");

		if (dialog.open() == ElementListSelectionDialog.OK) {
			Object[] result = dialog.getResult();  
			for (int i = 0; i < result.length; i++) {
				strElements = strElements + "in " + (String) result[i] + ": Events[Unit]\r\n";
			}
		}
		
		return strElements;
	}

	@Override
	public void close() throws IOException {
		shell.dispose();		
	}
}
