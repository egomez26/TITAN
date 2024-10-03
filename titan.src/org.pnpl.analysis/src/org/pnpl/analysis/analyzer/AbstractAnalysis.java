package org.pnpl.analysis.analyzer;

import org.eclipse.core.resources.IFile;
import org.pnpl.analysis.helpers.VariabilityHelper;

/**
 * Abstract class for analysis
 * @author Elena Gomez-Martinez
 *
 */

public abstract class AbstractAnalysis {
	protected VariabilityHelper vh = null;
	protected IFile vrbFile = null;
	
	public abstract boolean run();
	
	public void loadVariabilityFile(IFile vrbfile) {
		this.vrbFile = vrbfile; 
		vh = new VariabilityHelper(vrbfile);
	}
}
