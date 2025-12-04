package com.nishchay.algo.stdalgo;

/*
 *	==================================== Majority Element ========================================
 *
 *	Given an array arr[] of size n, find the element that appears more than ⌊n/2⌋ times. If no such element exists, return -1.
 *
 *	Examples:
 *				Input: arr[] = [1, 1, 2, 1, 3, 5, 1]
 *				Output: 1
 *				Explanation: Element 1 appears 4 times. Since (7/2) = 3, and 4 > 3, it is the majority element.
 *
 *				Input: arr[] = [7]
 *				Output: 7
 *				Explanation: Element 7 appears once. Since (1/2) = 0, and 1 > 0, it is the majority element.
 *
 *				Input: arr[] = [2, 13]
 *				Output: -1
 *				Explanation: No element appears more than (2/2) = 1 time, so there is no majority element.
 *
 *
 * https://www.geeksforgeeks.org/dsa/majority-element/
 * https://takeuforward.org/data-structure/find-the-majority-element-that-occurs-more-than-n-2-times/
 * */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BoyerMooreVoteAlgorithm {

    public static void main(String[] args) {

        int[] array = {2, 6, 2, 2, 6, 2, 2, 8, 2, 1};
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElement(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElementSorting(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElementHashing(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + getMajorityElement(array));

        array = new int[]{1, 1, 2, 1, 3, 5, 1};
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElement(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElementSorting(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElementHashing(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + getMajorityElement(array));

        array = new int[]{3, 3, 4, 2, 4, 4, 2, 4};
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElement(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElementSorting(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + majorityElementHashing(array));
        System.out.println(Arrays.toString(array) + ", Majority Element: " + getMajorityElement(array));
    }

    /*
     * ================ [Naive/Bruteforce Approach] Using Two Nested Loops  =====================
     *
     *  The idea is to use nested loops to count frequencies. The outer loop selects each element as a candidate,
     *  and the inner loop counts how many times it appears. If any element appears more than n / 2 times, it is the majority element.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int majorityElement(int[] arr) {
        int n = arr.length;

        // Loop to consider each element as a candidate for majority
        for (int i = 0; i < n; i++) {
            int freq = 0;

            // Inner loop to get the frequency of arr[i]
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    freq++;
                }
            }

            if (freq > n / 2) {
                return arr[i];
            }
        }
        return -1;
    }

    /*
     *  ================ [Better Approach] Using Sorting - O(nlog(n)) Time and O(1) Space  =====================
     * The idea is to sort the array so that similar elements are next to each other.
     * Once sorted, go through the array and keep track of how many times a middle element appears.
     *
     * Why middle element?
     * If a number appears more than half the time, it must occupy the middle position in the sorted array.
     *
     * Time Complexity  : O(nlog(n))
     * Space Complexity : O(1)
     * */
    private static int majorityElementSorting(int[] arr) {

        int n = arr.length;
        Arrays.sort(arr);

        // Potential majority element
        int candidate = arr[n / 2];

        // Count how many times candidate appears
        int count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
        if (count > n / 2) {
            return candidate;
        }
        return -1;
    }


    /*
     *  ================ [Better Approach] Using Hashing - O(n) Time and O(n) Space  =====================
     *
     * Step By Step Implementations:
     *  Initialize an empty hash map.
     *  Traverse the array and update the count of each element.
     *  After each update, check if the count exceeds n / 2.
     *  If found, return that element immediately.
     *  If no such element exists after the loop, return -1.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     * */
    private static int majorityElementHashing(int[] arr) {

        int n = arr.length;
        Map<Integer, Integer> countMap = new HashMap<>();

        // Traverse the array and count frequency using the hash map
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            // Check if current element count exceeds n / 2
            if (countMap.get(num) > n / 2) {
                return num;
            }
        }
        return -1;
    }

    /*
     *  ================ [Expected Approach] Using Moore's Voting Algorithm - O(n) Time and O(1) Space  =====================
     *
     *  The idea is to use the Boyer-Moore Voting Algorithm to efficiently find a potential majority element by canceling out different elements.
     *  If a majority element exists, it will remain as the candidate. Then verify it.
     *
     * This is a two-step process:
     *      1.  The first step gives the element that may be the majority element in the array. If there is a majority element in an array,
     *          then this step will definitely return majority element, otherwise, it will return candidate for majority element.
     *
     *      2.  Check if the element obtained from the above step is the majority element. This step is necessary as there might be no majority element.
     *
     *	Step By Step Approach:
     *	1. Initialize a candidate variable and a count variable.
     *	2. Traverse the array once:
     *		-> If count is zero, set the candidate to the current element and set count to one.
     *		-> If the current element equals the candidate, increment count.
     *		-> If the current element differs from the candidate, decrement count.(cancel out with other element)
     *	3. Traverse the array again to count the occurrences of the candidate.
     *	4. If the candidate's count is greater than n / 2, return the candidate as the majority element.
     *
     * Time Complexity  : O(n) +  O(n) =  O(n)
     * Space Complexity : O(1)
     * */
    private static Integer getMajorityElement(int[] array) {

        int n = array.length;
        int candidate = -1;
        int count = 0;

        // Find a candidate
        for (int curr : array) {
            if (count == 0) {
                candidate = curr;
                count = 1;
            } else if (curr == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Validate the candidate
        count = 0;
        for (int num : array) {
            if (num == candidate) {
                count++;
            }
        }

        if (count > n / 2) {
            return candidate;
        }
        return -1;
    }
}
