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
 * 				Input: str = ")(" // intermediate invalid states
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
        System.out.println(minAddToMakeValidStack(s));  // 3

        s = "(()(";
        System.out.println(minAddToMakeValidStack(s));  // 2
    }

    private static void doTestsPass() {
        String[] inputs = {"())", "(((", "(()", "()", ")(", "))(("};
        int[] outputs = {1, 3, 1, 0, 2, 4};


        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {
            result = result && minAddToMakeValidCounters(inputs[i]) == outputs[i];
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
    private static int minAddToMakeValidStack(String s) {
        Stack<Character> stack = new Stack<>();

        char[] arr = s.toCharArray();
        for (char curr : arr) {
            if (curr == '(') {
                stack.push(curr);
            } else { // ')'
                if (curr == ')' && stack.peek() == '(') {
                    stack.pop();                           // matched pair
                } else {
                    stack.push(curr);                      // unmatched ')'
                }
            }
        }
        // Remaining characters in stack are unmatched
        return stack.size();
    }

    /*
     * ================ [Approach 2] Using Counter / Balance Method - O(n) Time and O(1) Space  =====================
     *
     *  The idea is to track unmatched parentheses using counters instead of a stack.
     * 	    - open: counts how many unmatched '(' exist so far.
     *      - unmatchedClosing: counts how many unmatched ')' were found.
     *      - result = (unmatched '(') + (unmatched ')') = balance + unmatchedClosing
     *
     * 	while scanning left → right
     * 		for every '('
     * 			open++ 	// in expect of future ')'
     * 		for every '('
     * 			if open ==0, then its unmatched ')'
     * 				unmatchedClosing ++
     * 			else open-- // its matching ')' of past '('
     *
     * 	At last
     * 		return open + unmatchedClosing; // remaining ')' + unmatched ')'
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int minAddToMakeValidCounters(String s) {
        int open = 0;
        int unmatchedClosing = 0;

        for (char curr : s.toCharArray()) {
            // if current char is '(', increment open
            if (curr == '(') {
                open++;
            } else {
                // if current char is ')', decrement open
                if (open > 0) {
                    open--;
                } else { // counting unmatched ')'
                    unmatchedClosing++;
                }
            }
        }
        // total additions = remaining ')' + unmatched ')'
        return open + unmatchedClosing;
    }
}
