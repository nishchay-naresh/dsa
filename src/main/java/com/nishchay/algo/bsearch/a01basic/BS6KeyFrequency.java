package com.nishchay.algo.bsearch.a01basic;

/*
 *============== Count number of occurrences (or frequency) in a sorted array ====================
 *
 * Given a sorted array arr[] and a number key.
 * Write a function that counts the occurrences of key in arr[]. Expected time complexity is O(log n)
 *
 *
 * Examples:
 *				Input : arr[] = {1, 3, 5, 5, 5, 5, 6, 6, 7, 123, 125}
 *				key = 5
 *				Output : 4
 *
 *				Input : arr[] = {1, 3, 5, 5, 5, 5, 6, 6, 7, 123, 125}
 *				key = 7
 *				Output : 1
 *
 *				Input : arr[] = {2, 2, 5, 5, 6, 6, 8, 9, 9, 9}
 *				key = 9
 *				Output : 3
 *
 *				Input : arr[] = {2, 2, 5, 5, 6, 6, 8, 9, 9, 9}
 *				key = 2
 *				Output : 2
 *
 *				Input : arr[] = {2, 2, 5, 5, 6, 6, 8, 9, 9, 9}
 *				key = 4
 *				Output : 0
 *
 *				Input : arr[] = {101,101}
 *				key = 101
 *				Output : 2
 *
 * https://www.geeksforgeeks.org/count-number-of-occurrences-or-frequency-in-a-sorted-array/
 * */
public class BS6KeyFrequency {

    public static void main(String[] args) {

        bruteForceApproach();
        System.out.printf("---------------------------%n");
        binarySearchApproach();

    }

    private static void bruteForceApproach() {

        int[] arr = {1, 3, 5, 5, 5, 5, 6, 6, 7, 123, 125};

        int key = 5;
        System.out.printf("key occurrence = %d%n", keyOccurrences(arr, key));

        key = 7;
        System.out.printf("key occurrence = %d%n", keyOccurrences(arr, key));

        arr = new int[]{2, 2, 5, 5, 6, 6, 8, 9, 9, 9};

        key = 9;
        System.out.printf("key occurrence = %d%n", keyOccurrences(arr, key));

        key = 2;
        System.out.printf("key occurrence = %d%n", keyOccurrences(arr, key));

        key = 4;
        System.out.printf("key occurrence = %d%n", keyOccurrences(arr, key));

        arr = new int[]{101, 101};
        key = 101;
        System.out.printf("key occurrence = %d%n", keyOccurrences(arr, key));
    }

    /*
     * ===========Brute force / Linear Search approach==========
     *
     *  The Naive Approach is to run a for loop and check given elements in an array.
     *  And returns number of times x occurs in arr[0..n-1]
     *
     * Time Complexity  - O(n)
     * Space Complexity - O(1)
     *
     * */
    private static int keyOccurrences(int[] arr, int key) {
        int freq = 0;
        for (int curr : arr)
            if (key == curr)
                freq++;
        return freq;
    }


    private static void binarySearchApproach() {

        int[] arr = {1, 3, 5, 5, 5, 5, 6, 6, 7, 123, 125};

        int key = 5;
        System.out.printf("key occurrence = %d%n", countOccurrences(arr, key));

        key = 7;
        System.out.printf("key occurrence = %d%n", countOccurrences(arr, key));

        arr = new int[]{2, 2, 5, 5, 6, 6, 8, 9, 9, 9};

        key = 9;
        System.out.printf("key occurrence = %d%n", countOccurrences(arr, key));

        key = 2;
        System.out.printf("key occurrence = %d%n", countOccurrences(arr, key));

        key = 4;
        System.out.printf("key occurrence = %d%n", countOccurrences(arr, key));

        arr = new int[]{101, 101};
        key = 101;
        System.out.printf("key occurrence = %d%n", countOccurrences(arr, key));
    }

    /*
     * ===========Best using Improved Binary Search==========
     *
     *	1) Use Binary search to get index of the first occurrence of key. - firstIndex
     *	2) Use Binary search to get index of the last occurrence of key -  lastIndex
     *	3) Return (lastIndex - startIndex + 1);
     *
     * Time Complexity: O(log n)
     * Programming Paradigm: Divide & Conquer
     * */
    private static int countOccurrences(int[] arr, int key) {
        int first = BS5FirstLastOccurrenceOfKey.search(arr, key, true);
        if (first == -1)
            return 0;

        int last = BS5FirstLastOccurrenceOfKey.search(arr, key, false);
        return (last - first + 1);
    }
}
/*
 * O/P =>
 * key occurrence = 4
 * key occurrence = 1
 * key occurrence = 3
 * key occurrence = 2
 * key occurrence = 0
 * key occurrence = 2
 * ---------------------------
 * key occurrence = 4
 * key occurrence = 1
 * key occurrence = 3
 * key occurrence = 2
 * key occurrence = 0
 * key occurrence = 2
 * */