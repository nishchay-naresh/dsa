package com.nishchay.ds.array.a09subarray;

/*
 *  ======================= Maximum consecutive one’s (or zeros) in a binary array ====================
 *
 *  Given a binary array arr[] consisting of only 0s and 1s.
 *  Find the length of the longest contiguous sequence of either 1s (or 0s) in the array.
 *
 * Examples:
 *			Input: arr[] = [0, 1, 0, 1, 1, 1, 1]
 *			Output: 4
 *			Explanation: The maximum number of consecutive 1’s in the array is 4 from index 3-6.
 *
 *			Input: arr[] = [1, 1, 0, 1, 1, 1]
 *			Output: 3
 *			Explanation: The maximum number of consecutive 1’s in the array is 3 from index 3-5.
 *
 *			Input: arr[] = [1, 0, 1, 1, 0, 1]
 *			Output: 2
 *			Explanation: The maximum number of consecutive 1’s in the array is 2 from index 2-3.
 *
 *			Input: arr[] = [0, 0, 0, 0]
 *			Output: 0
 *			Explanation: No consecutive 1’s in the array.
 *
 *
 * https://www.geeksforgeeks.org/dsa/maximum-consecutive-ones-or-zeros-in-a-binary-array/
 * https://leetcode.com/problems/max-consecutive-ones/description/
 * */
class MaxConsecutiveOnesBinaryArray {

    public static void main(String[] args) {

        int[] arr = { 0, 1, 0, 1, 1, 1, 1 };
        System.out.println(maxConsecutiveOnes(arr)); // 4

        arr = new int[]{1, 1, 0, 1, 1, 1};
        System.out.println(maxConsecutiveOnes(arr)); // 3

        arr = new int[]{1, 0, 1, 1, 0, 1};
        System.out.println(maxConsecutiveOnes(arr)); // 2

        arr = new int[]{0, 0, 0, 0};
        System.out.println(maxConsecutiveOnes(arr)); // 0

    }

    /*
     * ================ [Naive/Bruteforce Approach] Linear Search for Missing Number  =====================
     *
     *  iterates through array use two variables to track the one's
     *      count   - current sequence of 1s
     *      maxCount- hold the max of all the values of count
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int maxConsecutiveOnes(int[] arr){

        if (arr.length == 0)
            return 0;

        int maxCount = 0, count = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            }else{
                count = 0;
            }
        }
        return maxCount;
    }



}