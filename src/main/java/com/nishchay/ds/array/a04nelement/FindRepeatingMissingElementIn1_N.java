package com.nishchay.ds.array.a04nelement;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *  ======================= Missing and Repeating in an Array ====================
 *
 * Given an unsorted array arr[] of size n, containing elements from the range 1 to n,
 * it is known that one number in this range is missing, and another number occurs twice in the array,
 * find both the duplicate number and the missing number.
 *
 * Examples:
 *				Input: arr[] = [3, 1, 3]
 *				Output: [3, 2]
 *				Explanation: 3 is occurs twice and 2 is missing.
 *
 *				Input: arr[] = [4, 3, 6, 2, 1, 1]
 *				Output: [1, 5]
 *				Explanation: 1 is occurs twice and 5 is missing.
 *
 *              Input: arr[] = {7, 3, 4, 5, 5, 6, 2}, n=7
 *	            Output: [1, 5]
 *              Explanation: 1 is occurs twice and 5 is missing.
 *
 * https://www.geeksforgeeks.org/dsa/find-a-repeating-and-a-missing-number/
 * https://leetcode.com/problems/set-mismatch/
 * */
class FindRepeatingMissingElementIn1_N {

    public static void main(String[] args) {

        int[] arr = new int[]{3, 1, 3};
        System.out.println(Arrays.toString(arr) + ", has repeating and missing no as - " + Arrays.toString(findTwoElement_auxiliaryArray(arr)));
        System.out.println(Arrays.toString(arr) + ", has repeating and missing no as - " + Arrays.toString(findTwoElement_sameArray(arr)));

        arr = new int[]{4, 3, 6, 2, 1, 1};
        System.out.println(Arrays.toString(arr) + ", has repeating and missing no as - " + Arrays.toString(findTwoElement_auxiliaryArray(arr)));
        System.out.println(Arrays.toString(arr) + ", has repeating and missing no as - " + Arrays.toString(findTwoElement_sameArray(arr)));

        arr = new int[]{7, 3, 4, 5, 5, 6, 2};
        System.out.println(Arrays.toString(arr) + ", has repeating and missing no as - " + Arrays.toString(findTwoElement_auxiliaryArray(arr)));
        System.out.println(Arrays.toString(arr) + ", has repeating and missing no as - " + Arrays.toString(findTwoElement_sameArray(arr)));
    }

    /*
     * ================ [Approach 1] Using Visited Array - O(n) Time and O(n) Space  =====================
     *
     *  The idea is to use a frequency array to keep track of how many times each number appears in the original array.
     *  Since we know the numbers should range from 1 to n with each appearing exactly once,
     *       any number appearing twice is our repeating number, and
     *       any number with zero frequency is our missing number.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int[] findTwoElement_auxiliaryArray(int[] arr) {
        int n = arr.length;

        // frequency array to count occurrences
        int[] freq = new int[n + 1];
        int repeating = -1;
        int missing = -1;

        // count the frequency of each element
        for (int val : arr) {
            freq[val]++;
        }

        // identify missing and repeating numbers
        for (int i = 1; i <= n; i++) {
            if (freq[i] == 0)
                missing = i;
            else if (freq[i] == 2)
                repeating = i;
        }

        return new int[]{repeating, missing};
    }

    /*
     * ================ [Approach 2] Using Array Marking - O(n) Time and O(1) Space  =====================
     * The main idea is to use the input array itself for tracking:
     *  it negates the value at the index corresponding to each element to mark it as visited.
     *      If it encounters a value that has already been negated, it identifies that number as the repeating one.
     *
     *      In a second pass, it finds the index that remains positive, which corresponds to the missing number.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int[] findTwoElement_sameArray(int[] arr) {
        int n = arr.length;
        int repeating = -1;

        for (int i = 0; i < n; i++) {
            int val = Math.abs(arr[i]);

            // if the value at index val - 1 is already negative (why -1, because index starts with 0 and number starts with 1)
            // it means we've seen this value before
            if (arr[val - 1] > 0) {
                arr[val - 1] = -arr[val - 1];
            } else {
                // if it's already negative, this value is
                // the repeating one
                repeating = val;
            }
        }

        int missing = -1;

        // after marking, the index with a positive value
        // corresponds to the missing number
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                missing = i + 1;
                break;
            }
        }

        // return result: first repeating, then missing
        return new int[]{repeating, missing};
    }
}