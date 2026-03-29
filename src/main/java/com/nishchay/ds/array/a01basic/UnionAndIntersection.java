package com.nishchay.ds.array.a01basic;

import java.util.*;

/*
 *  ======================= Union and Intersection of Two Sorted Arrays ====================
 *
 * ----------- Union of Two Sorted Arrays ------------------
 *
 * Union of two sorted arrays combines all unique elements from both arrays into a single sorted array.
 *
 *  We are given two sorted arrays arr1[] and arr2[].
 *  The task is to return union of both the arrays in sorted order.
 *  Union of two arrays is an array having all distinct elements that are present in either array.
 *  The input arrays may contain duplicates.
 *
 *
 * Examples:
 *		Input: arr1[] = {1, 1, 2, 3, 4, 5}, arr2[] = {2, 3, 4, 4, 5, 6}
 *		Output: {1, 2, 3, 4, 5, 6}
 *		Explanation: 1, 2, 3, 4, 5, 6 are the distinct elements present in either array.
 *
 *		Input: arr1[] = {1, 1, 2, 2, 2, 4}, arr2[] = {2, 2, 4, 4}
 *		Output: {1, 2, 4}
 *		Explanation: 1, 2 and 4 are the distinct elements present in either array.
 *
 *		Input: arr1[] = {3, 5, 10, 10, 10, 15, 15, 20}, arr2[] = {5, 10, 10, 15, 30}
 *		Output: {3, 5, 10, 15, 20, 30}
 *		Explanation: 3, 5, 10, 15, 20 and 30 are the distinct elements present in either array.
 *
 *
 * ----------- Intersection of Two Sorted Arrays ------------------
 *
 *  Intersection of two sorted arrays combines all unique elements that are common to both arrays into a single sorted array.
 *
 *  We are given two sorted arrays arr1[] and arr2[].
 *  The task is to return intersection of both the arrays in sorted order.
 *  Intersection of two arrays is an array having all common elements in both the arrays.
 *  The input arrays may contain duplicates.
 *
 * Examples:
 *
 *		Input: arr1[] = {1, 2, 2, 3, 3, 4, 5, 6}, arr2[] = {2, 3, 3, 5, 6, 6, 7}
 *		Output: {2, 3, 3, 5, 6}
 *		Explanation: 2, 3, 3, 5, 6 are the common elements present in either array.
 *
 * 		Input: arr1[] = {1, 1, 2, 2, 2, 4}, arr2[] = {2, 2, 4, 4}
 * 		Output: {2, 2, 4}
 * 		Explanation: 2 and 4 are only common elements in both the arrays.
 *
 * 		Input: arr1[] = {1, 2}, arr2[] = {3, 4}
 * 		Output: {}
 * 		Explanation: No common elements.
 *
 *
 * https://www.geeksforgeeks.org/dsa/union-and-intersection-of-two-sorted-arrays-2/
 * https://www.geeksforgeeks.org/dsa/intersection-of-two-sorted-arrays/
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * */
public class UnionAndIntersection {

    public static void main(String[] args) {
        unionExecution();
        System.out.println("----------------------------------------------------------------");
        intersectionExecution();
    }

    private static void unionExecution() {
        int[] arr1 = {1, 2, 2, 3, 4};
        int[] arr2 = {2, 2, 4, 5, 6};
        System.out.println(Arrays.toString(findUnion(arr1, arr2))); // [1, 2, 3, 4, 5, 6]

        arr1 = new int[]{1, 1, 2, 3, 4, 5};
        arr2 = new int[]{2, 3, 4, 4, 5, 6};
        System.out.println(Arrays.toString(findUnion(arr1, arr2))); // [1, 2, 3, 4, 5, 6]

        arr1 = new int[]{1, 1, 2, 2, 2, 4};
        arr2 = new int[]{2, 2, 4, 4};
        System.out.println(Arrays.toString(findUnion(arr1, arr2))); // [1, 2, 4]

        arr1 = new int[]{3, 5, 10, 10, 10, 15, 15, 20};
        arr2 = new int[]{5, 10, 10, 15, 30};
        System.out.println(Arrays.toString(findUnion(arr1, arr2))); // [3, 5, 10, 15, 20, 30]
    }

    /*
     * ================ [Expected Approach] Two Pointer approach =====================
     *
     * 	Since both arrays are sorted, we can use the two-pointer technique (similar to merge step in merge sort).
     * 	Use pointers i and j for both arrays.
     * 	Move through both arrays:
     * 		If elements are equal, → add one of them to result and move both.
     * 		If arr1[i] < arr2[j] → add arr1[i] and move i.
     * 		If arr1[i] > arr2[j] → add arr2[j] and move j.
     *
     *  Time Complexity     :  O(n1 + n2) — each array is traversed once.
     *  Space complexity    :  O(n1 + n2) (for the result array)
     */
    private static int[] findUnion(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        int n = arr1.length;
        int m = arr2.length;

        while (i < n && j < m) {
            // Skip duplicates within the same array
            if (i > 0 && arr1[i] == arr1[i - 1]) {
                i++;
                continue;
            }
            if (j > 0 && arr2[j] == arr2[j - 1]) {
                j++;
                continue;
            }

            if (arr1[i] < arr2[j]) {
                result.add(arr1[i++]);
            } else if (arr2[j] < arr1[i]) {
                result.add(arr2[j++]);
            } else { // Equal elements
                result.add(arr1[i]);
                i++;
                j++;
            }
        }

        // Add remaining elements
        while (i < n) {
            if (i == 0 || arr1[i] != arr1[i - 1]) {
                result.add(arr1[i]);
            }
            i++;
        }

        while (j < m) {
            if (j == 0 || arr2[j] != arr2[j - 1]) {
                result.add(arr2[j]);
            }
            j++;
        }

        // Convert to int[]
        return result.stream().mapToInt(Integer::intValue).toArray();
    }


    private static void intersectionExecution() {
        int[] arr1 = {1, 2, 2, 3, 3, 4, 5, 6};
        int[] arr2 = {2, 3, 3, 5, 6, 6, 7};
        System.out.println(Arrays.toString(findIntersectionUsingSet(arr1, arr2))); // [2, 3, 3, 5, 6]

        arr1 = new int[]{1, 1, 2, 2, 2, 4};
        arr2 = new int[]{2, 2, 4, 4};
        System.out.println(Arrays.toString(findIntersectionUsingSet(arr1, arr2))); // [2, 2, 4]

        arr1 = new int[]{1, 2};
        arr2 = new int[]{3, 4};
        System.out.println(Arrays.toString(findIntersectionUsingSet(arr1, arr2))); // [ ]

        arr1 = new int[]{3, 5, 10, 10, 10, 15, 15, 20};
        arr2 = new int[]{5, 10, 10, 15, 30};
        System.out.println(Arrays.toString(findIntersectionUsingSet(arr1, arr2))); // [5, 10, 10, 15]

        arr1 = new int[]{1, 2, 2, 1};
        arr2 = new int[]{2, 2};
        System.out.println(Arrays.toString(findIntersectionUsing2Pointers(arr1, arr2))); // [2]
    }

    /*
     * ================ [Expected Approach] Using a HashSet =====================
     *  Code for Unsorted Arrays
     *  The simplest way is to use a HashSet to track elements and find common values.
     *
     *  Why Set?
     *      Removes duplicates automatically, Fast lookup → O(1)
     *
     *  Time Complexity     :  O(n1 + n2) — each array is traversed once.
     *  Space complexity    :  O(min(n1, n2)) (for the result array)
     */
    private static int[] findIntersectionUsingSet(int[] arr1, int[] arr2) {
        // Put all elements of arr1 into a set
        Set<Integer> set1 = new HashSet<>();
        for (int num : arr1) {
            set1.add(num);
        }

        // Check arr2 against set1
        Set<Integer> resultSet = new HashSet<>();
        for (int num : arr2) {
            if (set1.contains(num)) {
                resultSet.add(num); // store intersection
            }
        }

        // Step 3: convert set to array
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            result[i] = num;
            i++;
        }
        return result;
    }

    /*
     * ================ [Expected Approach] Two Pointers after Sorting =====================
     *  First sort both of the arrays
     *
     * 	Since both arrays are sorted:
     * 	Use two pointers i and j.
     *
     * 	Compare elements:
     * 		If arr1[i] < arr2[j], increment i.
     * 		If arr1[i] > arr2[j], increment j.
     * 		If both are equal → store element and increment both.
     *
     *  Time Complexity     :   O(n1 + n2) — each array is traversed once.
     *  Space complexity    :  O(min(n1,n2)) (for the result array)
     */
    private static int[] findIntersectionUsing2Pointers(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int n1 = arr1.length;
        int n2 = arr2.length;
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                // Equal elements found - find the partner
                result.add(arr1[i]);
                i++;
                j++;
            }
        }

        // Convert to int[]
        return result.stream().distinct().mapToInt(Integer::intValue).toArray();
    }
}

