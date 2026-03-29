package com.nishchay.ds.array.a06sort;

import java.util.Arrays;


/*
 *===================================== Sorting Algorithms =====================================
 *
 * Sorting Algorithms:
 * Comparison Based : Selection Sort, Bubble Sort, Insertion Sort, Merge Sort, Quick Sort, Heap Sort, Cycle Sort, 3-way Merge Sort
 * Non Comparison Based : Counting Sort, Radix Sort, Bucket Sort, Pigeonhole Sort
 * Hybrid Sorting Algorithms : IntroSort, TimSort
 *
 *
 * Time Complexities of Sorting Algorithms:
 *      Selection Sort, Bubble Sort, Insertion Sort	- O(n^2)
 *      Heap Sort, Quick Sort, Merge Sort 				- O(n log n)
 *
 * Arrays.sort() for Primitive Types
 *  -   Algorithm Used: Dual-Pivot Quicksort
 *  -   Uses two pivots instead of one
 *
 * Arrays.sort() for Object Types
 *  -   Algorithm Used: TimSort
 *  -   Hybrid of Merge Sort + Insertion Sort
 *
 *
 * https://www.geeksforgeeks.org/dsa/sorting-algorithms/
 *
 * */
public class AlgorithmsSorting {

    public static void main(String[] args) {

        int[] arr = {50, 3, 11, 9, 7, 6, 2};

        System.out.println("original array = " + Arrays.toString(arr));
        bubbleSort(arr);
        selectionSort(arr);
        System.out.println("sorted array = " + Arrays.toString(arr));

    }

    /*
     * Finding the largest element with each iteration
     * Like - the way bubble is coming out of water
     * */
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swipe arr[j] & arr[j+1]
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }

    /*
     * - finding the smallest element in the array
     * - swape with the first element for the array
     * */
    private static void selectionSort(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {

            int jmin = i;
            for (int j = i + 1; j < size; j++)
                if (arr[j] < arr[jmin])
                    jmin = j;

            if (jmin != i) {
                // swipe arr[jmin] & arr[i]
                int t = arr[jmin];
                arr[jmin] = arr[i];
                arr[i] = t;
            }
        }
    }


}
