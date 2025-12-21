package com.nishchay.ds.array.a07consecutive;


import java.util.Arrays;

/*
 * ======================= Check if array elements are consecutive ====================
 *
 * Given an unsorted array of numbers, the task is to check if the array consists of consecutive numbers.
 *
 *	Examples:
 *				Input: arr[] = [5, 2, 3, 1, 4]
 *				Output: Yes
 *				Explanation: Array has consecutive numbers from 1 to 5.
 *
 *				Input: arr[] = [83, 78, 80, 81, 79, 82]
 *				Output: Yes
 *				Explanation: Array has consecutive numbers from 78 to 83.
 *
 *				Input: arr[] = [34, 23, 52, 12, 3]
 *				Output: No
 *				Explanation: Elements are not consecutive.
 *
 *				Input: arr[] = [7, 6, 5, 5, 3, 4]
 *				Output: No
 *				Explanation: 5 is present two times.
 * https://www.geeksforgeeks.org/dsa/check-if-array-elements-are-consecutive/
 * https://leetcode.com/problems/check-if-an-array-is-consecutive/description/
 * */
public class CheckForConsecutive {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3, 1, 4};
        System.out.println(Arrays.toString(arr) + ", areConsecutive() = " + areConsecutive_sorting(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_visited() = " + areConsecutive_visited(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_xor() = " + areConsecutive_xor(arr));

        arr = new int[]{83, 78, 80, 81, 79, 82};
        System.out.println(Arrays.toString(arr) + ", areConsecutive() = " + areConsecutive_sorting(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_visited() = " + areConsecutive_visited(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_xor() = " + areConsecutive_xor(arr));

        arr = new int[]{34, 23, 52, 12, 3};
        System.out.println(Arrays.toString(arr) + ", areConsecutive() = " + areConsecutive_sorting(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_visited() = " + areConsecutive_visited(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_xor() = " + areConsecutive_xor(arr));

        arr = new int[]{7, 6, 5, 5, 3, 4};
        System.out.println(Arrays.toString(arr) + ", areConsecutive() = " + areConsecutive_sorting(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_visited() = " + areConsecutive_visited(arr));
        System.out.println(Arrays.toString(arr) + ", areConsecutive_xor() = " + areConsecutive_xor(arr));
    }

    /*
     * ================ [Naive Approach] Using Sorting - O(n*log n) Time and O(1) Space  =====================
     *
     *	The idea is to sort the array and then compare all the adjacent elements in the array.
     *  If the difference between the next element and current element is not 1, then return false. Otherwise, after traversing the array, return true.
     *
     *  Time Complexity     : O(n*log n)
     *  Space complexity    : O(1)
     */
    private static boolean areConsecutive_sorting(int[] arr) {
        int n = arr.length;

        // Sort the array
        Arrays.sort(arr);

        // checking the adjacent elements
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1] + 1) {
                return false;
            }
        }
        return true;
    }

    /*
     * ================ [Better Approach] Using Visited Array - O(n) time and O(n) space  =====================
     *
     * The idea is to check if all elements in the array can form a consecutive sequence by
     * 	first finding the minimum and maximum values in the array,
     * 	then verifying two key conditions:
     * 		-	the range (max-min+1) should equal the array length,
     * 				indicating no gaps in the sequence
     * 		-	Each number in the range should appear exactly once,
     * 				which is validated using a visited array where each element's position is marked relative to the minimum value.
     *
     *  Time Complexity     : O(n) + O(n) = O(n), 2 scan of array
     *  Space complexity    : O(n)
     */
    private static boolean areConsecutive_visited(int[] arr) {
        int n = arr.length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find the minimum and maximum element in the array.
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // If elements are consecutive, then there should be max-min+1 elements.
        if (max - min + 1 != n)
            return false;

        boolean[] visited = new boolean[n];
        for (int num : arr) {

            // If element is already marked
            if (visited[num - min]) {
                return false;
            }
            // Mark the element.
            visited[num - min] = true;
        }
        return true;
    }

    /*
     * ================ [Expected Approach] Using XOR - O(n) time and O(1) space  =====================
     *
     * The idea is to check if all elements in the array can form a consecutive sequence by
     * 	first finding the minimum and maximum values in the array,
     * 	then verifying two key conditions:
     * 		-	the range (max-min+1) should equal the array length,
     * 				indicating no gaps in the sequence
     * 		-	Each number in the range should appear exactly once,
     * 				If the array contains exactly the consecutive numbers in the expected range,
     *              each number will appear exactly twice in the XOR operation (once from the range and once from the array),
     *              resulting in all values canceling out to produce a final XOR value of 0, confirming that the array consists of consecutive numbers.
     *
     *  Time Complexity     : O(n) + O(n) = O(n), 2 scan of array
     *  Space complexity    : O(1)
     */
    private static boolean areConsecutive_xor(int[] arr) {
        int n = arr.length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find the minimum and maximum element in the array.
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // If elements are consecutive, then there should be max-min+1 elements.
        if (max - min + 1 != n)
            return false;

        // Take xor of all elements in range [min, max]
        int xorVal = 0;
        for (int i = min; i <= max; i++) {
            xorVal ^= i;
        }

        // Take xor of all values present in the array
        for (int val : arr) {
            xorVal ^= val;
        }

        // If values in array are consecutive, then the final xor value will be 0.
        return xorVal == 0;
    }
}
