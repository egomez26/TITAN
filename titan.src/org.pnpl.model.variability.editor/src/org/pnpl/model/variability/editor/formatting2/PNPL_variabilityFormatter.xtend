/*
 * generated by Xtext 2.35.0
 */
package org.pnpl.model.variability.editor.formatting2

import com.google.inject.Inject
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.pnpl.model.variability.editor.services.PNPL_variabilityGrammarAccess
import variability.PresenceCondition
import variability.Variability
import variability.UnaryExpression

class PNPL_variabilityFormatter extends AbstractFormatter2 {
	
	@Inject extension PNPL_variabilityGrammarAccess

	def dispatch void format(Variability variability, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc.  
		for (presenceCondition : variability.presencecondition) {
			presenceCondition.format
			println(presenceCondition.format)	
		}
	}
	
	def dispatch void format(PresenceCondition presenceCondition, extension IFormattableDocument document) {
		presenceCondition.regionFor.keyword('PC').prepend[newLine];
		presenceCondition.regionFor.keyword('for').prepend[oneSpace];		
		for (element : presenceCondition.elements) {
			//No se unen las lineas:-/ 
			element.regionFor.keyword(',').prepend[oneSpace];
		}
		//Creo que esto si esta funcionando 
		presenceCondition.regionFor.keyword('=').prepend[oneSpace];
	}

	def dispatch void format(UnaryExpression unaryExpression, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		//unaryExpression.regionFor.keyword('NOT').append[autowrap];
		//println("asd")
	}
	
	// TODO: implement for BinaryExpression
}