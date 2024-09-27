/*
 * generated by Xtext 2.35.0
 */
package org.pnpl.model.variability.editor.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.ComposedChecks;
import org.eclipse.xtext.validation.ImportUriValidator;

@ComposedChecks(validators = {ImportUriValidator.class})
public abstract class AbstractPNPL_variabilityValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.petrinets.org/variability"));
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2002/Ecore"));
		return result;
	}
}