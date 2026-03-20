package com.nishchay.ds.matrix.a01traversal;

/*
 *	=========== Upper Triangular Matrix amd Lower Triangular Matrix of a sqare Matrix===========
 *
 * Given a 2D square matrix, print the Principal and Secondary diagonals.
 *
 *	For Example
 *	Input: mat[][] =   [
 *					        [1 ,2 ,3 ,4],
 *					        [4 ,3 ,2 ,1],
 *					        [7 ,8 ,9 ,6],
 *					        [6 ,5 ,4 ,3]
 *				        ]
 *		Output:
 *		Upper Triangular Matrix:
 *								1 2 3 4
 *								0 3 2 1
 *								0 0 9 6
 *								0 0 0 3
 *		Lower Triangular Matrix:
 *								1 0 0 0
 *								4 3 0 0
 *								7 8 9 0
 *								6 5 4 3
 *
 * NOTE - this Upper Triangular Matrix, or Lower Triangular Matrix concept is associated with the Principal Diagonal (row == column)
 *
 * https://www.geeksforgeeks.org/java-program-to-display-upper-triangular-matrix/
 * https://www.geeksforgeeks.org/java-program-to-display-lower-triangular-matrix/
 *
 * */
public class A03TriangularMatrix {

    public static void main(String[] args) {

        int[][] mat = new int[][]{
                {1 ,2 ,3 ,4},
                {4 ,3 ,2 ,1},
                {7 ,8 ,9 ,6},
                {6 ,5 ,4 ,3}
        };

        System.out.println("Original Matrix is  :-");
        print(mat);

        printUpperTriangularMatrix(mat);

       mat = new int[][]{
                {1 ,2 ,3 ,4},
                {4 ,3 ,2 ,1},
                {7 ,8 ,9 ,6},
                {6 ,5 ,4 ,3}
        };
        printLowerTriangularMatrix(mat);

    }

    /*
     *
     * For example, consider the following 4 X 4 input matrix.
     *
     *      A00 A01 A02 A03
     *      A10 A11 A12 A13
     *      A20 A21 A22 A23
     *      A30 A31 A32 A33
     *
     * Upper Triangular Matrix:
     *      						A00 A01 A02 A03
     *      						0   A11 A12 A13
     *      						0   0   A22 A23
     *      						0   0   0   A33
     * Condition for Upper Triangular Matrix:
     *				  if (i > j) {
     *                    matrix[i][j] = 0;
     *                }
     *
     * Lower Triangular Matrix:
     *      						A00 0   0   0
     *      						A10 A11 0   0
     *      						A20 A21 A22 0
     *      						A30 A31 A32 A33
     * Condition for Lower Triangular Matrix:
     *				  if (i < j) {
     *                    matrix[i][j] = 0;
     *                }
     * */

    private static void printUpperTriangularMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // if the number of rows and columns are not equal,then return
        if (row != col) {
            System.out.println("Matrix should be a Square Matrix");
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (i > j) {
                        matrix[i][j] = 0;
                    }
                }
            }

            System.out.println("Upper Triangular Matrix is  :-");
            print(matrix);
        }
    }

    private static void printLowerTriangularMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // if the number of rows and columns are not equal,then return
        if (row != col) {
            System.out.println("Matrix should be a Square Matrix");
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (i < j) {
                        matrix[i][j] = 0;
                    }
                }
            }

            System.out.println("Lower Triangular Matrix is  :-");
            print(matrix);
        }
    }

    private static void print(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
