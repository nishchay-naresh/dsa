package com.nishchay.ds.matrix.a02traversal;

/*
 *	=========== Print a given matrix rows in zigzag form ===========
 * Zig-Zag / Snake Traversal
 *
 * Row-wise alternating traversal
 * 	→ → →
 * 	← ← ←
 * 	→ → →
 *
 * Given a 2D array(non square), print it's rows in zigzag form.
 *
 *	Example :
 *
 *      M X N : 4 X 5
 *
 *      A00 A01 A02 A03 A04 ->  odd
 *      A10 A11 A12 A13 A14 ->  even
 *      A20 A21 A22 A23 A24 ->  odd
 *      A30 A31 A32 A33 A34 ->  even
 *
 *		printing diagonally strip wise ( bottom to up ) :
 *			A00 A01 A02 A03 A04, A14 A13 A12 A11 A10, A20 A21 A22 A23 A24, A34 A33 A32 A31 A30
 *
 *  	strip 1 - A00 A01 A02 A03 A04
 *  	strip 2 - A14 A13 A12 A11 A10
 *  	strip 3 - A20 A21 A22 A23 A24
 *  	strip 4 - A34 A33 A32 A31 A30
 *
 *
 *			Input:
 * 					50 51 52 53 54
 * 					19 15 16 17 18
 * 					90 91 92 93 94
 * 					77 88 55 44 99
 *
 *			Diagonal printing of the above matrix is :
 *			50 51 52 53 54
 *			18 17 16 15 19
 *			90 91 92 93 94
 *			99 44 55 88 77
 *
 *          50 51 52 53 54 18 17 16 15 19 90 91 92 93 94 99 44 55 88 77
 *
 * https://www.geeksforgeeks.org/dsa/print-given-matrix-zigzag-form/
 *
 * */
public class RowZigZag {

    public static void main(String[] args) {

        int[][] mat = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };
        System.out.println("\nRow wise ZigZag :-");
        printRowZigZag(mat);

        mat = new int[][]{
                {50, 51, 52, 53, 54},
                {19, 15, 16, 17, 18},
                {90, 91, 92, 93, 94},
                {77, 88, 55, 44, 99}
        };
        System.out.println("\nRow wise ZigZag :-");
        printRowZigZag(mat);
    }

    /*
    * Logic will be very simple
    *
    * Print all rows (0-m)
    *   odd row     : right to left
    *   even row    : left to right
    *
    * Time Complexity = Time complexity of the above solution is O(row*column).
    * Space Complexity =  O(1)
    * */
    private static void printRowZigZag(int[][] matrix) {
        int row = matrix.length; // noOfRows
        int col = matrix[0].length; // noOfCols

        for (int i = 0; i < row; i++) {
            if (i % 2 == 0) { // left to right
                for (int j = 0; j < col; j++) {
                    System.out.print(matrix[i][j] + "\t");
                }
            } else { // right to left
                for (int j = col-1; j >= 0; j--) {
                    System.out.print(matrix[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }


}
/*
 * o/p =>
 *	Row wise ZigZag :-
 *	1	2	3	4	5
 *	10	9	8	7	6
 *	11	12	13	14	15
 *
 *	Row wise ZigZag :-
 *	50	51	52	53	54
 *	18	17	16	15	19
 *	90	91	92	93	94
 *	99	44	55	88	77
 * */