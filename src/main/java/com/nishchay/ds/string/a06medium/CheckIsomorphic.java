package com.nishchay.ds.string.a06medium;

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
 *              Input:paper, title - false
 *              Input:library, privacy - false, failed based on key
 *              Input:badc, kikp       - false, failed based on value
 *
 *
 * https://www.geeksforgeeks.org/dsa/check-if-two-given-strings-are-isomorphic-to-each-other/
 * https://leetcode.com/problems/isomorphic-strings/description/
 *
 * */
public class CheckIsomorphic {

    public static void main(String[] args) {

        System.out.println("isIsomorphic(\"watch\", \"solid\")      - " + isIsomorphic("watch", "solid"));
        System.out.println("isIsomorphic(\"turtle\", \"tletur\")    - " + isIsomorphic("turtle", "tletur"));
        System.out.println("isIsomorphic(\"paper\", \"title\")      - " + isIsomorphic("paper", "title"));
        System.out.println("isIsomorphic(\"ofo\" , \"app\")         - " + isIsomorphic("ofo", "app"));

        System.out.println("isIsomorphic(\"egg\", \"add\")          - " + isIsomorphic("egg", "add"));
        System.out.println("isIsomorphic(\"foo\", \"bar\")          - " + isIsomorphic("foo", "bar"));
        System.out.println("isIsomorphic(\"paper\" , \"title\")     - " + isIsomorphic("ofo", "app"));
        System.out.println("isIsomorphic(\"library\" , \"privacy\") - " + isIsomorphic("ofo", "app"));
        System.out.println("isIsomorphic(\"badc\" , \"kikp\")       - " + isIsomorphic("ofo", "app"));

    }


    /*
     * ================ [Expected Approach 1] Using Hash Maps  =====================
     *  1. If lengths of str1 and str2 are different, return false.
     *  2. Hm<Character, Character> mapping = it will have the mapping chars
     *      with each new char mapping check for
     *          - check for previous mapping - by checking the key
     *          - check for previous mapping - by checking the value
     *          - if through with above two checks, add the new mapping in hashMap
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    public static boolean isIsomorphic(String str1, String str2) {
        // Two strings cannot be isomorphic if they have different lengths.
        if (str1.length() != str2.length()) {
            return false;
        }

        // Using a hashMap to store the char mapping
        Map<Character, Character> mapping = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);

            // If c1 has been encountered before:
            if (mapping.containsKey(c1)) {
                // Return false if first occurrence of c1 is mapped to a different character.
                if (mapping.get(c1) != c2)
                    return false;
            }
            // If c1 is encountered for the first time, it has not been mapped yet:
            else {
                // Return false if c2 is already mapped to some other char in str1
                if (mapping.containsValue(c2))
                    return false;

                // All checks passed. So insert in the mapping, and the set.
                mapping.put(c1, c2);
            }
        }
        return true;
    }
}
