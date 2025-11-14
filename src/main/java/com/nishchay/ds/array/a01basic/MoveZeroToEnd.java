package com.nishchay.ds.array.a01basic;

/*
 *  ======================= Move all Zeros to End of Array ====================
 *
 * Given an array of integer arr[], move all the zeros to the end of the array while maintaining the relative order of all non-zero elements.
 *
 * Examples:
 * 		Input: arr[] = [1, 2, 0, 4, 3, 0, 5, 0]
 * 		Output: [1, 2, 4, 3, 5, 0, 0, 0]
 * 		Explanation: There are three 0s that are moved to the end.
 *
 * 		Input: arr[] = [10, 20, 30]
 * 		Output: [10, 20, 30]
 * 		Explanation: No change in array as there are no 0s.
 *
 * 		Input: arr[] = [0, 0]
 * 		Output: [0, 0]
 * 		Explanation: No change in array as there are all 0s.
 *
 * https://www.geeksforgeeks.org/dsa/move-zeroes-end-array/
 *
 * */

import java.util.Arrays;

public class MoveZeroToEnd {

    public static void main(String[] args) {

        int[] arr = {1, 2, 0, 4, 3, 0, 5, 0};
        System.out.print("Actual Array =" + Arrays.toString(arr));
        pushZerosToEnd(arr);
        System.out.print(", Result array = " + Arrays.toString(arr));
        System.out.print("\n----------------------------------------------------------------\n");

        System.out.print("Actual Array =" + Arrays.toString(arr));
        moveZerosToEnd_2pass(arr);
        System.out.print(", Result array = " + Arrays.toString(arr));
        System.out.print("\n----------------------------------------------------------------\n");

        System.out.print("Actual Array =" + Arrays.toString(arr));
        pushZerosToEnd_1pass(arr);
        System.out.print(", Result array = " + Arrays.toString(arr));
        System.out.print("\n----------------------------------------------------------------\n");

        arr = new int[]{10, 20, 30};
        System.out.print("Actual Array =" + Arrays.toString(arr));
        pushZerosToEnd_1pass(arr);
        System.out.print(", Result array = " + Arrays.toString(arr));
        System.out.print("\n----------------------------------------------------------------\n");

        arr = new int[]{0, 0};
        System.out.print("Actual Array =" + Arrays.toString(arr));
        pushZerosToEnd_1pass(arr);
        System.out.print(", Result array = " + Arrays.toString(arr));
        System.out.print("\n----------------------------------------------------------------\n");
    }

    /*
     * ================ [Naive/Bruteforce Approach] Using Temporary Array  =====================
     *
     *
     *  1. Traverse main array copy all non-zeros to a temp array
     *  2. Fill the remaining cells with zeros in temp array
     *  3. Copy everything from temp array to main array
     *
     *  Time Complexity     : O(n) + O(n) = O(2n)
     *  Space complexity    : O(n)
     */
    private static void pushZerosToEnd(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];

        int t = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0)
                temp[t++] = arr[i];
        }
        while (t < n) {
            temp[t++] = 0;
        }

        for (int i = 0; i < n; i++)
            arr[i] = temp[i];
    }

    /*
     * ================ [Better Approach] Using two pointer  =====================
     *
     *  1. First Traversal: Shift non-zero elements to the beginning of array using 2 pointer
     *              1st pointer - to track the non-zeros elements
     *              2nd pointer - to scan all elements of array
     *  2. Second Traversal: Fill remaining positions with zeros
     *
     *
     *  Time Complexity     : O(n) + O(n) = O(2n)
     *  Space complexity    : O(1)
     */
    private static void moveZerosToEnd_2pass(int[] arr) {
        int n = arr.length;

        int nonZeroIndex = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[nonZeroIndex] = arr[i];
                nonZeroIndex++;
            }
        }

        // Fill remaining positions with zeros
        while (nonZeroIndex < n) {
            arr[nonZeroIndex] = 0;
            nonZeroIndex++;
        }
    }

    /*
     * ================ [Optimal Approach] 2 pointer approach  =====================
     *
     * We will modifying the last approach only to save the 2nd pass which we have used to fill the zeros
     *
     *  1. First Traversal: Shift non-zero elements to the beginning of array using 2 pointer
     *              1st pointer - to track the non-zeros elements
     *              2nd pointer - to scan all elements of array
     *  We will do the same thing as step 1, while shifting rather than simply copying at nonZeroIndex
     *
     *  We will be swaping it with zero
     *              1st pointer - we will position it to next zero element after all non-zeros
     *              when 2nd pointer will have a non-zero, we will swaping it with 1st pointer
     *              keep increment j only when we do the swapping
     *              in this way all the zeros will shift to the end of array
     *
     *
     *  Time Complexity     : O(n), as we are traversing the array only once.
     *                          first loop - O(x) , x - no of non-zeros in array
     *                          second loop - O(n-x)
     *                          over all = O(x) + O(n-x) = O(n)
     *  Space complexity    : O(1)
     *
     */
    private static void pushZerosToEnd_1pass(int[] arr) {
        int n = arr.length;

        // position j to then first zero, after all non-zeros from the beginning of array
        int j = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }
        if (j == -1) {
            // no zeros are there
            return;
        }
        int t;
        // now look for non-zeros in array, swap it with j
        for (int i = j + 1; i < n; i++) {
            if (arr[i] != 0) {
                // swap(arr[j],arr[i])
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
                // increment j only when we do the swapping
                j++;
            }
        }
        // this loop will break when i will cross the boundary
        // j will still point the - first zero, after all non-zeros from the beginning of array
    }
}
