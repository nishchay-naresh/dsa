package com.nishchay.ds.stack.a04med;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * ================================ 1047. Remove All Adjacent Duplicates In String ======================================
 *
 *	You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *	We repeatedly make duplicate removals on s until we no longer can.
 *	Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 *	Example 1:
 *			    Input: s = "abbaca"
 *			    Output: "ca"
 *			    Explanation: For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.
 *  					 The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 *	Example 2:
 *	            Input: s = "azxxzy"
 *	            Output: "ay"
 *
 * https://www.geeksforgeeks.org/dsa/remove-all-duplicate-adjacent-characters-from-a-string-using-stack/
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/
 * */
public class RemoveAdjacentDuplicateElements {
    public static void main(String[] args) {
        String str = "abbaca";
        System.out.println("Merged Array : " + removeAdjacentDuplicates(str)); // "ca"

        str = "azxxzy";
        System.out.println("Merged Array : " + removeAdjacentDuplicates(str)); // "ay"

        str = "aaccdd";
        System.out.println("Merged Array : " + removeAdjacentDuplicates(str)); // ""
    }

    /*
     * ================ [Expected/Optimal Approach] Using Stack - O(n * m) Time and O(1) Space =====================
     *
     * => This screams STACK.
     * Why stack?
     * 	-	You only care about the previous element
     * 	-	You want to repeatedly compare with the last resolved value
     *
     *	Algorithm
     *		-	Create an empty stack
     *		-	Traverse the String char wise
     *      -   If current char == stack top → pop
     *      -   Else → push
     *		-	Convert stack to result string
     *
     *  Time Complexity : O(n), Each element is pushed and popped at most once.
     *  Space Complexity: O(n), stack
     *
     * Stack is a legacy synchronized class. ArrayDeque is faster, cleaner, and the recommended replacement for stack behavior in modern Java.
     * */
    private static String removeAdjacentDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop(); // remove duplicate
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }
}
