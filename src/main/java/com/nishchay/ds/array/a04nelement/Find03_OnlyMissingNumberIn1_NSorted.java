package com.nishchay.ds.array.a04nelement;

import java.util.Arrays;

/*
 *	==================== Missing(single number) in a Sorted Array of Natural Numbers =======================
 *
 * Given a sorted array arr[] of n-1 integers, these integers are in the range of 1 to n.
 * There are no duplicates in the array. One of the integers is missing in the array. Find the missing integer.
 *
 * Examples:
 *				Input : arr[] = [1, 2, 3, 4, 6, 7, 8]
 *				Output : 5
 *				Explanation: The missing integer in the above array is 5
 *
 *				Input : arr[] = [1, 2, 3, 4, 5, 6, 8, 9]
 *				Output : 7
 *				Explanation: The missing integer in the above array is 7
 *
 * https://www.geeksforgeeks.org/find-the-missing-number-in-a-sorted-array/
 * https://leetcode.com/problems/missing-number/description/
 * https://www.youtube.com/watch?v=fz40YQ8Fg_I
 * */
class Find03_OnlyMissingNumberIn1_NSorted {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(arr) + ", has missing no as - " + getMissingNoLinearSearch(arr)); // 2
        System.out.println(Arrays.toString(arr) + ", has missing no as - " + getMissingNumber(arr));
        System.out.println(Arrays.toString(arr) + ", has missing no as - " + getMissingNoBinarySearch(arr)); // 2

        arr = new int[]{1, 2, 3, 4, 5, 6, 8, 9};
        System.out.println(Arrays.toString(arr) + ", has missing no as - " + getMissingNoLinearSearch(arr)); // 7
        System.out.println(Arrays.toString(arr) + ", has missing no as - " + getMissingNumber(arr));
        System.out.println(Arrays.toString(arr) + ", has missing no as - " + getMissingNoBinarySearch(arr)); // 7
    }

    /*
     * If you analyze the problem statement, you will get two things:
     *   -   only one number is missing
     *   -   data is sorted in array
     *
     * just follow the first stmt : since array starting index is 0, starting Number is 1, so arr[i] == i+1
     * So our problem reduces to search in an array to find the first cell, whose value is not the same as its index + 1
     *      => get the first instance of arr[i] != i+1
     * The missing number is one more than the index shift point (arr[i] != i+1)
     *
     * this can be solved using sequential search - O(n)
     * then we can improve the solution by applying the binary search - O(log n)
     *
     * */

    /*
     * ================ [Better approach 1] - Using Leaner Search to find index shift point - O(n) Time and O(1) Space =====================
     * Method 1 - Leaner Search
     * Logic : No == (index + 1) ?
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */
    private static int getMissingNoLinearSearch(int[] arr) {

        int n = arr.length;

        // staring from the left side, so will break the loop for the first instance of arr[i] != i+1
        for (int i = 0; i < n-1; i++) {
            // if no != i + 1
            if (arr[i] != i + 1)
                return i + 1;
        }
        return n;
    }

    /*
     *
     * ================ [Expected Approach] - Using Binary Search find index shift point - O(log(n)) Time and O(1) Space =====================
     * Method 2 - Binary Search
     *
     * condition to get the - first instance of arr[i] != i+1
     *   if (arr[mid] != mid+1 && arr[mid - 1] == mid)
     *
     * partition breaking / left & right logic :
     *
     *			if (arr[mid] != mid+1){
     *                  // index sequencing is not correct -  go left
     *                right = mid - 1;
     *            } else {
     *                  // index sequencing is correct -  go right
     *                left = mid + 1;
     *            }
     *
     * Time Complexity  : O(log n)
     * Space Complexity : O(1)
     * */
    private static int getMissingNoBinarySearch(int[] arr) {

        int left = 0, right = arr.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;
            // got the first instance of arr[i] != i+1
            if (arr[mid] != mid + 1 && arr[mid - 1] == mid)
                return mid + 1;
            // index sequencing is not correct -  go left
            if (arr[mid] != mid + 1)
                right = mid - 1;
            else // index sequencing is correct -  go right
                left = mid + 1;
        }

        // Will reach here if no missing element found.
        return -1;
    }

    /*
     * ================ [Better approach 2] - Using Formula for Sum of n terms - O(n) Time and O(1) Space =====================
     * As we know - sum of first n natural numbers is = (n * (n + 1)) / 2, we will use this
     *
     *  n = arr.length + 1;
     * Subtract and return the total sum of n with the sum of all elements in the array
     * Logic:
     *          sumOfN = (n * (n + 1)) / 2;
     *          ArraySum = sum of all elements in given array
     *          missing number = ArraySum - sumOfN
     *
     * */
    private static int getMissingNumber(int[] arr) {

        // Calculate the total sum
        int n = arr.length + 1;
        int totalSum = n * (n + 1) / 2;

        // Calculate sum of all elements in the given array
        int arraySum = 0;
        for (int num : arr) {
            arraySum += num;
        }

        // Subtract and return the total sum with the sum of all elements in the array
        int missingNumber = totalSum - arraySum;

        return missingNumber;
    }


}