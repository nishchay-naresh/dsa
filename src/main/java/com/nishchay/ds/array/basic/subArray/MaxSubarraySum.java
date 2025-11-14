package com.nishchay.ds.array.basic.subArray;

/*
 *  ======================= Kadane's Algorithm | MSS Maximum Subarray Sum | Finding and Printing ====================
 *
 *  Given an integer array arr[], find the subarray (containing at least one element) which has the maximum possible sum, and return that sum.
 *  Note: A subarray is a continuous part of an array.
 *
 * Examples:
 * 			Input: arr[] = [2, 3, -8, 7, -1, 2, 3]
 * 			Output: 11
 * 			Explanation: The subarray [7, -1, 2, 3] has the largest sum 11.
 *
 * 			Input: arr[] = [-2, -4]
 * 			Output: -2
 * 			Explanation: The subarray [-2] has the largest sum -2.
 *
 * 			Input: arr[] = [5, 4, 1, 7, 8]
 * 			Output: 25
 * 			Explanation: The subarray [5, 4, 1, 7, 8] has the largest sum 25.
 *
 * https://www.geeksforgeeks.org/dsa/largest-sum-contiguous-subarray/
 * https://leetcode.com/problems/maximum-subarray/description/
 * https://youtu.be/9IZYqostl2M?si=O2Ql8RBYo6enmPC9
 *
 * */

public class MaxSubarraySum {

    public static void main(String[] args) {
        int[] arr = {2, 3, -8, 7, -1, 2, 3};
        System.out.println(maxSubarraySum_3loop(arr));
        System.out.println(maxSubarraySum_2loop(arr));
    }


    /*
     * ================ [Naive/Bruteforce Approach] Iterative Approach - O(n^3) Time and O(1) Space =====================
     *
     *  The idea is to check the sum of all the subarrays and count them if a subarray sum is equal to k.
     *  return the count
     *
     *  Time Complexity     : O(n^3)
     *  Space complexity    : O(1)
     */
    private static int maxSubarraySum_3loop(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        for (int start = 0 ; start < n; start++) {
            for (int end = start; end < n; end++) {
                // calculate the currSum of subarray [start...end]
                int currSum = 0;
                for (int k = start; k <= end; k++){
                    currSum += arr[k];
                }
                maxSum = Math.max(currSum, maxSum);
            }
        }
        return maxSum;
    }

    /*
     *  ================ [Better Approach] Removing third loop - O(n^2) Time and O(1) Space  =====================
     *
     *  We can remove the third loop. Rather iterating from stating to end for all subarrays, we can calculate the sum by
     *  previous sum so for + add the current element the augmented one)
     *
     *  If we carefully observe, we can notice that to get the sum of the current subarray,
     *  we just need to add the current element(i.e. arr[j]) to the sum of the previous subarray i.e., arr[i….j-1].
     *
     *       Assume previous subarray = arr[i……j-1]
     *       current subarray = arr[i…..j]
     *       Sum of arr[i….j] = (sum of arr[i….j-1]) + arr[j]
     *
     * This is how we can remove the third loop(k-loop) and while moving j pointer, we can calculate the sum.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     */

    private static int maxSubarraySum_2loop(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0 ; i < n; i++) { // starting index i
            int currSum = 0;
            for (int j = i; j < n; j++) { // ending index j
                // calculate the currSum of subarray [i...j]
                // currSum of [i..j-1] + arr[j]
                currSum = currSum + arr[j];
                maxSum = Math.max(currSum, maxSum);
            }
        }
        return maxSum;
    }

    /*
     *  ================ [Optimize/Expected Approach] Kadane's Algorithm - O(n) Time =====================
     *
     * Intuition :
     *                  +ve  	    + 	    +ve     = +ve
     *                  -ve(small)  +   +ve(big)    = +ve
     *                  +ve(small)  +   -ve(big)    = -ve Kadane's Algorithm
     *
     * If a subarray sum is -ve, then adding it to another subarray will never give me a max sum.
     * So better discard that subarray and reset subarray sum to 0
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     *
     */
    private static int kadanesAlgorithm(int[] arr) {
        int n = arr.length;
        int maxSum = Long.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < n; i++) {

            currSum += arr[i];
            maxSum = Math.max(currSum, maxSum);

            // If currSum < 0: discard the currSum calculated
            if (currSum < 0) {
                currSum = 0;
            }
        }

        // To consider the currSum of the empty subarray
        // uncomment the following check:

        //if (maxSum < 0) maxSum = 0;

        return maxSum;
    }
}
