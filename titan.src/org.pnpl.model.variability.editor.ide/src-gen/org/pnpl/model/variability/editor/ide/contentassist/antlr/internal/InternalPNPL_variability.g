/*
 * generated by Xtext 2.35.0
 */
grammar InternalPNPL_variability;

options {
	superClass=AbstractInternalContentAssistParser;
}

@lexer::header {
package org.pnpl.model.variability.editor.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.pnpl.model.variability.editor.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.pnpl.model.variability.editor.services.PNPL_variabilityGrammarAccess;

}
@parser::members {
	private PNPL_variabilityGrammarAccess grammarAccess;

	public void setGrammarAccess(PNPL_variabilityGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleVariability
entryRuleVariability
:
{ before(grammarAccess.getVariabilityRule()); }
	 ruleVariability
{ after(grammarAccess.getVariabilityRule()); } 
	 EOF 
;

// Rule Variability
ruleVariability 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getVariabilityAccess().getGroup()); }
		(rule__Variability__Group__0)
		{ after(grammarAccess.getVariabilityAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFileURI
entryRuleFileURI
:
{ before(grammarAccess.getFileURIRule()); }
	 ruleFileURI
{ after(grammarAccess.getFileURIRule()); } 
	 EOF 
;

// Rule FileURI
ruleFileURI 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFileURIAccess().getGroup()); }
		(rule__FileURI__Group__0)
		{ after(grammarAccess.getFileURIAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpression
entryRuleExpression
:
{ before(grammarAccess.getExpressionRule()); }
	 ruleExpression
{ after(grammarAccess.getExpressionRule()); } 
	 EOF 
;

// Rule Expression
ruleExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionAccess().getAlternatives()); }
		(rule__Expression__Alternatives)
		{ after(grammarAccess.getExpressionAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePresenceCondition
entryRulePresenceCondition
:
{ before(grammarAccess.getPresenceConditionRule()); }
	 rulePresenceCondition
{ after(grammarAccess.getPresenceConditionRule()); } 
	 EOF 
;

// Rule PresenceCondition
rulePresenceCondition 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPresenceConditionAccess().getGroup()); }
		(rule__PresenceCondition__Group__0)
		{ after(grammarAccess.getPresenceConditionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFeature
entryRuleFeature
:
{ before(grammarAccess.getFeatureRule()); }
	 ruleFeature
{ after(grammarAccess.getFeatureRule()); } 
	 EOF 
;

// Rule Feature
ruleFeature 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFeatureAccess().getFeatureAssignment()); }
		(rule__Feature__FeatureAssignment)
		{ after(grammarAccess.getFeatureAccess().getFeatureAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleUnaryExpression
entryRuleUnaryExpression
:
{ before(grammarAccess.getUnaryExpressionRule()); }
	 ruleUnaryExpression
{ after(grammarAccess.getUnaryExpressionRule()); } 
	 EOF 
;

// Rule UnaryExpression
ruleUnaryExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getUnaryExpressionAccess().getGroup()); }
		(rule__UnaryExpression__Group__0)
		{ after(grammarAccess.getUnaryExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBinaryExpression
entryRuleBinaryExpression
:
{ before(grammarAccess.getBinaryExpressionRule()); }
	 ruleBinaryExpression
{ after(grammarAccess.getBinaryExpressionRule()); } 
	 EOF 
;

// Rule BinaryExpression
ruleBinaryExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBinaryExpressionAccess().getGroup()); }
		(rule__BinaryExpression__Group__0)
		{ after(grammarAccess.getBinaryExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEString
entryRuleEString
:
{ before(grammarAccess.getEStringRule()); }
	 ruleEString
{ after(grammarAccess.getEStringRule()); } 
	 EOF 
;

// Rule EString
ruleEString 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEStringAccess().getAlternatives()); }
		(rule__EString__Alternatives)
		{ after(grammarAccess.getEStringAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule UnaryOperator
ruleUnaryOperator
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration()); }
		('not')
		{ after(grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule BinaryOperator
ruleBinaryOperator
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorAccess().getAlternatives()); }
		(rule__BinaryOperator__Alternatives)
		{ after(grammarAccess.getBinaryOperatorAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionAccess().getFeatureParserRuleCall_0()); }
		ruleFeature
		{ after(grammarAccess.getExpressionAccess().getFeatureParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionAccess().getUnaryExpressionParserRuleCall_1()); }
		ruleUnaryExpression
		{ after(grammarAccess.getExpressionAccess().getUnaryExpressionParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionAccess().getBinaryExpressionParserRuleCall_2()); }
		ruleBinaryExpression
		{ after(grammarAccess.getExpressionAccess().getBinaryExpressionParserRuleCall_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EString__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); }
		RULE_STRING
		{ after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); }
		RULE_ID
		{ after(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryOperator__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorAccess().getANDEnumLiteralDeclaration_0()); }
		('and')
		{ after(grammarAccess.getBinaryOperatorAccess().getANDEnumLiteralDeclaration_0()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorAccess().getOREnumLiteralDeclaration_1()); }
		('or')
		{ after(grammarAccess.getBinaryOperatorAccess().getOREnumLiteralDeclaration_1()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorAccess().getIMPLIESEnumLiteralDeclaration_2()); }
		('implies')
		{ after(grammarAccess.getBinaryOperatorAccess().getIMPLIESEnumLiteralDeclaration_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Variability__Group__0__Impl
	rule__Variability__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVariabilityAccess().getPnKeyword_0()); }
	'pn'
	{ after(grammarAccess.getVariabilityAccess().getPnKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Variability__Group__1__Impl
	rule__Variability__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVariabilityAccess().getPetrinetAssignment_1()); }
	(rule__Variability__PetrinetAssignment_1)
	{ after(grammarAccess.getVariabilityAccess().getPetrinetAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Variability__Group__2__Impl
	rule__Variability__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVariabilityAccess().getFmKeyword_2()); }
	'fm'
	{ after(grammarAccess.getVariabilityAccess().getFmKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Variability__Group__3__Impl
	rule__Variability__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVariabilityAccess().getFeaturemodelAssignment_3()); }
	(rule__Variability__FeaturemodelAssignment_3)
	{ after(grammarAccess.getVariabilityAccess().getFeaturemodelAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Variability__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVariabilityAccess().getGroup_4()); }
	(rule__Variability__Group_4__0)*
	{ after(grammarAccess.getVariabilityAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Variability__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Variability__Group_4__0__Impl
	rule__Variability__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVariabilityAccess().getPresenceconditionAssignment_4_0()); }
	(rule__Variability__PresenceconditionAssignment_4_0)
	{ after(grammarAccess.getVariabilityAccess().getPresenceconditionAssignment_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Variability__Group_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVariabilityAccess().getSemicolonKeyword_4_1()); }
	';'
	{ after(grammarAccess.getVariabilityAccess().getSemicolonKeyword_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FileURI__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FileURI__Group__0__Impl
	rule__FileURI__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FileURI__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFileURIAccess().getFileURIAction_0()); }
	()
	{ after(grammarAccess.getFileURIAccess().getFileURIAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FileURI__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FileURI__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FileURI__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFileURIAccess().getImportURIAssignment_1()); }
	(rule__FileURI__ImportURIAssignment_1)
	{ after(grammarAccess.getFileURIAccess().getImportURIAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PresenceCondition__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group__0__Impl
	rule__PresenceCondition__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getPCKeyword_0()); }
	'PC'
	{ after(grammarAccess.getPresenceConditionAccess().getPCKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group__1__Impl
	rule__PresenceCondition__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getForKeyword_1()); }
	'for'
	{ after(grammarAccess.getPresenceConditionAccess().getForKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group__2__Impl
	rule__PresenceCondition__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getElementsAssignment_2()); }
	(rule__PresenceCondition__ElementsAssignment_2)
	{ after(grammarAccess.getPresenceConditionAccess().getElementsAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group__3__Impl
	rule__PresenceCondition__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getGroup_3()); }
	(rule__PresenceCondition__Group_3__0)*
	{ after(grammarAccess.getPresenceConditionAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group__4__Impl
	rule__PresenceCondition__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getEqualsSignKeyword_4()); }
	'='
	{ after(grammarAccess.getPresenceConditionAccess().getEqualsSignKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getExpressionAssignment_5()); }
	(rule__PresenceCondition__ExpressionAssignment_5)
	{ after(grammarAccess.getPresenceConditionAccess().getExpressionAssignment_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PresenceCondition__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group_3__0__Impl
	rule__PresenceCondition__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getCommaKeyword_3_0()); }
	','
	{ after(grammarAccess.getPresenceConditionAccess().getCommaKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PresenceCondition__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPresenceConditionAccess().getElementsAssignment_3_1()); }
	(rule__PresenceCondition__ElementsAssignment_3_1)
	{ after(grammarAccess.getPresenceConditionAccess().getElementsAssignment_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__UnaryExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__UnaryExpression__Group__0__Impl
	rule__UnaryExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getUnaryExpressionAccess().getOperatorAssignment_0()); }
	(rule__UnaryExpression__OperatorAssignment_0)
	{ after(grammarAccess.getUnaryExpressionAccess().getOperatorAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__UnaryExpression__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getUnaryExpressionAccess().getRightAssignment_1()); }
	(rule__UnaryExpression__RightAssignment_1)
	{ after(grammarAccess.getUnaryExpressionAccess().getRightAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BinaryExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BinaryExpression__Group__0__Impl
	rule__BinaryExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBinaryExpressionAccess().getLeftParenthesisKeyword_0()); }
	'('
	{ after(grammarAccess.getBinaryExpressionAccess().getLeftParenthesisKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BinaryExpression__Group__1__Impl
	rule__BinaryExpression__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBinaryExpressionAccess().getLeftAssignment_1()); }
	(rule__BinaryExpression__LeftAssignment_1)
	{ after(grammarAccess.getBinaryExpressionAccess().getLeftAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BinaryExpression__Group__2__Impl
	rule__BinaryExpression__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBinaryExpressionAccess().getOperatorAssignment_2()); }
	(rule__BinaryExpression__OperatorAssignment_2)
	{ after(grammarAccess.getBinaryExpressionAccess().getOperatorAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BinaryExpression__Group__3__Impl
	rule__BinaryExpression__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBinaryExpressionAccess().getRightAssignment_3()); }
	(rule__BinaryExpression__RightAssignment_3)
	{ after(grammarAccess.getBinaryExpressionAccess().getRightAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BinaryExpression__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBinaryExpressionAccess().getRightParenthesisKeyword_4()); }
	')'
	{ after(grammarAccess.getBinaryExpressionAccess().getRightParenthesisKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Variability__PetrinetAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVariabilityAccess().getPetrinetFileURIParserRuleCall_1_0()); }
		ruleFileURI
		{ after(grammarAccess.getVariabilityAccess().getPetrinetFileURIParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__FeaturemodelAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVariabilityAccess().getFeaturemodelFileURIParserRuleCall_3_0()); }
		ruleFileURI
		{ after(grammarAccess.getVariabilityAccess().getFeaturemodelFileURIParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variability__PresenceconditionAssignment_4_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVariabilityAccess().getPresenceconditionPresenceConditionParserRuleCall_4_0_0()); }
		rulePresenceCondition
		{ after(grammarAccess.getVariabilityAccess().getPresenceconditionPresenceConditionParserRuleCall_4_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FileURI__ImportURIAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFileURIAccess().getImportURISTRINGTerminalRuleCall_1_0()); }
		RULE_STRING
		{ after(grammarAccess.getFileURIAccess().getImportURISTRINGTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__ElementsAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_2_0()); }
		(
			{ before(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_2_0_1()); }
			ruleEString
			{ after(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_2_0_1()); }
		)
		{ after(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__ElementsAssignment_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_3_1_0()); }
		(
			{ before(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_3_1_0_1()); }
			ruleEString
			{ after(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_3_1_0_1()); }
		)
		{ after(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PresenceCondition__ExpressionAssignment_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPresenceConditionAccess().getExpressionExpressionParserRuleCall_5_0()); }
		ruleExpression
		{ after(grammarAccess.getPresenceConditionAccess().getExpressionExpressionParserRuleCall_5_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Feature__FeatureAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFeatureAccess().getFeatureEStringParserRuleCall_0()); }
		ruleEString
		{ after(grammarAccess.getFeatureAccess().getFeatureEStringParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryExpression__OperatorAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getUnaryExpressionAccess().getOperatorUnaryOperatorEnumRuleCall_0_0()); }
		ruleUnaryOperator
		{ after(grammarAccess.getUnaryExpressionAccess().getOperatorUnaryOperatorEnumRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryExpression__RightAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getUnaryExpressionAccess().getRightExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getUnaryExpressionAccess().getRightExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__LeftAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryExpressionAccess().getLeftExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getBinaryExpressionAccess().getLeftExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__OperatorAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryExpressionAccess().getOperatorBinaryOperatorEnumRuleCall_2_0()); }
		ruleBinaryOperator
		{ after(grammarAccess.getBinaryExpressionAccess().getOperatorBinaryOperatorEnumRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryExpression__RightAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryExpressionAccess().getRightExpressionParserRuleCall_3_0()); }
		ruleExpression
		{ after(grammarAccess.getBinaryExpressionAccess().getRightExpressionParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
