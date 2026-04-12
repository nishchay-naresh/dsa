package com.nishchay.algo.bsearch.a01basic;

import java.util.Arrays;

/*
 *	=========== Order-agnostic Binary Search ===========
 *
 *  Given a sorted array of numbers, find if a given number key is present in the array.
 *  Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
 *
 *
 *	Example 1:
 *		Input: [2, 8, 11, 19], key = 11
 *		Output: 2
 *
 *	Example 2:
 *		Input: [32, 28, 17, 9, 3], key = 9
 *		Output: 3
 *
 *
 * https://www.geeksforgeeks.org/dsa/order-agnostic-binary-search/
 * https://www.callicoder.com/order-agnostic-binary-search/
 *
 * */
public class BS4OrderAgnosticSearch {

    public static void main(String[] args) {

        int[] arr;
        int key;

        arr = new int[]{2, 8, 11, 19};
        key = 11;
        System.out.printf("orderAgnosticBS(%s, %d) = %d%n", Arrays.toString(arr), key, orderAgnosticBS(arr, key));

        arr = new int[]{32, 28, 17, 9, 3};
        key = 3;
        System.out.printf("orderAgnosticBS(%s, %d) = %d%n", Arrays.toString(arr), key, orderAgnosticBS(arr, key));


        arr = new int[]{20, 17, 15, 14, 12, 9, 8, 4, 3, 1};
        key = 9;
        System.out.printf("orderAgnosticBS(%s, %d) = %d%n", Arrays.toString(arr), key, orderAgnosticBS(arr, key));

    }

    /*
     * =========== Order-agnostic Binary Search Implementation ===========
     *
     * The implementation is similar to binary search
     * Except that We need to identify whether the array is sorted in ascending order or descending order
     *
     * ascending sorted array -> have logic for this
     * descending sorted array -> also have logic for this
     *
     * we can determine this ascending/descending by comparing first 2 elements of array
     * if(arr.length == 1){
     *      return arr[0] == key ? 0 : -1
     * }else{
     *      return arr[0]<arr[1]? BSSortedArray() : BSReverseSortedArray();
     * }
     *
     * */

    private static int orderAgnosticBS(int[] arr, int key) {
        if (arr.length == 1) {
            return arr[0] == key ? 0 : -1;
        }

        if(arr[0] < arr[1]) // ascending order
            return BS01SearchAlgorithms.binarySearchIterative(arr, key);

        // descending order
        return BS2ReverseSortedArray.binarySearchReverse(arr, key);
    }

}
