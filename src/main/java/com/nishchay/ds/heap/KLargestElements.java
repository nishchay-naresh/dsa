package com.nishchay.ds.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 *============== k largest(or smallest) elements in an unsorted array ====================
 *
 * Given an array arr[] and an integer k, the task is to find k largest elements in the given array.
 * Elements in the output array should be in decreasing order.
 *
 * Write an efficient program for printing k largest elements in an array. Elements in an array can be in any order.
 *
 * Examples:
 *				Input: [1, 23, 12, 9, 30, 2, 50], k = 3
 *				Output: [50, 30, 23]
 *
 *				Input:  [11, 5, 12, 9, 44, 17, 2], k = 2
 *				Output: [44, 17]
 *
 *		        Input: arr[] = {1, 23, 12, 9, 30, 2, 50}, k = 4
 *		        Output: {50, 30, 23, 12}
 *
 *		        Input: arr[] = {7, 10, 4, 3, 20, 15}, k = 3
 *		        Output: {20, 15, 10}
 *
 *
 * https://www.geeksforgeeks.org/dsa/k-largestor-smallest-elements-in-an-array/
 * */
public class KLargestElements {


    public static void main(String[] args) {

        int[] arr, res;
        int k;

        arr = new int[]{1, 23, 12, 9, 30, 2, 50};
        k = 3;
        System.out.print("kLargest - " + kLargest(arr, k)); // [50, 30, 23]

        arr = new int[]{1, 23, 12, 9, 30, 2, 50};
        k = 4;
        res = kLargestUsingHeap(arr, k);
        System.out.println("K largest elements " + Arrays.toString(res)); // [50, 30, 23, 12]

        arr = new int[]{7, 10, 4, 3, 20, 15};
        k = 3;
        res = kLargestUsingHeap(arr, k);
        System.out.println("K largest elements " + Arrays.toString(res)); // [20, 15, 10]
    }

    /*
     * ======================================== [Naive Approach] Using Sorting ======================================
     * The idea is to sort the input array in descending order, so the first k elements in the array will be the k largest elements.
     *
     * Time complexity: O(n * log n)
     * Auxiliary Space: O(n)
     * */
    private static ArrayList<Integer> kLargest(int[] arr, int k) {
        int n = arr.length;

        // Convert int type to Integer for sorting with a comparator
        Integer[] arrInteger = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        // Sort the array in descending order
        Arrays.sort(arrInteger, Collections.reverseOrder());

        // Store the first k elements in result list
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++)
            res.add(arrInteger[i]);

        return res;
    }

    /*
     * ======================================== [Expected Approach] Using Priority Queue(Min-Heap) ======================================
     * The idea is, as we iterate through the array, we keep track of the k largest elements at each step.
     * To do this, we use a min-heap. First, we insert the initial k elements into the min-heap.
     *
     * After that, for each next element, we compare it with the top of the heap.
     * Since the top element of the min-heap is the smallest among the k elements,
     *      if the current element is larger than the top, it means the top element is no longer one of the k largest elements.
     *      In this case, we remove the top and insert the larger element.
     * After completing the entire traversal, the heap will contain exactly the k largest elements of the array.
     *
     * Time complexity - O(n * log k), this solution can work in O(k + (n-k) Log K) as build heap take linear time.
     * Space complexity - O(k)
     * */
    private static int[] kLargestUsingHeap(int[] arr, int k) {

        int[] res = new int[k];

        // minHeap, bcus need largest element
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int e : arr) {
            minHeap.add(e);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        for (int i = k - 1; i >= 0; i--)
            res[i] = minHeap.poll();

        return res;
    }

}
