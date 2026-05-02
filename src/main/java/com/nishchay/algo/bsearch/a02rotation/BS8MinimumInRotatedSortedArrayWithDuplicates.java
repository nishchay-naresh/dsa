package com.nishchay.algo.bsearch.a02rotation;

import java.util.Arrays;

/*
 *============== Minimum in a Sorted and Rotated Array ====================
 *
 *  An element A[i] of an array A is a peak element if it’s not smaller than its neighbour(s).
 *
 *  Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 *  [4,5,6,7,0,1,4] if it was rotated 4 times.
 *  [0,1,4,4,5,6,7] if it was rotated 7 times.
 *  Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 *  Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 *  You must decrease the overall operation steps as much as possible.
 *
 * Examples:
 *				Input: arr[] = [1,3,5]
 *				Output: 1
 *				Explanation: 1 is the minimum element present in the array.
 *
 *				Input: arr[] = [2,2,2,0,1]
 *				Output: 0
 *				Explanation: 0 is the minimum element present in the array.
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 * */
public class BS8MinimumInRotatedSortedArrayWithDuplicates {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5};
        System.out.println(Arrays.toString(arr)+ ", findMin(arr) = " + findMin(arr));

        arr = new int[]{2,2,2,0,1};
        System.out.println(Arrays.toString(arr)+ ", findMin(arr) = " + findMin(arr));
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
     * 	Key Observations
     * 	Minimum is always in the unsorted half
     * 		If nums[mid] > nums[right] → min is on the right
     * 		If nums[mid] < nums[right] → min is on the left (including mid)
     * 		If nums[mid] == nums[right] → shrink search space safely
     *
     *
     * Time Complexity  = O(log n)
     * Space Complexity = O(1)
     * */
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // nums[mid] == nums[right], handle duplicates
                // Can't decide, shrink safely
                right--;
            }
        }
        return nums[left];
    }

}
