package com.nishchay.ds.matrix.a04med;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * ===================================== Valid Sudoku =======================================
 *
 * Determine if a 9 x 9 Sudoku board is valid.
 * Only the filled cells need to be validated according to the following rules:
 * 	1.	Each row must contain the digits 1-9 without repetition.
 *  2.	Each column must contain the digits 1-9 without repetition.
 * 	3.	Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * ---------------------------- Check Sudoku Board Configuration is Valid or Not ---------------------------
 * Given a Sudoku board configuration, check whether the given Sudoku board configuration is valid or not.
 * A valid configuration requires that each row, column, and 3x3 sub-matrix must contain the digits 1-9 without repetition.
 *
 *
 * Examples:
 * Example 1:
 *	Input: board =
 *					{
 *						{'5','3','.','.','7','.','.','.','.'},
 *						{'6','.','.','1','9','5','.','.','.'},
 *						{'.','9','8','.','.','.','.','6','.'},
 *						{'8','.','.','.','6','.','.','.','3'},
 *						{'4','.','.','8','.','3','.','.','1'},
 *						{'7','.','.','.','2','.','.','.','6'},
 *						{'.','6','.','.','.','.','2','8','.'},
 *						{'.','.','.','4','1','9','.','.','5'},
 *						{'.','.','.','.','8','.','.','7','9'},
 *					};
 *	Output: true
 *
 *
 *Example 2:
 *	Input: board =
 *					{
 *						{'8','3','.','.','7','.','.','.','.'},
 *						{'6','.','.','1','9','5','.','.','.'},
 *						{'.','9','8','.','.','.','.','6','.'},
 *						{'8','.','.','.','6','.','.','.','3'},
 *						{'4','.','.','8','.','3','.','.','1'},
 *						{'7','.','.','.','2','.','.','.','6'},
 *						{'.','6','.','.','.','.','2','8','.'},
 *						{'.','.','.','4','1','9','.','.','5'},
 *						{'.','.','.','.','8','.','.','7','9'},
 *					};
 *	Output: false
 *
 *
 * https://www.geeksforgeeks.org/dsa/check-if-given-sudoku-board-configuration-is-valid-or-not/
 * https://leetcode.com/problems/valid-sudoku/description/
 *
 *
 * Been asked in Altimetrik
 * */
public class ValidSudoku {

    public static void main(String[] args) {


        char[][] board = new char[][]
                {
                        {'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'},
                };
        // Output: true
        System.out.println(isValidSudoku_booleanArray(board) ? "true" : "false");
        System.out.println(isValidSudoku_hashSet(board) ? "true" : "false");

       board = new char[][]
                {
                        {'8','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'},
                };
        // Output: false
        System.out.println(isValidSudoku_booleanArray(board) ? "true" : "false");
        System.out.println(isValidSudoku_hashSet(board) ? "true" : "false");

    }


    /*
     * ================== [Naive Approach] Using 3 separate boolean arrays - Time O(n^3) and Space O(n) ====================
     *
     *	We need to ensure no duplicates in:
     *		-	Each row
     *		-	Each column
     *		-	Each 3×3 box
     *	Ignore "." (empty cells)
     *
     * To track the visited cell, using 3 boolean Using 3 arrays
     *      rows[i][num]   → number seen in row i
     *      cols[j][num]   → number seen in column j
     *      boxes[k][num]  → number seen in box k
     *
     * Since taking boolean[9][9] => So (1–9) → mapped to 0–8
     * Important Conversion to use the number as index => board[i][j] - '1'; // 0 to 8
     *
     *  Box Index (MOST IMPORTANT) : int boxIndex = (i / 3) * 3 + (j / 3);
     *  This maps 2D grid → 1D box number
     *                                      [0][1][2]
     *                                      [3][4][5]
     *                                      [6][7][8]
     *
     *
     * Time → O(9×9) = O(1) (constant)
     * Space → O(1)
     *
     * */
    private static boolean isValidSudoku_booleanArray(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.'){
                    continue;
                }

                int num = board[i][j] - '1'; // 0 to 8
                int boxIndex = (i / 3) * 3 + (j / 3);

                // if seen current number in any of the 2 boolean array in past
                if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                    return false;
                }
                // mar the current number as seen - for future comparison
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        return true;
    }

    /*
     *  ================ [Better Approach 1] Using Hashing - Time O(n^2) and Space O(n^2)  =====================
     *
     *			0	     1       2
     *		   0 1 2 | 3 4 5 | 6 7 8
     *		0  . . . | . . . | . . .
     *	0	1  . . . | . . . | . . .
     *		2  . . . | . . . | . . .
     *		   ------+-------+------
     *		3  . . . | . . . | . . .
     *	1	4  . . . | . . . | . . .
     *		5  . . . | . . . | . . .
     *		   ------+-------+------
     *		6  . . . | . . . | . . .
     *	2	7  . . . | . . . | . . .
     *		8  . . . | . . . | . . .
     *
     *      key(r/3,c/3)
     *		mapping logic can be = actual index/3
     *
     *  Sub-matrix(Box) index formation formula : int boxIndex = (i / 3) * 3 + (j / 3);
     *  This maps 2D grid → 1D box number
     *                                      [0][1][2]
     *                                      [3][4][5]
     *                                      [6][7][8]
     *
     *
     *  Time = O(N²)
     *  Space = O(1) => 9 × 9 + 9 × 9 + 9 × 9 = 243 elements max
     * */
    static boolean isValidSudoku_hashSet(char[][] mat) {

        // Lists of sets to track seen numbers in rows, columns, and subMatrices - 9 rows, 9 cols, 9 boxes
        List<HashSet<Integer>> rows = new ArrayList<>();
        List<HashSet<Integer>> cols = new ArrayList<>();
        List<HashSet<Integer>> subMat = new ArrayList<>();

        // Initialize the sets
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            subMat.add(new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = mat[i][j];

                if (mat[i][j] == '.'){
                    continue;
                }

                // Check for duplicates in the row
                if (rows.get(i).contains(val))
                    return false;
                rows.get(i).add(val);

                // Check for duplicates in the column
                if (cols.get(j).contains(val))
                    return false;
                cols.get(j).add(val);

                // Calculate the sub-matrix index
                int idx = (i / 3) * 3 + (j / 3);

                // Check for duplicates in the sub-matrix
                if (subMat.get(idx).contains(val))
                    return false;
                subMat.get(idx).add(val);
            }
        }
        return true;
    }

}
