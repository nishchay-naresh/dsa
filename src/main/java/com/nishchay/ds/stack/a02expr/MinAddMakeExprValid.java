package com.nishchay.ds.stack.a02expr;


/*
 *	============================= Minimum Additions for Valid Parentheses =====================================
 *
 * Given a string s consisting of parentheses '(' and ')'.
 *  Find the minimum number of parentheses (either '(' or ')') that must be added at any positions to make the string s a valid parentheses string.
 *
 *   Examples:
 *				Input: s = "(()("
 *				Output: 2
 *				Explanation: Two '(' are left unmatched, so we need two ')' to balance.
 *
 *				Input: s = ")))"
 *				Output: 3
 *				Explanation: Three '(' need to be added at the end to make the string valid.
 *
 * 				Input: str = "())"
 * 				Output: 1
 * 				Explanation: One '(' is required at beginning.
 *
 * 				Input: str = "((("
 * 				Output: 3
 * 				Explanation: Three ')' is required at end.
 *
 * 				Input: str = ")("
 * 				Output: 2
 * 				Explanation: One ')' & One '(' is required
 *
 *
 * https://www.geeksforgeeks.org/dsa/minimum-number-of-parentheses-to-be-added-to-make-it-valid/
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
 *
 * */

import java.util.Stack;

public class MinAddMakeExprValid {

    public static void main(String[] args) {

        executeForAnInput();
        System.out.println("................................");
        doTestsPass();
    }

    private static void executeForAnInput() {
        String s = "(((";
        System.out.println(minParenthesesStack(s));  // 3

        s = "(()(";
        System.out.println(minParenthesesStack(s));  // 2
    }

    private static void doTestsPass() {
        String[] inputs = {"())", "(((", "(()", "()", ")(", "))(("};
        int[] outputs = {1, 3, 1, 0, 2, 4};


        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {
            result = result && minParentheses(inputs[i]) == outputs[i];
            if (!result)
                System.out.println("Test failed for: " + inputs[i]);
            else
                System.out.println("Test passed for: " + inputs[i]);
        }
    }

    /*
     * ================ [Approach 1] Using Stack - O(n) Time and O(n) Space  =====================
     *
     *  The idea is to use the concept of valid parentheses refer - ValidParentheses.isBalanced()
     *  For every opening parenthesis, a matching closing parenthesis will remove it from the stack.
     *  At the end, only the unmatched parentheses remain in the stack,
     *  and their count gives the number of insertions needed to make the string valid.
     *
     * At the end of the string:
     *  - Every '(' left in the stack needs a ')' to become valid.
     *  - Every ')' left in the stack needs a '(' to become valid.
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int minParenthesesStack(String s) {
        Stack<Character> st = new Stack<>();

        char[] arr = s.toCharArray();
        for (char currChar : arr) {
            if (!st.isEmpty()) {
                if (currChar == '(') {       // opening bracket
                    st.push(currChar);
                }
                else if (st.peek() == '(' && currChar == ')') {
                    st.pop();               // matched pair
                }
                else {
                    st.push(currChar);       // unmatched ')'
                }
            } else {
                st.push(currChar);           // stack is empty -> push whatever comes
            }
        }
        return st.size();
    }

    /*
     * ================ [Approach 2] Using Counter / Balance Method - O(n) Time and O(1) Space  =====================
     *
     *  The idea is to track unmatched parentheses using counters instead of a stack.
     * 	    - balance: counts how many unmatched '(' exist so far.
     *      - unmatchedClosing: counts how many unmatched ')' were found.
     *      - result = (unmatched '(') + (unmatched ')') = balance + unmatchedClosing
     *
     *  The number of '(' minus the number of ')'
     *      Result == 0 : string is valid
     *      Result == negative : means '(' that many required
     *      Result == positive : means ')' that many required
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int minParentheses(String s) {
        int balance = 0;
        int unmatchedClosing = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // if current char is '(', increment balance
            if (c == '(') {
                balance++;
            }
            // if current char is ')', decrement balance
            else if (c == ')') {
                balance--;

                // if balance becomes negative, unmatched ')'
                if (balance < 0) {
                    unmatchedClosing++;
                    balance = 0;
                }
            }
        }

        // total additions = remaining '(' + unmatched ')'
        return balance + unmatchedClosing;
    }

}
