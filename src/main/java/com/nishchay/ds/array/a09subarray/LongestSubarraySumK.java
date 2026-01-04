package com.nishchay.ds.array.a09subarray;

/*
 *  ======================= Longest Subarray With Sum <= K ====================
 *
 *  Given an array arr[] of size n containing integers,
 *  The task is to find the length of the longest subarray having sum equal to the given value k.
 *
 * Note: If there is no subarray with sum equal to k, return 0.
 *
 * Examples:
 *			Input: arr[] = [10, 5, 2, 7, 1, -10], k = 15
 *			Output: 6
 *			Explanation: Subarrays with sum = 15 are [5, 2, 7, 1], [10, 5] and [10, 5, 2, 7, 1, -10]. The length of the longest subarray with a sum of 15 is 6.
 *
 *			Input: arr[] = [-5, 8, -14, 2, 4, 12], k = -5
 *			Output: 5
 *			Explanation: Only subarray with sum = 15 is [-5, 8, -14, 2, 4] of length 5.
 *
 *			Input: arr[] = [10, -10, 20, 30], k = 5
 *			Output: 0
 *			Explanation: No subarray with sum = 5 is present in arr[].
 *
 *
 * https://www.geeksforgeeks.org/dsa/longest-sub-array-sum-k/
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 * https://www.youtube.com/watch?v=frf7qxiN2qU&list=PLgUwDviBIf0rENwdL0nEH0uGom9no0nyB&index=4
 *
 * The Same question can be asked for no of subarrays whose sum is equal to k
 *
 * */

import java.util.HashMap;
import java.util.Map;

public class LongestSubarraySumK {

    public static void main(String[] args) {

        int[] arr = {10, 5, 2, 7, 1, -10};
        int k = 15;
        System.out.println(longestSubarray(arr, k));
        System.out.println("----------------------------------------");

        arr = new int[]{1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        k = 3;
        System.out.println(getLongestSubarray(arr, k)); //3

        arr = new int[]{1, 2, 3, 1, 1, 1, 1, 3, 3};
        k = 6;
        System.out.println(getLongestSubarray_2pointers(arr, k)); //4
    }

    /*
     * ================ [Naive/Bruteforce Approach] Iterative Approach - O(n^2) Time and O(1) Space =====================
     *
     *  The idea is to check the sum of all the subarrays and return the length of the longest subarray having the sum k.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     */
    private static int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        int res = 0;

        for (int i = 0; i < n; i++) {

            // Sum of subarray from i to j
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + arr[j];

                // If subarray sum is equal to k
                if (sum == k) {
                    // find subarray length and update result
                    int subLen = j - i + 1;
                    res = Math.max(res, subLen);
                }
            }
        }
        return res;
    }

    /*
     *  ================ [Optimize/Expected Approach] Using Prefix Sum and hashing concept - O(n) Time and O(n) Space  =====================
     *
     * To get the subarray sum, we have used two nested loops,
     * To optimize this from n^2 to n, we will use a concept like prefix sum to get the subarray sum
     *
     *              arr = [1, 2, 3, 4, 5 ], n=5
     *                     0, 1, 2, 3, 4
     *
     *      prefixSum[] =[ 1, 3, 6, 10, 15], sum till the current index (starting from 0 index)
     *                     0, 1, 2, 3, 4
     *      (i,j)= 2,3
     *          sumArraySum(i,j) = prefixSum[j] - prefixSum[i-1] or sumArraySum(i+1,j) = prefixSum[j] - prefixSum[i]
     *          sumArraySum(2,3) = prefixSum[3] - prefixSum[1] = 10 - 3 = 7
     *
     * These 2 equations will help us to solve the problem:
     *  1.  sumArraySum(i,j) = ps[j] - ps[i-1]
     *  2.  k = ps[j] - ps[i-1]
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     * */
    private static int getLongestSubarray(int[] arr, long k) {
        int n = arr.length;
        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += arr[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }
        return maxLen;
    }

    /*
     *  ================ [Optimize/Expected Approach] Using 2 pointer approach - O(n) Time =====================
     *
     * We are using two pointers, i.e. left and right.
     *  The left pointer denotes the starting index of the subarray and the right pointer denotes the ending index.
     *    MOVE - We will move the right pointer in arr forward direction every time adding the element i.e. arr[right] to the sum.
     *    SHRINK -  But when the sum of the subarray crosses k, we will move the left pointer in the forward direction as well to shrink the size of the subarray as well as to decrease the sum.
     * Thus, we will consider the length of the subarray whenever the sum becomes equal to k.
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     * https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/
     * */
    static int getLongestSubarray_2pointers(int[] arr, long k) {
        int n = arr.length;

        int left = 0, right = 0;
        long sum = arr[0];
        int maxLen = 0;
        while (right < n) {
            // if sum > k, reduce the subarray from left
            // until sum becomes less or equal to k:
            while (left <= right && sum > k) {
                sum = sum - arr[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Move forward thw right pointer:
            right++;
            if (right < n)
                sum = sum + arr[right];
        }
        return maxLen;
    }
}






