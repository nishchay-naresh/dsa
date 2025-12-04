package com.nishchay.ds.array.a01basic;
/*
 * =========== Find no of contiguous Prime Numbers in an array ===========
 *
 * Given an array arr[] of N elements. The task is to find the maximum number of the contiguous prime numbers in the given array.
 *
 *  Examples :
 *		Input: arr[] = {3, 5, 2, 66, 7, 11, 8}
 *		Output: 3
 *		Maximum contiguous prime number sequence is {2, 3, 5}
 *
 *		Input: arr[] = {1, 0, 2, 11, 32, 8, 9}
 *		Output: 2
 *		Maximum contiguous prime number sequence is {2, 11}
 *
 * https://www.geeksforgeeks.org/dsa/maximum-no-of-contiguous-prime-numbers-in-an-array/
 * https://www.geeksforgeeks.org/dsa/longest-sub-array-of-prime-numbers-using-segmented-sieve/
 * */

import static com.nishchay.math.a01easy.CheckPrime.isPrime;

public class ContiguousPrimeInArray {

    public static void main(String[] args) {

        int[] arr = {8, 4, 2, 1, 3, 5, 7, 9, 12, 11, 21, 5, 19, 17, 83, 79, 12};
        System.out.println("Maximum no. of contiguous Prime Numbers in an array:  " + primeSubarrayCount(arr));

        arr = new int[]{8, 4, 2, 1, 3, 5, 7, 9, 12};
        System.out.println("Maximum no. of contiguous Prime Numbers in an array:  " + primeSubarrayCount(arr));

        arr = new int[]{3, 5, 2, 66, 7, 11, 8};
        System.out.println("Maximum no. of contiguous Prime Numbers in an array:  " + primeSubarrayCount(arr));

        arr = new int[]{1, 0, 2, 11, 32, 8, 9};
        System.out.println("Maximum no. of contiguous Prime Numbers in an array:  " + primeSubarrayCount(arr));
    }


    /*
     * ============ [Optimal Approach] One Pass Approach ===============
     *
     * Algorithm:
     *      Create a sieve to check whether an element is prime or not in O(1).
     *      Traverse the array with two variables named current_max and max_so_far.
     *      If a prime number is found then increment current_max and compare it with max_so_far.
     *      If current_max is greater than max_so_far, then assign max_so_far with current_max
     *      Every time a non-prime element is found reset current_max to 0.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * */
    private static int primeSubarrayCount(int[] array) {
        int maxCountSoFar, currentCount;

        maxCountSoFar = currentCount = 0;
        for (int curr : array) {
            if (isPrime(curr)) {
                currentCount++;
                maxCountSoFar = Math.max(currentCount, maxCountSoFar);
            } else {
                currentCount = 0;
            }
        }
        return maxCountSoFar;
    }
}
