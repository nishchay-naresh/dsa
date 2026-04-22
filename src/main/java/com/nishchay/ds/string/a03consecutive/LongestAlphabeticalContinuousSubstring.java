package com.nishchay.ds.string.a03consecutive;


/*
 *	========================== 2414. Length of the Longest Alphabetical Continuous Substring ========================
 *
 * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet.
 * In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
 *
 * For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
 * Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous substring.
 *
 * Examples:
 *				Input: str = "abcabcdefabc"
 *				Output: 6
 *				All valid sub-strings are "abc", "abcdef" and "abc"
 *				And, the length of the longest of these is 6
 *
 *				Input: str = "zabcd"
 *				Output: 4
 *
 * Example 1:
 * 				Input: s = "abacaba"
 * 				Output: 2
 * 				Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
 * 							"ab" is the longest continuous substring.
 * Example 2:
 * 				Input: s = "abcde"
 * 				Output: 5
 * 				Explanation: "abcde" is the longest continuous substring.
 *
 *
 * Constraints:
 *          1 <= s.length <= 105
 *          s consists of only English lowercase letters.
 *
 * https://www.geeksforgeeks.org/dsa/length-of-the-longest-substring-with-consecutive-characters/
 * https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/description/
 * */
public class LongestAlphabeticalContinuousSubstring {

    public static void main(String[] args) {
        System.out.println(longestContinuousSubstring("abcabcdefabc")); // 6
        System.out.println(longestContinuousSubstring("zabcd"));        // 4
        System.out.println(longestContinuousSubstring("abacaba"));      // 2
        System.out.println(longestContinuousSubstring("abcde"));        // 5
    }


    /*
     * ================ [Expected Approach] Sliding Window / Linear Scan =====================
     *
     *	We just track:
     *	    currLen → current continuous streak
     *      maxLen → best answer
     *
     *
     * Time complexity: O(n) + O(n) = O(n)
     * Auxiliary space: O(n)
    * */
    private static int longestContinuousSubstring(String s) {
        int maxLen = 1;
        int currLen = 1;

        for (int i = 1; i < s.length(); i++) {

            // check alphabetical continuity
            if (s.charAt(i) == s.charAt(i - 1) + 1) {
                currLen++;
            } else {
                currLen = 1; // reset
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }
}