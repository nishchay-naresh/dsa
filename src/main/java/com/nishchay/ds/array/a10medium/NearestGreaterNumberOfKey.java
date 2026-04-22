package com.nishchay.ds.array.a10medium;


/*
 * =========================== Nearest greater number of a key =================================
 *
 * Find a nearest greater number of a key in an unordered array of ints
 * The smallest number in the array that is greater than
 *
 * Example 1:
 *              Input: arr = [7, 2, 10, 5, 8], key = 6
 *              Output: 7
 * Example 2:
 *              Input: arr = [10, 5, 11, 6, 20, 12], key = 10
 *              Output: 11
 *
 */
public class NearestGreaterNumberOfKey {

    public static void main(String[] args) {

        int[] arr = new int[]{7, 2, 10, 5, 8};
        int key = 6;
        System.out.println(nearestGreater(arr, key)); // Output: 7

        arr = new int[]{10, 5, 11, 6, 20, 12};
        key = 10;
        System.out.println(nearestGreater(arr, key)); // Output: 11

        key = 40;
        System.out.println(nearestGreater(arr, key)); // Output: -1
    }

    /*
     * ================ [Expected Approach] Leaner scan  =====================
     * Just scan once and track the best candidate:
     * Logic:
     *	Initialize result = +∞ (or Integer.MAX_VALUE)
     *	Loop through array:
     *		If num > key AND num < result → update result
     *	If result unchanged → no such element
     *
     *  Time Complexity : O(n)
     *  Space Complexity: O(1)
     *
     * */
    public static int nearestGreater(int[] arr, int key) {
        int result = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num > key && num < result) {
                result = num;
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
