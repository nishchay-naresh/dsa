package com.nishchay.ds.matrix.a01traversal;

/*
 *	=========== diagonal traversal of Matrix ===========
 *
 * Given a 2D matrix(non-square), print all elements of the given matrix in diagonal order(bottom-up).
 *
 *	For Example
 *
 *      M X N : 4 X 5
 *
 *      A00 A01 A02 A03 A04
 *      A10 A11 A12 A13 A14
 *      A20 A21 A22 A23 A24
 *      A30 A31 A32 A33 A34
 *
 *		printing diagonally strip wise ( bottom to up ) :
 *			A00, A10 A01, A20 A11 A02, A30 A21 A12 A03, A31 A22 A13 A04, A32 A23 A14, A33 A24, A34
 *
 *      No of strip = M + N -1 = 4 + 5 -1 = 8
 *
 *  	strip 1 - A00
 *  	strip 2 - A10, A01
 *  	strip 3 - A20, A11, A02
 *  	strip 4 - A30, A21, A12, A03
 *  	strip 5 - A31, A22, A13, A04
 *  	strip 6 - A32, A23, A14
 *  	strip 7 - A33, A24
 *  	strip 8 - A34
 *
 *
 *
 *			Input:
 *					1     2     3     4
 *					5     6     7     8
 *					9     10    11    12
 *					13    14    15    16
 *					17    18    19    20

 *			Diagonal printing of the above matrix is :
 *			1
 *			5 2
 *			9 6 3
 *			13 10 7 4
 *			17 14 11 8
 *			18 15 12
 *			19 16
 *			20
 *
 *
 * https://www.geeksforgeeks.org/zigzag-or-diagonal-traversal-of-matrix/
 * https://www.youtube.com/watch?v=IDxaZrrggu0
 * https://www.ideserve.co.in/learn/print-matrix-diagonally
 *
 * */
public class DiagonalStripTraversal {

    public static void main(String[] args) {

        int[][] matrix = {
                {50, 51, 52, 53, 54},
                {19, 15, 16, 17, 18},
                {90, 91, 92, 93, 94},
                {10, 11, 12, 13, 14}
        };

        System.out.println("Diagonal(bottom-up) Traversal is  :- ");
        diagonals_bottomUup(matrix);

        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20}
        };

        System.out.println("Diagonal(bottom-up) Traversal is  :- ");
        diagonals_bottomUup(matrix1);
//        printMatrixDiagonally(matrix1);

    }


    /*
     *      M X N : 4 X 5
     *
     *      A00 A01 A02 A03 A04
     *      A10 A11 A12 A13 A14
     *      A20 A21 A22 A23 A24
     *      A30 A31 A32 A33 A34
     *
     *      No of strip = M + N -1 = 4 + 5 -1 = 8
     *
     *  strip 1 - A00
     *  strip 2 - A10, A01
     *  strip 3 - A20, A11, A02
     *  strip 4 - A30, A21, A12, A03
     *  strip 5 - A31, A22, A13, A04
     *  strip 6 - A32, A23, A14
     *  strip 7 - A33, A24
     *  strip 8 - A34
     *
     *              1st iteration - for all the rows : k = 0 to M-1
     *                  i = k, i-- // since we have to move up , so always i--, j++
     *                  j = 0, j++
     *                  termination condition - i is not meeting 0 : i >= 0
     *
     *              2nd iteration - for all the columns ( exclude the first columns) : k = 1 to N-1
     *                  i = M - 1, i-- // since we have to move up , so always i--, j++
     *                  j = k, j++
     *                  termination condition - j is not meeting N-1 : i >= 0
     *
     * */

    private static void diagonals_bottomUup(int[][] matrix) {
        int M = matrix.length; // noOfRows
        int N = matrix[0].length; // noOfCols

        int i,j;
        for (int k = 0; k <= M - 1; k++) {
            for (i = k, j = 0; i >= 0 && j < N; i--, j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        for (int k = 1; k <= N - 1; k++) {
            for (i = M - 1, j = k; i >= 0 && j < N; i--, j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


}
/*
 * O/P =>
 *	Diagonal(bottom-up) Traversal is  :-
 *	50
 *	19 51
 *	90 15 52
 *	10 91 16 53
 *	11 92 17 54
 *	12 93 18
 *	13 94
 *	14
 *	Diagonal(bottom-up) Traversal is  :-
 *	1
 *	5 2
 *	9 6 3
 *	13 10 7 4
 *	17 14 11 8
 *	18 15 12
 *	19 16
 *	20
 *
 * */