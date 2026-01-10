package com.nishchay.algo.twopointer.a05medium;

import java.util.Arrays;

/*
 * ======================= Sort array after converting elements to their squares ====================
 *
 * Given an array of both positive and negative integers 'arr[]' which are sorted.
 * The task is to sort the square of the numbers of the Array.
 *
 * A negative number squared is always a positive number
 *      (-3) * (-3) which equals 9, not -9
 *      (-4) * (-4) = 16
 *
 * Examples:
 * 				Input: nums = [-4,-1,0,3,10]
 * 				Output: [0,1,9,16,100]
 * 				Explanation: After squaring, the array becomes [16,1,0,9,100]. After sorting, it becomes [0,1,9,16,100].
 *
 * 				Input: nums = [-7,-3,2,3,11]
 * 				Output: [4,9,9,49,121]
 * 				Explanation: After squaring, the array becomes [49,9,4,9,121]. After sorting, it becomes [4,9,9,49,121].
 *
 *				Input  : arr[] =  {-6, -3, -1, 2, 4, 5}
 *				Output : 1, 4, 9, 16, 25, 36
 *
 *				Input  : arr[] = {-5, -4, -2, 0, 1}
 *				Output : 0, 1, 4, 16, 25
 *
 * https://www.geeksforgeeks.org/dsa/sort-array-converting-elements-squares/
 * https://neetcode.io/solutions/squares-of-a-sorted-array
 * https://www.callicoder.com/squares-of-a-sorted-array/
 * https://leetcode.com/problems/squares-of-a-sorted-array/description/
 * */
public class SquaresOfASortedArray {

    public static void main(String[] args) {
        bruteforceApproachEx();
        System.out.println("..........................................");
        twoPointersApproachEx();
    }

    private static void bruteforceApproachEx() {
        int[] arr = new int[]{-6, -3, -1, 2, 4, 5};
        System.out.println("Before sort - " + Arrays.toString(arr));

        int[] result = sortSquares_sorting(arr);
        System.out.println("After Sort - " + Arrays.toString(result));  // [1, 4, 9, 16, 25, 36]

        arr = new int[]{-4, -1, 0, 3, 10};
        System.out.println("Before sort - " + Arrays.toString(arr));
        result = sortSquares_sorting(arr);
        System.out.println("After Sort - " + Arrays.toString(result));  // [0,1,9,16,100]

        arr = new int[]{-7, -3, 2, 3, 11};
        System.out.println("Before sort - " + Arrays.toString(arr));
        result = sortSquares_sorting(arr);
        System.out.println("After Sort - " + Arrays.toString(result));  // [4,9,9,49,121]
    }

    private static void twoPointersApproachEx() {
        int[] arr = new int[]{-6, -3, -1, 2, 4, 5};
        System.out.println("Before sort - " + Arrays.toString(arr));

        int[] result = sortSquares_sorting(arr);
        System.out.println("After Sort - " + Arrays.toString(result));  // [1, 4, 9, 16, 25, 36]

        arr = new int[]{-4, -1, 0, 3, 10};
        System.out.println("Before sort - " + Arrays.toString(arr));
        result = sortSquares_2pointers(arr);
        System.out.println("After Sort - " + Arrays.toString(result));  // [0,1,9,16,100]

        arr = new int[]{-7, -3, 2, 3, 11};
        System.out.println("Before sort - " + Arrays.toString(arr));
        result = sortSquares_2pointers(arr);
        System.out.println("After Sort - " + Arrays.toString(result));  // [4,9,9,49,121]
    }


    /*
     * ================ [Naive/Bruteforce Approach] Using Sorting  =====================
     *
     *  Simple solution is to first convert each array element into its square and then apply any "O(nlogn)" sorting algorithm to sort the array elements.
     *
     *  Time Complexity     : O(n log n)
     *  Space complexity    : O(1) assuming we are using the same input array for result as well
     */
    private static int[] sortSquares_sorting(int[] arr) {

        int n = arr.length;

        // convert each array element into its square
        for (int i = 0; i < n; i++)
            arr[i] = arr[i] * arr[i];

        // Sort the array using "inbuilt sort function" in Arrays class.
        Arrays.sort(arr);

        return arr;
    }

    /*
     * ================ [Optimal Approach] 2 pointer approach  =====================
     *
     * The optimal solution uses two pointers to build the result in O(n) time by comparing absolute values from both ends:
     *
     * 	1.	Initialize two pointers: left = 0 and right = n-1
     * 	2.	Initialize an empty result array to store the squared values. Intir;izeits writes index to n-1, bcus we are processing the bigger element first
     * 	3.	While left <= right,
     * 			compare the absolute values of nums[left] and nums[right].
     * 				If abs(nums[left]) is greater than abs(nums[right]), append nums[left] * nums[left] to result and increment left.
     * 			Otherwise,
     * 				append nums[right] * nums[right] to result and decrement right.
     * 	4.	Return result as the sorted array of squares.
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int[] sortSquares_2pointers(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int writerIndex = n - 1;

        int left = 0, right = n - 1;


        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[writerIndex--] = nums[left] * nums[left];
                left++;
            } else {
                result[writerIndex--] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }

}
