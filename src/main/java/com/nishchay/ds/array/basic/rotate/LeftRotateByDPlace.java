package com.nishchay.ds.array.basic.rotate;

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
 * */
public class LeftRotateByDPlace {

    public static void main(String[] args) {

        int[] arr1 = {10, 20, 30, 40, 50};
        rotateLeftDTimes(arr1,5); // shd be same as source

        arr1 =  new int[]{1, 2, 3, 4, 5, 6};
        rotateLeftDTimes(arr1,2); //[3, 4, 5, 6, 1, 2]

        arr1 =  new int[]{1, 2, 3};
        rotateLeftDTimes(arr1,4); //[2, 3, 1]

        System.out.println("---------------------------------------------");
        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int d = 3;
        rotateRightDTimes(arr2, d);  //[6, 7, 8, 1, 2, 3, 4, 5]

        arr2 = new int[]{3, 8, 9, 7, 6};
        rotateRightDTimes(arr2, d); // [9, 7, 6, 3, 8]
    }


    private static void rotateLeftDTimes(int[] arr, int d) {
        System.out.printf("       Original array  = %s%n", Arrays.toString(arr));
        // Repeat the rotation d times
        for (int i = 0; i < d; i++) {
            ArrayRotate.leftRotate(arr);
        }
        System.out.printf("Array %d right rotation = %s%n", d, Arrays.toString(arr));
    }


    /*
     * arr : [1, 2, 3, 4, 5, 6, 7, 8], d=3, right
     * ans : [6, 7, 8, 1, 2, 3, 4, 5]
     *
     *  arr = [3, 8, 9, 7, 6], d = 3, right
     *  ans = [9, 7, 6, 3, 8].
     * */
    private static void rotateRightDTimes(int[] arr, int d) {
        System.out.printf("       Original array  = %s%n", Arrays.toString(arr));
        // Repeat the rotation d times
        for (int i = 1; i <= d; i++) {
            ArrayRotate.rightRotate(arr);
        }
        System.out.printf("Array %d right rotation = %s%n", d, Arrays.toString(arr));
    }
}
