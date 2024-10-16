package org.pnpl.export.exporters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;

import org.pnpl.export.activator.Activator;

/**
 * Abstract class for the export of a derivable product from a PNPL
 * @author Elena Gomez-Martinez
 *
 */

public abstract class AbstractExportProducts extends AbstractExport {

	protected IFile model = null;
	protected URI modelURI = null; 
	protected IContainer targetFolder = null;
	
	public boolean run(IFile model) {
		if (model == null) return false;
		System.out.println("[pnpl] Starting export of a PN");		
		
		// Loading the pn file
		this.model = model; 
		this.modelURI = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
		
		// Call the export process
		IRunnableWithProgress operation = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				try {
					IContainer target = model.getProject().getFolder("export");
					targetFolder = target;
					if (!targetFolder.getLocation().toFile().exists()) {
						targetFolder.getLocation().toFile().mkdirs();
					}
					
					exportedFolder = targetFolder.getLocation().toFile().toString();
					export(BasicMonitor.toMonitor(monitor));
				} catch (IOException e) {
					monitor.subTask(e.getMessage());
					IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
					Activator.getDefault().getLog().log(status);
				} finally {
					if (targetFolder != null) {
						try {
							targetFolder.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}				
			}
		};
		
		try {
			PlatformUI.getWorkbench().getProgressService().run(true, true, operation);
		} catch (InvocationTargetException e) {
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
			Activator.getDefault().getLog().log(status);
		} catch (InterruptedException e) {
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
			Activator.getDefault().getLog().log(status);
		}
		
		return true;
	}
	
	protected abstract boolean export(Monitor monitor) throws IOException;
}
