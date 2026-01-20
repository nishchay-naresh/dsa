package com.nishchay.bit.appearonce;


import java.util.*;

/*
 *  =======================  Single Number III ====================
 *  ======================= Element Appears Once In Triplets - 1 such elements are there ====================
 *
 * Given an array where every element occurs three times, except one element which occurs only once.
 * Find the element that occurs once.
 *
 * Examples:
 *				Input: arr[] = [1, 10, 1, 1]
 *				Output: 10
 *				Explanation: 10 occurs once in the array while the other element 1 occurs thrice.
 *
 *				Input: arr[] = [3, 2, 1, 34, 34, 1, 2, 34, 2, 1]
 *				Output: 3
 *				Explanation: All elements except 3 occurs thrice in the array.
 *
 *              Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
 *              Output: 2
 *              Explanation: In the given array all element appear three times except ‘2' which appears once
 *
 * https://www.geeksforgeeks.org/dsa/find-the-element-that-appears-once/
 * https://leetcode.com/problems/single-number-ii/description/
 * */
class ElementAppearsOnceInTriplets {

    public static void main(String[] args) {
        int[] arr;

        arr = new int[]{1, 10, 1, 1};
        System.out.println("findSingleInTriplets26_hashing(arr) = " + findSingleInTriplets26_hashing(arr));
        System.out.println("findSingleInTriplets_sorting(arr)   = " + findSingleInTriplets_sorting(arr));
        System.out.println("findSingleInTriplets_xor(arr)       = " + findSingleInTriplets_xor(arr));

        arr = new int[]{3, 2, 1, 34, 34, 1, 2, 34, 2, 1};
        System.out.println("findSingleInTriplets26_hashing(arr) = " + findSingleInTriplets26_hashing(arr));
        System.out.println("findSingleInTriplets_sorting(arr)   = " + findSingleInTriplets_sorting(arr));
        System.out.println("findSingleInTriplets_xor(arr)       = " + findSingleInTriplets_xor(arr));

        arr =new int[] {12, 1, 12, 3, 12, 1, 1, 2, 3, 3};
        System.out.println("findSingleInTriplets26_hashing(arr) = " + findSingleInTriplets26_hashing(arr));
        System.out.println("findSingleInTriplets_sorting(arr)   = " + findSingleInTriplets_sorting(arr));
        System.out.println("findSingleInTriplets_xor(arr)       = " + findSingleInTriplets_xor(arr));

    }


    /*
     *  ================ [Better Approach] Using HashMap - O(n) time and O(n) space  =====================
     *
     * This approach uses an unordered map (freq) to track the frequency of elements in the array.
     * Iterate the array once, get the frequency of each element in hashMap
     * After building the frequency map, it collects elements that occur exactly once and returns it.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     */
    private static int findSingleInTriplets26_hashing(int[] arr) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        // Count the frequency of each element
        for (int it : arr) {
            freq.put(it, freq.getOrDefault(it, 0) + 1);
        }

        //  Return the element that appears only once
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    // CLUSTERING APPROACH (SORTING) - First sort the array then, find the smallest cluster
    // complexity -  O(n log n + n), space - O(0)
    // COUNTING SET BITS APPROACH log n <= 32 & n <= INT_MAX
    private static int findSingleInTriplets_sorting(int[] nums) {

        int n = nums.length;
        if (n < 3) {
            return nums[0];
        }
        Arrays.sort(nums);

        // finding the smallest cluster
        int i = 1;
        while (i < n) {
            if (nums[i] != nums[i - 1])
                return nums[i - 1];
            i += 3;
        }
        return nums[n - 1];
    }

    /*
     * ================ [Expected Approach] BIT MANIPULATION APPROACH - Using XOR + AND combination - O(n) Time and O(1) Space  =====================
     *
     *    We don’t count numbers. We count bits (0/1) using two variables:
     *      int ones; // bits seen 1 time
     *      int twos; // bits seen 2 times
     *      When a bit is seen 3 times, we remove it from both.
     *
     * ones = 0     // keeps only bits seen exactly once
     * twos = 0     // keeps only bits seen exactly twice
     * threes = 0
     *
     *      nums[i] will go to ones, if not in twos
     *      nums[i] will go to twos, if it is in ones -> add it to twos, delete it from ones
     *      nums[i] will go to threes, if it is in twos
     *      Operations - add , delete
     *
     *
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(2)
     * */
    private static int findSingleInTriplets_xor(int[] arr) {
        int ones = 0, twos = 0;

        for (int curr : arr) {
            ones = ( curr ^ ones ) & ~twos;
            twos = ( curr ^ twos ) & ~ones;
        }
        return ones;
    }

}
