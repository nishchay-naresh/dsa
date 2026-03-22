package com.nishchay.ds.matrix.a03transform;


/*
 *====================================== Program to find transpose of a matrix ==================================================
 * Given a 2D matrix mat[][], compute its transpose.
 * The transpose of a matrix is formed by converting all rows of mat[][] into columns and all columns into rows.
 *
 * Examples:
 *				Input: mat[][] = [[1, 1, 1, 1],
 *				                  [2, 2, 2, 2],
 *				                  [3, 3, 3, 3],
 *				                  [4, 4, 4, 4]]
 *				Output:res[][] = [[1, 2, 3 ,4],
 *				                  [1, 2, 3, 4],
 *				                  [1, 2, 3, 4],
 *				                  [1, 2, 3, 4]]
 *				Explanation:  The output is the transpose of the input matrix, where each row becomes a column.
 *				This rearranges the data so that vertical patterns in the original matrix become horizontal in the result.
 *
 *				Input: mat[][] = [[1, 2],
 *				                  [9, -2]]
 *				Output:res[][] = [[1, 9],
 *				                 [2, -2]]
 *				Explanation:  The output is the transpose of the input matrix, where each row becomes a column.
 *				This rearranges the data so that vertical patterns in the original matrix become horizontal in the result.
 *
 * https://www.geeksforgeeks.org/dsa/program-to-find-transpose-of-a-matrix/
 * */

public class MatrixTranspose {

    public static void main(String[] args) {

        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.print("\n----------original matrix-----------");
        print(mat);
        int[][] t = transpose(mat);
        System.out.println("\n----------transpose matrix-----------");
        print1(t);


        int[][] matrix = {
                {2, 5, 6},
                {8, 9, 3},
                {1, 9, 7}
        };
        System.out.print("\n----------original matrix-----------");
        print(matrix);
        transpose_m(matrix);
        System.out.println("\n----------transpose matrix-----------");
        print1(matrix);

    }

    private static void print1(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    private static void print(int[][] mat) {
        int m = mat.length;     // noOfRows
        int n = mat[0].length;  // noOfCols
        for (int i = 0; i < m; i++) {
            System.out.print("\n");
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + "\t");
            }
        }
    }

    /*
     * What is Matrix Transpose?
     *      The transpose of a matrix is obtained by converting rows into columns.
     *      Swaps mat[i][j] with mat[j][i].
     *      Pattern: result[j][i] = mat[i][j]
     *
     * Transpose of Any Matrix (m × n)
     *
     * Case	Method
     *  Rectangular matrix	->  Extra matrix required
     *  Square matrix	    ->  In-place possible
     *
     * */
    private static int[][] transpose(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = mat[i][j];
            }
        }
        return result;
    }

    /*
     * In-Place Transpose (ONLY for Square Matrix n × n)
     *
     * */
    private static void transpose_m(int[][] mat) {
        int n = mat.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }
}
