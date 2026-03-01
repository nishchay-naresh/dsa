package com.nishchay.ds.stack.a02expr;

import java.util.Stack;


/*
 *  ============================= Valid Parentheses in an Expression =====================================
 *
 * Given a string s containing three types of brackets {}, () and []. Determine whether the Expression are balanced or not.
 * An expression is balanced if each opening bracket has a corresponding closing bracket of the same type,
 * the pairs are properly ordered and no bracket closes before its matching opening bracket.
 *
 * 	-	Balanced: "[()()]{}" → every opening bracket is closed by same type of brackets also in the correct order.
 * 	-	Not balanced: "([{]})" → the ']' closes before the matching '{' is closed, breaking the nesting rule.
 *
 * 	Examples:
 * 				Input: s = "[{()}]"
 * 				Output: true
 * 				Explanation:  All the brackets are well-formed.
 *
 * 				Input:  s = "([{]})"
 * 				Output: false
 * 				Explanation: The expression is not balanced because there is a closing ']' before the closing '}'.
 *
 * 				Input: s = "()"
 * 				Output: true
 *
 * 				Input: s = "()[]{}"
 * 				Output: true
 *
 * 				Input: s = "(]"
 * 				Output: false
 *
 * 				Input: s = "([)]"
 * 				Output: false
 *
 * 				Input: s = "{[]}"
 * 				Output: true
 *
 * 				Input: s = "{[()]}"
 * 				Output: true
 *
 * 				Input: s = "{[(])}"
 * 				Output: false
 *
 * https://www.geeksforgeeks.org/dsa/check-for-balanced-parentheses-in-an-expression/
 * */
public class ValidParentheses {

    public static void main(String[] args) {

        executeForAnInput();
        System.out.println("................................");
        doTestsPass();
    }

    private static void executeForAnInput() {
        String s = "[()()]{}";
        System.out.println((isBalanced(s) ? "true" : "false"));
    }

    /**
     * Boolean doTestsPass()
     * Returns true if all tests pass. Otherwise, returns false.
     */
    public static void doTestsPass() {

        String expr = "{[}], [[()]], ({([])}), [[]), {{[]))]]), {[()]}, {[(])}, [[]]))), (), ()[]{}, (], ([)], {[]}";
        boolean[] outputs = {false, true, true, false, false, true, false, false, true, true, false, false, true};

        String[] inputs = expr.split(",");

        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {
            result = result && outputs[i] == isBalanced(inputs[i]);
            if (!result)
                System.out.println("Test failed for: " + inputs[i]);
            else
                System.out.println("Test passed for: " + inputs[i]);
        }
    }

    /*
     * ================ [Approach 1] Using Stack - O(n) Time and O(n) Space  =====================
     *
     *  We use a stack to ensure that every opening has a matching closing.
     *  Each opening is pushed onto the stack.
     *      When a closing appears, we check if the stack has a corresponding opening to pop; if not, the string is unbalanced.
     *  After processing the entire string, the stack must be empty for it to be considered balanced.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    public static boolean isBalanced(String exprStr) {

        char[] charArr = exprStr.trim().toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char currChar : charArr) {

            // Push opening brackets
            if (currChar == '(' || currChar == '{' || currChar == '[') {
                stack.push(currChar);
                // Handle closing brackets
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if (
                        (currChar == ')' && top != '(') ||
                        (currChar == '}' && top != '{') ||
                       (currChar == ']' && top != '[')
                ) {
                    return false;
                }
            }
        }
        // Stack should be empty at the end
        return stack.empty();
    }

}
