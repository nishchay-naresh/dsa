package com.nishchay.algo.recursion.permutation;

import java.util.Arrays;

/*
 * Java program to Print out all permutations of an Array
 *
 * example - Given input [3,2,1] all possible permutations (without repetitions) are
 *
 * [3, 2, 1]
 * [3, 1, 2]
 * [2, 3, 1]
 * [2, 1, 3]
 * [1, 2, 3]
 * [1, 3, 2]
 *
 * */
public class PrintAllPermutationSet {

    public static void main(String[] args) {
        int[] arr = {3, 2, 1};
        arrPermute(arr, 0, arr.length - 1);
    }


    /**
     * permutation function
     * Time Complexity: O(n*n!)
     */
    private static void arrPermute(int[] arr, int start, int end) {
        if (start == end)
            System.out.println(Arrays.toString(arr));
        else {
            for (int i = start; i <= end; i++) {
                swap(arr, start, i); // first swap -> to find a combination.
                arrPermute(arr, start + 1, end);
                swap(arr, start, i); //swap back for backtracking
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
/*
 * O/P =>
 * [3, 5, 2, 1]
 * [3, 5, 1, 2]
 * [3, 2, 5, 1]
 * [3, 2, 1, 5]
 * [3, 1, 2, 5]
 * [3, 1, 5, 2]
 * [5, 3, 2, 1]
 * [5, 3, 1, 2]
 * [5, 2, 3, 1]
 * [5, 2, 1, 3]
 * [5, 1, 2, 3]
 * [5, 1, 3, 2]
 * [2, 5, 3, 1]
 * [2, 5, 1, 3]
 * [2, 3, 5, 1]
 * [2, 3, 1, 5]
 * [2, 1, 3, 5]
 * [2, 1, 5, 3]
 * [1, 5, 2, 3]
 * [1, 5, 3, 2]
 * [1, 2, 5, 3]
 * [1, 2, 3, 5]
 * [1, 3, 2, 5]
 * [1, 3, 5, 2]
 * */