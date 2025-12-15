package com.nishchay.algo.slidingwindow;

/*
 *  ======================= Count of Subarrays with sum equals k in given Binary Array ====================
 *
 *  Given a binary array arr[] and an integer k, the task is to find the count of non-empty subarrays with a sum equal to k.
 *
 * Examples:
 *			Input: arr[] = {1, 0, 1, 1, 0, 1}, k = 2
 *			Output: 6
 *			Explanation: All valid subarrays are: {1, 0, 1}, {0, 1, 1}, {1, 1}, {1, 0, 1}, {0, 1, 1, 0}, {1, 1, 0}.
 *
 *			Input: arr[] = {0, 0, 0, 0, 0}, k = 0
 *			Output: 15
 *			Explanation: All subarrays have a sum equal to 0, and there are a total of 15 subarrays.
 *
 *
 * https://takeuforward.org/data-structure/binary-subarray-with-sum
 * https://www.geeksforgeeks.org/dsa/count-of-subarrays-with-sum-equals-k-in-given-binary-array/
 * https://leetcode.com/problems/binary-subarrays-with-sum/description/
 *
 * */

public class CountSubarraySumToKBinaryArray {

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 1, 0, 1};
        int k = 2;

        System.out.println(numberOfSubarrays(arr, k));
        System.out.println(numSubarraysWithSum(arr, k)); // Output: 4
    }

    /*
     * ================ [Naive Approach] Checking Each Subarray - O(n^2) time and O(1) space  =====================
     *
     *  The idea is to consider each subarray in the array and check if the sum is equal to the target.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *  Function to find count of subarrays with sum equal to k
     */
    private static int numberOfSubarrays(int[] arr, int k) {

        int count = 0, n = arr.length;

        // Check for each subarray
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + arr[j];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    /*
     *  ================ [Expected Approach] Using Sliding Window Approach - O(n) time and O(1) space  =====================
     * XOR of a number with itself is 0 .       i.e. x ^ x = 0
     * And XOR of a number with 0 is number.    i.e. 0 ^ x = x
     *
     *      1-n     : 1, 2, 3, 4, 5
     *      array   : 1, 2,    4, 5     XOR
     *  --------------------------------
     *                 0, 0, 3, 0, 0
     *
     * The given array arr[] has numbers in range [1, n]
     * This means that the result of XOR of first n natural numbers with all the array elements will give the missing number.
     * (XOR of all array elements) XOR (XOR of (1 to n+1) natural nos) = missing no
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */

    // Function to calculate number of subarrays with sum exactly equal to goal
    private static int numSubarraysWithSum(int[] nums, int goal) {
        // Return difference between atMost(goal) and atMost(goal - 1)
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    // Helper method to count subarrays with sum at most k
    private static int atMost(int[] nums, int k) {
        // No valid subarray for negative sum
        if (k < 0) return 0;

        int left = 0;
        int sum = 0;
        int count = 0;

        // Traverse array using right pointer
        for (int right = 0; right < nums.length; right++) {
            // Add current element to sum
            sum += nums[right];

            // Shrink window if sum exceeds k
            while (sum > k) {
                sum -= nums[left];
                left++;
            }

            // Add number of valid subarrays ending at right
            count += (right - left + 1);
        }

        return count;
    }

}
