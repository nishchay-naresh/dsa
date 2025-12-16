package com.nishchay.algo.slidingwindow;


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
 * https://www.geeksforgeeks.org/dsa/length-of-the-longest-substring-without-repeating-characters/
 * https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
 * */

import java.util.Arrays;

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
                    // Else update the result if this window is larger, and mark current character as visited.
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

        int[] last = new int[26];
        Arrays.fill(last, -1);

        int l = 0, maxLen = 0;

        for (int r = 0; r < s.length(); r++) {
            int idx = s.charAt(r) - 'a';

            if (last[idx] >= l) {
                l = last[idx] + 1;   // shrink window - if char was visited Move left pointer to the right of the last occurrence of it
            }

            last[idx] = r;           // update last index
            int currWindowLength = r - l + 1;
            maxLen = Math.max(maxLen, currWindowLength);
        }

        return maxLen;
    }
}
