package com.nishchay.algo.bsearch.a04bitonic;

/*
 * ============== Find a peak element of an Array ====================
 *
 * Given an array of integers(no-sorted). Find a peak element in it. Return the index to any of the peaks.
 * An array element is a peak if it is NOT smaller than its neighbors.
 *
 * For corner elements, we need to consider only one neighbour.
 * Note: Consider the element before the first element and the element after the last element to be negative infinity.
 *
 * Peak element = arr[i-1] < arr[i] > arr[i+1]
 *
 * NOTE - here solving for only one peak
 *
 *      Examples:
 *				Input: array[]= {5, 7, 10, 20, 15, 13}
 *				Output: 20
 *				The element 20 has neighbors 10 and 15, both of them are less than 20.
 *
 *				Input: array[] = {10, 20, 15, 2, 23, 90, 67}
 *				Output: 20 or 90
 *				The element 20 has neighbors 10 and 15,
 *				both of them are less than 20, similarly 90 has neighbors 23 and 67.
 *
 *				Input: array[]= {1, 2, 3, 4, 5}
 *				Output: 5
 *				The element 5 has only neighbors 4 which is less than 5.
 *
 *				Input: array[]= {10, 8, 6, 5, 3, 2}
 *				Output: 10
 *				The element 10 has only neighbors 8 which is less than 10.
 *
 *				Input: array[]= {1, 2, 3, 4, 5, 6, 7, 8, 5, 1}
 *				Output: 8
 *				The element 8 has neighbors 7 and 5, both of them are less than 8.
 *
 * https://www.geeksforgeeks.org/dsa/find-a-peak-in-a-given-array/
 * https://takeuforward.org/data-structure/peak-element-in-array/
 * https://www.techiedelight.com/find-peak-element-array/
 * https://leetcode.com/problems/find-peak-element/description/
 * */
public class BS14PeakElement {

    public static void main(String[] args) {

        int[] arr;

        arr = new int[]{5, 7, 9, 10, 18, 15, 13};
        System.out.printf("peak of the array = %d%n", findPeakElement(arr)); // 18
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 18

        arr = new int[]{10, 20, 15, 2, 23, 90, 67};
        System.out.printf("peak of the array = %d%n", findPeakElement(arr)); // 20,90
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 20,90

        arr = new int[]{1, 2, 3, 4, 5};
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 5
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 5

        arr = new int[]{10, 8, 6, 5, 3, 2};
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 10
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 10

        arr = new int[]{5, 19, 24, 14, 8, 4, 26, 12};
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 24
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 24

        arr = new int[]{2, 4, 6, 8, 10, 12, 15, 18, 21};
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 21
        System.out.printf("peak of the array = %d%n", peakElement(arr)); // 21
    }


    /*
     * ================  [Naive Approach] Using Linear Search - O(n) Time and O(1) Space =====================
     *
     *  The idea behind this approach is to check every adjacent element to find if there exists its pair or not as the array is sorted.
     *  -   The single element must appear at an odd position (or even index) because every other element appears twice.
     *  -   One by one check all odd postilions (or even indexes) and if we find an element which is different from next, we return the element.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int findPeakElement(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            // Check left neighbor if exists
            boolean left = (i == 0) || (arr[i] >= arr[i - 1]);
            // Check right neighbor if exists
            boolean right = (i == n - 1) || (arr[i] >= arr[i + 1]);

            // If both conditions are true
            if (left && right)
                return i;
        }
        return -1;
    }

    /*
     *  ================ [Expected Approach] Using Binary Search - O(log n) Time and O(1) Space:  =====================
     *
     * If we observe carefully, we can say that:
     * If an element is smaller than it's next element then it is guaranteed that at least one peak element will exist on the right side of this element.
     *       /
     *      /
     * Conversely if an element is smaller than it's previous element then it is guaranteed that at least one peak element will exist on the left side of this element.
     *      \
     *       \
     * Need to have two things :
     *
     *      An element arr[i] of an array arr is a peak element if it’s greater than its neighbour(s).
     *      arr[i] >= arr[i+1] if i = 0 			// left edge
     *      arr[i-1] <= arr[i] if i = n – 1 		// right edge
     *      arr[i-1] <= arr[i] >= arr[i+1] 			// middle ie 0 < i < n-1
     *
     * 	1	logic - finding peak at arr[mid]
     * 		if (array[mid] is the peak element)
     *          return arr[mid]
     *
     * 	2	logic - for left and right movement
     *      // If next neighbor is greater, then peak element will exist in the right subarray
     *      if (arr[mid] < arr[mid + 1])
     *           left = mid + 1;
     *      // Otherwise, it will exist in left subarray
     *      else
     *          right = mid - 1;
     *
     *
     * Time Complexity  : O(log n)
     * Space Complexity : O(1)
     * */
    private static int peakElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int n = arr.length;

        // If there is only one element, then it's a peak
        if (n == 1)
            return 0;

        // Check if the first element is a peak
        if (arr[0] > arr[1])
            return 0;

        // Check if the last element is a peak
        if (arr[n - 1] > arr[n - 2])
            return n - 1;

        // excluding the edge case index
        int left = 1, right = n - 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the element at mid is a peak element return mid
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
                return mid;

            // If next neighbor is greater, then peak element will exist in the right subarray
            if (arr[mid] < arr[mid + 1])
                left = mid + 1;
                // Otherwise, it will exist in left subarray
            else
                right = mid - 1;
        }
        return -1;
    }
}

/*
 * output =>
 * peak of the array = 4
 * peak of the array = 4
 * peak of the array = 1
 * peak of the array = 5
 * peak of the array = 4
 * peak of the array = 4
 * peak of the array = 0
 * peak of the array = 0
 * peak of the array = 2
 * peak of the array = 2
 * peak of the array = 8
 * peak of the array = 8
 *
 * */