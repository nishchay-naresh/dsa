package com.nishchay.algo.twopointer.a03twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 *	=================================== Two Sum - Pair with given Sum, pair index ================================
 *
 * Given an Array of integers, return all the pairs which sum up to a specific integer.
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order. - Assuming one pair is there
 *
 * Examples:
 *		        Input: nums = [2,7,11,15], target = 9
 *		        Output: [0,1]
 *		        Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 *		        Input: nums = [3,2,4], target = 6
 *		        Output: [1,2]
 *
 *		        Input: nums = [3,3], target = 6
 *		        Output: [0,1]
 *
 * https://leetcode.com/problems/two-sum/
 * https://www.tutorialcup.com/leetcode-solutions/two-sum-leetcode-solution.htm
 * https://redquark.org/leetcode/0001-two-sum/
 * */
public class TwoSumIndex {

    public static void main(String[] args) {
        twoSumIndexSortedEx();
        twoSumIndexNonsortedEx();
    }

    private static void twoSumIndexSortedEx() {
        int[] arr = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum_2pointers(arr, target)));
    }

    private static void twoSumIndexNonsortedEx() {
        int[] arr = new int[]{0, -1, 2, -3, 1};
        int target = -2;

        System.out.println("twoSum_2loop(arr, target)           - " + Arrays.toString(twoSum_2loop(arr, target)));
        System.out.println("twoSum_hashing(arr, target)         - " + Arrays.toString(twoSum_hashing(arr, target))); //{ 3, 4}

        arr = new int[]{3, 2, 1, 4};
        target = 6;
        System.out.println("twoSum_2loop(arr, target)           - " + Arrays.toString(twoSum_2loop(arr, target)));
        System.out.println("twoSum_hashing(arr, target)         - " + Arrays.toString(twoSum_hashing(arr, target)));//{ 1, 3}

        target = 8;
        System.out.println("twoSum_2loop(arr, target)           - " + Arrays.toString(twoSum_2loop(arr, target)));
        System.out.println("twoSum_hashing(arr, target)         - " + Arrays.toString(twoSum_hashing(arr, target)));//{ -1, -1}
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
    private static int[] twoSum_2loop(int[] arr, int target) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] + arr[j] == target)
                    return new int[]{i, j};

        return new int[]{-1, -1};
    }

    /*
     * ================ [Better Approach 2] Sorting and Two-Pointer Technique - O(n × log(n)) time and O(1) space  =====================
     *
     *  Given an sorted array, find the pair index using two-pointer technique(binary search).
     *
     * Check the sum of the elements at these two pointers:
     *	-	If the sum equals the target, we’ve found the pair.
     *	-	If the sum is less than the target, move the left pointer.
     *	-	If the sum is greater than the target, move the right pointer.
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
     */
    private static int[] twoSum_2pointers(int[] numbers, int target) {

        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target)
                return new int[]{left+1,  right+1};
            else if (sum < target)
                left++;
            else
                right--;
        }
        return new int[]{-1, -1};
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
     * 		=> Check if the complement exists in the map:
     * 				- If it is, then a pair found. Return the index as (i, hm.get(complement))
     * 				- If it isn’t, add the current number to the hashMap. hm.put(arr[i], i)
     * 		If the loop completes without finding a pair, return that no pair exists.
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int[] twoSum_hashing(int[] arr, int target) {
        int[] result = new int[]{-1, -1};
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                result[1] = i;
                result[0] = map.get(complement);
                return result;
            }
            map.put(arr[i], i);
        }
        return result;
    }
}