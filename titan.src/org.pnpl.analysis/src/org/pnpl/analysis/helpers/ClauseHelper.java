package org.pnpl.analysis.helpers;

import java.util.List;

public class ClauseHelper {
	private static final String oper_AND = "and";
	private static final String oper_OR = "or";
	private static final String oper_IMPLIES = "implies";
	private static final String oper_NOT = "not";
	
	public static boolean isBlank(String str) {
		return (str == null || str.trim().isEmpty());
	}
		
	public static String conjunction(String cond1, String cond2) {
		return concatenate(cond1, cond2, oper_AND);		
	}
	
	public static String disjunction(String cond1, String cond2) {
		return concatenate(cond1, cond2, oper_OR);		
	}
	
	public static String implication(String cond1, String cond2) {
		return concatenate(cond1, cond2, oper_IMPLIES);		
	}
	
	public static String negation(String cond) {
		if (!isBlank(cond)) 
			return "(" + oper_NOT + " " + cond + ")";
		else
			return cond;	
	}
	
	public static String parenthesis(String cond) {
		if (!isBlank(cond)) 
			return "(" + cond + ")";
		else
			return cond;	
	}
	
	public static String disjunctionOfConjunction(List<String> list) {
		int numElem = 0;
		String[] array = new String[list.size()];
		
		for (String str : list) {
			for (int i = 0; i < list.size(); i++) {
				if (i == numElem)
					array[i] =  concatenate(array[i],str,oper_AND);	
				else
					array[i] = concatenate(array[i],negation(str),oper_AND);			
			}
			numElem++;
		}
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			result = concatenate(result,parenthesis(array[i]),oper_OR);
		}
		return parenthesis(result);
	}
	
	public static String disjunctionOfConjunctionOneNegative(List<String> list) {
		int numElem = 0;
		String[] array = new String[list.size()];
		
		for (String str : list) {
			for (int i = 0; i < list.size(); i++) {
				if (i != numElem)
					array[i] =  concatenate(array[i],str,oper_AND);	
				else
					array[i] = concatenate(array[i],negation(str),oper_AND);			
			}
			numElem++;
		}
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			result = concatenate(result,parenthesis(array[i]),oper_OR);
		}
		return parenthesis(result);
	}
	
	private static String concatenate(String cond1, String cond2, String oper) {
		String result = "";
		if (!isBlank(cond1)) {
			if (!isBlank(cond2)) {
				result = cond1 + " " + oper + " " + cond2;
			} else {
				result = cond1;				
			}
		} else {
			if (!isBlank(cond2)) {
				result = cond2;
			}
		}
		return result;		
	}
}
