package com.nishchay.ds.array.basic.subArray;

/*
 *  ======================= Longest Subarray With Sum K ====================
 *
 *  Given an array arr[] of positive and negative integers,
 *  The goal is to find the number of subarrays having a sum exactly equal to a given number k.
 *
 * Examples:
 *			Input : arr[] = [10, 2, -2, -20, 10], k = -10
 *			Output : 3
 *			Explanation: Subarrays: arr[0...3], arr[1...4], arr[3...4] have sum equal to -10.
 *
 *			Input : arr[] = [9, 4, 20, 3, 10, 5], k = 33
 *			Output : 2
 *			Explanation: Subarrays: arr[0...2], arr[2...4] have sum equal to 33.
 *
 *			Input : arr[] = [1, 3, 5], k = 2
 *			Output : 0
 *			Explanation: No subarrays with 0 sum.

 *
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 * https://www.geeksforgeeks.org/dsa/number-subarrays-sum-exactly-equal-k/
 *
 * */

public class CountSubarraySumK {

    public static void main(String[] args) {

        int[] arr = {10, 2, -2, -20, 10};
        int k = -10;
        System.out.println(cntSubarrays(arr, k));

    }

    /*
     * ================ [Naive/Bruteforce Approach] Iterative Approach - O(n^2) Time and O(1) Space =====================
     *
     *  The idea is to check the sum of all the subarrays and count them if a subarray sum is equal to k.
     *  return the count
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    public static int cntSubarrays(int[] arr, int k) {

        // for maintaining the count of
        // subarrays whose sum equal to k
        int count = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int currSum = 0;

            // subarray from i to each j -> arr[i....j]
            for (int j = i; j < n; j++) {
                currSum += arr[j];

                // increment count if the currSum equals k
                if (currSum == k) {
                    count++;
                }
            }
        }

        return count;
    }
}
