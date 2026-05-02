package com.nishchay.algo.bsearch.a02rotation;

/*
 *============== Minimum in a Sorted and Rotated Array ====================
 *
 * Given a sorted array of distinct elements arr[] of size n that is rotated at some unknown point, the task is to find the minimum element in it.
 *
 * Examples:
 *				Input: arr[] = [5, 6, 1, 2, 3, 4]
 *				Output: 1
 *				Explanation: 1 is the minimum element present in the array.
 *
 *				Input: arr[] = [3, 1, 2]
 *				Output: 1
 *				Explanation: 1 is the minimum element present in the array.
 *
 *				Input: arr[] = [4, 2, 3]
 *				Output: 2
 *				Explanation: 2 is the only minimum element in the array.
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-minimum-element-in-a-sorted-and-rotated-array/
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 * */
public class BS7MinimumInRotatedSortedArrayNoDuplicates {
    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 2, 3, 4};
        System.out.println(findMin(arr));
    }

    /*
     * =========== [Expected Approach] Binary Search - O(log n) Time and O(1) Space ==========
     *
     *  We can optimize the minimum element searching by using Binary Search
     *  where we find the mid element and then decide whether to stop or to go to left half or right half:
     *  min element can only in non-sorted array (right - mid + 1, left - mid)
     *
     *
     *  If arr[mid] > arr[right], it means arr[left ... mid] is sorted and we need to search in the right half.
     *       So we change left = left + 1.
     *  If arr[mid] <= arr[right], it means arr[mid ... right] is sorted and we need to search in the left half.
     *      So we change right = mid. (Note: Current mid might be the minimum element).
     *
     *
     * Time Complexity  - O(log n)
     * Space Complexity - O(1)
     * Function to find the minimum element using binary search
     * */
    private static int findMin(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {

            // The current subarray is already sorted, the minimum is at the left index
            if (arr[left] < arr[right])
                return arr[left];

            // Calculate mid index
            int mid = left + (right - left) / 2;

            // Check which half to discard
            if (arr[mid] > arr[right]) {
                // Right half is not sorted - Minimum lies in right half
                left = mid + 1;
            } else {
                // Right half is sorted - Minimum lies in left half (including mid)
                right = mid;
            }
        }
        // When left == right, we found the smallest element
        return arr[left];
    }

}
