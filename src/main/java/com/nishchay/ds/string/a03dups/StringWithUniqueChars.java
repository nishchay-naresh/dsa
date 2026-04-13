package com.nishchay.ds.string.a03dups;

import java.util.HashSet;
import java.util.Set;

/*
 * ========================== Determine if a string has all Unique Characters ====================================
 *
 * Given a string, determine if the string has all unique characters.
 *
 * Examples:
 *				Input : abcd10jk
 *				Output: true
 *
 *				Input : hutg9mnd!nk9
 *				Output: false
 *
 * Examples
 *		        "java" - false
 * 		        "perl" - true
 * 		        "apple"  - false
 * 		        "orange" - false
 *
 * https://www.geeksforgeeks.org/dsa/determine-string-unique-characters/
 *
 * */
public class StringWithUniqueChars {

    public static void main(String[] args) {

        System.out.println("isUniqueCharsOnlyHashSet(\"abcd10jk\")      - " + isUniqueCharsOnlyHashSet("abcd10jk"));
        System.out.println("isUniqueCharsOnlyHashSet(\"hutg9mnd!nk9\")  - " + isUniqueCharsOnlyHashSet("hutg9mnd!nk9"));

        System.out.println("isUniqueCharsOnlyHashSet(\"java\")      - " + isUniqueCharsOnlyHashSet("java"));
        System.out.println("isUniqueCharsOnlyHashSet(\"perl\")      - " + isUniqueCharsOnlyHashSet("perl"));
        System.out.println("isUniqueCharsOnlyHashSet(\"apple\")     - " + isUniqueCharsOnlyHashSet("apple"));
        System.out.println("isUniqueCharsOnlyHashSet(\"orange\")    - " + isUniqueCharsOnlyHashSet("orange"));

        System.out.println("isUniqueCharsOnlyArray(\"orange\")      - " + isUniqueCharsOnlyArray("orange"));
        System.out.println("isUniqueCharsOnlyStream(\"orange\")     - " + isUniqueCharsOnlyStream("orange"));
    }

    /*
     * ================ [Efficient Approach] Using hashSet – O(n) Time and O(n) Space =====================
     *
     *  First traversal string and try to add each char in HashSet,
     *  HashSet will help you to identify the 2nd occurrence of char
     *
     *  Time Complexity     = O(n)
     *  Space complexity    = O(n)
     */
    private static boolean isUniqueCharsOnlyHashSet(String str) {
        Set<Character> hashSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!hashSet.add(c))
                return false; //return false if could not add
        }
        return true;
    }

    /*
     * ================ [Efficient Approach] Using Visited array concept – O(n) Time and O(n) Space =====================
     *
     *  Time Complexity     = O(n)
     *  Space complexity    = O(256)
     */
    public static boolean isUniqueCharsOnlyArray(String str) {
        boolean[] visited = new boolean[256];
        for (char ch : str.toCharArray()) {
            if (visited[ch]) {
                return false;   // Character already seen
            }
            visited[ch] = true; // No duplicate characters found
        }
        return true;
    }

    private static boolean isUniqueCharsOnlyStream(String str) {
        return str.length() == str.chars().distinct().count();
    }
}
