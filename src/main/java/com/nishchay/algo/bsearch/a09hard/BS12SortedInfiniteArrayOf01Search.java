package com.nishchay.algo.bsearch.a09hard;

/*
 *============== Find the index of first 1 in an infinite sorted array of 0s and 1s ====================
 *
 *
 * Given an infinite sorted array consisting 0s and 1s. The problem is to find the index of first ‘1’ in that array.
 * As the array is infinite, therefore it is guaranteed that number ‘1’ will be present in the array.
 *
 *	Example 1:
 *		Input : arr[] = {0, 0, 1, 1, 1, 1}
 *		Output : 2
 *
 *	Example 2:
 *		Input : arr[] = {1, 1, 1, 1,, 1, 1}
 *		Output : 0
 *
 *
 * https://www.geeksforgeeks.org/find-index-first-1-infinite-sorted-array-0s-1s/
 *
 * */
public class BS12SortedInfiniteArrayOf01Search {

    public static void main(String[] args) {

        int[] arr;

        arr = new int[]{0, 0, 1, 1, 1, 1};
        System.out.printf("First Occurrence of 1 is at= %d%n", findPos(arr)); // 2

        arr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.printf("First Occurrence of 1 is at= %d%n", findPos(arr));// 9

    }

    /*
     *
     * ================ Binary Search in infinite array of 0, 1 =======================
     *  Inputs :
     *          -   infinite
     *          -   sorted
     *
     *	1. First find the right bound
     *	2. Apply BS in array for left to right
     *
     *    int left = 0
     *    int right = 1
     *    while(0 == arr[right]){
     *        left=right
     *        right=right * 2
     *    }
     *    BS(arr, left, right)
     *
     */

    private static int findPos(int[] arr) {

        int left = 0;
        int right = 1;
        while (0 == arr[right]) {
            left = right;
            //check that 2*h doesn't exceeds array length to prevent ArrayOutOfBoundException
            if (right * 2 < arr.length - 1)
                right = 2 * right;
            else
                right = arr.length - 1;
        }
        return indexOfFirstOne(arr, left, right);

    }

    private static int indexOfFirstOne(int[] arr, int low, int high) {
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid] == 1 && (mid == 0 || arr[mid - 1] == 0))
                break;
            else if (arr[mid] == 1)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return mid;
    }
}
