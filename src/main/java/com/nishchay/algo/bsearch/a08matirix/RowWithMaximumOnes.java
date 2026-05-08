package com.nishchay.algo.bsearch.a08matirix;

/*
 *============== Find the row with maximum number of 1s ====================
 *
 *
 * Given a binary 2D array, where each row is sorted. Find the row with the maximum number of ones.
 * These 2D arrays only have 0 or 1, noting else
 *
 * In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should be selected.
 * Return an array containing the index of the row, and the number of ones in it.
 *
 *	Examples :
 *				Input matrix :          0 1 1 1
 *				                        0 0 1 1
 *				                        1 1 1 1
 *				                        0 0 0 0
 *				Output: 2
 *				Explanation: Row = 2 has maximum number of ones, that is 4.
 *
 *				Input matrix :          0 0 1 1
 *				                        0 1 1 1
 *				                        0 0 1 1
 *				                        0 0 0 0
 *				Output: 1
 *				Explanation: Row = 1 has maximum number of ones, that is 3.
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-the-row-with-maximum-number-1s/
 * https://takeuforward.org/arrays/find-the-row-with-maximum-number-of-1s/
 * https://leetcode.com/problems/row-with-maximum-ones/description/
 * */
public class RowWithMaximumOnes {

    public static void main(String[] args) {


        int[][] mat = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        int R = 4, C = 4;

        System.out.println("Index of row with maximum 1s is " + rowWithMax1s(mat, R, C));
        System.out.println("Index of row with maximum 1s is " + rowWithMax1sBS(mat,mat.length, mat[0].length));
    }

    /*
     * ================  [Naive Approach] Using Linear Search - O(n) Time and O(1) Space =====================
     *
     *  A simple approach is to iterate through the array and keep track of the maximum element occurred so far.
     *  Once the traversal is complete, return the maximum element.
     *
     *  Time Complexity     : O(R X C)
     *  Space complexity    : O(1)
     *
     * Function that returns index of row with maximum number of 1 s.
     */
    private static int rowWithMax1s(int[][] mat, int R, int C) {
        // Flag to check if there is not even a single 1 in the matrix.
        boolean flag = true;
        // Initialize max values
        int max_row_index = 0, maxCount = 0;

        // Traverse for each row and count number of ones
        for (int i = 0; i < R; i++) {

            int currCount = 0;
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == 1) {
                    currCount++;
                    flag = false;
                }
            }
            if (currCount > maxCount) {
                maxCount = currCount;
                max_row_index = i;
            }

        }
        // Edge case where there are no 1 in the matrix
        if (flag) {
            return -1;
        }

        return max_row_index;
    }

    /*
     *  ================ [Expected Approach] Using Binary Search - O(log n) Time and O(1) Space:  =====================
     *
     * Optimizing the prev solution, we can't optimize the row traversal, but we can optimize the column traversal
     * because each row is sorted, so we can apply binary search here
     *
     * We can solve this by any of these solutions:
     *  - lower bound of (1)
     *  - upper bound of (0)
     *  - first occurrence of 1
     *
     *
     *
     * Time Complexity  : O(n X logm), where n = given row number, m = given column number. We are using a loop running for n times to traverse the rows. Then we are applying binary search on each row with m columns.
     * Space Complexity : O(1)
     * Function to find the lower bound (first index where value >= x)
     * */
    private static int rowWithMax1sBS(int[][] matrix, int n, int m) {
        int cnt_max = 0;  // Keeps track of the maximum number of 1s found so far
        int index = -1;   // Stores index of the row with maximum 1s

        // Iterate over all rows
        for (int i = 0; i < n; i++) {
            // Count of 1s = total columns - index of first 1 (lower bound)
            int cnt_ones = m - lowerBound(matrix[i], m, 1);
            if (cnt_ones > cnt_max) {
                cnt_max = cnt_ones;
                index = i;
            }
        }
        return index;  // Return row index with maximum 1s
    }

    private static int lowerBound(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n;  // Default value if no such index is found

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ans = mid;        // Possible answer found
                high = mid - 1;   // Try to find a smaller index
            } else {
                low = mid + 1;    // Move right
            }
        }
        return ans;  // Index of first element >= x
    }


}
