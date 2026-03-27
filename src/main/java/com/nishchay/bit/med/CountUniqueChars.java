package com.nishchay.bit.med;


import java.util.HashSet;
import java.util.Set;

/*
 *	============================ count unique characters in a string =================================
 *
 *	Given a string, str consisting of lowercase English alphabets, the task is to find the number of unique characters present in the string.
 *
 *	Examples:
 *				Input: str = “geeksforgeeks”
 *				Output: 7
 *				Explanation: The given string “geeksforgeeks” contains 7 unique characters {‘g’, ‘e’, ‘k’, ‘s’, ‘f’, ‘o’, ‘r’}.
 *
 *				Input: str = “madam”
 *				Output: 3
 *
 *				abcd - 4
 *				abca - 3
 *				abaaab - 2
 *
 * https://www.geeksforgeeks.org/dsa/count-the-number-of-unique-characters-in-a-given-string/
 *
 */
public class CountUniqueChars {

    public static void main(String[] args) {

        System.out.println(countUnique("abcd"));   // 4
        System.out.println(countUnique("abca"));   // 3
        System.out.println(countUnique("abaaab")); // 2

        System.out.println(countUnique256("geeksforgeeks"));   // 7
        System.out.println(countUnique256("madam"));           // 3

        System.out.println(countUnique26("geeksforgeeks"));   // 7
        System.out.println(countUnique26("madam"));           // 3
    }

    /*
     * ================ [Expected Approach] Using Set (Best & Clean)  =====================
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    public static int countUnique(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }
        return set.size();
    }


    /*
     * ================ [Expected Approach] Using Boolean Array (Faster) =====================
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(256)
     */
    public static int countUnique256(String str) {
        boolean[] seen = new boolean[256]; // ASCII
        int count = 0;

        for (char c : str.toCharArray()) {
            if (!seen[c]) {
                seen[c] = true;
                count++;
            }
        }
        return count;
    }

    /*
     * ================ [Expected Approach] Using Boolean Array (Faster) =====================
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(26)
     */
    public static int countUnique26(String str) {
        int bitmask = 0;

        for (char c : str.toCharArray()) {
            int pos = c - 'a';
            bitmask = bitmask | (1 << pos);
        }
        return Integer.bitCount(bitmask);
    }
}
