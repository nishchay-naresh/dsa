package com.nishchay.ds.matrix.a04med;

/*
 *	=========== Search element in a sorted matrix ===========
 *
 * Given a sorted matrix mat[n][m](non square) and an element ‘x’. Find the position of x in the matrix if it is present, else print -1.
 * Matrix is sorted in a way such that all elements in a row are sorted in increasing order
 * Such that for row ‘i’, where 1 <= i <= n-1, the first element of row ‘i’ is greater than or equal to the last element of row ‘i-1’.
 *
 * ie each row elements are sorted in asc order
 * and last element of ith row <= first element of i+1th row
 *
 *	We are given a 2D array where all elements in any individual row or column are sorted.
 * 	In such a matrix, we have to search or find the position of, a given key.
 *
 *	For Example
 *		Input : mat[][] = { {1, 5, 9},
 *		                    {14, 20, 21},
 *		                    {30, 34, 43} }
 *		        x = 14
 *		Output : Found at (1, 0)
 *
 *		Input : mat[][] = { {1, 5, 9, 11},
 *		                    {14, 20, 21, 26},
 *		                    {30, 34, 43, 50} }
 *		        x = 42
 *		Output : -1
 *
 * https://www.educative.io/m/search-matrix
 * https://www.geeksforgeeks.org/dsa/search-element-sorted-matrix/
 *
 * refer this class - com.nishchay.algo.bsearch.a08matirix.BS18SearchInRowWiseColWiseSortedArray.java
 * */
public class SearchSortedMatrix {


    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {1, 5, 45, 80, 81},
                {6, 7, 48, 82, 83},
                {20, 22, 49, 85, 86},
                {21, 23, 50, 90, 92}
        };

        verifySearch(matrix);
    }

    /*
     * A Simple Solution is to one by one compare x with every element of the matrix, time complexity of this solution is O(n x m).
     *
     *
     * However, there are better solutions with less time complexity that use the sorted property of matrix.
     *
     *  We start from the upper right corner of the matrix and compare its value with the key.
     *  If matrix[i][j] ==  key
     *      return (i,j)
     *  If matrix[i][j] >  key
     *      we move one position to the left.
     *  If matrix[i][j] <  key
     *      we move one position down.
     *
     *	Runtime Complexity : O(m+n) where ‘m’ is number of rows and ‘n’ is number of columns.
     *	Memory Complexity  : O(1)
     * */
    private static int[] searchInMatrix(int[][] matrix, int key) {

        int M = matrix.length; //rows
        int N = matrix[0].length; // columns

        // Let's start searching from top right. - starting from matrix[0][N-1]
        // Alternatively, searching from bottom left - matrix[M-1][0] can also work.
        int i = 0, j = N - 1;
        while (i < M && j >= 0) {
            if (matrix[i][j] == key) {
                return new int[]{i, j};
            } else if (key < matrix[i][j]) {
                // search left
                --j;
            } else {
                // search down.
                ++i;
            }
        }
        return new int[]{-1, -1};
    }


    private static void verifySearch(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                System.out.print("\n Verifying at " + i + ", " + j);
                int[] keyLocation = searchInMatrix(matrix, matrix[i][j]);
                if (keyLocation[0] == i && keyLocation[1] == j)
                    System.out.print(" verification done");
            }
        }
    }

}