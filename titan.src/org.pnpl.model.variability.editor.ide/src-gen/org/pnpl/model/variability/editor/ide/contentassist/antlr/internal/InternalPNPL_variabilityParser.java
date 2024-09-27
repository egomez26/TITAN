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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPNPL_variabilityParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'not'", "'and'", "'or'", "'implies'", "'pn'", "'fm'", "';'", "'PC'", "'for'", "'='", "','", "'('", "')'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalPNPL_variabilityParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalPNPL_variabilityParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalPNPL_variabilityParser.tokenNames; }
    public String getGrammarFileName() { return "InternalPNPL_variability.g"; }


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



    // $ANTLR start "entryRuleVariability"
    // InternalPNPL_variability.g:53:1: entryRuleVariability : ruleVariability EOF ;
    public final void entryRuleVariability() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:54:1: ( ruleVariability EOF )
            // InternalPNPL_variability.g:55:1: ruleVariability EOF
            {
             before(grammarAccess.getVariabilityRule()); 
            pushFollow(FOLLOW_1);
            ruleVariability();

            state._fsp--;

             after(grammarAccess.getVariabilityRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVariability"


    // $ANTLR start "ruleVariability"
    // InternalPNPL_variability.g:62:1: ruleVariability : ( ( rule__Variability__Group__0 ) ) ;
    public final void ruleVariability() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:66:2: ( ( ( rule__Variability__Group__0 ) ) )
            // InternalPNPL_variability.g:67:2: ( ( rule__Variability__Group__0 ) )
            {
            // InternalPNPL_variability.g:67:2: ( ( rule__Variability__Group__0 ) )
            // InternalPNPL_variability.g:68:3: ( rule__Variability__Group__0 )
            {
             before(grammarAccess.getVariabilityAccess().getGroup()); 
            // InternalPNPL_variability.g:69:3: ( rule__Variability__Group__0 )
            // InternalPNPL_variability.g:69:4: rule__Variability__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Variability__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVariabilityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVariability"


    // $ANTLR start "entryRuleFileURI"
    // InternalPNPL_variability.g:78:1: entryRuleFileURI : ruleFileURI EOF ;
    public final void entryRuleFileURI() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:79:1: ( ruleFileURI EOF )
            // InternalPNPL_variability.g:80:1: ruleFileURI EOF
            {
             before(grammarAccess.getFileURIRule()); 
            pushFollow(FOLLOW_1);
            ruleFileURI();

            state._fsp--;

             after(grammarAccess.getFileURIRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFileURI"


    // $ANTLR start "ruleFileURI"
    // InternalPNPL_variability.g:87:1: ruleFileURI : ( ( rule__FileURI__Group__0 ) ) ;
    public final void ruleFileURI() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:91:2: ( ( ( rule__FileURI__Group__0 ) ) )
            // InternalPNPL_variability.g:92:2: ( ( rule__FileURI__Group__0 ) )
            {
            // InternalPNPL_variability.g:92:2: ( ( rule__FileURI__Group__0 ) )
            // InternalPNPL_variability.g:93:3: ( rule__FileURI__Group__0 )
            {
             before(grammarAccess.getFileURIAccess().getGroup()); 
            // InternalPNPL_variability.g:94:3: ( rule__FileURI__Group__0 )
            // InternalPNPL_variability.g:94:4: rule__FileURI__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FileURI__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFileURIAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFileURI"


    // $ANTLR start "entryRuleExpression"
    // InternalPNPL_variability.g:103:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:104:1: ( ruleExpression EOF )
            // InternalPNPL_variability.g:105:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalPNPL_variability.g:112:1: ruleExpression : ( ( rule__Expression__Alternatives ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:116:2: ( ( ( rule__Expression__Alternatives ) ) )
            // InternalPNPL_variability.g:117:2: ( ( rule__Expression__Alternatives ) )
            {
            // InternalPNPL_variability.g:117:2: ( ( rule__Expression__Alternatives ) )
            // InternalPNPL_variability.g:118:3: ( rule__Expression__Alternatives )
            {
             before(grammarAccess.getExpressionAccess().getAlternatives()); 
            // InternalPNPL_variability.g:119:3: ( rule__Expression__Alternatives )
            // InternalPNPL_variability.g:119:4: rule__Expression__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Expression__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRulePresenceCondition"
    // InternalPNPL_variability.g:128:1: entryRulePresenceCondition : rulePresenceCondition EOF ;
    public final void entryRulePresenceCondition() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:129:1: ( rulePresenceCondition EOF )
            // InternalPNPL_variability.g:130:1: rulePresenceCondition EOF
            {
             before(grammarAccess.getPresenceConditionRule()); 
            pushFollow(FOLLOW_1);
            rulePresenceCondition();

            state._fsp--;

             after(grammarAccess.getPresenceConditionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePresenceCondition"


    // $ANTLR start "rulePresenceCondition"
    // InternalPNPL_variability.g:137:1: rulePresenceCondition : ( ( rule__PresenceCondition__Group__0 ) ) ;
    public final void rulePresenceCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:141:2: ( ( ( rule__PresenceCondition__Group__0 ) ) )
            // InternalPNPL_variability.g:142:2: ( ( rule__PresenceCondition__Group__0 ) )
            {
            // InternalPNPL_variability.g:142:2: ( ( rule__PresenceCondition__Group__0 ) )
            // InternalPNPL_variability.g:143:3: ( rule__PresenceCondition__Group__0 )
            {
             before(grammarAccess.getPresenceConditionAccess().getGroup()); 
            // InternalPNPL_variability.g:144:3: ( rule__PresenceCondition__Group__0 )
            // InternalPNPL_variability.g:144:4: rule__PresenceCondition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPresenceConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePresenceCondition"


    // $ANTLR start "entryRuleFeature"
    // InternalPNPL_variability.g:153:1: entryRuleFeature : ruleFeature EOF ;
    public final void entryRuleFeature() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:154:1: ( ruleFeature EOF )
            // InternalPNPL_variability.g:155:1: ruleFeature EOF
            {
             before(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            ruleFeature();

            state._fsp--;

             after(grammarAccess.getFeatureRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalPNPL_variability.g:162:1: ruleFeature : ( ( rule__Feature__FeatureAssignment ) ) ;
    public final void ruleFeature() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:166:2: ( ( ( rule__Feature__FeatureAssignment ) ) )
            // InternalPNPL_variability.g:167:2: ( ( rule__Feature__FeatureAssignment ) )
            {
            // InternalPNPL_variability.g:167:2: ( ( rule__Feature__FeatureAssignment ) )
            // InternalPNPL_variability.g:168:3: ( rule__Feature__FeatureAssignment )
            {
             before(grammarAccess.getFeatureAccess().getFeatureAssignment()); 
            // InternalPNPL_variability.g:169:3: ( rule__Feature__FeatureAssignment )
            // InternalPNPL_variability.g:169:4: rule__Feature__FeatureAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Feature__FeatureAssignment();

            state._fsp--;


            }

             after(grammarAccess.getFeatureAccess().getFeatureAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleUnaryExpression"
    // InternalPNPL_variability.g:178:1: entryRuleUnaryExpression : ruleUnaryExpression EOF ;
    public final void entryRuleUnaryExpression() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:179:1: ( ruleUnaryExpression EOF )
            // InternalPNPL_variability.g:180:1: ruleUnaryExpression EOF
            {
             before(grammarAccess.getUnaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleUnaryExpression();

            state._fsp--;

             after(grammarAccess.getUnaryExpressionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnaryExpression"


    // $ANTLR start "ruleUnaryExpression"
    // InternalPNPL_variability.g:187:1: ruleUnaryExpression : ( ( rule__UnaryExpression__Group__0 ) ) ;
    public final void ruleUnaryExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:191:2: ( ( ( rule__UnaryExpression__Group__0 ) ) )
            // InternalPNPL_variability.g:192:2: ( ( rule__UnaryExpression__Group__0 ) )
            {
            // InternalPNPL_variability.g:192:2: ( ( rule__UnaryExpression__Group__0 ) )
            // InternalPNPL_variability.g:193:3: ( rule__UnaryExpression__Group__0 )
            {
             before(grammarAccess.getUnaryExpressionAccess().getGroup()); 
            // InternalPNPL_variability.g:194:3: ( rule__UnaryExpression__Group__0 )
            // InternalPNPL_variability.g:194:4: rule__UnaryExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getUnaryExpressionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnaryExpression"


    // $ANTLR start "entryRuleBinaryExpression"
    // InternalPNPL_variability.g:203:1: entryRuleBinaryExpression : ruleBinaryExpression EOF ;
    public final void entryRuleBinaryExpression() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:204:1: ( ruleBinaryExpression EOF )
            // InternalPNPL_variability.g:205:1: ruleBinaryExpression EOF
            {
             before(grammarAccess.getBinaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleBinaryExpression();

            state._fsp--;

             after(grammarAccess.getBinaryExpressionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBinaryExpression"


    // $ANTLR start "ruleBinaryExpression"
    // InternalPNPL_variability.g:212:1: ruleBinaryExpression : ( ( rule__BinaryExpression__Group__0 ) ) ;
    public final void ruleBinaryExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:216:2: ( ( ( rule__BinaryExpression__Group__0 ) ) )
            // InternalPNPL_variability.g:217:2: ( ( rule__BinaryExpression__Group__0 ) )
            {
            // InternalPNPL_variability.g:217:2: ( ( rule__BinaryExpression__Group__0 ) )
            // InternalPNPL_variability.g:218:3: ( rule__BinaryExpression__Group__0 )
            {
             before(grammarAccess.getBinaryExpressionAccess().getGroup()); 
            // InternalPNPL_variability.g:219:3: ( rule__BinaryExpression__Group__0 )
            // InternalPNPL_variability.g:219:4: rule__BinaryExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__BinaryExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBinaryExpressionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBinaryExpression"


    // $ANTLR start "entryRuleEString"
    // InternalPNPL_variability.g:228:1: entryRuleEString : ruleEString EOF ;
    public final void entryRuleEString() throws RecognitionException {
        try {
            // InternalPNPL_variability.g:229:1: ( ruleEString EOF )
            // InternalPNPL_variability.g:230:1: ruleEString EOF
            {
             before(grammarAccess.getEStringRule()); 
            pushFollow(FOLLOW_1);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getEStringRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // InternalPNPL_variability.g:237:1: ruleEString : ( ( rule__EString__Alternatives ) ) ;
    public final void ruleEString() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:241:2: ( ( ( rule__EString__Alternatives ) ) )
            // InternalPNPL_variability.g:242:2: ( ( rule__EString__Alternatives ) )
            {
            // InternalPNPL_variability.g:242:2: ( ( rule__EString__Alternatives ) )
            // InternalPNPL_variability.g:243:3: ( rule__EString__Alternatives )
            {
             before(grammarAccess.getEStringAccess().getAlternatives()); 
            // InternalPNPL_variability.g:244:3: ( rule__EString__Alternatives )
            // InternalPNPL_variability.g:244:4: rule__EString__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__EString__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getEStringAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "ruleUnaryOperator"
    // InternalPNPL_variability.g:253:1: ruleUnaryOperator : ( ( 'not' ) ) ;
    public final void ruleUnaryOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:257:1: ( ( ( 'not' ) ) )
            // InternalPNPL_variability.g:258:2: ( ( 'not' ) )
            {
            // InternalPNPL_variability.g:258:2: ( ( 'not' ) )
            // InternalPNPL_variability.g:259:3: ( 'not' )
            {
             before(grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration()); 
            // InternalPNPL_variability.g:260:3: ( 'not' )
            // InternalPNPL_variability.g:260:4: 'not'
            {
            match(input,11,FOLLOW_2); 

            }

             after(grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnaryOperator"


    // $ANTLR start "ruleBinaryOperator"
    // InternalPNPL_variability.g:269:1: ruleBinaryOperator : ( ( rule__BinaryOperator__Alternatives ) ) ;
    public final void ruleBinaryOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:273:1: ( ( ( rule__BinaryOperator__Alternatives ) ) )
            // InternalPNPL_variability.g:274:2: ( ( rule__BinaryOperator__Alternatives ) )
            {
            // InternalPNPL_variability.g:274:2: ( ( rule__BinaryOperator__Alternatives ) )
            // InternalPNPL_variability.g:275:3: ( rule__BinaryOperator__Alternatives )
            {
             before(grammarAccess.getBinaryOperatorAccess().getAlternatives()); 
            // InternalPNPL_variability.g:276:3: ( rule__BinaryOperator__Alternatives )
            // InternalPNPL_variability.g:276:4: rule__BinaryOperator__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__BinaryOperator__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBinaryOperatorAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBinaryOperator"


    // $ANTLR start "rule__Expression__Alternatives"
    // InternalPNPL_variability.g:284:1: rule__Expression__Alternatives : ( ( ruleFeature ) | ( ruleUnaryExpression ) | ( ruleBinaryExpression ) );
    public final void rule__Expression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:288:1: ( ( ruleFeature ) | ( ruleUnaryExpression ) | ( ruleBinaryExpression ) )
            int alt1=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_ID:
                {
                alt1=1;
                }
                break;
            case 11:
                {
                alt1=2;
                }
                break;
            case 22:
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalPNPL_variability.g:289:2: ( ruleFeature )
                    {
                    // InternalPNPL_variability.g:289:2: ( ruleFeature )
                    // InternalPNPL_variability.g:290:3: ruleFeature
                    {
                     before(grammarAccess.getExpressionAccess().getFeatureParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleFeature();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getFeatureParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalPNPL_variability.g:295:2: ( ruleUnaryExpression )
                    {
                    // InternalPNPL_variability.g:295:2: ( ruleUnaryExpression )
                    // InternalPNPL_variability.g:296:3: ruleUnaryExpression
                    {
                     before(grammarAccess.getExpressionAccess().getUnaryExpressionParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleUnaryExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getUnaryExpressionParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalPNPL_variability.g:301:2: ( ruleBinaryExpression )
                    {
                    // InternalPNPL_variability.g:301:2: ( ruleBinaryExpression )
                    // InternalPNPL_variability.g:302:3: ruleBinaryExpression
                    {
                     before(grammarAccess.getExpressionAccess().getBinaryExpressionParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleBinaryExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getBinaryExpressionParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Alternatives"


    // $ANTLR start "rule__EString__Alternatives"
    // InternalPNPL_variability.g:311:1: rule__EString__Alternatives : ( ( RULE_STRING ) | ( RULE_ID ) );
    public final void rule__EString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:315:1: ( ( RULE_STRING ) | ( RULE_ID ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_STRING) ) {
                alt2=1;
            }
            else if ( (LA2_0==RULE_ID) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalPNPL_variability.g:316:2: ( RULE_STRING )
                    {
                    // InternalPNPL_variability.g:316:2: ( RULE_STRING )
                    // InternalPNPL_variability.g:317:3: RULE_STRING
                    {
                     before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalPNPL_variability.g:322:2: ( RULE_ID )
                    {
                    // InternalPNPL_variability.g:322:2: ( RULE_ID )
                    // InternalPNPL_variability.g:323:3: RULE_ID
                    {
                     before(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EString__Alternatives"


    // $ANTLR start "rule__BinaryOperator__Alternatives"
    // InternalPNPL_variability.g:332:1: rule__BinaryOperator__Alternatives : ( ( ( 'and' ) ) | ( ( 'or' ) ) | ( ( 'implies' ) ) );
    public final void rule__BinaryOperator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:336:1: ( ( ( 'and' ) ) | ( ( 'or' ) ) | ( ( 'implies' ) ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt3=1;
                }
                break;
            case 13:
                {
                alt3=2;
                }
                break;
            case 14:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalPNPL_variability.g:337:2: ( ( 'and' ) )
                    {
                    // InternalPNPL_variability.g:337:2: ( ( 'and' ) )
                    // InternalPNPL_variability.g:338:3: ( 'and' )
                    {
                     before(grammarAccess.getBinaryOperatorAccess().getANDEnumLiteralDeclaration_0()); 
                    // InternalPNPL_variability.g:339:3: ( 'and' )
                    // InternalPNPL_variability.g:339:4: 'and'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getBinaryOperatorAccess().getANDEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalPNPL_variability.g:343:2: ( ( 'or' ) )
                    {
                    // InternalPNPL_variability.g:343:2: ( ( 'or' ) )
                    // InternalPNPL_variability.g:344:3: ( 'or' )
                    {
                     before(grammarAccess.getBinaryOperatorAccess().getOREnumLiteralDeclaration_1()); 
                    // InternalPNPL_variability.g:345:3: ( 'or' )
                    // InternalPNPL_variability.g:345:4: 'or'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getBinaryOperatorAccess().getOREnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalPNPL_variability.g:349:2: ( ( 'implies' ) )
                    {
                    // InternalPNPL_variability.g:349:2: ( ( 'implies' ) )
                    // InternalPNPL_variability.g:350:3: ( 'implies' )
                    {
                     before(grammarAccess.getBinaryOperatorAccess().getIMPLIESEnumLiteralDeclaration_2()); 
                    // InternalPNPL_variability.g:351:3: ( 'implies' )
                    // InternalPNPL_variability.g:351:4: 'implies'
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getBinaryOperatorAccess().getIMPLIESEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryOperator__Alternatives"


    // $ANTLR start "rule__Variability__Group__0"
    // InternalPNPL_variability.g:359:1: rule__Variability__Group__0 : rule__Variability__Group__0__Impl rule__Variability__Group__1 ;
    public final void rule__Variability__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:363:1: ( rule__Variability__Group__0__Impl rule__Variability__Group__1 )
            // InternalPNPL_variability.g:364:2: rule__Variability__Group__0__Impl rule__Variability__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Variability__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Variability__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__0"


    // $ANTLR start "rule__Variability__Group__0__Impl"
    // InternalPNPL_variability.g:371:1: rule__Variability__Group__0__Impl : ( 'pn' ) ;
    public final void rule__Variability__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:375:1: ( ( 'pn' ) )
            // InternalPNPL_variability.g:376:1: ( 'pn' )
            {
            // InternalPNPL_variability.g:376:1: ( 'pn' )
            // InternalPNPL_variability.g:377:2: 'pn'
            {
             before(grammarAccess.getVariabilityAccess().getPnKeyword_0()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getVariabilityAccess().getPnKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__0__Impl"


    // $ANTLR start "rule__Variability__Group__1"
    // InternalPNPL_variability.g:386:1: rule__Variability__Group__1 : rule__Variability__Group__1__Impl rule__Variability__Group__2 ;
    public final void rule__Variability__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:390:1: ( rule__Variability__Group__1__Impl rule__Variability__Group__2 )
            // InternalPNPL_variability.g:391:2: rule__Variability__Group__1__Impl rule__Variability__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Variability__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Variability__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__1"


    // $ANTLR start "rule__Variability__Group__1__Impl"
    // InternalPNPL_variability.g:398:1: rule__Variability__Group__1__Impl : ( ( rule__Variability__PetrinetAssignment_1 ) ) ;
    public final void rule__Variability__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:402:1: ( ( ( rule__Variability__PetrinetAssignment_1 ) ) )
            // InternalPNPL_variability.g:403:1: ( ( rule__Variability__PetrinetAssignment_1 ) )
            {
            // InternalPNPL_variability.g:403:1: ( ( rule__Variability__PetrinetAssignment_1 ) )
            // InternalPNPL_variability.g:404:2: ( rule__Variability__PetrinetAssignment_1 )
            {
             before(grammarAccess.getVariabilityAccess().getPetrinetAssignment_1()); 
            // InternalPNPL_variability.g:405:2: ( rule__Variability__PetrinetAssignment_1 )
            // InternalPNPL_variability.g:405:3: rule__Variability__PetrinetAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Variability__PetrinetAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVariabilityAccess().getPetrinetAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__1__Impl"


    // $ANTLR start "rule__Variability__Group__2"
    // InternalPNPL_variability.g:413:1: rule__Variability__Group__2 : rule__Variability__Group__2__Impl rule__Variability__Group__3 ;
    public final void rule__Variability__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:417:1: ( rule__Variability__Group__2__Impl rule__Variability__Group__3 )
            // InternalPNPL_variability.g:418:2: rule__Variability__Group__2__Impl rule__Variability__Group__3
            {
            pushFollow(FOLLOW_3);
            rule__Variability__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Variability__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__2"


    // $ANTLR start "rule__Variability__Group__2__Impl"
    // InternalPNPL_variability.g:425:1: rule__Variability__Group__2__Impl : ( 'fm' ) ;
    public final void rule__Variability__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:429:1: ( ( 'fm' ) )
            // InternalPNPL_variability.g:430:1: ( 'fm' )
            {
            // InternalPNPL_variability.g:430:1: ( 'fm' )
            // InternalPNPL_variability.g:431:2: 'fm'
            {
             before(grammarAccess.getVariabilityAccess().getFmKeyword_2()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getVariabilityAccess().getFmKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__2__Impl"


    // $ANTLR start "rule__Variability__Group__3"
    // InternalPNPL_variability.g:440:1: rule__Variability__Group__3 : rule__Variability__Group__3__Impl rule__Variability__Group__4 ;
    public final void rule__Variability__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:444:1: ( rule__Variability__Group__3__Impl rule__Variability__Group__4 )
            // InternalPNPL_variability.g:445:2: rule__Variability__Group__3__Impl rule__Variability__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__Variability__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Variability__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__3"


    // $ANTLR start "rule__Variability__Group__3__Impl"
    // InternalPNPL_variability.g:452:1: rule__Variability__Group__3__Impl : ( ( rule__Variability__FeaturemodelAssignment_3 ) ) ;
    public final void rule__Variability__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:456:1: ( ( ( rule__Variability__FeaturemodelAssignment_3 ) ) )
            // InternalPNPL_variability.g:457:1: ( ( rule__Variability__FeaturemodelAssignment_3 ) )
            {
            // InternalPNPL_variability.g:457:1: ( ( rule__Variability__FeaturemodelAssignment_3 ) )
            // InternalPNPL_variability.g:458:2: ( rule__Variability__FeaturemodelAssignment_3 )
            {
             before(grammarAccess.getVariabilityAccess().getFeaturemodelAssignment_3()); 
            // InternalPNPL_variability.g:459:2: ( rule__Variability__FeaturemodelAssignment_3 )
            // InternalPNPL_variability.g:459:3: rule__Variability__FeaturemodelAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Variability__FeaturemodelAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getVariabilityAccess().getFeaturemodelAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__3__Impl"


    // $ANTLR start "rule__Variability__Group__4"
    // InternalPNPL_variability.g:467:1: rule__Variability__Group__4 : rule__Variability__Group__4__Impl ;
    public final void rule__Variability__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:471:1: ( rule__Variability__Group__4__Impl )
            // InternalPNPL_variability.g:472:2: rule__Variability__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Variability__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__4"


    // $ANTLR start "rule__Variability__Group__4__Impl"
    // InternalPNPL_variability.g:478:1: rule__Variability__Group__4__Impl : ( ( rule__Variability__Group_4__0 )* ) ;
    public final void rule__Variability__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:482:1: ( ( ( rule__Variability__Group_4__0 )* ) )
            // InternalPNPL_variability.g:483:1: ( ( rule__Variability__Group_4__0 )* )
            {
            // InternalPNPL_variability.g:483:1: ( ( rule__Variability__Group_4__0 )* )
            // InternalPNPL_variability.g:484:2: ( rule__Variability__Group_4__0 )*
            {
             before(grammarAccess.getVariabilityAccess().getGroup_4()); 
            // InternalPNPL_variability.g:485:2: ( rule__Variability__Group_4__0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==18) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalPNPL_variability.g:485:3: rule__Variability__Group_4__0
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Variability__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getVariabilityAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group__4__Impl"


    // $ANTLR start "rule__Variability__Group_4__0"
    // InternalPNPL_variability.g:494:1: rule__Variability__Group_4__0 : rule__Variability__Group_4__0__Impl rule__Variability__Group_4__1 ;
    public final void rule__Variability__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:498:1: ( rule__Variability__Group_4__0__Impl rule__Variability__Group_4__1 )
            // InternalPNPL_variability.g:499:2: rule__Variability__Group_4__0__Impl rule__Variability__Group_4__1
            {
            pushFollow(FOLLOW_7);
            rule__Variability__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Variability__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group_4__0"


    // $ANTLR start "rule__Variability__Group_4__0__Impl"
    // InternalPNPL_variability.g:506:1: rule__Variability__Group_4__0__Impl : ( ( rule__Variability__PresenceconditionAssignment_4_0 ) ) ;
    public final void rule__Variability__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:510:1: ( ( ( rule__Variability__PresenceconditionAssignment_4_0 ) ) )
            // InternalPNPL_variability.g:511:1: ( ( rule__Variability__PresenceconditionAssignment_4_0 ) )
            {
            // InternalPNPL_variability.g:511:1: ( ( rule__Variability__PresenceconditionAssignment_4_0 ) )
            // InternalPNPL_variability.g:512:2: ( rule__Variability__PresenceconditionAssignment_4_0 )
            {
             before(grammarAccess.getVariabilityAccess().getPresenceconditionAssignment_4_0()); 
            // InternalPNPL_variability.g:513:2: ( rule__Variability__PresenceconditionAssignment_4_0 )
            // InternalPNPL_variability.g:513:3: rule__Variability__PresenceconditionAssignment_4_0
            {
            pushFollow(FOLLOW_2);
            rule__Variability__PresenceconditionAssignment_4_0();

            state._fsp--;


            }

             after(grammarAccess.getVariabilityAccess().getPresenceconditionAssignment_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group_4__0__Impl"


    // $ANTLR start "rule__Variability__Group_4__1"
    // InternalPNPL_variability.g:521:1: rule__Variability__Group_4__1 : rule__Variability__Group_4__1__Impl ;
    public final void rule__Variability__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:525:1: ( rule__Variability__Group_4__1__Impl )
            // InternalPNPL_variability.g:526:2: rule__Variability__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Variability__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group_4__1"


    // $ANTLR start "rule__Variability__Group_4__1__Impl"
    // InternalPNPL_variability.g:532:1: rule__Variability__Group_4__1__Impl : ( ';' ) ;
    public final void rule__Variability__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:536:1: ( ( ';' ) )
            // InternalPNPL_variability.g:537:1: ( ';' )
            {
            // InternalPNPL_variability.g:537:1: ( ';' )
            // InternalPNPL_variability.g:538:2: ';'
            {
             before(grammarAccess.getVariabilityAccess().getSemicolonKeyword_4_1()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getVariabilityAccess().getSemicolonKeyword_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__Group_4__1__Impl"


    // $ANTLR start "rule__FileURI__Group__0"
    // InternalPNPL_variability.g:548:1: rule__FileURI__Group__0 : rule__FileURI__Group__0__Impl rule__FileURI__Group__1 ;
    public final void rule__FileURI__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:552:1: ( rule__FileURI__Group__0__Impl rule__FileURI__Group__1 )
            // InternalPNPL_variability.g:553:2: rule__FileURI__Group__0__Impl rule__FileURI__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__FileURI__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FileURI__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FileURI__Group__0"


    // $ANTLR start "rule__FileURI__Group__0__Impl"
    // InternalPNPL_variability.g:560:1: rule__FileURI__Group__0__Impl : ( () ) ;
    public final void rule__FileURI__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:564:1: ( ( () ) )
            // InternalPNPL_variability.g:565:1: ( () )
            {
            // InternalPNPL_variability.g:565:1: ( () )
            // InternalPNPL_variability.g:566:2: ()
            {
             before(grammarAccess.getFileURIAccess().getFileURIAction_0()); 
            // InternalPNPL_variability.g:567:2: ()
            // InternalPNPL_variability.g:567:3: 
            {
            }

             after(grammarAccess.getFileURIAccess().getFileURIAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FileURI__Group__0__Impl"


    // $ANTLR start "rule__FileURI__Group__1"
    // InternalPNPL_variability.g:575:1: rule__FileURI__Group__1 : rule__FileURI__Group__1__Impl ;
    public final void rule__FileURI__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:579:1: ( rule__FileURI__Group__1__Impl )
            // InternalPNPL_variability.g:580:2: rule__FileURI__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FileURI__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FileURI__Group__1"


    // $ANTLR start "rule__FileURI__Group__1__Impl"
    // InternalPNPL_variability.g:586:1: rule__FileURI__Group__1__Impl : ( ( rule__FileURI__ImportURIAssignment_1 ) ) ;
    public final void rule__FileURI__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:590:1: ( ( ( rule__FileURI__ImportURIAssignment_1 ) ) )
            // InternalPNPL_variability.g:591:1: ( ( rule__FileURI__ImportURIAssignment_1 ) )
            {
            // InternalPNPL_variability.g:591:1: ( ( rule__FileURI__ImportURIAssignment_1 ) )
            // InternalPNPL_variability.g:592:2: ( rule__FileURI__ImportURIAssignment_1 )
            {
             before(grammarAccess.getFileURIAccess().getImportURIAssignment_1()); 
            // InternalPNPL_variability.g:593:2: ( rule__FileURI__ImportURIAssignment_1 )
            // InternalPNPL_variability.g:593:3: rule__FileURI__ImportURIAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__FileURI__ImportURIAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFileURIAccess().getImportURIAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FileURI__Group__1__Impl"


    // $ANTLR start "rule__PresenceCondition__Group__0"
    // InternalPNPL_variability.g:602:1: rule__PresenceCondition__Group__0 : rule__PresenceCondition__Group__0__Impl rule__PresenceCondition__Group__1 ;
    public final void rule__PresenceCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:606:1: ( rule__PresenceCondition__Group__0__Impl rule__PresenceCondition__Group__1 )
            // InternalPNPL_variability.g:607:2: rule__PresenceCondition__Group__0__Impl rule__PresenceCondition__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__PresenceCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__0"


    // $ANTLR start "rule__PresenceCondition__Group__0__Impl"
    // InternalPNPL_variability.g:614:1: rule__PresenceCondition__Group__0__Impl : ( 'PC' ) ;
    public final void rule__PresenceCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:618:1: ( ( 'PC' ) )
            // InternalPNPL_variability.g:619:1: ( 'PC' )
            {
            // InternalPNPL_variability.g:619:1: ( 'PC' )
            // InternalPNPL_variability.g:620:2: 'PC'
            {
             before(grammarAccess.getPresenceConditionAccess().getPCKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getPresenceConditionAccess().getPCKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__0__Impl"


    // $ANTLR start "rule__PresenceCondition__Group__1"
    // InternalPNPL_variability.g:629:1: rule__PresenceCondition__Group__1 : rule__PresenceCondition__Group__1__Impl rule__PresenceCondition__Group__2 ;
    public final void rule__PresenceCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:633:1: ( rule__PresenceCondition__Group__1__Impl rule__PresenceCondition__Group__2 )
            // InternalPNPL_variability.g:634:2: rule__PresenceCondition__Group__1__Impl rule__PresenceCondition__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__PresenceCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__1"


    // $ANTLR start "rule__PresenceCondition__Group__1__Impl"
    // InternalPNPL_variability.g:641:1: rule__PresenceCondition__Group__1__Impl : ( 'for' ) ;
    public final void rule__PresenceCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:645:1: ( ( 'for' ) )
            // InternalPNPL_variability.g:646:1: ( 'for' )
            {
            // InternalPNPL_variability.g:646:1: ( 'for' )
            // InternalPNPL_variability.g:647:2: 'for'
            {
             before(grammarAccess.getPresenceConditionAccess().getForKeyword_1()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getPresenceConditionAccess().getForKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__1__Impl"


    // $ANTLR start "rule__PresenceCondition__Group__2"
    // InternalPNPL_variability.g:656:1: rule__PresenceCondition__Group__2 : rule__PresenceCondition__Group__2__Impl rule__PresenceCondition__Group__3 ;
    public final void rule__PresenceCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:660:1: ( rule__PresenceCondition__Group__2__Impl rule__PresenceCondition__Group__3 )
            // InternalPNPL_variability.g:661:2: rule__PresenceCondition__Group__2__Impl rule__PresenceCondition__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__PresenceCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__2"


    // $ANTLR start "rule__PresenceCondition__Group__2__Impl"
    // InternalPNPL_variability.g:668:1: rule__PresenceCondition__Group__2__Impl : ( ( rule__PresenceCondition__ElementsAssignment_2 ) ) ;
    public final void rule__PresenceCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:672:1: ( ( ( rule__PresenceCondition__ElementsAssignment_2 ) ) )
            // InternalPNPL_variability.g:673:1: ( ( rule__PresenceCondition__ElementsAssignment_2 ) )
            {
            // InternalPNPL_variability.g:673:1: ( ( rule__PresenceCondition__ElementsAssignment_2 ) )
            // InternalPNPL_variability.g:674:2: ( rule__PresenceCondition__ElementsAssignment_2 )
            {
             before(grammarAccess.getPresenceConditionAccess().getElementsAssignment_2()); 
            // InternalPNPL_variability.g:675:2: ( rule__PresenceCondition__ElementsAssignment_2 )
            // InternalPNPL_variability.g:675:3: rule__PresenceCondition__ElementsAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__PresenceCondition__ElementsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getPresenceConditionAccess().getElementsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__2__Impl"


    // $ANTLR start "rule__PresenceCondition__Group__3"
    // InternalPNPL_variability.g:683:1: rule__PresenceCondition__Group__3 : rule__PresenceCondition__Group__3__Impl rule__PresenceCondition__Group__4 ;
    public final void rule__PresenceCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:687:1: ( rule__PresenceCondition__Group__3__Impl rule__PresenceCondition__Group__4 )
            // InternalPNPL_variability.g:688:2: rule__PresenceCondition__Group__3__Impl rule__PresenceCondition__Group__4
            {
            pushFollow(FOLLOW_10);
            rule__PresenceCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__3"


    // $ANTLR start "rule__PresenceCondition__Group__3__Impl"
    // InternalPNPL_variability.g:695:1: rule__PresenceCondition__Group__3__Impl : ( ( rule__PresenceCondition__Group_3__0 )* ) ;
    public final void rule__PresenceCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:699:1: ( ( ( rule__PresenceCondition__Group_3__0 )* ) )
            // InternalPNPL_variability.g:700:1: ( ( rule__PresenceCondition__Group_3__0 )* )
            {
            // InternalPNPL_variability.g:700:1: ( ( rule__PresenceCondition__Group_3__0 )* )
            // InternalPNPL_variability.g:701:2: ( rule__PresenceCondition__Group_3__0 )*
            {
             before(grammarAccess.getPresenceConditionAccess().getGroup_3()); 
            // InternalPNPL_variability.g:702:2: ( rule__PresenceCondition__Group_3__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==21) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalPNPL_variability.g:702:3: rule__PresenceCondition__Group_3__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__PresenceCondition__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getPresenceConditionAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__3__Impl"


    // $ANTLR start "rule__PresenceCondition__Group__4"
    // InternalPNPL_variability.g:710:1: rule__PresenceCondition__Group__4 : rule__PresenceCondition__Group__4__Impl rule__PresenceCondition__Group__5 ;
    public final void rule__PresenceCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:714:1: ( rule__PresenceCondition__Group__4__Impl rule__PresenceCondition__Group__5 )
            // InternalPNPL_variability.g:715:2: rule__PresenceCondition__Group__4__Impl rule__PresenceCondition__Group__5
            {
            pushFollow(FOLLOW_12);
            rule__PresenceCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__4"


    // $ANTLR start "rule__PresenceCondition__Group__4__Impl"
    // InternalPNPL_variability.g:722:1: rule__PresenceCondition__Group__4__Impl : ( '=' ) ;
    public final void rule__PresenceCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:726:1: ( ( '=' ) )
            // InternalPNPL_variability.g:727:1: ( '=' )
            {
            // InternalPNPL_variability.g:727:1: ( '=' )
            // InternalPNPL_variability.g:728:2: '='
            {
             before(grammarAccess.getPresenceConditionAccess().getEqualsSignKeyword_4()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getPresenceConditionAccess().getEqualsSignKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__4__Impl"


    // $ANTLR start "rule__PresenceCondition__Group__5"
    // InternalPNPL_variability.g:737:1: rule__PresenceCondition__Group__5 : rule__PresenceCondition__Group__5__Impl ;
    public final void rule__PresenceCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:741:1: ( rule__PresenceCondition__Group__5__Impl )
            // InternalPNPL_variability.g:742:2: rule__PresenceCondition__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__5"


    // $ANTLR start "rule__PresenceCondition__Group__5__Impl"
    // InternalPNPL_variability.g:748:1: rule__PresenceCondition__Group__5__Impl : ( ( rule__PresenceCondition__ExpressionAssignment_5 ) ) ;
    public final void rule__PresenceCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:752:1: ( ( ( rule__PresenceCondition__ExpressionAssignment_5 ) ) )
            // InternalPNPL_variability.g:753:1: ( ( rule__PresenceCondition__ExpressionAssignment_5 ) )
            {
            // InternalPNPL_variability.g:753:1: ( ( rule__PresenceCondition__ExpressionAssignment_5 ) )
            // InternalPNPL_variability.g:754:2: ( rule__PresenceCondition__ExpressionAssignment_5 )
            {
             before(grammarAccess.getPresenceConditionAccess().getExpressionAssignment_5()); 
            // InternalPNPL_variability.g:755:2: ( rule__PresenceCondition__ExpressionAssignment_5 )
            // InternalPNPL_variability.g:755:3: rule__PresenceCondition__ExpressionAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__PresenceCondition__ExpressionAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getPresenceConditionAccess().getExpressionAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group__5__Impl"


    // $ANTLR start "rule__PresenceCondition__Group_3__0"
    // InternalPNPL_variability.g:764:1: rule__PresenceCondition__Group_3__0 : rule__PresenceCondition__Group_3__0__Impl rule__PresenceCondition__Group_3__1 ;
    public final void rule__PresenceCondition__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:768:1: ( rule__PresenceCondition__Group_3__0__Impl rule__PresenceCondition__Group_3__1 )
            // InternalPNPL_variability.g:769:2: rule__PresenceCondition__Group_3__0__Impl rule__PresenceCondition__Group_3__1
            {
            pushFollow(FOLLOW_9);
            rule__PresenceCondition__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group_3__0"


    // $ANTLR start "rule__PresenceCondition__Group_3__0__Impl"
    // InternalPNPL_variability.g:776:1: rule__PresenceCondition__Group_3__0__Impl : ( ',' ) ;
    public final void rule__PresenceCondition__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:780:1: ( ( ',' ) )
            // InternalPNPL_variability.g:781:1: ( ',' )
            {
            // InternalPNPL_variability.g:781:1: ( ',' )
            // InternalPNPL_variability.g:782:2: ','
            {
             before(grammarAccess.getPresenceConditionAccess().getCommaKeyword_3_0()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getPresenceConditionAccess().getCommaKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group_3__0__Impl"


    // $ANTLR start "rule__PresenceCondition__Group_3__1"
    // InternalPNPL_variability.g:791:1: rule__PresenceCondition__Group_3__1 : rule__PresenceCondition__Group_3__1__Impl ;
    public final void rule__PresenceCondition__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:795:1: ( rule__PresenceCondition__Group_3__1__Impl )
            // InternalPNPL_variability.g:796:2: rule__PresenceCondition__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PresenceCondition__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group_3__1"


    // $ANTLR start "rule__PresenceCondition__Group_3__1__Impl"
    // InternalPNPL_variability.g:802:1: rule__PresenceCondition__Group_3__1__Impl : ( ( rule__PresenceCondition__ElementsAssignment_3_1 ) ) ;
    public final void rule__PresenceCondition__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:806:1: ( ( ( rule__PresenceCondition__ElementsAssignment_3_1 ) ) )
            // InternalPNPL_variability.g:807:1: ( ( rule__PresenceCondition__ElementsAssignment_3_1 ) )
            {
            // InternalPNPL_variability.g:807:1: ( ( rule__PresenceCondition__ElementsAssignment_3_1 ) )
            // InternalPNPL_variability.g:808:2: ( rule__PresenceCondition__ElementsAssignment_3_1 )
            {
             before(grammarAccess.getPresenceConditionAccess().getElementsAssignment_3_1()); 
            // InternalPNPL_variability.g:809:2: ( rule__PresenceCondition__ElementsAssignment_3_1 )
            // InternalPNPL_variability.g:809:3: rule__PresenceCondition__ElementsAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__PresenceCondition__ElementsAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getPresenceConditionAccess().getElementsAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__Group_3__1__Impl"


    // $ANTLR start "rule__UnaryExpression__Group__0"
    // InternalPNPL_variability.g:818:1: rule__UnaryExpression__Group__0 : rule__UnaryExpression__Group__0__Impl rule__UnaryExpression__Group__1 ;
    public final void rule__UnaryExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:822:1: ( rule__UnaryExpression__Group__0__Impl rule__UnaryExpression__Group__1 )
            // InternalPNPL_variability.g:823:2: rule__UnaryExpression__Group__0__Impl rule__UnaryExpression__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__UnaryExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UnaryExpression__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__0"


    // $ANTLR start "rule__UnaryExpression__Group__0__Impl"
    // InternalPNPL_variability.g:830:1: rule__UnaryExpression__Group__0__Impl : ( ( rule__UnaryExpression__OperatorAssignment_0 ) ) ;
    public final void rule__UnaryExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:834:1: ( ( ( rule__UnaryExpression__OperatorAssignment_0 ) ) )
            // InternalPNPL_variability.g:835:1: ( ( rule__UnaryExpression__OperatorAssignment_0 ) )
            {
            // InternalPNPL_variability.g:835:1: ( ( rule__UnaryExpression__OperatorAssignment_0 ) )
            // InternalPNPL_variability.g:836:2: ( rule__UnaryExpression__OperatorAssignment_0 )
            {
             before(grammarAccess.getUnaryExpressionAccess().getOperatorAssignment_0()); 
            // InternalPNPL_variability.g:837:2: ( rule__UnaryExpression__OperatorAssignment_0 )
            // InternalPNPL_variability.g:837:3: rule__UnaryExpression__OperatorAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__OperatorAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getUnaryExpressionAccess().getOperatorAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__0__Impl"


    // $ANTLR start "rule__UnaryExpression__Group__1"
    // InternalPNPL_variability.g:845:1: rule__UnaryExpression__Group__1 : rule__UnaryExpression__Group__1__Impl ;
    public final void rule__UnaryExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:849:1: ( rule__UnaryExpression__Group__1__Impl )
            // InternalPNPL_variability.g:850:2: rule__UnaryExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__1"


    // $ANTLR start "rule__UnaryExpression__Group__1__Impl"
    // InternalPNPL_variability.g:856:1: rule__UnaryExpression__Group__1__Impl : ( ( rule__UnaryExpression__RightAssignment_1 ) ) ;
    public final void rule__UnaryExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:860:1: ( ( ( rule__UnaryExpression__RightAssignment_1 ) ) )
            // InternalPNPL_variability.g:861:1: ( ( rule__UnaryExpression__RightAssignment_1 ) )
            {
            // InternalPNPL_variability.g:861:1: ( ( rule__UnaryExpression__RightAssignment_1 ) )
            // InternalPNPL_variability.g:862:2: ( rule__UnaryExpression__RightAssignment_1 )
            {
             before(grammarAccess.getUnaryExpressionAccess().getRightAssignment_1()); 
            // InternalPNPL_variability.g:863:2: ( rule__UnaryExpression__RightAssignment_1 )
            // InternalPNPL_variability.g:863:3: rule__UnaryExpression__RightAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__RightAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getUnaryExpressionAccess().getRightAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__1__Impl"


    // $ANTLR start "rule__BinaryExpression__Group__0"
    // InternalPNPL_variability.g:872:1: rule__BinaryExpression__Group__0 : rule__BinaryExpression__Group__0__Impl rule__BinaryExpression__Group__1 ;
    public final void rule__BinaryExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:876:1: ( rule__BinaryExpression__Group__0__Impl rule__BinaryExpression__Group__1 )
            // InternalPNPL_variability.g:877:2: rule__BinaryExpression__Group__0__Impl rule__BinaryExpression__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__BinaryExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BinaryExpression__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__0"


    // $ANTLR start "rule__BinaryExpression__Group__0__Impl"
    // InternalPNPL_variability.g:884:1: rule__BinaryExpression__Group__0__Impl : ( '(' ) ;
    public final void rule__BinaryExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:888:1: ( ( '(' ) )
            // InternalPNPL_variability.g:889:1: ( '(' )
            {
            // InternalPNPL_variability.g:889:1: ( '(' )
            // InternalPNPL_variability.g:890:2: '('
            {
             before(grammarAccess.getBinaryExpressionAccess().getLeftParenthesisKeyword_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getBinaryExpressionAccess().getLeftParenthesisKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__0__Impl"


    // $ANTLR start "rule__BinaryExpression__Group__1"
    // InternalPNPL_variability.g:899:1: rule__BinaryExpression__Group__1 : rule__BinaryExpression__Group__1__Impl rule__BinaryExpression__Group__2 ;
    public final void rule__BinaryExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:903:1: ( rule__BinaryExpression__Group__1__Impl rule__BinaryExpression__Group__2 )
            // InternalPNPL_variability.g:904:2: rule__BinaryExpression__Group__1__Impl rule__BinaryExpression__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__BinaryExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BinaryExpression__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__1"


    // $ANTLR start "rule__BinaryExpression__Group__1__Impl"
    // InternalPNPL_variability.g:911:1: rule__BinaryExpression__Group__1__Impl : ( ( rule__BinaryExpression__LeftAssignment_1 ) ) ;
    public final void rule__BinaryExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:915:1: ( ( ( rule__BinaryExpression__LeftAssignment_1 ) ) )
            // InternalPNPL_variability.g:916:1: ( ( rule__BinaryExpression__LeftAssignment_1 ) )
            {
            // InternalPNPL_variability.g:916:1: ( ( rule__BinaryExpression__LeftAssignment_1 ) )
            // InternalPNPL_variability.g:917:2: ( rule__BinaryExpression__LeftAssignment_1 )
            {
             before(grammarAccess.getBinaryExpressionAccess().getLeftAssignment_1()); 
            // InternalPNPL_variability.g:918:2: ( rule__BinaryExpression__LeftAssignment_1 )
            // InternalPNPL_variability.g:918:3: rule__BinaryExpression__LeftAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__BinaryExpression__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getBinaryExpressionAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__1__Impl"


    // $ANTLR start "rule__BinaryExpression__Group__2"
    // InternalPNPL_variability.g:926:1: rule__BinaryExpression__Group__2 : rule__BinaryExpression__Group__2__Impl rule__BinaryExpression__Group__3 ;
    public final void rule__BinaryExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:930:1: ( rule__BinaryExpression__Group__2__Impl rule__BinaryExpression__Group__3 )
            // InternalPNPL_variability.g:931:2: rule__BinaryExpression__Group__2__Impl rule__BinaryExpression__Group__3
            {
            pushFollow(FOLLOW_12);
            rule__BinaryExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BinaryExpression__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__2"


    // $ANTLR start "rule__BinaryExpression__Group__2__Impl"
    // InternalPNPL_variability.g:938:1: rule__BinaryExpression__Group__2__Impl : ( ( rule__BinaryExpression__OperatorAssignment_2 ) ) ;
    public final void rule__BinaryExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:942:1: ( ( ( rule__BinaryExpression__OperatorAssignment_2 ) ) )
            // InternalPNPL_variability.g:943:1: ( ( rule__BinaryExpression__OperatorAssignment_2 ) )
            {
            // InternalPNPL_variability.g:943:1: ( ( rule__BinaryExpression__OperatorAssignment_2 ) )
            // InternalPNPL_variability.g:944:2: ( rule__BinaryExpression__OperatorAssignment_2 )
            {
             before(grammarAccess.getBinaryExpressionAccess().getOperatorAssignment_2()); 
            // InternalPNPL_variability.g:945:2: ( rule__BinaryExpression__OperatorAssignment_2 )
            // InternalPNPL_variability.g:945:3: rule__BinaryExpression__OperatorAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__BinaryExpression__OperatorAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getBinaryExpressionAccess().getOperatorAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__2__Impl"


    // $ANTLR start "rule__BinaryExpression__Group__3"
    // InternalPNPL_variability.g:953:1: rule__BinaryExpression__Group__3 : rule__BinaryExpression__Group__3__Impl rule__BinaryExpression__Group__4 ;
    public final void rule__BinaryExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:957:1: ( rule__BinaryExpression__Group__3__Impl rule__BinaryExpression__Group__4 )
            // InternalPNPL_variability.g:958:2: rule__BinaryExpression__Group__3__Impl rule__BinaryExpression__Group__4
            {
            pushFollow(FOLLOW_14);
            rule__BinaryExpression__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BinaryExpression__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__3"


    // $ANTLR start "rule__BinaryExpression__Group__3__Impl"
    // InternalPNPL_variability.g:965:1: rule__BinaryExpression__Group__3__Impl : ( ( rule__BinaryExpression__RightAssignment_3 ) ) ;
    public final void rule__BinaryExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:969:1: ( ( ( rule__BinaryExpression__RightAssignment_3 ) ) )
            // InternalPNPL_variability.g:970:1: ( ( rule__BinaryExpression__RightAssignment_3 ) )
            {
            // InternalPNPL_variability.g:970:1: ( ( rule__BinaryExpression__RightAssignment_3 ) )
            // InternalPNPL_variability.g:971:2: ( rule__BinaryExpression__RightAssignment_3 )
            {
             before(grammarAccess.getBinaryExpressionAccess().getRightAssignment_3()); 
            // InternalPNPL_variability.g:972:2: ( rule__BinaryExpression__RightAssignment_3 )
            // InternalPNPL_variability.g:972:3: rule__BinaryExpression__RightAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__BinaryExpression__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getBinaryExpressionAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__3__Impl"


    // $ANTLR start "rule__BinaryExpression__Group__4"
    // InternalPNPL_variability.g:980:1: rule__BinaryExpression__Group__4 : rule__BinaryExpression__Group__4__Impl ;
    public final void rule__BinaryExpression__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:984:1: ( rule__BinaryExpression__Group__4__Impl )
            // InternalPNPL_variability.g:985:2: rule__BinaryExpression__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__BinaryExpression__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__4"


    // $ANTLR start "rule__BinaryExpression__Group__4__Impl"
    // InternalPNPL_variability.g:991:1: rule__BinaryExpression__Group__4__Impl : ( ')' ) ;
    public final void rule__BinaryExpression__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:995:1: ( ( ')' ) )
            // InternalPNPL_variability.g:996:1: ( ')' )
            {
            // InternalPNPL_variability.g:996:1: ( ')' )
            // InternalPNPL_variability.g:997:2: ')'
            {
             before(grammarAccess.getBinaryExpressionAccess().getRightParenthesisKeyword_4()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getBinaryExpressionAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__Group__4__Impl"


    // $ANTLR start "rule__Variability__PetrinetAssignment_1"
    // InternalPNPL_variability.g:1007:1: rule__Variability__PetrinetAssignment_1 : ( ruleFileURI ) ;
    public final void rule__Variability__PetrinetAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1011:1: ( ( ruleFileURI ) )
            // InternalPNPL_variability.g:1012:2: ( ruleFileURI )
            {
            // InternalPNPL_variability.g:1012:2: ( ruleFileURI )
            // InternalPNPL_variability.g:1013:3: ruleFileURI
            {
             before(grammarAccess.getVariabilityAccess().getPetrinetFileURIParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleFileURI();

            state._fsp--;

             after(grammarAccess.getVariabilityAccess().getPetrinetFileURIParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__PetrinetAssignment_1"


    // $ANTLR start "rule__Variability__FeaturemodelAssignment_3"
    // InternalPNPL_variability.g:1022:1: rule__Variability__FeaturemodelAssignment_3 : ( ruleFileURI ) ;
    public final void rule__Variability__FeaturemodelAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1026:1: ( ( ruleFileURI ) )
            // InternalPNPL_variability.g:1027:2: ( ruleFileURI )
            {
            // InternalPNPL_variability.g:1027:2: ( ruleFileURI )
            // InternalPNPL_variability.g:1028:3: ruleFileURI
            {
             before(grammarAccess.getVariabilityAccess().getFeaturemodelFileURIParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleFileURI();

            state._fsp--;

             after(grammarAccess.getVariabilityAccess().getFeaturemodelFileURIParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__FeaturemodelAssignment_3"


    // $ANTLR start "rule__Variability__PresenceconditionAssignment_4_0"
    // InternalPNPL_variability.g:1037:1: rule__Variability__PresenceconditionAssignment_4_0 : ( rulePresenceCondition ) ;
    public final void rule__Variability__PresenceconditionAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1041:1: ( ( rulePresenceCondition ) )
            // InternalPNPL_variability.g:1042:2: ( rulePresenceCondition )
            {
            // InternalPNPL_variability.g:1042:2: ( rulePresenceCondition )
            // InternalPNPL_variability.g:1043:3: rulePresenceCondition
            {
             before(grammarAccess.getVariabilityAccess().getPresenceconditionPresenceConditionParserRuleCall_4_0_0()); 
            pushFollow(FOLLOW_2);
            rulePresenceCondition();

            state._fsp--;

             after(grammarAccess.getVariabilityAccess().getPresenceconditionPresenceConditionParserRuleCall_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variability__PresenceconditionAssignment_4_0"


    // $ANTLR start "rule__FileURI__ImportURIAssignment_1"
    // InternalPNPL_variability.g:1052:1: rule__FileURI__ImportURIAssignment_1 : ( RULE_STRING ) ;
    public final void rule__FileURI__ImportURIAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1056:1: ( ( RULE_STRING ) )
            // InternalPNPL_variability.g:1057:2: ( RULE_STRING )
            {
            // InternalPNPL_variability.g:1057:2: ( RULE_STRING )
            // InternalPNPL_variability.g:1058:3: RULE_STRING
            {
             before(grammarAccess.getFileURIAccess().getImportURISTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getFileURIAccess().getImportURISTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FileURI__ImportURIAssignment_1"


    // $ANTLR start "rule__PresenceCondition__ElementsAssignment_2"
    // InternalPNPL_variability.g:1067:1: rule__PresenceCondition__ElementsAssignment_2 : ( ( ruleEString ) ) ;
    public final void rule__PresenceCondition__ElementsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1071:1: ( ( ( ruleEString ) ) )
            // InternalPNPL_variability.g:1072:2: ( ( ruleEString ) )
            {
            // InternalPNPL_variability.g:1072:2: ( ( ruleEString ) )
            // InternalPNPL_variability.g:1073:3: ( ruleEString )
            {
             before(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_2_0()); 
            // InternalPNPL_variability.g:1074:3: ( ruleEString )
            // InternalPNPL_variability.g:1075:4: ruleEString
            {
             before(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_2_0_1()); 

            }

             after(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__ElementsAssignment_2"


    // $ANTLR start "rule__PresenceCondition__ElementsAssignment_3_1"
    // InternalPNPL_variability.g:1086:1: rule__PresenceCondition__ElementsAssignment_3_1 : ( ( ruleEString ) ) ;
    public final void rule__PresenceCondition__ElementsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1090:1: ( ( ( ruleEString ) ) )
            // InternalPNPL_variability.g:1091:2: ( ( ruleEString ) )
            {
            // InternalPNPL_variability.g:1091:2: ( ( ruleEString ) )
            // InternalPNPL_variability.g:1092:3: ( ruleEString )
            {
             before(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_3_1_0()); 
            // InternalPNPL_variability.g:1093:3: ( ruleEString )
            // InternalPNPL_variability.g:1094:4: ruleEString
            {
             before(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_3_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getPresenceConditionAccess().getElementsEObjectEStringParserRuleCall_3_1_0_1()); 

            }

             after(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__ElementsAssignment_3_1"


    // $ANTLR start "rule__PresenceCondition__ExpressionAssignment_5"
    // InternalPNPL_variability.g:1105:1: rule__PresenceCondition__ExpressionAssignment_5 : ( ruleExpression ) ;
    public final void rule__PresenceCondition__ExpressionAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1109:1: ( ( ruleExpression ) )
            // InternalPNPL_variability.g:1110:2: ( ruleExpression )
            {
            // InternalPNPL_variability.g:1110:2: ( ruleExpression )
            // InternalPNPL_variability.g:1111:3: ruleExpression
            {
             before(grammarAccess.getPresenceConditionAccess().getExpressionExpressionParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getPresenceConditionAccess().getExpressionExpressionParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PresenceCondition__ExpressionAssignment_5"


    // $ANTLR start "rule__Feature__FeatureAssignment"
    // InternalPNPL_variability.g:1120:1: rule__Feature__FeatureAssignment : ( ruleEString ) ;
    public final void rule__Feature__FeatureAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1124:1: ( ( ruleEString ) )
            // InternalPNPL_variability.g:1125:2: ( ruleEString )
            {
            // InternalPNPL_variability.g:1125:2: ( ruleEString )
            // InternalPNPL_variability.g:1126:3: ruleEString
            {
             before(grammarAccess.getFeatureAccess().getFeatureEStringParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getFeatureAccess().getFeatureEStringParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__FeatureAssignment"


    // $ANTLR start "rule__UnaryExpression__OperatorAssignment_0"
    // InternalPNPL_variability.g:1135:1: rule__UnaryExpression__OperatorAssignment_0 : ( ruleUnaryOperator ) ;
    public final void rule__UnaryExpression__OperatorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1139:1: ( ( ruleUnaryOperator ) )
            // InternalPNPL_variability.g:1140:2: ( ruleUnaryOperator )
            {
            // InternalPNPL_variability.g:1140:2: ( ruleUnaryOperator )
            // InternalPNPL_variability.g:1141:3: ruleUnaryOperator
            {
             before(grammarAccess.getUnaryExpressionAccess().getOperatorUnaryOperatorEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleUnaryOperator();

            state._fsp--;

             after(grammarAccess.getUnaryExpressionAccess().getOperatorUnaryOperatorEnumRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__OperatorAssignment_0"


    // $ANTLR start "rule__UnaryExpression__RightAssignment_1"
    // InternalPNPL_variability.g:1150:1: rule__UnaryExpression__RightAssignment_1 : ( ruleExpression ) ;
    public final void rule__UnaryExpression__RightAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1154:1: ( ( ruleExpression ) )
            // InternalPNPL_variability.g:1155:2: ( ruleExpression )
            {
            // InternalPNPL_variability.g:1155:2: ( ruleExpression )
            // InternalPNPL_variability.g:1156:3: ruleExpression
            {
             before(grammarAccess.getUnaryExpressionAccess().getRightExpressionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getUnaryExpressionAccess().getRightExpressionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__RightAssignment_1"


    // $ANTLR start "rule__BinaryExpression__LeftAssignment_1"
    // InternalPNPL_variability.g:1165:1: rule__BinaryExpression__LeftAssignment_1 : ( ruleExpression ) ;
    public final void rule__BinaryExpression__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1169:1: ( ( ruleExpression ) )
            // InternalPNPL_variability.g:1170:2: ( ruleExpression )
            {
            // InternalPNPL_variability.g:1170:2: ( ruleExpression )
            // InternalPNPL_variability.g:1171:3: ruleExpression
            {
             before(grammarAccess.getBinaryExpressionAccess().getLeftExpressionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getBinaryExpressionAccess().getLeftExpressionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__LeftAssignment_1"


    // $ANTLR start "rule__BinaryExpression__OperatorAssignment_2"
    // InternalPNPL_variability.g:1180:1: rule__BinaryExpression__OperatorAssignment_2 : ( ruleBinaryOperator ) ;
    public final void rule__BinaryExpression__OperatorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1184:1: ( ( ruleBinaryOperator ) )
            // InternalPNPL_variability.g:1185:2: ( ruleBinaryOperator )
            {
            // InternalPNPL_variability.g:1185:2: ( ruleBinaryOperator )
            // InternalPNPL_variability.g:1186:3: ruleBinaryOperator
            {
             before(grammarAccess.getBinaryExpressionAccess().getOperatorBinaryOperatorEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleBinaryOperator();

            state._fsp--;

             after(grammarAccess.getBinaryExpressionAccess().getOperatorBinaryOperatorEnumRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__OperatorAssignment_2"


    // $ANTLR start "rule__BinaryExpression__RightAssignment_3"
    // InternalPNPL_variability.g:1195:1: rule__BinaryExpression__RightAssignment_3 : ( ruleExpression ) ;
    public final void rule__BinaryExpression__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPNPL_variability.g:1199:1: ( ( ruleExpression ) )
            // InternalPNPL_variability.g:1200:2: ( ruleExpression )
            {
            // InternalPNPL_variability.g:1200:2: ( ruleExpression )
            // InternalPNPL_variability.g:1201:3: ruleExpression
            {
             before(grammarAccess.getBinaryExpressionAccess().getRightExpressionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getBinaryExpressionAccess().getRightExpressionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BinaryExpression__RightAssignment_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400830L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000007000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800000L});

}