package com.nishchay.ds.string.a06med;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 *  ================================ Isomorphic Strings Check ============================================
 * Given two strings s1 and s2 of equal length, consisting only of lowercase English letters, determine if they are isomorphic.
 * Two strings are isomorphic if characters in s1 can be replaced to get s2 such that:
 *  - Each character in s1 maps to a unique character in s2.
 *  - The mapping is consistent throughout the string.
 *  - The order of characters is preserved.
 *
 * Examples:
 *				Input: s1 = "aab", s2 = "xxy"
 *				Output: true
 *				Explanation: Each character in s1 can be consistently mapped to a unique character in s2 (a → x, b → y).
 *
 *				Input: s1 = "aab", s2 = "xyz"
 *				Output: false
 *				Explanation: Same character 'a' in s1 maps to two different characters 'x' and 'y' in s2.
 *
 *				Input: s1 = "abc", s2 = "xxz"
 *				Output: false
 *				Explanation: Two different characters 'a' and 'b' in s1 maps with same character 'x' in s2.
 *
 *              Input:egg,add - true
 *              Input:foo, bar - false
 *              Input:paper, title - true
 *              Input:library, privacy - false, failed based on key
 *              Input:badc, kikp       - false, failed based on value
 *                      b → k, a → i, d → k  ❌ conflict (k already mapped from b)
 *
 * This is a classic bijection / mapping consistency problem.
 *
 * https://www.geeksforgeeks.org/dsa/check-if-two-given-strings-are-isomorphic-to-each-other/
 * https://leetcode.com/problems/isomorphic-strings/description/
 *
 * */
public class CheckIsomorphic {

    public static void main(String[] args) {

        System.out.println(isIsomorphic_2map("aab", "xxy"));   // true
        System.out.println(isIsomorphic_2map("aab", "xyz"));   // false
        System.out.println(isIsomorphic_2map("abc", "xxz"));   // false
        System.out.println(isIsomorphic_2map("egg", "add"));   // true
        System.out.println(isIsomorphic_2map("foo", "bar"));   // false
        System.out.println(isIsomorphic_2map("paper", "title")); // true
        System.out.println(isIsomorphic_2map("library", "privacy"));    // false

        System.out.println("isIsomorphic(watch, solid)      - " + isIsomorphic_intArray("watch", "solid"));
        System.out.println("isIsomorphic(turtle, tletur)    - " + isIsomorphic_intArray("turtle", "tletur"));
        System.out.println("isIsomorphic(paper, title)      - " + isIsomorphic_intArray("paper", "title"));
        System.out.println("isIsomorphic(ofo, app)          - " + isIsomorphic_intArray("ofo", "app"));
        System.out.println("isIsomorphic(egg, add)          - " + isIsomorphic_intArray("egg", "add"));
        System.out.println("isIsomorphic(foo, bar)          - " + isIsomorphic_intArray("foo", "bar"));
        System.out.println("isIsomorphic(paper, title)      - " + isIsomorphic_intArray("ofo", "app"));
        System.out.println("isIsomorphic(library, privacy)  - " + isIsomorphic_intArray("ofo", "app"));
        System.out.println("isIsomorphic(badc, kikp)        - " + isIsomorphic_intArray("ofo", "app"));
    }


    /*
     * ======================== [Expected Approach] Using 2 Hash Maps  =============================
     *
     * Two strings are isomorphic if there is a 1-to-1 mapping (bijection) between characters.
     *
     * That means:
     * 	    One character → maps to exactly one character ✅
     * 	    Two different characters → CANNOT map to same character ❌
     * So we must enforce:
     * 	    Forward mapping: s1 → s2
     *  	Reverse mapping: s2 → s1
     *
     * ------- Why 2 maps? ------
     * If you use only one map:
     * 	    a → x
     * 	    b → x   ❌ (invalid but won't be caught with one map)
     *
     * So we need to have 2 maps?
     * 	    map1: s1 → s2
     * 	    map2: s2 → s1
     *
     * ---------- Approach ---------------
     * Loop through both strings together:
     * 	At index i:
     * 		Let c1 = s1.charAt(i)
     * 		Let c2 = s2.charAt(i)
     * 	Cases:
     * 		If mapping exists → must match  (both directions)
     * 		If not → create mapping (both directions)
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n) + O(n) = O(n)
     */
    public static boolean isIsomorphic_2map(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            // Check forward mapping
            if (map1.containsKey(c1)) {
                if (map1.get(c1) != c2)
                    return false;
            } else {
                map1.put(c1, c2);
            }

            // Check reverse mapping
            if (map2.containsKey(c2)) {
                if (map2.get(c2) != c1)
                    return false;
            } else {
                map2.put(c2, c1);
            }
        }
        return true;
    }

    /*
     * ======================== [Expected Approach] Using int[] inplace of HashMaps  =============================
     *
     *  Since only lowercase letters exist → use int arrays
     *  Keep the same above logic only using int[] to store the mapping inplace of HashMap
     * ---------- Approach ---------------
     * Loop through both strings together:
     * 	At index i:
     * 		Let c1 = s1.charAt(i)
     * 		Let c2 = s2.charAt(i)
     * 	Cases:
     * 		If mapping exists → must match  (both directions)
     * 		If not → create mapping (both directions)
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1) (since 26 letters)
     */
    public static boolean isIsomorphic_intArray(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int[] s1Mapping = new int[26];
        int[] s2Mapping = new int[26];
        Arrays.fill(s1Mapping, -1);
        Arrays.fill(s2Mapping, -1);

        for (int i = 0; i < s1.length(); i++) {
            int c1 = s1.charAt(i) - 'a';
            int c2 = s2.charAt(i) - 'a';

            if (s1Mapping[c1] != -1 && s1Mapping[c1] != c2) return false;
            if (s2Mapping[c2] != -1 && s2Mapping[c2] != c1) return false;

            s1Mapping[c1] = c2;
            s2Mapping[c2] = c1;
        }
        return true;
    }
}
