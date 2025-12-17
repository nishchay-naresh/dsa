package com.nishchay.ds.array.a03minmax;

import com.nishchay.ds.array.ArrayUtils;

import java.util.Arrays;

/*
 * =========== Find second largest element in an array ===========
 *
 *  Examples :
 *	        Input: arr[] = {12, 35, 1, 10, 34, 1}
 *	        Output: 34
 *          Explanation: The largest element of the array is 35 and the second largest element is 34.
 *
 *          Input: arr[] = {7,2,1,7,4,5}
 *	        Output: 5
 *          Explanation: The largest element of the array is 10 and the second largest element is 5.
 *
 *	        Input: arr[] = {10, 5, 10}
 *	        Output: 5
 *          Explanation: The largest element of the array is 10 and the second largest element is 5.
 *
 *	        Input: arr[] = {10, 10, 10}
 *          Output: -1
 *          Explanation: The largest element of the array is 10 there is no second largest element.
 *
 * https://www.geeksforgeeks.org/dsa/find-second-largest-element-array/
 * */
public class SecondLargestElement {

    public static void main(String[] args) {

        int[] arr;

        arr = new int[]{7, 2, 1, 7, 4, 5};
        System.out.println("Second largest element = " + getSecondLargest(arr));
        System.out.println("Second largest element = " + getSecondLargest_2pass(arr));
        System.out.println("Second largest element = " + getSecondLargest_1pass(arr));

        System.out.print("----------------------------------------------------");
        arr = new int[]{12, 35, 1, 10, 34, 1};
        System.out.printf("\n%s array is having second largest element = %d", Arrays.toString(arr), getSecondLargest_1pass(arr));

        arr = new int[]{10, 5, 10};
        System.out.printf("\n%s array is having second largest element = %d", Arrays.toString(arr), getSecondLargest_1pass(arr));

        arr = new int[]{10, 10, 10};
        System.out.printf("\n%s array is having second largest element = %d", Arrays.toString(arr), getSecondLargest_1pass(arr));

        arr = new int[]{100};
        System.out.printf("\n%s array is having second largest element = %d", Arrays.toString(arr), getSecondLargest_1pass(arr));

        arr = new int[]{};
        System.out.printf("\n%s array is having second largest element = %d", Arrays.toString(arr), getSecondLargest_1pass(arr));

    }

    /*
     * ============ [Naive/Bruteforce Approach] Using Sorting ===============
     *  1. Sort the array in ascending order
     *  2. Check for the second largest element: n-2 to 0
     *          return the first element which is not equal to the largest element
     *      else
     *          return -1;
     *
     *   Time Complexity  : O(n*log(n)) + n
     *   Space Complexity : 1
     *
     * */
    static int getSecondLargest(int[] arr) {
        int n = arr.length;

        Arrays.sort(arr);

        // Check for the second largest element: n-2 to 0
        for (int i = n - 2; i >= 0; i--) {

            // return the first element which is not equal to the largest element
            if (arr[i] != arr[n - 1]) {
                return arr[i];
            }
        }
        return -1;
    }

    /*
     * ============ [Better Approach] Two Pass Approach ===============
     *  The approach is to traverse the array twice.
     *      In the first traversal, find the maximum element.
     *      In the second traversal, find the maximum element ignoring the one we found in the first traversal.
     *
     *   Time Complexity  : n + n = 2n = O(n)
     *   Space Complexity : 1
     *
     * */
    static int getSecondLargest_2pass(int[] arr) {

        // In the first traversal, find the maximum element.
        int largest = ArrayUtils.findMax(arr);
        int secondLargest = Integer.MIN_VALUE; // assuming

        // In the first traversal, finding the secondLargest largest element
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > secondLargest && arr[i] < largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    /*
     * ============ [Optimal Approach] One Pass Approach ===============
     *  Using the same logic of finding the largest element in an array
     *   Using this fact  : if the largest is getting updated during the scan, its previous value become second largest
     *
     *   Time Complexity  : O(n)
     *   Space Complexity : O(1)
     * */
    private static int getSecondLargest_1pass(int[] arr) {

        int length = arr.length;
        int largest, secondLargest;

        if (length < 2) {
            System.out.println("Invalid Input ");
            return -1;
        }

        largest = arr[0];
        secondLargest = Integer.MAX_VALUE;
        for (int i = 1; i < length - 1; i++) {
            // if current element is smaller than largest then update both largest and secondLargest
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            }
            // if arr[i] is in between largest and secondLargest then update secondLargest
            else if (arr[i] > secondLargest && arr[i] != largest)
                secondLargest = arr[i];
        }
        if (secondLargest == Integer.MAX_VALUE) {
            return -1;
        }
        return secondLargest;
    }
}
