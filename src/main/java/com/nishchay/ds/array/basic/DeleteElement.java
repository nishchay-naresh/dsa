package com.nishchay.ds.array.basic;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
 *	Input: arr[] = { 1, 2, 3, 4, 5 }, key = 3
 *	Output: arr[] = { 1, 2, 4, 5 }
 *
 *	Input: arr[] = { 4, 5, 9, 8, 1 }, key = 8
 *	Output: arr[] = { 4, 5, 9, 1 }
 * */
public class DeleteElement {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        int[] resArr = deleteElementCopy(arr, 3);
        System.out.println("Before deletion :" + Arrays.toString(arr));
        System.out.println("After deletion :" + Arrays.toString(resArr));

        int[] arr1 = {4, 5, 9, 8, 1};
        deleteElementShifting(arr1, 8);

        int[] resArr1 = deleteElementStream(arr1, 8);
        System.out.println("Before deletion :" + Arrays.toString(arr1));
        System.out.println("After deletion :" + Arrays.toString(resArr1));

    }

    // Deleting element  - Array copy approach
    // Time Complexity: O(n) ,  Space Complexity: O(n)
    private static int[] deleteElementCopy(int[] array, int key) {

        int[] newArray = new int[array.length - 1];
        for (int i = 0, k = 0; i < array.length; i++) {
            if (array[i] != key) {
                newArray[k] = array[i];
                k++;
            }
        }
        return newArray;
    }


    // Deleting element  - Shifting elements in the same array
    // Time Complexity: O(n) ,  Space Complexity: O(1)
    private static void deleteElementShifting(int[] array, int key) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                count++;

                // shifting elements
                for (int k = i; k < array.length - 1; k++) {
                    array[k] = array[k + 1];
                }
                i--;
            }
        }

        System.out.print("After Deletion :" );
        for(int i = 0; i < array.length-count; i++){
            System.out.print(" " + array[i]);
        }
        System.out.println();
        System.out.println("Whole array :" + Arrays.toString(array));
    }

    private static int[] deleteElementStream(int[] intArr, int key) {

        return IntStream.of(intArr)
                .filter(e -> e != key)
                .toArray();
    }

}