package com.nishchay.ds.array.a10medium;

import java.util.HashMap;
import java.util.Map;

/*
 *  ============================= Minimum delete operations to make all elements of array same ==========================
 *
 * Given an array of n elements such that element may repeat. We can delete any number of elements from the array.
 * The task is to find a minimum number of elements to be deleted from the array to make it equal.
 * Examples:
 *
 * 			Input: arr[] = {4, 3, 4, 4, 2, 4}
 * 			Output: 2
 * 			After deleting 2 and 3 from array, array becomes
 * 			arr[] = {4, 4, 4, 4}
 *
 * 			Input: arr[] = {1, 2, 3, 4, 5}
 * 			Output: 4
 * 			We can delete any four elements from array.
 *
 * 			Input: arr[] = {2, 3, 4, 2, 3, 1, 3, 2}
 * 			Output: 5
 * 			After deleting 4,1 and 2/3 from array, array becomes
 * 			arr[] = {2, 2, 2} or { 3, 3, 3}
 *
 * https://www.hackerrank.com/challenges/equality-in-a-array/problem
 * https://www.geeksforgeeks.org/dsa/minimum-delete-operations-make-elements-array/
 * */
public class EqualizeArray {

    public static void main(String[] args) {
        int[] arr = new int[] {4, 3, 4, 4, 2, 4};
        System.out.println("minDelete - " + minDelete(arr)); //2

        arr = new int[] {1, 2, 3, 4, 5};
        System.out.println("minDelete - " + minDelete(arr)); //4

        arr = new int[] {2, 3, 4, 2, 3, 1, 3, 2};
        System.out.println("minDelete - " + minDelete(arr)); // 5
    }

    /*
     * ================ [Expected Approach] - Hashing approach - O(n) Time and O(n) Space  =====================
     *
     * 	============== Core Intuition =================
     * 	To make all elements equal:
     * 		-	Keep the value that appears maximum times
     * 		-	Remove all other elements.
     *
     * 	So the problem becomes: Find maximum frequency element
     * 	Formula
     * 		Minimum deletions = n − maxFrequency
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int minDelete(int[] arr) {

        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        for (int curr : arr) {
            int currFreq = freqMap.getOrDefault(curr, 0) + 1;
            freqMap.put(curr, currFreq);
            maxFreq = Math.max(currFreq, maxFreq);
        }
        return arr.length - maxFreq;
    }
}