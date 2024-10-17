package org.pnpl.analysis.handlers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import org.pnpl.analysis.analyzer.AbstractAnalysis;
import org.pnpl.analysis.helpers.MenuHelper;

/**
 * Handler for the abstract class of analysis
 * @author Elena Gomez-Martinez
 * @author Esther Guerra
 */

public abstract class AnalysisHandler extends AbstractHandler implements IHandler {
	private static String STRING_ALL = "All products are ";
	private static String STRING_SOME = "Some products are not ";
	
	private MenuHelper menu = new MenuHelper();	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IFile              vrbfile  = menu.locateFile(event);
			if (vrbfile != null) {		
				
				// obtain extensions of analysis
				Map<String, IConfigurationElement> offeredAnalysis = new HashMap<String, IConfigurationElement>();
				IConfigurationElement[] extensions = registry.getConfigurationElementsFor(getExtensionPointIdentifier());
				for (IConfigurationElement extension : extensions) {
					if (extension.getName().equals("client")) {
						offeredAnalysis.put(extension.getAttribute("name"), extension);
					}
				}
				
				// selecting the property to analyse
				ElementListSelectionDialog dialog = new ElementListSelectionDialog(null, new LabelProvider());
				dialog.setMultipleSelection(false);
				dialog.setElements(offeredAnalysis.keySet().toArray());
				dialog.setTitle("PNPL Analysis");
				dialog.setMessage("Please, select a property to analyse");		
				
				// user pressed cancel
				if (dialog.open() != ElementListSelectionDialog.OK) return false;
				
				// user pressed ok
				Object[] selection = dialog.getResult();
				
				if (selection != null && selection.length > 0) {
					IConfigurationElement extension = offeredAnalysis.get(selection[0]);
					AbstractAnalysis      analysis  = (AbstractAnalysis)extension.createExecutableExtension("class");
					String                name      = (String)extension.getAttribute("name");
				
					long time_start, time_end;
					time_start = System.currentTimeMillis();
								
					analysis.loadVariabilityFile(vrbfile);
					
					String outprint = "";
					if (!analysis.run()) {
						outprint = STRING_SOME;
					} else {
						outprint = STRING_ALL;
					}
					outprint = outprint + name;
					time_end = System.currentTimeMillis();
					
					System.out.println("[pnpl] " + outprint);
					String timeprint =  "The task has taken " + ( time_end - time_start ) + " milliseconds";
					System.out.println("[pnpl] " + timeprint);
					
					MessageDialog.openInformation(null, "Analysis results", outprint + ".\n" + timeprint);					
				}
			}
				
		} 
		catch (Exception e) { e.printStackTrace();}
		return true;
	}

	// identifier of the extension point 
	protected abstract String getExtensionPointIdentifier(); 
}
