package com.nishchay.algo.twopointer.a04threesum;

import java.util.*;

/*
 * ============== 3 Sum – All Distinct Triplets with given Sum in an Array ====================
 * ============== Find all triplets with zero sum - Without Duplicate/distinct elements ====================
 *
 * Given an array arr[], and an integer target, find all possible unique triplets in the array whose sum is equal to the given target value.
 * We can return triplets in any order, but all the returned triplets should be internally sorted,
 *  i.e., for any triplet [q1, q2, q3], the condition q1 ≤ q2 ≤ q3 should hold.
 * Given an array of distinct elements. The task is to find triplets in the array whose sum is zero.
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
 *				Explanation: There is not triplet with sum 10.
 *
 * Examples - 1
 *			    Input : arr[] = {0, -1, 2, -3, 1}
 *			    Output : [0, -1, 1]	[2, -3, 1]
 *				Explanation : The triplets with zero sum are  0 + -1 + 1 = 0 and 2 + -3 + 1 = 0
 *
 * Examples - 2
 *			    Input : arr[] = {1, -2, 1, 0, 5}
 *			    Output : [1,-2, 1]
 *				Explanation : The triplets with zero sum is 1 + -2 + 1 = 0
 *
 * Examples - 3
 *			    Input: a[] = [-5, 3, 2, -3, 1]
 *			    Output: [-5, 3, 2] [2, -3, 1]
 *
 * Examples - 4
 *			    Input: a[] = [-1, 0, 1, 2, -2, -4]
 *			    Output: [-1, 0, 1]	[0, 2, -2]
 *
 * https://www.geeksforgeeks.org/dsa/unique-triplets-sum-given-value/
 *
 * ========================= Find all Triplets with zero sum =============================
 * Been asked in epam solutions
 *
 *      Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 *      such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *      Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *          Input: nums = [-1, 0, 1, 2, -2, -4]
 *          Output: [-1, 0, 1]	[0, 2, -2]
 *
 * Example 2:
 *          Input: nums = []
 *          Output: []
 *
 * Example 3:
 *          Input: nums = [0]
 *          Output: []
 *
 * Constraints:
 *      0 <= nums.length <= 3000
 *      -105 <= nums[i] <= 105
 *
 *
 * */
public class TripletsWithZeroSumDistinct {

    public static void main(String[] args) {
        bruteforceWay();
        hashingWay();
        twoPointersWay();
    }

    private static void bruteforceWay() {
        validateEdgeCase_bruteforce();

        int[] arr = new int[] {0, -1, 2, -3, 1};
        printTriplets(findTriplets_bruteforce(arr)); // [0, -1, 1] [2, -3, 1]

        arr = new int[]{1, -2, 1, 0, 5};
        printTriplets(findTriplets_bruteforce(arr)); // [1, -2, 1]

        arr = new int[]{-5, 3, 2, -3, 1};
        printTriplets(findTriplets_bruteforce(arr));  // [-5, 3, 2][2, -3, 1]

        arr = new int[]{-1, 0, 1, 2, -2, -4};
        printTriplets(findTriplets_bruteforce(arr));  // [-1, 0, 1]	[0, 2, -2]
    }

    private static void hashingWay() {

        validateEdgeCase_hashing();

        int[] arr = new int[] {0, -1, 2, -3, 1};
        printTriplets(findTriplets_hashing(arr)); // [0, -1, 1] [2, -3, 1]

        arr = new int[]{1, -2, 1, 0, 5};
        printTriplets(findTriplets_hashing(arr)); // [1, -2, 1]

        arr = new int[]{-5, 3, 2, -3, 1};
        printTriplets(findTriplets_hashing(arr));  // [-5, 3, 2][2, -3, 1]

        arr = new int[]{-1, 0, 1, 2, -2, -4};
        printTriplets(findTriplets_hashing(arr));  // [-1, 0, 1][0, 2, -2]
    }

    private static void twoPointersWay() {

        validateEdgeCase_twoPointers();

        int[] arr = new int[] {0, -1, 2, -3, 1};
        printTriplets(findTriplets_twoPointers(arr)); // [0, -1, 1] [2, -3, 1]

        arr = new int[]{1, -2, 1, 0, 5};
        printTriplets(findTriplets_twoPointers(arr)); // [1, -2, 1]

        arr = new int[]{-5, 3, 2, -3, 1};
        printTriplets(findTriplets_twoPointers(arr));  // [-5, 3, 2][2, -3, 1]

        arr = new int[]{-1, 0, 1, 2, -2, -4};
        printTriplets(findTriplets_twoPointers(arr));  // [-1, 0, 1][0, 2, -2]

    }

    private static void printTriplets(List<List<Integer>> triplets) {
        System.out.print("\nTriplets = ");
        for(List<Integer> list : triplets){
            System.out.print(list);
        }
    }

    /*
     * ========= naive/bruteforce approach =========
     *
     *	Approach:
     *	1. 	Run three nested loops with loop counter i, j, k
     *	2. 	The first loops will run from 0 to n-3 and
     *          second loop from i+1 to n-2 and
     *          the third loop from j+1 to n-1.
     *	3. 	Check if the sum of elements at i’th, j’th, k’th is equal to zero or not.
     *
     * Time Complexity: O(n^3), As three nested loops are used.
     * Auxiliary Space: O(1).
     *
     * */
    private static List<List<Integer>> findTriplets_bruteforce(int[] arr) {

        int n = arr.length;
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        List<Integer> curr = Arrays.asList(arr[i], arr[j], arr[k]);
                        Collections.sort(curr);
                        // If triplet doesn't exist in the res, then only insert it.
                        if (!triplets.contains(curr)) {
                            triplets.add(curr);
                        }
                    }
                }
            }
        }
        return triplets;
    }

    private static void validateEdgeCase_bruteforce() {
        List<List<Integer>> output;
        List<int[]> inputs = Arrays.asList(new int[]{}, new int[]{0}, new int[]{0, 0}, new int[]{-1, 1});
        for (int[] input : inputs) {
            output = findTriplets_bruteforce(input);
            if (output.size() > 0) {
                System.out.print("\nEdge cases test got failed....!!!");
            }
        }
        System.out.print("\nEdge cases test validated successfully");
    }

    /*
     * ========= [Better Approach] - Using Hashing - O(n^2 log n) Time and O(n) Space =========
     *	Approach:
     *	1.	Create a hashmap to store a key-value pair.
     *	2.	Run a nested loop with two loops, the outer loop from 0 to n-2 and the inner loop from i+1 to n-1
     *	3.	Check if the sum of ith and jth element multiplied with -1 is present in the hashmap or not
     *	4.	If the element is present in the hashmap, print the triplet else insert the j’th element in the hashmap.
     *
     * Time Complexity: O(n^2).
     * Auxiliary Space: O(n).
     *
     * */
    private static List<List<Integer>> findTriplets_hashing(int[] arr) {

        int n = arr.length;

        // Set to handle duplicates
        Set<List<Integer>> triplets = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int third = -(arr[i] + arr[j]);
                if (set.contains(third)) {
                    List<Integer> curr = Arrays.asList(arr[i], arr[j], third);
                    Collections.sort(curr);
                    triplets.add(curr);
                } else {
                    set.add(arr[j]);
                }
            }
        }
        return new ArrayList<>(triplets);
    }

    private static void validateEdgeCase_hashing() {
        List<List<Integer>> output;
        List<int[]> inputs = Arrays.asList(new int[]{}, new int[]{0}, new int[]{0, 0}, new int[]{-1, 1});
        for (int[] input : inputs) {
            output = findTriplets_hashing(input);
            if (output.size() > 0) {
                System.out.print("\nEdge cases test got failed....!!!");
            }
        }
        System.out.print("\nEdge cases test validated successfully");
    }

    /*
     * ========= [Expected Approach] - sorting then using Two Pointers Technique - O(n^2) Time and O(1) Space  =========
     *
     *	Approach:
     *	1.	Sort the array in ascending order.
     *	2.	Traverse the array from start to end.
     *	3.	For every index i, create two variables left = i + 1 and right = n – 1
     *	4.	Run a loop until left is less than right if (sum = array[i] +  array[left] + array[right]) == 0 we got the triplet increment left, decrement right and continue the loop
     *	5.	If the sum < 0 then increment the value of left
     *		    , by increasing the value of left the sum will increase as array is sorted, so array[left+1] > array [left]
     *	6.	If the sum > 0 then decrement the value of right
     *          , by decreasing the value of right the sum will decrease as array is sorted, so array[right-1] < array [right].
     *
     * Time Complexity: O(n * log n) + n^2 = n^2
     * Auxiliary Space: O(1).
     *
     * */
    private static List<List<Integer>> findTriplets_twoPointers(int[] arr) {

        int n = arr.length;
        List<List<Integer>> triplets = new ArrayList<>();

        // sort array elements
        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) {
            // initialize left and right
            int left = i + 1;
            int right = n - 1;
            int curr = arr[i];
            while (left < right) {
                if (curr + arr[left] + arr[right] == 0) {
                    List<Integer> currTriplet = Arrays.asList(curr, arr[left], arr[right]);
                    Collections.sort(currTriplet);
                    triplets.add(currTriplet);
                    left++;
                    right--;
                }

                // If sum of three elements is less than zero then increment in left
                else if (curr + arr[left] + arr[right] < 0)
                    left++;
                    // if sum is greater than zero then decrement in right side
                else
                    right--;
            }
        }
        return triplets;
    }

    private static void validateEdgeCase_twoPointers() {
        List<List<Integer>> output;
        List<int[]> inputs = Arrays.asList(new int[]{}, new int[]{0}, new int[]{0, 0}, new int[]{-1, 1});
        for (int[] input : inputs) {
            output = findTriplets_twoPointers(input);
            if (output.size() > 0) {
                System.out.print("\nEdge cases test got failed....!!!");
            }
        }
        System.out.print("\nEdge cases test validated successfully");
    }

}