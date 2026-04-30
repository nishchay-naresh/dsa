package com.nishchay.algo.greedy.jump;


/*
 * ================================================ Jump Game =================================================
 *
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *      1 <= nums.length <= 104
 *      0 <= nums[i] <= 1000
 *      It's guaranteed that you can reach nums[n - 1].
 *
 *
 * https://takeuforward.org/Greedy/jump-game-i
 * https://leetcode.com/problems/jump-game-ii/description/
 * */
public class JumpGame2 {

    public static void main(String[] args) {

        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println("Minimum jumps required: " + jump(nums));

        nums = new int[]{2, 3, 1, 4, 1, 1, 1, 2};
        System.out.println("Minimum jumps required: " + jump(nums));
    }

    /*
     * ======================= [Expected] Using Greedy Approach - O(n) Time and O(1) Space =====================
     * The core idea is to greedily track the farthest reachable index (maxReach).  Instead of jumping at every step,
     * keep moving within the current range. Only when you reach the end of that range, take a jump and extend the range to maxReach.
     * This way, each jump covers the maximum possible distance, ensuring the minimum number of jumps.
     *
     *              |
     *      arr = [ 2  3 1  4 1  1 1 2 ]
     *             [ ][   ][   ][     ]
     *              0   1    2     3        => 3 jumps
     *
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * */
    private static int jump(int[] nums) {
        // Initialize jumps and range trackers
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // Loop through array up to second last index
        for (int i = 0; i < nums.length - 1; i++) {
            // Update the farthest index we can reach
            farthest = Math.max(farthest, i + nums[i]);

            // If current index reaches the end of current range
            if (i == currentEnd) {
                // Increment jump count
                jumps++;

                // Update range to the farthest index
                currentEnd = farthest;
            }
        }

        // Return the total number of jumps
        return jumps;
    }
}
