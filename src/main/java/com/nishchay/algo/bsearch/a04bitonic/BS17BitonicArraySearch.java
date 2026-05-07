package com.nishchay.algo.bsearch.a04bitonic;


import com.nishchay.algo.bsearch.a01basic.BS01SearchAlgorithms;
import com.nishchay.algo.bsearch.a01basic.BS2ReverseSortedArray;

import java.util.Arrays;

/*
 *============== Find an element in Bitonic array ====================
 *
 * Given a bitonic sequence of n distinct elements, write a program to find a given element x in the bitonic sequence in O(log n) time.
 *
 * Examples - 1
 *		Input :  arr[] = {-3, 9, 18, 20, 17, 5, 1};
 *		         key = 20
 *		Output : Found at index 3
 *
 * Examples - 2
 *		Input :  arr[] = {5, 6, 7, 8, 9, 10, 3, 2, 1};
 *		         key = 30
 *		Output : Not Found
 *
 *
 * https://www.geeksforgeeks.org/find-element-bitonic-array/
 * https://www.callicoder.com/search-in-bitonic-array/
 * https://leetcode.com/problems/find-in-mountain-array/description/
 *
 * */
public class BS17BitonicArraySearch {

    public static void main(String[] args) {

        int[] arr;
        int key;

        arr = new int[]{4, 10, 15, 20, 45, 35, 29, 17, 10};
        key = 20;
        System.out.printf("found at = %d%n", search(arr, key)); // 3
        System.out.printf("found at = %d%n", searchBitonic(arr, key)); // 3

        arr = new int[]{4, 10, 15, 20, 45}; // only increasing
        key = 30;
        System.out.printf("found at = %d%n", search(arr, key)); // -1
        System.out.printf("found at = %d%n", searchBitonic(arr, key)); // -1
    }

    /*
     * This problem is a variation of some of the existing problems we have already solved in this series:
     * 	1.  Find Maximum Element in a Bitonic Array
     * 	2.  Order-agnostic Binary Search
     *
     * This problem can be easily solve in below way :
     *	    1.  Find bitonic point in given bitonic sequence - maxIndex
     *	    2.  Apply binary search from
     *		    -   0 to maxIndex and  ( descending order)
     *		    -   maxIndex+1 to array_length-1 ( descending order)
     *
     * Time complexity: O(log n)
     *  Auxiliary Space: O(1)
     * */
    private static int search(int[] arr, int key) {
        int maxIndex = BS16BitonicArrayMax.peakIndexInMountainArray(arr);

        int[] left = Arrays.copyOfRange(arr,0, maxIndex-1); // copying 0 to maxIndex, excluding pivot
        int[] right = {};
        if (arr.length - 1 > maxIndex + 1) {
            right = Arrays.copyOfRange(arr, maxIndex + 1, arr.length-1); // copying maxIndex to length, excluding length
        }

        int targetIndex = BS01SearchAlgorithms.binarySearchIterative(left, key);
        if (targetIndex != -1) {
            return targetIndex;
        }
        return BS2ReverseSortedArray.binarySearchReverse(right, key);
    }

    // Function for binary search in an ascending array
    private static  int ascendingBinarySearch(int[] arr, int low, int high, int key) {
        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] > key)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    // Function for binary search in a descending array
    private static int descendingBinarySearch(int[] arr, int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }


    // Function to search key in a bitonic array
    private static int searchBitonic(int[] arr, int key) {

        int index =  BS16BitonicArrayMax.peakIndexInMountainArray(arr);

        if (key > arr[index])
            return -1;
        else if (key == arr[index])
            return index;
        else {
            int temp = ascendingBinarySearch(
                    arr, 0, index - 1, key);
            if (temp != -1)
                return temp;
            // Search in right of k
            return descendingBinarySearch(arr, index + 1,arr.length - 1, key);
        }
    }
}
