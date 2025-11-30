package com.nishchay.algo.recursion.subset;

/*
 *	===========Print all distinct subsets of a given set===========
 * Given a set of positive integers, find all its subsets.
 *
 *Examples:
 *
 *	Input: array = {1, 2, 3}
 *	Output: // this space denotes null element.
 *	         1
 *	         1 2
 *	         1 2 3
 *	         1 3
 *	         2
 *	         2 3
 *	         3
 *	Explanation: These are all the subsets that
 *	can be formed using the array.
 *
 * https://www.techiedelight.com/print-distinct-subsets-given-set/
 * https://www.geeksforgeeks.org/backtracking-to-find-all-subsets/
 *
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintSubSet {

    public static void main(String[] args) {

        //Main List for storing all subsets
        List<List<Integer>> subset = new ArrayList<>();

        // Input ArrayList
        ArrayList<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);

        findSubsets(subset, input, new ArrayList<>(), 0);


        // Comparator is used so that all subset get
        // sorted in ascending order of values
        Collections.sort(subset, (o1, o2) -> {
            int n = Math.min(o1.size(), o2.size());
            for (int i = 0; i < n; i++) {
                if (o1.get(i) == o2.get(i)) {
                    continue;
                } else {
                    return o1.get(i) - o2.get(i);
                }
            }
            return 1;
        });


        // Printing Subset
        for (int i = 0; i < subset.size(); i++) {
            for (int j = 0; j < subset.get(i).size(); j++) {
                System.out.print(subset.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }


    //Time Complexity : O(2 ^ n)
    private static void findSubsets(List<List<Integer>> subset, ArrayList<Integer> nums, ArrayList<Integer> output, int index) {
        // Base Condition
        if (index == nums.size()) {
            subset.add(output);
            return;
        }

        // Not Including Value which is at Index
        findSubsets(subset, nums, new ArrayList<>(output), index + 1);

        // Including Value which is at Index
        output.add(nums.get(index));
        findSubsets(subset, nums, new ArrayList<>(output), index + 1);
    }
}

/*
 * O/P =>
 *      1
 *      1 2
 *      1 2 3
 *      1 3
 *      2
 *      2 3
 *      3
 * */