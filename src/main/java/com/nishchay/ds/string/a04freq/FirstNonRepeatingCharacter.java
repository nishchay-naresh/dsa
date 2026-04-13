package com.nishchay.ds.string.a04freq;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 *	====================================== First Unique(non-repeated) Character in a String =================================================
 *
 * Given a string s of lowercase English letters, the task is to find the first non-repeating character. If there is no such character, return '$'.
 *
 * Examples:
 *				Input: s = "geeksforgeeks"
 *				Output: 'f'
 *				Explanation: 'f' is the first character in the string which does not repeat.
 *
 *				Input: s = "racecar"
 *				Output: 'e'
 *				Explanation: 'e' is the only character in the string which does not repeat.
 *
 *				Input: "aabbccc"
 *				Output: '$'
 *				Explanation: All the characters in the given string are repeated.
 *
 * https://www.geeksforgeeks.org/dsa/given-a-string-find-its-first-non-repeating-character/
 * https://leetcode.com/problems/first-unique-character-in-a-string/description/
 * */

/**
 * <p>
 * char findFirst(String input)
 * Finds the first character that does not repeat anywhere in the input string
 * <p>
 * If all characters are repeated, return 0
 * Given "apple", the answer is "a"
 * Given "racecars", the answer is "e"
 * Given "ababdc", the answer is "d"
 * Given "simplest", the answer is "i"
 **/
public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {

        System.out.println("findFirst_arr26(geeksforgeeks)  = " + findFirst_arr26("geeksforgeeks"));
        System.out.println("findFirst_map(geeksforgeeks)    = " + findFirst_map("geeksforgeeks"));
        System.out.println("findFirst_arr26(racecar)        = " + findFirst_arr26("racecar"));
        System.out.println("findFirst_map(racecar)          = " + findFirst_map("racecar"));
        System.out.println("findFirst_arr26(aabbccc)        = " + findFirst_arr26("aabbccc"));
        System.out.println("findFirst_map(aabbccc)          = " + findFirst_map("aabbccc"));

        doTestsPass();
    }

    /*
     * ================ [Efficient Approach] Using LinkedHashMap or order based frequency – O(2*n) Time and O(n) Space =====================
     *
     *  First traversal string and record order-based frequency of each character in LinkedHashMap
     *  Second traversal this frequency map to get the first entry whose value is 1
     *
     *
     *  Time Complexity     = O(2*n) = O(n)
     *  Space complexity    = O(n)
     */
    private static char findFirst_map(String input) {

        char firstChar = '$';
        // use of LinkedHashMap instead of HashMap is important,
        // to maintain the insertion order for the First Unique(non-repeated) Character
        Map<Character, Integer> freqMap = new LinkedHashMap<>();

        for (char currChar : input.toCharArray()) {
            freqMap.put(currChar, freqMap.getOrDefault(currChar, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                firstChar = entry.getKey();
                break;
            }
        }
        // if the freMap is of type HashMap rather LinkedHashMap
        // then need to traverse the string from start, check for its character for 1 frequency
        return firstChar;
    }

    /*
     * ================ [Efficient Approach 1] Using Frequency Array (Two Traversal) – O(2*n) Time and O(MAX_CHAR) Space =====================
     *
     * using an int[26] to store the frequency of each char
     *
     *  The efficient approach is to either use an array of size MAX_CHAR to store the frequencies of each character. Traverse the input string twice:
     *  -   First traversal is to count the frequency of each character.
     *  -   Second traversal to find the first character in string with a frequency of one.
     *
     *
     *  Time Complexity     = O(2*n) = O(n)
     *  Space complexity    = O(26)
     */
    private static char findFirst_arr26(String s) {
        final int MAX_CHAR = 26;
        int[] freq = new int[MAX_CHAR];

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            freq[index]++;
        }
        // Find the first character with frequency 1
        for (char c : s.toCharArray()) {
            if (freq[c - 'a'] == 1) {
                return c;
            }
        }
        return '$';
    }

    /**
     * Boolean doTestsPass()
     * Returns true if all tests pass. Otherwise, returns false.
     */
    private static void doTestsPass() {
        // feel free to make testing more elegant
        String[] inputs = {"apple", "racecars", "ababdc", "simplest"};
        char[] outputs = {'a', 'e', 'd', 'i'};

        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {
            result = result && findFirst_map(inputs[i]) == outputs[i];
            if (!result)
                System.out.println("Test failed for: " + inputs[i]);
            else
                System.out.println("Test passed for: " + inputs[i]);
        }
    }

}
