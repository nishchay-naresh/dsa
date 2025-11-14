package com.nishchay.ds.array.a01basic;


/*
 *  ======================= Minimum increment by k operations to make all equal ====================
 *
 * You are given an array of n-elements, and an integer k.
 * You have to find the number of operations needed to make all elements of array equal.
 * Where a single operation can increment an element by k.
 * If it is possible to make all elements equal print no of operation else print -1.
 *
 * Examples:
 *			Input : arr[] = {4, 7, 19, 16},  k = 3
 *			Output : 10
 *			Explanation: 4+(5*k), 7+(4*k), 19, 16+(1*k), k=3, minNoOfOperations = 5 + 4 + 1 = 10
 *                       19-4 = 15, check its divisibility by k 15%k==0 if so then 15/k= noOfOperations
 *
 *			Input : arr[] = { 21, 33, 9, 45, 63 }, k = 6
 *			Output : 24
 *			Explanation: 21+(7*k), 33+(5*k), 9+(9*k), 45+(3*k), 0 minNoOfOperations = 7 + 5 + 9 + 3 = 24
 *
 *			Input : arr[] = {4, 4, 4, 4}, k = 3
 *			Output : 0
 *			Explanation: all elements of array are already equal, so minNoOfOperations = 0
 *
 *			Input : arr[] = {4, 2, 6, 8}, k = 3
 *			Output : -1
 *			Explanation: it is not possible to make all elements equal, so minNoOfOperations = -1
 *
 *
 * https://www.geeksforgeeks.org/dsa/minimum-increment-k-operations-make-elements-equal/
 *
 * */

import com.nishchay.ds.array.ArrayUtils;

public class MakeElementEqual {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 7, 19, 16};
        int k = 3;
        System.out.println(minOps(arr, k));

        arr = new int[]{21, 33, 9, 45, 63};
        k = 6;
        System.out.println(minOps(arr, k));

        arr = new int[]{4, 4, 4, 4};
        k = 3;
        System.out.println(minOps(arr, k));

        arr = new int[]{4, 2, 6, 8};
        k = 3;
        System.out.println(minOps(arr, k));

    }

    /*
     * Approach:
     *  1. Find the max element in the array
     *  2. traverse the array and check for each element
     *      if((max-a[i])%k==0)
     *          noOfOperations for a[i] = operations required can be calculated by finding value of (max - a[i])/k for all elements.
     *          minNoOfOperations = minNoOfOperations + noOfOperations
     *      else
     *          return -1;
     *
     *  return minNoOfOperations;
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     * */
    private static int minOps(int[] arr, int k) {

        int max = ArrayUtils.findMax(arr);
        int minNoOfOperations = 0;

        // iterate for all elements
        for (int i = 0; i < arr.length; i++) {

            // check if element can make equal to max or not
            if ((max - arr[i]) % k != 0) {
                return -1;
            } else {
                minNoOfOperations = minNoOfOperations + (max - arr[i]) / k;
            }
        }

        // return result
        return minNoOfOperations;
    }
}

