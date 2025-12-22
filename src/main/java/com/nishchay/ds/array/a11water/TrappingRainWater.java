package com.nishchay.ds.array.a11water;

/*
 *
 * Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 *  Examples:
 *      		Input: arr[] = {2, 0, 2}
 *      		Output: 2
 *      		Explanation: Will trap 0 + 2 + 0 = 2 units.
 *
 *	    		Input: arr[] = {3, 0, 2, 0, 4}
 *	    		Output: 7
 *      		Explanation: Will trap 0 + 3 + 1 + 3 + 0 = 7 units.
 *
 *	    		Input: arr[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
 *	    		Output: 6
 *      		Explanation: Will trap 0 + 0 + 1 + 0 + 1 + 2 + 1 + 0 + 0 + 1 + 0 + 0 = 6 units.
 *
 *	    		Input: arr[] = {3, 0, 1, 0, 4, 0, 2}
 *	    		Output: 10
 *	    		Explanation: Will trap 0 + 3 + 2 + 3 + 0 + 2 + 0 = 10 units.
 *
 *      		Input: arr[] = {3, 0, 2, 0, 4}
 *	    		Output: 7
 *	    		Explanation: Will trap 0 + 3 + 1 + 3 + 0 = 7 units.
 *
 *	    		Input: arr[] = {1, 2, 3, 4}
 *	    		Output: 0
 *	    		Explanation: Cannot trap water as there is no height bound on both sides
 *
 *
 * https://www.geeksforgeeks.org/dsa/trapping-rain-water/
 * https://www.interviewbit.com/blog/trapping-rain-water/
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * */

import java.util.Arrays;

public class TrappingRainWater {
    public static void main(String[] args) {

        int[][] input2D = {
                {2, 0, 2},                              // 2
                {3, 0, 2, 0, 4},                        // 7
                {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1},   // 6
                {4, 2, 0, 3, 2, 5},                     // 9
                {3, 0, 1, 0, 4, 0, 2},                  // 10
                {3, 0, 2, 0, 4},                        // 7
                {1, 2, 3, 4},                           // 0
                {1, 3, 2, 4, 6, 1},                     // 1
                {1, 3, 2, 1, 4, 6, 7, 3, 1},            // 3
                {7, 0, 4, 2, 5, 0, 6, 4, 0, 5},         // 25
                {5, 6, 7, 12, 2, 10, 17, 5, 3}          // 12
        };

        int[] outputs = {2, 7, 6, 9, 10, 7, 0, 1, 3, 25, 12};

        boolean result = true;
        for (int i = 0; i < input2D.length; i++) {
            result = result && trappingWater_2pointer(input2D[i]) == (outputs[i]);
            if (!result)
                System.out.println("Test failed for: " + Arrays.toString(input2D[i]));
            else
                System.out.println("Test passed for: " + Arrays.toString(input2D[i]));
        }

        int[] arr1 = {0, 1, 2, 4, 5, 7, 9, 8, 6, 3, 2, 1};  // 0
        System.out.println(totalWater_2loop(arr1));

        int[] arr2 = {5, 6, 7, 12, 2, 10, 5, 17, 5, 3};        // 0,0,0,0,10,2,7,0,0,0 = 19
        System.out.println(totalWater_2auxiliaryArray(arr2));
    }

    /*
     *
     *  ithWater = min(left_max, right_max) – height[i]
     *  first and last index will never contribute any water
     *
     * This is the formula that will give us the solution.
     * We will implement this (means finding left_max, right_max) only in various approaches - Bruteforce, Better, Optimal solutions
     *
     * ================ [Bruteforce Approach] Using nested loop - O(n^2) Time and O(1) Space  =====================
     *
     * Bruteforce - simply have a nested loop to find the left_max, right_max.
     *
     *	Traverse the array height[] from 1 to n-2 and for each element:
     *		Initialise left_max = right_max = height[i]
     *		Traverse from height[i] till the beginning of the array and update:
     *			left_max = max(left_max, height[i])
     *		Similarly, traverse from height[i] till the end of the array and update:
     *			right_max = max(right_max, height[i])
     *      ithWater = min(leftMax, rightMax) - height[i];
     *      totalWater = totalWater + ithWater;
     *
     *
     * Time Complexity : O(n^2) For each element, the left and right half are traversed.
     * Space Complexity: O(1)
     */
    private static int totalWater_2loop(int[] height) {
        int n = height.length;
        int leftMax, rightMax, totalWater;
        totalWater = 0;

        // excluding the first and last element
        for (int i = 1; i < n - 1; i++) {

            // Find a maximum element on its left
            leftMax = height[i];
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // Find a maximum element on its right
            rightMax = height[i];
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            int ithWater = Math.min(leftMax, rightMax) - height[i];
            totalWater = totalWater + ithWater;
        }
        return totalWater;
    }


    /*
     *  ================ [Better Approach] Prefix and suffix max for each index - O(n) Time and O(n) Space  =====================
     *
     * In the previous approach, for every element, we needed to calculate the highest element on the left and on the right.
     * So, to reduce the time complexity from O(n^2) to O(n) :
     * We will use two auxiliary array - leftMax[] and rightMax[] to store the max bar on left and right
     *
     * 	=> For every element, we first calculate and store the highest bar on the left and on the right (say stored in arrays leftMax[] and rightMax[]).
     * 	=> Then iterate the array and use the calculated values to find the amount of water stored in this index,
     * 		which is the same as
     * 		int ithWater = Math.min(leftMax, rightMax) - height[i];
     *         totalWater = totalWater + ithWater;
     *
     * Time Complexity  : O(n) + O(n) + O(n) = O(3n) = O(n), since the array is traversed thrice.
     * Space Complexity : O(n) + O(n) = O(2n) = O(n), since two auxiliary arrays are needed.
     * */
    private static int totalWater_2auxiliaryArray(int[] height) {
        int n = height.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Prefix max - leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // suffix max -  rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int totalWater = 0;
        for (int i = 1; i < n - 1; i++) {
            int ithWater = Math.min(leftMax[i], rightMax[i]) - height[i];
            totalWater = totalWater + ithWater;
        }
        return totalWater;
    }

    /*
     *  ================ [Optimize Approach] Using Two Pointers Approach - O(n) Time and O(1) Space  =====================
     *          ithWater = min(lmax, rmax) – height[i]
     * In the previous approach, we were using two auxiliary array - leftMax[] and rightMax[] to store the max bar on left and right
     * now we will try to achieve the same using 2 variable - lmax abd rmax
     *      lmax = max (lmax, height[left])
     *      rmax = max (rmax, height[right])
     *
     * To optimize it further, if we know which is smaller out of right_max, left_max, we never need both of them, ====>  we only need one, the smaller one
     * we will never get both right_max, left_max in leaner traversal ---> or <----- . =====> we need to travel from both side
     * Why processing the smaller number first ====> because we need the smaller one out of right_max, left_max
     * When these loops get terminated ===> When both left and right pointer to the largest no
     *
     *
     * while (left < right) {
     *      lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);
     *      we will process the smaller number first
     *      if (lmax < rmax) {
     *          will compute height[left] water and add to total
     *          based on right pointer movement, will find the next rightMax
     *      }
     *      else {
     *          will compute height[right] water and add to total
     *          based on right pointer movement, will find the next rightMax
     *     }
     * }
     *
     *      Time Complexity: O(n).
     *      Space Complexity: O(1).
     *      Can't do better than this, because we at leased need to touch each element once
     * */
    public static int trappingWater_2pointer(int[] height) {

        int left, right, lmax, rmax, totalWater, ithWater;
        left = 0;
        right = height.length - 1;
        lmax = rmax = totalWater = 0;

        while (left < right) {
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);

            // processing the smaller number first
            if (lmax < rmax) {
                // since we have processed the smaller number first, we are sure that lmax < rmax
                ithWater = lmax - height[left];
                totalWater = totalWater + ithWater;
                left++;
            } else {
                // since we have processed the smaller number first, now we are in else part we are sure that leftMax > rightMax
                ithWater = rmax - height[right];
                totalWater = totalWater + ithWater;
                right--;
            }
        }
        return totalWater;
    }
}

