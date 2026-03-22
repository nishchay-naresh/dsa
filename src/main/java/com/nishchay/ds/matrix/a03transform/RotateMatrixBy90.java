package com.nishchay.ds.matrix.a03transform;

/*
 *	====================== Rotate an Image 90 Degree Clockwise ========================
 *
 * Given a square matrix mat[][], turn it by 90 degrees in an clockwise direction without using any extra space
 * This can only be applicable for a square matrix
 *
 * Examples :
 *				Input: mat[][] = {{1, 2, 3},
 *				                  {4, 5, 6},
 *				                  {7, 8, 9}}
 *				Output:res[][] = {{7, 4, 1},
 *				        		  {8, 5, 2},
 *				        		  {9, 6, 3}}
 *
 *				Input: mat[][] = {{1, 2, 3, 4},
 *				                  {5, 6, 7, 8},
 *				                  {9, 10, 11,12}
 *				                  {13, 14, 15, 16}}
 *				Output:res[][] = {{13, 9, 5, 1},
 *				         		  {14, 10, 6, 2},
 *				         		  {15, 11, 7, 3},
 *				         		  {16, 12, 8, 4}
 *
 * https://www.geeksforgeeks.org/dsa/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/
 * https://leetcode.com/problems/rotate-image/description/
 *
 * */
public class RotateMatrixBy90 {

    public static void main(String[] args) {
        bruteforceEx();
        System.out.println("..................................................");
        transposeWayEx();
    }

    private static void bruteforceEx() {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6,},
                {7, 8, 9}
        };

        rotate90_tempMatrix(mat);
        System.out.println("Rotated matrix  = ");
        print(mat);

        mat = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        rotate90_tempMatrix(mat);
        System.out.println("Rotated matrix  = ");
        print(mat);
    }

    private static void transposeWayEx() {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6,},
                {7, 8, 9}
        };

        rotate90(mat);
        System.out.println("Rotated matrix  = ");
        print(mat);

        mat = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        rotate90(mat);
        System.out.println("Rotated matrix  = ");
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
     * ================ [Naive Approach] - O(n^2) Time and O(n^2) Space  =====================
     *
     * matrix of = m X n, m=4, n=4
     * original martix =>
     *                 1, 2, 3, 4
     *                 5, 6, 7, 8
     *                 9, 10, 11, 12
     *                 13, 14, 15, 16
     * rotated matrix =>
     * 				13, 9, 5, 1
     * 				14, 10, 6, 2
     * 				15, 11, 7, 3
     * 				16, 12, 8, 4
     * We need to create temp matrix, that where we will store the rotated matrix
     *
     * The pattern is :
     *      1st row -> is goes as last column
     *      2nd row -> is goes as 2nd last column
     *      3nd row -> is goes as 3rd last column
     *      4th row -> is goes as 4th last column
     *
     * Observe the index for the first row
     *      [0][0] -> [0][3]
     *      [0][1] -> [1][3]
     *      [0][2] -> [2][3]
     *      [0][3] -> [3][3]
     *
     * Observe the index for the second row
     *      [1][0] -> [0][2]
     *      [1][1] -> [1][2]
     *      [1][2] -> [2][2]
     *      [1][3] -> [3][2]
     *
     *  column is same, row is constant
     *      1st row : 0 -> 3
     *      2nd row : 1 -> 2
     *      3nd row : 2 -> 1
     *      4th row : 3 -> 0
     *   i -> (n-1)-i
     *
     * Do you see a pattern? Mainly we need to move mat[i][j] to mat[j][n-i-1].
     * We first move elements in a temporary matrix, then copy the temporary to the original.
     * If we directly copy elements within the matrix, it would cause data loss.
     *
     * Time Complexity: O(m*n). To traverse the matrix O(m*n) time is required.
     * Space Complexity: O(m*n).
     * */
    private static void rotate90_tempMatrix(int[][] matrix) {

        int n = matrix.length;

        // Initialize the result matrix with zeros
        int[][] res = new int[n][n];

        // Flip the matrix clockwise using nested loops
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[j][n - i - 1] = matrix[i][j];
            }
        }

        // Copy result back to original matrix
        for (int i = 0; i < n; i++) {
            System.arraycopy(res[i], 0, matrix[i], 0, n);
        }
    }


    /*
     * ================ [Expected Approach] Transposing and Reversing Rows - O(n^2) Time and O(1) Space  =====================
     *
     * Follow the given steps to solve the problem:
     * 	1.	Perform Transpose of the matrix
     * 	2.	Reverse every individual row of the matrix
     *
     * 1  2  3                  	1  4  7                         7  4  1
     *
     * 4  5  6   -—Transpose-->   	2  5  8     —-Reverse rows—->   8  5  2
     *
     * 7  8  9                  	3  6  9                         9  6  3
     *
     *
     * Time Complexity:  O(n^2)
     * Space Complexity: O(1).
     * */
    private static void rotate90(int[][] matrix) {
        int n = matrix.length;

        // Perform Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            int start = 0, end = n - 1;
            while (start < end) {
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
                start++;
                end--;
            }
        }
    }
}
