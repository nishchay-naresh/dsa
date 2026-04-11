package com.nishchay.algo.bsearch.a01basic;

/*
 *	=========== Implement Lower Bound and Upper Bound ===========
 *  Given a sorted array of nums and an integer x, write a program to find the lower bound of x.
 * The lower bound algorithm finds the first and smallest index in a sorted array where the value at that index is greater than or equal to a given key i.e. x.
 * If no such index is found, return the size of the array.
 *
 *  Given a sorted array arr[] and a number target, find the Lower Bound and upper bound of the target in this given array.
 *
 *	Lower Bound - smallest index such that arr[i] >= x
 *	Upper Bound - smallest index such that arr[i] < x
 *
 *	Lower Bound of X = The first index where the value is >= X.
 *	Upper Bound of X = The first index where the value is > X.
 *
 *  If there is no lower bound index is possible will return the last index of the array
 *
 * ---------------- Lower Bound------------------------
 * 	Examples:
 *				Input : nums= [1,2,2,3], x = 2
 *				Output:1
 *				Explanation:Index 1 is the smallest index such that arr[1] >= x.
 *
 *				Input : nums= [3,5,8,15,19], x = 9
 *				Output: 3
 *				Explanation:Index 3 is the smallest index such that arr[3] >= x.
 *
 *              Input: arr[] = [3, 5, 8, 15, 19]
 *                              0  1  2  3   4
 *              X = 8,
 *              lb = 2
 *
 *              X = 9,
 *              lb = 3
 *
 *              X = 16,
 *              lb = 4
 *
 *              X = 20,
 *              lb = 5
 *
 *              Input: arr[] = [3, 5, 8, 15, 19, 19, 19, 19]
 *              X = 19,
 *              lb = 4
 *
 * https://youtu.be/6zhGS79oQ4k?si=K-2aJ3MExImMPkvk
 * https://www.geeksforgeeks.org/cpp/binary-search-functions-in-c-stl-binary_search-lower_bound-and-upper_bound/
 * https://takeuforward.org/arrays/implement-lower-bound-bs-2/
 *
 * ---------------- Upper Bound------------------------
 *	Examples:
 *				Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 9
 *				Output: 3
 *				Explanation: 3 is the smallest index in arr[] at which element (arr[3] = 10) is larger than 9.
 *
 *				Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 11
 *				Output: 6
 *				Explanation: 6 is the smallest index in arr[] at which element (arr[6] = 25) is larger than 11.
 *
 *				Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 100
 *				Output: 7
 *				Explanation: As no element in arr[] is greater than 100, return the length of array.
 *
 *
 * https://takeuforward.org/arrays/implement-upper-bound/
 * https://www.geeksforgeeks.org/dsa/implement-upper-bound/
 * */
public class BS3LowerAndUpperBound {

    public static void main(String[] args) {

        int[] arr = {3, 5, 8, 15, 19};
        int x = 9;
        System.out.println("The lower bound is the index: " + lowerBound(arr, x));
        System.out.println("The upper bound is the index: " + upperBound(arr, x));
    }

    /*
     * =========== Finding Lower Bound using Binary Search Implementation ===========
     *
     * The implementation is similar to binary search
     * rather than searching for a key, we are looking for a possible lower bound
     *
     * Place the 2 pointers i.e. low and high:
     *      low = 0, high = n-1
     * Calculate the ‘mid’:
     *      mid = (low+high) / 2
     * Compare arr[mid] with x
     *      Case 1 - If arr[mid] >= x
     *          This condition means that the index mid may be an answer.
     *          So, we will update the ‘ans’ variable with mid and search in the left half if there is any smaller index that satisfies the same condition.
     *      Case 2 - If arr[mid] < x
     *          In this case, mid cannot be our answer, we need to find some bigger element.
     *           So, we will eliminate the left half and search in the right half for the answer.
     *
     * Function to find the lower bound index using binary search
     * */
     private static int lowerBound(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;               // Default value if not found

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= x) {
                ans = mid;            // Store possible answer
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private static int upperBound(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
