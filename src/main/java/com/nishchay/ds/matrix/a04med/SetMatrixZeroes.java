package com.nishchay.ds.matrix.a04med;



/*
* ================================= Set Matrix Rows and Columns to Zeroes ====================================
*
 * Given a matrix mat[][] of size nxm, the task is to update the matrix such that if an element is zero, set its entire row and column to zeroes.
 *
 * Examples:
 * 				Input: mat[][] = [[1, -1, 1],
 * 				                  [-1, 0, 1],
 * 				                  [1, -1, 1]]
 *
 * 				Output: res[][] =[[1, 0, 1],
 * 				                  [0, 0, 0],
 * 				                  [1, 0, 1]]
 * 				Explanation: mat[1][1] = 0, so all elements in row 1 and column 1 are updated to zeroes.
 *
 * 				Input: mat[][] = [[0, 1, 2, 0],
 * 				                  [3, 4, 0, 2],
 * 				                  [1, 3, 1, 5]]
 * 				Output: res[][] =[[0, 0, 0, 0],
 * 				                  [0, 0, 0, 0],
 * 				                  [0, 3, 0, 0]]
 * 				Explanation: mat[0][0], mat[1][2] and mat[0][3] are 0s,
 *              so all elements in row 0, row 1, column 0, column 2 and column 3 are updated to zeroes.
 *
 *
 *
 * https://www.geeksforgeeks.org/dsa/set-matrix-rows-and-columns-to-zeroes/
 * https://leetcode.com/problems/set-matrix-zeroes/description/
 * https://youtu.be/N0MgLvceX7M?si=ExtigckeRAKj-abW
 *
* */
public class SetMatrixZeroes {

    public static void main(String[] args) {

        int[][] mat = {
                { 0, 1, 2, 0 },
                { 3, 4, 0, 2 },
                { 1, 3, 1, 5 }
        };

        setMatrixZeroes(mat);

        print(mat);
    }

    private static void print(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    /*
     * ================ [Naive Approach] Using Two Auxiliary Arrays - O(n*m) Time and O(n+m) Space  =====================
     *
     *  The idea is to maintain two additional arrays, say rows[] and cols[] to store the rows and columns which contains at least one element equal to 0
     *  So, first traverse the entire matrix and for each mat[i][j] = 0, mark rows[i] = true and cols[j] = true.
     *  Now in the second traversal, for each cell (i, j), if either rows[i] or cols[j] is marked as true, update mat[i][j] = 0 else continue to the next cell.
     *
     * Time complexity: O(n * m) + O(n * m) = O (2*(n * m)) =  O(n * m)
     * Space complexity: O(n+m)
     *
     * */
    private static void setMatrixZeroes(int[][] mat) {

        int n = mat.length, m = mat[0].length;

        // To store which rows and columns are supposed to mark with zeroes
        boolean[] rows = new boolean[n];
        boolean[] cols = new boolean[m];


        // Traverse the matrix to fill rows[] and cols[]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        // Set matrix elements to zero if they belong to a marked row or column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // Mark cell (i, j) with zero if either of rows[i] or cols[j] is true
                if (rows[i] || cols[j] ) {
                    mat[i][j] = 0;
                }
            }
        }
    }


    /*
     *  ================ [Optimized Approach] Using First Row and Column itself to mark rows and coln for zeros- O(n*m) Time and O(1) Space  =====================
     *
     * Optimized approach - Using First Row and Column itself to mark rows and coln for zeros
     * O(n*m) Time and O(1) Space
     * Time complexity: O(n * m) + O(n * m) = O (2*(n * m)) =  O(n * m)
     * Space complexity: O(n) + O(m)
     *
     * */
    private static void setMatrixZeroes_optimized(int[][] matrix) {

        int n = matrix.length, m = matrix[0].length;
        int c0 = 1;

        // Traverse the arr and mark first cell of each row and column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {

                    // mark i-th row
                    matrix[i][0] = 0;

                    // mark j-th column
                    if (j == 0)
                        c0 = 0;
                    else
                        matrix[0][j] = 0;
                }
            }
        }

        // Traverse and mark the matrix from
        // (1, 1) to (n - 1, m - 1)
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {

                // Check for col & row
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Mark the first row
        if (matrix[0][0] == 0) {
            for (int j = 0; j < m; j++)
                matrix[0][j] = 0;
        }

        // Mark the first column
        if (c0 == 0) {
            for (int i = 0; i < n; i++)
                matrix[i][0] = 0;
        }
    }

}
