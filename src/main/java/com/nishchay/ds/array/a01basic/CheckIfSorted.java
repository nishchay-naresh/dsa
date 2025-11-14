package com.nishchay.ds.array.a01basic;


/*
 *  ======================= Check if an Array is Sorted ====================
 *
 * Given an array arr[], check if it is sorted in ascending order or not.
 * Equal values are allowed in an array, and two consecutive equal values are considered sorted.
 *
 * Examples:
 * 	    Input: arr[] = [10, 20, 30, 40, 50]
 * 	    Output: true
 * 	    Explanation: The given array is sorted.
 *
 * 	    Input: arr[] = [90, 80, 100, 70, 40, 30]
 *  	Output: false
 * 	    Explanation: The given array is not sorted.
 *
 * 	    Input: arr[] = [1, 2, 2, 3, 3, 5]
 *  	Output: true
 * 	    Explanation: The given array is not sorted.
 *
 *      Input: arr[] = [1, 2, 1, 3, 4]
 *  	Output: true
 * 	    Explanation: The given array is not sorted.
 *
 *
 * https://www.geeksforgeeks.org/dsa/program-check-array-sorted-not-iterative-recursive/
 *
 * */
import java.util.Arrays;

public class CheckIfSorted {

    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40, 50};
        System.out.printf("\n%s array isSorted = %b", Arrays.toString(arr), isSorted(arr));

        arr = new int[]{90, 80, 100, 70, 40, 30};
        System.out.printf("\n%s array isSorted = %b", Arrays.toString(arr), isSorted(arr));

        arr = new int[]{1, 2, 2, 3, 3, 5};
        System.out.printf("\n%s array isSorted = %b", Arrays.toString(arr), isSorted(arr));

        arr = new int[]{1, 2, 1, 3, 4};
        System.out.printf("\n%s array isSorted = %b", Arrays.toString(arr), isSorted(arr));

        arr = new int[]{100};
        System.out.printf("\n%s array isSorted = %b", Arrays.toString(arr), isSorted(arr));

        arr = new int[]{};
        System.out.printf("\n%s array isSorted = %b", Arrays.toString(arr), isSorted(arr));
    }

    /*
     * Sorted: Each element left to right, must be smaller or equal( if duplicates )
     *
     *  Traverse the array from 1st index, check each element is smaller or equal to a previous element
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     * */
    private static boolean isSorted(int[] arr) {

        int n = arr.length;
        // Base Case
        if (n == 0)
            return false;

        for (int i = 1; i < n-1; i++) {
            if(arr[i] < arr[i-1]){
                return false;
            }
        }
        return true;
    }
}

