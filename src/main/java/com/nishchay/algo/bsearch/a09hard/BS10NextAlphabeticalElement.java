package com.nishchay.algo.bsearch.a09hard;

/*
 *============== Smallest alphabet greater than a given character ====================
 *
 *
 * Given a characters array letters that is sorted in non-decreasing order and a character key,
 * return the smallest character in the array that is larger than key.
 * For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
 *
 *	Example 1:
 *	    Input: letters = ["c","f","j"], key = "a"
 *	    Output: "c"
 *
 *	Example 2:
 *	    Input: letters = ["c","f","j"], key = "c"
 *	    Output: "f"
 *
 *	Example 3:
 *	    Input: letters = ["c","f","j"], key = "d"
 *	    Output: "f"
 *
 *
 * https://www.geeksforgeeks.org/smallest-alphabet-greater-than-a-given-character/
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 * */
public class BS10NextAlphabeticalElement {

    public static void main(String[] args) {

        char key;
        char[] arr;

        arr = new char[]{'a', 'b'};
        key = 'z';
        System.out.printf("Next Alphabetical Element of %c = %c%n", key, getNextChar(arr, key)); // a

        arr = new char[]{'c', 'f', 'j'};
        key = 'a';
        System.out.printf("Next Alphabetical Element of %c = %c%n", key, getNextChar(arr, key)); // c

        key = 'c';
        System.out.printf("Next Alphabetical Element of %c = %c%n", key, getNextChar(arr, key)); // f

        key = 'd';
        System.out.printf("Next Alphabetical Element of %c = %c%n", key, getNextChar(arr, key)); // f

        arr = new char[]{'D', 'J', 'K'};
        key = 'B';
        System.out.printf("Next Alphabetical Element of %c = %c%n", key, getNextChar(arr, key)); // D

        arr = new char[]{'h', 'n', 's'};
        key = 't';
        System.out.printf("Next Alphabetical Element of %c = %c%n", key, getNextChar(arr, key)); // h
    }

    /*
     *	if (arr[mid] > key) {
     *		nextChar = arr[mid];
     *		right = mid - 1;
     *	}
     *	else {
     *		left = mid + 1;
     *	}
     * */
    private static char getNextChar(char[] arr, char key) {

        if (key >= arr[arr.length - 1])
            return arr[0];

        int left = 0, right = arr.length - 1;
        int mid;
        char nextChar = ' ';

        while (left <= right) {

            mid = (left + right) / 2;

            if (arr[mid] > key) {
                nextChar = arr[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return nextChar;
    }

}
