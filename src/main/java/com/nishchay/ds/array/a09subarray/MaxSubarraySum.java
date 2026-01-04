package com.nishchay.ds.array.a09subarray;

/*
 *  ======================= Kadane's Algorithm | MSS Maximum Subarray Sum | Finding and Printing ====================
 *
 *  Given an integer array arr[], find the subarray (containing at least one element) which has the maximum possible sum, and return that sum.
 *  Note: A subarray is a continuous part of an array.
 *
 * Examples:
 * 			Input: arr[] = [2, 3, -8, 7, -1, 2, 3]
 * 			Output: 11
 * 			Explanation: The subarray [7, -1, 2, 3] has the largest sum 11.
 *
 * 			Input: arr[] = [-2, -4]
 * 			Output: -2
 * 			Explanation: The subarray [-2] has the largest sum -2.
 *
 * 			Input: arr[] = [5, 4, 1, 7, 8]
 * 			Output: 25
 * 			Explanation: The subarray [5, 4, 1, 7, 8] has the largest sum 25.
 *
 * 			Input: arr[] = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * 			Output: 6
 * 			Explanation: The subarray [4, -1, 2, 1] has the largest sum 6.
 *
 * https://takeuforward.org/data-structure/kadanes-algorithm-maximum-subarray-sum-in-an-array/
 * https://www.geeksforgeeks.org/dsa/largest-sum-contiguous-subarray/
 * https://leetcode.com/problems/maximum-subarray/description/
 * https://youtu.be/9IZYqostl2M?si=O2Ql8RBYo6enmPC9
 *
 * */

public class MaxSubarraySum {

    public static void main(String[] args) {
        int[] arr = {2, 3, -8, 7, -1, 2, 3};
        System.out.println(maxSubarraySum_3loop(arr));
        System.out.println(maxSubarraySum_2loop(arr));
        System.out.println(kadaneAlgorithm(arr));

        arr = new int[]{-2, -4};
        System.out.println(kadaneAlgorithm(arr));

        arr = new int[]{5, 4, 1, 7, 8};
        System.out.println(kadaneAlgorithm(arr));

        System.out.println("----------------------------");
        System.out.println(kadaneAlgorithmSubarrayIndex(arr));

        arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(kadaneAlgorithmSubarrayIndex(arr));

    }


    /*
     * ================ [Naive/Bruteforce Approach] Iterative Approach - O(n^3) Time and O(1) Space =====================
     *
     *  The idea is to check the sum of all the subarrays and count them if a subarray sum is equal to k.
     *  return the count
     *
     *  Time Complexity     : O(n^3)
     *  Space complexity    : O(1)
     */
    private static int maxSubarraySum_3loop(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        for (int start = 0 ; start < n; start++) {
            for (int end = start; end < n; end++) {
                // calculate the currSum of subarray [start...end]
                int currSum = 0;
                for (int k = start; k <= end; k++){
                    currSum += arr[k];
                }
                maxSum = Math.max(currSum, maxSum);
            }
        }
        return maxSum;
    }

    /*
     *  ================ [Better Approach] Removing third loop - O(n^2) Time and O(1) Space  =====================
     *
     *  We can remove the third loop. Rather iterating from stating to end for all subarrays, we can calculate the sum by
     *  previous sum so for + add the current element the augmented one)
     *
     *  If we carefully observe, we can notice that to get the sum of the current subarray,
     *  we just need to add the current element(i.e. arr[j]) to the sum of the previous subarray i.e., arr[i….j-1].
     *
     *       Assume previous subarray = arr[i……j-1]
     *       current subarray = arr[i…..j]
     *       Sum of arr[i….j] = (sum of arr[i….j-1]) + arr[j]
     *
     * This is how we can remove the third loop(k-loop) and while moving j pointer, we can calculate the sum.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     */

    private static int maxSubarraySum_2loop(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0 ; i < n; i++) { // starting index i
            int currSum = 0;
            for (int j = i; j < n; j++) { // ending index j
                // calculate the currSum of subarray [i...j]
                // currSum of [i..j-1] + arr[j]
                currSum = currSum + arr[j];
                maxSum = Math.max(currSum, maxSum);
            }
        }
        return maxSum;
    }

    /*
     *  ================ [Optimize/Expected Approach] Kadane's Algorithm - O(n) Time =====================
     * Since we are not required to find the subarray (sub array itself with elements or start index - index end )
     * we only need to tell the sum of sub-array, so Kadane's algorithm can help us on this.
     * We aren't required to run loop 2, in one loop by finding the subarray sum we can find the max sum
     *
     * Intuition:
     *                  +ve  	    + 	    +ve     = +ve
     *                  -ve(small)  +   +ve(big)    = +ve
     *                  +ve(small)  +   -ve(big)    = -ve Kadane's Algorithm
     *
     * If a subarray sum is -ve, then adding it with the next available element will never give me a max sum.
     * So better discard that subarray and reset subarray sum to 0, start forming next subarray from next element
     *
     * NOTE: why adding the next element to the current subarray sum: possibly extending the current subarray by considering the next element
     *
     * Steps:
     *          We will run a loop(say i) to iterate the given array.
     *          Now, while iterating, we will add the elements to the sum variable and consider the maximum one.
     *          If at any point, the sum becomes negative, we will set the sum to 0 as we are not going to consider it as a part of our answer.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     */
    private static int kadaneAlgorithm(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int curr : arr) {
            currSum = currSum + curr;
            maxSum = Math.max(currSum, maxSum);

            // If currSum < 0: discard the currSum calculated
            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }

    /*
     *  ================ [Optimize/Expected Approach] Kadane's Algorithm - O(n) Time =====================
     * Followup question :
     *          There might be more than one subarray with the maximum sum. We need to print any of them.
     *
     * Intuition:   Our approach is to store the starting index and the ending index of the subarray.
     *              Thus, we can easily get the subarray afterward without actually storing the subarray elements.
     *
     *      If we carefully observe our algorithm:
     *          startIndex - subarray always starts at the particular index where the sum variable is equal to 0
     *          endIndex - sum always crosses the maximum sum (updating the maxSum)
     *     The rest of the approach will be the same as Kadane’s Algorithm.
     *
     */
    private static int kadaneAlgorithmSubarrayIndex(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        int currStart = 0;
        int ansStart = -1, ansEnd = -1;
        for (int i = 0; i < n; i++) {
            if (currSum == 0)
                currStart = i;

            currSum = currSum + arr[i];
            if (currSum > maxSum) {
                maxSum = currSum;
                // updating start and end index of subarray
                ansStart = currStart;
                ansEnd = i;
            }

            // If currSum < 0: discard the currSum calculated
            if (currSum < 0) {
                currSum = 0;
            }
        }

        //printing the subarray:
        System.out.print("The subarray is: [");
        for (int i = ansStart; i <= ansEnd; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("]\n");

        return maxSum;
    }
}
