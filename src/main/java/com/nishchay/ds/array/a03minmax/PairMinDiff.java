package com.nishchay.ds.array.a03minmax;

import java.util.Arrays;

/*
 *======================= Find Pair With Min Diff ============================
 *
 * Given an unsorted array, find the minimum difference between any pair in the given array.
 * If there are less than 2 elements, then we need to return -1.
 *
 * 	Examples
 *				Input: {1, 5, 3, 19, 18, 25}
 *				Output: 1
 *				Explanation: Minimum difference is between 18 and 19
 *
 *				Input: {30, 5, 20, 9}
 *				Output: 4
 *				Explanation: Minimum difference is between 5 and 9
 *
 *				Input: {1, 19, -4, 31, 38, 25, 100}
 *				Output: 5
 *				Explanation: Minimum difference is between 1 and -4
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-minimum-difference-pair/
 *
 * Another approach could be, store them in hashMap<No,Index> - sort this entry based on no, then take first 2 entry
 * */
public class PairMinDiff {

    public static void main(String[] args) {
        findMinDiffPairEx();
        System.out.println("...................................................");
        minDiffPairEx();
    }

    private static void findMinDiffPairEx() {
        int[] arr;
        arr = new int[]{1, 5, 3, 19, 18, 25};
        System.out.println(Arrays.toString(arr) + ", findMinDiffPair(arr) = " + findMinDiffPair(arr));// 1

        arr = new int[]{30, 5, 20, 9};
        System.out.println(Arrays.toString(arr) + ", findMinDiffPair(arr) = " + findMinDiffPair(arr));// 4

        arr = new int[]{1, 19, -4, 31, 38, 25, 100};
        System.out.println(Arrays.toString(arr) + ", findMinDiffPair(arr) = " + findMinDiffPair(arr));// 5

        arr = new int[]{-2};
        System.out.println(Arrays.toString(arr) + ", findMinDiffPair(arr) = " + findMinDiffPair(arr));// -1
    }

    private static void minDiffPairEx() {
        int[] arr;
        arr = new int[]{1, 5, 3, 19, 18, 25};
        System.out.println(Arrays.toString(arr) + ", minDiffPair(arr) = " + minDiffPair(arr));// 1

        arr = new int[]{30, 5, 20, 9};
        System.out.println(Arrays.toString(arr) + ", minDiffPair(arr) = " + minDiffPair(arr));// 4

        arr = new int[]{1, 19, -4, 31, 38, 25, 100};
        System.out.println(Arrays.toString(arr) + ", minDiffPair(arr) = " + minDiffPair(arr));// 5

        arr = new int[]{-2};
        System.out.println(Arrays.toString(arr) + ", minDiffPair(arr) = " + minDiffPair(arr));// -1
    }

    /*
     * ================ [Naive Approach] - Nested Loops - O(n^2) Time and O(1) Space  =====================
     *
     * A simple solution is to use two loops two generate every pair of elements and compare them to get the minimum difference
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int findMinDiffPair(int[] arr){
        int n = arr.length;
        if (n < 2)
            return -1;

        int minDiff = Integer.MAX_VALUE;

        // Diff by comparing difference of all possible pairs in given array
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++){
                int diff = Math.abs(arr[i] - arr[j]);
                if (diff < minDiff) {
                    minDiff = diff;
                }
            }
        return minDiff;
    }


    /*
     * ================ [Improved Approach] - applying sorting - O(n log n) Time and O(1) Space  =====================
     *
     * The idea is to use sorting and compare every adjacent pair of the array :
     *      1.  Sort array in ascending order
     *      2.  Initialize difference as infinite
     *      3.  Compare all adjacent pairs in a sorted array and keep track of the minimum difference
     *
     *
     *  Time Complexity     : O(n log n)
     *  Space complexity    : O(1)
     */
    private static int minDiffPair(int[] arr){
        int n = arr.length;
        if (n < 2)
            return -1;

        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++){
            int diff = arr[i + 1] - arr[i];
            if (diff < minDiff)
                minDiff = diff;
        }
        return minDiff;
    }
}
