package com.nishchay.ds.stack.a03nge;

import java.util.Arrays;
import java.util.Stack;

/*
 * ====================  DailyTemperatures / Count of days remaining for the next day with higher temperature =======================
 *
 *  Given a list arr[] of everyday temperatures. For each day, the task is to find the count of days remaining for the next day with warmer temperatures.
 *  If there is no such day for which warmer temperature is possible then print 0.
 *
 * Examples:
 *				Input: arr[] = {73, 74, 75, 71, 69, 72, 76, 73}
 *				Output: {1, 1, 4, 2, 1, 1, 0, 0}
 *				Explanation:
 *					For 73 temperature, next warmer temperature is 74 which at a distance 1
 *					For 74 temperature, next warmer temperature is 75 which at a distance 1
 *					For 75 temperature, next warmer temperature is 76 which at a distance 4
 *					For 71 temperature, next warmer temperature is 72 which at a distance 2
 *					For 69 temperature, next warmer temperature is 72 which at a distance 1
 *					For 72 temperature, next warmer temperature is 76 which at a distance 1
 *					For 76 temperature, there is no valid next warmer day
 *					For 73 temperature, there is no valid next warmer day
 *
 *				Input: arr[] = {75, 74, 73, 72}
 *				Output: {0, 0, 0, 0}
 *
 *				Input: temperatures = [73,74,75,71,69,72,76,73]
 *				Output: [1,1,4,2,1,1,0,0]
 *
 *				Input: temperatures = [30,40,50,60]
 *				Output: [1,1,1,0]
 *
 *				Input: temperatures = [30,60,90]
 *				Output: [1,1,0]
 *
 * https://www.danielleskosky.com/daily-temperatures/
 * https://leetcode.com/problems/daily-temperatures/description/
 * */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println("daysOfWait - " + Arrays.toString(getDaysCount(temps)));
    }


    /*
     * ================ [Expected Approach] Using Stack - O(n) Time and O(n) Space  =====================
     *
     * Naive Approach: The idea is to iterate for each possible pair of the arrays and check the next greater temperatures for each current element.
     * Time Complexity: O(N2)
     * Auxiliary Space: O(1)
     *
     * Efficient Approach:
     *  This problem basically asks to find how far is the current index from the index of next greater temperature to the temperature at the current index.
     *  The most optimal way to solve this problem is by making use of a stack.
     *  Below are the steps:
     *
     *      Rather than pushing the NGE in stack, pushing its index
     *
     * 1. Iterate over the everyday temperature of the given array arr[] using the current index.
     * 2. If the stack is empty, push the current index to the stack.
     * 3. If the stack is not empty, then do the following:
     * 	If the temperature at the current index is lesser than the temperature of the index at the top of the stack, push the current index.
     * 	If the temperature at the current index is greater than the temperature of the index at top of the stack, then update the no of days to wait for warmer temperature as:
     * 		current index – index at top of the stack
     * 		Pop the stack once the number of days has been updated in the output list.
     * 4. Repeat the above steps for all the indices in the stack that are lesser than the temperature at the current index.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int[] getDaysCount(int[] temperatures) {
        int n = temperatures.length;
        int[] daysOfWait = new int[n];
        Arrays.fill(daysOfWait, 0);

        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {

            // Check if the current index is the next warmer temperature of any previous indexes
            while (!s.isEmpty() && temperatures[s.peek()] < temperatures[i]) {
                daysOfWait[s.peek()] = i - s.peek();

                s.pop();
            }
            s.push(i);
        }
        return daysOfWait;
    }
}
