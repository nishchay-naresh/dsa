package com.nishchay.algo.recursion.pointer2;

import java.util.Arrays;


/*
 *  ============================= Reverse an Array using Recursion ======================================
 *
 *  Given an integer array arr[], Reverse the array using recursion.
 *
 * Examples:
 *			Input: arr[] = [1, 2, 3, 4, 5]
 *			Output: [5, 4, 3, 2, 1]
 *			Explanation: After reversing the array recursively, the order of elements is reversed.
 *
 *			Input: arr[] = [10, 20, 30]
 *			Output: [30, 20, 10]
 *			Explanation: Array elements are reversed using recursion.
 *
 * https://www.geeksforgeeks.org/dsa/reverse-an-array-using-recursion/
 */

public class ArrayReverse {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        System.out.println("Original Array - " + Arrays.toString(arr));
        reverseArray(arr);
        System.out.println("Reversed Array - " + Arrays.toString(arr));

        System.out.println("-----------------------------------------");

        System.out.println("Original Array - " + Arrays.toString(arr));
        reverseArray(arr, 0, arr.length - 1);
        System.out.println("Reversed Array - " + Arrays.toString(arr));
    }

    private static void reverseArray(int[] arr) {
        reverseArrayRec(arr, 0, arr.length - 1);
    }

    private static void reverseArrayRec(int[] arr, int start, int end) {
        // base Case
        if (start >= end)
            return;

        // Swap
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        // Recursive call
        reverseArrayRec(arr, start + 1, end - 1);
    }

    private static void reverseArray(int[] arr, int i, int n) {
        // base Case
        if (i >= n/2)
            return;

        // Swap
        int temp = arr[i];
        arr[i] = arr[n-i];
        arr[n-i] = temp;

        // Recursive call
        reverseArray(arr, i + 1, n);
    }

}
