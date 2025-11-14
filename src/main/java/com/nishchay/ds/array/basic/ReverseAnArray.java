package com.nishchay.ds.array.basic;

import java.util.Arrays;


/*
 *  ======================= Reverse An Array ====================
 *
 *  Reverse an array arr[]. it means reversing the content of an int array
 *  Reversing an array means rearranging the elements such that the first element becomes the last, the second element becomes second last and so on.
 *
 *  * Examples:
 * 			Input: arr[] = [1, 4, 3, 2, 6, 5]
 * 			Output:  [5, 6, 2, 3, 4, 1]
 * 			Explanation: The first element 1 moves to last position, the second element 4 moves to second-last and so on.
 *
 * 			Input: arr[] = [4, 5, 1, 2]
 * 			Output: [2, 1, 5, 4]
 * 			Explanation: The first element 4 moves to last position, the second element 5 moves to second last and so on.
 *
 * https://www.geeksforgeeks.org/dsa/program-to-reverse-an-array/
 * */
public class ReverseAnArray {

    public static void main(String[] args) {
        int[] arr = new int[]{10, 20, 30, 40, 50};
        arrayReversePrint(arr);

        System.out.println("-------------------------------------");
        executeReverseArray(arr);

        arr = new int[]{1, 4, 3, 2, 6, 5};
        executeReverseArray(arr);

        arr = new int[]{4, 5, 1, 2};
        executeReverseArray(arr);
    }

    private static void arrayReversePrint(int[] arr) {
        System.out.println("Actual Array : " + Arrays.toString(arr));
        System.out.print("Printing Array in reverse order:");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }


    private static void executeReverseArray(int[] arr) {
        System.out.println("Actual Array : " + Arrays.toString(arr));
        reverseArray(arr);
        System.out.println("Reversed Array : " + Arrays.toString(arr));
    }

    /*
     * ================ [Optimal Approach] 2 pointer approach  =====================
     *
     *  The idea is to maintain two pointers: at both end of the array
     *          keep swaping the elements at these two positions.
     *  Until they don't meet/cross each other
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        int temp;
        while (left < right) {
            // swap arr[left] & arr[right] element
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}
