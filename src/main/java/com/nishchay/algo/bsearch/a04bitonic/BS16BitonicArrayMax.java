package com.nishchay.algo.bsearch.a04bitonic;

/*
 *============== Maximum Element in a Bitonic Array ====================
 *============== Find bitonic point in given bitonic sequence ====================
 *============== Peak Index in a Mountain Array ====================
 *
 *
 * Given an array of integers which is initially increasing and then decreasing, find the maximum value in the array.
 *
 *
 * Examples - 1
 *		Input: array[]= {4, 10, 15, 20, 45, 35, 29, 17, 10} //increasing then decreasing
 *		Output: 45
 *
 * Examples - 2
 *		Input: array[] = {4, 10, 15, 20, 45} // only increasing
 *		Output: 45
 *
 * Examples - 3
 *		Input: array[]= {100, 85, 60, 45, 10, 5, 3}; // only decreasing
 *		Output: 100
 *
 * Examples - 4
 *		Input: array[]= {50, 100}; // only increasing
 *		Output: 100
 *
 * Examples - 5
 *		Input: array[]= {100, 50}; // only decreasing
 *		Output: 100
 *
 * Examples - 6
 *		Input: array[]= {100}; // only increasing
 *		Output: 100
 *
 *
 * https://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and-then-decreasing/
 * https://www.geeksforgeeks.org/find-bitonic-point-given-bitonic-sequence/?ref=rp
 * https://www.callicoder.com/maximum-element-in-bitonic-array/
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
 * */
public class BS16BitonicArrayMax {

    public static void main(String[] args) {

        int[] arr;

        arr = new int[]{4, 10, 15, 20, 45, 35, 29, 17, 10}; //increasing then decreasing
        System.out.printf("max = %d%n", bitonicPoint(arr));
        System.out.printf("max = %d%n", findMaxElement(arr)); // 45


        arr = new int[]{4, 10, 15, 20, 45}; // only increasing
        System.out.printf("max = %d%n", bitonicPoint(arr));
        System.out.printf("max = %d%n", findMaxElement(arr)); // 45

        arr = new int[]{100, 85, 60, 45, 10, 5, 3}; // only decreasing
        System.out.printf("max = %d%n", bitonicPoint(arr));
        System.out.printf("max = %d%n", findMaxElement(arr)); // 100

        arr = new int[]{50, 100}; // only increasing
        System.out.printf("max = %d%n", bitonicPoint(arr));
        System.out.printf("max = %d%n", findMaxElement(arr)); // 100

        arr = new int[]{100, 50}; // only decreasing
        System.out.printf("max = %d%n", bitonicPoint(arr));
        System.out.printf("max = %d%n", findMaxElement(arr)); // 100

        arr = new int[]{100}; // only increasing
        System.out.printf("max = %d%n", bitonicPoint(arr));
        System.out.printf("max = %d%n", findMaxElement(arr)); // 100

        arr = new int[]{8, 10, 100, 400, 500, 3, 2, 1};
        System.out.printf("max = %d%n", bitonicPoint(arr)); // 500
        System.out.printf("max = %d%n", findMaxElement(arr)); // 100
    }

    /*
     * ================  [Naive Approach] Using Linear Search - O(n) Time and O(1) Space =====================
     *
     *  A simple approach is to iterate through the array and keep track of the maximum element occurred so far.
     *  Once the traversal is complete, return the maximum element.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int bitonicPoint(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++)
            max = Math.max(max, arr[i]);

        return max;
    }

    public static int findMaxElement(int[] arr) {
        int maxIndex = peakIndexInMountainArray(arr);
        return  maxIndex == -1 ? maxIndex : arr[maxIndex];
    }

    /*
    *
    * refer this - BS14PeakElement#peakElement()
    * */
    public static int peakIndexInMountainArray(int[] arr) {

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
        int start = 1;
        int end = n - 2;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1])
                return mid;
            if (arr[mid - 1] > arr[mid])    // decreasing segment
                end = mid - 1;
            else                            // increasing segment
                start = mid + 1;
        }
        return -1;
    }
}
