package com.nishchay.ds.array.a10medium;


import java.util.Arrays;

/*
 * =========================== 3847. Find the Score Difference in a Game =================================
 *
 * You are given an integer array nums, where nums[i] represents the points scored in the ith game.
 * There are exactly two players. Initially, the first player is active and the second player is inactive.
 *
 * The following rules apply sequentially for each game i:
 * 		If nums[i] is odd, the active and inactive players swap roles.
 * 		In every 6th game (that is, game indices 5, 11, 17, ...), the active and inactive players swap roles.
 * 		The active player plays the ith game and gains nums[i] points.
 * 		Return the score difference, defined as the first player's total score minus the second player's total score.
 *
 *
 * Example 1:
 * 				Input: nums = [1,2,3]
 * 				Output: 0
 * 				Explanation:
 * 							Game 0: Since the points are odd, the second player becomes active and gains nums[0] = 1 point.
 * 							Game 1: No swap occurs. The second player gains nums[1] = 2 points.
 * 							Game 2: Since the points are odd, the first player becomes active and gains nums[2] = 3 points.
 * 							The score difference is 3 - 3 = 0.
 * Example 2:
 * 				Input: nums = [2,4,2,1,2,1]
 * 				Output: 4
 * 				Explanation:
 * 							Games 0 to 2: The first player gains 2 + 4 + 2 = 8 points.
 * 							Game 3: Since the points are odd, the second player is now active and gains nums[3] = 1 point.
 * 							Game 4: The second player gains nums[4] = 2 points.
 * 							Game 5: Since the points are odd, the players swap roles.
 *							Then, because this is the 6th game, the players swap again. The second player gains nums[5] = 1 point.
 * 							The score difference is 8 - 4 = 4.
 *
 * Example 3:
 * 				Input: nums = [1]
 * 				Output: -1
 * 				Explanation:
 * 							Game 0: Since the points are odd, the second player is now active and gains nums[0] = 1 point.
 * 							The score difference is 0 - 1 = -1.
 *
 * https://leetcode.com/problems/find-the-score-difference-in-a-game/description/
 *
 */
public class TwoPlayers {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int diff = scoreDifference(nums);
        System.out.println("Scores - " + Arrays.toString(nums) + ", scoreDifference - " + diff);

        nums = new int[]{2, 4, 2, 1, 2, 1};
        diff = scoreDifference(nums);
        System.out.println("Scores - " + Arrays.toString(nums) + ", scoreDifference - " + diff);

        nums = new int[]{1};
        diff = scoreDifference(nums);
        System.out.println("Scores - " + Arrays.toString(nums) + ", scoreDifference - " + diff);
    }

    /*
     * Approach:
     * 		Initialize Scores: first and second are set to 0. The game starts with the first player (isFirst = true).
     * 		Iterate through Array: Loop through every number in nums.
     * 			The Toggle Triggers:
     * 				Odd Number Rule: If arr[i] is odd (arr[i] % 2 == 1), then
     *					turn switches (isFirst = !isFirst).
     * 				Index Rule: If 6th element (at indices 5, 11, 17, etc., where i % 6 == 5)
     *					turn switches (isFirst = !isFirst) again
     * 			Score Accumulation: based on player turn (isFirst) add the score to the respective variable
     * 		Final Result: Return the difference first - second.
     *
     *  Time Complexity : O(n)
     *  Space Complexity: O(1)
     *
     * */
    public static int scoreDifference(int[] nums) {
        int first = 0, second = 0;
        boolean isFirst = true;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            // Toggle based on odd number
            if (curr % 2 == 1) {
                isFirst = !isFirst;
            }
            // Toggle every 6th element
            if (i % 6 == 5) {
                isFirst = !isFirst;
            }
            if (isFirst) {
                first += nums[i];
            } else {
                second += nums[i];
            }
        }
        return first - second;
    }
}
