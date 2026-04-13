package com.nishchay.ds.string.a01basic;

/*
 *==============1528. Shuffle String====================
 *
 * Given a string s and an integer array indices of the same length.
 * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 * Return the shuffled string.
 *
 *			Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 *			Output: "leetcode"
 *			Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 *
 *			Input: s = "abc", indices = [0,1,2]
 *			Output: "abc"
 *			Explanation: After shuffling, each character remains in its position.
 *
 *
 * https://leetcode.com/problems/shuffle-string/
 * */
public class ShuffleString {

    public static void main(String[] args) {
        String str = "codeleet";
        int[] indices = new int[]{4, 5, 6, 7, 0, 2, 1, 3};
        System.out.println("shuffled string - " + restoreString(str, indices)); //leetcode

        str = "abc";
        indices = new int[]{0, 1, 2};
        System.out.println("shuffled string - " + restoreString(str, indices)); //abc

        str = "aiohn";
        indices = new int[]{3, 1, 4, 2, 0};
        System.out.println("shuffled string - " + restoreString(str, indices)); //nihao

        str = "aaiougrt";
        indices = new int[]{4, 0, 2, 6, 7, 3, 1, 5};
        System.out.println("shuffled string - " + restoreString(str, indices)); //arigatou

        str = "art";
        indices = new int[]{1, 0, 2};
        System.out.println("shuffled string - " + restoreString(str, indices)); //rat
    }

    /*
     * As per question, place the char taking from the main string, place it to the indexes given in indices
     *
     * Time Complexity - O(n)
     * Space Complexity - O(n)
     * */
    private static String restoreString(String s, int[] indices) {
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[indices[i]] = s.charAt(i);
        }
        return new String(result);
    }
}
