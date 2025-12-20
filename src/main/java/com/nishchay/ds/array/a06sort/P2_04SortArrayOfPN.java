package com.nishchay.ds.array.a06sort;

import java.util.Arrays;

/*
 * Rearrange Positive & Negative Values
 * Given an array a[] consisting of +ve, -ve nos P,N
 * Write a method to rearrange all negative integers to the left of the middle element and all positive integers to the right
 *
 * Original array = [2, 4, -6, 8, -5, -10, 6, -11, 3, 9]
 *   Sorted array = [-6, -5, -10, -11, 4, 2, 6, 8, 3, 9]
 *
 * */
public class P2_04SortArrayOfPN {

    public static void main(String[] args) {

        int[] arr = {2, 4, -6, 8, -5, -10, 6, -11, 3, 9};

        System.out.println("Original array = " + Arrays.toString(arr));
        sortPN(arr);
        System.out.println("  Sorted array = " + Arrays.toString(arr));

        System.out.println("Original array = " + Arrays.toString(arr));
        segregateNegativePositive(arr);
        System.out.println("  Sorted array = " + Arrays.toString(arr));
    }

    /*
     *	Complexity Analysis:
     *	Time Complexity: O(n).
     *	Space Complexity: O(1).
     *	As no extra space is required.
     * */
    private static void sortPN(int[] arr) {
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {   // if negative number found
                if (i != left) {
                    // swapping with leftmost positive
                    swap(arr, left, i);
                }
                left++;
            }
        }
    }


    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    /*
     * ================ [Expected  Approach] Use two pointer approach  =====================
     *
     * Maintain two indexes. Initialize the first index left as 0 and the second index right as n-1.
     *	Do following while left < right
     *	    a) Keep incrementing index left while there are negative at it
     *	    b) Keep decrementing index right while there are positive at it
     *	    c) If left < right then exchange arr[left] and arr[right]
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *  No fo array scan    : 1
     *
     * */
    private static void segregateNegativePositive(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (arr[left] < 0 && left < right) // negative
                left++;
            while (arr[right] > 0 && left < right) // positive
                right--;
            if (left < right) {
                // swipe left and right
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
                left++;
                right--;
            }
        }
    }


}
/*
 * Original array = [2, 4, -6, 8, -5, -10, 6, -11, 3, 9]
 *   Sorted array = [-6, -5, -10, -11, 4, 2, 6, 8, 3, 9]
 *
 * */
