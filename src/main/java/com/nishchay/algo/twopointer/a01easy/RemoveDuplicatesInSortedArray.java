package com.nishchay.algo.twopointer.a01easy;

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
 * https://www.callicoder.com/remove-duplicates-from-sorted-array/
 * */
public class RemoveDuplicatesInSortedArray {

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5, 5};
        int newSize = removeDuplicates(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array = ");
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
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array = ");
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

        HashSet<Integer> set = new HashSet<>();

        // To maintain the new size of the array
        int idx = 0;

        for (int i = 0; i < arr.length; i++) {
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
                arr[idx] = arr[i];
                idx++;
            }
        }
        return idx;
    }

    /*
     * ================ [Optimal Approach] 2 pointer approach  =====================
     * Since the array is sorted, we do not need to maintain a hash set.
     * All occurrences of an element would be consecutive.
     * So we mainly need to check if the current element is the same as the previous element or not.
     *
     *  1st pointer - to track the unique elements      -- writerIndex
     *  2nd pointer - to scan all elements of array     -- i
     *
     * The first element arr[0] is always the right place, means it can't be duplicate, its unique
     *
     *  Traverse the array from 1st index, check each element
     *      check if its equivalent to arr[writerIndex]
     *              keep moving i
     *      else
     *              move 'writerIndex'
     *              copy a[i] at a[writerIndex]
     *
     * Why returning writerIndex+1 - because 'writerIndex' is an index, we need to return the size of unique elements
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int removeDuplicates_2pointers(int[] arr) {
        int writerIndex = 0; // the first element is always unique

        // scanning the array from 1st index
        for (int i = 1; i < arr.length; i++) {
            if (arr[writerIndex] != arr[i]) {
                // Move unique index forward
                writerIndex++;
                // copying the next unique element
                arr[writerIndex] = arr[i];
            }
        }
        return (writerIndex + 1);
    }

}
