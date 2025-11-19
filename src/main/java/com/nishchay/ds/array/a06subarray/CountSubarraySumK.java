package com.nishchay.ds.array.a06subarray;

/*
 *  ======================= Count Subarrays having Sum K ====================
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
 *			Input : arr[] = {1,2,3,-3,1,1,1,4,2,-3}; k=3
 *			ans :	total subareas are - 8
 *					{1,2}
 *					{1,2,3,-3}
 *					{3}
 *					{2,3,-3,1}
 *					{3, -3, 1, 1, 1}
 *					{1, 1, 1}
 *					{4, 2, -3}
 *					{-3, 1, 1, 1, 4, 2, -3}
 *
 * https://takeuforward.org/arrays/count-subarray-sum-equals-k/
 * https://www.geeksforgeeks.org/dsa/number-subarrays-sum-exactly-equal-k/
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 *
 * https://youtu.be/xvNwoz-ufXA?si=kYknp_6H49L10BUg
 * https://youtu.be/KDH4mhFVvHw?si=PTzL-Iokgq-sM9mA
 * */

import java.util.HashMap;
import java.util.Map;

public class CountSubarraySumK {

    public static void main(String[] args) {

        int[] arr = {10, 2, -2, -20, 10};
        int k = -10;
        System.out.println(countSubarrays_3loop(arr, k));

        arr = new int[]{1, 2, 3, -3, 1, 1, 1, 4, 2, -3};
        k = 3;
        System.out.println(countSubarrays_2loop(arr, k));

        System.out.println(countSubarrays_2pointers(arr, k)); // not working

        System.out.println(countSubarrays_prefixSumHashing(arr, k));

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
    private static int countSubarrays_3loop(int[] arr, int K) {
        int n = arr.length;
        int cnt = 0;

        for (int i = 0 ; i < n; i++) {
            for (int j = i; j < n; j++) {
                // calculate the sum of subarray [i...j]
                int sum = 0;
                for (int k = i; k <= j; k++)
                    sum += arr[k];

                // Increase the count if sum == K
                if (sum == K)
                    cnt++;
            }
        }
        return cnt;
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
     * */
    private static int countSubarrays_2loop(int[] arr, int k) {
        int n = arr.length;
        int cnt = 0;

        for (int i = 0 ; i < n; i++) { // starting index i
            int sum = 0;
            for (int j = i; j < n; j++) { // ending index j
                // calculate the sum of subarray [i...j]
                // sum of [i..j-1] + arr[j]
                sum = sum + arr[j];

                // Increase the count if sum == k:
                if (sum == k)
                    cnt++;
            }
        }
        return cnt;
    }


    /*
     *  ================ [Optimize/Expected Approach] Using 2 pointer approach - O(n) Time =====================
     *
     * We are using two pointers i.e. left and right.
     *  The left pointer denotes the starting index of the subarray and the right pointer denotes the ending index.
     *    MOVE - We will move the right pointer in arr forward direction every time adding the element i.e. arr[right] to the sum.
     *    SHRINK -  But when the sum of the subarray crosses k, we will move the left pointer in the forward direction as well to shrink the size of the subarray as well as to decrease the sum.
     * Thus, we will consider the length of the subarray whenever the sum becomes equal to k.
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     * this approach is not working here
     * */
    static int countSubarrays_2pointers(int[] arr, int k) {
        int n = arr.length;

        int left = 0, right = 0;
        long sum = arr[0];
        int cnt = 0;
        while (right < n) {
            // if sum > k, reduce the subarray from left
            // until sum becomes less or equal to k:
            while (left <= right && sum > k) {
                sum = sum - arr[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                cnt++;
            }

            // Move forward thw right pointer:
            right++;
            if (right < n)
                sum = sum + arr[right];
        }
        return cnt;
    }

    static int countSubarrays_prefixSumHashing(int[] arr, int k) {

        // HashMap to store prefix sums frequencies
        Map<Integer, Integer> prefixSums = new HashMap<>();
        int count = 0;
        int currSum = 0;

        for (int i = 0; i < arr.length; i++) {
            // Add the current element to sum so far.
            currSum += arr[i];

            // If currSum is equal to a desired sum
            // then a new subarray is found.
            if (currSum == k)
                count++;

            // Check if the difference exists in the prefixSums map.
            if (prefixSums.containsKey(currSum - k))
                count += prefixSums.get(currSum - k);

            // Add currSum to the set of prefix sums.
            prefixSums.put(currSum, prefixSums.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }
}
