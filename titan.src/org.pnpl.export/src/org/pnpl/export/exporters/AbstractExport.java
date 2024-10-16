package org.pnpl.export.exporters;

import org.eclipse.core.resources.IFile;

/**
 * Abstract class for exporting 
 * @author Elena Gomez-Martinez
 *
 */

public abstract class AbstractExport {
	
	public boolean isExportation = true;
	
	protected String exportedFileName;
	protected String exportedFolder;
	
	public String getExportedFolder() {
		return exportedFolder;
	}
	
	public String getExportedFileName() {
		return exportedFileName;
	}
		
	public String getExportedFile() {
		return exportedFolder + "\\" + exportedFileName;
	}
	
	public abstract boolean run(IFile vrb);
}
