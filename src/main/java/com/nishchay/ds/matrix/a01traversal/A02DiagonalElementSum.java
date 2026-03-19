package com.nishchay.ds.matrix.a01traversal;

/*
 *	=========== Efficiently compute sums of diagonals of a matrix ===========
 *
 * Given a 2D square matrix, find the sum of elements in Principal and Secondary diagonals.
 *
 *	For Example
 *
 *		For example, consider the following 4 X 4 input matrix.
 *
 *		A00 A01 A02 A03
 *		A10 A11 A12 A13
 *		A20 A21 A22 A23
 *		A30 A31 A32 A33
 *		The Primary diagonal   - A00, A11, A22, A33
 *		The Secondary Diagonal - A03, A12, A21, A30
 *
 *
 *		Principal Diagonal sum: A00 + A11 + A22 + A33
 *		Secondary Diagonal sum: A03 + A12 + A21 + A30
 *
 *
 * 	Input :
 * 			4
 * 				1 2 3 4
 * 				4 3 2 1
 * 				7 8 9 6
 * 				6 5 4 3
 * 	Output :
 * 			Principal Diagonal: 16
 * 			Secondary Diagonal: 20
 *
 * https://www.geeksforgeeks.org/efficiently-compute-sums-of-diagonals-of-a-matrix/
 *
 * */
public class A02DiagonalElementSum {

    public static void main(String[] args) {

        int n = 4;
        int[][] mat = {
                {1, 2, 3, 4},
                {4, 3, 2, 1},
                {7, 8, 9, 6},
                {6, 5, 4, 3}
        };

        System.out.println("-------------O(n^2)---------");
        printDiagonalSums(mat, n);
        System.out.println("-------------O(n)---------");
        System.out.println("diagonalSum(mat) = " + diagonalSum(mat));;
    }

    /*
     * Method 1 (O(n ^ 2) :
     *
     *	Time Complexity: O(n^2)
     *	Auxiliary Space: O(1)
     * */
    private static void printDiagonalSums(int[][] mat, int n) {

        int principal = 0, secondary = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Condition for principal diagonal
                if (i == j)
                    principal += mat[i][j];

                // Condition for secondary diagonal
                if ((i + j) == (n - 1))
                    secondary += mat[i][j];
            }
        }

        System.out.println("Principal Diagonal:" + principal);
        System.out.println("Secondary Diagonal:" + secondary);
    }

    /*
     *	 -------------O(n^2)---------
     *	  if (i == j)
     *		principal += mat[i][j];
     *
     *	  if ((i + j) == (n - 1))       // j = n - i - 1
     *		secondary += mat[i][j];
     *
     *	 -------------O(n)---------
     *	 principal += mat[i][i];
     *	 secondary += mat[i][n - i - 1];
     *
     *  if matrix size is odd - subtract the middle element once (  bus that is common in both diagonal )
     *
     * */

    private static int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += mat[i][i];                 // primary diagonal
            sum += mat[i][n - 1 - i];         // secondary diagonal
        }

        // If n is odd, subtract the middle element once
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }
        return sum;
    }

}
