/*
 * generated by Xtext 2.36.0
 */
package org.pnpl.model.variability.editor.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.pnpl.model.variability.editor.services.PNPL_variabilityGrammarAccess;
import variability.BinaryExpression;
import variability.Feature;
import variability.FileURI;
import variability.PresenceCondition;
import variability.UnaryExpression;
import variability.Variability;
import variability.VariabilityPackage;

@SuppressWarnings("all")
public class PNPL_variabilitySemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private PNPL_variabilityGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == EcorePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case EcorePackage.EOBJECT:
				sequence_EObject(context, (EObject) semanticObject); 
				return; 
			}
		else if (epackage == VariabilityPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case VariabilityPackage.BINARY_EXPRESSION:
				sequence_BinaryExpression(context, (BinaryExpression) semanticObject); 
				return; 
			case VariabilityPackage.FEATURE:
				sequence_Feature(context, (Feature) semanticObject); 
				return; 
			case VariabilityPackage.FILE_URI:
				sequence_FileURI(context, (FileURI) semanticObject); 
				return; 
			case VariabilityPackage.PRESENCE_CONDITION:
				sequence_PresenceCondition(context, (PresenceCondition) semanticObject); 
				return; 
			case VariabilityPackage.UNARY_EXPRESSION:
				sequence_UnaryExpression(context, (UnaryExpression) semanticObject); 
				return; 
			case VariabilityPackage.VARIABILITY:
				sequence_Variability(context, (Variability) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Expression returns BinaryExpression
	 *     BinaryExpression returns BinaryExpression
	 *
	 * Constraint:
	 *     (left=Expression operator=BinaryOperator right=Expression)
	 * </pre>
	 */
	protected void sequence_BinaryExpression(ISerializationContext context, BinaryExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, VariabilityPackage.Literals.BINARY_EXPRESSION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VariabilityPackage.Literals.BINARY_EXPRESSION__LEFT));
			if (transientValues.isValueTransient(semanticObject, VariabilityPackage.Literals.BINARY_EXPRESSION__OPERATOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VariabilityPackage.Literals.BINARY_EXPRESSION__OPERATOR));
			if (transientValues.isValueTransient(semanticObject, VariabilityPackage.Literals.BINARY_EXPRESSION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VariabilityPackage.Literals.BINARY_EXPRESSION__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getBinaryExpressionAccess().getLeftExpressionParserRuleCall_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getBinaryExpressionAccess().getOperatorBinaryOperatorEnumRuleCall_2_0(), semanticObject.getOperator());
		feeder.accept(grammarAccess.getBinaryExpressionAccess().getRightExpressionParserRuleCall_3_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EObject returns EObject
	 *
	 * Constraint:
	 *     {EObject}
	 * </pre>
	 */
	protected void sequence_EObject(ISerializationContext context, EObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Expression returns Feature
	 *     Feature returns Feature
	 *
	 * Constraint:
	 *     feature=EString
	 * </pre>
	 */
	protected void sequence_Feature(ISerializationContext context, Feature semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, VariabilityPackage.Literals.FEATURE__FEATURE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VariabilityPackage.Literals.FEATURE__FEATURE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFeatureAccess().getFeatureEStringParserRuleCall_0(), semanticObject.getFeature());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     FileURI returns FileURI
	 *
	 * Constraint:
	 *     importURI=STRING
	 * </pre>
	 */
	protected void sequence_FileURI(ISerializationContext context, FileURI semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, VariabilityPackage.Literals.FILE_URI__IMPORT_URI) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VariabilityPackage.Literals.FILE_URI__IMPORT_URI));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFileURIAccess().getImportURISTRINGTerminalRuleCall_1_0(), semanticObject.getImportURI());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     PresenceCondition returns PresenceCondition
	 *
	 * Constraint:
	 *     (elements+=[EObject|EString] elements+=[EObject|EString]* expression=Expression)
	 * </pre>
	 */
	protected void sequence_PresenceCondition(ISerializationContext context, PresenceCondition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Expression returns UnaryExpression
	 *     UnaryExpression returns UnaryExpression
	 *
	 * Constraint:
	 *     (operator=UnaryOperator right=Expression)
	 * </pre>
	 */
	protected void sequence_UnaryExpression(ISerializationContext context, UnaryExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, VariabilityPackage.Literals.UNARY_EXPRESSION__OPERATOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VariabilityPackage.Literals.UNARY_EXPRESSION__OPERATOR));
			if (transientValues.isValueTransient(semanticObject, VariabilityPackage.Literals.UNARY_EXPRESSION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VariabilityPackage.Literals.UNARY_EXPRESSION__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnaryExpressionAccess().getOperatorUnaryOperatorEnumRuleCall_0_0(), semanticObject.getOperator());
		feeder.accept(grammarAccess.getUnaryExpressionAccess().getRightExpressionParserRuleCall_1_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Variability returns Variability
	 *
	 * Constraint:
	 *     (petrinet=FileURI featuremodel=FileURI presencecondition+=PresenceCondition*)
	 * </pre>
	 */
	protected void sequence_Variability(ISerializationContext context, Variability semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
