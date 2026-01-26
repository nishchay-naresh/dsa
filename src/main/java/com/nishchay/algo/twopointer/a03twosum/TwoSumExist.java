package com.nishchay.algo.twopointer.a03twosum;

import java.util.Arrays;
import java.util.HashSet;

/*
 *	=================================== 2 Sum problem ================================
 *
 *	The 2-Sum problem is a popular algorithmic challenge where the goal is to identify two distinct elements in an array whose sum equals a specific target.
 *	Emphasizes ones understanding on :
 *	    - array manipulation
 *	    - optimizing search operations through hashing
 *
 *	Problem can categories in 2 gatories:
 *	    1. 2Sum on Unsorted Input
 *	    2. 2Sum on Sorted Input
 *
 * https://www.geeksforgeeks.org/dsa/2sum/
 *
 *	=================================== Two Sum - Pair with given Sum ================================
 * Given an array arr[] of n integers and a target value, check if there exists a pair whose sum equals the target.
 *
 * Examples:
 * 				Input: arr[] = [0, -1, 2, -3, 1], target = -2
 * 				Output: true
 * 				Explanation: There is a pair (1, -3) with the sum equal to given target, 1 + (-3) = -2.
 *
 * 				Input: arr[] = [1, -2, 1, 0, 5], target = 0
 * 				Output: false
 * 				Explanation: There is no pair with sum equals to given target.
 *
 *
 * https://www.geeksforgeeks.org/dsa/check-if-pair-with-given-sum-exists-in-array/
 * */
public class TwoSumExist {

    public static void main(String[] args) {
        int[] arr = {0, -1, 2, -3, 1};
        int target = -2;

        System.out.println("twoSum_2loop(arr, target)           - " + twoSum_2loop(arr, target));
        System.out.println("twoSum_binarySearch(arr, target)    - " + twoSum_binarySearch(arr, target));
        System.out.println("twoSum_2pointers(arr, target)       - " + twoSum_2pointers(arr, target));
        System.out.println("twoSum_hashing(arr, target)         - " + twoSum_hashing(arr, target));
    }

    /*
     * ================ [Naive Approach] Generating all Possible Pairs - O(n2) time and O(1) space  =====================
     *
     *  The basic approach is to generate all the possible pairs and check if any of them add up to the target value.
     *  To generate all pairs, we simply run two nested loops.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static boolean twoSum_2loop(int[] arr, int target) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            // For each element arr[i], check every other element arr[j] that comes after it
            for (int j = i + 1; j < n; j++) {
                // Check if the sum of the current pair equals the target
                if (arr[i] + arr[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * ================ [Better Approach 1] Sorting and Binary Search - O(n × log(n)) time and O(1) space  =====================
     *
     *  To check if a pair with a given sum exists in the array, we first sort the array.
     *  Then for each element, we compute the required complement (i.e., target - arr[i])
     *  and perform binary search on the remaining subarray (from index i+1 to end) to find that complement.
     *
     * Step By Step Implementation:
     * 	-	Sort the array in non-decreasing order.
     * 	-	Loop through each element arr[i] from index 0 to n-2.
     * 	-	For each arr[i], calculate complement = target - arr[i].
     * 	-	Perform binary search for complement in the subarray from index i+1 to n-1.
     * 	-	If the complement is found, return true.
     * 	-	If the loop completes without finding any valid pair, return false.
     *
     *  Time Complexity     : n log(n) sorting + n log(n) solution  = O(n × log(n))
     *  Space complexity    : O(1)
     */
    private static boolean twoSum_binarySearch(int[] arr, int target) {

        Arrays.sort(arr);

        for (int i = 0; i <= arr.length - 2; i++) {
            int complement = target - arr[i];

            // Use binary search to find the complement
            if (binarySearch(arr, i + 1, arr.length - 1, complement))
                return true;
        }
        return false;
    }

    // Function to perform binary search
    static boolean binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return true;
            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }

    /*
     * ================ [Better Approach 2] Sorting and Two-Pointer Technique - O(n × log(n)) time and O(1) space  =====================
     *
     *  The idea is to use the two-pointer technique over sorted array
     *  Once the array is sorted then we can use this approach by keeping one pointer at the beginning (left) and another at the end (right) of the array.
     *  Keep moving left and right pointers based on their computed sum.
     *
     * Check the sum of the elements at these two pointers:
     *	-	If the sum equals the target, we’ve found the pair.
     *	-	If the sum is less than the target, move the left pointer.
     *	-	If the sum is greater than the target, move the right pointer.
     *
     *
     *  Time Complexity     : O(n × log(n)) + O(n) = O(n log n)
     *  Space complexity    : O(1)
     */
    private static boolean twoSum_2pointers(int[] arr, int target) {

        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target)
                return true;
            else if (sum < target)
                left++;
            else
                right--;
        }
        return false;
    }

    /*
     * ================ [Expected Approach] Using HashSet - O(n) time and O(n) space  =====================
     *
     * Rather than checking every possible pair, we store each number in a HashSet during iterating over the array's elements.
     * 	 For each number, we calculate its complement (i.e., target - current number) and check
     * 		if this complement exists in hashMap
     * 			found the pair that sums to the target
     * 		else
     * 			we store each number in a hashMap
     *
     *
     * Step By Step Implementations:
     * 		Create an empty Hash Set or Unordered Set
     * 		Iterate through the array and for each number in the array:
     * 		=> Calculate the complement (target - current number).
     * 		=> Check if the complement exists in the set:
     * 				- If it is, then pair found.
     * 				- If it isn’t, add the current number to the set.
     * 		If the loop completes without finding a pair, return that no pair exists.
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static boolean twoSum_hashing(int[] arr, int target) {
        HashSet<Integer> set = new HashSet<>();

        for (int curr : arr) {
            int complement = target - curr;
            if (set.contains(complement)) {
                return true;
            }
            set.add(curr);
        }
        return false;
    }
}