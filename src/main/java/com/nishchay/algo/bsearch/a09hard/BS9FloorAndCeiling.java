package com.nishchay.algo.bsearch.a09hard;

/*
 *================= Floor & Ceiling in a Sorted Array ====================
 *
 *	floor of x in array =  	largest smaller number
 *							largest element in array smaller than or equal to x
 *
 *	ceil of x in array  =	smallest greater number
 *							smallest element in array greater than or equal to x.
 *
 *============== Floor in a Sorted Array ====================
 * Given a sorted array and a value key, the floor of key is the largest element in array smaller than or equal to key.
 * Write efficient functions to find floor of key.
 *
 *
 * Examples - 1
 *		Input : arr[] = {1, 2, 8, 10, 10, 12, 19}, key = 5
 *		Output : 2
 *		2 is the largest element in
 *		arr[] smaller than 5.
 *
 * Examples - 2
 *		Input : arr[] = {1, 2, 8, 10, 10, 12, 19}, key = 20
 *		Output : 19
 *		19 is the largest element in
 *		arr[] smaller than 20.
 *
 * Examples - 3
 *		Input : arr[] = {1, 2, 8, 10, 10, 12, 19}, key = 0
 *		Output : -1
 *		Since floor doesn't exist,
 *		output is -1.
 *
 *
 *============== Ceiling in a Sorted Array ====================
 *
 * Given a sorted array and a value key, the ceiling of key is the smallest element in array greater than or equal to key.
 * Write efficient functions to find floor and ceiling of key.
 *
 * 	Example 1
 * 		Input : a[] = {1, 3, 9, 15, 15, 18, 21}, key = 0
 * 		Output : 1
 *      1 is the smallest element in the array greater than or equal to 5.
 *
 * 	Example 2
 * 		Input : a[] = {1, 3, 9, 15, 15, 18, 21}, key = 1
 * 		Output : 1
 *      1 is the smallest element in the array greater than or equal to 1.
 *
 * 	Example 3:
 * 		Input : a[] = {1, 3, 9, 15, 15, 18, 21}, key = 5
 * 		Output : 9
 *      9 is the smallest element in the array greater than or equal to 5.
 *
 * 	Example 4:
 * 		Input : a[] = {1, 3, 9, 15, 15, 18, 21}, key = 25
 * 		Output : -1
 *      Ceiling doesn't exist
 *
 *
 * https://www.geeksforgeeks.org/floor-in-a-sorted-array/
 * https://www.geeksforgeeks.org/ceiling-in-a-sorted-array/
 * https://www.techiedelight.com/find-floor-ceil-number-sorted-array/
 * */
public class BS9FloorAndCeiling {

    public static void main(String[] args) {

        floorEx();
        System.out.printf("----------------------------------------------%n");
        ceilingEx();

    }


    private static void floorEx() {

        int key;
        int[] arr;

        arr = new int[]{1, 2, 8, 10, 10, 12, 19};
        key = 5;
        System.out.printf("floor of %d = %d%n", key, getFloor(arr, key)); // 2

        key = 20;
        System.out.printf("floor of %d = %d%n", key, getFloor(arr, key)); // 19

        key = 0;
        System.out.printf("floor of %d = %d%n", key, getFloor(arr, key)); // -1

        arr = new int[]{1, 3, 9, 15, 15, 18, 21};
        key = 5;
        System.out.printf("floor of %d = %d%n", key, getFloor(arr, key)); // 3

        key = 25;
        System.out.printf("floor of %d = %d%n", key, getFloor(arr, key)); // 21

        key = 0;
        System.out.printf("floor of %d = %d%n", key, getFloor(arr, key)); // -1
    }

    private static void ceilingEx() {

        int key;
        int[] arr;

        arr = new int[]{1, 3, 9, 15, 15, 18, 21};

        key = 0;
        System.out.printf("ceil of %d = %d%n", key, getCeil(arr, key)); // 1

        key = 1;
        System.out.printf("ceil of %d = %d%n", key, getCeil(arr, key)); // 1

        key = 5;
        System.out.printf("ceil of %d = %d%n", key, getCeil(arr, key)); // 9

        key = 25;
        System.out.printf("ceil of %d = %d%n", key, getCeil(arr, key)); // -1

    }

    /*
     *	if (arr[mid] == key) {
     *		return arr[mid];
     *	// floor means smaller
     *	} else if (arr[mid] < key) {
     *		floor = arr[mid];
     *		left = mid + 1;
     *	} else {
     *		right = mid - 1;
     *	}
     * */
    private static int getFloor(int[] arr, int key) {

        int left = 0, right = arr.length - 1;
        int mid;

        int floor = -1;

        while (left <= right) {

            mid = (left + right) / 2;

            if (arr[mid] == key) {
                return arr[mid];
                // if arr[mid] < key, the floor exists in the right sub-array
                // arr[mid] could be a possible floor, so update floor to the middle element
                // and reduce our search space to the right sub-array
            } else if (arr[mid] < key) {
                floor = arr[mid];
                left = mid + 1;
            } else { // arr[mid] > key, the floor exists in the left sub-array
                right = mid - 1;
            }
        }

        return floor;
    }

    /*
     *	if (arr[mid] == key) {
     *		return arr[mid];
     *	}
     *	// ceil means greater
     *	else if (arr[mid] > key) {
     *		ceil = arr[mid];
     *		right = mid - 1;
     *	}
     *	else {
     *		left = mid + 1;
     *	}
     * */
    private static int getCeil(int[] arr, int key) {

        int left = 0, right = arr.length - 1;
        int mid;
        int ceil = -1;

        while (left <= right) {

            mid = (left + right) / 2;

            if (arr[mid] == key) {
                return arr[mid];
            }

            // if arr[mid] > key, the ceil exists in the left sub-array arr[left…mid]
            //  arr[mid] could be a possible ceil, so update ceil to the middle element
            // and reduce our search space to the left subarray arr[left…mid-1]
            else if (arr[mid] > key) {
                ceil = arr[mid];
                right = mid - 1;
            }

            // arr[mid] < key, the ceil exists in the right sub-array
            else {
                left = mid + 1;
            }
        }

        return ceil;
    }

}