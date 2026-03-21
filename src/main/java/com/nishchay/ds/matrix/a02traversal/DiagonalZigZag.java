package com.nishchay.ds.matrix.a02traversal;

/*
 *	=========== Print matrix in diagonal zigzag pattern ===========
 * LeetCode: 498
 * Pattern: row + col parity
 *
 * Given a matrix of n*n size, the task is to print its elements in a diagonal pattern.
 *
 *	For Example
 *
 *      M X N : 5 X 5
 *
 *      A00 A01 A02 A03 A04
 *      A10 A11 A12 A13 A14
 *      A20 A21 A22 A23 A24
 *      A30 A31 A32 A33 A34
 *		A40 A41 A42 A43 A44
 *
 *		printing diagonally strip wise ( bottom to up ) :
 *			A00, A01 A10, A20 A11 A02, A03 A12 A21 A30, A40 A31 A22 A13 A04, A14 A23 A32 A41, A42 A33 A24, A34 A43, A44
 *
 *      No of strip = M + N -1 = 5 + 5 -1 = 9
 *
 *  	strip 1 - A00
 *  	strip 2 - A01, A10
 *  	strip 3 - A20, A11, A02
 *  	strip 4 - A03, A12, A21, A30
 *  	strip 5 - A40, A31, A22, A13, A04
 *  	strip 6 - A14, A23, A32, A41
 *  	strip 7 - A42, A33, A24
 *  	strip 8 - A34, A43
 *  	strip 9 - A44
 *
 *
 *
 *
 *			Input:
 *					50 51 52 53 54
 *					19 15 16 17 18
 *					90 91 92 93 94
 *					10 11 12 13 14
 *					77 88 55 44 99
 *
 *			Diagonal printing of the above matrix is :
 *			50
 *			51 19
 *			90 15 52
 *			53 16 91 10
 *			77 11 92 17 54
 *			18 93 12 88
 *			55 13 94
 *			14 44
 *			99
 *
 * https://www.geeksforgeeks.org/print-matrix-diagonal-pattern/
 * https://leetcode.com/problems/diagonal-traverse/description/
 *
 * */
public class DiagonalZigZag {


    public static void main(String[] args) {

        int[][] m = {
                {50, 51, 52, 53, 54},
                {19, 15, 16, 17, 18},
                {90, 91, 92, 93, 94},
                {10, 11, 12, 13, 14},
                {77, 88, 55, 44, 99}
        };

        System.out.println("ZigZag diagonals are  :-");
        int[] res = findDiagonalOrder(m);
        for (int x : res) {
            System.out.print(x + " ");
        }

        System.out.println("\n--------------------------------------");
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        res = findDiagonalOrder(mat);
        for (int x : res) {
            System.out.print(x + " ");
        }

    }

    /*
     * Diagonal alternate direction:
     *
     *	upward   		|	downward
     *		r--; c++ 	|		r++; c--
     *	if(r>=m || r<0)	|	if(r>=m || r<0)
     *	  c++			|		c++
     *	if(c>=n || c<0)	|	if(c>=n || c<0)
     *	 r++			|		r++
     *
     *
     * Time Complexity = O(m × n)
     * Space Complexity =  O(1)
     * */
    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0)
            return new int[0];

        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];

        int r = 0, c = 0, idx = 0;
        boolean up = true; // since the first move is upward

        while (idx < m * n) {
            result[idx++] = mat[r][c];

            if (up) {
                if (c == n - 1) {        // hit right wall
                    r++;
                    up = false;
                } else if (r == 0) {     // hit top wall
                    c++;
                    up = false;
                } else {
                    r--;
                    c++;
                }
            } else {
                if (r == m - 1) {        // hit bottom wall
                    c++;
                    up = true;
                } else if (c == 0) {     // hit left wall
                    r++;
                    up = true;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return result;
    }

}
