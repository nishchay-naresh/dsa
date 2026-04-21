package com.nishchay.ds.array.a05merge;

import java.util.*;
import java.util.stream.Collectors;

/*
 *  ==================================== Merge K sorted arrays ====================================
 *
 * Given a 2D matrix, mat[][] consisting of sorted arrays,
 * Where each row is sorted in non-decreasing order,
 * Find a single sorted array that contains all the elements from the matrix.
 *
 * Examples:
 *				Input: mat[][] = [
 *									[1, 3, 5, 7],
 *									[2, 4, 6, 8],
 *									[0, 9, 10, 11]
 *								 ]
 *				Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
 *				Explanation: Merging all elements from the 3 sorted arrays and sorting them results.
 *
 *				Input: mat[][] = [
 *									[1, 2, 3, 4],
 *									[2, 2, 3, 4],
 *									[5, 5, 6, 6],
 *									[7, 8, 9, 9]
 *								 ]
 *				Output: [1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9, 9]
 *				Explanation: Merging all elements from the 4 sorted arrays and sorting them results.
 *
 * https://www.geeksforgeeks.org/dsa/merge-k-sorted-arrays/
 * */
public class MergeKSortedArray {

    public static void main(String[] args) {

        int[][] mat = new int[][]{
                {1, 3, 5, 7},
                {2, 4, 6, 8},
                {0, 9, 10, 11}
        };

        System.out.println("mergeArrays_sort(mat)     = " + mergeArrays_sort(mat));
        System.out.println("mergeArrays_merging(mat)  = " + mergeArrays_merging(mat));

        heapEx();
    }

    private static void heapEx() {
        int[][] data = {
                {5, 8, 15, 20},              // Row 1
                {6, 7, 9, 12, 22, 25, 36},   // Row 2
                {1, 8},                      // Row 3
                {8, 10, 11, 22, 25},         // Row 4
                {16},                        // Row 5
                {6, 9, 12, 13, 18}           // Row 6
        };
        ArrayList<Integer> mergedAscendingOrderData = mergeArrays_heap(data);
        System.out.println("mergedAscendingOrderData = " + mergedAscendingOrderData);
        // Output: [1, 5, 6, 6, 7, 8, 8, 8, 9, 9, 10, 11, 12, 12, 13, 15, 16, 18, 20, 22, 22, 25, 25, 36]
    }


    /*
     * ================ [Naive Approach] Concatenate all and Sort =====================
     *
     * Merges sorted arrays by first flattening all the arrays into a single list
     * Then sorts this combined single list using the standard sorting algorithm.
     * This ensures the final output is a fully sorted containing all elements from the input arrays.
     *
     *  Time Complexity     = O(n log(n)), where n is total number of elements
     *  Space complexity    = O(n)
     */
    private static List<Integer> mergeArrays_sort(int[][] mat) {
        ArrayList<Integer> mergedList = new ArrayList<>();

        // Append all arrays into mergedList
        for (int[] rows : mat) {
            for (int cells : rows) {
                mergedList.add(cells);
            }
        }

        mergedList.sort(Integer::compareTo);
        return mergedList;
    }

    /*
     * ======================================== [Another Different Approach] Incremental Two-Array Merge ======================================
     * we will utilize com.nishchay.ds.array.a05merge.MergeTwoSortedArray.mergeSortedArray(-,-) to solve this problem
     *	1. Take the first row as the initially sorted array
     *	2. Merge it with the second row
     *	3. Merge the result with the third row
     *	4. Continue until all rows are merged
     *	This works because:
     *		-	Each row is already sorted
     *		-	Merging two sorted arrays keeps the result sorted
     *
     * Time complexity: O(c * r²), where r rows and c columns
     *                  O(N * r)   (since N = r*c)
     * Auxiliary Space: O(n + m)
     *
     *
     * Why it's worse?
     * Because: You keep re-merging already processed data again and again
     * */

    public static List<Integer> mergeArrays_merging(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new ArrayList<>();
        }
        int[] result = mat[0];

        for (int i = 1; i < mat.length; i++) {
            result = MergeTwoSortedArray.mergeSortedArray(result, mat[i]);
        }

        return Arrays.stream(result)
                .boxed()
                .collect(Collectors.toList());
    }

    /*
     * ================ [Expected Approach] Using Min-Heap - Works better for Different Sized Arrays ================
     *
     * The idea is to merge sorted arrays using a Min Heap.
     * We start by inserting the first element of each array into the heap.
     * The smallest element is always at the top, so we remove it and add it to the output array.
     * Then we insert the next element from the same array into the heap.
     * We repeat this process until the heap is empty.
     * This ensures that we always pick the smallest available element, producing a fully sorted merged array efficiently.
     *
     *
     * Then we insert the next element from the same array into the heap.
     *      To do this we need two details = {processedElement, arayIndex, elementIndex}
     *      Keeping them in an integer array of size 3, we will create an PQ for such array
     *
     *      Min-heap:   of int[3] {value, array index, element index},
     *                  comparison logic is based on arr[0]/value
     *                  Integer.campare(arr -> arr[0])
     *
     *
     * Time complexity: O(n*log(k)), k is number of rows and n is total number of elements.
     * Auxiliary Space: O(k)
     * */
    static ArrayList<Integer> mergeArrays_heap(int[][] mat) {
        int k = mat.length;

        ArrayList<Integer> output = new ArrayList<>();

        // Min-heap: {value, {array index, element index}}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Push first element of each array
        for (int i = 0; i < k; ++i) {
            if (mat[i].length > 0) {
                minHeap.add(new int[]{mat[i][0], i, 0});
            }
        }

        // Merge all elements
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int val = top[0];
            int arayIndex = top[1];
            int elementIndex = top[2];

            output.add(val);

            // Push next element from same array
            if (elementIndex + 1 < mat[arayIndex].length) {
                minHeap.add(new int[]{mat[arayIndex][elementIndex + 1], arayIndex, elementIndex + 1});
            }
        }
        return output;
    }
}
