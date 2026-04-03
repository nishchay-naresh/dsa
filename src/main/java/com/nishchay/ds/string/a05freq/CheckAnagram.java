package com.nishchay.ds.string.a05freq;

import java.util.Arrays;
import java.util.Map;

/*
 *	====================================== Check if two Strings are Anagrams of each other =================================================
 *
 * Given two non-empty strings s1 and s2 of lowercase letters, determine if they are anagrams
 *  — i.e., if they contain the same characters with the same frequencies.
 *
 * Examples:
 *				Input: s1 = “geeks”  s2 = “kseeg”
 *				Output: true
 *				Explanation: Both the string have same characters with same frequency. So, they are anagrams.
 *
 *				Input: s1 = "allergy", s2 = "allergyy"
 *				Output: false
 *				Explanation: Although the characters are mostly the same, s2 contains an extra 'y' character. Since the frequency of characters differs, the strings are not anagrams.
 *
 *				Input: s1 = "listen", s2 = "lists"
 *				Output: false
 *				Explanation: The characters in the two strings are not the same — some are missing or extra. So, they are not anagrams.

 * https://www.geeksforgeeks.org/dsa/check-whether-two-strings-are-anagram-of-each-other/
 * https://leetcode.com/problems/valid-anagram/description/
 * */
public class CheckAnagram {

    public static void main(String[] args) {

        System.out.println("  isAnagramSort(\"cat\", \"act\") - " +  isAnagramSort("cat", "act"));
        System.out.println("  isAnagramSort(\"Keep\", \"Peek\") - " + isAnagramSort("Keep", "Peek"));
        System.out.println("  isAnagramSort(\"Mother In Law\", \"Hitler Woman\") - " +  isAnagramSort("Mother In Law", "Hitler Woman"));

        System.out.println("  isAnagramStream(\"cat\", \"act\") - " + isAnagramStream("cat", "act"));
        System.out.println("  isAnagramStream(\"Keep\", \"Peek\") - " + isAnagramStream("Keep", "Peek"));
        System.out.println("  isAnagramStream(\"Mother In Law\", \"Hitler Woman\") - " +  isAnagramStream("Mother In Law", "Hitler Woman"));

        System.out.println("  isAnagramMap(\"cat\", \"act\") - " + isAnagramMap("cat", "act"));
        System.out.println("  isAnagramMap(\"keep\", \"peek\") - " + isAnagramMap("keep", "peek"));
        System.out.println("  isAnagramMap(\"motherinlaw\", \"hitlerwoman\") - " +  isAnagramMap("motherinlaw", "hitlerwoman"));
    }

    /*
    * Sorting, then array comparison
    *
    * Time Complexity     = O(n log n) = O(n)
    * Space complexity    = O(1)
    * */
    private static boolean isAnagramSort(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }
        char[] a1 = string1.toCharArray();
        char[] a2 = string2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

    private static boolean isAnagramStream(String str1, String str2) {
        int[] str1Chars = str1.toLowerCase().chars().sorted().toArray();
        int[] str2Chars = str2.toLowerCase().chars().sorted().toArray();
        return Arrays.equals(str1Chars, str2Chars);
    }

    /*
     * get the freqMap for each char in both of the input strings
     * then comparing these two frequency maps
     *
     * Time Complexity     = O(2*n) = O(n)
     * Space complexity    = O(2*n)
     * */
    private static boolean isAnagramMap(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }

        Map<Character, Integer> freqMap1 = F00StringFrequencyUtility.getFrequencyMap(string1);
        Map<Character, Integer> freqMap2 = F00StringFrequencyUtility.getFrequencyMap(string2);
        return freqMap1.equals(freqMap2);
    }
}
