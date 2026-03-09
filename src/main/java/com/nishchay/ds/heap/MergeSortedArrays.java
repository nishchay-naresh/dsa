package com.nishchay.ds.heap;

import com.nishchay.ds.array.a10medium.MergeTwoSortedArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 *============== k largest(or smallest) elements in an unsorted array ====================
 *
 * Given a 2D matrix, mat[][] consisting of sorted arrays, where each row is sorted in non-decreasing order,
 * find a single sorted array that contains all the elements from the matrix.
 *
 * Examples:
 *				Input: mat[][] = [[1, 3, 5, 7], [2, 4, 6, 8], [0, 9, 10, 11]]
 *				Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
 *				Explanation: Merging all elements from the 3 sorted arrays and sorting them results in: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].
 *
 *				Input: mat[][] = [[1, 2, 3, 4], [2, 2, 3, 4], [5, 5, 6, 6], [7, 8, 9, 9]]
 *				Output: [1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9, 9]
 *				Explanation: Merging all elements from the 4 sorted arrays and sorting them results in:[1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9, 9].
 *
 * https://www.geeksforgeeks.org/dsa/merge-k-sorted-arrays/
 * */
public class MergeSortedArrays {

    public static void main(String[] args) {

        int[][] mat = {
                {1, 3, 5, 7},
                {2, 4, 6, 8},
                {0, 9, 10, 11}
        };


        System.out.println("mergeSortedMatrix_sorting(mat) = " + mergeSortedMatrix_sorting(mat));
        System.out.println("mergeSortedMatrix_merging(mat) = " + Arrays.toString(mergeSortedMatrix_merging(mat)));
    }

    /*
     * ======================================== [Naive Approach] Concatenate all and Sort ======================================
     * The idea is to merges sorted arrays by first flattening all the arrays into a single one-dimensional array.
     * Then sorts this combined array using the standard sorting algorithm (sort() from the Arrays).
     * This ensures the final output is a fully sorted array containing all elements from the input arrays.
     *
     * Time complexity: O(n * log n), where n is total number of elements
     * Auxiliary Space: O(n)
     * */
    private static ArrayList<Integer> mergeSortedMatrix_sorting(int[][] mat) {
        ArrayList<Integer> result = new ArrayList<>();

        // Append all arrays into result
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                result.add(mat[i][j]);
            }
        }

        // Sort the result
        Collections.sort(result);
        return result;
    }

    /*
     * ======================================== [Improved Approach] Incremental Two-Array Merge ======================================
     * we weill utilize com.nishchay.ds.array.a10medium.MergeTwoSortedArray.mergeSortedArray(-,-) to solve this problem
     *	1. Take the first row as the initial sorted array
     *	2. Merge it with the second row
     *	3. Merge the result with the third row
     *	4. Continue until all rows are merged
     *	This works because:
     *		-	Each row is already sorted
     *		-	Merging two sorted arrays keeps the result sorted
     *
     * Time complexity: O(n + m), where n roes and m columns
     * Auxiliary Space: O(n + m)
     * */
    public static int[] mergeSortedMatrix_merging(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[0];
        }
        int[] result = mat[0];

        for (int i = 1; i < mat.length; i++) {
            result = MergeTwoSortedArray.mergeSortedArray(result, mat[i]);
        }
        return result;
    }
}
