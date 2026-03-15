package com.nishchay.ds.stack.a03nge;

import java.util.Arrays;
import java.util.Stack;

/*
 * ====================  Next greater element in a Circular Array/ 503. Next Greater Element II =======================
 *
 * Given a circular array arr[], find the next greater element for each element in the array.
 * Note: The next greater element of an element is the first next element greater than it when traversing the array in order (circularly).
 * If no such element exists, return -1 for that position.
 *
 *				Input: arr[] = [5, 7, 1, 2, 6]
 *				Output: [7, -1, 2, 6, 7]
 *				Explanation:
 *				Next greater element for 5 is 7.
 *				For 7, no greater element exists, so it is -1.
 *				For 1, the next greater element is 2.
 *				For 2, the next greater element is 6.
 *				For 6, the next greater element is 7 (circularly).
 *
 *				Input: arr[] = [6, 8, 0, 1, 3]
 *				Output: [8, -1, 1, 3, 6]
 *				Explanation: In the array, the next larger element to 6 is 8,
 *				for 8 there is no larger elements hence it is -1, for 0 it is 1, for 1 it is 3 and then for 3 there is 6.
 *
 * Example 1:
 * 				Input: nums = [1,2,1]
 * 				Output: [2,-1,2]
 * 				Explanation: The first 1's next greater number is 2;
 * 				The number 2 can't find next greater number.
 * 				The second 1's next greater number needs to search circularly, which is also 2.
 * Example 2:
 * 				Input: nums = [1,2,3,4,3]
 * 				Output: [2,3,4,-1,4]
 *
 * https://www.geeksforgeeks.org/dsa/find-the-next-greater-element-in-a-circular-array/
 * https://leetcode.com/problems/next-greater-element-ii/description/
 * */
public class NextGreaterElement2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1};
        System.out.println("result array - " + Arrays.toString(nextGreaterElementCircular(nums)));

        nums = new int[]{1, 2, 3, 4, 3};
        System.out.println("result array - " + Arrays.toString(nextGreaterElementCircular(nums)));

        nums = new int[]{5, 7, 1, 2, 6};
        System.out.println("result array - " + Arrays.toString(nextGreaterElementCircular(nums)));
    }

    /*
     *  ================ [Expected Approach] Using Map - O(n) Time and O(n) Space  =====================
     *
     *	nums 		    = [3,6,5,4,2], n
     *  extended array 	= [3,6,5,4,2][3,6,5,4,2], 2n
     *  ans		        = [6,-1,6,6,3]
     *
     * Formula to access the element in an extended array = i % n
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int[] nextGreaterElementCircular(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stk = new Stack<>();

        for (int i =  2 * n - 1; i >= 0; i--) {

            int curr = nums[i % n];

            while (!stk.isEmpty() && stk.peek() <= curr) {
                stk.pop();
            }

            if (i < n){
                if (stk.isEmpty()) {
                    result[i] = -1;
                } else {
                    result[i] = stk.peek() ;
                }
            }
            stk.push(curr);
        }
        return result;
    }

}
