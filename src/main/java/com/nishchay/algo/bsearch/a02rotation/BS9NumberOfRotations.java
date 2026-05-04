package com.nishchay.algo.bsearch.a02rotation;

/*
 *============== Find the Rotation Count in Rotated Sorted array ====================
 *
 * Consider an array of distinct numbers sorted in increasing order.
 * The array has been rotated (clockwise) k number of times. Given such an array, find the value of k.
 *
 * Examples - 1
 *		Input : arr[] = {15, 18, 2, 3, 6, 12}
 *		Output: 2
 *		Explanation : Initial array must be {2, 3,6, 12, 15, 18}. We get the given array after rotating the initial array twice.
 *
 * Examples - 2
 *		Input : arr[] = {7, 9, 11, 12, 5}
 *		Output: 4
 *
 * Examples - 3
 *		Input: arr[] = {7, 9, 11, 12, 15};
 *		Output: 0
 *
 * https://www.youtube.com/watch?v=4qjprDkJrjY&ab_channel=mycodeschool
 * https://www.techiedelight.com/find-number-rotations-circularly-sorted-array/
 * https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/
 * */
public class BS9NumberOfRotations {


    public static void main(String[] args) {

        int[] arr = {15, 18, 2, 3, 6, 12};
        System.out.printf("Rotation Count = %d%n", findRotations(arr)); // 2

        arr = new int[]{7, 9, 11, 12, 5};
        System.out.printf("Rotation Count = %d%n", findRotations(arr)); // 4

        arr = new int[]{7, 9, 11, 12, 15};
        System.out.printf("Rotation Count = %d%n", findRotations(arr)); // 0

        arr = new int[]{4, 5, 6, 7, 0, 1, 2, 3};
        System.out.printf("Rotation Count = %d%n", findRotations(arr)); // 4

    }


    /* ====> # of times array is been rotated = index of the smallest element
     *
     *	Input: a = [4, 6, 8, 10, 0, 1, 2]
     *	Output: 4
     *	Explanation: The original array would be [0, 1, 2, 4, 6, 8, 10] and it was rotated 4 times.
     *
     *	# of times array is been rotated = index of the smallest element
     *	-------
     *	We have solve this problem - minimum in rotated sorted array, BS7MinimumInRotatedSortedArray#findMin()
     *  only difference we need to find the index, rather than element
     *  so we can use the same code only modification need to do rather than element we need to return the index
     *	===============================================================
     *
     *	 2,3,5,8,11,12
     *	 ^			 |	// clock wise - 1 rotation
     *	 |----------<|
     *
     *	 12,2,3,5,8,11 => circular sorted array
     *
     *	 11,12,2,3,5,8 // clock wise - 2 rotation
     *
     *	 How many times the array has been rotated ?
     *	 # of times array is been rotated = index of the smallest element
     *
     *
     * Time Complexity  - O(log n)
     * Space Complexity - O(1)
     * Function to find rotation count using binary search
     * */
    private static int findRotations(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[high]) {
                // Right half is not sorted - Minimum lies in right half
                low = mid + 1;
            } else {
                // Right half is sorted - Minimum lies in left half (including mid)
                high = mid;
            }
        }

        // When low == high, we found the smallest element
        return low;
    }
}
