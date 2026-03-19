package com.nishchay.ds.matrix.a01traversal;

import java.util.*;

/*
 *	Key Idea
 *
 *	Row-wise traversal means:
 *	for(row)
 *	   for(col)
 *
 *	Column-wise traversal means:
 *	for(col)
 *	   for(row)
 *
 *	So the outer loop decides the traversal direction.
 *
* */
public class A00RowAndColumn {

    public static void main(String[] args) {

        rowWiseSquareMatrix();
        rowWiseNonSquareMatrix();

        columnWiseSquareMatrix();
        columnWiseNonSquareMatrix();

        columnWiseSquareList();
    }

    private static void rowWiseSquareMatrix() {
        int[][] mat = {
                {1, 3, 5},
                {0, 9, 7},
                {2, 4, 6}
        };
        List<Integer> result = new ArrayList<>();
        for (int[] row : mat) {
            for (int val : row) {
                result.add(val);
            }
        }
        System.out.println(result); // [1, 3, 5, 0, 9, 7, 2, 4, 6]
    }

    private static void rowWiseNonSquareMatrix() {
        int[][] mat = {
                {1, 3, 5, 7},
                {0, 9, 10, 11},
                {2, 4, 6, 8}
        };

        List<Integer> result = new ArrayList<>();

        int rows = mat.length;
        int cols = mat[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                result.add(mat[row][col]);
            }
        }
        System.out.println(result); // [1, 3, 5, 7, 0, 9, 10, 11, 2, 4, 6, 8]
    }

    private static void columnWiseSquareMatrix() {

        int[][] mat = {
                {1, 3, 5},
                {0, 9, 7},
                {2, 4, 6}
        };

        List<Integer> result = new ArrayList<>();

        int n = mat.length; // square matrix

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                result.add(mat[row][col]);
            }
        }
        System.out.println(result); // [1, 0, 2, 3, 9, 4, 5, 7, 6]
    }

    private static void columnWiseNonSquareMatrix() {

        int[][] mat = {
                {1, 3},
                {0, 9, 10, 11},
                {2, 4, 6}
        };

        List<Integer> result = new ArrayList<>();

        int maxCols = 0;

        // Non-square matrix - find maximum column length
        for (int[] row : mat) {
            maxCols = Math.max(maxCols, row.length);
        }

        // Traverse column by column
        for (int col = 0; col < maxCols; col++) {
            for (int row = 0; row < mat.length; row++) {
                if (col < mat[row].length) {
                    result.add(mat[row][col]);
                }
            }
        }
        System.out.println(result); // [1, 0, 2, 3, 9, 4, 10, 6, 11]
    }


    private static void columnWiseSquareList() {

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(1, 3));
        lists.add(Arrays.asList(0, 9, 10, 11, 16));
        lists.add(Arrays.asList(2, 4, 6));
        lists.add(Arrays.asList(8));

        List<Integer> result = new ArrayList<>();
        int maxCols = 0;
        // find maximum column size
        for (List<Integer> row : lists) {
            maxCols = Math.max(maxCols, row.size());
        }

        // column-wise traversal
        for (int col = 0; col < maxCols; col++) {
            for (List<Integer> row : lists) {
                if (col < row.size()) {
                    result.add(row.get(col));
                }
            }
        }

        System.out.println(result); // [1, 0, 2, 8, 3, 9, 4, 10, 6, 11, 16]
    }
}
