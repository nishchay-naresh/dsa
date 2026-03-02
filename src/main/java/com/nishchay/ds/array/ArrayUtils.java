package com.nishchay.ds.array;


/*
*
* this class is having basic operations/functionalities of tree as a data structure
*
* */
public class ArrayUtils {


    // Iterative Approach - O(n) Time and O(1) Space
    public static int findMax(int[] arr) {
        int max = arr[0]; // assuming it's max value

        // Now traverse array from 1 ro n-1
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    public static int findMin(int[] arr) {
        int min = arr[0];

        // Now traverse array from 1 ro n-1
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    public static int linearSearch(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            // Return the index if key is found
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    public static boolean binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target)
                return true;
            else if (target > arr[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

}
