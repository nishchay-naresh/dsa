package com.nishchay.algo.bsearch.a09hard;

/*
 *============== Minimum difference element in a sorted array ====================
 *
 *
 *  Given an array of integers sorted in ascending order, and a target value.
 *  find the element in the array that has minimum difference with the target value(consider absolute value).
 *
 *	Example 1:
 *		Input: a[] = [2, 5, 10, 12, 15], target = 6
 *		Output: 5
 *		Explanation: The difference between the target value '6' and '5' is the minimum.
 *
 *	Example 2:
 *		Input: a[] = [2, 5, 10, 12, 15], target = 5
 *		Output: 5
 *
 *	Example 3:
 *		Input: a[] = [2, 5, 10, 12, 15], target = 8
 *		Output: 10
 *
 *	Example 4:
 *		Input: a[] = [2, 5, 10, 12, 15], target = 11
 *		Output: 10
 *
 *	Example 5:
 *		Input: a[] = [2, 5, 10, 12, 15], target = 20
 *		Output: 15
 *
 *
 * https://www.callicoder.com/minimum-difference-element-in-sorted-array/
 * */
public class BS13MinDiffElement {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 5, 10, 12, 15};
        int target;


        target = 6;
        System.out.printf("MinimumDifferenceElementWith(%d) = %d%n", target, getMinDifference(arr, target)); // 5

        target = 5;
        System.out.printf("MinimumDifferenceElementWith(%d) = %d%n", target, getMinDifference(arr, target)); // 5

        target = 8;
        System.out.printf("MinimumDifferenceElementWith(%d) = %d%n", target, getMinDifference(arr, target)); // 10

        target = 11;
        System.out.printf("MinimumDifferenceElementWith(%d) = %d%n", target, getMinDifference(arr, target)); // 10

        target = 20;
        System.out.printf("MinimumDifferenceElementWith(%d) = %d%n", target, getMinDifference(arr, target)); // 15

    }

    /*
     *  Hint - utilise the BS terminating condition result
     *
     *	1. if target itself there in array
     *       then return arr[mid]
     *	2. if target is not there in array
     *       find target's neighbours in array
     *       then return arr[left] - target <  target - arr[right] ? arr[left] : arr[right]
     *
     */
    private static int getMinDifference(int[] arr, int target) {
        int n = arr.length;

        if (target < arr[0])
            return arr[0];
        if (target > arr[n - 1])
            return arr[n - 1];

        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (target == arr[mid]) {
                return arr[mid];
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        /*
           At the end of the while loop,
           a[left] is the ceiling of target (Smallest number greater than target), and
           a[right] is the floor of target (Largest number smaller than target).

           Return the element whose difference with target is smaller
         */
        if ((arr[left] - target) < (target - arr[right]))
            return arr[left];
        return arr[right];

    }

}
