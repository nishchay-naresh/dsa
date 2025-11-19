package com.nishchay.ds.array.a06subarray;

import java.util.Arrays;

/*
*
*
*  https://leetcode.com/problems/range-sum-query-immutable/description/
*  https://youtu.be/yuws7YK0Yng?si=6FhAb-NN2s2SScig
* */
public class PrefixSum {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 4, 20, 3, 10, 5};
        prefixSum(arr);
    }

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

}
