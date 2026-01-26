package com.nishchay.algo.twopointer.a04threesum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 *	=================================== 3 Sum problem ================================
 *
 *	The 3-Sum problem is a classic algorithmic problem where the goal is to find all unique triplets in an array that sum up to a specific target value, usually zero.
 *	Emphasizes ones understanding on:
 *	    - array manipulation
 *      - sorting
 *
 *	Problem can categories in 2 categories:
 *	    1. 3Sum on Unsorted Input
 *	    2. 3Sum on Sorted Input
 *
 * https://www.geeksforgeeks.org/dsa/3sum/
 *
 *	=================================== 3 Sum - Triplet Sum in Array ================================
 * Given an array arr[] and an integer sum, check if there is a triplet in the array which sums up to the given target sum.
 *
 * Examples:
 *				Input: arr[] = [1, 4, 45, 6, 10, 8], target = 13
 *				Output: true
 *				Explanation: The triplet [1, 4, 8] sums up to 13
 *
 *				Input: arr[] = [1, 2, 4, 3, 6, 7], target = 10
 *				Output: true
 *				Explanation: The triplets [1, 3, 6] and [1, 2, 7] both sum to 10.
 *
 *				Input: arr[] = [40, 20, 10, 3, 6, 7], sum = 24
 *				Output: false
 *				Explanation:  No triplet in the array sums to 24.
 *
 * https://www.geeksforgeeks.org/dsa/find-a-triplet-that-sum-to-a-given-value/
 *
 * */
public class ThreeSumExist {

    public static void main(String[] args) {
        int[] arr = new int[]{0, -1, 2, -3, 1};
        int target = -2;
        System.out.println("hasTripletSum_bruteforce(arr, target) - " + hasTripletSum_bruteforce(arr, target));
        System.out.println("hasTripletSum_hashing(arr, target)    - " + hasTripletSum_hashing(arr, target));
        System.out.println("hasTripletSum_2pointers(arr, target)  - " + hasTripletSum_2pointers(arr, target));

        arr = new int[]{1, 4, 45, 6, 10, 8};
        target = 13;
        System.out.println("hasTripletSum_bruteforce(arr, target) - " + hasTripletSum_bruteforce(arr, target));
        System.out.println("hasTripletSum_hashing(arr, target)    - " + hasTripletSum_hashing(arr, target));
        System.out.println("hasTripletSum_2pointers(arr, target)  - " + hasTripletSum_2pointers(arr, target));
    }

    /*
     * ================ [Naive Approach] Generating All Triplets - O(n^3) Time and O(1) Space  =====================
     *
     *  A simple method is to generate all possible triplets and compare the sum of every triplet with the given target.
     *  If the sum is equal to target, return true. Otherwise, return false.
     *
     *  Time Complexity     : O(n^3)
     *  Space complexity    : O(1)
     */
    private static boolean hasTripletSum_bruteforce(int[] arr, int target) {
        int n = arr.length;

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Fix the second element as arr[j]
            for (int j = i + 1; j < n - 1; j++) {

                // Now look for the third number
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target)
                        return true;
                }
            }
        }
        return false;
    }


    /*
     * ================ [Better Approach] - Hash Set - O(n^2) Time and O(n) Space =====================
     *
     * The idea is to traverse every element arr[i] in a loop. For every arr[i],
     * use the hashing based solution of 2 Sum Problem to check if there is a pair with sum equal to given sum - arr[i].
     *
     *	Approach:
     *	-	Iterate through the array, fixing the first element (arr[i]) for the triplet.
     *	-	For each arr[i], use a Hash Set to store potential second elements and run another loop inside it for j from i+1 to n-1.
     *	-	Inside a nested loop, check if given sum - arr[i] - arr[j] is present in the hash set. If yes, then print the triplet.
     *	-	If no triplet is found in the entire array, the function returns false.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(n)
     */
    static boolean hasTripletSum_hashing(int[] arr, int target) {
        int n = arr.length;

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Hash set to store potential second elements
            Set<Integer> set = new HashSet<>();

            // Fix the second element as arr[j]
            for (int j = i + 1; j < n; j++) {
                int third = target - arr[i] - arr[j];

                // Search for third element in hash set
                if (set.contains(third)) {
                    return true;
                }
                set.add(arr[j]);
            }
        }
        return false;
    }

    /*
     * ================ [Expected Approach] - Sorting and Two Pointer - O(n^2) Time and O(1) Space  =====================
     *
     *  We first sort the array. After sorting, we traverse every element arr[i] in a loop.
     *  For every arr[i], use the Two Pointer Technique based solution of 2 Sum Problem to check if there is a pair with sum equal to given sum - arr[i].
     *
     * Check the sum of the elements at these two pointers:
     *	-	If the sum equals the target, we’ve found the pair.
     *	-	If the sum is less than the target, move the left pointer.
     *	-	If the sum is greater than the target, move the right pointer.
     *
     *
     *  Time Complexity     : O(n × log(n)) + O(n^2) = O(n^2)
     *  Space complexity    : O(1)
     */
    private static boolean hasTripletSum_2pointers(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Initialize left and right pointers with start and end of remaining subarray
            int left = i + 1, right = n - 1;

            int requiredSum = target - arr[i];
            while (left < right) {
                if (arr[left] + arr[right] == requiredSum)
                    return true;
                if (arr[left] + arr[right] < requiredSum)
                    left++;
                else if (arr[left] + arr[right] > requiredSum)
                    right--;
            }
        }
        return false;
    }

}