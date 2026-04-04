package com.nishchay.algo.slidingwindow;

import java.util.Arrays;

/*
 *  ======================= Longest Substring Without Repeating Characters ====================
 *
 * Given a string s having lowercase characters, find the length of the longest substring without repeating characters.
 *
 * Examples:
 *				Input: s = "geeksforgeeks"
 *				Output: 7
 *				Explanation: The longest substrings without repeating characters are "eksforg” and "ksforge", with lengths of 7.
 *
 *				Input: s = "aaa"
 *				Output: 1
 *				Explanation: The longest substring without repeating characters is "a"
 *
 *				Input: s = "abcdefabcbb"
 *				Output: 6
 *				Explanation: The longest substring without repeating characters is "abcdef".
 *
 *
 *	Example 1:
 *		  		Input: s = "abcabcbb"
 *	      		Output: 3
 *	      		Explanation: The answer is "abc", with the length of 3.
 *
 *	Example 2:
 *	      		Input: s = "bbbbb"
 *	      		Output: 1
 *	      		Explanation: The answer is "b", with the length of 1.
 *
 *	Example 3:
 *	      		Input: s = "pwwkew"
 *	      		Output: 3
 *	      		Explanation: The answer is "wke", with the length of 3.
 *
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * https://www.geeksforgeeks.org/dsa/length-of-the-longest-substring-without-repeating-characters/
 * https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * */

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "geeksforgeeks";
        System.out.println(longestUniqueSubstring(s));
        System.out.println(longestNonRepeatingSubstring_slidingWindow(s));

        s = "cadbzabcd";
        System.out.println(longestUniqueSubstring(s));
        System.out.println(longestNonRepeatingSubstring_slidingWindow(s));
    }

    /*
     * ================ [Naive Approach] Substrings Starting From Every Index - O(n^2) Time and O(26) Space  =====================
     *
     *  The idea is to find all the substring out of given string, then check for each substring formed by unique chars
     *  check for each substring formed by unique chars -  will use a boolean array visited[26]
     *  keep track of included characters in the substring -  visited[s.charAt(j) - 'a'] = true;
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(26)
     *
     */
    private static int longestUniqueSubstring(String s){
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {

            // Initializing all characters as not visited
            boolean[] visited = new boolean[26];

            for (int j = i; j < n; j++) {

                // If current character is visited break the loop
                if (visited[s.charAt(j) - 'a'])
                    break;
                    // Else update the result if this window is larger, and mark the current character as visited.
                else {
                    int currSubstringLength = j - i + 1;
                    maxLength = Math.max(maxLength, currSubstringLength);
                    visited[s.charAt(j) - 'a'] = true;
                }
            }
        }
        return maxLength;
    }


    /*
     * ================ [Expected Approach 1] Using Sliding Window - O(n) Time and O(1) Space  =====================
     *
     *  The idea is to precompute the prefix sum array where each element at index i stores the sum of elements from index 0 to i-1.
     *  Using this, we can compute the sum of any subarray in constant time O(1) using the difference of two prefix values.
     *  This eliminates the need to iterate over each subarray element repeatedly.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(26)
     */
    private static int longestNonRepeatingSubstring_slidingWindow(String s) {

        int[] last = new int[256];
        Arrays.fill(last, -1);

        int l = 0;
        int maxLen = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (last[c] >= l) {
                l = last[c] + 1;    // shrink window - if char was visited, Move a left pointer to the right of the last occurrence of it
            }
            last[c] = r;            // update last index
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}
