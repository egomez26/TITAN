package org.pnpl.export.handlers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import org.pnpl.export.exporters.AbstractExport;
import org.pnpl.export.helpers.MenuHelper;

/**
 * Handler for the abstract class for export
 * @author Elena Gomez-Martinez
 */

public abstract class ExportHandler extends AbstractHandler implements IHandler {
	private MenuHelper menu = new MenuHelper();	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IFile              vrbfile  = menu.locateFile(event);
			if (vrbfile != null) {		
				
				// obtain extensions of exporters
				Map<String, IConfigurationElement> offeredExporter = new HashMap<String, IConfigurationElement>();
				IConfigurationElement[] extensions = registry.getConfigurationElementsFor(getExtensionPointIdentifier());
				for (IConfigurationElement extension : extensions) {
					if (extension.getName().equals("client")) {
						offeredExporter.put(extension.getAttribute("name"), extension);
					}
				}
				
				ElementListSelectionDialog dialog = new ElementListSelectionDialog(null, new LabelProvider());
				dialog.setMultipleSelection(false);
				dialog.setElements(offeredExporter.keySet().toArray());
				dialog.setTitle("Exportation");
				dialog.setMessage("Export to...");
				
				// user pressed cancel
				if (dialog.open() != ElementListSelectionDialog.OK) return false;
				
				// user pressed ok
				Object[] selection = dialog.getResult();
				if (selection.length>0) {
					IConfigurationElement extension = offeredExporter.get(selection[0]);
					AbstractExport        exporter  = (AbstractExport)extension.createExecutableExtension("class");
					String                name      = (String)extension.getAttribute("name");
				
					exporter.run(vrbfile);
					System.out.println("[pnpl] Export to " + name + " done");
				}
				
			}
		} 
		catch (Exception e) { e.printStackTrace();}
		return true;
	}

	// identifier of the extension point 
	protected abstract String getExtensionPointIdentifier(); 
}
