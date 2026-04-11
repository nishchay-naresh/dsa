package com.nishchay.algo.bsearch.a01basic;

import java.util.Arrays;


/*
 * ================================== Binary Search ===================================
 *
 * Given a sorted array arr[] of n elements, write a function to search a given element key in arr[].
 *
 *
 *
 * Examples - 1
 *		Input  : arr[] = {5, 7, 8, 10, 12, 18, 23, 25, 29};
 *		         key = 23
 *		Output : Found at index 6
 *
 *
 * https://www.geeksforgeeks.org/binary-search/
 * */
public class BS01SearchAlgorithms {

    public static void main(String[] args) {

        System.out.println("------------ linearSearch-------------");
        linearSearchEx();
        System.out.println("------------ binarySearch-------------");
        binarySearchEx();
    }

    private static void linearSearchEx() {

        int[] arr = {3, 4, 1, 7, 5, 12, 8};

        System.out.println("arr : " + Arrays.toString(arr));

        System.out.println("linearSearch(arr, 12) : " + linearSearch(arr, 12));
        System.out.println("linearSearch(arr, 17) : " + linearSearch(arr, 17));
        System.out.println("linearSearch(arr, 1)  : " + linearSearch(arr, 1));
    }

    private static void binarySearchEx() {
        int[] arr = {3, 4, 1, 7, 5, 12, 8};
        int length = arr.length;

        Arrays.sort(arr); // need to have a sorted array for binary search

        System.out.println("arr : " + Arrays.toString(arr));
        System.out.println("binarySearchIterative(arr, 12) : " + binarySearchIterative(arr, 12));
        System.out.println("binarySearchIterative(arr, 17) : " + binarySearchIterative(arr, 17));
        System.out.println("binarySearchIterative(arr, 1)  : " + binarySearchIterative(arr, 1));
        System.out.println("................................");
        System.out.println("arr : " + Arrays.toString(arr) + ", length - " + length);
        System.out.println("binarySearchRecursive(arr, 0, length, 12) : " + binarySearchRecursive(arr, 0, length - 1, 12));
        System.out.println("binarySearchRecursive(arr, 0, length, 17) : " + binarySearchRecursive(arr, 0, length - 1, 17));
        System.out.println("binarySearchRecursive(arr, 0, length, 1)  : " + binarySearchRecursive(arr, 0, length - 1, 1));
    }


    /*
     * =========== Linear/Sequential Search ============
     *
     * just start either left/right side of the array, compare each element with the key, until you done with the entire collection
     *  if found
     *      return index
     *  else
     *      return -1;
     *
     *  Time complexity - O(n)
     * */
    public static int linearSearch(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            // Return the index if key is found
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    /*
     *  ============================= Binary Search ====================================
     *  Binary Search is a searching algorithm that operates on a sorted data
     *  It's repeatedly dividing data into halves to find a target value in logarithmic time O(log N).
     *
     *	1. Divide the search space into two halves by finding the middle index "mid".
     *	2. Compare the middle element of the search space with the key.
     *		If the key is found at middle element, the process is terminated, we return the mid index.
     *		If the key is not found at middle element, choose which half will be used as the next search space.
     *			-> If the key is smaller than the middle element, then the left side is used for the next search.
     *			-> If the key is larger than the middle element, then the right side is used for the next search.
     *	3. This process is continued until the key is found or the total search space is exhausted.
     *
     * --------------------------------- Overflow case -----------------------------------------
     * for a very big data, high - Integer.MAX_VALUE, low = 0 / or a big number
     *   mid = (low + high) - Overflow integer can't hold that value
     *
     * So the alternative way to compute the mid for very big data set to avoid integer overflow
     *      mid = low + (high - low)/2
     *
     *
     *  Time complexity - O(log n)
     * */
    public static int binarySearchIterative(int[] sortedArray, int key) {
        int left, right, mid;
        left = 0;
        right = sortedArray.length - 1;

        while (left <= right) {

            mid = (left + right) / 2;

            if (sortedArray[mid] == key) {
                return mid;
            } else if (sortedArray[mid] > key) { //go left
                right = mid - 1;
            } else if (sortedArray[mid] < key) { //go right
                left = mid + 1;
            }
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

    /*
     * The same logic we are recursively applying
     * Calling the same method by passing the chosen half of data for the next search.
     *
     * Time complexity - O(log n)
     *
     * */
    public static int binarySearchRecursive(int[] array, int left, int right, int key) {

        if (right < left) {
            return -1;
        }
        int mid = (left + right) / 2;

        if (key == array[mid])
            return mid;
        else if (key < array[mid])
            return binarySearchRecursive(array, left, mid - 1, key);

        return binarySearchRecursive(array, mid + 1, right, key);
    }

}
