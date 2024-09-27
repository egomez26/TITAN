/*
 * generated by Xtext 2.35.0
 */
package org.pnpl.model.variability.editor.ui;

import com.google.inject.Injector;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.pnpl.model.variability.editor.ui.internal.EditorActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class PNPL_variabilityExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FrameworkUtil.getBundle(EditorActivator.class);
	}
	
	@Override
	protected Injector getInjector() {
		EditorActivator activator = EditorActivator.getInstance();
		return activator != null ? activator.getInjector(EditorActivator.ORG_PNPL_MODEL_VARIABILITY_EDITOR_PNPL_VARIABILITY) : null;
	}

}
