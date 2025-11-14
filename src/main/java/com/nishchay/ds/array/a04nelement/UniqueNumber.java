package com.nishchay.ds.array.a04nelement;

/*
 *  ======================= Unique Number ====================
 *
 *  Given an array of integers, every element in the array appears twice except for one element which appears only once.
 *  The task is to identify and return the element that occurs only once.
 *
 * Examples:
 *			Input:  arr[] = [2, 3, 5, 4, 5, 3, 4]
 *			Output: 2
 *			Explanation: Since 2 occurs once, while other numbers occur twice, 2 is the answer.
 *
 *			Input: arr[] = [2, 2, 5, 5, 20, 30, 30]
 *			Output: 20
 *			Explanation: Since 20 occurs once, while other numbers occur twice, 20 is the answer.
 *
 *			Input: arr[] = [4, 1, 2, 1, 2]
 *			Output: 4
 *			Explanation: Since 1 occurs once, while other numbers occur twice, 4 is the answer.
 *
 *			Input: arr[] = [1]
 *			Output: 1
 *			Explanation: Since 1 is the only element that occurs once, 1 is the answer.
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-element-appears-array-every-element-appears-twice/
 * https://leetcode.com/problems/single-number/description/
 * */
class UniqueNumber {

    public static void main(String[] args) {

        int[] arr = {2, 3, 5, 4, 5, 3, 4};
        System.out.println(getSingleElement(arr));  // 2
        System.out.println(findUnique(arr));        // 2

        arr = new int[]{2, 2, 5, 5, 20, 30, 30};
        System.out.println(findUnique(arr));        // 20

        arr = new int[]{4, 1, 2, 1, 2};
        System.out.println(findUnique(arr));        // 4

        arr = new int[]{1};
        System.out.println(findUnique(arr));        // 1

    }

    /*
     * ================ [Naive/Bruteforce Approach]  Nested Loop Frequency Counting using Linear Search - O(n^2) Time and O(1) Space =====================
     *
     *  iterates through the array and counts the frequency of each element using a nested loop.
     *      For each element, the inner loop counts how many times it appears in the array.
     *          If an element appears exactly once, it is returned as the result.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     * ================ [Better Approach] Using Hash Map - O(n) Time and O(n) Space ================
     * This approach uses a hash map (or dictionary) to track the frequency of each element in the array.
     *
     * Step by step approach:
     *  1.  Traverse all elements and insert them into a hash table. Element is used as key and the frequency is used as the value in the hash table.
     *  2.  Iterate through the map and return the value with count equal to 1.
     *
     *  Time Complexity     : O(n log n) + O(n/2+1)
     *  Space complexity    : O(n), ot better solution will have O(n/2+1)
     */
    private static int getSingleElement(int[] arr) {

        // Size of the array:
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int num = arr[i]; // selected element
            int cnt = 0;

            //find the occurrence using linear search:
            for (int j = 0; j < n; j++) {
                if (arr[j] == num)
                    cnt++;
            }

            // if the occurrence is 1 return ans:
            if (cnt == 1)
                return num;
        }
        return -1;
    }


    /*
     *  ================ [Optimize/Expected Approach] Using XOR Operation - O(n) Time and O(1) Space  =====================
     * XOR of a number with itself is 0 .       i.e. x ^ x = 0
     * And XOR of a number with 0 is number.    i.e. 0 ^ x = x
     *
     *      array   : 1, 2, 3, 4, 5
     *      array   : 1, 2,    4, 5     XOR
     *  --------------------------------
     *                 0, 0, 3, 0, 0
     *
     * XOR of two identical numbers cancels them out (results in zero),
     * So after XORing all the elements, only the element that appears once will remain.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */
    private static int findUnique(int[] arr) {
        int res = 0;

        // Find XOR of all elements
        for (int i = 0; i < arr.length; i++) {
            res = res ^ arr[i];
        }
        return res;
    }

}