package com.nishchay.algo.twopointer.a01easy;

/*
 *  ======================= Move all Zeros to End of Array ====================
 *
 * Given an array of integer arr[], move all the zeros to the end of the array while maintaining the relative order of all non-zero elements.
 *
 * Examples:
 * 		Input: arr[] = [1, 2, 0, 4, 3, 0, 5, 0]
 * 		Output: [1, 2, 4, 3, 5, 0, 0, 0]
 * 		Explanation: There are three 0's that are moved to the end.
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
 * Asked in EPAM Systems
 * */

import java.util.Arrays;

public class P202MoveZeroToEnd {

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
     *  1. Traverse main array copy all non-zeros to temp array
     *  2. Fill the remaining cells with zeros in temp array
     *  3. Copy everything from temp array to the main array
     *
     *  Time Complexity     : O(n) + O(n) = O(2n)
     *  Space complexity    : O(n)
     */
    private static void pushZerosToEnd(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];

        int t = 0;
        for (int curr : arr) {
            if (curr != 0){
                temp[t++] = curr;
                }
        }
        while (t < n) {
            temp[t++] = 0;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    /*
     * ================ [Better Approach] Using two pointer  =====================
     *
     *  1. First Traversal: Shift non-zero elements to the beginning of array using 2 pointer
     *              1st pointer - to track the non-zeros elements       -- writerIndex
     *              2nd pointer - to scan all elements of array         -- i
     *  2. Second Traversal: Fill remaining positions with zeros
     *
     *
     *  Time Complexity     : O(n) + O(n) = O(2n) = O(n)
     *  Space complexity    : O(1)
     */
    private static void moveZerosToEnd_2pass(int[] arr) {
        int n = arr.length;

        int writerIndex = 0;
        // Step 1: Move all non-zero elements forward
        for (int curr : arr) {
            if (curr != 0) {
                arr[writerIndex] = curr;
                writerIndex++;
            }
        }

        //  Step 2: Fill remaining positions with zeros
        while (writerIndex < n) {
            arr[writerIndex] = 0;
            writerIndex++;
        }
    }

    /*
     * ================ [Optimal Approach] Even Better (Single Pass Swap Version) ====================
     *
     *Think of the array as divided into two zones:
     *                      [ Non-zero zone | Unprocessed zone ]
     *                           ^
     *                       insertPos
     *
     * Everything from insertPos onward is either zero or not yet processed.
     * Push all non-zero elements to the left side, keeping order.
     *
     * If i == insertPos, nothing changes. Doing self-swaps
     * can improve this by applying
     *
     * 	if(arr[i] != 0) {
     * 	    if (i != insertPos) {
     * 	        swap
     * 	    }
     * 	    insertPos++;
     * 	}
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     */
    private static void pushZerosToEnd_1pass(int[] arr) {
        int writerIndex = 0;

        // If i == writerIndex, nothing changes.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[writerIndex];
                arr[writerIndex] = temp;
                writerIndex++;
            }
        }
    }
}
