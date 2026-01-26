package com.nishchay.algo.twopointer.a03twosum;

import java.util.*;

/*
 *	=================================== Two Sum - Pair with given Sum count pairs ================================
 *
 * Given an array arr[] of n integers and a target value, find the number of pairs of integers in the array whose sum is equal to target.
 *
 * Examples:
 * 				Input: arr[] = [1, 5, 7, -1, 5], target = 6
 *				Output:  3
 *				Explanation: Pairs with sum 6 are (1, 5), (7, -1) & (1, 5).
 *
 *				Input: arr[] = [1, 1, 1, 1], target = 2
 *				Output:  6
 *				Explanation: Pairs with sum 2 are (1, 1), (1, 1), (1, 1), (1, 1), (1, 1) and (1, 1).
 *
 *				Input: arr[] = [10, 12, 10, 15, -1], target = 125
 *				Output:  0
 *				Explanation: There is no pair with sum = target
 *
 * https://www.geeksforgeeks.org/dsa/count-pairs-with-given-sum/
 * */
public class TwoSumPairCount {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 5, 7, -1, 5};
        int target = 6;
        System.out.println("twoSumCount_2loop(arr, target)      - " + twoSumCount_2loop(arr, target));
        System.out.println("twoSumCount_hashing(arr, target)    - " + twoSumCount_hashing(arr, target));

        arr = new int[]{1, 1, 1, 1};
        target = 2;
        System.out.println("twoSumCount_2loop(arr, target)      - " + twoSumCount_2loop(arr, target));
        System.out.println("twoSumCount_hashing(arr, target)    - " + twoSumCount_hashing(arr, target));

        arr = new int[]{10, 12, 10, 15, -1};
        target = 125;
        System.out.println("twoSumCount_2loop(arr, target)      - " + twoSumCount_2loop(arr, target));
        System.out.println("twoSumCount_hashing(arr, target)    - " + twoSumCount_hashing(arr, target));
    }

    /*
     * ================ [Naive Approach] Generating all Possible Pairs - O(n2) time and O(1) space  =====================
     *
     *  The basic approach is to generate all the possible pairs and check if any of them add up to the target value.
     *  To generate all pairs, we simply run two nested loops.
     *
     *	1. Run a loop to maintain the first index of the solution in array
     *	2. Run another loop to maintain a second index of the solution for every first integer
     *	3. If at any point, the sum of values at these two indices is equal to the target
     *		 count the pair
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int twoSumCount_2loop(int[] arr, int target) {
        int n = arr.length;
        int cnt = 0;

        for (int i = 0; i < n-1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /*
     * ================ [Expected Approach] Using HashMap - O(n) time and O(n) space  =====================
     *
     * Rather than checking every possible pair, we store each number with its index in a HashMap during iterating over the array's elements.
     * 	 For each number, we calculate its complement (i.e., target - current number) and check
     * 		if this complement exists in hashMap
     * 			found the pair that sums to the target
     * 		else
     * 			we store each number in a hashMap
     *
     * Step By Step Implementations:
     * 		Create an empty HashMat
     * 		Iterate through the array and for each number in the array:
     * 		=> Calculate the complement (target - current number).
     * 		=> Check if the complement exists in the map:
     * 				- If it is, then a pair found. Return the index as (i, hm.get(complement))
     * 				- If it isn’t, add the current number to the hashMap. hm.put(arr[i], i)
     * 		If the loop completes without finding a pair, return that no pair exists.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static  int twoSumCount_hashing(int[] arr, int target) {
        Map<Integer, Integer> freq = new HashMap<>();
        int cnt = 0;

        for (int curr : arr) {
            int complement = target - curr;
            // Check if the complement (target - arr[i]) exists in the map. If yes, increment count
            if (freq.containsKey(complement)) {
                cnt = cnt + freq.get(complement);
            }
            // Increment the frequency of arr[i] (for the case of duplicates)
            freq.put(curr, freq.getOrDefault(curr, 0) + 1);
        }
        return cnt;
    }
}