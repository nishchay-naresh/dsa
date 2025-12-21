package com.nishchay.ds.array.a07consecutive;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * ======================= Partition into minimum subsets of consecutive numbers ====================
 *  Count the minimum number of subsets (or subsequences) with consecutive numbers
 *
 *	Given an array of distinct positive numbers,
 *  the task is to partition the array into minimum number of subsets (or subsequences)
 *  such that each subset contains consecutive numbers only.
 *
 *	Examples:
 *	            Input :  arr[] = {100, 56, 5, 6, 102, 58, 101, 57, 7, 103, 59}
 *	            Output : 3
 *              Explanation : {5, 6, 7}, { 56, 57, 58, 59}, {100, 101, 102, 103} are 3 subset in which numbers are consecutive.
 *
 *	Examples:
 *              Input :  arr[] = {10, 100, 105}
 *              Output : 3
 *              Explanation : {10}, {100} and {105} are 3 subsets in which numbers are consecutive.
 *
 * https://www.geeksforgeeks.org/dsa/count-minimum-number-subsets-subsequences-consecutive-numbers/
 * */
public class CountConsecutiveNumbersSubset {

    public static void main(String[] args) {
        int[] arr = { 100, 56, 5, 6, 102, 58, 101, 57, 7, 103, 59 };
        System.out.println(countOfConsecutiveNosSubset_sorting(arr)); // 3
        System.out.println(countOfConsecutiveNosSubset_hashing(arr)); // 3

        arr = new int[]{10, 100, 105};
        System.out.println(countOfConsecutiveNosSubset_sorting(arr)); // 3
        System.out.println(countOfConsecutiveNosSubset_hashing(arr)); // 3
    }

    /*
     * ================ [Naive Approach] Sorting - O(n*log n) Time and O(1) Space  =====================
     *
     *  The idea is to Sort the array - so that elements which are consecutive in nature became consecutive in the array.
     *  Then traverse the sorted array to count the number of such subsets.
     *  One needs to count the pairs of consecutive numbers such that the difference between them is not equal to one.
     *  Each of such pair will belong to a new subset.
     *
     *  Time Complexity     : O(n*log n)
     *  Space complexity    : O(1)
     */
    private static int countOfConsecutiveNosSubset_sorting(int[] arr){

        Arrays.sort(arr);

        int count = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            // Check if there's a break between consecutive numbers
            if (arr[i] + 1 != arr[i + 1])
                count++;
        }
        return count;
    }

    /*
     * ================ [Expected Approach] Hashing - O(n) Time and O(n) Space  =====================
     *
     *  The idea is to use Hashing. We first insert all elements in a Hash Set.
     *  Then, traverse over all the elements and check if the current element can be a starting element of a consecutive subsequence.
     *      How to find - starting element of a consecutive subsequence
     *          To check if the current element, say X can be a starting element, check if (X – 1) is present in the set.
     *          If (X – 1) is present in the set, then X cannot be starting of a consecutive subsequence.
     *  If it is then increment count by 1 and keep skipping elements X, X + 1, X + 2 …. Till all consecutive numbers of this subset are skipped.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int countOfConsecutiveNosSubset_hashing(int[] arr){

        // Create a HashSet from the array
        Set<Integer> s = new HashSet<>();
        for(int num : arr){
            s.add(num);
        }

        int count = 0;
        for(int x : arr){
            // Check for the start of a new subset
            if(!s.contains(x - 1)){
                count++;
            }
        }
        return count;
    }

}
