package com.nishchay.ds.array.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * ====================== Leaders in an array ==========================
 *
 * Given an array arr[] of size n, the task is to find all the Leaders in the array.
 * An element is a Leader if it is greater than or equal to all the elements to its right side.
 *
 * Note: The rightmost element is always a leader.
 *
 * Examples:
 * 			Input: arr[] = [16, 17, 4, 3, 5, 2]
 * 			Output: [17 5 2]
 * 			Explanation:
 *					17 is greater than all the elements to its right i.e., [4, 3, 5, 2], therefore 17 is a leader.
 *					5 is greater than all the elements to its right i.e., [2], therefore 5 is a leader.
 *					2 has no element to its right, therefore 2 is a leader.
 *
 * 			Input: arr[] = [1, 2, 3, 4, 5, 2]
 * 			Output: [5 2]
 * 			Explanation:
 *					5 is greater than all the elements to its right i.e., [2], therefore 5 is a leader.
 *					2 has no element to its right, therefore 2 is a leader.
 *
 *
 * https://www.geeksforgeeks.org/dsa/leaders-in-an-array/
 * */
public class Leaders {
    public static void main(String[] args) {
        int[] arr = {16, 17, 4, 3, 5, 2};
        List<Integer> result = getLeaders(arr);
        System.out.println("leaders = " + result);

        result = getLeaders_onePass(arr);
        System.out.println("leaders = " + result);

        arr = new int[]{10, 22, 12, 3, 0, 6};
        result = getLeaders_onePass(arr);
        System.out.println("leaders = " + result);
    }

    /*
     * ================ [Naive/Bruteforce Approach] Using Nested Two Pass  =====================
     *
     *
     *  Assuming each element is leader, using a flag to mark it
     *  Then checking ti against of each of its next elements in next nexted loop
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(n)
     */
    private static List<Integer> getLeaders(int[] arr) {
        int n = arr.length;
        List<Integer> leaders = new ArrayList<>();

        boolean isLeader;
        for (int i = 0; i < n; i++) {
            isLeader = true;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) {
                leaders.add(arr[i]);
            }
        }
        return leaders;
    }

    /*
     * ================ [Optimal Approach] Using Suffix Maximum  =====================
     *
     *  Scan all the elements from right to left in an array and keep track of the maximum till now
     *     If current element is great than maximum, then its a leader
     *      and updating the maximum
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static List<Integer> getLeaders_onePass(int[] arr) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>();

        // Start with the rightmost element
        int maxRight = arr[n - 1];

        // Rightmost element is always a leader
        result.add(maxRight);

        // Traverse the array from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= maxRight) {
                maxRight = arr[i];
                result.add(maxRight);
            }
        }

        // Reverse the result list to maintain the original order
        Collections.reverse(result);

        return result;
    }
}
