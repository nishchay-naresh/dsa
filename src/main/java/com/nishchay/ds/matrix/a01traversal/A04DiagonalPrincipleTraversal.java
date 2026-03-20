package com.nishchay.ds.matrix.a01traversal;

/*
 *	=========== Diagonal Traverse ===========
 *
 * Given a 2D matrix, print its diagonal traversal.
 *
 *	For Example
 *
 *		For example, consider the following 5 X 5 input matrix.
 *
 *               {50 ,51 ,52 ,53, 54},
 *               {19 ,15 ,16 ,17, 18},
 *               {90 ,91 ,92 ,93, 94},
 *               {10 ,11 ,12 ,13, 14},
 *               {77 ,88 ,55 ,44, 99}
 *
 * 			Principal Diagonal & Upper Traversal is  - 50 15 92 13 99, 51 16 93 14, 52 17 94, 53 18, 54
 * 			Principal Diagonal & Lower Traversal is  - 50 15 92 13 99, 19 91 12 44, 90 11 55, 10 88, 77
 *
 * https://www.youtube.com/watch?v=lvRdFCMD_Ew
 *
 * */
public class A04DiagonalPrincipleTraversal {

    public static void main(String[] args) {

        int[][] m = {
                {50, 51, 52, 53, 54},
                {19, 15, 16, 17, 18},
                {90, 91, 92, 93, 94},
                {10, 11, 12, 13, 14},
                {77, 88, 55, 44, 99}
        };

        System.out.println("Original Matrix is  :-");
        printMatrix(m);

        System.out.print("\n Diagonal & Upper Traversal is  :- ");
        printDiagonalUpper(m);

        System.out.print("\n Diagonal & Lower Traversal is  :- ");
        printDiagonalBottom(m);

    }


    private static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
    }


    /*
     * The Principal diagonal and upper diagonal elements starting from principal
     *
     * For example, consider the following 4 X 4 input matrix.
     *
     *      A00 A01 A02 A03
     *      A10 A11 A12 A13
     *      A20 A21 A22 A23
     *      A30 A31 A32 A33
     *
     * Principal diagonal Traversal :  A00, A11, A22, A33, A01, A12, A23, A02, A13, A03
     *
     *  Diagonal 1 - A00, A11, A22, A33     Gap -0
     *  Diagonal 2 - A01, A12, A23          Gap -1
     *  Diagonal 3 - A02, A13               Gap -2
     *  Diagonal 4 - A33                    Gap -3
     *              this Gap : 0 to M(noOfRows)
     *                  i = 0   // starting value in all diagonal
     *                  j = gap // starting value in all diagonal
     *                  termination condition - j is not meeting right wall : j < N(noOfCols)
     *
     * */
    private static void printDiagonalUpper(int[][] matrix) {

        int M = matrix.length; // noOfRows
        int N = matrix[0].length; // noOfCols

        for (int gap = 0; gap < M; gap++) {
            for (int i = 0, j = gap; j < N; i++, j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }

    }


    /*
     * The Principal diagonal and bottom diagonal elements starting from principal
     *
     * For example, consider the following 4 X 4 input matrix.
     *
     *      A00 A01 A02 A03
     *      A10 A11 A12 A13
     *      A20 A21 A22 A23
     *      A30 A31 A32 A33
     *
     * Principal diagonal Traversal :  A00, A11, A22, A33, A10, A21, A32, A20, A31, A30
     *
     *  Diagonal 1 - A00, A11, A22, A33     Gap -0
     *  Diagonal 2 - A10, A21, A32          Gap -1
     *  Diagonal 3 - A20, A31               Gap -2
     *  Diagonal 4 - A30                    Gap -3
     *              this Gap : 0 to M(noOfRows)
     *                  i = gap   // starting value in all diagonal
     *                  j = 0 // starting value in all diagonal
     *                  termination condition - i is not meeting bottom wall : i< M(noOfRows)
     *
     *
     * */
    private static void printDiagonalBottom(int[][] matrix) {

        int M = matrix.length; // noOfRows
        int N = matrix[0].length; // noOfCols

        for (int gap = 0; gap < M; gap++) {
            for (int i = gap, j = 0; i < M; i++, j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }

    }

}
