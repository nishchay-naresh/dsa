package com.nishchay.ds.array;


/*
*
* this class is having basic operations/functionalities of tree as a data structure
*
* */
public class ArrayUtils {


    // Iterative Approach - O(n) Time and O(1) Space
    public static int findMax(int[] arr) {
        int max = arr[0]; // assuming it's max value

        // Now traverse array from 1 ro n-1
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    public static int findMin(int[] arr) {
        int min = arr[0];

        // Now traverse array from 1 ro n-1
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }


}
