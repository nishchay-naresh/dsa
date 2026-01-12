package com.nishchay.old;

import java.util.Arrays;

/*
 *==============1920. Build Array from Permutation ====================
 *
 * Build an array from its element(which in turns are index)
 *
 * Given a zero-based permutation nums (0-indexed),
 * build an array ans of the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
 *
 * A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).
 *
 *	Example 1:
 *
 * 		Input: nums = [0,2,1,5,3,4]
 * 		Output: [0,1,2,4,5,3]
 * 		Explanation: The array ans is built as follows:
 * 		ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
 * 		    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
 * 		    = [0,1,2,4,5,3]
 *
 *	Example 2:
 *
 *		Input: nums = [5,0,1,2,3,4]
 *		Output: [4,5,0,1,2,3]
 *		Explanation: The array ans is built as follows:
 *		ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
 *		    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
 *		    = [4,5,0,1,2,3]
 *
 * https://youtu.be/0Q7m9VFCrUI?si=E4RZgX6z4Q4ikBRr
 * https://leetcode.com/problems/build-array-from-permutation/
 * */
public class BuildArrayFromPermutation {

    public static void main(String[] args) {
//        usingExtraArrayEx();
        usingSameArrayEx();
    }

    private static void usingExtraArrayEx() {
        int[] arr;
        int[] res;

        arr = new int[]{0, 2, 1, 5, 3, 4};
        res = buildArray(arr);
        System.out.println("res = " + Arrays.toString(res));// [0, 1, 2, 4, 5, 3]

        arr = new int[]{5, 0, 1, 2, 3, 4};
        res = buildArray(arr);
        System.out.println("res = " + Arrays.toString(res));// [4, 5, 0, 1, 2, 3]
    }

    private static void usingSameArrayEx() {
        int[] arr;

        arr = new int[]{0, 2, 1, 5, 3, 4};
        buildArrayNoSpace(arr);
        System.out.println("res = " + Arrays.toString(arr));//{0,1,2,4,5,3}

        arr = new int[]{5, 0, 1, 2, 3, 4};
        buildArrayNoSpace(arr);
        System.out.println("res = " + Arrays.toString(arr));//{4,5,0,1,2,3}
    }

    /*
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     * */
    private static int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            res[i] = nums[nums[i]];
        }
        return res;
    }

    /*
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     * */
    private static int[] buildArrayNoSpace(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] + n * (nums[nums[i]] % n);
        }

        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] / n;
        }
        return nums;
    }
}
