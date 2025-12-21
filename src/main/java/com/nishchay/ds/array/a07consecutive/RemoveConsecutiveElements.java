package com.nishchay.ds.array.a07consecutive;


import java.util.ArrayList;
import java.util.List;

/*
 * ======================= Find all ranges of consecutive numbers from Array ====================
 *
 * Given a sorted array arr[] consisting of N integers without any duplicates, the task is to find the ranges of consecutive numbers from that array.
 *
 * Examples:
 *				Input: arr[] = {1, 2, 3, 6, 7}
 *				Output: 1->3, 6->7
 *				Explanation:There are two ranges of consecutive number from that array.
 *							Range 1 = 1 -> 3
 *							Range 2 = 6 -> 7
 *
 *				Input: arr[] = {-1, 0, 1, 2, 5, 6, 8}
 *				Output: -1->2, 5->6, 8
 *				Explanation:There are three ranges of consecutive number from that array.
 *							Range 1 = -1 -> 2
 *							Range 2 = 5 -> 6
 *							Range 3 = 8
 *
 *              input: intArr[] = {-4, -3, -2, -1, 0, 6, 7, 9, 11, 12, 13, 18, 20, 21, 22}
 *              remove consecutive natural numbers from array
 *              output: String = -4 -> 0, 6, 7, 9, 11 -> 13, 18, 20 -> 22
 *
 * https://www.geeksforgeeks.org/dsa/find-all-ranges-of-consecutive-numbers-from-array/
 * */
public class RemoveConsecutiveElements {

    public static void main(String[] args) {
        int[] intArr = {-4, -3, -2, -1, 0, 6, 7, 9, 11, 12, 13, 18, 20, 21, 22};

        System.out.println("result = " + removeConsecutiveNumbers(intArr));
        System.out.print("result1 = " );
        findRanges(intArr).forEach(System.out::print);

    }

    private static String removeConsecutiveNumbers(int[] num) {
        StringBuilder res = new StringBuilder();
        int length = num.length;

        for (int i = 0; i < length; i++) {

            // number sequence
            if (i + 2 < length && num[i] + 1 == num[i + 1] && num[i] + 2 == num[i + 2]) {
                res.append(num[i]).append(" -> ");

                int j = i + 2;
                while (j + 1 < length && num[j] + 1 == num[j + 1]) {
                    j++;
                }
                res.append(num[j]).append(", ");
                i = j;
            } else if (i + 1 == length) {  // last element
                res.append(num[i]);
            } else { // single number
                res.append(num[i]).append(", ");
            }
        }
        return res.toString();
    }
    /*
     *  Since the array is already sorted and has no duplicates, we can solve this efficiently in one pass (O(n)).
     *
     * 	1.	Start a range with start = arr[0]
     * 	2.	Traverse the array from index 1
     * 	3.	If the current number is not consecutive:
     * 		(arr[i] != arr[i-1] + 1)
     * 			-	Close the previous range (start -> arr[i-1])
     * 			-	Start a new range from arr[i]
     * 	4.	After the loop, print the last range
     * */
    public static List<String> findRanges(int[] arr) {

        List<String> result = new ArrayList<>();

        if (arr == null || arr.length == 0) {
            return result;
        }

        int start = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // If current element is not consecutive
            if (arr[i] != arr[i - 1] + 1) {
                printRange(start, arr[i - 1], result);
                start = arr[i];
            }
        }

        // Print last range
        printRange(start, arr[arr.length - 1], result);
        return result;
    }

    private static void printRange(int start, int end,  List<String> result) {
        if (start == end) {
            result.add(start + ", ");
        } else {
            result.add(start + " -> " + end + ", ");
        }
    }

}
