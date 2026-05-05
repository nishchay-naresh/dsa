package com.nishchay.algo.bsearch.a04bitonic;

/*
 *============== Bitonic Array/ Bitonic Sequence ====================
 * Program to check if an array is bitonic or not
 * Given an array of N elements. The task is to check if the array is bitonic or not.
 *
 * A Bitonic Sequence is a sequence of n distinct elements that is first strictly increasing then after a point strictly decreasing.
 * 	-	Distinct elements
 * 	-	First strictly increasing then strictly decreasing
 * 	-	only strictly increasing, No decreasing
 * 	-	No increasing, only strictly decreasing
 *
 * 		Examples
 *				Input: array[]= {4,10,15,20,45,35,29,17,10}
 *				Output: true
 *				increasing then decreasing
 *
 *				Input: array[] = {4,10,15,20,45}
 *				Output: true
 *				only increasing, Edge case (No decreasing part)
 *
 *				Input: array[]= {100, 80, 60, 40, 20, 0}
 *				Output: true
 *				only decreasing, Edge case (No increasing part)
 *
 *				Input: array[]= {-3, 9, 7, 20, 17, 5, 1}
 *				Output: false
 *				not bitonic, two decreasing point
 *
 *				Input: array[]= {4,10,10,15,20,45}
 *				Output: false
 *				not bitonic, bcus of duplicates
 *
 *				Input: array[]= {4,10,15,20,45,35,29,55,66,44,24}
 *				Output: false
 *				not bitonic array, only 1 increasing and 1 decreasing should be there
 *
 *
 * https://www.geeksforgeeks.org/dsa/program-to-check-if-an-array-is-bitonic-or-not/
 * */
public class BS15CheckBitonic {

    public static void main(String[] args) {

        int[] arr;

        arr = new int[]{10};
        System.out.printf("isBitonic = %b%n", isBitonic(arr));

        arr = new int[]{4, 10, 15, 20, 45, 35, 29, 17, 10};
        System.out.printf("isBitonic = %b%n", isBitonic(arr));

        arr = new int[]{4, 10, 15, 20, 45};
        System.out.printf("isBitonic = %b%n", isBitonic(arr));

        arr = new int[]{100, 80, 60, 40, 20, 0};
        System.out.printf("isBitonic = %b%n", isBitonic(arr));

        arr = new int[]{-3, 9, 7, 20, 17, 5, 1};
        System.out.printf("isBitonic = %b%n", isBitonic(arr));

        arr = new int[]{4, 10, 10, 15, 20, 45};
        System.out.printf("isBitonic = %b%n", isBitonic(arr));

        arr = new int[]{4, 10, 15, 20, 45, 35, 29, 55, 66, 44, 24};
        System.out.printf("isBitonic = %b%n", isBitonic(arr));

    }

    /*
     *  ======= Check Bitonic logic =======
     *
     * iterate thought all the elements of array
     *  - Check for increasing sequence
     *       return true    - if array got over
     *  - Check for decreasing sequence
     *       return false   -  if array is not over yet
     *       return true    - if array got over
     *
     * Time Complexity: O(n), As we are traversing the array only once.
     * Auxiliary Space: O(1), As constant extra space is used.
     * */
    private static boolean isBitonic(int[] arr) {

        int n = arr.length;

        int i = 0;
        // Check for an increasing sequence
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        if (i == n - 1)
            return true;

        // Check for a decreasing sequence
        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }
        if (i == n - 1)
            return true;

        return false;
    }

}