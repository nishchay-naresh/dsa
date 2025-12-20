package com.nishchay.ds.array.a06sort;

import java.util.Arrays;

/*
 *	=========== Move Zeros to the Start/End ===========
 *
 * Given an array of random numbers, move all the zero’s of a given array to the start/end of the array.
 * maintaining the order of other elements in the array.
 *
 * Examples:
 *				Input: arr[] = [1, 2, 0, 4, 3, 0, 5, 0]
 *				Output: [1, 2, 4, 3, 5, 0, 0, 0]
 *				Explanation: There are three 0s that are moved to the end.
 *
 *				Input: arr[] = [10, 20, 30]
 *				Output: [10, 20, 30]
 *				Explanation: No change in array as there are no 0s.
 *
 *				Input: arr[] = [0, 0]
 *				Output: [0, 0]
 *				Explanation: No change in array as there are all 0s.
 *
 * For example,
 *		if the given array is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}
 *		moving all zeros to end   - {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}
 *		moving all zeros to start - {0, 0, 0, 0, 1, 9, 8, 4, 2, 7, 6}
 *
 *	Example 1:
 *		Input :  arr[] = {1, 2, 0, 4, 3, 0, 5, 0};
 *		Output : moving all zeros to end   -  {1, 2, 4, 3, 5, 0, 0, 0};
 *				moving all zeros to start  -  {0, 0, 0, 1, 2, 4, 3, 5};
 *
 *	Example 2:
 *		Input : arr[]  = {1, 2, 0, 0, 0, 3, 6};
 *		Output : moving all zeros to end   -  {1, 2, 3, 6, 0, 0, 0};
 *				moving all zeros to start  -  {0, 0, 0, 1, 2, 3, 6};
 *
 *
 * https://www.educative.io/m/move-zeros-left
 * https://www.geeksforgeeks.org/dsa/move-zeroes-end-array/
 * */
public class P2_01MoveZeros {

    public static void main(String[] args) {

        int[] arr;

        arr = new int[]{1, 2, 9, 4, 3, 0, 5, 0};
        System.out.println("Original Array : " + Arrays.toString(arr));
        pushZerosToEnd_1loop(arr);
        System.out.println("pushZerosToEnd : " + Arrays.toString(arr));

        System.out.println("=========== Move Zeros to the Start ===========");
        arr = new int[]{1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
        pushStartEx(arr);
        arr = new int[]{1, 2, 0, 4, 3, 0, 5, 0};
        pushStartEx(arr);
        arr = new int[]{10, 2, 13, 4, 3, 7, 5, 1};
        pushStartEx(arr);
        arr = new int[]{1};
        pushStartEx(arr);

        System.out.println("=========== Move Zeros to the End ===========");
        arr = new int[]{1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
        pushEndEx(arr);
        arr = new int[]{1, 2, 0, 4, 3, 0, 5, 0};
        pushEndEx(arr);
        arr = new int[]{10, 2, 13, 4, 3, 7, 5, 1};
        pushEndEx(arr);
        arr = new int[]{1};
        pushEndEx(arr);

    }

    private static void pushEndEx(int[] arr) {
        System.out.println("Original Array : " + Arrays.toString(arr));
        pushZerosToEnd_2loop(arr);
        System.out.println("pushZerosToEnd : " + Arrays.toString(arr));
    }

    private static void pushStartEx(int[] arr) {
        System.out.println("Original Array  : " + Arrays.toString(arr));
        pushZerosToStart(arr);
        System.out.println("pushZerosToStart: " + Arrays.toString(arr));
    }

    /*
     * ================ [Better Approach] Two Traversals  =====================
     * =========== Move Zeros to the end ===========
     *  We need maintaining the relative order of non-zero elements
     *
     *  The idea is to move all the zeros to the end of the array while maintaining the relative order of non-zero elements using two traversals.
     *
     *  First Traversal: Shift non-zero elements to the beginning of array
     *  Second Traversal: Fill remaining positions with zeros
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * */
    private static void pushZerosToEnd_2loop(int[] arr) {

        if (arr.length < 1) {
            return;
        }
        int n = arr.length;
        int writerIndex = 0;

        for (int i = 0; i < n; i++) {
            // If the element is non-zero, Shift them to the beginning of array
            if (arr[i] != 0) {
                arr[writerIndex++] = arr[i];
            }
        }

        //  Fill remaining positions with zeros
        while (writerIndex < n){
            arr[writerIndex++] = 0;
        }
    }


    /*
     *	=========== Move Zeros to the start ===========
     *  need t apply same logic of above,
     *  rater then traversing left to right, we need to traverse from right to left
     *
     * initialize writeIndex = n-1
     * Traverse the given array ‘arr’ from right to left.
     * 	    if current element is non-zero, write it to writeIndex , then do writeIndex++
     * Now n - writeIndex = noOfZeros
     * So iterate noOfZeros times and copy all zeros at writeIndex
     *
     * Time Complexity: O(n) where n is number of elements in input array.
     * Auxiliary Space: O(1)
     * */
    private static void pushZerosToStart(int[] arr) {

        if (arr.length < 1) {
            return;
        }

        int n = arr.length;
        int writerIndex = n - 1;

        for (int i = n - 1; i >= 0; i--)
            if (arr[i] != 0)
                arr[writerIndex--] = arr[i];

        while (writerIndex >= 0)
            arr[writerIndex--] = 0;
    }


    /*
     * ================ [Expected Approach] One Traversal  =====================
     * =========== Move Zeros to the end ===========
     *  The idea is similar to the previous approach where we took a pointer, say writerIndex to track where the next non-zero element should be placed.
     *  However, on encountering a non-zero element, instead of directly placing the non-zero element at arr[writerIndex], we will swap the non-zero element with arr[writerIndex].
     *  This will ensure that if there is any zero present at arr[writerIndex], it is pushed towards the end of array and is not overwritten.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * */
    private static void pushZerosToEnd_1loop(int[] arr) {
        int writerIndex = 0;

        for (int i = 0; i < arr.length; i++) {

            // If the current element is non-zero, then not incrementing the writerIndex
            if (arr[i] != 0) {

                // Swap the current element with 'writerIndex' when - arr[i] != 0 && i != writerIndex
                if (i != writerIndex) {
                    int temp = arr[i];
                    arr[i] = arr[writerIndex];
                    arr[writerIndex] = temp;
                }
                writerIndex++;
            }
        }
    }

}

