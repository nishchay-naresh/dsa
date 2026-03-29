package com.nishchay.ds.string.a02easy;

/*
 * ================================= Longest Common Prefix =======================================
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Examples:
 *				Input: arr[] = ["geeksforgeeks", "geeks", "geek", "geezer"]
 *				Output: “gee”
 *				Explanation: “gee” is the longest common prefix in all the given strings: “geeksforgeeks”, “geeks”, “geeks” and “geezer”.
 *
 *				Input: arr[] = [“apple”, “ape”, “april”]
 *				Output : “ap”
 *				Explanation: “ap” is the longest common prefix in all the given strings: “apple”, “ape” and “april”.
 *
 *				Input: arr[] = [“hello”, “world”]
 *				Output: “”
 *				Explanation: There’s no common prefix in the given strings.
 *
 * 	            Input: strs = ["cluster", "clue", "clutch", "club", "clumsy"]
 *              Output: "fl"
 *
 * 				Input: strs = ["flower","flow","flight"]
 * 				Output: "fl"
 *
 * 				Input: strs = ["dog","racecar","car"]
 * 				Output: ""
 * 				Explanation: There is no common prefix among the input strings.
 *
 *
 * https://www.geeksforgeeks.org/dsa/longest-common-prefix-using-character-by-character-matching/
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] arr = new String[]{"geeksforgeeks", "geeks", "geek", "geezer"};
        System.out.println("longestCommonPrefix - " + longestCommonPrefix(arr)); // gee

        arr = new String[]{"hello", "world"};
        System.out.println("longestCommonPrefix - " + longestCommonPrefix(arr)); // ""

        arr = new String[]{"cluster", "clue", "clutch", "club", "clumsy"};
        System.out.println("longestCommonPrefix - " + longestCommonPrefix(arr)); // clu

        arr = new String[]{"flower","flow","flight"};
        System.out.println("longestCommonPrefix - " + longestCommonPrefix(arr)); // fl

        arr = new String[]{"dog","racecar","car"};
        System.out.println("longestCommonPrefix - " + longestCommonPrefix(arr)); // ""
    }

    /*
     * ================ [Expected Approach] by char by char Matching - O(n * m) Time and O(1) Space =====================
     *
     * Vertical Scanning - Longest Common Prefix using char by char Matching
     *	c l u s t e r
     *	c l u e
     *	c l u t c h
     *	c l u b
     *	c l u m s y
     *	^ ^ ^
     *	| | | ... so on
     *
     *	The maximum possible prefix limited by the shortest string
     *	Compare characters at the same index across all strings
     *	Stop immediately when:
     *		A mismatch occurs, or One string ends
     *
     *
     *  Time Complexity : O(n * m), n = number of strings, m = length of shortest string
     *  Space Complexity: O(1)
     * */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            char currChar = first.charAt(i);

            for (int j = 1; j < strs.length; j++) {
                // string ended or mismatch found
                if (i >= strs[j].length() || strs[j].charAt(i) != currChar) {
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }
}