package com.nishchay.ds.array.a11water;

/*
 *  =============================== Container with Most Water ===================================
 *
 *  Given an array arr[] of non-negative integers, where each element arr[i] represents the height of the vertical lines,
 *  find the maximum amount of water that can be contained between any two lines, together with the x-axis.
 *
 * Examples:
 *				Input: arr[] = [1, 5, 4, 3]
 *				Output: 6
 *				Explanation: 5 and 3 are 2 distance apart. So the size of the base = 2. Height of container = min(5, 3) = 3. So total area = 3 * 2 = 6.
 *
 *				Input: arr[] = [3, 1, 2, 4, 5]
 *				Output: 12
 *				Explanation: 5 and 3 are 4 distance apart. So the size of the base = 4. Height of container = min(5, 3) = 3. So total area = 4 * 3 = 12.
 *
 *				Input: arr[] = [2, 1, 8, 6, 4, 6, 5, 5]
 *				Output: 25
 *				Explanation: 8 and 5 are 5 distance apart. So the size of the base = 5. Height of container = min(8, 5) = 5. So, total area = 5 * 5 = 25.
 *
 * https://www.geeksforgeeks.org/dsa/container-with-most-water/
 * https://leetcode.com/problems/container-with-most-water/
 * */

import java.util.Arrays;

public class ContainerWithMostWater {

    public static void main(String[] args) {

        int[] height = new int[]{1, 5, 4, 3};
        System.out.println(Arrays.toString(height) + ", maxArea = " + maxWater_2loop(height));      // 6
        System.out.println(Arrays.toString(height) + ", maxArea = " + maxWater_2pointers(height));  // 6

        height = new int[]{3, 1, 2, 4, 5};
        System.out.println(Arrays.toString(height) + ", maxArea = " + maxWater_2loop(height));      // 12
        System.out.println(Arrays.toString(height) + ", maxArea = " + maxWater_2pointers(height));  // 12

        height = new int[]{2, 1, 8, 6, 4, 6, 5, 5};
        System.out.println(Arrays.toString(height) + ", maxArea = " + maxWater_2loop(height));      // 25
        System.out.println(Arrays.toString(height) + ", maxArea = " + maxWater_2pointers(height));  // 25

        int[] barArray = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(Arrays.toString(barArray) + ", maxArea = " + maxWater_2loop(barArray));      // 49
        System.out.println(Arrays.toString(barArray) + ", maxArea = " + maxWater_2pointers(barArray));  // 49

    }

    /*
     * ================ [Naive Approach] Checking all possible boundaries / containers - O(n^2) Time and O(1) Space  =====================
     *
     *  The idea is to evaluate all possible pairs of lines in the array.
     *   For each line height[i], treat it as the left boundary, and then iterate through all lines to its right (height[j], where j > i) to consider them as the right boundary.
     *   For every such pair, compute the water that can be trapped between them using the
     *          formula: min(height[i], height[j]) × (j - i)
     *                   This represents the height (limited by the shorter line) multiplied by the width between the two lines.
     *
     *  Finally, keep track of the maximum water obtained among all such pairs.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int maxWater_2loop(int[] height) {
        int n = height.length;
        int maxWater = 0;
        for (int i = 0; i < n; i++) { // start point
            for (int j = i + 1; j < n; j++) { // end point
                int wd = j - i ;
                int ht = Math.min(height[i], height[j]);
                int currentWater = wd * ht;
                // keep track of maximum currentWater
                maxWater = Math.max(currentWater, maxWater);
            }
        }
        return maxWater;
    }

    /*
     * ================ [Expected Approach] Using Two Pointers - O(n) Time and O(1) Space  =====================
     * we have understood this much so far :
     *          container = width * height, width = right - left, height = min(ht[left], ht[right])
     *
     *  The idea is to maintain two pointers:
     *       left pointer at the beginning of the array and
     *       right pointer at the end of the array.
     *  These pointers act as the container's sides, so we can calculate the amount of water stored between them using the
     *      formula: min(arr[left], arr[right]) * (right - left).
     *  Finally, keep track of the maximum water obtained among all such pairs.
     *
     * HOW TO DECIDE WHICH POINTER TO MOVE - left / right
     * We always move the pointer, which have the lesser value.
     *       Because the pointer with bigger value always gives us the less area, Because area is till decided by the smaller bar
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */

    private static int maxWater_2pointers(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;
        int ht, wd, currentArra;

        while (i < j) {
            ht = Math.min(height[i], height[j]);
            wd = j - i;
            currentArra = ht * wd;
            maxArea = Math.max(maxArea, currentArra);

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

}
