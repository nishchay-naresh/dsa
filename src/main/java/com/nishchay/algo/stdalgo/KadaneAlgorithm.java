package com.nishchay.algo.stdalgo;

/*
 *	============================= Find maximum Sum Contiguous Subarray =======================================
 * Kadane’s Algorithm
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 *	For Example
 *                      0    1  2   3   4  5  6   7  8
 *		Input: nums =  {-2, -3, 4, -1, -2, 1, 5, -3,-1}
 *		Output: 7
 *		Explanation: [4,-1,-2,1,5] has the largest sum = 7.
 *
 *		Input: nums = {5, 4, -1, 7, 8}
 *		Output: 23
 *
 *      Input: nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4}
 *      Output: 6
 *
 * https://youtu.be/AHZpyENo7k4?si=4yDYseAE9muzYIrp
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 * https://leetcode.com/problems/maximum-subarray/
 *
 * */


public class KadaneAlgorithm {

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3, -1};
        System.out.println("maxSubArraySum_2loop(arr)   = " + maxSubArraySum_2loop(arr));
        System.out.println("kadaneAlgorithm(arr)        = " + kadaneAlgorithm(arr));

        arr = new int[]{5, 4, -1, 7, 8};
        System.out.println("maxSubArraySum_2loop(arr)   = " + maxSubArraySum_2loop(arr));
        System.out.println("kadaneAlgorithm(arr)        = " + kadaneAlgorithm(arr));
    }


    /*
     * ================ [Naive/Bruteforce Approach] Using Two Nested Loops  =====================
     *
     * Generating all possible sub arrays
     *
     *      1,  -3,  2,  1,  -1
     *      +----|---|---|----|
     *          +----|---|----|
     *              +----|----|
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int maxSubArraySum_2loop(int[] arr) {
        int n = arr.length;
        int curr_sum, max_sum;
        max_sum = 0;
        for (int i = 0; i < n - 1; i++) {
            curr_sum = arr[i];
            for (int j = i + 1; j < n; j++) {
                // get the sum for subset, i to j
                curr_sum = curr_sum + arr[j];
                max_sum = Math.max(max_sum, curr_sum);
            }
        }
        return max_sum;
    }

    /*
     *  ================ [Optimize/Expected Approach] Kadane's Algorithm - O(n) Time =====================
     *
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
}
