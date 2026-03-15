package com.nishchay.ds.stack.a03nge;

import java.util.Arrays;
import java.util.Stack;

/*
 * ====================  Next Greater Element (NGE) for every element in given Array =======================
 *
 *  Given an array arr[] of integers, determine the Next Greater Element (NGE) for every element in the array, maintaining the order of appearance.
 *      -   The Next Greater Element for an element x is defined as the first element to the right of x in the array that is strictly greater than x.
 *      -   If no such element exists for an element, its Next Greater Element is -1.
 *
 * Examples:
 * 				Input: arr[] = [1, 3, 2, 4]
 * 				Output: [3, 4, 4, -1]
 * 				Explanation: The next larger element to 1 is 3, 3 is 4, 2 is 4 and for 4, since it doesn't exist, it is -1.
 *
 *  			Input: arr[] = [6, 8, 0, 1, 3]
 *  			Output: [8, -1, 1, 3, -1]
 *  			Explanation: The next larger element to 6 is 8, for 8 there is no larger elements hence it is -1, for 0 it is 1,
 *							 for 1 it is 3 and then for 3 there is no larger element on right and hence -1.
 *
 * 				Input: arr[] = [50, 40, 30, 10]
 * 				Output: [-1, -1, -1, -1]
 * 				Explanation: There is no greater element for any of the elements in the array, so all are -1.
 *
 * 				Input: arr[] = [3, 10, 4, 2, 1, 2, 6, 1, 7, 2, 9]
 * 				Output:        [10,-1, 6, 6, 2, 6, 7, 7, 9, 9,-1]
 *
 *
 *  https://www.geeksforgeeks.org/dsa/next-greater-element/
 *
 * */
public class NextGreaterElement {

    public static void main(String[] args) {

        int[] arr = { 1, 3, 2, 4};
        System.out.println("Original array - " + Arrays.toString(arr));
        System.out.println("NGE set        - " + Arrays.toString(nextGreaterElement_2loop(arr)));
        System.out.println("NGE set        - " + Arrays.toString(nextGreaterElement(arr)));

        arr = new int[]{6, 8, 0, 1, 3};
        System.out.println("Original array - " + Arrays.toString(arr));
        System.out.println("NGE set        - " + Arrays.toString(nextGreaterElement_2loop(arr)));
        System.out.println("NGE set        - " + Arrays.toString(nextGreaterElement(arr)));

        arr = new int[]{50, 40, 30, 10};
        System.out.println("Original array - " + Arrays.toString(arr));
        System.out.println("NGE set        - " + Arrays.toString(nextGreaterElement_2loop(arr)));
        System.out.println("NGE set        - " + Arrays.toString(nextGreaterElement(arr)));

        arr = new int[]{3, 10, 4, 2, 1, 2, 6, 1, 7, 2, 9};
        System.out.println("Original array - " + Arrays.toString(arr));
        System.out.println("NGE set        - " + Arrays.toString(nextGreaterElement(arr)));
    }


    /*
     * ================ [Naive/Bruteforce Approach] Linear Search for Missing Number  =====================
     *
     *  iterates through each number from 1 to n+1 (where n is the size of the array) and checks if the number is present in the array.
     *  For each number, it uses a nested loop to search the array. If a number is not found, it is returned as the missing number.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int[] nextGreaterElement_2loop(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        // initialize result array with -1
        Arrays.fill(result, -1);

        for (int i = 0; i < n; i++) {
            // check for the next greater element in the rest of the array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    result[i] = arr[j];
                    break;
                }
            }
        }
        return result;
    }

    /*
     *  ================ [Expected Approach] Using Stack - O(n) Time and O(n) Space  =====================
     *
     *	Next Greater Element (NGE) Definition:
     *      -   right element
     *      -   greater than the current element
     *
     * If we keep track of all the element right of arr[i] and then greater among all of these
     *      - then we have found the solution
     *      - this can be achieved using -  monotonic decreasing stack + right to left traversal of the array
     *
     * The idea is to use a monotonic decreasing stack + right to left traversal of the array
     *
     * We traverse the array from right to left. For each element,
     *   we pop elements from the stack that are smaller than or equal to it, since they cannot be the next greater element.
     *   If the stack is not empty, the top of the stack is the next greater element.
     *   Finally, we push the current element onto the stack.
     *
     * ------------------
     * What is a Monotonic Stack?
     *	A monotonic stack is a stack that maintains its elements in a monotonic order:
     *		Monotonic Increasing Stack → elements are in increasing order
     *		Monotonic Decreasing Stack → elements are in decreasing order
     *	The order is maintained by popping elements that break the monotonic property when a new element is processed.
     * -------------------
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    public static int[] nextGreaterElement(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stk = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            // Pop elements from the stack that are less than or equal to the current element
            while (!stk.isEmpty() && stk.peek() <= nums[i]) {
                stk.pop();
            }

            if (stk.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stk.peek();
            }

            stk.push(nums[i]);
        }
        return result;
    }

}
