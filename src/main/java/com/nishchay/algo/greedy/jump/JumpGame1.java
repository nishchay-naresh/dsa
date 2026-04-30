package com.nishchay.algo.greedy.jump;


import java.util.Arrays;

/*
 * ================================================ Jump Game =================================================
 *
 * Given a array of positive integers arr, where each element denotes the maximum length of jump you can cover.
 * Find out if you can make it to the last index starting from the first index of the list.
 * Return true if you can reach the last index else false.
 *
 * You are given an integer array nums.
 * You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 * Example 1:
 *              Input: nums = [2,3,1,1,4]
 *              Output: true
 *              Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *              Input: nums = [3,2,1,0,4]
 *              Output: false
 *              Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 *			    Input: arr = [1, 2, 0, 3, 0, 0]
 *			    Output: true
 *			    Explanation: Jump 1 step from first index to second index. Then jump 2 steps to reach 4th index, and now jump 2 steps to reach the end.
 *
 *			    Input: arr = [1, 0, 2]
 *			    Output: false
 *			    Explanation: You can't reach the end of the array.
 *
 *
 * Constraints:
 * 	1 <= nums.length <= 104
 * 	0 <= nums[i] <= 105
 *
 * https://www.geeksforgeeks.org/problems/jump-game/1
 * https://takeuforward.org/Greedy/jump-game-i
 * https://leetcode.com/problems/jump-game/description/
 * */
public class JumpGame1 {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 1, 1, 4};
        System.out.println("arr - " + Arrays.toString(arr) + ", isJumpPossibleTillEnd - " + isJumpPossibleTillEnd(arr));

        arr = new int[]{3, 2, 1, 0, 4};
        System.out.println("arr - " + Arrays.toString(arr) + ", isJumpPossibleTillEnd - " + isJumpPossibleTillEnd(arr));

        arr = new int[]{1, 2, 3, 1, 1, 0, 2, 5};
        System.out.println("arr - " + Arrays.toString(arr) + ", isJumpPossibleTillEnd - " + isJumpPossibleTillEnd(arr));

        arr = new int[]{1, 2, 4, 1, 1, 0, 2, 5};
        System.out.println("arr - " + Arrays.toString(arr) + ", isJumpPossibleTillEnd - " + isJumpPossibleTillEnd(arr));

        arr = new int[]{1, 2, 0, 3, 0, 0};
        System.out.println("arr - " + Arrays.toString(arr) + ", isJumpPossibleTillEnd - " + isJumpPossibleTillEnd(arr));

        arr = new int[]{1, 0, 2};
        System.out.println("arr - " + Arrays.toString(arr) + ", isJumpPossibleTillEnd - " + isJumpPossibleTillEnd(arr));
    }

    /*
     * ==================================== [Expected Approach] Use greedy approach  ===================================
     *
     * Initialize a variable maxIndex to keep track of the farthest index we can reach from the start
     * Iterate through each index of the array and at each interaction
     * 	check if the current index is greater than the maximum index we can reach so far.
     * 		if true, it means the current index is not reachable hence we return false.
     * 	But if current index is reachable we update the maxIndex -  maxIndex = Math.max(maxIndex, i + arr[i]);
     * If we exit the loop without returning false, it means we have reached the last index, therefore we can return a true.
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * */
    public static boolean isJumpPossibleTillEnd(int[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i > maxIndex) {
                return false;
            }
            // update the farthest reachable index
            maxIndex = Math.max(maxIndex, i + arr[i]);
        }
        return true;
    }
}
