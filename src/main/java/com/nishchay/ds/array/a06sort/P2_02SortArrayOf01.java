package com.nishchay.ds.array.a06sort;

import java.util.Arrays;

/*
 *	======================== Segregate 0s and 1s in an array =================================
 *
 *  You are given an array of 0's and 1's in random order.
 *  Segregate 0's to left side and 1's right side of the array [you have to sort the array].
 *  Traverse an array only once.
 *
 * Examples:
 *				Input : [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
 *				Output : [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]
 *
 *				Input :  [0, 1, 0]
 *				Output :  [0, 0, 1]
 *
 *				Input :  [1, 1]
 *				Output :  [1, 1]
 *
 *				Input :  [0]
 *				Output :  [0]
 *
 *              Input array : [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
 *              Output array : [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]
 *
 * https://www.geeksforgeeks.org/dsa/segregate-0s-and-1s-in-an-array-by-traversing-array-once/
 * */
public class P2_02SortArrayOf01 {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 1, 1, 0, 1};

        System.out.println("Original array - " + Arrays.toString(arr));
        segregate0and1(arr);
        System.out.println("Sorted array - " + Arrays.toString(arr));

        arr = new int[]{0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
        System.out.println("Original array - " + Arrays.toString(arr));
        segregate0and1TwoPointers(arr);
        System.out.println("Sorted array - " + Arrays.toString(arr));
    }

    /*
     * ================ [Naive/Bruteforce Approach] Linear Search for Missing Number  =====================
     *
     *  iterates through the array and get number of ones.
     *  Once we have ones count, we can fill the array first zeros and then ones in the next iteration.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *  No fo array scan    : 2
     */
    private static void segregate0and1(int[] arr) {
        int length = arr.length;
        int countZeros = 0;

        for (int curr : arr) {
            if (curr == 0)
                countZeros++;
        }

        // loop fills the arr with 0
        for (int i = 0; i < countZeros; i++)
            arr[i] = 0;

        // loop fills remaining arr space with 1
        for (int i = countZeros; i < length; i++)
            arr[i] = 1;

    }

    /*
     * ================ [Expected  Approach] Use two pointer approach  =====================
     * Classic two-pointer partitioning algorithm to segregate 0s and 1s in O(n) time and O(1) space
     *
     * Maintain two indexes. Initialize the first index left as 0 and the second index right as n-1.
     *	Do following while left < right
     *	    a) Move left forward for 0's
     *	    b) Move right backward for 1's
     *	    c) Swap misplaced values
     *         arr[left]==1 && arr[right] == 0, then swap them
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *  No fo array scan    : 1
     *
     * */
    private static void segregate0and1TwoPointers(int[] arr) {

        int left = 0, right = arr.length - 1;

        while (left < right) {
            // Increment left index while we see 0 at a left
            while (arr[left] == 0 && left < right)
                left++;

            // Decrement right index while we see 1 at right
            while (arr[right] == 1 && left < right)
                right--;

            /* If left is smaller than right, then
             there is a 1 at left and a 0 at right.  Exchange arr[left] and arr[right]
             */
            if (left < right) {
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
                left++;
                right--;
            }
        }
    }

}
