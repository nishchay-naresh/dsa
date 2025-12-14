package com.nishchay.algo.recursion.subset;

import static com.nishchay.ds.array.a09subarray.AllSubarrays.printSubArrays;

public class PrintSubArray {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3};
        System.out.println("iterative way to print subarrays");
        printSubArrays(arr);
        System.out.println("recursive way to print subarrays");
        printSubArray(arr, 0);
    }

    private static void printSubArray(int[] input, int currIndex) {

        if (currIndex == input.length)
            return;

        //print all the subarray from currIndex to end
        StringBuilder result = new StringBuilder();
        for (int i = currIndex; i < input.length; i++) {
            result.append(" ").append(input[i]).append(" ");
            System.out.println("[" + result + "] ");
        }
        printSubArray(input, currIndex + 1);
    }
}
