package com.nishchay.bit.appearonce;

import java.util.*;

/*
 *  =======================  Single Number II ====================
 *  ======================= Element Appears Once In Pairs - 2 such elements are there ====================
 *
 * Given an array arr[] containing 2*n + 2 positive numbers,
 * Out of which 2*n numbers exist in pairs whereas the other two number occur exactly once and are distinct.
 * The task is to find the other two numbers.
 *
 * Note: Return the numbers in increasing order.
 *
 * 		Example:
 * 				Input: arr[] = [1, 2, 3, 2, 1, 4]
 * 				Output: 3 4
 * 				Explanation: 3 and 4 occur exactly once.
 *
 * 				Input: arr[] = [2, 1, 3, 2]
 * 				Output: 1 3
 * 				Explanation: 1 and 3 occur exactly once.
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-two-non-repeating-elements-in-an-array-of-repeating-elements/
 * https://leetcode.com/problems/single-number-iii/description/
 * https://takeuforward.org/plus/dsa/problems/single-number---iii?tab=editorial&approach=optimal
 * */
class Element2AppearsOnceInPairs {

    public static void main(String[] args) {
        int[] arr;

        arr = new int[] {1, 2, 3, 2, 1, 4};
        System.out.println("findUnique_hashing(arr) = "+ Arrays.toString(findUnique_hashing(arr))); // 3, 4
        System.out.println("findUnique_sorting(arr) = "+ Arrays.toString(findUnique_sorting(arr))); // 3, 4
        System.out.println("findUnique_xor(arr)     = "+ Arrays.toString(findUnique_xor(arr)));     // 3, 4

        arr = new int[] {2, 1, 3, 2};
        System.out.println("findUnique_hashing(arr) = "+ Arrays.toString(findUnique_hashing(arr))); // 1, 3
        System.out.println("findUnique_sorting(arr) = "+ Arrays.toString(findUnique_sorting(arr))); // 1, 3
        System.out.println("findUnique_xor(arr)     = "+ Arrays.toString(findUnique_xor(arr)));     // 1, 3
    }

    /*
     * ================ [Better Approach 1] Using Sorting - O(n log n) time and O(1) space =====================
     *
     *  This approach first sorts the array to group identical elements next to each other.
     *  Then, it iterates through the sorted array, checking for pairs of identical elements and skipping them.
     *  If an element is unique (appears only once), it is added to the result.
     *  This method ensures that the two unique elements are found and returned efficiently after sorting.
     *
     *  Time Complexity     : O(n log n)
     *  Space complexity    : O(1)
     */
    private static int[] findUnique_sorting(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        // Temporary array to store unique elements
        int[] temp = new int[n];
        int idx = 0;

        int i = 0;
        while (i < n) {
            // If element is present twice, skip both
            if (i + 1 < n && arr[i + 1] == arr[i]) {
                i += 2;
            } else {
                // If unique, store in temp
                temp[idx++] = arr[i];
                i++;
            }
        }

        // Copy valid elements to a properly-sized array
        int[] result = new int[idx];
        for (int j = 0; j < idx; j++) {
            result[j] = temp[j];
        }

        return result;
    }

    /*
     *  ================ [Better Approach] Using HashMap - O(n) time and O(n) space  =====================
     *
     * This approach uses an unordered map (freq) to track the frequency of elements in the array.
     * After building the frequency map, it collects elements that occur exactly once and returns them in ascending order.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     */
    private static int[] findUnique_hashing(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int x : arr) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        List<Integer> temp = new ArrayList<>();

        // Collect numbers that appear exactly once
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                temp.add(entry.getKey());
            }
        }

        // Sort the result
        Collections.sort(temp);

        // Convert to int[]
        int[] result = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }

        return result;
    }

    /*
     *  ================ [Optimize/Expected Approach] Using XOR Operation - O(n) Time and O(1) Space  =====================
     *
     * XOR of a number with itself is 0.        ie x ^ x = 0
     * And XOR of a number with 0 is number.    ie 0 ^ x = x
     *
     *      array : 1, 2, 3, 4, 5
     *      array : 1, 2,    4,      XOR
     *  --------------------------------
     *              0, 0, 3, 0, 5
     *
     *  ------- Concept of bucketing --------
     *	1.	Find one bit where the two unique numbers differ → rightmost difference Bit which is set
     *	2.	use it to separate the array
     *	3.	XOR cancels duplicates
     *	4.	uniques remain.
     *
     *
     *  nums = {2, 4, 2, 14, 3, 7, 7, 3}
     *
     * XOR = 2^2 ^ 3^3 ^ 7^7 ^ 14^4
     *      14^4 (since these 2 are distinct numbers, there must be minimum of 1 bit difference in their binary representation)
     *      10
     *
     * binary reps
     * 14   ->     1 1 1 0
     *  4   ->     0 1 0 0
     * ----------------------
     *    ^        1 0 1 0 , we can use either of the bit (since both are the difference bit), here we will use rightmost bit here
     *
     *  num     = 1 0 1 0 1 0 0
     * num-1    = 1 0 1 0 0 1 1
     * ----------------------------
     * &          1 0 1 0 0 0 0     if you observe that everything right to rightmost set bit is turn to 0, and left part is copied
     *   num    = 1 0 1 0 1 0 0
     * ----------------------------
     * ^          0 0 0 0 1 0 0
     *
     * Now, coming back to our problem, we will do the same with xorAll
     * Based on this rightmost set bit, number is been collected in 2 diff buckets :
     *          Bucket1  - rightmost diff bit is set        -   14
     *          Bucket2  - rightmost diff bit is not set    -   4
     *
     * We will iterate the array again and start putting all the element s among these 2 buckets
     * Since all the other numbers are appeared 2 time XOR of them will cancel out, we will end up having 1 different number in each bucket
     * Duplicate numbers always go into the same group, so they cancel out
     *
     *
     * Time Complexity  : O(n) + O(n) = O(2n) = O(n)
     * Space Complexity : O(1)
     * */
    private static int[] findUnique_xor(int[] arr) {
        // variable to store XOR of all elements
        int xorAll = 0;

        // Step 1: XOR all numbers
        for (int e : arr) {
            xorAll = xorAll ^ e;
        }

        // Step 2: get the rightmost difference Bit which is set in overall XOR
        // variable to get the rightmostDiffBit is set in overall XOR
        int rightmostDiffBit = (xorAll & (xorAll - 1)) ^ xorAll;

        // variables to stores XOR of elements in bucket 1 and 2
        int xor1 = 0, xor2 = 0;

        // Step 3: Divide numbers into two buckets based on rightmostDiffBit
        for (int e : arr) {
            if ((e & rightmostDiffBit) == 0) {
                xor1 = xor1 ^ e;
            } else {
                xor2 = xor2 ^ e;
            }
        }
        // Return the result in sorted order
        if(xor1 < xor2)
            return new int[]{xor1, xor2};
        return new int[]{xor2, xor1};
    }
}