package com.nishchay.algo.bsearch.a03medium;

/*
 *============== Search in an almost/nearly sorted array ====================
 *
 * Given an array which is sorted, but after sorting some elements are moved to either of the adjacent positions,
 *  i.e., arr[i] may be present at arr[i+1] or arr[i-1].
 *
 *  Given an integer target.  You have to return the index ( 0-based ) of the target in the array. If target is not present return -1.
 *
 * Basically, the element arr[i] can only be swapped with either arr[i+1] or arr[i-1].
 * For example consider the array {2, 3, 10, 4, 40}, 4 is moved to next position and 10 is moved to previous position.
 *
 *  Examples:
 *				Input: arr[] =  [10, 3, 40, 20, 50, 80, 70], target = 40
 *				Output: 2
 *				Explanation: Output is index of 40 in given array i.e. 2
 *
 *				Input: arr[] =  [10, 3, 40, 20, 50, 80, 70], target = 90
 *				Output: -1
 *				Explanation: 90 is not present in the array.
 *
 * https://www.geeksforgeeks.org/dsa/search-almost-sorted-array/
 * https://www.educative.io/edpresso/how-to-search-in-a-nearly-sorted-array
 * */
public class SearchInNearlySortedArray {

    public static void main(String[] args) {

        int key;
        int[] arr;

        arr = new int[]{10, 3, 40, 20, 50, 80, 70};
        key = 40;
        System.out.printf("Found At = %d%n", searchAlmostSorted(arr, key)); // 2

        key = 3;
        System.out.printf("Found At = %d%n", searchAlmostSorted(arr, key)); // 1

        arr = new int[]{10, 3, 40, 20, 50, 80, 70};
        key = -1;
        System.out.printf("Found At = %d%n", searchAlmostSorted(arr, key)); // -1

        arr = new int[]{15, 20, 30, 25, 35};
        key = 25;
        System.out.printf("Found At = %d%n", searchAlmostSorted(arr, key)); // 3

        key = 100;
        System.out.printf("Found At = %d%n", searchAlmostSorted(arr, key)); // -1
    }

    /*
     *  ================ [Expected Approach] Using Binary Search - O(log n) Time and O(1) Space:  =====================
     *
     * The idea is to compare the key with middle 3 elements, if present then return the index.
     * If not present, then compare the key with middle element to decide whether to go in left half or right half.
     * Comparing with middle element is enough as
     *      -   all the elements after mid+2 must be greater than element mid and
     *      -   all elements before mid-2 must be smaller than mid element
     *
     * Follow the steps below to implement the idea:
     *
     * 	Set mid = s+(s-e)/2.
     * 	If arr[mid] == key || arr[mid] == arr[mid-1] || arr[mid] == arr[mid+1]
     * 	 	return mid / mid-1 / mid+1 accordingly
     * 	If arr[mid] > x
     * 	   	e = mid - 2	// go left
     * 	Else
     * 	   	s = mid + 2 // go right
     *
     *  Time complexity  = O(log n)
     *  Space complexity = O(1)
     * */
    private static int searchAlmostSorted(int[] arr, int key) {

        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;

            if (key == arr[mid]) {
                return mid;
            }
            // checking segmentation fault - for lower bound
            if (mid > 0 && key == arr[mid - 1]) {
                return mid - 1;
            }
            // checking segmentation fault - for upper bound
            if (mid < n - 1 && key == arr[mid + 1]) {
                return mid + 1;
            }
            if (key < arr[mid]) {
                end = mid - 2;
            } else {
                start = mid + 2;
            }
        }
        return -1;
    }

}
