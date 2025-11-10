package com.nishchay.ds.array.basic.minmax;

import java.util.Arrays;

public class SecondSmallestElement {


    public static void main(String[] args) {

        int[] arr = {7, 2, 1, 7, 4, 5};
        System.out.printf("\n%s array is having second smallest element = %d", Arrays.toString(arr), getSecondSmallest_1pass(arr));

        arr = new int[]{12, 35, 1, 10, 34, 1};
        System.out.printf("\n%s array is having second smallest element = %d", Arrays.toString(arr), getSecondSmallest_1pass(arr));

        arr = new int[]{10, 5, 10};
        System.out.printf("\n%s array is having second smallest element = %d", Arrays.toString(arr), getSecondSmallest_1pass(arr));

        arr = new int[]{10, 10, 10};
        System.out.printf("\n%s array is having second smallest element = %d", Arrays.toString(arr), getSecondSmallest_1pass(arr));

    }

    /*
     * ============ [Optimal Approach] One Pass Approach ===============
     *  Using the same logic of finding the smallest element in an array
     *      using this fact  : if the smallest is getting updated during the scan, its previous value become second smallest
     *
     *   Time Complexity  : n + n = 2n = O(n)
     *   Space Complexity : 1
     *
     * */
    private static int getSecondSmallest_1pass(int[] arr) {

        int length = arr.length;
        int smallest, secondSmallest;

        if (length < 2) {
            System.out.println("Invalid Input ");
            return -1;
        }
        smallest = arr[0];
        secondSmallest = Integer.MAX_VALUE;
        for (int i = 1; i < length - 1; i++) {
            // if current element is smaller than smallest then update both smallest and secondSmallest
            if (arr[i] < smallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            }
            // if arr[i] is in between smallest and secondSmallest then update secondSmallest
            else if (arr[i] < secondSmallest && arr[i] != smallest)
                secondSmallest = arr[i];
        }
        if (secondSmallest == Integer.MAX_VALUE) {
            return -1;
        }
        return secondSmallest;
    }
}
