package com.nishchay.ds.array.a05twosum;

import java.util.*;

/*
 *============== Two Sum Problem ====================
 *
 * Given an array A[] and a number x, check for pair in A[] with sum as x (aka Two Sum)
 *
 * Given an array of integers, return the two numbers whose sum is equal to a given target.
 *
 * Examples - 1
 *			Input: Given nums = [2, 7, 11, 15], target = 9.
 *			Output : [2, 7]
 *					 Explanation: Because 2 + 7 = 9.
 *
 * Examples - 2
 *			Input : arr[] = {0, -1, 2, -3, 1}, target = -2.
 *			Output : [-3, 1]
 *					Explanation : Because -3 + 1 = -2.
 *
 * Examples - 3
 *			Input : arr[] = {1, -2, 1, 0, 5}, target = 0.
 *			Output : []
 *					Explanation: No valid pair exists for 0.
 *
 * Examples - 4
 *			Input : arr[] = {3, 5, 2, -4, 8, 11}, target = 7.
 *			Output : [5, 2],[-4, 11]
 *					Explanation : Because because 2 + 5 = 7 and 11 + -4 = 7.
 *
 *
 * https://www.callicoder.com/two-sum-problem/
 * https://www.geeksforgeeks.org/given-an-array-a-and-a-number-x-check-for-pair-in-a-with-sum-as-x/
 *
 * */
public class P2_01TwoSumProblem {

    public static void main(String[] args) {

        bruteforceWay();
        System.out.print("\n-----------------------------");
        hashingWay();
        System.out.print("\n-----------------------------");
        twoPointersWay();

    }

    /*
     * ========= naive/bruteforce approach =========
     *
     *	Approach:
     *	1. 	Run two nested loops with loop counter i, j
     *	2. 	The first loops will run from 0 to n-1 and
     *          second loop from i+1 to n and
     *	3. 	Check if the sum of elements at i’th, j’th is equal to sum/target value or not.
     *
     * Time Complexity: O(n^2).
     * Auxiliary Space: O(1).
     *
     * */
    private static List<int[]> findPair_bruteforce(int[] arr, int sum) {

        int n = arr.length;
        List<int[]> pairs = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == sum) {
                    pairs.add(new int[]{arr[i], arr[j]});
                }
            }
        }
        return pairs;
    }

    private static void bruteforceWay() {

        int[] intArr;
        int sum;
        List<int[]> pairs;

        intArr = new int[]{2, 7, 11, 15}; sum = 9;
        pairs = findPair_bruteforce(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [2, 7]

        intArr = new int[]{0, -1, 2, -3, 1}; sum = -2;
        pairs = findPair_bruteforce(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [-3, 1]

        intArr = new int[]{1, -2, 1, 0, 5}; sum = 0;
        pairs = findPair_bruteforce(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// []

        intArr = new int[]{3, 5, 2, -4, 8, 11}; sum = 7;
        pairs = findPair_bruteforce(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [5, 2],[-4, 11]

    }

    /*
     * ========= hashing approach =========
     * One can use a HashMap to solve the problem in O(n) time complexity
     *
     *	Approach:
     *	1.	Initialize an empty HashMap.
     *	2.	Iterate over the elements of the array.
     *	3.	For every element in the array, do below :
     *	    -   If the element exists in the Map, then check if it’s complement (target - element) also exists in the Map or not.
     *      -   Otherwise, put the element in the Map, and move to the next iteration.
     *
     * Time Complexity: O(n).
     * Auxiliary Space: O(n).
     *
     * */
    private static List<int[]> findPair_hashing(int[] arr, int sum) {

        int n = arr.length, complement;
        List<int[]> pairs = new ArrayList<>();

        Set<Integer> numMap = new HashSet<>();
        for (int e : arr) {
            complement = sum - e;
            if (numMap.contains(complement)) {
                pairs.add(new int[]{e, complement});
            } else {
                numMap.add(e);
            }
        }
        return pairs;
    }

    private static void hashingWay() {

        int[] intArr;
        int sum;
        List<int[]> pairs;

        intArr = new int[]{2, 7, 11, 15}; sum = 9;
        pairs = findPair_hashing(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [2, 7]

        intArr = new int[]{0, -1, 2, -3, 1}; sum = -2;
        pairs = findPair_hashing(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [-3, 1]

        intArr = new int[]{1, -2, 1, 0, 5}; sum = 0;
        pairs = findPair_hashing(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// []

        intArr = new int[]{3, 5, 2, -4, 8, 11}; sum = 7;
        pairs = findPair_hashing(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [5, 2],[-4, 11]

    }

    /*
     * ========= sorting then two pointers approach =========
     *
     *	Approach:
     *	1.	Sort the array.
     *	2.	Initialize two variables, one pointing to the beginning of the array (left) and another pointing to the end of the array (right).
     *	3.	Loop until left < right, and for each iteration
     *	    - if arr[left] + arr[right] == target, then we got the pair increment left, decrement right and continue the loop
     *      - if arr[left] + arr[right] < target, increment the left index.
     *      - else, decrement the right index.
     *
     * This approach is called the two-pointer approach. It is a very common pattern for solving array related problems.
     *
     * Time Complexity: O(n * log n).
     * Auxiliary Space: O(1).
     *
     * */
    private static List<int[]> findPairs_twoPointers(int[] arr, int sum) {

        List<int[]> pairs = new ArrayList<>();

        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            if(arr[left] + arr[right] == sum) {
                pairs.add(new int[]{arr[left], arr[right]});
                left++;
                right--;
            } else if (arr[left] + arr[right] < sum) {
                // If sum of three elements is less than zero then increment in left
                left++;
            } else {
                // if sum is greater than zero then decrement in right side
                right--;
            }
        }
        return pairs;
    }

    private static void twoPointersWay() {

        int[] intArr;
        int sum;
        List<int[]> pairs;

        intArr = new int[]{2, 7, 11, 15}; sum = 9;
        pairs = findPairs_twoPointers(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [2, 7]

        intArr = new int[]{0, -1, 2, -3, 1}; sum = -2;
        pairs = findPairs_twoPointers(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [-3, 1]

        intArr = new int[]{1, -2, 1, 0, 5}; sum = 0;
        pairs = findPairs_twoPointers(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// []

        intArr = new int[]{3, 5, 2, -4, 8, 11}; sum = 7;
        pairs = findPairs_twoPointers(intArr, sum);
        System.out.print("\npairs = ");
        for (int[] curr : pairs)
            System.out.print("\t" + Arrays.toString(curr));// [5, 2],[-4, 11]

    }

}
