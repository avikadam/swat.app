package org.sits.solve;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.sits.custom.CustomStack;

public class InfixExpressingUsingStack {
	public static String evaluateInfix(String exps) {

		/** remove if any spaces from the expression **/
		exps = exps.replaceAll("\\s+", "");
		/** we assume that the expression is in valid format **/
		CustomStack<String> stack = new CustomStack<String>(exps.length());
		/** break the expression into tokens **/
		StringTokenizer tokens = new StringTokenizer(exps, "{}()*/+-", true);
		while (tokens.hasMoreTokens()) {
			String tkn = tokens.nextToken();
			/** read each token and take action **/
			if (tkn.equals("(") || tkn.equals("{") || tkn.matches("[0-9]+") || tkn.equals("*") || tkn.equals("/")
					|| tkn.equals("+") || tkn.equals("-")) {
				/** push token to the stack **/
				stack.push(tkn);
			} else if (tkn.equals("}") || tkn.equals(")")) {
				try {
					int op2 = Integer.parseInt(stack.pop());
					String oprnd = stack.pop();
					int op1 = Integer.parseInt(stack.pop());
					/** Below pop removes either } or ) from stack **/
					if (!stack.isStackEmpty()) {
						stack.pop();
					}
					int result = 0;
					if (oprnd.equals("*")) {
						result = op1 * op2;
					} else if (oprnd.equals("/")) {
						result = op1 / op2;
					} else if (oprnd.equals("+")) {
						result = op1 + op2;
					} else if (oprnd.equals("-")) {
						result = op1 - op2;
					}
					/** push the result to the stack **/
					stack.push(result + "");
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		}
		String finalResult = "";
		try {
			finalResult = stack.pop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalResult;
	}

	public static void main(String a[]) {
		try (Scanner c = new Scanner(System.in)) {
			System.out.println("Enter expression (e.g. '((2*5)+(6/2)))' ");
			String expr = c.nextLine();
			System.out.println("Final Result: " + evaluateInfix(expr));
		}
		/*
		 * expr = "(((2 * 5) - (1 * 2)) / (11 - 9))";
		 * System.out.println("Expression: " + expr);
		 * System.out.println("Final Result: " + evaluateInfix(expr));
		 */

	}
}
