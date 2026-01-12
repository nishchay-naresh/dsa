package com.nishchay.old;

import java.util.Arrays;
import java.util.Random;

/*
 *	=========== Fisher–Yates shuffle Algorithm ===========
 *
 * Picks up a random song from the playlist and plays it.
 *
 * There is a audio player given which picks up a random song from the playlist and plays it.
 * The song should not be repeated until all songs are played atleast once.The sequence number of the songs are given in an array.
 * Design such audio player without using any extra space.
 *
 *
 *========================
 * Given an array, write a program to generate a random permutation of array elements.
 * This question is also asked as “shuffle a deck of cards” or “randomize a given array”.
 * Here shuffle means that every permutation of array element should equally likely.
 *
 *
 *
 * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 * https://www.geeksforgeeks.org/dsa/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
 * */
public class SongShuffle {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        shuffle(arr);

    }


    /*
     *
     *ALGORITHM -
     *To shuffle an array a of n elements (indices 0..n-1):
     *	for i from n - 1 to 1 do
     *		j = random integer with 0 <= j <= i
     *		exchange a[j] and a[i]
     *
     * Time Complexity  - O(n)
     * */
    private static void shuffle(int[] arr) {
        // Creating a object for Random class
        Random random = new Random();

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = arr.length - 1; i > 0; i--) {

            // Pick a random index from 0 to i - assuming it working O(1)
            int j = random.nextInt(i);

            // Swap arr[i] with the element at random index
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            System.out.println(arr[i]);
        }
        // Prints the random array
        System.out.println(Arrays.toString(arr));
    }

}
