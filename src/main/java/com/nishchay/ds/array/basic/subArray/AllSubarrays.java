package com.nishchay.ds.array.basic.subArray;

/*
 *  ======================= Generating All Subarrays ====================
 *
 *  Given an array arr[], the task is to generate all the possible subarrays of the given array.
 *
 * Examples:
 *			Input: arr[] = [1, 2, 3]
 *			Output: [ [1], [1, 2], [2], [1, 2, 3], [2, 3], [3] ]
 *
 *			Input: arr[] = [1, 2]
 *			Output: [ [1], [1, 2], [2] ]
 *
 *
 * https://www.geeksforgeeks.org/dsa/generating-subarrays-using-recursion/
 * */

public class AllSubarrays {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3};
        getSubArrays(arr);
        System.out.println("-----------------------------------------");
        arr = new int[]{1, 2};
        getSubArrays(arr);
    }

    /*
     * ================ [Naive/Bruteforce Approach] Iterative Approach - O(n^2) Time and O(1) Space =====================
     *
     *  To generate a subarray- we will iterate through 2 nested loops
     *
     *      outer loop : run a loop from [0 to n-1]
     *          inner loop : run a loop from [i to n-1]
     *              innermost loop(optional if not printing) : to print the elements in this subarray.
     *
     * Outermost Loop: Picks starting index of current subarray
     * Middle Loop: Picks ending index of current subarray
     * Innermost Loop: Prints the subarray from the starting index to the ending index
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     */
    private static void getSubArrays(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                // Print subarray between current starting and ending points
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
            }
        }
    }
}
