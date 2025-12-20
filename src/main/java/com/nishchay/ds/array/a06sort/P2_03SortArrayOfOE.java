package com.nishchay.ds.array.a06sort;

import java.util.Arrays;

/*
 *	======================== Segregate Even and Odd numbers =================================
 *
 * Given an array arr[], write a function that segregates even and odd numbers.
 * The functions should put all even numbers first, and then odd numbers.
 * Order maintenance is not required
 *
 * Examples:
 *				Input: arr = [0, 1, 2, 3, 4]
 *				Output: arr = [0, 2, 4, 1, 3]
 *				Explanation: 0 2 4 are even and 1 3 are odd numbers.
 *				Please note that [2, 0, 4, 3, 1] or [4, 2, 0, 1, 3] are also valid outputs. We only need to make sure that all even elements are before all odd.
 *
 *				Input : arr = {1, 5, 11}
 *				Output : arr = {1, 5, 11}
 *				Explanation All numbers are odd
 *
 *              i/p - {12, 34, 45, 9, 8, 90, 3}
 *              o/p - {12, 34, 8, 90, 45, 9, 3}
 *
 * https://www.geeksforgeeks.org/dsa/segregate-even-and-odd-numbers/
 * */
public class P2_03SortArrayOfOE {

    public static void main(String[] args) {

        int[] arr = {12, 34, 45, 9, 8, 90, 3};
        System.out.println("Original array = " + Arrays.toString(arr));

        segregateEvenOdd(arr);
        System.out.println("  Sorted array = " + Arrays.toString(arr));
    }

    /*
     * ================ [Expected  Approach] Use two pointer approach  =====================
     *
     * Maintain two indexes. Initialize the first index left as 0 and the second index right as n-1.
     *	Do following while left < right
     *	    a) Keep incrementing index left while there are even at it
     *	    b) Keep decrementing index right while there are odd at it
     *	    c) If left < right then exchange arr[left] and arr[right]
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *  No fo array scan    : 1
     *
     * */
    private static void segregateEvenOdd(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (arr[left] % 2 == 0 && left < right) // even
                left++;
            while (arr[right] % 2 == 1 && left < right) // odd
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
 * Original array = [12, 34, 45, 9, 8, 90, 3]
 *   Sorted array = [12, 34, 90, 8, 9, 45, 3]
 * */
