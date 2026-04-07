package com.nishchay.ds.string.a05freq;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 *	=========== Freq Of Repeating Chars ===========
 *	=========== Print all the duplicate characters with its frequency in a string ===========
 *
 * Given a string s, the task is to identify all characters that appear more than once and print each as a list containing the character and its count.
 *
 * Examples:
 *				Input: s = "geeksforgeeks"
 *				Output: ['e', 4], ['g', 2], ['k', 2], ['s', 2]
 *				Explanation: Characters e, g, k, and s appear more than once. Their counts are shown in order of first occurrence.
 *
 *				Input: s = "programming"
 *				Output: ['r', 2], ['g', 2], ['m', 2]
 *				Explanation: Only r, g, and m are duplicates. Output lists them with their counts.
 *
 *				Input: s = "mississippi"
 *				Output: ['i', 4], ['s', 4], ['p', 2]
 *				Explanation: Characters i, s, and p have multiple occurrences. The output reflects that with count and order preserved.
 *
 *		        String str = "teststring";
 *		        Output: ['s', 2], ['t', 3]
 *
 * https://www.geeksforgeeks.org/dsa/print-all-the-duplicates-in-the-input-string/
 * */
public class FreqOfRepeatingChars {

    public static void main(String[] args) {
        String s;

        s = "geeksforgeeks";
        printRepeatingChars(s);
        System.out.println();

        s = "programming";
        printRepeatingChars(s);
        System.out.println();

        s = "mississippi";
        printRepeatingChars(s);
        System.out.println();

        s = "teststring";
        printRepeatingChars(s);
        System.out.println();
    }

    /*
     * ================ [Efficient Approach] Using Hashing(hashMap)  – O(n) Time and O(n) Space =====================
     *
     *  First traversal string and try to add each char in HashSet
     *  HashSet will help you to identify the 2nd occurrence of char
     *
     *  Time Complexity     = O(2*n) = O(n)
     *  Space complexity    = O(n)
     */
    private static void printRepeatingChars(String str) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char currChar : str.toCharArray()) {
            freqMap.put(currChar, freqMap.getOrDefault(currChar, 0) + 1);
        }

        // Traverse the map and print characters with count > 1
        for (Map.Entry<Character, Integer> it : freqMap.entrySet()) {
            if (it.getValue() > 1) {
                System.out.print("['" + it.getKey() + "', " + it.getValue() + "], ");
            }
        }
    }

}
