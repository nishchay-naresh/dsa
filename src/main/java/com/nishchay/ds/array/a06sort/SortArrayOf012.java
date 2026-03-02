package com.nishchay.ds.array.a06sort;

import java.util.Arrays;

/*
 *	========================== The Dutch national flag problem(DNF) ===================================
 *
 * Given an array arr[] consisting of only 0's, 1's, and 2's. The goal is to sort an array,
 * i.e., put all 0s first, then all 1s and all 2s in last.
 *
 * This problem is the same as the famous "Dutch National Flag problem".
 * The problem was proposed by Edsger Dijkstra. The problem is as follows:
 *      Given n balls of color red, white or blue arranged in a line in random order.
 *      You have to arrange all the balls such that the balls with the same color are adjacent with the order of the balls,
 *      with the order of the color being red, white and blue
 *  (i.e., all red color balls come first then the white color balls and then the blue color balls).
 *
 * Examples:
 *				Input: arr[] = [0, 1, 2, 0, 1, 2]
 *				Output: [0, 0, 1, 1, 2, 2]
 *				Explanation: [0, 0, 1, 1, 2, 2] has all 0s first, then all 1s and all 2s in last.
 *
 *				Input: arr[] = [0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1]
 *				Output: [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]
 *				Explanation: {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2} has all 0s first, then all 1s and all 2s in last.
 *
 * https://www.geeksforgeeks.org/dsa/sort-an-array-of-0s-1s-and-2s/
 * https://www.educative.io/edpresso/the-dutch-national-flag-problem-in-cpp
 * https://leetcode.com/problems/sort-colors/description/
 * */
public class SortArrayOf012 {

    public static void main(String[] args) {

        int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};

        System.out.println("Original array = " + Arrays.toString(arr));
        sort012_sort(arr);
        System.out.println("  Sorted array = " + Arrays.toString(arr));

        System.out.println("Original array = " + Arrays.toString(arr));
        sort012_count(arr);
        System.out.println("  Sorted array = " + Arrays.toString(arr));

        System.out.println("Original array = " + Arrays.toString(arr));
        sort012_3Pointers(arr);
        System.out.println("  Sorted array = " + Arrays.toString(arr));

    }


    /*
     * ================  [Naive Approach] Sorting - O(n × log(n)) Time and O(1) Space  =====================
     *
     * The naive solution is to simply sort the array using a standard sorting algorithm or sort library function.
     * This will simply place all the 0s first, then all 1s and 2's at last.
     *
     *  Time Complexity     : O(n × log(n))
     *  Space complexity    : O(1)
     */
    private static void sort012_sort(int[] arr) {
        Arrays.sort(arr);
    }

    /*
     * ================  [Better Approach] Counting 0's, 1's, and 2's - Two Pass  =====================
     *
     * A better solution:
     *  Traverse the array once - count number of 0's, 1's, and 2's, say c0, c1 and c2 respectively.
     *  Traverse the array again, put c0 (count of 0's) 0's first, then c1 1's and finally c2 2's.
     *  This solution works in O(n) time, but it requires two traversals of the array.
     *
     *  Time Complexity     : O(n) + O(n) = O(n)
     *  Two traversals of the array are needed.
     *  Space complexity    : O(1)
     */
    private static void sort012_count(int[] arr) {
        int count0, count1, count2;
        count0 = count1 = count2 = 0;
        for (int e : arr) {
            if (e == 0) count0++;
            else if (e == 1) count1++;
            else if (e == 2) count2++;
        }

        int k = 0;
        for (int i = 0; i < count0; i++) {
            arr[k] = 0;
            k++;
        }
        for (int i = 0; i < count1; i++) {
            arr[k] = 1;
            k++;
        }
        for (int i = 0; i < count2; i++) {
            arr[k] = 2;
            k++;
        }
    }

    /*
     * ================  [Expected Approach] Using 3 pointers - One Pass - O(n) Time and O(1) Space  =====================
     *
     * The problem is similar to "Segregate 0's, 1's in an array".
     * The idea is to sort the array of size n using three pointers: lo = 0, mid = 0 and hi = n - 1
     *
     *  Here keeping 3 pointers (hi, low & mid), initializing them as
     *      lo = mid = 0,   high = n - 1
     *      mid is the traversing pointer
     *  while (mid <= low)
     *      if array[mid] == 0,    swap(lo, mid); lo++; mid++;
     *      if array[mid] == 1,    do nothing simply mid++;;
     *      if array[mid] == 2,    swap(mid, high); high--;
     *
     *
     *	Time Complexity     :  O(n)
     *	Only One traversals of the array is needed.
     *	Space complexity    : O(1)
     *
     *
     * why doing a lo++ while swapping with low, but not doing a lo++ whiling swaping with high
     * The element at lo (before swap) must have been:
     *  either 0 (safe) or 1 (also safe, because 1 belongs in a middle region), It can never be a 2
     *
     * */
    private static void sort012_3Pointers(int[] arr) {

        int lo, high, mid;
        lo = mid = 0;
        high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 0) {
                swap(arr, lo, mid);
                lo++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else if (arr[mid] == 2) {
                swap(arr, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
