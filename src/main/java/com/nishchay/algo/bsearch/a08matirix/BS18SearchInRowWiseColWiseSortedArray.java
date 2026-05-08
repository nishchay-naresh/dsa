package com.nishchay.algo.bsearch.a08matirix;

import com.nishchay.algo.bsearch.a01basic.BS01SearchAlgorithms;

/*
 *============== Search in Row wise And Column wise Sorted Array ====================
 *
 *
 * Given an m x n matrix and a key value, find the position of the key value in the matrix if it is present in it.
 * Otherwise, print “Not Found”. In the given matrix, every row and column is sorted in increasing order.
 *
 *  Examples:
 * 				Input: x = 62, mat[][] = [
 * 										[3, 30, 38],
 * 										[20, 52, 54],
 * 										[35, 60, 69]
 * 										]
 * 				Output: false
 * 				Explanation: 62 is not present in the matrix.
 *
 * 				Input: x = 55, mat[][] = [
 * 										[18, 21, 27],
 * 										[38, 55, 67]
 * 										]
 * 				Output: true
 * 				Explanation: mat[1][1] is equal to 55.
 *
 * 				Input: x = 35, mat[][] = [
 * 										[3, 30, 38],
 * 										[20, 52, 54],
 * 										[35, 60, 69]
 * 										]
 * 				Output: true
 * 				Explanation: mat[2][0] is equal to 35.
 *
 *	Example 1:
 *			Input: matrix[4][4] = {
 *                                {10, 20, 30, 40},
 *			                      {15, 25, 35, 45},
 *			                      {27, 29, 37, 48},
 *			                      {32, 33, 39, 50}
 *                          };
 *			              target = 29
 *			Output: Found at (2, 1)
 *			Explanation: Element at (2,1) is 29
 *
 *	Example 2:
 *			Input : matrix[4][4] = {
 *                                {3,  4,  7,  9},
 *			                      {12, 13, 16, 18},
 *			                      {20, 21, 23, 29},
 *			                     };
 *			              target = 23
 *			Output : Element not found
 *			Explanation: Element 100 is not found
 *
 *
 * https://www.callicoder.com/binary-search-row-wise-column-wise-sorted-matrix/
 * https://www.geeksforgeeks.org/dsa/search-in-row-wise-and-column-wise-sorted-matrix/
 * https://www.educative.io/edpresso/how-to-search-in-a-row-wise-and-column-wise-sorted-matrix
 * */
public class BS18SearchInRowWiseColWiseSortedArray {

    public static void main(String[] args) {

        leanerSearchEx();
        System.out.println("..................................");
        searchInSortedMatrixEx();
    }

    private static void leanerSearchEx() {

        int[][] mat = {
                {3, 30, 38},
                {20, 52, 54},
                {35, 60, 69}
        };
        int key = 35;

        System.out.println("matrixSearch(mat, 35) - " + matrixSearch(mat, key));
        System.out.println("matrixSearch(mat, 35) - " + matSearch(mat, key));
        key = 71;
        System.out.println("matrixSearch(mat, 71) - " + matrixSearch(mat, key));
        System.out.println("matrixSearch(mat, 71) - " + matSearch(mat, key));
    }

    /*
     * ================  [Naive Approach] Using Linear Search - O(n) Time and O(1) Space =====================
     *
     *  The simple idea is to traverse the complete matrix and search for the target element.
     *  If the target element is found, return true. Otherwise, return false.
     *
     *  Time Complexity     : O(n*m)
     *  Space complexity    : O(1)
     *
     */
    private static boolean matrixSearch(int[][] mat, int key) {
        int n = mat.length, m = mat[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == key)
                    return true;
            }
        }
        return false;
    }


    /*
     * ================  [Better Approach] Binary Search - O(n*log m) Time and O(1) Space =====================
     *
     * To optimize the above approach we are going to use the Binary Search algorithm.
     * The problem specifies that each row in the given matrix is sorted in ascending order.
     * Instead of searching each column sequentially, we can efficiently apply Binary Search on each row to determine if the target is present.
     *
     *
     *  Time Complexity     : O(n*log m)
     *  Space complexity    : O(1)
     */
    private static boolean matSearch(int[][] mat, int x) {
        // Iterate over each row and perform binary search
        for (int[] currRow : mat) {
            if (BS01SearchAlgorithms.binarySearch(currRow, x))
                return true;
        }
        return false;
    }

    private static void searchInSortedMatrixEx() {
        int[][] matrix = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50}
        };

        int key = 29;
        int[] result = searchInSortedMatrix(matrix, key);
        if (result[0] != -1 && result[1] != -1) {
            System.out.printf("%d is found at (%d, %d)%n", key, result[0], result[1]);
        } else {
            System.out.println("Element not found");
        }

        key = 100;
        result = searchInSortedMatrix(matrix, key);
        if (result[0] != -1 && result[1] != -1) {
            System.out.printf("%d is found at (%d, %d)%n", key, result[0], result[1]);
        } else {
            System.out.println("Element not found");
        }

        int[][] matrix1 =  {
                {3,  4,  7,  9},
                {12, 13, 16, 18},
                {20, 21, 23, 29},
        };
        key = 23;
        result = searchInSortedMatrix(matrix1, key);
        if (result[0] != -1 && result[1] != -1) {
            System.out.printf("%d is found at (%d, %d)%n", key, result[0], result[1]);
        } else {
            System.out.println("Element not found");
        }
    }

    /*
     * ================  [Expected Approach] Eliminating rows or columns - O(n + m) Time and O(1) Space =====================
     *	Matrix properties assumed:
     *		Each row is sorted left → right
     *		Each column is sorted top → bottom
     *
     *	Start from the top-right corner and move intelligently:
     *		If x is greater, move down
     *		If x is smaller, move left
     *
     *	This works because from the top-right:
     *		Everything to the left is smaller
     *		Everything below is bigger
     *
     *  We start from the top right corner of the matrix and compare its value with the key.
     *  If matrix[i][j] ==  key
     *      return (i,j)
     *  If matrix[i][j] >  key
     *      we move one position to the left.
     *  If matrix[i][j] <  key
     *      we move one position down.
     *
     *  Runtime Complexity : O(m+n) where ‘m’ is number of rows and ‘n’ is number of columns.
     *
     *  Time Complexity     : O(n+m)
     *  Space complexity    : O(1)
     *
     */
    private static int[] searchInSortedMatrix(int[][] matrix, int key) {

        if (matrix.length == 0) {
            return new int[]{-1, -1};
        }

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        int i = 0;
        int j = numCols - 1;
        while (i >= 0 && i < numRows && j >= 0 && j < numCols) {
            if (key == matrix[i][j]) {
                return new int[]{i, j};
            } else if (matrix[i][j] > key) {
                j--;  // search left
            } else {
                i++;  // search down.
            }
        }
        return new int[]{-1, -1};
    }

}
