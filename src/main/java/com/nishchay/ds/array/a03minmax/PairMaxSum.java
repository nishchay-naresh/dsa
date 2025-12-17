package com.nishchay.ds.array.a03minmax;


/*
 * ======================= Pair With Max Sum / Largest pair sum in an array ============================
 *
 *  Given an unsorted distinct integer array, find the largest pair sum in it.
 *  For example, the largest pair sum in {12, 34, 10, 6, 40} is 74.
 *  If there are less than 2 elements, then we need to return -1.
 *
 * Examples:
 *				Input :  arr[] =  {12, 34, 10, 6, 40},
 *				Output :  74
 *
 *				Input : arr[]  = {10, 10, 10},
 *				Output : 20
 *
 *				Input  arr[]  = {10},
 *				Output :  -1
 *
 *	            Input: arr[] = {8, 2, 14, 6, 3, 7, 1}
 *	            Output: 22
 *	            Explanation: pair = 14, 8
 *
 *	            Input: arr[] = {12, 34, 10, 6, 40}
 *	            Output: 74
 *	            Explanation: pair = 40, 34
 *
 *	            Input: arr[] = {-2, 3}
 *	            Output: 1
 *	            Explanation: pair = 3,-2
 *
 *	            Input: arr[] = {-2}
 *	            Output: -1
 *	            Explanation: less than 2 elements
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-the-largest-pair-sum-in-an-unsorted-array/
 * */

import java.util.Arrays;

public class PairMaxSum {

    public static void main(String[] args) {

        findMaxSumPairEx();
        System.out.println("...................................................");
        maxSumPairEx();
    }

    private static void findMaxSumPairEx() {
        int[] arr;
        arr = new int[]{12, 34, 10, 6, 40};
        System.out.println(Arrays.toString(arr) + ", findMaxSumPair(arr) = " + findMaxSumPair(arr));// 74

        arr = new int[]{10, 10, 10};
        System.out.println(Arrays.toString(arr) + ", findMaxSumPair(arr) = " + findMaxSumPair(arr)); // 20

        arr = new int[]{8, 2, 14, 6, 3, 7, 1};
        System.out.println(Arrays.toString(arr) + ", findMaxSumPair(arr) = " + findMaxSumPair(arr)); // 22

        arr = new int[]{-2, 3};
        System.out.println(Arrays.toString(arr) + ", findMaxSumPair(arr) = " + findMaxSumPair(arr)); // 1

        arr = new int[]{-2};
        System.out.println(Arrays.toString(arr) + ", findMaxSumPair(arr) = " + findMaxSumPair(arr)); // -1
    }

    private static void maxSumPairEx() {
        int[] arr;
        arr = new int[]{12, 34, 10, 6, 40};
        System.out.println(Arrays.toString(arr) + ", maxSumPair(arr) = " + maxSumPair(arr));// 74

        arr = new int[]{10, 10, 10};
        System.out.println(Arrays.toString(arr) + ", maxSumPair(arr) = " + maxSumPair(arr)); // 20

        arr = new int[]{8, 2, 14, 6, 3, 7, 1};
        System.out.println(Arrays.toString(arr) + ", maxSumPair(arr) = " + maxSumPair(arr)); // 22

        arr = new int[]{-2, 3};
        System.out.println(Arrays.toString(arr) + ", maxSumPair(arr) = " + maxSumPair(arr)); // 1

        arr = new int[]{-2};
        System.out.println(Arrays.toString(arr) + ", maxSumPair(arr) = " + maxSumPair(arr)); // -1
    }

    /*
     * ================ [Naive Approach] - Nested Loops - O(n^2) Time and O(1) Space  =====================
     *
     * The idea is to use two nested loops to iterate over all possible pairs of integers in the array,
     * compute their sum and keep track of the maximum sum encountered so far.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int findMaxSumPair(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return (maxSum == Integer.MIN_VALUE) ? -1 : maxSum;
    }

    /*
     * ================ [Expected Solution] - Largest two Elements - O(n) Time and O(1) Space =====================
     *
     * This problem mainly boils down to finding the largest and second-largest element in an array.
     * We can find the largest and second-largest in O(n) time by traversing the array once.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int maxSumPair(int[] arr) {

        int n = arr.length;
        if (n < 2)
            return -1;

        int largest, secondLargest;
        if (arr[0] > arr[1]) {
            largest = arr[0];
            secondLargest = arr[1];
        } else {
            largest = arr[1];
            secondLargest = arr[0];
        }

        // Traverse remaining array and find first and second largest element
        for (int i = 2; i < n; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return (largest + secondLargest);
    }

}
