package com.nishchay.algo.twopointer.a05medium;

import java.util.Arrays;

/*
 *	=================================== 3 Sum - Triplet Sum Closest to Target ================================
 * Given an array arr[] of n integers and an integer target, find the sum of triplets such that the sum is closest to target.
 * Note: If there are multiple sums closest to target, print the maximum one.
 *
 * Examples:
 *				Input: arr[] = [-1, 2, 2, 4], target = 4
 *				Output: 5
 *				Explanation: All possible triplets
 *
 *				[-1, 2, 2], sum = (-1) + 2 + 2 = 3
 *				[-1, 2, 4], sum = (-1) + 2 + 4 = 5
 *				[-1, 2, 4], sum = (-1) + 2 + 4 = 5
 *				[2, 2, 4], sum = 2 + 2 + 4 = 8
 *				Triplet [-1, 2, 2], [-1, 2, 4] and [-1, 2, 4] have sum closest to target, so return the maximum one, that is 5.
 *
 *				Input: arr[] = [1, 10, 4, 5], target = 10
 *				Output: 10
 *				Explanation: All possible triplets
 *
 *				[1, 10, 4], sum = (1 + 10 + 4) = 15
 *				[1, 10, 5], sum = (1 + 10 + 5) = 16
 *				[1, 4, 5], sum = (1 + 4 + 5) = 10
 *				[10, 4, 5], sum = (10 + 4 + 5) = 19
 *				Triplet [1, 4, 5] has sum = 10 which is closest to target.
 *
 * https://www.geeksforgeeks.org/dsa/find-a-triplet-in-an-array-whose-sum-is-closest-to-a-given-number/
 * https://www.callicoder.com/triplet-sum-closest-to-target/
 * https://leetcode.com/problems/3sum-closest/description/
 *
 * */
public class ThreeSumClosestToTarget {

    public static void main(String[] args) {

        int[] arr = new int[]{-1, 2, 2, 4};
        int target = 4;
        System.out.println("closest3Sum_bruteforce(arr, target) - " + closest3Sum_bruteforce(arr, target)); // 5
        System.out.println("closest3Sum_2pointers(arr, target)  - " + closest3Sum_2pointers(arr, target));  // 5

        arr = new int[]{1, 10, 4, 5};
        target = 10;
        System.out.println("closest3Sum_bruteforce(arr, target) - " + closest3Sum_bruteforce(arr, target)); // 10
        System.out.println("closest3Sum_2pointers(arr, target)  - " + closest3Sum_2pointers(arr, target));  // 10
    }

    /*
     * ================ [Naive Approach] Generating All Triplets - O(n^3) Time and O(1) Space  =====================
     *
     *  The naive approach is to explore all subsets of size three and keep a track of the difference between target and the sum of the subset.
     *  Then, return the sum which is closest to target.
     *
     *  Time Complexity     : O(n^3)
     *  Space complexity    : O(1)
     */
    private static int closest3Sum_bruteforce(int[] arr, int target) {
        int n = arr.length;
        int minDiff = Integer.MAX_VALUE;
        int result = 0;

        // Generating all possible triplets
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int currSum = arr[i] + arr[j] + arr[k];
                    int currDiff = Math.abs(currSum - target);

                    // if currentDiff is less than minDiff, it indicates that this triplet is closer to the target
                    if (currDiff < minDiff) {
                        result = currSum;
                        minDiff = currDiff;
                    }
                    // If multiple sums are closest, take the maximum one
                    else if (currDiff == minDiff) {
                        result = Math.max(result, currSum);
                    }
                }
            }
        }
        return result;
    }

    /*
     * ================ [Expected Approach] - Sorting and Two Pointer - O(n^2) Time and O(1) Space  =====================
     *
     * Initially, we sort the input array so that we can apply two pointers technique.
     * Then, we iterate over the array fixing the first element of the triplet and then use two pointers technique to find the remaining two elements.
     * Set one pointer at the beginning (left) and another at the end (right) of the remaining array.
     * We then find the absolute difference between the sum of triplet and target and store the triplet having minimum absolute difference.
     *
     * Check the sum of the elements at these two pointers:
     *	-	If sum == target, we’ve found the triplet with sum = target, therefore this is the triplet with closest sum.
     *	-	If sum < target, move left pointer towards right to increase the sum.
     *	-	If sum > target, move right pointer towards left to decrease the sum.
     *
     *
     *  Time Complexity     : O(n × log(n)) + n^2 =  O(n^2)
     *  Space complexity    : O(1)
     */
    private static int closest3Sum_2pointers(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        int result = 0;
        int minDiff = Integer.MAX_VALUE;

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Initialize left and right pointers with start and end of remaining subarray
            int left = i + 1, right = n - 1;

            while (left < right) {
                int currSum = arr[i] + arr[left] + arr[right];

                // If |currSum - target| < minDiff, then we have found a triplet which is closer to target
                if (Math.abs(currSum - target) < minDiff) {
                    minDiff = Math.abs(currSum - target);
                    result = currSum;
                }
                // If multiple sums are closest, take maximum one
                else if (Math.abs(currSum - target) == minDiff) {
                    result = Math.max(result, currSum);
                }
                if (currSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

}
