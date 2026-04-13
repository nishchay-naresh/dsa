package com.nishchay.ds.string.a05anagram;

import com.nishchay.ds.string.a04freq.F00StringFrequencyUtility;

import java.util.Arrays;
import java.util.HashMap;
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

        System.out.println("isAnagram_sorting(cat, act) 				= " +  isAnagram_sorting("cat", "act"));
        System.out.println("isAnagram_intArray(cat, act) 				= " +  isAnagram_intArray("cat", "act"));

        System.out.println("isAnagram_intArray(keep, peek)				= " +  isAnagram_intArray("keep", "peek"));
        System.out.println("isAnagram_hashMap(keep, peek)				= " +  isAnagram_hashMap("keep", "peek"));
        System.out.println("isAnagram_intArray(listen, silent)          = " +  isAnagram_intArray("listen", "silent"));
        System.out.println("isAnagram_hashMap(listen, silent)	        = " +  isAnagram_hashMap("listen", "silent"));

        System.out.println("isAnagram_intArray(rat, car)                = " +  isAnagram_intArray("rat", "car"));
        System.out.println("isAnagram_hashMap(rat, car)	                = " +  isAnagram_hashMap("rat", "car"));
    }

    /*
     * 	Two strings are anagrams if:
     * 		-	Same characters
     * 		-	Same frequency
     * 		-	Order doesn’t matter
     *
     * 	Example:
     * 			listen → silent ✅
     * 			rat → car ❌
     *
     * ============= 1. Sorting Approach (Most intuitive) ==================
     *
     * Sort both strings → then compare
     *
     * Time Complexity     = O(n log n)
     * Space complexity    = O(n)
     *
     * */
    private static boolean isAnagram_sorting(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }
        char[] a1 = string1.toCharArray();
        char[] a2 = string2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

    /*
     *
     * ============= 2️. Frequency Count (Best & Optimal) ==================
     * Assuming input only having smaller chars : a-z
     *
     * Count characters using array
     * get the frequency of each char for both of the input strings in int[]
     *
     * Time Complexity     = O(n)
     * Space complexity    = O(1) (fixed 26)
     *
     * */
    private static boolean isAnagram_intArray(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        int[] freq = new int[26];
        Arrays.fill(freq, 0);

        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int f : freq) {
            if (f != 0) return false;
        }
        return true;
    }

    /*
     * ============= 3️. HashMap Approach (Generic charset) ==================
     *
     * Get the freqMap for each char for both of the input strings
     * then comparing these two frequency maps
     *
     * Time Complexity     = O(2*n) = O(n)
     * Space complexity    = O(2*n)
     * */
    private static boolean isAnagram_hashMap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0)
                map.remove(c);
        }
        return map.isEmpty();
    }
}
