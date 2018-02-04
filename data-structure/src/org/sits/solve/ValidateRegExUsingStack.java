package org.sits.solve;

import org.junit.Test;
import org.sits.custom.CustomStack;

public class ValidateRegExUsingStack {

	@Test
	public static void main(String a[]) {
		ValidateRegExUsingStack mdm = new ValidateRegExUsingStack();

		String expression = "{(a+b)*(c+d)}";
		System.out.println(expression + " == " + mdm.isValidateRegEx(expression));

		expression = "{(a+b)+[x*(c+d)]}";
		System.out.println(expression + " == " + mdm.isValidateRegEx(expression));

		expression = "{(a+b)+[x*(c+d)}}";
		System.out.println(expression + " == " + mdm.isValidateRegEx(expression));
	}

	public boolean isValidateRegEx(String inputExpr) {

		int stackSize = inputExpr.length();
		CustomStack<Character> theStack = new CustomStack<>(stackSize);
		for (int j = 0; j < inputExpr.length(); j++) {
			char ch = inputExpr.charAt(j);
			switch (ch) {
			case '{':
			case '[':
			case '(':
				theStack.push(ch);
				break;
			case '}':
			case ']':
			case ')':
				if (!theStack.isStackEmpty()) {
					char stackContent = theStack.pop();
					if ((ch == '}' && stackContent != '{') || (ch == ']' && stackContent != '[')
							|| (ch == ')' && stackContent != '(')) {
						System.out.println("Mismatch found: " + ch + " at " + j);
						return false;
					}
				} else {
					System.out.println("Mismatch found: " + ch + " at " + j);
					return false;
				}
				break;
			default:
				break;
			}
		}
		if (!theStack.isStackEmpty()) {
			System.out.println("Error: missing right delimiter");
			return false;
		}
		return true;
	}
}
