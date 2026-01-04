package com.nishchay.ds.array.a09subarray;

import java.util.Arrays;

/*
 *  To calculate the sum of a subarray from a prefix sum array, use the following formula:
 *       subarray_sum(l, r) = prefix_sum[r] - prefix_sum[l - 1]
 *
 *
 * https://www.geeksforgeeks.org/dsa/prefix-sum-array-implementation-applications-competitive-programming/
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 * https://youtu.be/yuws7YK0Yng?si=6FhAb-NN2s2SScig
 *
 * */
public class PrefixSum {

    public static void main(String[] args) {
        prefixSumEx();
        System.out.println(".......................................");
        rangeSumEx();
    }

    private static void prefixSumEx() {
        int[] arr = new int[]{2, 1, 5, 1, 3, 2};

        System.out.println("arr       = " + Arrays.toString(arr));
        subarraySum_2loop(arr);
        System.out.println("-------------------------------");
        prefixSum(arr);

        arr = new int[]{10, 20, 10, 5, 15};
        prefixSum(arr);
    }

    private static void subarraySum_2loop(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int currSum = 0;
            for (int j = 0; j <= i; j++) {
                currSum = currSum + arr[j];
            }
            System.out.print(currSum + ", ");
        }
        System.out.print("\n");
    }

    /*
     * ============================= Prefix Sum Implementation ======================================
     *
     * To get the subarray sum, we have used two nested loops,
     * To optimize this from n^2 to n, we will use a concept like prefix sum to get the subarray sum
     *
     *              Arr = [1, 2, 3, 4, 5 ], n=5
     *                     0, 1, 2, 3, 4
     *
     *      prefixSum[] =[ 1, 3, 6, 10, 15], sum till the current index (starting from 0 index)
     *                     0, 1, 2, 3, 4
     *      (i,j)= 2,3
     *          sumArraySum(i,j) = prefixSum[j] - prefixSum[i-1] or sumArraySum(i+1,j) = prefixSum[j] - prefixSum[i]
     *          sumArraySum(2,3) = prefixSum[3] - prefixSum[1] = 10 - 3 = 7
     *
     * The idea is to create an array prefixSum[] of size n, and for each index i in range 1 to n - 1,
     *        set prefixSum[i] = prefixSum[i - 1] + arr[i].
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(n)
     * */
    private static void prefixSum(int[] arr) {
        int n = arr.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        System.out.println("arr       = " + Arrays.toString(arr));
        System.out.println("prefixSum = " + Arrays.toString(prefixSum));
    }

    private static void rangeSumEx() {

        int[] arr = new int[]{2, 1, 5, 1, 3, 2};
        int n = arr.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        System.out.println("prefixSum         = " + Arrays.toString(prefixSum));
        System.out.println("sumRange(arr, 1, 3) = " + sumRange(prefixSum,1,3)); // 7
        System.out.println("sumRange(arr, 2, 5) = " + sumRange(prefixSum,2,5)); // 11
    }

    /*
     * ============================= Using Prefix Sum for sub-array sum ======================================
     * To calculate the sum of a subarray from a prefix sum array, use the following formula:
     *          subarray_sum(left, right) = prefix_sum[right] - prefix_sum[left - 1]
     *           subarray_sum (0, right) =  prefix_sum[right];
     *
     * Time Complexity: O(1)
     * Auxiliary Space: O(1)
     * */
    public static int sumRange(int[] prefixSum, int left, int right) {
        if (left >= 0 && right <= prefixSum.length - 1) {
            if (left == 0) {
                return prefixSum[right];
            }
            return prefixSum[right] - prefixSum[left - 1];
        }
        return -1;
    }
}
