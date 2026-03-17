package com.nishchay.ds.stack.a04med;

import java.util.*;

/*
 * ====================== 3834. Merge Adjacent Equal Elements ==========================
 *
 * You are given an integer array nums.
 * You must repeatedly apply the following merge operation until no more changes can be made:
 *
 * If any two adjacent elements are equal, choose the leftmost such adjacent pair in the current array and replace them with a single element equal to their sum.
 * After each merge operation, the array size decreases by 1. Repeat the process on the updated array until no more changes can be made.
 *
 * Return the final array after all possible merge operations.
 *
 * Example 1:
 * 				Input: nums = [3,1,1,2]
 * 				Output: [3,4]
 * 				Explanation:
 * 				The middle two elements are equal and merged into 1 + 1 = 2, resulting in [3, 2, 2].
 * 				The last two elements are equal and merged into 2 + 2 = 4, resulting in [3, 4].
 * 				No adjacent equal elements remain. Thus, the answer is [3, 4].
 *
 * Example 2:
 * 				Input: nums = [2,2,4]
 * 				Output: [8]
 * 				Explanation:
 * 				The first two elements are equal and merged into 2 + 2 = 4, resulting in [4, 4].
 * 				The first two elements are equal and merged into 4 + 4 = 8, resulting in [8].
 *
 * Example 3:
 * 				Input: nums = [3,7,5]
 * 				Output: [3,7,5]
 * 				Explanation:
 * 				There are no adjacent equal elements in the array, so no operations are performed.
 *
 *
 * https://leetcode.com/problems/merge-adjacent-equal-elements/description/
 * */
public class MergeAdjacentEqualElements {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 1, 2};
        System.out.println("Merged Array : " + Arrays.toString(mergeAdjacentEqualElements(arr)));

        arr = new int[]{2, 2, 4};
        System.out.println("Merged Array : " + Arrays.toString(mergeAdjacentEqualElements(arr)));

        arr = new int[]{3, 7, 5};
        System.out.println("Merged Array : " + Arrays.toString(mergeAdjacentEqualElements(arr)));
    }


    /*
     * ================ [Expected/Optimal Approach] Using Stack - O(n * m) Time and O(1) Space =====================
     *
     * Problem (rephrased clearly)
     * 	-	If two adjacent elements are equal, they can be merged into one element whose value is their sum.
     * 	-	After merging, the new element may again merge with its new neighbor.
     * 	-	Continue until no more merges are possible.
     * 	-	Return the final array.
     *
     * Intuition:
     * Once you merge two equal numbers, the result might again be equal to its neighbor → chain reaction.
     *
     * => This screams STACK.
     * Why stack?
     * 	-	You only care about the previous element
     * 	-	You want to repeatedly compare with the last resolved value
     *
     *	Algorithm
     *		-	Create an empty stack
     *		-	Traverse the array
     *		-	If top of stack == current element → merge
     *		-	Keep merging while possible
     *		-	Push the final value
     *		-	Convert stack to array
     *
     *  Time Complexity : O(n), Each element is pushed and popped at most once.
     *  Space Complexity: O(n), stack
     *
     * Stack is a legacy synchronized class. ArrayDeque is faster, cleaner, and the recommended replacement for stack behavior in modern Java.
     * */
    private static int[] mergeAdjacentEqualElements(int[] nums) {
        // Stack<Integer> stack = new Stack<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int num : nums) {
            int curr = num;

            // Keep merging while top is equal
            while (!stack.isEmpty() && stack.peek() == curr) {
                curr = curr + stack.pop();
            }
            stack.push(curr);
        }

        // Stack is reversed, fix order
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

}
