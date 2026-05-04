package com.nishchay.algo.bsearch.a02rotation;


/*
 *============== Search in sorted rotated array with duplicates. ====================
 *
 * Given a sorted and rotated array (possibly with duplicates), determine if a given key exists in it, returning true if found, otherwise false.
 *
 * Examples:
 *				Input: arr[] = [3, 3, 3, 1, 2, 3], key = 3
 *				Output: true
 *				Explanation: 3 is present in the array.
 *
 *				Input: arr[] = [3, 3, 3, 1, 2, 3], key = 11
 *				Output: false
 *				Explanation: 11 is not present in the given array.
 *
 * https://takeuforward.org/arrays/search-element-in-rotated-sorted-array-ii/
 * https://www.geeksforgeeks.org/dsa/search-an-element-in-a-sorted-and-rotated-array-with-duplicates/
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 * */
public class SearchInRotatedSortedArrayDuplicates {

    public static void main(String[] args) {

        int[] arr = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
        int key = 3;

        boolean ans = searchInRotatedArrayII(arr, key);

        if (ans)
            System.out.println("Target is present in the array.");
        else
            System.out.println("Target is not present.");
    }

    /*
     * =========== [Expected Approach] Using Single Binary Search - O(log n) Time and O(1) Space ==========
     *
     * Why we can't apply previous logic here that has been working with unique data
     * Edge case :
     * arr [] = { 3, 1, 2, 3 ,3, 3, 3}
     *            |        |        |
     *           low      mid       high
     * here we can't determine which one is the sorted half
     *
     *
     * Algoritham:
     *		Start by looking at the middle element of the array.
     *		Check if this middle element is the target if yes,
     *			return its index immediately.
     *
     *		Now figure out which half of the array (left side or right side) is sorted.
     *		If the left part is sorted:
     *			Check if the target number falls within the range of that sorted part.
     *			If it does, discard the right half and continue the search in the left part.
     *			If it doesn’t, discard the left half and search in the right side.
     *		If the right part is sorted:
     *			Do the same check if the target is in that sorted part.
     *			If yes, discard the left side and search in the right.
     *			If not, discard the right and continue with the left.
     *		Repeat this process of eliminating half the array until the target is found or the search space is empty.
     *
     * Time Complexity  - O(log n)
     * Space Complexity - O(1)
     *
     * Function to search key in a rotated sorted array using binary search
     * */
    private static boolean searchInRotatedArrayII(int[] array, int key) {
        int low = 0, high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (array[mid] == key)
                return true;

            // Handle duplicates: cannot determine sorted side
            if (array[low] == array[mid] && array[mid] == array[high]) {
                low++;
                high--;
                continue;
            }

            // Left half is sorted
            if (array[low] <= array[mid]) {
                if (array[low] <= key && key <= array[mid]) {
                    high = mid - 1; // Search left
                } else {
                    low = mid + 1;  // Search right
                }
            }
            // Right half is sorted
            else {
                if (array[mid] <= key && key <= array[high]) {
                    low = mid + 1;  // Search right
                } else {
                    high = mid - 1; // Search left
                }
            }
        }

        return false;
    }

}

