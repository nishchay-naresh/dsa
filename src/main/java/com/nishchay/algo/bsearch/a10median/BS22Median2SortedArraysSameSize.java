package com.nishchay.algo.bsearch.a10median;

import java.util.Arrays;

/*
 * =============================== Median of two sorted arrays of same size ===================================
 *
 * Given 2 sorted arrays a[] and b[], each of size n, the task is to find the median of the array obtained after merging a[] and b[].
 *
 * Note: Since the size of the merged array will always be even, the median will be the average of the middle two numbers.
 *
 * Examples:
 *				Input: a[] = [1, 12, 15, 26, 38], b[] = [2, 13, 17, 30, 45]
 *				Output: 16
 *				Explanation: The merged sorted array is [1, 2, 12, 13, 15, 17, 26, 30, 38, 45]. The middle two elements are 15 and 17, so median = (15 + 17)/2 = 16
 *
 *				Input: a[] = [10], b[] = [21]
 *				Output : 15.5
 *				Explanation: The merged sorted array is [10, 21]. The middle two elements are 10 and 21, so median = (10 + 21)/2 = 15.5
 *
 *
 * https://www.geeksforgeeks.org/dsa/median-of-two-sorted-arrays/
 * https://www.geeksforgeeks.org/dsa/median-of-two-sorted-arrays-of-different-sizes/#expected-approach-using-binary-search-ologminn-m-time-and-o1-space
 *
 * */
public class BS22Median2SortedArraysSameSize {
    public static void main(String[] args) {

        int[] a = new int[]{1, 12, 15, 26, 38};
        int[] b = new int[]{2, 13, 17, 30, 45};

        System.out.println("getMedian_sort(a, b)    = " + getMedian_sort(a, b));
        System.out.println("getMedian_merging(a, b) = " + getMedian_merging(a, b));

        //  arr1 - {1,5,7,12} , arr3 - {6, 9, 15, 17} => merged -{1,5,6,7,9,12,15,19}
        a = new int[]{1, 5, 7, 9};
        b = new int[]{6, 12, 15, 17};
        System.out.println("getMedian_merging(a, b) = " + getMedian_merging(a, b));

    }

    /*
     * ================ [Naive Approach] Using Sorting - O(n * log n) Time and O(n) Space  =====================
     *  Note: Since the size of the merged array will always be even, the median will be the average of the middle two numbers.
     *
     *  The idea is to concatenate both the arrays into a new array, sort the new array and return the middle of the new sorted array.
     *
     *  Time Complexity: O((2n) * log(2n)), where n is the size of array a[] and b[].
     *  Auxiliary Space: O(2n), because we are creating a new merged array of size 2n.
     */
    private static double getMedian_sort(int[] a, int[] b) {

        // Concatenate the two arrays
        int[] merged  = new int[a.length + b.length];
        System.arraycopy(a, 0, merged , 0, a.length);
        System.arraycopy(b, 0, merged , a.length, b.length);

        // Sort the concatenated array
        Arrays.sort(merged );

        // Calculate and return the median
        int n = merged .length;
        int mid1 = n / 2;
        int mid2 = mid1 - 1;
        return (merged [mid1] + merged [mid2]) / 2.0;
    }

    /*
     * ================ [Naive Approach] Using Merging of 2 sorted array logics, but not storing the result   =====================
     * ------- concatenate both the arrays then sort --------
     * In above solution not utilizing the fact that - both the arrays are sorted
     *      =>  Simply concatenating both of the arrays together, then sorted them
     *
     * ------- Using Merging of 2 sorted array logics --------
     *  1.  Merge both arrays into one sorted array - applying logic of merging of two sorted array
     *  2.  Pick middle elements
     *      Time Complexity     : O(n)+O(n), Merge
     *      Space Complexity    : O(n), Storing merge sorted version
     *
     * Why This is NOT Optimal
     * Even though O(n) is good, we can do better:
     * We are:
     *          Creating a new array ❌
     *          Processing ALL elements ❌
     *
     * ================ [Better Approach] Using Merging of 2 sorted array logics, but not storing the result   =====================
     *  Key Observation (This leads to optimal solution)
     *      To find median, we ONLY need:
     *                      mid and mid-1 th elements
     *                      We DO NOT need entire merged array
     *
     * Since the given arrays are sorted.
     * The idea is to apply merging of 2 sorted array logics, but not storing the result merged array.
     * ex:   arr1 - {1,5,7,12} , arr3 - {6, 9, 15, 17} => merged - {1, 5, 6, 7, 9, 12, 15, 19}
     *      Out of all element we only need (7,9), => (merged[3],merged[4]) => (merged[n/2-1],merged[n/2])
     *
     * we only need to merge till n/2, fetched last two element from merged so far. No need to process complete array
     *
     *  Time Complexity: O(n), only integrating  n+n/2 = n
     *  Auxiliary Space: O(1), As no additional space is used.
     */
    private static double getMedian_merging(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int i = 0, j = 0;
        int count;

        // m1 to store element at index n of merged array
        // m2 to store element at index (n - 1) of merged array
        int m1 = -1, m2 = -1;

        // Loop till n
        // keeping the current process element in m2,
        // always copy last process value in m1 when going for next iteration
        for (count = 0; count <= n; count++) {
            m1 = m2;

            // If both the arrays have remaining elements
            if (i < n && j < n) {
                if (arr1[i] > arr2[j])
                    m2 = arr2[j++];
                else
                    m2 = arr1[i++];
            }

            // If only arr1 has remaining elements
            else if (i < n)
                m2 = arr1[i++];
            // If only arr2 has remaining elements
            else if (j < n)
                m2 = arr2[j++];
        }

        return (m1 + m2) / 2.0;
    }


    /*
     * ================ [Expected/Optimize Approach] Using Binary Search - O(log min(n, m)) Time and O(1) Space   =====================
     * Even in optimized brute, We still:
     *      -   Traverse elements sequentially]
     *      -   Compare one-by-one
     * Why We Need O(log n), Because:
     *      -   Arrays are already sorted
     *      -   We should use binary search
     *      -   Jump instead of scanning
     *
     * --------- partition logic --------------
     * We partition both arrays such that:
     *      -   Left half has n elements
     *      -   Right half has n elements
     *      -   All left elements ≤ all right elements
     *
     *
     * TODO - https://youtu.be/F9c7LpRZWVQ?si=Q6z7vmVjNqDKyavC
     */
    private static double getMedian_binarySearch(int[] a, int[] b) {
        int n = a.length, m = b.length;

        // if a[] has more elements, then call medianOf2 with reversed parameters
        if (n > m)
            return getMedian_binarySearch(b, a);

        int lo = 0, hi = n;
        while (lo <= hi) {
            int mid1 = (lo + hi) / 2;
            int mid2 = (n + m + 1) / 2 - mid1;

            // find elements to the left and right of partition in a[]
            int l1 = (mid1 == 0) ?
                    Integer.MIN_VALUE : a[mid1 - 1];
            int r1 = (mid1 == n) ?
                    Integer.MAX_VALUE : a[mid1];

            // find elements to the left and right of partition in b[]
            int l2 = (mid2 == 0) ?
                    Integer.MIN_VALUE : b[mid2 - 1];
            int r2 = (mid2 == m) ?
                    Integer.MAX_VALUE : b[mid2];

            // if it is a valid partition
            if (l1 <= r2 && l2 <= r1) {

                // if the total elements are even, then median is the average of two middle elements
                if ((n + m) % 2 == 0)
                    return (Math.max(l1, l2)+Math.min(r1, r2))/2.0;

                    // if the total elements are odd, then median is the middle element
                else
                    return Math.max(l1, l2);
            }

            // check if we need to take fewer elements from a[]
            if (l1 > r2)
                hi = mid1 - 1;
                // check if we need to take more elements from a[]
            else
                lo = mid1 + 1;
        }
        return 0;
    }

}
