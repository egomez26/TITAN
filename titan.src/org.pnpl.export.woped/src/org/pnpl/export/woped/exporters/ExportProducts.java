package org.pnpl.export.woped.exporters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.Monitor;
import org.pnpl.export.exporters.AbstractExportProducts;
import org.pnpl.export.woped.main.Generate;

/**
 * Export a PN to CPNTools
 * @author Elena Gomez-Martinez
 *
 */

public class ExportProducts extends AbstractExportProducts{
	private static String outputFile = "output_CPNTools.cpn";

	@Override
	protected boolean export(Monitor monitor) throws IOException {
		exportedFileName = outputFile;

		try {
			monitor.subTask("Loading...");
			
			Generate gen0 = new Generate(modelURI, targetFolder.getLocation().toFile(), getArguments());
			monitor.worked(1);
			String generationID = org.eclipse.acceleo.engine.utils.AcceleoLaunchingUtil.computeUIProjectID("org.pnpl.export", 
					"org.pnpl.export.woped.main.Generate", 
					modelURI.toString(), 
					targetFolder.getFullPath().toString(), 
					new ArrayList<String>());
			gen0.setGenerationID(generationID);
			gen0.doGenerate(monitor);
			
			monitor.subTask("Finished...");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}

	private List<? extends Object> getArguments() {
		return new ArrayList<String>();
	}
}
