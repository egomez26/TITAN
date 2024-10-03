package org.pnpl.analysis.analyzer.helpers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

import org.pnpl.solvers.sat.presenceConditions.Operator;
import org.prop4j.And;
import org.prop4j.Implies;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.Or;

import variability.BinaryExpression;
import variability.BinaryOperator;
import variability.Expression;
import variability.Feature;
import variability.UnaryExpression;
import variability.UnaryOperator;
import variability.VariabilityFactory;

/**
 * Functions to work with Expressions (to represent the presence conditions)
 * @author Elena Gomez-Martinez
 *
 */

public class ExpressionHelper {
	private Deque<Expression> operandStack = new ArrayDeque<>();
	private Deque<Object> operatorStack = new ArrayDeque<>();

	private boolean isOpen = false;
	private boolean isUnary = false;
	
	public int getSizeNestedExpression(Expression expr) {
		int value = 0;

		if(expr != null) {
			if (expr instanceof Feature) 
				value = 1;
			else if (expr instanceof UnaryExpression)
				value = 1 + getSizeNestedExpression(((UnaryExpression) expr).getRight());
			else if (expr instanceof BinaryExpression) {
				BinaryExpression bin = (BinaryExpression) expr;
				value = getSizeNestedExpression(bin.getLeft())  
						+ getSizeNestedExpression(bin.getRight());
			}
		}
		return value;
	}

	// Transform a constraint (as a Node) in the Feature Model into a Expression
	public Expression buildExpression(Node node) {
		if (node.getChildren() == null) {
			Feature f = VariabilityFactory.eINSTANCE.createFeature();
			f.setFeature(node.getContainedFeatures().get(0));
			return (Expression) f;
		} else {
			if (node.getChildren().length == 1) {
				if (node instanceof Not) {
					UnaryExpression una =  VariabilityFactory.eINSTANCE.createUnaryExpression();
					una.setOperator(UnaryOperator.NOT);
					una.setRight(this.buildExpression(node.getChildren()[0]));
					return (Expression) una;
				}
			} else {
				BinaryExpression bin =  VariabilityFactory.eINSTANCE.createBinaryExpression();
				
				if (node instanceof And) {
					bin.setOperator(BinaryOperator.AND);
				} else if (node instanceof Or) {
					bin.setOperator(BinaryOperator.OR);	
				} else if(node instanceof Implies) {
					bin.setOperator(BinaryOperator.IMPLIES);
				}
				
				bin.setLeft(this.buildExpression(node.getChildren()[0]));
				bin.setRight(this.buildExpression(node.getChildren()[1]));
				
				return (Expression) bin;
			}
		}
		return null;		
	}
	
	// Transform a constraint (as a Node) in the Feature Model into a String
	public Expression buildExpression(String condition) {		
		//System.out.println("[pnpl] Loading pc " +condition);
		return getExpression(condition);
	}
	
	// EGM: Be aware of deprecated elements
	private Expression getExpression(String condition) {
		Scanner scanner = new Scanner(condition);
        Stack<Expression> operandStack = new Stack<>();
        int numOpen = 0;

        // Pattern for words (including underscore)
        Pattern wordPattern = Pattern.compile("\\w+");
        // Pattern for parenthesis and other individual characters
        Pattern specialCharPattern = Pattern.compile("[()]");

        while (scanner.hasNext()) {
            if (scanner.hasNext(wordPattern)) {
                String word = scanner.next(wordPattern);
                Expression expr = handleWord(word);
                if (expr != null) {
                    operandStack.push(expr);
                    if (isUnary) {
                        operandStack.push(handleOperator());
                        isUnary = false;
                    }
                }
            } else if (scanner.hasNext(specialCharPattern)) {
                String specialChar = scanner.next(specialCharPattern);
                if (specialChar.equals("(")) {
                    isOpen = true;
                    numOpen++;
                } else if (specialChar.equals(")")) {
                    if (isOpen) {
                        operandStack.push(handleOperator());
                        numOpen--;
                        if (numOpen == 0) {
                            isOpen = false;
                        }
                    }
                }
            } else {
                scanner.next(); // Consume another unexpected token
            }
        }

        scanner.close();
        return operandStack.isEmpty() ? null : operandStack.pop();
	}

	private Expression handleWord(String token) {
		if (isFeature(token)) {    		
			Feature f = VariabilityFactory.eINSTANCE.createFeature();
			f.setFeature(token);
			return (Expression) f;
		}
		else {
			UnaryOperator una = getUnaryOperator(token);
			if (una != null) {
				operatorStack.push(una);
				isUnary = true;
			} else {
				BinaryOperator bin = getBinaryOperator(token);			
				if (bin != null) {
					operatorStack.push(bin);
				}
			} 
		}
		return null;
	}

	private Expression handleOperator() {
		Object o = operatorStack.pop();
		if(o instanceof UnaryOperator) {
			UnaryExpression una =  VariabilityFactory.eINSTANCE.createUnaryExpression();
			una.setOperator((UnaryOperator) o);
			una.setRight(operandStack.pop());
			return (Expression) una;
		} else if (o instanceof BinaryOperator) {
			BinaryExpression bin =  VariabilityFactory.eINSTANCE.createBinaryExpression();
			bin.setOperator((BinaryOperator) o);
			bin.setRight(operandStack.pop());
			bin.setLeft(operandStack.pop());
			return (Expression) bin;
		}			
		return null;
	}

	private boolean isFeature(String tok) {
		for (Operator o : Operator.values()) {
			if (o.getOperator().equals(tok)) return false;
		}
		return true;
	}

	private BinaryOperator getBinaryOperator(String tok) {
		for (BinaryOperator o : BinaryOperator.values()) {
			if (o.getLiteral().equals(tok.toUpperCase())) return o;
		}
		return null;
	}

	private UnaryOperator getUnaryOperator(String tok) {
		for (UnaryOperator o : UnaryOperator.values()) {
			if (o.getLiteral().equals(tok.toUpperCase())) return o;
		}
		return null;
	}
}
