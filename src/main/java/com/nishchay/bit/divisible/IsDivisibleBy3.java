package com.nishchay.bit.divisible;


/*
 *	============================ Check if a Binary Number Is Divisible by 3 =================================
 *
 * Given a string of binary characters, check if it is multiple of 3 or not.
 * Examples:
 * 				Input : 1 0 1 0 / 10
 * 				Output : NO
 * 				Explanation: (1 0 1 0) is 10 and hence not a multiple of 3
 *
 * 				Input :  1 1 0 0 / 12
 * 				Output : YES
 * 				Explanation: (1 1 0 0) is 12 and hence a multiple of 3
 *
 *              Input : 1100101101 / 813
 *              Output : true
 *
 * https://www.geeksforgeeks.org/dsa/check-binary-string-multiple-3-using-dfa/
 */
public class IsDivisibleBy3 {

    public static void main(String[] args) {
        System.out.println("Converting to decimal");
        System.out.println("1100101101 - " + isDivisibleBy3_decimal("1100101101"));
        System.out.println("1010 - " + isDivisibleBy3_decimal("1010"));
        System.out.println("1100 - " + isDivisibleBy3_decimal("1100"));

        System.out.println("applying math logic ");
        System.out.println("1100101101 - " + isDivisibleBy3("1100101101"));
        System.out.println("1010 - " + isDivisibleBy3("1010"));
        System.out.println("1100 - " + isDivisibleBy3("1100"));
    }

    /*
     * ================ [Naive/Bruteforce Approach] Converting binary to decimal  =====================
     *
     *  First convert binary to decimal, then check divisibility using % operator
     *
     *  Overflow for large binary numbers
     *   Defeats the purpose of the problem
     */
    private static boolean isDivisibleBy3_decimal(String binary) {
        int num = Integer.parseInt(binary, 2);
        return num % 3 == 0;
    }


    /*
     *  ================ [Expected Approach] applying math logic  =====================
     *
     *  A binary number is divisible by 3 if:
     *      (difference between count of set bits at odd positions and even positions) is divisible by 3
     *
     *  Positions counted from right (LSB = position 1).
     * Ex -
     *      4 3 2 1   - Positions (from right)
     *      1 1 0 0
     *
     *      Odd positions   -> bits: 0, 1   ->  count = 1
     *      Even positions  -> bits: 0, 1   ->  count = 1
     *      Difference = 1-1 = 0 -> divisible by 3
     *
     *      4 3 2 1   - Positions (from right)
     *      1 0 1 0
     *
     *      Odd positions   -> bits: 0, 0   ->  count = 0
     *      Even positions  -> bits: 1, 1   ->  count = 2
     *      Difference = 0-2 = -2 = 2 -> not divisible by 3
     *
     *  Time Complexity: O(n), n - length of the binary string
     *  Auxiliary Space: O(1)
     */
    private static boolean isDivisibleBy3(String binary) {

        int oddCount = 0;
        int evenCount = 0;

        int position = 1; // start from rightmost bit

        for (int i = binary.length() - 1; i >= 0; i--) {

            if (binary.charAt(i) == '1') {
                if (position % 2 == 0)
                    evenCount++;
                else
                    oddCount++;
            }
            position++;
        }

        return Math.abs(oddCount - evenCount) % 3 == 0;
    }
}
