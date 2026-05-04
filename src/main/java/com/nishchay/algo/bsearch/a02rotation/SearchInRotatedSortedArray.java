package com.nishchay.algo.bsearch.a02rotation;


/*
 *============== Search in a Sorted and Rotated Array ====================
 *
 * Given a sorted and rotated array arr[] of distinct elements,
 *  find the index of given key in the array. If the key is not present in the array, return -1.
 *
 * Examples:
 *				Input: arr[] = [5, 6, 7, 8, 9, 10, 1, 2, 3], key = 3
 *				Output: 8
 *				Explanation: 3 is present at index 8.
 *
 *				Input: arr[] = [3, 5, 1, 2], key = 6
 *				Output: -1
 *				Explanation: 6 is not present.
 *
 *				Input: arr[] = [33, 42, 72, 99], key = 42
 *				Output: 1
 *				Explanation: 42 is found at index 1.
 *
 *				Input: arr[] = [4, 5, 6, 7, 0, 1, 2], key = 3
 *				Output :-1
 *				Explanation: Here, the target is 3. Since 3 is not present in the given rotated sorted array. Thus, we get the output as -1.
 *
 * https://takeuforward.org/data-structure/search-element-in-a-rotated-sorted-array/
 * https://www.geeksforgeeks.org/dsa/search-an-element-in-a-sorted-and-pivoted-array/
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 * */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {


        int[] arr = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        int key = 1;
        System.out.println("searchInRotatedArray(arr, 1) : " + searchInRotatedArray(arr, key));

        arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        key = 0;
        System.out.println("searchInRotatedArray(arr, 0) : " + searchInRotatedArray(arr, key));
        System.out.println("searchInRotatedArray(arr, 3) : " + searchInRotatedArray(arr, 3));
    }

    /*
     * =========== [Naive Approach] Using Linear Search - O(n) Time and O(1) Space ==========
     *
     *  A simple approach is to iterate through the array and check for each element,
     *  if it matches the target then return the index, otherwise return -1.
     *  for code refer - BS01SearchAlgorithms#linearSearch(-,-)
     *
     * Time Complexity  - O(n)
     * Space Complexity - O(1)
     *
     * */

    /*
     * =========== [Expected Approach] Using Single Binary Search - O(log n) Time and O(1) Space ==========
     *
     *  In a rotated sorted array, the entire array is no longer fully sorted ,
     *  but an important property still holds: in every part of the array you look at, one side will always be sorted.
     *  This means either the left portion or the right portion of the array will be in increasing order. That’s the key idea we use to find the target efficiently.
     *
     *  Identify the sorted half - left/right, we will use the binary search in a modified way
     *
     *  Once we know which part is sorted, we check if the target lies inside that sorted section. If it does, we discard the other half.
     *  If not, we discard the sorted half and search the remaining half.
     *
     * Algoritham:
     *		Start by looking at the middle element of the array.
     *		Check if this middle element is the target if yes,
     *			return its index immediately.
     *
     *		Now figure out which half of the array (left side or right side) is sorted.
     *		If the left part is sorted:
     *			Check if the target number falls within the range of that sorted part.
     *			If it does, discard the right half and continue the search in the left part.
     *			If it doesn’t, discard the left half and search in the right side.
     *		If the right part is sorted:
     *			Do the same check if the target is in that sorted part.
     *			If yes, discard the left side and search in the right.
     *			If not, discard the right and continue with the left.
     *		Repeat this process of eliminating half the array until the target is found or the search space is empty.
     *
     * Time complexity = O(log n)
     * space complexity = O(1)
     * Function to search key in rotated sorted array using binary search
     * */
    private static  int searchInRotatedArray(int[] array, int key) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (array[mid] == key)
                return mid;

            // If left part is sorted
            if (array[low] <= array[mid]) {

                // If key lies within sorted left part
                if (array[low] <= key && key < array[mid]) {
                    high = mid - 1;
                }
                // Else, search in right half
                else {
                    low = mid + 1;
                }
            }
            // Else, right part is sorted
            else {
                // If key lies within sorted right part
                if (array[mid] < key && key <= array[high]) {
                    low = mid + 1;
                }
                // Else, search in left half
                else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}

