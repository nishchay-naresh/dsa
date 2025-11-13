package com.nishchay.ds.array.basic.subArray;

/*
 *  ======================= Longest Subarray With Sum K ====================
 *
 *  Given an array arr[] consisting of both positive and negative integers, find the length of the longest subarray whose elements sum is zero.
 *
 * Examples:
 *			Input: arr[] = [15, -2, 2, -8, 1, 7, 10]
 *			Output: 5
 *			Explanation: The longest subarray with sum equals to 0 is [-2, 2, -8, 1, 7].
 *
 *			Input: arr[] = [1, 2, 3]
 *			Output: 0
 *			Explanation: There is no subarray with 0 sum.
 *
 *			Input:  arr[] = [1, 0, 3]
 *			Output:  1
 *			Explanation: The longest sub-array with sum equal to 0 is [0].
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-the-largest-subarray-with-0-sum/
 *
 * */


import static com.nishchay.ds.array.basic.subArray.LongestSubarraySumK.getLongestSubarray_2pointers;

public class LongestSubarraySum0 {

    public static void main(String[] args) {

        int[] arr = {15, -2, 2, -8, 1, 7, 10};
        System.out.println(getLongestSubarray_2pointers(arr, 0));

        arr = new int[]{1, 2, 3};
        System.out.println(getLongestSubarray_2pointers(arr, 0));

        arr = new int[]{1, 0, 3};
        System.out.println(getLongestSubarray_2pointers(arr, 0));

    }


}






