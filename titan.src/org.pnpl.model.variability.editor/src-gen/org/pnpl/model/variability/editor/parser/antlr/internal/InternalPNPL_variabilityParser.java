package org.pnpl.model.variability.editor.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.pnpl.model.variability.editor.services.PNPL_variabilityGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPNPL_variabilityParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'pn'", "'fm'", "';'", "'PC'", "'for'", "','", "'='", "'('", "')'", "'not'", "'and'", "'or'", "'implies'"
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

        public InternalPNPL_variabilityParser(TokenStream input, PNPL_variabilityGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Variability";
       	}

       	@Override
       	protected PNPL_variabilityGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleVariability"
    // InternalPNPL_variability.g:65:1: entryRuleVariability returns [EObject current=null] : iv_ruleVariability= ruleVariability EOF ;
    public final EObject entryRuleVariability() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariability = null;


        try {
            // InternalPNPL_variability.g:65:52: (iv_ruleVariability= ruleVariability EOF )
            // InternalPNPL_variability.g:66:2: iv_ruleVariability= ruleVariability EOF
            {
             newCompositeNode(grammarAccess.getVariabilityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariability=ruleVariability();

            state._fsp--;

             current =iv_ruleVariability; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariability"


    // $ANTLR start "ruleVariability"
    // InternalPNPL_variability.g:72:1: ruleVariability returns [EObject current=null] : (otherlv_0= 'pn' ( (lv_petrinet_1_0= ruleFileURI ) ) otherlv_2= 'fm' ( (lv_featuremodel_3_0= ruleFileURI ) ) ( ( (lv_presencecondition_4_0= rulePresenceCondition ) ) otherlv_5= ';' )* ) ;
    public final EObject ruleVariability() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_petrinet_1_0 = null;

        EObject lv_featuremodel_3_0 = null;

        EObject lv_presencecondition_4_0 = null;



        	enterRule();

        try {
            // InternalPNPL_variability.g:78:2: ( (otherlv_0= 'pn' ( (lv_petrinet_1_0= ruleFileURI ) ) otherlv_2= 'fm' ( (lv_featuremodel_3_0= ruleFileURI ) ) ( ( (lv_presencecondition_4_0= rulePresenceCondition ) ) otherlv_5= ';' )* ) )
            // InternalPNPL_variability.g:79:2: (otherlv_0= 'pn' ( (lv_petrinet_1_0= ruleFileURI ) ) otherlv_2= 'fm' ( (lv_featuremodel_3_0= ruleFileURI ) ) ( ( (lv_presencecondition_4_0= rulePresenceCondition ) ) otherlv_5= ';' )* )
            {
            // InternalPNPL_variability.g:79:2: (otherlv_0= 'pn' ( (lv_petrinet_1_0= ruleFileURI ) ) otherlv_2= 'fm' ( (lv_featuremodel_3_0= ruleFileURI ) ) ( ( (lv_presencecondition_4_0= rulePresenceCondition ) ) otherlv_5= ';' )* )
            // InternalPNPL_variability.g:80:3: otherlv_0= 'pn' ( (lv_petrinet_1_0= ruleFileURI ) ) otherlv_2= 'fm' ( (lv_featuremodel_3_0= ruleFileURI ) ) ( ( (lv_presencecondition_4_0= rulePresenceCondition ) ) otherlv_5= ';' )*
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getVariabilityAccess().getPnKeyword_0());
            		
            // InternalPNPL_variability.g:84:3: ( (lv_petrinet_1_0= ruleFileURI ) )
            // InternalPNPL_variability.g:85:4: (lv_petrinet_1_0= ruleFileURI )
            {
            // InternalPNPL_variability.g:85:4: (lv_petrinet_1_0= ruleFileURI )
            // InternalPNPL_variability.g:86:5: lv_petrinet_1_0= ruleFileURI
            {

            					newCompositeNode(grammarAccess.getVariabilityAccess().getPetrinetFileURIParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_4);
            lv_petrinet_1_0=ruleFileURI();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariabilityRule());
            					}
            					set(
            						current,
            						"petrinet",
            						lv_petrinet_1_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.FileURI");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getVariabilityAccess().getFmKeyword_2());
            		
            // InternalPNPL_variability.g:107:3: ( (lv_featuremodel_3_0= ruleFileURI ) )
            // InternalPNPL_variability.g:108:4: (lv_featuremodel_3_0= ruleFileURI )
            {
            // InternalPNPL_variability.g:108:4: (lv_featuremodel_3_0= ruleFileURI )
            // InternalPNPL_variability.g:109:5: lv_featuremodel_3_0= ruleFileURI
            {

            					newCompositeNode(grammarAccess.getVariabilityAccess().getFeaturemodelFileURIParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_5);
            lv_featuremodel_3_0=ruleFileURI();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariabilityRule());
            					}
            					set(
            						current,
            						"featuremodel",
            						lv_featuremodel_3_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.FileURI");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPNPL_variability.g:126:3: ( ( (lv_presencecondition_4_0= rulePresenceCondition ) ) otherlv_5= ';' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==14) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalPNPL_variability.g:127:4: ( (lv_presencecondition_4_0= rulePresenceCondition ) ) otherlv_5= ';'
            	    {
            	    // InternalPNPL_variability.g:127:4: ( (lv_presencecondition_4_0= rulePresenceCondition ) )
            	    // InternalPNPL_variability.g:128:5: (lv_presencecondition_4_0= rulePresenceCondition )
            	    {
            	    // InternalPNPL_variability.g:128:5: (lv_presencecondition_4_0= rulePresenceCondition )
            	    // InternalPNPL_variability.g:129:6: lv_presencecondition_4_0= rulePresenceCondition
            	    {

            	    						newCompositeNode(grammarAccess.getVariabilityAccess().getPresenceconditionPresenceConditionParserRuleCall_4_0_0());
            	    					
            	    pushFollow(FOLLOW_6);
            	    lv_presencecondition_4_0=rulePresenceCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getVariabilityRule());
            	    						}
            	    						add(
            	    							current,
            	    							"presencecondition",
            	    							lv_presencecondition_4_0,
            	    							"org.pnpl.model.variability.editor.PNPL_variability.PresenceCondition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_5=(Token)match(input,13,FOLLOW_5); 

            	    				newLeafNode(otherlv_5, grammarAccess.getVariabilityAccess().getSemicolonKeyword_4_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariability"


    // $ANTLR start "entryRuleFileURI"
    // InternalPNPL_variability.g:155:1: entryRuleFileURI returns [EObject current=null] : iv_ruleFileURI= ruleFileURI EOF ;
    public final EObject entryRuleFileURI() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFileURI = null;


        try {
            // InternalPNPL_variability.g:155:48: (iv_ruleFileURI= ruleFileURI EOF )
            // InternalPNPL_variability.g:156:2: iv_ruleFileURI= ruleFileURI EOF
            {
             newCompositeNode(grammarAccess.getFileURIRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFileURI=ruleFileURI();

            state._fsp--;

             current =iv_ruleFileURI; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFileURI"


    // $ANTLR start "ruleFileURI"
    // InternalPNPL_variability.g:162:1: ruleFileURI returns [EObject current=null] : ( () ( (lv_importURI_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleFileURI() throws RecognitionException {
        EObject current = null;

        Token lv_importURI_1_0=null;


        	enterRule();

        try {
            // InternalPNPL_variability.g:168:2: ( ( () ( (lv_importURI_1_0= RULE_STRING ) ) ) )
            // InternalPNPL_variability.g:169:2: ( () ( (lv_importURI_1_0= RULE_STRING ) ) )
            {
            // InternalPNPL_variability.g:169:2: ( () ( (lv_importURI_1_0= RULE_STRING ) ) )
            // InternalPNPL_variability.g:170:3: () ( (lv_importURI_1_0= RULE_STRING ) )
            {
            // InternalPNPL_variability.g:170:3: ()
            // InternalPNPL_variability.g:171:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFileURIAccess().getFileURIAction_0(),
            					current);
            			

            }

            // InternalPNPL_variability.g:177:3: ( (lv_importURI_1_0= RULE_STRING ) )
            // InternalPNPL_variability.g:178:4: (lv_importURI_1_0= RULE_STRING )
            {
            // InternalPNPL_variability.g:178:4: (lv_importURI_1_0= RULE_STRING )
            // InternalPNPL_variability.g:179:5: lv_importURI_1_0= RULE_STRING
            {
            lv_importURI_1_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_importURI_1_0, grammarAccess.getFileURIAccess().getImportURISTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFileURIRule());
            					}
            					setWithLastConsumed(
            						current,
            						"importURI",
            						lv_importURI_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFileURI"


    // $ANTLR start "entryRuleExpression"
    // InternalPNPL_variability.g:199:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalPNPL_variability.g:199:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalPNPL_variability.g:200:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalPNPL_variability.g:206:1: ruleExpression returns [EObject current=null] : (this_Feature_0= ruleFeature | this_UnaryExpression_1= ruleUnaryExpression | this_BinaryExpression_2= ruleBinaryExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Feature_0 = null;

        EObject this_UnaryExpression_1 = null;

        EObject this_BinaryExpression_2 = null;



        	enterRule();

        try {
            // InternalPNPL_variability.g:212:2: ( (this_Feature_0= ruleFeature | this_UnaryExpression_1= ruleUnaryExpression | this_BinaryExpression_2= ruleBinaryExpression ) )
            // InternalPNPL_variability.g:213:2: (this_Feature_0= ruleFeature | this_UnaryExpression_1= ruleUnaryExpression | this_BinaryExpression_2= ruleBinaryExpression )
            {
            // InternalPNPL_variability.g:213:2: (this_Feature_0= ruleFeature | this_UnaryExpression_1= ruleUnaryExpression | this_BinaryExpression_2= ruleBinaryExpression )
            int alt2=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_ID:
                {
                alt2=1;
                }
                break;
            case 20:
                {
                alt2=2;
                }
                break;
            case 18:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalPNPL_variability.g:214:3: this_Feature_0= ruleFeature
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getFeatureParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Feature_0=ruleFeature();

                    state._fsp--;


                    			current = this_Feature_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPNPL_variability.g:223:3: this_UnaryExpression_1= ruleUnaryExpression
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getUnaryExpressionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_UnaryExpression_1=ruleUnaryExpression();

                    state._fsp--;


                    			current = this_UnaryExpression_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalPNPL_variability.g:232:3: this_BinaryExpression_2= ruleBinaryExpression
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getBinaryExpressionParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_BinaryExpression_2=ruleBinaryExpression();

                    state._fsp--;


                    			current = this_BinaryExpression_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRulePresenceCondition"
    // InternalPNPL_variability.g:244:1: entryRulePresenceCondition returns [EObject current=null] : iv_rulePresenceCondition= rulePresenceCondition EOF ;
    public final EObject entryRulePresenceCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePresenceCondition = null;


        try {
            // InternalPNPL_variability.g:244:58: (iv_rulePresenceCondition= rulePresenceCondition EOF )
            // InternalPNPL_variability.g:245:2: iv_rulePresenceCondition= rulePresenceCondition EOF
            {
             newCompositeNode(grammarAccess.getPresenceConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePresenceCondition=rulePresenceCondition();

            state._fsp--;

             current =iv_rulePresenceCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePresenceCondition"


    // $ANTLR start "rulePresenceCondition"
    // InternalPNPL_variability.g:251:1: rulePresenceCondition returns [EObject current=null] : (otherlv_0= 'PC' otherlv_1= 'for' ( ( ruleEString ) ) (otherlv_3= ',' ( ( ruleEString ) ) )* otherlv_5= '=' ( (lv_expression_6_0= ruleExpression ) ) ) ;
    public final EObject rulePresenceCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_expression_6_0 = null;



        	enterRule();

        try {
            // InternalPNPL_variability.g:257:2: ( (otherlv_0= 'PC' otherlv_1= 'for' ( ( ruleEString ) ) (otherlv_3= ',' ( ( ruleEString ) ) )* otherlv_5= '=' ( (lv_expression_6_0= ruleExpression ) ) ) )
            // InternalPNPL_variability.g:258:2: (otherlv_0= 'PC' otherlv_1= 'for' ( ( ruleEString ) ) (otherlv_3= ',' ( ( ruleEString ) ) )* otherlv_5= '=' ( (lv_expression_6_0= ruleExpression ) ) )
            {
            // InternalPNPL_variability.g:258:2: (otherlv_0= 'PC' otherlv_1= 'for' ( ( ruleEString ) ) (otherlv_3= ',' ( ( ruleEString ) ) )* otherlv_5= '=' ( (lv_expression_6_0= ruleExpression ) ) )
            // InternalPNPL_variability.g:259:3: otherlv_0= 'PC' otherlv_1= 'for' ( ( ruleEString ) ) (otherlv_3= ',' ( ( ruleEString ) ) )* otherlv_5= '=' ( (lv_expression_6_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,14,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getPresenceConditionAccess().getPCKeyword_0());
            		
            otherlv_1=(Token)match(input,15,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getPresenceConditionAccess().getForKeyword_1());
            		
            // InternalPNPL_variability.g:267:3: ( ( ruleEString ) )
            // InternalPNPL_variability.g:268:4: ( ruleEString )
            {
            // InternalPNPL_variability.g:268:4: ( ruleEString )
            // InternalPNPL_variability.g:269:5: ruleEString
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPresenceConditionRule());
            					}
            				

            					newCompositeNode(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_2_0());
            				
            pushFollow(FOLLOW_9);
            ruleEString();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPNPL_variability.g:283:3: (otherlv_3= ',' ( ( ruleEString ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalPNPL_variability.g:284:4: otherlv_3= ',' ( ( ruleEString ) )
            	    {
            	    otherlv_3=(Token)match(input,16,FOLLOW_8); 

            	    				newLeafNode(otherlv_3, grammarAccess.getPresenceConditionAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalPNPL_variability.g:288:4: ( ( ruleEString ) )
            	    // InternalPNPL_variability.g:289:5: ( ruleEString )
            	    {
            	    // InternalPNPL_variability.g:289:5: ( ruleEString )
            	    // InternalPNPL_variability.g:290:6: ruleEString
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getPresenceConditionRule());
            	    						}
            	    					

            	    						newCompositeNode(grammarAccess.getPresenceConditionAccess().getElementsEObjectCrossReference_3_1_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    ruleEString();

            	    state._fsp--;


            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_5=(Token)match(input,17,FOLLOW_10); 

            			newLeafNode(otherlv_5, grammarAccess.getPresenceConditionAccess().getEqualsSignKeyword_4());
            		
            // InternalPNPL_variability.g:309:3: ( (lv_expression_6_0= ruleExpression ) )
            // InternalPNPL_variability.g:310:4: (lv_expression_6_0= ruleExpression )
            {
            // InternalPNPL_variability.g:310:4: (lv_expression_6_0= ruleExpression )
            // InternalPNPL_variability.g:311:5: lv_expression_6_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getPresenceConditionAccess().getExpressionExpressionParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_2);
            lv_expression_6_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPresenceConditionRule());
            					}
            					set(
            						current,
            						"expression",
            						lv_expression_6_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePresenceCondition"


    // $ANTLR start "entryRuleFeature"
    // InternalPNPL_variability.g:332:1: entryRuleFeature returns [EObject current=null] : iv_ruleFeature= ruleFeature EOF ;
    public final EObject entryRuleFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature = null;


        try {
            // InternalPNPL_variability.g:332:48: (iv_ruleFeature= ruleFeature EOF )
            // InternalPNPL_variability.g:333:2: iv_ruleFeature= ruleFeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeature=ruleFeature();

            state._fsp--;

             current =iv_ruleFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalPNPL_variability.g:339:1: ruleFeature returns [EObject current=null] : ( (lv_feature_0_0= ruleEString ) ) ;
    public final EObject ruleFeature() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_feature_0_0 = null;



        	enterRule();

        try {
            // InternalPNPL_variability.g:345:2: ( ( (lv_feature_0_0= ruleEString ) ) )
            // InternalPNPL_variability.g:346:2: ( (lv_feature_0_0= ruleEString ) )
            {
            // InternalPNPL_variability.g:346:2: ( (lv_feature_0_0= ruleEString ) )
            // InternalPNPL_variability.g:347:3: (lv_feature_0_0= ruleEString )
            {
            // InternalPNPL_variability.g:347:3: (lv_feature_0_0= ruleEString )
            // InternalPNPL_variability.g:348:4: lv_feature_0_0= ruleEString
            {

            				newCompositeNode(grammarAccess.getFeatureAccess().getFeatureEStringParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_feature_0_0=ruleEString();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getFeatureRule());
            				}
            				set(
            					current,
            					"feature",
            					lv_feature_0_0,
            					"org.pnpl.model.variability.editor.PNPL_variability.EString");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleUnaryExpression"
    // InternalPNPL_variability.g:368:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // InternalPNPL_variability.g:368:56: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // InternalPNPL_variability.g:369:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
             newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;

             current =iv_ruleUnaryExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnaryExpression"


    // $ANTLR start "ruleUnaryExpression"
    // InternalPNPL_variability.g:375:1: ruleUnaryExpression returns [EObject current=null] : ( ( (lv_operator_0_0= ruleUnaryOperator ) ) ( (lv_right_1_0= ruleExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Enumerator lv_operator_0_0 = null;

        EObject lv_right_1_0 = null;



        	enterRule();

        try {
            // InternalPNPL_variability.g:381:2: ( ( ( (lv_operator_0_0= ruleUnaryOperator ) ) ( (lv_right_1_0= ruleExpression ) ) ) )
            // InternalPNPL_variability.g:382:2: ( ( (lv_operator_0_0= ruleUnaryOperator ) ) ( (lv_right_1_0= ruleExpression ) ) )
            {
            // InternalPNPL_variability.g:382:2: ( ( (lv_operator_0_0= ruleUnaryOperator ) ) ( (lv_right_1_0= ruleExpression ) ) )
            // InternalPNPL_variability.g:383:3: ( (lv_operator_0_0= ruleUnaryOperator ) ) ( (lv_right_1_0= ruleExpression ) )
            {
            // InternalPNPL_variability.g:383:3: ( (lv_operator_0_0= ruleUnaryOperator ) )
            // InternalPNPL_variability.g:384:4: (lv_operator_0_0= ruleUnaryOperator )
            {
            // InternalPNPL_variability.g:384:4: (lv_operator_0_0= ruleUnaryOperator )
            // InternalPNPL_variability.g:385:5: lv_operator_0_0= ruleUnaryOperator
            {

            					newCompositeNode(grammarAccess.getUnaryExpressionAccess().getOperatorUnaryOperatorEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_10);
            lv_operator_0_0=ruleUnaryOperator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
            					}
            					set(
            						current,
            						"operator",
            						lv_operator_0_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.UnaryOperator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPNPL_variability.g:402:3: ( (lv_right_1_0= ruleExpression ) )
            // InternalPNPL_variability.g:403:4: (lv_right_1_0= ruleExpression )
            {
            // InternalPNPL_variability.g:403:4: (lv_right_1_0= ruleExpression )
            // InternalPNPL_variability.g:404:5: lv_right_1_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getUnaryExpressionAccess().getRightExpressionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_right_1_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_1_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryExpression"


    // $ANTLR start "entryRuleBinaryExpression"
    // InternalPNPL_variability.g:425:1: entryRuleBinaryExpression returns [EObject current=null] : iv_ruleBinaryExpression= ruleBinaryExpression EOF ;
    public final EObject entryRuleBinaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBinaryExpression = null;


        try {
            // InternalPNPL_variability.g:425:57: (iv_ruleBinaryExpression= ruleBinaryExpression EOF )
            // InternalPNPL_variability.g:426:2: iv_ruleBinaryExpression= ruleBinaryExpression EOF
            {
             newCompositeNode(grammarAccess.getBinaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBinaryExpression=ruleBinaryExpression();

            state._fsp--;

             current =iv_ruleBinaryExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBinaryExpression"


    // $ANTLR start "ruleBinaryExpression"
    // InternalPNPL_variability.g:432:1: ruleBinaryExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_left_1_0= ruleExpression ) ) ( (lv_operator_2_0= ruleBinaryOperator ) ) ( (lv_right_3_0= ruleExpression ) ) otherlv_4= ')' ) ;
    public final EObject ruleBinaryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_4=null;
        EObject lv_left_1_0 = null;

        Enumerator lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPNPL_variability.g:438:2: ( (otherlv_0= '(' ( (lv_left_1_0= ruleExpression ) ) ( (lv_operator_2_0= ruleBinaryOperator ) ) ( (lv_right_3_0= ruleExpression ) ) otherlv_4= ')' ) )
            // InternalPNPL_variability.g:439:2: (otherlv_0= '(' ( (lv_left_1_0= ruleExpression ) ) ( (lv_operator_2_0= ruleBinaryOperator ) ) ( (lv_right_3_0= ruleExpression ) ) otherlv_4= ')' )
            {
            // InternalPNPL_variability.g:439:2: (otherlv_0= '(' ( (lv_left_1_0= ruleExpression ) ) ( (lv_operator_2_0= ruleBinaryOperator ) ) ( (lv_right_3_0= ruleExpression ) ) otherlv_4= ')' )
            // InternalPNPL_variability.g:440:3: otherlv_0= '(' ( (lv_left_1_0= ruleExpression ) ) ( (lv_operator_2_0= ruleBinaryOperator ) ) ( (lv_right_3_0= ruleExpression ) ) otherlv_4= ')'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getBinaryExpressionAccess().getLeftParenthesisKeyword_0());
            		
            // InternalPNPL_variability.g:444:3: ( (lv_left_1_0= ruleExpression ) )
            // InternalPNPL_variability.g:445:4: (lv_left_1_0= ruleExpression )
            {
            // InternalPNPL_variability.g:445:4: (lv_left_1_0= ruleExpression )
            // InternalPNPL_variability.g:446:5: lv_left_1_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getBinaryExpressionAccess().getLeftExpressionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_11);
            lv_left_1_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBinaryExpressionRule());
            					}
            					set(
            						current,
            						"left",
            						lv_left_1_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPNPL_variability.g:463:3: ( (lv_operator_2_0= ruleBinaryOperator ) )
            // InternalPNPL_variability.g:464:4: (lv_operator_2_0= ruleBinaryOperator )
            {
            // InternalPNPL_variability.g:464:4: (lv_operator_2_0= ruleBinaryOperator )
            // InternalPNPL_variability.g:465:5: lv_operator_2_0= ruleBinaryOperator
            {

            					newCompositeNode(grammarAccess.getBinaryExpressionAccess().getOperatorBinaryOperatorEnumRuleCall_2_0());
            				
            pushFollow(FOLLOW_10);
            lv_operator_2_0=ruleBinaryOperator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBinaryExpressionRule());
            					}
            					set(
            						current,
            						"operator",
            						lv_operator_2_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.BinaryOperator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPNPL_variability.g:482:3: ( (lv_right_3_0= ruleExpression ) )
            // InternalPNPL_variability.g:483:4: (lv_right_3_0= ruleExpression )
            {
            // InternalPNPL_variability.g:483:4: (lv_right_3_0= ruleExpression )
            // InternalPNPL_variability.g:484:5: lv_right_3_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getBinaryExpressionAccess().getRightExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_12);
            lv_right_3_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBinaryExpressionRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"org.pnpl.model.variability.editor.PNPL_variability.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getBinaryExpressionAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBinaryExpression"


    // $ANTLR start "entryRuleEString"
    // InternalPNPL_variability.g:509:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // InternalPNPL_variability.g:509:47: (iv_ruleEString= ruleEString EOF )
            // InternalPNPL_variability.g:510:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // InternalPNPL_variability.g:516:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;


        	enterRule();

        try {
            // InternalPNPL_variability.g:522:2: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // InternalPNPL_variability.g:523:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // InternalPNPL_variability.g:523:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_STRING) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalPNPL_variability.g:524:3: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_0);
                    		

                    			newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalPNPL_variability.g:532:3: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_1);
                    		

                    			newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "ruleUnaryOperator"
    // InternalPNPL_variability.g:543:1: ruleUnaryOperator returns [Enumerator current=null] : (enumLiteral_0= 'not' ) ;
    public final Enumerator ruleUnaryOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalPNPL_variability.g:549:2: ( (enumLiteral_0= 'not' ) )
            // InternalPNPL_variability.g:550:2: (enumLiteral_0= 'not' )
            {
            // InternalPNPL_variability.g:550:2: (enumLiteral_0= 'not' )
            // InternalPNPL_variability.g:551:3: enumLiteral_0= 'not'
            {
            enumLiteral_0=(Token)match(input,20,FOLLOW_2); 

            			current = grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryOperator"


    // $ANTLR start "ruleBinaryOperator"
    // InternalPNPL_variability.g:560:1: ruleBinaryOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'and' ) | (enumLiteral_1= 'or' ) | (enumLiteral_2= 'implies' ) ) ;
    public final Enumerator ruleBinaryOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalPNPL_variability.g:566:2: ( ( (enumLiteral_0= 'and' ) | (enumLiteral_1= 'or' ) | (enumLiteral_2= 'implies' ) ) )
            // InternalPNPL_variability.g:567:2: ( (enumLiteral_0= 'and' ) | (enumLiteral_1= 'or' ) | (enumLiteral_2= 'implies' ) )
            {
            // InternalPNPL_variability.g:567:2: ( (enumLiteral_0= 'and' ) | (enumLiteral_1= 'or' ) | (enumLiteral_2= 'implies' ) )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt5=1;
                }
                break;
            case 22:
                {
                alt5=2;
                }
                break;
            case 23:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalPNPL_variability.g:568:3: (enumLiteral_0= 'and' )
                    {
                    // InternalPNPL_variability.g:568:3: (enumLiteral_0= 'and' )
                    // InternalPNPL_variability.g:569:4: enumLiteral_0= 'and'
                    {
                    enumLiteral_0=(Token)match(input,21,FOLLOW_2); 

                    				current = grammarAccess.getBinaryOperatorAccess().getANDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getBinaryOperatorAccess().getANDEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPNPL_variability.g:576:3: (enumLiteral_1= 'or' )
                    {
                    // InternalPNPL_variability.g:576:3: (enumLiteral_1= 'or' )
                    // InternalPNPL_variability.g:577:4: enumLiteral_1= 'or'
                    {
                    enumLiteral_1=(Token)match(input,22,FOLLOW_2); 

                    				current = grammarAccess.getBinaryOperatorAccess().getOREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getBinaryOperatorAccess().getOREnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalPNPL_variability.g:584:3: (enumLiteral_2= 'implies' )
                    {
                    // InternalPNPL_variability.g:584:3: (enumLiteral_2= 'implies' )
                    // InternalPNPL_variability.g:585:4: enumLiteral_2= 'implies'
                    {
                    enumLiteral_2=(Token)match(input,23,FOLLOW_2); 

                    				current = grammarAccess.getBinaryOperatorAccess().getIMPLIESEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getBinaryOperatorAccess().getIMPLIESEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBinaryOperator"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000140030L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000E00000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});

}