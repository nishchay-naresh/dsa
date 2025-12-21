package com.nishchay.ds.array.a07consecutive;

import java.util.Arrays;
import java.util.HashSet;

/*
 * ======================= leetcode-128 Longest Consecutive Sequence ====================
 *
 * Given an array of integers, the task is to find the length of the longest subsequence such that elements in the subsequence are consecutive integers,
 * the consecutive numbers can be in any order.
 *
 * Examples:
 *				Input: arr[] = [2, 6, 1, 9, 4, 5, 3]
 *				Output: 6
 *				Explanation:  The longest consecutive subsequence [2, 6, 1, 4, 5, 3].
 *
 *				Input: arr[] = [1,1,1,2,2,3]
 *				Output: 3
 *				Explanation: The subsequence [1, 2, 3] is the longest subsequence of consecutive elements
 *
 *				Input: nums = [100,4,200,1,3,2]
 *				Output: 4
 *				Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 *				Input: nums = [0,3,7,2,5,8,4,6,0,1]
 *				Output: 9
 *
 *				Input: nums = [1,0,1,2]
 *				Output: 3
 *
 * https://leetcode.com/problems/longest-consecutive-sequence/description/
 * https://www.geeksforgeeks.org/dsa/longest-consecutive-subsequence/
 * */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, 1, 9, 4, 5, 3};
        System.out.println(Arrays.toString(arr) + " = " + longestConsecutiveSeq(arr));
        System.out.println(Arrays.toString(arr) + " = " + findLongestConsecutiveSeq(arr));

        arr = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(arr) + " = " + longestConsecutiveSeq(arr));
        System.out.println(Arrays.toString(arr) + " = " + findLongestConsecutiveSeq(arr));

        arr = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(Arrays.toString(arr) + " = " + longestConsecutiveSeq(arr));
        System.out.println(Arrays.toString(arr) + " = " + findLongestConsecutiveSeq(arr));

        arr = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(Arrays.toString(arr) + " = " + longestConsecutiveSeq(arr));
        System.out.println(Arrays.toString(arr) + " = " + findLongestConsecutiveSeq(arr));

        arr = new int[]{1, 0, 1, 2};
        System.out.println(Arrays.toString(arr) + " = " + longestConsecutiveSeq(arr));
        System.out.println(Arrays.toString(arr) + " = " + findLongestConsecutiveSeq(arr));

        arr = new int[]{1, 9, 3, 8, 10, 4, 11, 2};
        System.out.println(Arrays.toString(arr) + " = " + longestConsecutiveSeq(arr));
        System.out.println(Arrays.toString(arr) + " = " + findLongestConsecutiveSeq(arr));
    }


    /*
     * ================ [Naive Approach] Using Sorting - O(n*log n) Time and O(1) Space  =====================
     *
     *	1.	Sort the array in ascending order.
     *  2.	Iterate the array and find the consecutive sequence
     *		For each element arr[i]; we can have three cases:
     *			arr[i] = arr[i - 1], its duplicate, skip it
     *			arr[i] = arr[i - 1] + 1, increase the consecutive count and update result if consecutive count is greater than result.
     *			Arr[i] > arr[i - 1], then reset the consecutive count to 1.
     *	3.	After iterating over all the elements, return the result.
     *
     *  Time Complexity     : O(n*log n)
     *  Space complexity    : O(1)
     */
    private static int longestConsecutiveSeq(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;

        Arrays.sort(arr);

        // consecutive sequence counters
        int maxCount = 1, count = 1;

        for (int i = 1; i < n; i++) {

            // Skip duplicates
            if (arr[i] == arr[i - 1])
                continue;

            // Check if the current element is equal to previous element + 1
            if (arr[i] == arr[i - 1] + 1) {
                count++;
            } else {
                // Reset the count
                count = 1;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    /*
     * ================ [Expected Approach] Using Hashing - O(n) Time and O(n) Space =====================
     *
     * The idea is to use Hashing.
     *
     * We first insert all elements in a Hash Set.
     * Then, traverse over all the elements and check if the current element can be a starting element of a consecutive subsequence.
     *      If X is the starting of consecutive subsequence
     *          then we keep removing elements X + 1, X + 2 and track the count of consecutive subsequence
     *
     * Check if the current element, say X can be a starting element of consecutive subsequence
     *        if (st.contains(X) && !st.contains(X - 1))
     *
     * if(currentElement - 1 is not there in hashTable) // means at the starting element of the sequence
     *   then only we will proceed with the sequence counting
     *
     * Time complexity: O(n)
     * Auxiliary space: O(n)
     *
     * */
    private static int findLongestConsecutiveSeq(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        int maxCount = 0;

        for (int val : arr)
            set.add(val);

        // check each possible sequence from the start
        for (int val : arr) {
            // If the current element is the starting element of a sequence
            if (set.contains(val) && !set.contains(val - 1)) {

                // Then check for next elements in the sequence
                int cur = val, cnt = 0;
                while (set.contains(cur)) {
                    // Remove this number to avoid recomputation
                    set.remove(cur);
                    cur++;
                    cnt++;
                }
                maxCount = Math.max(maxCount, cnt);
            }
        }
        return maxCount;
    }

}
