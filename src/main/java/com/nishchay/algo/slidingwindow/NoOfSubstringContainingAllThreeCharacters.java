package com.nishchay.algo.slidingwindow;


/*
 *  ======================= Number of substring containing all three characters ====================
 *
 * Given a string s, consisting only of characters 'a', 'b', 'c'.
 * Find the number of substrings that contain at least one occurrence of all these characters 'a', 'b', 'c'.
 *
 * Examples:
 *				Input: s = "abcba"
 *				Output:  5
 *				Explanation: The substrings containing at least one occurrence of the characters 'a', 'b', 'c'
 *                            are "abc", "abcb", "abcba", "bcba", "cba".
 *
 *
 *				Input : s = "ccabcc"
 *				Output : 8
 *				Explanation : The substrings containing at least one occurrence of the characters 'a' , 'b' , 'c'
 *                            are "ccab" , "ccabc" , "ccabcc" , "cab" , "cabc" , "cabcc" , "abc" , "abcc".
 *
 *
 * https://takeuforward.org/data-structure/number-of-substring-containing-all-three-characters
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
 * */

public class NoOfSubstringContainingAllThreeCharacters {

    public static void main(String[] args) {

        String s = "abcabc";
        System.out.println(numberOfSubstrings(s)); // 10

        s = "ccabcc";
        System.out.println(numberOfSubstrings(s)); // 8
        System.out.println(numberOfSubstrings_slidingWindow(s)); // 8

    }

    /*
     * ================ [Naive Approach] Substrings Starting From Every Index - O(n) Time and O(3) Space  =====================
     *
     * The idea is to find all the substring out of given string, then check for each substring s having those 3 chars
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(3)
     * Function to count substrings containing at least one 'a', one 'b', and one 'c'
     */
    private static int numberOfSubstrings(String s) {

        int n = s.length();

        int count = 0;

        for (int i = 0; i < n; i++) {
            // Array to track the count of 'a', 'b', and 'c'
            int[] freq = new int[3];
            for (int j = i; j < n; j++) {
                // Update frequency for current character
                freq[s.charAt(j) - 'a'] = 1;

                // Check if all three characters are present
                if (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // improved solution
    // Function to count substrings containing at least one 'a', 'b', and 'c' using a sliding window
    private static int numberOfSubstrings_slidingWindow(String s) {
        // Frequency array for 'a', 'b', 'c'
        int[] freq = new int[3];

        // Left pointer for the sliding window
        int left = 0;

        // Result variable to store count of valid substrings
        int res = 0;

        // Traverse the string with right pointer
        for (int right = 0; right < s.length(); right++) {
            // Increment frequency of current character
            freq[s.charAt(right) - 'a']++;

            // Shrink the window from the left while all characters are present
            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                // Count substrings from current right to end
                res += (s.length() - right);

                // Move left pointer and update frequency
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return res;
    }
}

