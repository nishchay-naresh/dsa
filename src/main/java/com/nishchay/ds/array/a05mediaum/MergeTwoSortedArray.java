package com.nishchay.ds.array.a05mediaum;

import java.util.Arrays;

/*
 *  ==================================== Merge two sorted arrays ====================================
 *
 *  Given two sorted arrays arr1[] of size n and arr2[] of size m. Merge these two arrays.
 * There are two sorted array are there, you need to merge them in a third in sorted order
 *
 *   Input: arr1[] = [1, 3, 4, 5], arr2[] = [2, 4, 6, 8]
 *      Combined sorted array, result[] = [1, 2, 3, 4, 4, 5, 6, 8]
 *
 * Examples:
 *			Output: arr1[] = [1, 2, 3, 4], arr2[] = [4, 5, 6, 8]
 *			Explanation: Combined sorted array = [1, 2, 3, 4, 4, 5, 6, 8],
 *						 array arr1[] contains smallest 4 elements: 1, 2, 3, 4, and array arr2[] contains the remaining 4 elements: 4, 5, 6, 8.
 *
 *			Output: arr1[] = [4, 5, 7], arr2[] = [8, 8, 9]
 *			Explanation: Combined sorted array = [4, 5, 7, 8, 8, 9],
 *			             array arr1[] contains smallest 3 elements: 4, 5, 7, and array arr2[] contains the remaining 3 elements: 8, 8, 9.
 *
 * https://leetcode.com/problems/merge-sorted-array/description/
 * https://www.geeksforgeeks.org/dsa/merge-two-sorted-arrays/
 * */
public class MergeTwoSortedArray {

    public static void main(String[] args) {

        int[] arr1 = {46, 47, 86, 89, 92, 95, 101, 111};
        int[] arr2 = {15, 30, 42, 55, 63};
        System.out.println("arr1 - " + Arrays.toString(arr1));
        System.out.println("arr2 - " + Arrays.toString(arr2));
        System.out.println("merged array - " + Arrays.toString(mergeSortedArray(arr1, arr2)));

        arr1 = new int[]{1, 2, 3, 4};
        arr2 = new int[]{4, 5, 6, 8};
        System.out.println("arr1 - " + Arrays.toString(arr1));
        System.out.println("arr2 - " + Arrays.toString(arr2));
        System.out.println("merged array - " + Arrays.toString(mergeSortedArray(arr1, arr2)));
    }

    /*
     * ================ [Expected Approach] Two Pointer Merge =====================
     *
     * Merging of two sorted arrays to third one in sorted order
     * traverse in both of the arrays simultaneously
     *   compare each element, copy smaller element to result array
     *   then copy the remaining array to result array
     *
     *  Time Complexity     : O(n + m)
     *  Space complexity    : O(n + m)
     */
    private static int[] mergeSortedArray(int[] source1, int[] source2) {

        int i, j, k;
        final int n = source1.length;
        final int m = source2.length;
        int[] result = new int[n + m];

        i = j = k = 0;
        // merge elements in sorted order
        while (i < n && j < m) {

            if (source1[i] < source2[j]) {
                result[k] = source1[i];
                i++;
            } else {
                result[k] = source2[j];
                j++;
            }
            k++;
        }

        // copy remaining elements from source1
        while (i < n) {
            result[k] = source1[i];
            k++;
            i++;
        }

        // copy remaining elements from source2
        while (j < m) {
            result[k] = source2[j];
            k++;
            j++;
        }
        return result;
    }

}
