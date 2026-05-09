package com.nishchay.algo.bsearch.a09hard;

import com.nishchay.algo.bsearch.a01basic.BS01SearchAlgorithms;

/*
 *============== Find key in a sorted infinite array ====================
 *
 *
 * Search in a sorted infinite array
 *	Given an infinite sorted array (or an array with unknown size), Find if a given target value is present in the array.
 *   Write a function to return the index of the target if it is present in the array, otherwise return -1.
 *
 *	Example 1:
 *		Input: [2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28. 32, 35], target = 16
 *		Output: 7
 *		Explanation: The target is present at index '7' in the array.
 *
 *	Example 2:
 *		Input: [2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28. 32, 35], target = 19
 *		Output: -1
 *		Explanation: The target not present in the array
 *
 *
 * https://www.callicoder.com/search-in-sorted-infinite-array/
 * https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/
 * */
public class BS11SortedInfiniteArraySearch {

    public static void main(String[] args) {

        int key;
        int[] arr;

        arr = new int[]{2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28, 32, 35};
        key = 16;
        int ans = findPos(arr, key);
        if (ans == -1)
            System.out.print("Element not found");
        else
            System.out.printf("%d found at index = %d%n", key, ans);// 7

        key = 19;
        ans = findPos(arr, key);
        if (ans == -1)
            System.out.print("Element not found");
        else
            System.out.println("Element found at index " + ans); // 7

    }



    /*
     *
     * ================ Binary Search in infinite array =======================
     *  Inputs :
     *          -   infinite
     *          -   sorted
     *
     *	1. First find the right bound
     *	2. Apply BS in array for left to right
     *
     *    int left = 0
     *    int right = 1
     *    while(key > arr[right]){
     *        left=right
     *        right=right * 2
     *    }
     *    BS(arr, left, right, key)
     *
     *
     *	Method takes an infinite size array and a key to be
     *	searched and returns its position if found else -1.
     *	We don't know the size of arr[], and we can assume size to be infinite in this function.
     *	NOTE THAT THIS FUNCTION ASSUMES arr[] TO BE OF INFINITE SIZE THEREFORE, THERE IS NO INDEX OUT OF BOUND CHECKING
     */

    private static int findPos(int[] arr, int key) {

        int left = 0;
        int right = 1;
        while (key > arr[right]) {
            left = right;
            //check that 2*h doesn't exceeds array length to prevent ArrayOutOfBoundException
            if (right * 2 < arr.length - 1)
                right = 2 * right;
            else
                right = arr.length - 1;
        }
        return BS01SearchAlgorithms.binarySearchRecursive(arr, left, right, key);

    }


}
