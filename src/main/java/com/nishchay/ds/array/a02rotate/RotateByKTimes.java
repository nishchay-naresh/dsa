package com.nishchay.ds.array.a02rotate;

import java.util.Arrays;

/*
 * ====================== Rotate an array by D places ==========================
 *
 * Given an array of integer arr[] of size n, the task is to rotate the array elements to the left by d positions.
 *
 * Ex = arr[] = {1, 2, 3, 4, 5, 6}, d = 6 (array size), left.
 *              It will bring back the array to the same position
 *              Output: {1, 2, 3, 4, 5, 6}
 *              multiple of size time rotation will always give you the same array
 *              so for a bigger number than array size = always do a % first, bring it down to < size
 *
 * Examples:
 * 			Input: arr[] = {1, 2, 3, 4, 5, 6}, d = 2, left
 * 			Output: {3, 4, 5, 6, 1, 2}
 * 			Explanation: After first left rotation, arr[] becomes {2, 3, 4, 5, 6, 1} and after the second rotation, arr[] becomes {3, 4, 5, 6, 1, 2}
 *
 * 			Input: arr[] = {1, 2, 3}, d = 4, left
 * 			Output: {2, 3, 1}
 * 			Explanation: 	The array is rotated as follows:
 * 							After first left rotation, arr[] = {2, 3, 1}
 * 							After second left rotation, arr[] = {3, 1, 2}
 * 							After third left rotation, arr[] = {1, 2, 3}
 * 							After fourth left rotation, arr[] = {2, 3, 1}
 *
 * Given an array, rotate the array to the right by d place
 * arr: [1, 2, 3, 4, 5, 6, 7, 8], d=3, right
 * ans: [6, 7, 8, 1, 2, 3, 4, 5]
 *
 * https://www.geeksforgeeks.org/dsa/array-rotation/
 * https://leetcode.com/problems/rotate-array/description/
 * */
public class RotateByKTimes {

    public static void main(String[] args) {

        int[] arr1 = {10, 20, 30, 40, 50};
        rotateLeftKTimes(arr1, 5); // shd be same as source

        arr1 = new int[]{1, 2, 3, 4, 5, 6};
        rotateLeftKTimes(arr1, 2); //[3, 4, 5, 6, 1, 2]

        arr1 = new int[]{1, 2, 3};
        rotateLeftKTimes(arr1, 4); //[2, 3, 1]

        System.out.println("---------------------------------------------");
        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int d = 3;
        rotateRightKTimes(arr2, d);  //[6, 7, 8, 1, 2, 3, 4, 5]

        arr2 = new int[]{3, 8, 9, 7, 6};
        rotateRightKTimes(arr2, d); // [9, 7, 6, 3, 8]

        arr2 = new int[]{3, 8, 9, 7, 6};
        rotateRightKTimes1(arr2, d); // [9, 7, 6, 3, 8]
    }

    private static void rotateLeftKTimes(int[] arr, int k) {
        k = k % arr.length;
        if (k == 0) {
            return;
        }
        System.out.println("Original array  = " + Arrays.toString(arr));
        // Repeat the rotation k times
        for (int i = 0; i < k; i++) {
            ArrayRotate.leftRotate(arr);
        }
        System.out.println(k + ", times left rotation array = " + Arrays.toString(arr));
    }

    /*
     * arr : [1, 2, 3, 4, 5, 6, 7, 8], k=3, right
     * ans : [6, 7, 8, 1, 2, 3, 4, 5]
     *
     *  arr = [3, 8, 9, 7, 6], k = 3, right
     *  ans = [9, 7, 6, 3, 8].
     *
     *  Time Complexity     : O(nk)
     *  Space complexity    : O(1)
     *
     * Getting failed with - Time Limit Exceeded error
     * */
    private static void rotateRightKTimes(int[] arr, int k) {

        k = k % arr.length;
        if (k == 0) {
            return;
        }
        System.out.println("Original array  = " + Arrays.toString(arr));
        // Repeat the rotation k times
        for (int i = 1; i <= k; i++) {
            ArrayRotate.rightRotate(arr); // O(n)
        }
        System.out.println(k + ", times right rotation array = " + Arrays.toString(arr));
    }

    /*
     * 	Steps:
     * 		1.	Reverse whole array
     * 		2.	Reverse first k elements
     * 		3.	Reverse remaining elements
    * */
    private static void rotateRightKTimes1(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        if (k == 0) return;

        // Step 1: Reverse whole array
        reverse(nums, 0, n - 1);

        // Step 2: Reverse first k elements
        reverse(nums, 0, k - 1);

        // Step 3: Reverse remaining elements
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
