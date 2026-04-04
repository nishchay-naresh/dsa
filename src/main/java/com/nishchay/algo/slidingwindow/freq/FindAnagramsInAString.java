package com.nishchay.algo.slidingwindow.freq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *	====================================== Find All Anagrams in a String =================================================
 *
 * Given two strings, String and pattern, determine if any substring of the String is an anagram of the pattern.
 * If there exists an/many anagram return their starting indexes
 *
 * Examples:
 *
 *				Input: s = "cbaebabacd", p = "abc"
 *				Output: [0,6]
 *				Explanation:
 *				The substring with start index = 0 is "cba", which is an anagram of "abc".
 *				The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 *				Input: s = "abab", p = "ab"
 *				Output: [0,1,2]
 *				Explanation:
 *				The substring with start index = 0 is "ab", which is an anagram of "ab".
 *				The substring with start index = 1 is "ba", which is an anagram of "ab".
 *				The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *				Input: s = "BACDGABCDA",  pat = "ABCD"
 *				Output: [0, 5, 6]
 *				Explanation: "BACD" is at 0, "ABCD" at 5 and "BCDA" at 6
 *
 *				Input: s = "AAABABAA", pat = "AABA"
 *				Output:  [0, 1, 4]
 *				Explanation: "AAAB" is at 0, "AABA" at 5 and "ABAA" at 6
 *
 * https://www.geeksforgeeks.org/dsa/anagram-substring-search-or-search-for-all-permutations-set-2/
 * https://takeuforward.org/data-structure/anagram-substring-search
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 *
 * ================================ Count Occurrences of Anagrams =================================================
 *	=============================== Find and count all Anagrams in a String ==========================================
 *
 * same questions can be asked for the count - simply need to return the ArrayList.size();
 *
 *
 *  https://www.geeksforgeeks.org/dsa/count-occurrences-of-anagrams/
 * */
public class FindAnagramsInAString {

    public static void main(String[] args) {

        String str = "tufuftfut";
        String pattern = "tuf";
        List<Integer> indexes = findAnagramsIndexes(str, pattern);  // [0, 3, 5, 6]
        if (indexes.isEmpty()) {
            System.out.println("There are No Anagrams of " + pattern + " in " + str);
        } else {
            System.out.println("starting Indices of Anagrams are " + indexes);
        }

        str = "cbaebabacd";
        pattern = "abc";
        System.out.println(findAnagramsIndexes(str, pattern)); // [0, 6]

        str = "abab";
        pattern = "ab";
        System.out.println(findAnagramsIndexes(str, pattern));  // [0,1,2]

        str = "bacdgabcda";
        pattern = "abcd";
        System.out.println(findAnagramsIndexes(str, pattern));  // [0, 5, 6]

        str = "aaababaa";
        pattern = "aaba";
        System.out.println(findAnagramsIndexes(str, pattern)); // [0, 1, 4]
    }

    /*
     * ================ [Expected Approach] Sliding Window + Freq - O(n) Time and O(26) Space  =====================
     *
     *	Use Sliding Window of fixed size - p.length()
     *	Steps:
     *	1. Build frequency array for p
     *	2. Maintain a sliding window in s, keep sliding it with a size 1
     *	3. Track frequency of window - keep updating the freq by adding and removing char
     *	4. Compare both arrays
     *	5. If both freq arrays match → record index
     *
     *
     *	-------------- Visualization ------------------
     *
     *	Example:
     *	s = c b a e b a b a c d
     *	p = a b c
     *
     *	Window size = 3
     *	[c b a] e b a b a c d  -> anagram -> index 0
     *	c [b a e] b a b a c d
     *	c b [a e b] a b a c d
     *	c b a [e b a] b a c d
     *	c b a e [b a b] a c d
     *	c b a e b [a b a] c d
     *	c b a e b a [b a c] d -> anagram -> index 6
     *
     *  Efficient because:
     *	Instead of recomputing frequency every time
     *	we only update 2 characters
     *		+1 → new char entering window
     *		-1 → char leaving a window
     *
     * Time Complexity: O(n), n - length(S)
     *  Auxiliary Space: O(26)
     */
    private static List<Integer> findAnagramsIndexes(String str, String pattern) {
        List<Integer> indexes = new ArrayList<>();

        if (str.length() < pattern.length()) {
            return indexes;
        }
        int k = pattern.length();
        int[] pCount = new int[26];
        int[] window = new int[26];

        // First window
        for (int i = 0; i < k; i++) {
            pCount[pattern.charAt(i) - 'a']++;
            window[str.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCount, window)) {
            indexes.add(0);
        }

        // subsequent windows
        for (int i = k; i < str.length(); i++) {
            window[str.charAt(i) - 'a']++;
            window[str.charAt(i - k) - 'a']--;
            if (Arrays.equals(pCount, window))  //If arrays are equal, add i-m+1 to the indexes
                indexes.add(i - k + 1);
        }
        return indexes;
    }

}
