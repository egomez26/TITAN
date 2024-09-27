package org.pnpl.model.variability.editor.providers

import java.util.Collection
import java.util.Collections
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.IResourceDescription
import org.eclipse.xtext.resource.IResourceDescription.Delta
import org.eclipse.xtext.resource.IResourceDescription.Manager
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.impl.DefaultResourceDescription
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy
import org.eclipse.xtext.resource.impl.SimpleResourceDescriptionsBasedContainerManager
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator

class PetriNetsResourceServiceProvider implements IResourceServiceProvider {
	
	val containerManager = new SimpleResourceDescriptionsBasedContainerManager();
	
	override canHandle(URI uri) {
		uri.fileExtension().equals("petrinets")
	}
	
	override <T> get(Class<T> t) {
//		try { return t.newInstance(); }  catch (Throwable e) {}
		null;
	}
	
	override getContainerManager() {
		containerManager
	}
	
	override getEncodingProvider() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getResourceDescriptionManager() {
		new Manager() {

			override isAffected(Collection<Delta> deltas, IResourceDescription candidate, IResourceDescriptions context) throws IllegalArgumentException {
				false;
			}

			override isAffected(Delta delta, IResourceDescription candidate) throws IllegalArgumentException {
				false;
			}

			override createDelta(IResourceDescription oldDescription, IResourceDescription newDescription) {
				new DefaultResourceDescriptionDelta(oldDescription, newDescription);
			}

			override getResourceDescription(Resource resource) {
				var strat = new DefaultResourceDescriptionStrategy();
				strat.setQualifiedNameProvider(
					new IQualifiedNameProvider() {
						def name (EObject obj) {
							var fname = obj.eClass.EAllStructuralFeatures.findFirst[it.name.equals("name")]
							if (fname!==null) 
								return QualifiedName.create(obj.eGet(fname).toString())								
						}
						override apply(EObject obj) { 
							name(obj)
						}
						override getFullyQualifiedName(EObject obj) {
							name(obj)
						}
					});

				return new DefaultResourceDescription(resource, strat);
			}
		};
	}
	
	override getResourceValidator() {
		new IResourceValidator() {
			override validate(Resource resource, CheckMode mode, CancelIndicator indicator) {
				return Collections.emptyList();
			}
		}
	}	
	
}