package com.nishchay.algo.bsearch.a01basic;


/*
 *============== Binary Search on Reverse Sorted Array ====================
 *
 * Given an array arr[] sorted in decreasing order, and an integer key,
 * the task is to check if key is present in the given array or not.
 * If key is present in the array, print its index ( 0-based indexing). Otherwise, print -1.
 *
 * You can return the answer in any order.
 *
 * Examples -1
 *		Input: arr[] = {5, 4, 3, 2, 1}, key = 4
 *		Output: 1
 *
 * Examples -2
 *		Input: arr[] = {10, 8, 2, -9}, key = 5
 *		Output: -1
 *
 *
 * https://www.geeksforgeeks.org/dsa/search-an-element-in-a-reverse-sorted-array/
 * */
public class BS2ReverseSortedArray {

    public static void main(String[] args) {

        int[] arr = {20, 17, 15, 14, 12, 9, 8, 4, 3, 1};

        System.out.println("binarySearchReverse(arr, 9) : " + binarySearchReverse(arr, 9));
        System.out.println("binarySearchReverse(arr, 1) : " + binarySearchReverse(arr, 1));
        System.out.println("binarySearchReverse(arr, 11) : " + binarySearchReverse(arr, 11));

        arr = new int[]{5, 4, 3, 2, 1};
        System.out.println("binarySearchReverse(arr, 4) : " + binarySearchReverse(arr, 4));

        arr = new int[]{10, 8, 2, -9};
        System.out.println("binarySearchReverse(arr, 5) : " + binarySearchReverse(arr, 5));
    }

    /*
     * Need to perform binary Search on Reverse Sorted Array
     *
     * So everything will be same only, moving left/right logic will change
     * since data is on reverse sorted
     *  - smaller element will be on right now
     *  - greater element will be on left now
     *
     *	left = 0; right = endOfArray, calculate the mid,
     *	Compare the key items with the mid element
     *	If key == arr[mid],
     *		then return the mid index as result.
     *	Else If key < arr[mid],  key lies in the right half of the collection.
     *		Thus repeat steps 1 to 3 for right half of the collection.
     *	Else key > arr[mid], then the key is in the left half of the collection.
     *		Thus repeat steps 1 to 3 for left half of the collection.
     *
     *
     *  Time complexity - O(log n)
     * */
    public static int binarySearchReverse(int[] sortedArray, int key) {

        int left, right, mid;
        left = 0;
        right = sortedArray.length - 1;

        while (left <= right) {

            mid = left + (right - left)/2;

            if (sortedArray[mid] == key) {
                return mid;
            } else if (sortedArray[mid] > key) { //  go right
                left = mid + 1;
            } else if (sortedArray[mid] < key) {//  go left
                right = mid - 1;
            }
        }
        return -1;
    }
}
