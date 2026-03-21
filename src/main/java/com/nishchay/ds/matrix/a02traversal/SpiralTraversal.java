package com.nishchay.ds.matrix.a02traversal;

import java.util.Arrays;
import java.util.Stack;

/*
 *	====================== Print a given matrix in spiral form ========================
 *
 * Given a matrix mat[][] of size m x n, the task is to print all elements of the matrix in spiral form.
 *
 *	For Example
 *
 *		For example, consider the following 4 X 4 input matrix.
 *
 *		A00 A01 A02 A03
 *		A10 A11 A12 A13
 *		A20 A21 A22 A23
 *		A30 A31 A32 A33
 *		spiral matrix traversal  -  A00, A01, A02, A03, A13, A23, A33, A32, A31, A30, A20, A10, A11, A12, A22, A21,
 *
 *			Input:  1    2   3   4
 *			        5    6   7   8
 *			        9   10  11  12
 *			        13  14  15  16
 *			Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
 *			Explanation: The output is matrix in spiral format.
 *
 *			Input:  1   2   3   4  5   6
 *			        7   8   9  10  11  12
 *			        13  14  15 16  17  18
 *			Output: 1 2 3 4 5 6 12 18 17 16 15 14 13 7 8 9 10 11
 *			Explanation:The output is matrix in spiral format.
 *
 * https://www.geeksforgeeks.org/dsa/print-a-given-matrix-in-spiral-form/
 * https://leetcode.com/problems/spiral-matrix/description/
 * */
public class SpiralTraversal {

    public static void main(String[] args) {
        spiralEx();
        reverseSpiralEx();
    }

    private static void spiralEx() {
        int[][] mat = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println("Spiral Traversal is  = " + Arrays.toString(spiralTraversal(mat)));

        mat = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };

        System.out.println("Spiral Traversal is  = " + Arrays.toString(spiralTraversal(mat)));
    }

    /*
     *	matrix of = m X n, m=6, n=6
     *			01 02 03 04 05 06
     *			20 21 22 23 24 07
     *			19 32 33 34 25 08
     *			18 31 36 35 26 09
     *			17 30 29 28 27 10
     *			16 15 14 13 12 11
     *
     * For spiral traversal, there is a pattern
     *	right -> bottom -> left -> top
     *
     * top = 0, bottom = 5
     * left = 0, right = 5
     *
     * right:
     *      for(i : left -> right)
     *          m[top][i]
     *      top++
     * bottom:
     *      for(i : top -> bottom)
     *          m[i][right]
     *      right--
     * left:
     *      for(i : right -> left)
     *          m[bottom][i]
     *      bottom--
     * top :
     *      for(i : bottom -> top)
     *          m[i][left]
     *      left++
     *
     * Time Complexity: O(m*n). To traverse the matrix O(m*n) time is required.
     * Space Complexity: O(1). No extra space is required
     *
     * */
    private static int[] spiralTraversal(int[][] matrix) {

        int m = matrix.length; // noOfRows
        int n = matrix[0].length; // noOfCols
        int[] result = new int[m * n];
        int idx = 0;

        int left, right, top, bottom, dir;

        left = 0;
        right = n - 1;
        top = 0;
        bottom = m - 1;
        dir = 0; // starting condition is - left

        int i;
        while (top <= bottom && left <= right) {
            if (dir == 0) {
                // left to right - row
                for (i = left; i <= right; i++) {
                    result[idx++] = matrix[top][i];
                }
                top++;
                // top to bottom - col
            } else if (dir == 1) {
                for (i = top; i <= bottom; i++) {
                    result[idx++] = matrix[i][right];
                }
                right--;
                // right to left - row
            } else if (dir == 2) {
                for (i = right; i >= left; i--) {
                    result[idx++] = matrix[bottom][i];
                }
                bottom--;
                // bottom to top - col
            } else if (dir == 3) {
                for (i = bottom; i >= top; i--) {
                    result[idx++] = matrix[i][left];
                }
                left++;
            }
            dir = (dir + 1) % 4;
        }
        return result;
    }


    /*
     *  =========== Reverse spiral traversal of matrix ===========
     *
     * Input:
     *         1    2   3   4
     *         5    6   7   8
     *         9   10  11  12
     *         13  14  15  16
     *
     * Output:  10 11 7 6 5 9 13 14 15 16 12 8 4 3 2 1
     *
     * Input:
     *         1   2   3   4  5   6
     *         7   8   9  10  11  12
     *         13  14  15 16  17  18
     *
     * Output: 11 10 9 8 7 13 14 15 16 17 18 12 6 5 4 3 2 1
     *
     * https://www.geeksforgeeks.org/print-given-matrix-reverse-spiral-form/
     * https://www.geeksforgeeks.org/print-matrix-antispiral-form/
     * */

    private static void reverseSpiralEx() {

        int[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.print("Reverse Spiral Traversal is  :- ");
        antiSpiralTraversal(m);

        m = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };

        System.out.print("Reverse Spiral Traversal is  :- ");
        antiSpiralTraversal(m);

    }

    private static void antiSpiralTraversal(int[][] matrix) {

        int M = matrix.length; // noOfRows
        int N = matrix[0].length; // noOfCols

        Stack<Integer> stack = new Stack<>();

        int left, right, top, bottom, dir;

        left = 0;
        right = N - 1;
        top = 0;
        bottom = M - 1;
        dir = 0; // starting condition is - left

        int i;
        while (top <= bottom && left <= right) {
            if (dir == 0) {
                // left to right - row
                for (i = left; i <= right; i++)
                    stack.push(matrix[top][i]);
                top++;
                // top to bottom - col
            } else if (dir == 1) {
                for (i = top; i <= bottom; i++)
                    stack.push(matrix[i][right]);
                right--;
                // right to left - row
            } else if (dir == 2) {
                for (i = right; i >= left; i--)
                    stack.push(matrix[bottom][i]);
                bottom--;
                // bottom to top - col
            } else if (dir == 3) {
                for (i = bottom; i >= top; i--)
                    stack.push(matrix[i][left]);
                left++;
            }
            dir = (dir + 1) % 4;
        }

        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }

    }
}
