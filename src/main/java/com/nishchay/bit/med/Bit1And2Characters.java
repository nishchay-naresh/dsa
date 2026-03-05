package com.nishchay.bit.med;


import java.util.Arrays;

/*
 *	============================ 717. 1-bit and 2-bit Characters =================================
 *
 * We have two special characters:
 *
 * The first character can be represented by one bit 0.
 * The second character can be represented by two bits (10 or 11).
 * Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.
 *
 * Examples:
 * 				Input: bits = [1,0,0]
 * 				Output: true
 * 				Explanation: The only way to decode it is two-bit character and one-bit character.
 * 				So the last character is one bit character.
 *
 * 				Input: bits = [1,1,1,0]
 * 				Output: false
 * 				Explanation: The only way to decode it is two-bit character and two-bit character.
 * 				So the last character is not one bit character.
 *
 * https://algo.monster/liteproblems/717
 * https://leetcode.com/problems/1-bit-and-2-bit-characters/description/
 *
 */
public class Bit1And2Characters {

    public static void main(String[] args) {
        int[] bits;

        bits = new int[]{1, 0, 0};
        System.out.println(Arrays.toString(bits) + " = " + isOneBitCharacter(bits));
        bits = new int[]{1, 1, 1, 0};
        System.out.println(Arrays.toString(bits) + " = " + isOneBitCharacter(bits));
    }

    /*
     * ================ [Expected Approach] single scan  =====================
     *
     * Start from index 0 till n-2
     *  And each time decide how many elements to skip based on the value of the current element:
     *      If bits[i] == 0
     *          move i += 1 // skip 1 element (representing a one-bit character)
     *      If bits[i] == 1
     *          move i += 2 // skip 2 elements (representing a two-bit character)
     * At the end, check whether you stopped exactly at the last index.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static boolean isOneBitCharacter(int[] bits) {
        int i = 0, n = bits.length;
        while (i < n - 1) {
            if (bits[i] == 1) {
                i += 2;   // 2-bit character
            } else {
                i += 1;   // 1-bit character
            }
        }
        return i == n - 1;
    }

}
