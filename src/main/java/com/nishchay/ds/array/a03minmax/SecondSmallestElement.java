package com.nishchay.ds.array.a03minmax;

import java.util.Arrays;

public class SecondSmallestElement {


    public static void main(String[] args) {

        int[] arr = {7, 2, 1, 7, 4, 5};
        System.out.println("Array = "+ Arrays.toString(arr) + ", Second largest element = " + getSecondSmallest_1pass(arr));

        arr = new int[]{12, 35, 1, 10, 34, 1};
        System.out.println("Array = "+ Arrays.toString(arr) + ", Second largest element = " + getSecondSmallest_1pass(arr));

        arr = new int[]{10, 5, 10};
        System.out.println("Array = "+ Arrays.toString(arr) + ", Second largest element = " + getSecondSmallest_1pass(arr));

        arr = new int[]{10, 10, 10};
        System.out.println("Array = "+ Arrays.toString(arr) + ", Second largest element = " + getSecondSmallest_1pass(arr));
    }

    /*
     * ============ [Optimal Approach] One Pass Approach ===============
     *  Using the same logic of finding the smallest element in an array
     *      using this fact  : if the smallest is getting updated during the scan, its previous value become second smallest
     *
     *   Time Complexity  : O(n)
     *   Space Complexity : O(1)
     * */
    private static int getSecondSmallest_1pass(int[] arr) {

        int n = arr.length;
        if (n < 2){
            System.out.println("Invalid Input ");
            return -1;
        }

        int smallest, secondSmallest;
        if (arr[0] < arr[1]) {
            smallest = arr[0];
            secondSmallest = arr[1];
        } else {
            smallest = arr[1];
            secondSmallest = arr[0];
        }

        for (int i = 2; i < n; i++) {
            if (arr[i] < smallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < secondSmallest && arr[i] != smallest)
                secondSmallest = arr[i];
        }
        return secondSmallest;
    }

}
