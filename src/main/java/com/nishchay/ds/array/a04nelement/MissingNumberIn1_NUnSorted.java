package com.nishchay.ds.array.a04nelement;

/*
 *  ======================= Find the Missing Number ====================
 *
 *  Given an array arr[] of size n-1 with distinct integers in the range of [1, n].
 *  This array represents a permutation of the integers from 1 to n with one element missing.
 *  Find the missing element in the array.
 *
 * Examples:
 * 			Input: arr[] = [8, 2, 4, 5, 3, 7, 1]
 * 			Output: 6
 * 			Explanation: Here the size of the array is 7, so the range will be [1, 8]. All the numbers from 1 to 8 are present except 6.
 *
 * 			Input: arr[] = [1, 2, 3, 5]
 * 			Output: 4
 * 			Explanation: Here the size of the array is 4, so the range will be [1, 5]. The missing number between 1 to 5 is 4
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-the-missing-number/
 * https://www.youtube.com/watch?v=6KHW7ZQwtCA
 * */
class MissingNumberIn1_NUnSorted {

    public static void main(String[] args) {

        int[] arr1 = {10, 5, 9, 4, 2, 7, 8, 3, 1};
        System.out.println(getMissingNumber(arr1));         // 6
        System.out.println(getMissingNoHashMap(arr1));      // 6
        System.out.println(getMissingNoSum(arr1));          // 6
        System.out.println(getMissingNoXOR_2pass(arr1));    // 6
        System.out.println(getMissingNoXOR_1pass(arr1));    // 6

        int[] arr2 = {8, 2, 4, 6, 3, 7, 1};
        System.out.println(getMissingNoSum(arr2));          // 5

        int[] arr3 = {1, 2, 3, 5};
        System.out.println(getMissingNoXOR_1pass(arr3));    // 4
    }

    /*
     * ================ [Naive/Bruteforce Approach] Linear Search for Missing Number  =====================
     *
     *  iterates through each number from 1 to n+1 (where n is the size of the array) and checks if the number is present in the array.
     *  For each number, it uses a nested loop to search the array. If a number is not found, it is returned as the missing number.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int getMissingNumber(int[] arr) {
        int n = arr.length + 1;

        // Iterate from 1 to n and check - if the current number is present in array
        for (int i = 1; i <= n; i++) {
            boolean found = false;
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] == i) {
                    found = true;
                    break;
                }
            }
            // If the current number is not present
            if (!found)
                return i;
        }
        return -1;
    }

    /*
     *  ================ [Better Approach] Using Hashing - O(n) Time and O(n) Space  =====================
     * Method 1 - Hashing / freq count
     * Function to find missing number -  by first having the sum of First N Numbers
     *
     * Time Complexity  : O(n) + O(n) = O(2n)
     * Space Complexity : O(n)
     * */
    private static int getMissingNoHashMap(int[] arr) {

        int n = arr.length;
        // n + 1, because n nos are there in array and 1 is missing, so n+1
        // again we are not using index 0, so n + 2
        int freqArraySize = n + 2;
        boolean[] freq = new boolean[freqArraySize];// all cells are initialized with false

        for (int i = 0; i < n; i++) {
            freq[arr[i]] = true;
        }

        int res = -1;
        for (int i = 1; i < freqArraySize; i++) {
            if (freq[i] == false)
                res = i;
        }
        return res;
    }

    /*
     *  ================ [Optimize/Expected Approach] Using Sum of n terms Formula - O(n) Time and O(1) Space  =====================
     *
     * The sum of the first n natural numbers is given by the formula (n * (n + 1)) / 2.
     * The idea is to compute this sum and subtract the sum of all elements in the array from it to get the missing number.
     *
     * Function to find missing number -  by first having the sum of First N Numbers
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     * Problem - since we ae keeping the sum in single long variable, we can run in overflow issue for a bigger array
     * */
    private static int getMissingNoSum(int[] arr) {
        int n = arr.length;
        long expSum = (long) (n + 1) * (n + 2) / 2; // sum of First N Numbers

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Return the missing number
        return (int) (expSum - sum);
    }

    /*
     *  ================ [Optimize/Expected Approach] Using XOR Operation - O(n) Time and O(1) Space  =====================
     * XOR of a number with itself is 0 .       i.e. x ^ x = 0
     * And XOR of a number with 0 is number.    i.e. 0 ^ x = x
     *
     *      1-n     : 1, 2, 3, 4, 5
     *      array   : 1, 2,    4, 5     XOR
     *  --------------------------------
     *                 0, 0, 3, 0, 0
     *
     * The given array arr[] has numbers in range [1, n]
     * This means that the result of XOR of first n natural numbers with all the array elements will give the missing number.
     * (XOR of all array elements) XOR (XOR of (1 to n+1) natural nos) = missing no
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */
    private static int getMissingNoXOR_2pass(int[] arr) {

        int n = arr.length;
        int x1 = 0;
        // For xor of all the elements from 1 to n+1
        // n+1, bcus n nos are there in array and 1 is missing so n+1
        for (int i = 1; i <= n + 1; i++)  // loop : 1 - n+1
            x1 = x1 ^ i;

        int x2 = 0;
        // do a xor of each array element,
        for (int i = 0; i < n; i++) {    // loop : 0 - n-1
            x2 = x2 ^ arr[i];
        }

        return (x1 ^ x2);
    }

    /*
     *  ================ Further optimizing previous code to single pass - O(n) Time and O(1) Space  =====================
     *
     * In the above code, there are two loop, we can combine above two loop to one
     *      natural number loop,    loop1 : 1 - n+1
     *      array traversal loop,   loop2 : 0 - n-1
     *
     * we find the relationship between the above two loops can be combined like this:
     *          for (int i = 0; i < n; i++) {    // loop : 0 - n-1
     *              x1 = x1 ^ (i+1);
     *              x2 = x2 ^ arr[i];
     *           }
     *          x1 = x1 ^ n+1;
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */
    private static int getMissingNoXOR_1pass(int[] arr) {
        int n = arr.length;
        int x1 = 0;
        int x2 = 0;

        for (int i = 0; i < n; i++) {
            x1 = x1 ^ (i+1);
            x2 = x2 ^ arr[i];
        }
        x1 = x1 ^ n+1;
        return (x1 ^ x2);
    }
}