package com.nishchay.ds.array.a06sort;

import java.util.Arrays;

public class MergeSort {

    // Driver code
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        System.out.println("Original Array - " + Arrays.toString(arr));

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("Sorted array - " + Arrays.toString(arr));
    }

    /*
     * Merge Sort is a Divide and Conquer algorithm.
     * It solves a big problem by breaking it into smaller problems, solving those, and then combining the results.
     *
     * 1. Divide
     * 	If the array has 0 or 1 element → it’s already sorted.
     * 	Otherwise:
     * 	Split the array into two halves:
     * 		Left half
     * 		Right half
     *
     * 2. Conquer (Recursive Step)
     * 	Recursively apply merge sort on the left half.
     * 	Recursively apply merge sort on the right half.
     * 	Each half keeps splitting until it reaches size 1.
     *
     * 3. Merge
     * 	After both halves are sorted independently, merge them into a single sorted array.
     *
     * */
    public void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

}
