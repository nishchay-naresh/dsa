package com.nishchay.algo.twopointer.a05medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * ======================= 2149. Rearrange Array Elements by Sign ====================
 *
 * Given an array arr[] of size n, consisting positive and negative numbers.
 * The task is to rearrange it in alternate positive and negative manner without changing the relative order of positive and negative numbers.
 * In case of extra positive/negative numbers, they appear at the end of the array.
 *
 * Note: The rearranged array should start with a positive number and 0 (zero) should be considered as a positive number.
 *
 * Examples:
 *				Input:  arr[] = [1, 2, 3, -4, -1, 4]
 *				Output: arr[] = [1, -4, 2, -1, 3, 4]
 *
 *				Input:  arr[] = [-5, -2, 5, 2, 4, 7, 1, 8, 0, -8]
 *				Output: arr[] = [5, -5, 2, -2, 4, -8, 7, 1, 8, 0]
 *
 * https://www.geeksforgeeks.org/dsa/rearrange-array-alternating-positive-negative-items-o1-extra-space/
 * https://takeuforward.org/arrays/rearrange-array-elements-by-sign
 * https://leetcode.com/problems/rearrange-array-elements-by-sign/description/
 * */
public class RearrangeArrayByPN {

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 2, 3, -4, -1, 4};

        // Equal number of positive and negative integers
        int[] result = rearrangeBySign_sameOddAndEVen_nBy2(arr);
        System.out.println("result = " + Arrays.toString(result));

        // Handle Unequal Counts
        arr = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        result = rearrangeBySign(arr);
        System.out.println("result = " + Arrays.toString(result));

    }

    /*
     * Equal number of positive and negative integers
     * ======================== [Naive Approach] Most Intuitive  ============================
     * Traverse array → store:
     *      positives list
     *      negatives list
     *  Merge them alternately
     *
     * ======================== [Optimal Approach] 2 pointer approach  ============================
     * 	Instead of extra arrays, place directly using indices:
     *      Even index(0,2,4,6,8,10,...) → positive
     *      Odd index (1,3,5,7,9,11,...) → negative
     *
     *
     *		int[] result = new int[n];
     *	    int posIndex = 0; // even index
     *	    int negIndex = 1; // odd index
     *
     *	    for (int curr : nums) {
     *	        if (curr >= 0)
     *				result[posIndex] = curr
     *				posIndex += 2
     *			else
     *				result[negIndex++] = curr
     *				negIndex += 2
     *		}
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int[] rearrangeBySign_sameOddAndEVen_nBy2(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];
        int posIndex = 0, negIndex = 1;

        for (int curr : nums) {
            if (curr < 0) {
                result[negIndex] = curr;
                negIndex += 2;
            } else {
                result[posIndex] = curr;
                posIndex += 2;
            }
        }
        return result;
    }

    /*
     * For Non-Equal number of positive and negative integers
     * ======================== [Naive Approach] Most Intuitive  ============================
     * Traverse array → store:
     *      positives list
     *      negatives list
     *  Merge them alternately
     *
     */
    public static int[] rearrangeBySign(int[] nums) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        // Separate
        for (int curr : nums) {
            if (curr >= 0)
                pos.add(curr);
            else
                neg.add(curr);
        }

        int n = nums.length;
        int[] result = new int[n];

        int i = 0, p = 0, ne = 0;

        // Alternate
        while (p < pos.size() && ne < neg.size()) {
            result[i++] = pos.get(p++);
            result[i++] = neg.get(ne++);
        }

        // Add remaining positives
        while (p < pos.size()) {
            result[i++] = pos.get(p++);
        }

        // Add remaining negatives
        while (ne < neg.size()) {
            result[i++] = neg.get(ne++);
        }
        return result;
    }
}
