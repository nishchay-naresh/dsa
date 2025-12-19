package com.nishchay.ds.array.a04nelement;


/*
 *  ================= Find the only repetitive element between 1 to n-1 ================
 *
 * Given an array arr[] of size n filled with numbers from 1 to n-1 in random order.
 * The array has only one repetitive element. The task is to find the repetitive element.
 *
 *	Examples:
 *				Input: arr[] = [1, 3, 2, 3, 4]
 *				Output: 3
 *				Explanation: The number 3 is the only repeating element.
 *
 *				Input: arr[] = [1, 5, 1, 2, 3, 4]
 *				Output: 1
 *				Explanation: The number 1 is the only repeating element.
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-repetitive-element-1-n-1/
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 * */

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

class Find01_OnlyRepetitiveElementIn1_N {

    public static void main(String[] args) {

        int[][] input2D = {
                {1, 2, 3, 2, 4},                        // n-5, dup-2, missing-5
                {9, 8, 2, 6, 1, 8, 5, 3, 4, 7},         // n-10, dup-8, missing-10
                {1, 2, 3, 1, 5, 4},                     // n-6, dup-1, missing-6
                {1, 4, 3, 3, 2, 6},                     // n-6, dup-3, missing-5
                {9, 8, 2, 6, 1, 8, 5, 3, 4, 7},         // n-10, dup-8, missing-10
                {1, 2, 3, 1, 5, 4},                     // n-6, dup-1, missing-6
                {1, 5, 1, 2, 3, 4},                     // n-6, dup-1, missing-6
        };

        int[] outputs = {2, 8, 1, 3, 8, 1, 1, 3};
        boolean result = true;

        for (int i = 0; i < input2D.length; i++) {
            result = result && executeAllApproach(input2D[i], outputs[i]);
            if (!result)
                System.out.println("Test failed for: " + Arrays.toString(input2D[i]));
            else
                System.out.println("Test passed for: " + Arrays.toString(input2D[i]));
        }

    }

    private static boolean executeAllApproach(int[] input, int output) {
        List<Function<int[], Integer>> functionList = Arrays.asList(
                arr -> findDuplicate_2loop(arr),
                arr -> findDuplicate_sorting(arr),
                arr -> findDuplicate_hasSet(arr),
                arr -> findDuplicate_xor(arr),
                arr -> findDuplicate_sumFormula(arr),
                arr -> findDuplicate_elementsAsIndexes(arr)
        );
        for (Function<int[], Integer> currFn : functionList) {
            if (output != currFn.apply(input)) {
                return false;
            }
        }
        return true;
    }

    /*
     * ================ [Bruteforce Approach] Using Nested Loop- O(n^2) Time and O(1) Space  =====================
     *  Logic : Using 2 nested loop
     *
     *  The idea is to use two nested loops.
     *  The outer loop traverses through all elements and the inner loop check if the element picked by the outer loop appears anywhere else.
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     * [Better Approach 1]
     * Logic Using sorting - O(n Log n) Time and O(1) Space
     *
     */
    private static int findDuplicate_2loop(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j])
                    return arr[i];
            }
        }
        return -1;
    }

    /*
     * ================ [Better Approach 1] Sorting - O(n Log n) Time and O(1) Space  =====================
     *
     * Logic Using sorting - O(n Log n) Time and O(1) Space
     *
     * Time Complexity : O(n Log n) + O(n) = O(n Log n)
     * Space Complexity: O(1)
     *
     */
    private static int findDuplicate_sorting(int[] arr) {
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 1; i++) {

            // If the adjacent elements are equal
            if (arr[i] == arr[i + 1]) {
                return arr[i];
            }
        }
        return -1;
    }

    /*
     * ================ [Better Approach 2] Hash Set - O(n) Time and O(n) Space  =====================
     *
     * Logic  - Using HashSet
     * Use a HastSet to store elements visited. If an already visited element appears again, return it.
     *
     *
     * Time Complexity : O(n)
     * Space Complexity: O(n)
     */
    private static int findDuplicate_hasSet(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int x : arr) {
            // If the element is already in the set
            if (set.contains(x))
                return x;
            set.add(x);
        }
        return -1;
    }


    /*
     * ================ [Expected Approach 1] Using sum formula - O(n) Time and O(1) Space =====================
     *
     * As we know - sum of first n natural numbers is = (n * (n + 1)) / 2, we will use this
     *
     * We compute sum of array elements and subtract a natural number sum from it to find the duplicate element.
     * Logic:
     *          sumOfN = (n * (n + 1)) / 2;
     *          ArraySum = sum of all elements in arr (sumOfN + one duplicate)
     *          duplicate = ArraySum - sumOfN
     *
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * */
    private static int findDuplicate_sumFormula(int[] arr) {

        int n = arr.length - 1;
        int sumOfN = (n * (n + 1)) / 2;

        int arraySum = 0;
        for (int num : arr)
            arraySum += num;

        return (arraySum - sumOfN);
    }

    /*
     * ============= [Expected Approach 2] Using bit-wise XOR operator =====================
     *
     * Using bit-wise XOR operator, x ^ x = 0 and x ^ 0 = x
     * XOR PROPERTY:
     *          2 XOR 2 = 0 i.e., XOR of same no is 0.
     *          2 XOR 0 = 2 i.e., XOR with 0 is N
     *
     * Logic: (XOR of first n-1 natural numbers)   XOR     (XOR of all array elements)
     *          1 ^ 2 ^ 3 .. ^ (n-1)    ^       arr[0] ^ arr[1] ^ .... arr[n-1] -  ans
     *
     *
     *	Example:
     *	N=5
     *	arr[5]={1,2,3,2,4}
     *	XOR of 1st N-1 natural no. X1=1^2^3^4
     *  N-1, because N no are there in array, +1 is the no which is repeated
     *	XOR of array elements X2=1^2^3^2^4
     *	X1 XOR X2 = 1^2^3^4^1^2^3^2^4= 1^1^2^2^2^3^3^4^4=2
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     *
     * */
    private static int findDuplicate_xor(int[] arr) {
        int n = arr.length;
        int res = 0;
        // XOR all numbers from 1 to n-1 and all array elements
        for (int i = 0; i < n - 1; i++)
            res = res ^ (i + 1) ^ arr[i];

        // XOR the last element in the array
        res = res ^ arr[n - 1];

        return res;
    }

    /*
     * ============= [Expected Approach 3] Using Elements as Indexes =====================
     *
     * Logic : As there are only positive numbers,
     *  so visit the index equal to the current element and make it negative.
     *  If an index value is already negative,
     *  then it means that current element is repeated.
     *
     *  PS - if we are sure that array is only having +ve nos, then we can remove Math.abs()) from all places
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * */
    private static int findDuplicate_elementsAsIndexes(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[Math.abs(arr[i])] < 0) {
                return Math.abs(arr[i]);
            }
            arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
        }
        return -1;
    }

}