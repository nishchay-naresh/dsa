package com.nishchay.algo.recursion.subset;

import java.util.ArrayList;
import java.util.List;

/*
 *	=========== Generating all possible Subsequences using Recursion ===========
 * Given an array arr[]. The task is to find all the possible subsequences of the given array using recursion.
 *
 *Examples:
 *			Input: arr[] = [1, 2, 3]
 *			Output : [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3], []
 *
 *			Input: arr[] = [1, 2]
 *			Output : [2], [1], [1, 2], []
 *
 *
 * https://www.geeksforgeeks.org/dsa/generating-all-possible-subsequences-using-recursion/
 * https://leetcode.com/problems/subsets/description/
 *
 * */
public class GetAllSubsequences {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3 };

        List<List<Integer>> subsets = getSubsets(arr);
        System.out.println("subsets = " + subsets);
        printSubsets(subsets);
    }

    public static List<List<Integer>> getSubsets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, arr, new ArrayList<>(), result);
        return result;
    }

    /*
    *
    * Time complexity: O(2^n)
    * Auxiliary Space: O(n)
    * */
    private static void helper(int index, int[] arr, List<Integer> subarray, List<List<Integer> > result) {

        // Base case: When we reach the end of the array, add the current subsequence to the result
        if (index == arr.length) {
            result.add(new ArrayList<>(subarray));
            return;
        }

        // Include the current element in the subsequence
        subarray.add(arr[index]);

        // Recurse to the next element
        helper(index + 1, arr, subarray, result);

        // Backtrack: Remove the current element and explore the next possibility
        subarray.remove(subarray.size() - 1);

        //  Do not include the current element in the subsequence
        helper(index + 1, arr, subarray, result);
    }

    private static void printSubsets( List<List<Integer>> subsets) {
        for (List<Integer> subsequence : subsets) {
            for (int num : subsequence) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
