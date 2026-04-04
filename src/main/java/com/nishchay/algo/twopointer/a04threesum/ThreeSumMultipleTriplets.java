package com.nishchay.algo.twopointer.a04threesum;

import java.util.*;

/*
 * ============== 3 Sum – All Distinct Triplets with given Sum in an Array ====================
 * ============== Three Sum Problem - Distinct ====================
 *
 * Given an array arr[], and an integer target, find all possible unique triplets in the array whose sum is equal to the given target value.
 * We can return triplets in any order, but all the returned triplets should be internally sorted,
 * i.e., for any triplet [q1, q2, q3], the condition q1 ≤ q2 ≤ q3 should hold.
 * In other words, given an array arr and a target value target, return all triplets a, b, c such as a + b + c = target.
 *
 * Examples :
 *				Input: arr[] = {12, 3, 6, 1, 6, 9}, target = 24
 *				Output: {{3, 9, 12}, {6, 6, 12}}
 *				Explanation: There are two unique triplets that add up to 24:
 *				3 + 9 + 12 = 24
 *				6 + 6 + 12 = 24
 *
 *				Input: arr[] = {-2, 0, 1, 1, 2}, target = 10
 *				Output: {}
 *				Explanation: There is no triplet with sum 10.
 *
 *
 * Examples - 1
 *			    Input : array: [7, 12, 3, 1, 2, -6, 5, -8, 6], target = 0
 *			    Output : [7, 1, -8]	[3, 5, -8]	[1, -6, 5]	[2, -8, 6]
 *
 * Examples - 2
 *			    Input : arr[] = {2, 7, 4, 0, 9, 5, 1, 3}, target = 6
 *			    Output : {0, 1, 5}, {0, 2, 4}, {1, 2, 3}
 *
 * Examples - 3
 *			    Input : arr[] = {12, 3, 4, 1, 6, 9}, target = 24
 *			    Output: {12, 3, 9}
 *
 * Examples - 4
 *			    Input : arr[] = {1, 2, 3, 4, 5}, target = 9
 *			    Output : {5, 3, 1}, {2, 3, 4}
 *
 *
 * https://www.callicoder.com/three-sum-problem/
 * https://www.geeksforgeeks.org/dsa/unique-triplets-sum-given-value/
 *
 * */
public class ThreeSumMultipleTriplets {

    public static void main(String[] args) {

        bruteforceWay();
        System.out.print("\n-----------------------------");
        hashingWay();
        System.out.print("\n-----------------------------");
        twoPointersWay();
    }

    private static void printTriplets(List<List<Integer>> triplets) {
        System.out.print("\nTriplets = ");
        for (List<Integer> list : triplets) {
            System.out.print(list);
        }
    }

    private static void bruteforceWay() {

        int target;
        int[] arr;

        arr = new int[]{7, 12, 3, 1, 2, -6, 5, -8, 6};
        target = 0;
        printTriplets(findTriplets_bruteforce(arr, target)); // [7, 1, -8], [3, 5, -8], [1, -6, 5], [2, -8, 6]

        arr = new int[]{2, 7, 4, 0, 9, 5, 1, 3};
        target = 6;
        printTriplets(findTriplets_bruteforce(arr, target));// [0, 1, 5], [0, 2, 4], [1, 2, 3]

        arr = new int[]{12, 3, 4, 1, 6, 9};
        target = 24;
        printTriplets(findTriplets_bruteforce(arr, target));// [12, 3, 9]

        arr = new int[]{1, 2, 3, 4, 5};
        target = 9;
        printTriplets(findTriplets_bruteforce(arr, target));// [5, 3, 1], [2, 3, 4]
    }


    private static void hashingWay() {

        int target;
        int[] arr;

        arr = new int[]{7, 12, 3, 1, 2, -6, 5, -8, 6};
        target = 0;
        printTriplets(findTriplets_hashing(arr, target)); // [7, 1, -8], [3, 5, -8], [1, -6, 5], [2, -8, 6]

        arr = new int[]{2, 7, 4, 0, 9, 5, 1, 3};
        target = 6;
        printTriplets(findTriplets_hashing(arr, target));// [0, 1, 5], [0, 2, 4], [1, 2, 3]

        arr = new int[]{12, 3, 4, 1, 6, 9};
        target = 24;
        printTriplets(findTriplets_hashing(arr, target));// [12, 3, 9]

        arr = new int[]{1, 2, 3, 4, 5};
        target = 9;
        printTriplets(findTriplets_hashing(arr, target));// [5, 3, 1], [2, 3, 4]
    }

    private static void twoPointersWay() {
        int target;
        int[] arr;

        arr = new int[]{7, 12, 3, 1, 2, -6, 5, -8, 6};
        target = 0;
        printTriplets(findTriplets_twoPointers(arr, target)); // [7, 1, -8], [3, 5, -8], [1, -6, 5], [2, -8, 6]

        arr = new int[]{2, 7, 4, 0, 9, 5, 1, 3};
        target = 6;
        printTriplets(findTriplets_twoPointers(arr, target));// [0, 1, 5], [0, 2, 4], [1, 2, 3]

        arr = new int[]{12, 3, 4, 1, 6, 9};
        target = 24;
        printTriplets(findTriplets_twoPointers(arr, target));// [12, 3, 9]

        arr = new int[]{1, 2, 3, 4, 5};
        target = 9;
        printTriplets(findTriplets_twoPointers(arr, target));// [5, 3, 1], [2, 3, 4]

        arr = new int[]{12, 3, 6, 1, 6, 9};
        target = 24;
        printTriplets(findTriplets_twoPointers(arr, target));// [3, 9, 12], [6, 6, 12]

    }

    /*
     * ========= [Naive Approach] - By Exploring all the triplets - O(n^3) Time and O(1) Space =========
     *
     *  The basic approach is to generate all the possible triplets and check if any of them add up to the target value.
     *  To generate all triplets, we simply run three nested loops.
     *
     *	1. Run 3 nested loops to maintain i, j, k index in array
     *	2. If at any point, the sum of values at these three indices is equal to target
     *
     *
     *	Approach:
     *	1. 	Run three nested loops with loop counter i, j, k
     *	2. 	The first loops will run from 0 to n-3
     *              second loop from i+1 to n-2
     *                  third loop from j+1 to n-1.
     *	3. 	Check if the target of elements at i’th, j’th, k’th is equal to target or not.
     *          arr[i] + arr[j] + arr[k] == target
     *          Print these elements
     *
     * Key Idea (Reduce 3-Sum → 2-Sum)
     *
     * Time Complexity: O(n^3).
     * Auxiliary Space: O(1).
     *
     * */
    private static List<List<Integer>> findTriplets_bruteforce(int[] arr, int target) {

        int n = arr.length;
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target) {
                        List<Integer> currTriplet = Arrays.asList(arr[i], arr[j], arr[k]);
                        Collections.sort(currTriplet);

                        // If triplet doesn't exist in the res, then only insert it.
                        if (!triplets.contains(currTriplet)) {
                            triplets.add(currTriplet);
                        }
                    }
                }
            }
        }
        return triplets;
    }


    /*
     * ========= [Better Approach] - Using Hashing - O(n^2 log n) Time and O(n) Space =========
     * Key Idea (Reduce 3-Sum → 2-Sum)
     * Instead of fixing 3 numbers, we:
     *      1.   Fix one number
     *      2.   Solve Two Sum for the remaining part of the array using hashing
     *
     * The idea is to maintain a hash set to track whether a particular element occurred in the array so far or not.
     * As we traverse all pairs using two nested loops, for each pair {arr[i], arr[j]},
     * we check if the complement (target - arr[i] - arr[j]) is already in the set.
     * If it is, we have found a triplet whose target equals the target.
     * Each valid triplet is inserted into hashSet to avoid duplicates.
     *
     *	Approach:
     *	You need to iterate through the array,
     *  fix the first element, and then try to find the other two elements using the approach similar to the two-sum problem.
     *
     * Time Complexity: O(n^2).
     * Auxiliary Space: O(n).
     *
     * */
    private static List<List<Integer>> findTriplets_hashing(int[] arr, int target) {

        int n = arr.length;
        // Set to handle duplicates
        Set<List<Integer>> triplets = new HashSet<>();

        // Generating all pairs
        for (int i = 0; i < n - 1; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int complement = target - arr[i] - arr[j];
                if (set.contains(complement)) {
                    List<Integer> currTriplet = Arrays.asList(arr[i], arr[j], complement);
                    Collections.sort(currTriplet);
                    triplets.add(currTriplet);
                }
                set.add(arr[j]);

            }
        }
        return new ArrayList<>(triplets);
    }

    /*
     * ========= [Expected Approach] - sorting then using Two Pointers Technique - O(n^2) Time and O(1) Space =========
     *
     *  Sorting then using two pointers approach - like binary search
     *
     * 	Algorithm Steps:
     * 		Sort the array
     * 		Fix the first element 'i'
     * 		Use two pointers: left = i + 1, Right = n - 1
     * 		Check : sum = arr[i] + arr[left] + arr[right]
     * 			then increment left and decrement right based on the current sum
     *
     *	Approach:
     *	1.	Sort the array in ascending order.
     *	2.	Traverse the array from start to end.
     *	3.	For every index i, create two variables left = i + 1 and right = n – 1
     *	4.	Run a loop until left is less than right if (array[i] +  array[left] + array[right]) == sum we got the triplet increment left, decrement right and continue loop
     *	5.	If the sum < 0 then increments left,
     *		    now sum will increase as array is sorted, array[left+1] > array[left]
     *	6.	If the sum > 0 then decrement right,
     *          now sum will decrease as array is sorted, so array[right-1] < array[right].
     *
     * Time Complexity: O(n * log n).
     * Auxiliary Space: O(1).
     *
     * */
    private static List<List<Integer>> findTriplets_twoPointers(int[] arr, int sum) {

        int n = arr.length;
        List<List<Integer>> triplets = new ArrayList<>();

        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) {

            // initialize left and right
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                if (arr[i] + arr[left] + arr[right] == sum) {
                    List<Integer> currTriplet = Arrays.asList(arr[i], arr[left], arr[right]);
                    Collections.sort(currTriplet);
                    triplets.add(currTriplet);
                    left++;
                    right--;
                } else if (arr[i] + arr[left] + arr[right] < sum)
                    left++;
                else
                    right--;
            }
        }
        return triplets;
    }
}
