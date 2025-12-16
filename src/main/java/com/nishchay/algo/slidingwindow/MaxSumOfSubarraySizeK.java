package com.nishchay.algo.slidingwindow;


/*
 *  ======================= Maximum (or Minimum) sum of a subarray of size k / Constant Window ====================
 *
 * Given an array of integers arr[] and an integer k, find the maximum possible sum among all contiguous subarrays of size exactly k.
 * A subarray is a sequence of consecutive elements from the original array. Return the maximum sum that can be obtained from any such subarray of length k.
 *
 * Examples:
 * 				Input  : arr[] = [100, 200, 300, 400],  k = 2
 * 				Output : 700
 * 				Explanation: We get maximum sum by adding subarray [300,400] of size 2
 *
 * 				Input  : arr[] = [1, 4, 2, 10, 23, 3, 1, 0, 20], k = 4
 * 				Output : 39
 * 				Explanation: We get maximum sum by adding subarray [4, 2, 10, 23] of size 4.
 *
 * 				Input  : arr[] = [2, 3], k = 1
 * 				Output : 3
 * 				Explanation: The subarrays of size 1 are [2] and [3]. The maximum sum is 3.
 *
 *				Input:arr= [-1, 2, 3, 3, 4, 5, -1], K= 4
 *				Output:15
 *				Explanation:  For the given array [-1, 2, 3, 3, 4, 5, -1] and K=4,
 *				the maximum sum that can be obtained by picking up 4 elements consecutively is achieved by selecting elements [3, 3, 4, 5], resulting in a sum of 15.
 *
 *				Example 2:
 *				Input:arr= [2, -5, 6, 1, -2, 4, 3], K= 3
 *				Output: 11
 *				Explanation: For the array [2, -5, 6, 1, -2, 4, 3] and K=3,
 *				the maximum sum that can be obtained by picking up 3 elements consecutively is achieved by selecting elements [6, 1, -2], resulting in a sum of 11.
 *
 *
 * https://takeuforward.org/sliding-window/constant-window
 * https://www.geeksforgeeks.org/dsa/find-maximum-minimum-sum-subarray-size-k/
 * */

public class MaxSumOfSubarraySizeK {

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("maxSubarraySum(arr, k) - " + maxSubarraySum(arr, k));
        System.out.println("maxSubarraySumPrefixSum(arr, k) - " + maxSubarraySumPrefixSum(arr, k));
        System.out.println("maxSubarraySumSlidingWindow(arr, k) - " + maxSubarraySumSlidingWindow(arr, k));
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
     * ================ [Better Approach - 1] Using Prefix Sum - O(n) Time and O(n) Space  =====================
     *
     *  The idea is to precompute the prefix sum array where each element at index i stores the sum of elements from index 0 to i-1.
     *  Using this, we can compute the sum of any subarray in constant time O(1) using the difference of two prefix values.
     *  This eliminates the need to iterate over each subarray element repeatedly.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int maxSubarraySumPrefixSum(int[] arr, int k) {
        int n = arr.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        int maxSum = 0;

        //Slide the window - compute a sum of each subarray of size k using prefixSum array
        for (int i = 0; i < n - k; i++) {
            int j = i + k - 1;
            int currSum = prefixSum[j + 1] - prefixSum[i];
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    /*
     * ================ 2 pointer and sliding window   =====================
     *
     * Algorithm
     * 	Initialise variables to keep track
     *		the left pointer of the sliding window, the right pointer of the sliding window,
     *		the current sum and the maximum sum.
     * 	Calculate the sum of the first K elements of the array and set it as the initial value of maximum sum.
     * 		Slide the Window and Update Maximum Sum: While the right pointer is less than the size of the array.
     * 		Slide the window and update the window sum by removing the leftmost elements and adding the next element to the right.
     * 		Update the maximum sum with the maximumSum and currentWindowSum
     * 		Move the left and right pointer to the right by one position.
     * 	After sliding the window through the entire array, return the maximum sum obtained.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * ==========================================================================
     * 	Two pointer & Sliding window based questions
     * 	1.	Constant window
     * 	2.	Longest substring / subarray where <condition>
     * 	3.	No of subarray where <condition>
     * 	4. Shortest / Maximum window <condition>
     *
     *
     */
    private static int maxSubarraySumSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        if (n < k)
            return -1;

        // compute a sum of the first window (0 to k-1)
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum = windowSum + arr[i];
        }

        int maxSum = windowSum;
        int left = 0;
        int right = k - 1;

        // slide the window (0 to n-k-1)
        while (right < n - 1) {
            windowSum = windowSum - arr[left];
            left++;
            right++;
            windowSum = windowSum + arr[right];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

}
