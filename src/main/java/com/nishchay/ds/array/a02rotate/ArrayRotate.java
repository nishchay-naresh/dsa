package com.nishchay.ds.array.a02rotate;

import java.util.Arrays;

/*
 * ====================== Rotate an array by 1 places ==========================
 *
 * Types of Rotations in Array
 *       1. Right Rotation (or Clockwise)
 *                  right ->     arr[10, 20, 30, 40, 50], temp = arr[n-1] because this is getting rotated
 *                                   |------<----<-----|
 *                               arr[50, 10, 20, 30, 40]
 *
 *       2. Left Rotation (Or Counter Clockwise)
 *                   arr[10, 20, 30, 40, 50]  <-- left, temp = arr[0] because this is getting rotated
 *                       |----->------>-----|
 *                   arr[20, 30, 40, 50, 10]
 *
 *   arr     = {10, 20, 30, 40, 50}   <-----
 *   Left    = {20, 30, 40, 50, 10}
 *   arr     = {10, 20, 30, 40, 50}   ----->
 *   Right   = {50, 10, 20, 30, 40}
 *
 *
 * https://www.geeksforgeeks.org/dsa/complete-guide-on-array-rotations/
 * */
public class ArrayRotate {

    public static void main(String[] args) {
        int[] arr1 = {10, 20, 30, 40, 50};
        System.out.println("                        Actual Array =" + Arrays.toString(arr1));
        leftRotate(arr1);
        System.out.println("Left rotate an array by one position =" + Arrays.toString(arr1));
        System.out.println("----------------------------------------------------------------");
        int[] arr2 = {100, 200, 300, 400, 500};
        System.out.println("                        Actual Array  =" + Arrays.toString(arr2));
        rightRotate(arr2);
        System.out.println("Right rotate an array by one position =" + Arrays.toString(arr2));
    }

    // Left rotate an array by one position

    static void leftRotate(int[] arr) {
        int n = arr.length;

        // move forward ward - left to right
        int first = arr[0];
        for (int i = 1; i < n ; i++) {
            arr[i - 1] = arr[i];
        }
        arr[n - 1] = first;
    }

    // Right rotate an array by one position
    static void rightRotate(int[] arr) {
        int n = arr.length;

        // move back ward - right to left
        int last = arr[n - 1];
        for (int i = n - 1; i > 0; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = last;
    }
}
