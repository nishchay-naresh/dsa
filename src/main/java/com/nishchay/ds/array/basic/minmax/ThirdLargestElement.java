package com.nishchay.ds.array.basic.minmax;

/*
 *	=========== Third largest element in an array of distinct elements ===========
 *
 * Given an array of n integers, find the third largest element. All the elements in the array are distinct integers.
 *
 *	Example 1:
 *		Input: arr[] = {1, 14, 2, 16, 10, 20}
 *		Output: The third Largest element is 14
 *		Explanation: Largest element is 20, second largest element is 16 and third largest element is 14
 *
 *	Example 2:
 *		Input: arr[] = {19, -10, 20, 14, 2, 16, 10}
 *		Output: The third Largest element is 16
 *		Explanation: Largest element is 20, second largest element is 19 and third largest element is 16
 *
 * https://www.geeksforgeeks.org/third-largest-element-array-distinct-elements/
 *
 * */
public class ThirdLargestElement {

    public static void main(String[] args) {

        int[] arr;

        arr = new int[]{1, 14, 2, 16, 10, 20};
        System.out.printf("The third Largest element is %d%n", get3rdLargestBruteForce(arr)); // 14
        System.out.printf("The third Largest element is %d%n", get3rdLargest(arr)); // 14

        arr = new int[]{19, -10, 20, 14, 2, 16, 10};
        System.out.printf("The third Largest element is %d%n", get3rdLargestBruteForce(arr)); // 16
        System.out.printf("The third Largest element is %d%n", get3rdLargest(arr)); // 16

    }

    /*
     * ============ [Better Approach] Three Pass Approach ===============
     *  The approach is to traverse the array thrice.
     *      First, iterate through the array and find maximum.
     *      Store this as first maximum along with its index.
     *      Now traverse the whole array finding the second max, excluding the maximum element.
     *      Finally traverse the array the third time and find the third largest element i.e., excluding the maximum and second maximum.
     *
     *   Time Complexity  : n + n + n = 3n = O(n)
     *   Space Complexity : 1
     *
     * */
    private static int get3rdLargestBruteForce(int[] arr) {

        int length = arr.length;

        if (length < 3) {
            System.out.println(" Invalid Input ");
            return -1;
        }

        // Find first largest element
        int first = arr[0];
        for (int i = 1; i < length; i++) {
            if (arr[i] > first) {
                first = arr[i];
            }

        }

        // Find second largest element
        int second = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            if (arr[i] > second && arr[i] < first) {
                second = arr[i];
            }
        }

        // Find third largest element
        int third = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            if (arr[i] > third && arr[i] < second) {
                third = arr[i];
            }
        }
        return third;
    }

    /*
     * ============ [Optimal Approach] One Pass Approach ===============
     *
     * Algorithm:
     *      First, iterate through the array and find maximum.
     *      Now traverse the whole array again and find the second max
     *      Finally traverse the array the third time and find the third largest element
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * */
    private static int get3rdLargest(int[] arr) {

        int arr_size = arr.length;

        if (arr_size < 3) {
            System.out.println("Invalid Input ");
            return -1;
        }

        // Initialize largest, secondLargest and thirdLargest Largest element
        int largest = arr[0], secondLargest = Integer.MIN_VALUE,
                thirdLargest = Integer.MIN_VALUE;

        // Traverse array elements to find the thirdLargest Largest
        for (int i = 1; i < arr_size; i++) {
            if (arr[i] > largest) {
                thirdLargest = secondLargest;
                secondLargest = largest;
                largest = arr[i];
            // If arr[i] is in between largest and secondLargest
            } else if (arr[i] > secondLargest) {
                thirdLargest = secondLargest;
                secondLargest = arr[i];
            // If arr[i] is in between secondLargest and thirdLargest
            } else if (arr[i] > thirdLargest) {
                thirdLargest = arr[i];
            }
        }
        return thirdLargest;
    }
}
