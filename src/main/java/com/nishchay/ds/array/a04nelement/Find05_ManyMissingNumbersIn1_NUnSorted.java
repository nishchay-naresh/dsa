package com.nishchay.ds.array.a04nelement;

import java.util.*;

import com.nishchay.ds.array.ArrayUtils;

/*
 *	=========== Find All Numbers Disappeared in an Array ===========
 *
 *	Given an array nums of n integers where nums[i] is in the range [1, n],
 *  return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 *	Example 1:
 *		Input: nums = [4,3,2,7,8,2,3,1]
 *		Output: [5,6]
 *
 *	Example 2:
 *		Input: nums = [1,1]
 *		Output: [2]
 *
 *
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * Find All Numbers Disappeared in an Array:
 * test:[1, 5, 3, 4, 7]
 * ans: [2, 6]
 * The solution must be in JAVA
 *
 * ========================== Problem Summary =================================
 *
 *  Arrays nums[i] ∈ [1, n]
 *	Array size = n
 *
 *	Some numbers appear twice, some once.
 *	You must return numbers in range [1, n] that are missing.
 *
 *	Example
 *	Input: [4,3,2,7,8,2,3,1]
 *	Range: 1 -> 8
 *	Missing: 5, 6
 *
 *
 * */
public class Find05_ManyMissingNumbersIn1_NUnSorted {

    public static void main(String[] args) {

        findDisappearedNumbersEx();
        findDisappearedNumbersWithUpperElementEx();

    }

    private static void findDisappearedNumbersEx() {

        int[] nums;

        nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("findDisappearedNumbers(nums) = " + findDisappearedNumbers(nums)); // [5, 6]
        System.out.println("findMissingNumbers(nums) = " + findMissingNumbers(nums)); // [5, 6]

        nums = new int[]{1, 1};
        System.out.println("findDisappearedNumbers(nums) = " + findDisappearedNumbers(nums)); // [2]
        System.out.println("findMissingNumbers(nums) = " + findMissingNumbers(nums)); // [2]

    }

    /*
     *  ================ Using Extra Space – Simpler but Not Optimal - O(n) Time and O(n) Space  =====================
     *  Using Extra Space – using a boolean array to mark visited
     *
     * Creating a boolean array of size+1, using each element from the main array as index here to mark them as visited
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     * */
    private static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n + 1];

        for (int num : nums) {
            visited[num] = true;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i])
                result.add(i);
        }
        return result;
    }

    /*
     *  ================ [Optimize/Expected Approach] Using XOR Operation - O(n) Time and O(1) Space  =====================
     *	----------- Key Insight ------------
     *
     *	Values are in range 1 to n.
     *	That means: Value ↔ Index mapping exists
     *	So we can use:Index = value - 1
     *	This is the "Using Elements as Indexes" trick.
     *
     *	Algo:
     *		1.	For each number x, go to index x-1
     *		2.	Mark that index as negative (visited)
     *		3.	After marking, any index that remains positive → number missing
     *
     *  Time Complexity  : O(n) + O(n) = O(2n) = O(n)
     *  Space Complexity : O(1)
     * */
    private static List<Integer> findMissingNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        System.out.println("nums = " + Arrays.toString(nums));

        // Step 1: Mark visited numbers
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        System.out.println("nums = " + Arrays.toString(nums));
        // Step 2: Collect missing numbers
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    private static void findDisappearedNumbersWithUpperElementEx() {

        int[] nums = new int[]{1, 5, 3, 4, 7};
        System.out.println("Missing numbers = " + findDisappearedNumbersWithUpperElement(nums)); // [2, 6]

    }

    /*
     * Find All Numbers Disappeared in an Array:
     * test:[1, 5, 3, 4, 7]
     * ans: [2, 6]
     * The solution must be in JAVA
     *
     *  duplicates are not here, so hashSet is not required
     * */
    private static List<Integer> findDisappearedNumbersWithUpperElement(int[] arr) {
        List<Integer> missingNums = new ArrayList<>();

        int max = ArrayUtils.findMax(arr);
        for (int i = 1; i <= max; i++)
            if (-1 == ArrayUtils.linearSearch(arr, i))
                missingNums.add(i);

        return missingNums;
    }

}
