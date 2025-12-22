package com.nishchay.ds.array.a11water;

import java.util.Stack;

/*
 *	========================== Largest Rectangular Area in a Histogram =============================
 *
 * Given an array arr[] representing a histogram, where each element denotes the height of a bar and every bar has a uniform width of 1 unit,
 * find the largest rectangular area that can be formed within the histogram. The rectangle must be formed using contiguous bars.
 *
 * Examples:
 *				Input: arr[] = [60, 20, 50, 40, 10, 50, 60]
 *				Output: 100
 *				Explanation: We get the maximum area by picking bars highlighted above in green (50, and 60).
 *							   The area is computed (the smallest height) * (no. of the picked bars) = 50 * 2 = 100.
 *
 *				Input: arr[] = [3, 5, 1, 7, 5, 9]
 *				Output: 15
 *				Explanation:  We get the maximum are by picking bars 7, 5 and 9. The area is computed (smallest height) * (no. of the picked bars) = 5 * 3 = 15.
 *
 *	            Input : int hist[] = {6, 2, 5, 4, 5, 1, 6}.
 *              Output: 12
 *		        Explanation: The numbers 1, 3 and 6 appear more than once in the array.
 *
 *		        Input: int hist[] = { 6, 2, 5, 4, 5, 1, 6 };
 *		        Output:  Maximum area is 12
 *
 * https://www.danielleskosky.com/largest-rectangle-in-histogram/
 * https://www.geeksforgeeks.org/dsa/largest-rectangular-area-in-a-histogram-using-stack/
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 * */
public class LargestRectangleArea {

    public static void main(String[] args) {

        int[] hist = {2, 1, 5, 2, 1, 3}; //6
        System.out.println("Maximum area is " + largestRectangleArea_bruteForce(hist));

        hist = new int[]{2, 4, 8, 10, 8, 4, 2}; // 24
        System.out.println("Max rectangle area: " + largestRectangleArea(hist));

        int[] input = {6, 2, 5, 4, 5, 1, 6};
        System.out.println("Max rectangle area: " + largestRectangleArea1(input));
    }

    /*
     * ================ [Naive Approach] By Finding Max Area of Rectangles all Heights - O(n2) Time and O(1) Space  =====================
     * The idea is to fix each bar as the height of the rectangle and expand towards the left and right and keep adding more bar.
     * Keep evaluating the aea for all possible rectangles and keep tracing the max out of it.
     *
     * Why j=i, because we need to consider a single bar as a rectangle as well
     *
     * Time Complexity : O(n^2).
     * Space Complexity: O(1).
     * */
    private static int largestRectangleArea_bruteForce(int[] heights) {
        int maxArea = 0;
        int length = heights.length;
        for (int i = 0; i < length; i++) {      // start point
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < length; j++) {  // end point
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /*
     * Try to find the largest rectangle for each bar (if we have to include that one)
     *
     *                          heights = [2, 1, 5, 6, 2, 3]
     *                          index      0  1  2  3  4  5
     *  largest rectangle for each bar     2, 6, 10,6, 8, 3
     *
     *  To get this, we always have the height, to get the width, we need to have left and right.
     *  So for each index we need to have left smallest nearest, right smallest nearest
     *
     * TODO - https://youtu.be/ysy1o-QEj3k?si=m27hTu4oYN5Oy0CS
     *
     * Scan from left to right to compute left[], which represents the left most boundary of current height.
     * Scan from right to left to compute right[], which represents the right most boundary of current height.
     * Scan from left to right again to compute rectangle area based on the height of that each position.
     *
     * Time Complexity: O(n).
     * Space Complexity: O(n).
    * */
    private static int largestRectangleArea1(int[] heights) {
        // validate input
        if(heights == null || heights.length == 0) {
            return 0;
        }

        // init
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];


        // build left
        left[0] = 0;
        for(int i = 1; i < n; i++) {
            int currentLeft = i-1;
            while(currentLeft >= 0 && heights[currentLeft] >= heights[i]) {
                currentLeft = left[currentLeft]-1;
            }

            left[i] = currentLeft+1;
        }

        // build right
        right[n-1] = n-1;
        for(int i = n-2; i >= 0; i--) {
            int currentRight = i+1;
            while(currentRight < n && heights[i] <= heights[currentRight]) {
                currentRight = right[currentRight]+1;
            }

            right[i] = currentRight-1;
        }

        int maxArea = 0;
        for(int i = 0; i < n; i++) {
            int currArea = (right[i] - left[i] + 1) * heights[i];
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    /*
     * Using Stack
     *
     * Step 1 : First we will take two arrays left_smaller[] and right_smaller[] and initialize it with -1 and n respectively.
     *
     * Step 2 : For every element we will store
     * 		the index of previous smaller and next smaller element in left_smaller[] and right_smaller[] arrays respectively.
     * 		(It will take O(n) time).
     *
     * Step 3 : Now for every element we will calculate area
     * 		int height = heights[i];
     * 		int length = right_smaller[i] - left_smaller[i] + 1
     * 		ith area = height * length; // currArea
     * Step 4 : We can find the maximum of all the area calculated in step 3 to get the desired maximum area.
     *
     *
     * Time Complexity: O(n2).
     * Space Complexity: O(1).
     * */
    public static int largestRectangleArea(int[] heights) {
        int arraySize = heights.length;
        int[] left_smaller = new int[arraySize];
        int[] right_smaller = new int[arraySize];
        Stack<Integer> stack = new Stack<>();

        // filling out left_smaller array
        for (int i = 0; i < arraySize; i++) {
            if (stack.isEmpty()) {
                left_smaller[i] = 0;
            } else {
                while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }
                left_smaller[i] = stack.empty() ? 0 : stack.peek() + 1;
            }
            stack.push(i);
        }

        stack.clear();

        // filling out right_smaller array
        for (int i = arraySize - 1; i >= 0; i--) {
            if (stack.empty()) {
                right_smaller[i] = arraySize - 1;
            } else {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }
                right_smaller[i] = stack.isEmpty() ? arraySize - 1 : stack.peek() - 1;
            }
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < arraySize; i++) {
            int area = heights[i] * (right_smaller[i] - left_smaller[i] + 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

}
