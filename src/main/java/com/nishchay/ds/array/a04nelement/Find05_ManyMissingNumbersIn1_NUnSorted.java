package com.nishchay.ds.array.a04nelement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
 * */
public class Find05_ManyMissingNumbersIn1_NUnSorted {

    public static void main(String[] args) {

        findDisappearedNumbersEx();
        findDisappearedNumbersWithUpperElementEx();

    }


    private static void findDisappearedNumbersEx() {

        int[] nums;

        nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Missing numbers = " + findDisappearedNumbers(nums)); // [5, 6]

        nums = new int[]{1, 1};
        System.out.println("Missing numbers = " + findDisappearedNumbers(nums)); // [2]

    }

    // Constrain is here is that - to total no of element  = N ( max value of array)
    private static List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> missingNums = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int n = nums.length;

        // adding them in set will remove the duplicates, set will have 1 to N
        for (int curr : nums )
            set.add(curr);

        for (int i = 1; i <= n; i++)
            if (!set.contains(i))
                missingNums.add(i);

        return missingNums;

    }

    private static void findDisappearedNumbersWithUpperElementEx() {

        int[] nums = new int[]{1, 5, 3, 4, 7};
        System.out.println("Missing numbers = " + findDisappearedNumbersWithUpperElement(nums)); // [2, 6]
        System.out.println("Missing numbers = " + findDisappearedNumbersWithUpperElement_stream(nums)); // [2, 6]

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

    private static List<Integer> findDisappearedNumbersWithUpperElement_stream(int[] arr) {
        return IntStream.rangeClosed(1, ArrayUtils.findMax(arr))
                        .filter(e -> -1 == ArrayUtils.linearSearch(arr,e))
                        .boxed()
                        .collect(Collectors.toList());

    }
}
