package com.nishchay.algo.bsearch.a03medium;

/*
 *  ======================= Single Element in a Sorted Array ====================
 *
 *  Given a sorted array arr[] where every element appears exactly twice except one element that appears only once, find that single element.
 *
 * Examples:
 *				Input: arr[] = [1, 1, 2, 2, 3, 4, 4]
 *				Output: 3
 *				Explanation: All numbers except 3 occur twice in the array.
 *
 *				Input: arr[] = [1, 1, 3, 3, 4, 4, 5, 5, 7, 7, 8]
 *				Output: 8
 *				Explanation: All numbers except 8 occur twice in the array.
 *
 *
 * https://takeuforward.org/data-structure/search-single-element-in-a-sorted-array/
 * https://www.geeksforgeeks.org/dsa/find-the-element-that-appears-once-in-a-sorted-array/
 * */
class SingleElementInASortedArray {

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 3, 4, 4};
        System.out.println("findSingleElement(arr)      - " + findSingleElement(arr));                   // 3
        System.out.println("findSingleElement_xor(arr)  - " + findSingleElement_xor(arr));           // 3
        System.out.println("singleNonDuplicate(arr)     - " + singleNonDuplicate(arr));                 // 3

        arr = new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        System.out.println("findSingleElement(arr)      - " + findSingleElement(arr));                   // 4
        System.out.println("findSingleElement_xor(arr)  - " + findSingleElement_xor(arr));           // 4
        System.out.println("singleNonDuplicate(arr)     - " + singleNonDuplicate(arr));                 // 3

        arr = new int[]{1, 1, 3, 3, 4, 4, 5, 5, 7, 7, 8};
        System.out.println("findSingleElement(arr)      - " + findSingleElement(arr));                   // 8
        System.out.println("findSingleElement_xor(arr)  - " + findSingleElement_xor(arr));           // 8
        System.out.println("singleNonDuplicate(arr)     - " + singleNonDuplicate(arr));                 // 3
    }

    /*
     * ================ [Naive/Bruteforce Approach] Linear Search for Missing Number  =====================
     *
     *  The idea behind this approach is to check every adjacent element to find if there exists its pair or not as the array is sorted.
     *  -   The single element must appear at an odd position (or even index) because every other element appears twice.
     *  -   One by one check all odd postilions (or even indexes) and if we find an element which is different from next, we return the element.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static int findSingleElement(int[] arr) {
        int n = arr.length;

        // Since array index stars with 0 and every other element appears twice,
        // we will check on index like - 0,2,4,6,8---
        for (int i = 0; i < n - 1; i += 2) {
            if (arr[i] != arr[i + 1]) {
                return arr[i];
            }
        }

        // If no element found, the single element must be the last one
        return arr[n - 1];
    }

    /*
     *  ================ [Naive Approach 2] Using Bitwise XOR - O(n) Time and O(1) Space  =====================
     *
     * The idea behind this approach is to use bitwise XOR between pair of elements, as XOR of two same numbers is always equal to 0.
     *
     * We can use the properties of XOR (a ^ a = 0 & a ^ 0 = a) to find the element that occurs once.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     * A XOR based function to find the element that appears only once
     * */
    private static int findSingleElement_xor(int[] arr) {
        int xor = 0;
        for (int curr : arr) {
            xor = xor ^ curr;
        }
        return xor;
    }

    /*
     *  ================ [Expected Approach] Using Binary Search - O(log n) Time and O(1) Space:  =====================
     *
     * The idea is to use Binary Search. Below is an observation on the input array.
     * All elements before the element that occurs once have the first occurrence at even index (0, 2, ..) and the next occurrence at odd index (1, 3, ...).
     *      pair - (even, odd) , first occurrence at even index. Its pair will be at odd index
     *           after adding the -  element that occurs once in array
     *      pair - (odd, even) , first occurrence at odd index, and its pair will be at even index
     *
     * Intuition:
     * 		In a properly paired array:
     * 			index: 0 1 2 3 4 5 6 7 ...
     * 			value: 4 4 6 6 8 8 9 9 ...
     *
     * 		Pairs always start at even indices:
     * 			(0,1), (2,3), (4,5) ...
     *
     * 		Now imagine the single element appears somewhere:
     * 			index: 0 1 2 3 4 5 6
     * 			Value: 1 1 2 3 3 4 4
     * 		                   ↑
     * 		                 single
     *
     * 		After the single element:
     * 		The pairing battens breaks, Pairs shift by one index
     * 		we will use this index pattern in binary search
     *
     *      Set low = 0, high = arr.length - 1
     *      while(low < high)
     * 			- Always shift mid to eve index
     * 			- if arr[mid] == arr[mid + 1]
     * 				Pair is correct -> single element is on the right side
     * 				low = mid + 2
     * 		    - if arr[mid] != arr[mid + 1]
     * 				Pair is broken -> single element is on the left or at mid
     * 				high = mid
     *
     *
     * Time Complexity  : O(log n)
     * Space Complexity : O(1)
     * */
    private static int singleNonDuplicate(int[] arr) {

        int low = 0, high = arr.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            // ensures mid always points to even index (the first element of a pair)
            if (mid % 2 == 1) {
                mid--;
            }
            // If a valid pair exists at mid, single element is on the right
            if (arr[mid] == arr[mid + 1]) {
                low = mid + 2;
                // If invalid pair exists at mid single element is on the left or at mid
            } else {
                high = mid;
            }
        }
        return arr[low];
    }

}