package org.pnpl.export.exporters;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import PetriNets.PetriNet;
import org.pnpl.analysis.helpers.VariabilityHelper;
import org.pnpl.export.activator.Activator;

public abstract class AbstractExportPNPL extends AbstractExport {
	protected VariabilityHelper vrbHelper = null;
	protected PetriNet pn = null;
	protected IFeatureModel fm = null;
	protected Map<String, String> pcList = null;
	
	protected List<Map<String,Boolean>> configurationList = null;
	protected List<String> blockedFeatures = null;
	
	@Override
	public boolean run(IFile vrb) {
		if (vrb == null) return false;
		System.out.println("[pnpl] Starting export of a PNPL");		
		
		// Loading the variability file
		vrbHelper = new VariabilityHelper();
		vrbHelper.load(vrb);

		pn = vrbHelper.getPetriNet();
		fm = vrbHelper.getFeatureModel();
		pcList = vrbHelper.extractPresenceConditions(true);
		
		// Call the export process
		IRunnableWithProgress operation = new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				IContainer targetFolder = null;
				try {
					IContainer target = vrb.getProject().getFolder("export");
					targetFolder = target;
					
					if (!targetFolder.getLocation().toFile().exists()) {
						targetFolder.getLocation().toFile().mkdirs();
					}

					exportedFolder = targetFolder.getLocation().toFile().toString();
					export(targetFolder.getLocation().toFile(), BasicMonitor.toMonitor(monitor));					
				} catch (IOException e) {
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
	
	protected abstract boolean export(File dstFolder, Monitor monitor) throws IOException;
	
	protected void selectConfiguration(Shell shell) {
		// Select the options for the export, including configuration file
		configurationList = Lists.newArrayList();
		blockedFeatures = Lists.newArrayList();
		
		if (isExportation) {
			readConfigurationFiles(selectConfigurationFiles(shell));
			selectBlockFeatures(shell);
		}
	}
	
	private List<String> selectConfigurationFiles(Shell shell) {
		List<String> files = Lists.newArrayList();

		MessageBox msgDlg =
				new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		msgDlg.setText("Selecting features");
		msgDlg.setMessage("Would you like to use configuration files for features?");

		if (msgDlg.open() == SWT.YES) {

			FileDialog dlg = new FileDialog(shell, SWT.MULTI);
			dlg.setFilterExtensions(new String[] { "*.config" });

			if (dlg.open() != null) {
				String[] names = dlg.getFileNames();
				for (int i = 0, n = names.length; i < n; i++) {
					StringBuffer buf = new StringBuffer(dlg.getFilterPath());
					if (buf.charAt(buf.length() - 1) != File.separatorChar)
						buf.append(File.separatorChar);
					buf.append(names[i]);
					files.add(buf.toString());
				}
			}
		}
		
		return files;
	}
	
	private void selectBlockFeatures(Shell shell) {
		MessageBox msgDlg =
				new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		msgDlg.setText("Selecting features");
		msgDlg.setMessage("Would you like to block some features?");

		if (msgDlg.open() == SWT.YES) {
			List<String> featuresInTree = Lists.newArrayList();
			for(IFeature feat : fm.getFeatures()) {
				if (feat.getStructure() != fm.getStructure().getRoot()) {
					featuresInTree.add(feat.getName());
				}
			}

			ElementListSelectionDialog  dialog = new ElementListSelectionDialog (
					shell,
					new LabelProvider());
			dialog.setElements(featuresInTree.toArray());
			dialog.setTitle("Selecting features to block"); 
			dialog.setMessage("List of features of the Feature Model");
			
			if (dialog.open() == Window.OK) {
				Object[] result = dialog.getResult();  
				for (int i = 0; i < result.length; i++) {
					blockedFeatures.add((String) result[i]);
				}
			}			
		}
	}

	private void readConfigurationFiles(List<String> configFiles) {
		for (String fileName : configFiles) {
			Map<String, Boolean> thisConfig = Maps.newHashMap();

			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(fileName));
				String line = reader.readLine();
				if (line != null)
					thisConfig.put(line, true);			
				while (line != null) {
					line = reader.readLine();
					if (line != null)
						thisConfig.put(line, true);				
				}
				reader.close();
				configurationList.add(thisConfig);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
