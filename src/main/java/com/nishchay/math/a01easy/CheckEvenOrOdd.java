package com.nishchay.math.a01easy;

/*
 * ========= Check Even or Odd =========
 *
 * 	Examples:
 * 		Input: n = 15
 * 		Output: false
 * 		Explanation: 15 % 2 = 1, so 15 is odd .
 *
 * 		Input: n = 44
 * 		Output: true
 * 		Explanation: 44 % 2 = 0, so 44 is even.
 * */
public class CheckEvenOrOdd {

    public static void main(String[] args) {
        int n = 15;
        System.out.printf("%d is even = %b", n, isEven(n));
        n = 44;
        System.out.printf("\n%d is even = %b", n, isEvenUsingBit(n));

    }

    private static boolean isEven(int n) {
        return n % 2 == 0;
    }

    /*
     * Using Bitwise AND Operator - O(1) Time and O(1) Space
     * Examples:
     *
     * 15  ->       1 1 1 1
     *           &  0 0 0 1
     *              -------
     *              0 0 0 1, so this we can say it is an odd number.
     *
     * 44 ->        1 0 1 1 0 0
     *           &  0 0 0 0 0 1
     *              -------------
     *              0 0 0 0 0 0, so this we can say it is an even number.
     *
     * */
    private static boolean isEvenUsingBit(int n) {
        return ((n & 1) == 0) ? true : false;
    }
}
