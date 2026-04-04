package com.nishchay.algo.twopointer.a01easy;

import java.util.Arrays;

/*
 *==================== Remove duplicates from sorted array Keep Pairs ========================
 *==================== Keep at most two occurrences of each number in a sorted array. ========================
 * Anything appearing more than twice is removed.
 *
 * Given a sorted array, remove the duplicates from the array in-place such that each element appears at most twice, and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 *
 * Examples:
 *			    Input : array[]  = {2, 2, 2, 2, 2}
 *			    Output : array[] = {2,2}, new size = 2
 *
 *			    Input : array[]  = {1, 2, 2, 3, 4, 4, 4, 5, 5}
 *			    Output : array[] = {1, 2, 2, 3, 4, 4, 5, 5}, new size = 8
 *
 *			    Input : array[]  = {1, 1, 1, 3, 5, 5, 7}
 *			    Output : array[] = {1, 1, 3, 5, 5, 7}, new size = 6
 *
 *			    Input : array[]  = {}
 *			    Output : array[] = {}, new size = 0
 *
 * https://www.callicoder.com/remove-duplicates-from-sorted-array-ii/
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 *
 * */
public class RemoveDuplicatesInSortedArray_KeepPairs {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 2, 2, 2, 3, 4, 4, 4, 8, 8, 8, 7, 7};
        int newSize = removeDuplicates(arr);
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, newSize)));
        System.out.println("------------------------------------------------");
        twoPointersWay();
    }

    private static void twoPointersWay() {

        int[] arr;
        int newSize;

        arr = new int[]{2, 2, 2, 2, 2};
        newSize = keepingPairs_twoPointers(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array = ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();

        arr = new int[]{1, 2, 2, 3, 4, 4, 4, 5, 5};
        newSize = keepingPairs_twoPointers(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array = ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();

        arr = new int[]{1, 1, 1, 3, 5, 5, 7};
        newSize = keepingPairs_twoPointers(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array = ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();

        arr = new int[]{};
        newSize = keepingPairs_twoPointers(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array = ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();


        arr = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        newSize = removeDuplicates(arr);
        System.out.print("Actual Array =" + Arrays.toString(arr) + ", newSize = " + newSize + ", Result array = ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }


    /*
     * ================================= Approach : Two pointer approach  =========================================
     *
     *  Since we are using the same input array for a result as well, so we need to return the updated size of it.
     *  Think of it like - compacting the array.
     *
     *	Key logic: if (i < n - 2 && arr[i] == arr[i + 2])
     *	This checks:Is this element repeated at least 3 times consecutively?
     *	Example:
     *			Index: 0 1 2
     *			Array: 1 1 1
     *	Check:
     *			arr[0] == arr[2] => 1 == 1 → true => There are at least 3 identical elements.
     *			So we skip the first one.
     *
     *
     * Time Complexity: O(n).
     * Auxiliary Space: O(1).
     *
     * */
    private static int keepingPairs_twoPointers(int[] arr) {

        int n = arr.length;

        if (n == 0 || n == 1 || n == 2)
            return n;

        // using the writeIndex to write a refined element in the same array
        int writeIndex = 0;

        for (int i = 0; i < n; i++) {

            // If arr[i] == arr[i+2] then skip the arr[i] because it is repeated more than twice.
            // Skipping the duplicate from the left, instead of right
            if (i < n - 2 && arr[i] == arr[i + 2]) {
                continue;
            }
            arr[writeIndex++] = arr[i];
        }
        return writeIndex;
    }

    /*
     * https://codechunkers.medium.com/solution-to-leetcodes-remove-duplicates-from-sorted-array-ii-62deced592a1
     *
     * skipping the first 2 index, 0, 1 because an element can be almost - 2 unique / 1 pair, (1,2 or 1,1)
     *
     * star checking element for triplet from index 2
     *      checking the current element against of -2 index (which is writeIndex)
     *
     * */
    private static int removeDuplicates(int[] nums) {
        int writeIndex = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[writeIndex - 2]) {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }
        return writeIndex;
    }

}
