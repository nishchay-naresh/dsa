package com.nishchay.algo.bsearch.a10median;


/*
 * =================================== Median of two Sorted Arrays of Different Sizes =======================================
 *

 * Given two sorted arrays, a[] and b[], find the median of these sorted arrays.
 * Assume that the two sorted arrays are merged and then median is selected from the combined array.
 *
 * This is an extension of Median of two sorted arrays of equal size problem.
 * BS22Median2SortedArraysSameSize.java - Here we handle arrays of unequal size also.
 *
 * Examples:
 * 				Input: a[] = [-5, 3, 6, 12, 15], b[] = [-12, -10, -6, -3, 4, 10]
 * 				Output: 3
 * 				Explanation: The merged array is [-12, -10, -6, -5 , -3, 3, 4, 6, 10, 12, 15]. So the median of the merged array is 3.
 *
 * 				Input: a[] = [1], b[] = [2, 4, 5, 6, 7]
 * 				Output: 4.5
 * 				Explanation: The merged array is [1, 2, 4, 5, 6, 7].
 * 				The total number of elements are even, so there are two middle elements. Take the average between the two: (4 + 5) / 2 = 4.5
 *
 *
 * https://www.geeksforgeeks.org/dsa/median-of-two-sorted-arrays-of-different-sizes/
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * */
public class BS23Median2SortedArraysDiffSize {

    public static void main(String[] args) {

        int[] arr1 = new int[]{-5, 3, 6, 12, 15};
        int[] arr2 = new int[]{-12, -10, -6, -3, 4, 10};
        System.out.println(medianOf2(arr1, arr2));

        arr1 = new int[]{1};
        arr2 = new int[]{2, 4, 5, 6, 7};

        System.out.println(medianOf2(arr1, arr2));
    }

    /*
     *
     * ================ [Better Approach] Using Merging of 2 sorted array logics, but not storing the result   =====================
     * Use Merge of Merge Sort - O(m + n) Time and O(1) Space
     * [Better Approach] Use Merge of Merge Sort - O(m + n) Time and O(1) Space
     *
     * The idea is to simulate the merging process of two sorted arrays without actually creating a new one.
     * By iterating through both arrays together until reaching the middle index, the algorithm keeps track of the last two selected elements.
     *
     *  Time Complexity: O(n), only integrating  n+n/2 = n
     *  Auxiliary Space: O(1), As no additional space is used.
     */
    static double medianOf2(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int i = 0, j = 0;

        // m1 to store the middle element
        // m2 to store the second middle element
        int m1 = -1, m2 = -1;

        // loop till (m + n)/2
        for (int count = 0; count <= (m + n) / 2; count++) {
            m1 = m2;

            // if both the arrays have remaining elements
            if (i != n && j != m)
                m2 = (a[i] > b[j]) ? b[j++] : a[i++];

                // if only a[] has remaining elements
            else if (i < n)
                m2 = a[i++];

                // if only b[] has remaining elements
            else
                m2 = b[j++];
        }

        // return median based on odd/even size
        if ((m + n) % 2 == 1)
            return m1;
        else
            return (m1 + m2) / 2.0;
    }
}
