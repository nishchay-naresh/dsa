package com.nishchay.algo.slidingwindow.freq;

import java.util.Arrays;
import java.util.HashMap;

/*
 *	====================================== Check if main String have the anagrams of other other =================================================
 *
 * There are two string largerString, smallerString
 * check whether a large String (consider only contiguous chars) having anagram of smaller String - return true/false
 * Example:
 *              String smallerString = "aabc";
 *              String largerString = "aabxxaabxxcbaa";         - true
 *
 *              String smallerString = "aabc";
 *              String largerString = "aabxxaabxxcbay";         - false
 *
 *              String smallerString = "abc";
 *              String largerString = "xxxcbay";                - true
 *
 *
 *	====================================== 567. Permutation in String =================================================
 *	Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *	In other words, returns true if one of s1's permutations is the substring of s2.
 *
 *
 *	Example1:
 *			Input: s1 = "ab", s2 = "eidbaooo"
 *			Output: true
 *			Explanation: s2 contains one permutation of s1 ("ba").
 *	Example2:
 *			Input: s1 = "ab", s2 = "eidboaoo"
 *			Output: false
 *
 * So this is NOT about generating permutations ❌
 * It’s about matching character counts ✅
 *
 * https://www.geeksforgeeks.org/dsa/check-if-a-string-contains-an-anagram-of-another-string-as-its-substring/
 * https://leetcode.com/problems/permutation-in-string/description/
 *
 *
 * Have been asked in Altimetrik
 * */
public class FindAnagramSubString {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println("AnagramSubString for s1 = "+ s1 + ", s2 - " + s2 + " = " + checkAnagramSubString(s1, s2));

        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println("AnagramSubString for s1 = "+ s1 + ", s2 - " + s2 + " = " + checkAnagramSubString(s1, s2));

        s1 = "aabc";
        s2 = "aabxxaabxxcbaa";
        System.out.println("AnagramSubString for s1 = "+ s1 + ", s2 - " + s2 + " = " + checkAnagramSubString(s1, s2));
        System.out.println("AnagramSubString for s1 = "+ s1 + ", s2 - " + s2 + " = " + checkAnagramSubStringMap(s1, s2));

        s1 = "aabc";
        s2 = "aabxxaabxxcbay";
        System.out.println("AnagramSubString for s1 = "+ s1 + ", s2 - " + s2 + " = " + checkAnagramSubString(s1, s2));
        System.out.println("AnagramSubString for s1 = "+ s1 + ", s2 - " + s2 + " = " + checkAnagramSubStringMap(s1, s2));

        s1 = "abc";
        s2 = "xxxcbay";
        System.out.println("AnagramSubString for s1 = "+ s1 + ", s2 - " + s2 + " = " + checkAnagramSubString(s1, s2));
    }

    /*
     * ================ [Expected Approach] Sliding Window + Freq - O(n2) Time and O(26) Space  =====================
     *
     * 	-------- Intuition -------------
     *
     * 	Instead of trying all permutations (that are factorial ❌), think like this:
     * 	If two strings are permutations:
     *
     * 	Their character frequency must be identical means they are anagram of each other
     * 	Example:
     * 			"abd" → {a:1, b:1, d:1}
     * 			"dab" → {a:1, b:1, d:1}
     *
     * 	So this problem reduces to:
     * 	Is there a window of size s1.length() in s2 whose frequency matches s1?
     *
     * This screams: Sliding Window + Frequency Array
     *
     * 	Why Sliding Window?
     * 	We only care about substrings of fixed size: windowSize = s1.length()
     *
     *  Time Complexity     : O(26 * len(S2))
     *  Space complexity    : O(26)
     *
     * Time Complexity: O(26 * len(S2))
     *  Auxiliary Space: O(26)
     */
    private static boolean checkAnagramSubString(String s1, String s2) {

        if (s1.length() > s2.length())
            return false;

        int[] freqS1 = new int[26];
        int[] freqS2WindowWise = new int[26];

        // Processing the first window for both of the strings, fixing size of window - size of s2
        for (int i = 0; i < s1.length(); i++) {
            freqS1[s1.charAt(i) - 'a']++;
            freqS2WindowWise[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(freqS1, freqS2WindowWise)) {
            return true;
        }

        // Subsequent windows - Only for s1, compare the freq of window and keep sliding this window by 1
        int left = 0;
        for (int right = s1.length(); right < s2.length(); right++) {

            // add right char freq to freqS2WindowWise
            freqS2WindowWise[s2.charAt(right) - 'a']++;
            // subtract left char freq from freqS2WindowWise
            freqS2WindowWise[s2.charAt(left) - 'a']--;
            left++;

            if (Arrays.equals(freqS1, freqS2WindowWise)) {
                return true;
            }
        }
        return false;
    }


    private static boolean checkAnagramSubStringMap(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        HashMap<Character, Integer> s1Freq = new HashMap<>();
        HashMap<Character, Integer> s2Freq = new HashMap<>();

        // Processing the first window for both of the strings
        for (int i = 0; i < s1.length(); i++) {
            s1Freq.put(s1.charAt(i), s1Freq.getOrDefault(s1.charAt(i), 0) + 1);
            s2Freq.put(s2.charAt(i), s2Freq.getOrDefault(s2.charAt(i), 0) + 1);
        }
        if (s1Freq.equals(s2Freq)) {
            return true;
        }

        // Sliding Window - now processing the freq in a window of s1 size and keep sliding this window by 1
        int left = 0;
        for (int right = s1.length(); right < s2.length(); right++) {

            // add right char freq to s2Freq
            char rightChar = s2.charAt(right);
            s2Freq.put(rightChar, s2Freq.getOrDefault(rightChar, 0) + 1);

            // subtract left char freq from freqS2WindowWise
            char leftChar = s2.charAt(left);
            s2Freq.put(leftChar, s2Freq.get(leftChar) - 1);
            if (s2Freq.get(leftChar) == 0) {
                s2Freq.remove(leftChar);
            }
            left++;

            if (s1Freq.equals(s2Freq)) {
                return true;
            }
        }
        return false;
    }
}
