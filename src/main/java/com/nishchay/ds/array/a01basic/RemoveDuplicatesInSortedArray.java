package com.nishchay.ds.array.a01basic;

import java.util.Arrays;
import java.util.HashSet;

/*
 *  ======================= Remove duplicates from Sorted Array ====================
 *
 * Given a sorted array arr[] of size n, the goal is to rearrange the array so that all distinct elements appear at the beginning in sorted order.
 * Additionally, return the length of this distinct sorted subarray.
 *
 * 1. Count unique elements - return it
 * 2. Place these unique elements at the beginning of the same array starting from index 0
 *
 * Examples:
 *		Input: arr[] = [2, 2, 2, 2, 2]
 *		Output: [2]
 *		Explanation: All the elements are 2, So only keep one instance of 2.
 *
 *		Input: arr[] = [1, 2, 2, 3, 4, 4, 4, 5, 5]
 *		Output: [1, 2, 3, 4, 5]
 *
 *		Input: arr[] = [1, 1, 2, 2, 2, 3, 3]
 *		Output: [1, 2, 3]
 *
 *		Input: arr[] = [1, 2, 3]
 *		Output: [1, 2, 3]
 *		Explanation: No change as all elements are distinct.
 *
 * https://www.geeksforgeeks.org/dsa/remove-duplicates-sorted-array/
 * https://takeuforward.org/data-structure/remove-duplicates-in-place-from-sorted-array/
 *
 * */
public class RemoveDuplicatesInSortedArray {

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5, 5};
        int newSize = removeDuplicates(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array - ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();


        arr = new int[]{2, 2, 2, 2, 2};
        executeFor2PointerApproach(arr);

        arr = new int[]{1, 2, 2, 3, 4, 4, 4, 5, 5};
        executeFor2PointerApproach(arr);

        arr = new int[]{1, 2, 3};
        executeFor2PointerApproach(arr);

        arr = new int[]{1, 1, 2, 2, 2, 3, 3};
        executeFor2PointerApproach(arr);
    }

    private static void executeFor2PointerApproach(int[] arr) {
        int newSize = removeDuplicates_2pointers(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array - ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    /*
     * ================ [Naive/Bruteforce Approach] Using Hash Set  =====================
     *
     *  Using Hash Set - Works for an unsorted array as well
     *
     *  Traverse the array left to right
     *      check for duplicates using Hash Set
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int removeDuplicates(int[] arr) {

        HashSet<Integer> s = new HashSet<>();

        // To maintain the new size of the array
        int idx = 0;

        for (int i = 0; i < arr.length; i++) {
            if (!s.contains(arr[i])) {
                s.add(arr[i]);
                arr[idx] = arr[i];
                idx++;
            }
        }
        return idx;
    }

    /*
     * ================ [Optimal Approach] 2 pointer approach  =====================
     *
     *  1st pointer - to track the unique elements
     *  2nd pointer - to scan all elements of array
     *
     * element arr[0] is - always the right place, means it can't be duplicate, its unique
     *
     *  Traverse the array from 1st index, check each element
     *      check if its equivalent to arr[i]
     *              keep moving j
     *      else
     *              move i
     *              copy a[j] at a[i]
     *
     * why returning i+1 - because i is an index, we need to return the size of unique elements
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int removeDuplicates_2pointers(int[] arr) {
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                arr[i+1] = arr[j];
                i++;
            }
        }
        return (i + 1);
    }

}
