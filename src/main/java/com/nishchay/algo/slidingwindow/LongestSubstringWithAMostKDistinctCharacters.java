package com.nishchay.algo.slidingwindow;


import java.util.HashSet;
import java.util.Set;

/*
 *  ======================= Longest substring with k unique characters ====================
 *  ======================= Longest Substring With At Most K Distinct Characters ====================
 *
 * Given a string s and a non negative integer k, find the length of the longest substring that contains exactly k distinct characters.
 * If no such substring exists, return -1.
 *
 * Examples:
 *				Input: s = "aabacbebebe", k = 3
 *				Output: 7
 *				Explanation: The longest substring with exactly 3 distinct characters is "cbebebe", which includes 'c', 'b', and 'e'.
 *
 *				Input: s = "aaaa", k = 2
 *				Output: -1
 *				Explanation: The string contains only one unique character, so there's no substring with 2 distinct characters.
 *
 *				Input: s = "aabaaab", k = 2
 *				Output: 7
 *				Explanation: The entire string "aabaaab" has exactly 2 unique characters 'a' and 'b', making it the longest valid substring.
 *
 *
 * https://takeuforward.org/plus/dsa/problems/longest-substring-with-at-most-k-distinct-characters
 * https://www.geeksforgeeks.org/dsa/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
 * */
public class LongestSubstringWithAMostKDistinctCharacters {

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;

        System.out.println(longestKSubString(s, k));
        System.out.println(longestKSubString_slidingWindow(s, k));
    }

    /*
     * ================ [Naive Approach] Using nested loops with a hash set - O(n^2) Time and O(1) Space  =====================
     *
     * The idea is to find all the substring out of given string, then check for each substring for distinct chars
     * It uses a set to track unique characters, and whenever a substring has exactly k distinct characters, it updates the maximum length.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int longestKSubString(String s, int k) {

        int longestSubstrLength = -1;

        // set to track unique characters in current substring
        Set<Character> st = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {

            // reset the set for a new starting index - 'i'
            st.clear();

            // expand the substring from index i to j
            for (int j = i; j < s.length(); j++) {
                st.add(s.charAt(j));

                // number of unique characters becomes exactly k,
                if (st.size() == k) {
                    longestSubstrLength = Math.max(longestSubstrLength, j - i + 1);
                }

                if (st.size() > k)
                    break;
            }
        }

        return longestSubstrLength;
    }

    /*
     * ================ [Expected Approach]Sliding Window with Frequency Count - O(n) Time and O(1) Space  =====================
     *
     *  The idea is to precompute the prefix sum array where each element at index i stores the sum of elements from index 0 to i-1.
     *  Using this, we can compute the sum of any subarray in constant time O(1) using the difference of two prefix values.
     *  This eliminates the need to iterate over each subarray element repeatedly.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    public static int longestKSubString_slidingWindow(String s, int k) {
        int n = s.length();
        int i = 0, j = 0;
        int cnt = 0;
        int maxi = -1;
        int[] fre = new int[26];

        // cnt represents the number of unique characters in the current window
        while (j < n) {
            int currCharAscii = s.charAt(j) - 'a';

            // include s[j] into the window
            fre[currCharAscii]++;

            // it is the first occurrence of this character in the window
            if (fre[currCharAscii] == 1)
                cnt++;

            // shrink the window if the number of unique characters is more than k
            while (cnt > k) {
                fre[currCharAscii]--;

                // one unique character removed
                if (fre[currCharAscii] == 0)
                    cnt--;
                i++;
            }

            // we have exactly k unique characters
            if (cnt == k) {
                maxi = Math.max(maxi, j - i + 1);
            }

            j++;
        }

        return maxi;
    }

}
