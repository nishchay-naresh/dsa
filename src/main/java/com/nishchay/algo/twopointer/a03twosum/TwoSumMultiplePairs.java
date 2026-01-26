package com.nishchay.algo.twopointer.a03twosum;

import java.util.*;

/*
 *	=================================== Two Sum - Pair with given Sum, multiple pairs ================================
 *
 * Given an Array of integers, return all the pairs which sum up to a specific integer.
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * Solve the same problem for multiple pairs.
 *
 * You can return the answer in any order.
 *
 *      Examples:
 *		        Input: nums = [-5, 1, -40, 20, 6, 8, 7], target = 15
 *		        Output: [-5, 20], [7, 8]
 *
 *		        Input: nums = [1, 2, 3, 4, 5, 6], target = 7
 *		        Output: [1, 6],[2, 5],[3, 4]
 *
 * https://coderbyte.com/algorithm/two-sum-problem
 * https://www.callicoder.com/two-sum-problem/
 * https://www.geeksforgeeks.org/given-an-array-a-and-a-number-x-check-for-pair-in-a-with-sum-as-x/
 * */
public class TwoSumMultiplePairs {

    public static void main(String[] args) {

        int[] arr = new int[]{-5, 1, -40, 20, 6, 8, 7};
        int target = 15;
        printPairs(twoSum_2loop(arr, target));
        printPairs(twoSum_hashing(arr, target));

        arr = new int[]{1, 2, 3, 4, 5, 6};
        target = 7;
        printPairs(twoSum_2loop(arr, target));
        printPairs(twoSum_hashing(arr, target));

        arr = new int[]{3, 2, 1, 4};
        target = 8;
        printPairs(twoSum_2loop(arr, target));
        printPairs(twoSum_hashing(arr, target));

        arr= new int[]{0, -1, 2, -3, 1};
        target = -2;
        System.out.print("\n----------------");
        printPairs(twoSum_2loop(arr, target));
        printPairs(twoSum_hashing(arr, target));
    }

    private static void printPairs(List<int[]> res) {
        System.out.print("\nPairs = ");
        for(int[] e : res){
            System.out.print(Arrays.toString(e));
        }
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
     *		 Print its indices
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static List<int[]> twoSum_2loop(int[] arr, int target) {
        int n = arr.length;
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] + arr[j] == target)
                    pairs.add(new int[]{arr[i], arr[j]});

        if (pairs.isEmpty()) {
            pairs.add(new int[]{-1, -1});
        }
        return pairs;
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
     *
     * Step By Step Implementations:
     * 		Create an empty HashMat
     * 		Iterate through the array and for each number in the array:
     * 		=> Calculate the complement (target - current number).
     * 		=> Check if the complement exists in the set/map:
     * 				- If it is, then a pair found. Return the index as (i, hm.get(complement))
     * 				- If it isn’t, add the current number to the hashMap. hm.put(arr[i], i)
     * 		If the loop completes without finding a pair, return that no pair exists.
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     *
     *  Efficient code works when an input array has duplicates
     */
    private static  List<int[]> twoSum_hashing(int[] arr, int target) {
        List<int[]> pairs = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();

        for (int curr : arr) {
            int complement = target - curr;
            if (set.contains(complement)) {
                pairs.add(new int[]{complement, curr});
            }
            set.add(curr);
        }
        if(pairs.isEmpty()){
            pairs.add(new int[]{-1, -1});
        }
        return pairs;
    }

}