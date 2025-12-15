package com.nishchay.algo.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *============== First negative integer in every window of size k ====================
 *
 *
 * Given an array and a positive integer k, find the first negative integer for each window(contiguous subarray) of size k.
 * If a window does not contain a negative integer, then print 0 for that window.
 *
 * Examples:
 *				Input: arr[] = [-8, 2, 3, -6, 1] , k = 2
 *				Output: [-8, 0, -6, -6]
 *				Explanation: First negative integer for each window of size 2
 *				                        [-8, 2] = -8
 *				                        [2, 3]= 0 (does not contain a negative integer)
 *				                        [3, -6] = -6
 *				                        [-6, 10] = -6
 *
 *				Input: arr[] = [12, -1, -7, 8, -15, 30, 16, 28], k = 3
 *				Output: [-1, -1, -7, -15, -15, 0]
 *				Explanation: First negative integer for each window of size 3
 *										[ 12, -1, -7] = -1
 *										[-1,-7, 8] = -1
 *										[-7, 8, -15] = -7
 *										[8, -15, 30] = -15
 *										[-15, 30, 16] = -15
 *										[30, 16, 28] = 0
 *
 *
 *
 * https://www.callicoder.com/first-negative-number-in-every-window-of-size-k/
 * https://www.geeksforgeeks.org/dsa/first-negative-integer-every-window-size-k/
 * */
public class FirstNegativeNumber {

    public static void main(String[] args) {

        int[] arr;
        int k;

        arr = new int[]{-8, 2, 3, -6, 10};
        k = 2;
        System.out.println("Negative Numbers - " +  Arrays.toString(firstNegInt(arr, k)));

        arr = new int[]{12, -1, -7, 8, -15, 30, 16, 28};
        k = 3;
        System.out.println("Negative Numbers - " +  Arrays.toString(firstNegInt(arr, k)));



    }

    /*
     * ================ [Naive Approach] Nested Loops - O(n*k) time and O(1) space  =====================
     *
     *  The idea is to loop through the array, and for each window of size k, check each element to find the first negative number.
     *  If a negative number is found, it is added to the result list, and the inner loop breaks.
     *  If no negative number is found in the window, 0 gets added to the result list.
     *  This ensures that each window is processed individually, and the result is output for all windows in sequence.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */

    private static int[] firstNegInt(int[] arr, int k) {
        List<Integer> res = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i <= (n - k); i++) {
            boolean found = false;

            for (int j = i; j < i+k; j++) {

                // if a negative integer is found, then
                // it is the first negative integer for
                // the current window. Set the flag and break
                if (arr[j] < 0) {
                    res.add(arr[j]);
                    found = true;
                    break;
                }
            }
            // if the current window does not contain a negative integer
            if (!found) {
                res.add(0);
            }
        }
        // Convert List to int[]
        return res.stream().mapToInt(i -> i).toArray();
    }


    /*
     * ================ [Better Approach - 1] Using Prefix Sum - O(n) Time and O(n) Space  =====================
     *
     *  The idea is to precompute the prefix sum array where each element at index i stores the sum of elements from index 0 to i-1.
     *  Using this, we can compute the sum of any subarray in constant time O(1) using the difference of two prefix values.
     *  This eliminates the need to iterate over each subarray element repeatedly.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */

}
