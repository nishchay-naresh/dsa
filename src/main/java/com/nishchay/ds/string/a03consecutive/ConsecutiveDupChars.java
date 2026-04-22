package com.nishchay.ds.string.a03consecutive;

/*
 *  ======================= Largest substring with same Characters ====================
 *
 * Given a string s of size N. The task is to find the largest substring which consists of the same characters
 *
 * Examples:
 *				Input : s = "abcdddddeff"
 *				Output : 5
 *				Substring is "ddddd"
 *
 *				Input : s = aabceebeee
 *				Output : 3
 *
 * https://www.geeksforgeeks.org/dsa/largest-substring-with-same-characters/
 * https://leetcode.com/problems/consecutive-characters/description/
 * */
public class ConsecutiveDupChars {

    public static void main(String[] args) {
        System.out.println(longestSameCharSubstring("abcdddddeff"));    // 5
        System.out.println(longestSameCharSubstring("aabceebeee"));     // 3
        System.out.println(longestSameCharSubstring("apple"));          // 2
    }

    /*
     * ================ [Approach] Traverse through the string from left to right  =====================
     *
     * The key word is substring with same characters → contiguous block, not scattered.
     *
     * ------------ Intuition ----------
     *	You don’t need any complex DS here.
     *	Just scan linearly and track:
     *		current streak length (currCount)
     *		max streak seen so far (maxCount)
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int longestSameCharSubstring(String s) {

        if (s == null || s.isEmpty()){
            return 0;
        }

        int maxCount = 1;
        int currCount = 1;

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) == s.charAt(i - 1)) {
                currCount++;
            } else {
                currCount = 1; // reset
            }
            maxCount = Math.max(maxCount, currCount);
        }
        return maxCount;
    }

}


