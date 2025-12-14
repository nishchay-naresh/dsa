package com.nishchay.algo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 *
 * ==========================================================================
 * 	Two pointer & Sliding window based questions
 * 	1.	Constant window (Fixed Size)
 *  2.  Sliding Window Technique (Variable Size)
 *
 * */
public class ATwoPointersWithSlidingWindow {

    public static void main(String[] args) {

        int[] arr = {1, 4, 2, 10, 2, 5, 1, 0, 12};
        int k = 3;
        System.out.println("Maximum sum of subarray of size " + k + ": " + fixedSlidingWindow_maxSubarraySum(arr, k)); // Output: 17
        System.out.println("Maximum sum of subarray of size " + k + ": " + maxSubarraySum(arr, k)); // Output: 17
        System.out.println("-----------------------------------------------");
        String s = "araaci";
        k = 2;
        System.out.println("Longest substring with at most " + k + " distinct characters: " + variableSlidingWindow_longestSubstringWithKDistinct(s, k)); // Output: 4
    }

    /*
     * ================ [Naive Approach] Fixed-Size Window Brute Force - O(n * k) time and O(1) space  =====================
     *
     *  The idea is to iterate over all possible subarrays of size k and calculate their sums one by one.
     *  For each subarray, compare its sum with the current maximum and update accordingly.
     *
     *     Outer loop: i = 0 to n-k
     *          Inner loop: j = i to i+k
     *              compute sum of current subarray
     *              update maxSum with currSum
     *
     *  Time Complexity     : O(n * k)
     *  Space complexity    : O(1)
     *  check all subarrays of size k
     */
    private static int maxSubarraySum(int[] arr, int k) {
        int n = arr.length;
        int maxSum = 0;

        for (int i = 0; i <= n - k; i++) {
            int windowSum = 0;
            for (int j = i; j < i + k; j++) {
                windowSum = windowSum + arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    /*
     *
     * ============ Sliding Window Technique (Fixed Size) =================
     * This structure is used for problems involving a fixed-size subarray or substring,
     *  like finding the maximum sum of k consecutive elements.
     *
     * */
    private static int fixedSlidingWindow_maxSubarraySum(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException("Invalid input array or window size.");
        }

        int currentWindowSum = 0;
        // Calculate sum of the first window
        for (int i = 0; i < k; i++) {
            currentWindowSum += arr[i];
        }

        int maxSum = currentWindowSum;

        // Slide the window
        for (int i = k; i < arr.length; i++) {
            currentWindowSum = currentWindowSum + arr[i] - arr[i - k]; // Add new element, remove old
            maxSum = Math.max(maxSum, currentWindowSum); // Update max sum
        }
        return maxSum;

    }


    /*
     *
     * ============ Sliding Window Technique (Variable Size) =================
     * This structure is used when the window size needs to adjust dynamically based on a condition,
     *  such as finding the longest substring with at most k distinct characters.
     *
     * */
    private static int variableSlidingWindow_longestSubstringWithKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k == 0) {
            return 0;
        }

        int left = 0;
        int maxLength = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar, 0) + 1);

            // Shrink the window if condition is violated (too many distinct characters)
            while (charFrequency.size() > k) {
                char leftChar = s.charAt(left);
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);
                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1); // Update max length
        }
        return maxLength;
    }


}
