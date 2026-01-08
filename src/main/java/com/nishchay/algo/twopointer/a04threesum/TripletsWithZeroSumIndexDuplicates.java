package com.nishchay.algo.twopointer.a04threesum;

import java.util.*;

/*
 * =================================== 3 Sum - Find All Triplets with Zero Sum ================================
 *
 * Given an array arr[], the task is to find all possible indices {i, j, k} of triplet {arr[i], arr[j], arr[k]} such that their sum is equal to zero
 * and all indices in a triplet should be distinct (i != j, j != k, k != i), means we can only consider an element only one time
 * We need to return indices of a triplet in sorted order, i.e., i < j < k.
 * Need to return all unique triplets
 *
 * Examples:
 *				Input: arr[] = {0, -1, 2, -3, 1}
 *				Output: {{0, 1, 4}, {2, 3, 4}}
 *				Explanation:  Two triplets with sum 0 are:
 *				arr[0] + arr[1] + arr[4] = 0 + (-1) + 1 = 0
 *				arr[2] + arr[3] + arr[4] = 2 + (-3) + 1 = 0
 *
 *				Input: arr[] = {1, -2, 1, 0, 5}
 *				Output: {{0, 1, 2}}
 *				Explanation: Only triplet which satisfies the condition is arr[0] + arr[1] + arr[2] = 1 + (-2) + 1 = 0
 *
 *				Input: arr[] = {2, 3, 1, 0, 5}
 *				Output: {{}}
 *				Explanation: There is no triplet with sum 0
 *
 * https://www.geeksforgeeks.org/dsa/find-triplets-array-whose-sum-equal-zero/
 *
 *============== Find all triplets with zero sum - With Duplicate ====================
 *
 * Given an array of distinct elements. The task is to find triplets in the array whose sum is zero.
 *
 * Examples - 1
 *			Input : arr[] = {-2, 0, 1, 1, 2}
 *			Output : [-2, 0, 2], [-2, 1, 1]
 *
 * Examples - 2
 *			Input : arr[] = {1, -1, 2, 0, -2, 4, -2, -2, 4}
 *			Output : [1, -1, 0], [4, -2, -2], [2, -2, 0]
 *
 * Examples - 3
 *			Input: a[] = {-1, 0, 1, 2, -1, -4}
 *			Output: [-1, 0, 1],[-1, -1, 2]
 *
 * Examples - 4
 *			Input: a[] = {-1, -1, 2, -1, -1, 2, -1, -1, 2}
 *			Output: [-1, -1, 2]
 *
 * https://www.callicoder.com/find-triplets-with-zero-sum/
 * https://afteracademy.com/blog/triplet-with-zero-sum
 *
 * */
public class TripletsWithZeroSumIndexDuplicates {

    public static void main(String[] args) {
        int[] arr = new int[]{0, -1, 2, -3, 1};
        printTriplets(threeSum_3loop(arr));
        printTriplets(threeSum_hashing(arr));

        twoPointersWay();
    }

    private static void twoPointersWay() {

        int[] arr = new int[]{-2, 0, 1, 1, 2};
        printTriplets(findTriplets_duplicates_twoPointers(arr)); // [-2, 0, 2], [-2, 1, 1]

        arr = new int[]{1, -1, 2, 0, -2, 4, -2, -2, 4};
        printTriplets(findTriplets_duplicates_twoPointers(arr)); // [1, -1, 0], [4, -2, -2], [2, -2, 0]

        arr = new int[]{-1, 0, 1, 2, -1, -4};
        printTriplets(findTriplets_duplicates_twoPointers(arr)); // [-1, 0, 1],[-1, -1, 2]

        arr = new int[]{-1, -1, 2, -1, -1, 2, -1, -1, 2};
        printTriplets(findTriplets_duplicates_twoPointers(arr)); // 	[-1, -1, 2]

    }

    private static void printTriplets(List<int[]> res) {
        System.out.print("\nTriplets = ");
        for (int[] e : res) {
            System.out.print(Arrays.toString(e));
        }
    }

    /*
     * ================ [Naive Approach] Generating all possible triplets - O(n3) time and O(1) space  =====================
     *
     *  The basic approach is to generate all the possible triplets and check if any of them add up to the target value.
     *  To generate all triplets, we simply run three nested loops.
     *
     *	1. Run 3 nested loops to maintain i, j, k index in array
     *	2. If at any point, the sum of values at these three indices is equal to the 0
     *		 Print its indices
     *
     *  Time Complexity     : O(n^3), As three nested loops are used.
     *  Space complexity    : O(1)
     */
    private static List<int[]> threeSum_3loop(int[] arr) {
        List<int[]> triplets = new ArrayList<>();
        int n = arr.length;

        // Generating all triplets
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    // If the sum of triplet equals to zero then add it's indexes to the result
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        triplets.add(new int[]{i, j, k});
                    }
                }
            }
        }
        return triplets;
    }

    /*
     * ================ [Expected Approach] Using Hash Map - O(n^2) Time and O(n) Space  =====================
     *
     *	Approach:
     *	1.	Create a hashmap to store <element-its index> pair.
     *	2.	Run a nested loop with two loops, the outer loop from 0 to n-2 and the inner loop from i+1 to n-1
     *	3.	Check if the sum of ith and jth element multiplied with -1 is present in the hashmap or not
     *	4.	If the element is present in the hashmap, print the triplet else insert the j’th element in the hashmap.
     *
     * Time Complexity: O(n^2).
     * Auxiliary Space: O(n).
     *
     */
    private static List<int[]> threeSum_hashing(int[] arr) {
        int n = arr.length;
        List<int[]> triplets = new ArrayList<>();

        // Map to store indices for each value, maintaining List<Indexes> for the case of duplicates
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {

                int third = -(arr[j] + arr[k]);
                if (map.containsKey(third)) {
                    // Append the i, j, k
                    for (int indexes : map.get(third)) {
                        triplets.add(new int[]{indexes, j, k});
                    }
                }
            }
            // After j'th index is traversed, will put it into map, with its index
            map.putIfAbsent(arr[j], new ArrayList<>());
            map.get(arr[j]).add(j);
        }
        return triplets;
    }

    /*
     * ========= sorting then two pointers approach =========
     *
     *	Approach:
     *	1.	Create arr hashmap to store arr key-value pair.
     *	2.	Run arr nested loop with two loops, the outer loop from 0 to n-2 and the inner loop from i+1 to n-1
     *	3.	Check if the sum of ith and jth element multiplied with -1 is present in the hashmap or not
     *	4.	If the element is present in the hashmap, print the triplet else insert the j’th element in the hashmap.
     *
     * Time Complexity: n X log n + n ^ 2 = O(n^2).
     * Auxiliary Space: O(1).
     *
     */
    private static List<int[]> findTriplets_duplicates_twoPointers(int[] arr) {

        List<int[]> triplets = new ArrayList<>();

        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            // Skip duplicates
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            // Fix one number
            int curr = arr[i];

            // Use Two-sum approach to get the other two numbers
            int left = i + 1, right = arr.length - 1;
            while (left < right) {
                int sum = curr + arr[left] + arr[right];
                if (sum == 0) {
                    triplets.add(new int[]{curr, arr[left], arr[right]});
                    left++;
                    right--;

                    // Skip duplicates
                    while (left < right && arr[left] == arr[left - 1]) {
                        left++;
                    }
                    while (left < right && arr[right] == arr[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }

}
