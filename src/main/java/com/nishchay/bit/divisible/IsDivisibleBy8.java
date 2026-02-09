package com.nishchay.bit.divisible;


/*
 *	============================ Check if a Binary Number Is Divisible by 8 =================================
 *
 * Given a string of binary characters, check if it is multiple of 8 or not.
 * Examples:
 *		        1000		8			true
 * 		        11000		24			true
 * 		        101000		40			true
 * 		        111000		56			true
 * 		        101100		44			false
 *
 *
 *  ========================== Check if a large number(decimal) is divisible by 8 or not =======================
 * Given a number, the task is to check if a number is divisible by 8 or not.
 *  The input number may be large and it may not be possible to store even if we use long int.
 *
 * 				Input  : n = 1128
 * 				Output : Yes
 * 				Input : n = 1124
 * 				Output : No
 * 				Input  : n = 363588395960667043875487
 * 				Output : No
 *
 * https://www.geeksforgeeks.org/dsa/check-large-number-divisible-8-not/
 */
public class IsDivisibleBy8 {

    public static void main(String[] args) {


        System.out.println("---------------------- BinaryNumber -----------------------");
        System.out.println("Converting to decimal");
        System.out.println("1000        - " + isDivisibleBy8_decimal("1000"));
        System.out.println("11000       - " + isDivisibleBy8_decimal("11000"));
        System.out.println("101000      - " + isDivisibleBy8_decimal("101000"));
        System.out.println("111000      - " + isDivisibleBy8_decimal("111000"));
        System.out.println("101100      - " + isDivisibleBy8_decimal("101100"));

        System.out.println("Applying math logic");
        System.out.println("1000        - " + isDivisibleBy8_string("1000"));
        System.out.println("11000       - " + isDivisibleBy8_string("11000"));
        System.out.println("101000      - " + isDivisibleBy8_string("101000"));
        System.out.println("111000      - " + isDivisibleBy8_string("111000"));
        System.out.println("101100      - " + isDivisibleBy8_string("101100"));

        System.out.println("---------------------- DecimalNumber -----------------------");
        long n = 76952;
        System.out.println("decimalNumberDivisibleBy8(76952) - " + decimalNumberDivisibleBy8(n));//true
        n = 1128;
        System.out.println("decimalNumberDivisibleBy8(1128) - " + decimalNumberDivisibleBy8(n));//true
        n = 1124;
        System.out.println("decimalNumberDivisibleBy8(1124) - " + decimalNumberDivisibleBy8(n)); //false
    }

    /*
     * ================ [Naive/Bruteforce Approach] Converting binary to decimal  =====================
     *
     *  First convert binary to decimal, then check difficulty using % operator
     *
     *  Overflow for large binary numbers
     *  Slower
     *  Converts whole number
     */
    private static boolean isDivisibleBy8_decimal(String binary) {
        int num = Integer.parseInt(binary, 2);
        return num % 8 == 0;
    }


    /*
     *  ================ [Expected Approach] applying math logic  =====================
     *
     *  A binary number is divisible by 8 if its last 3 bits are 0.
     *  Because: 8 = 2³, So binary number must be divisible by 2³.
     *
     *  So - Binary number ends with "000"
     *
     * Ex -
     * 		Binary		Decimal		Divisible by 8
     *-------------------------------------------------
     * 		1000		8			true
     * 		11000		24			true
     * 		101000		40			true
     * 		111000		56			true
     * 		101100		44			false
     *
     *
     * Time Complexity: O(n), n - length of the binary string
     *  Auxiliary Space: O(1)
     */
    private static boolean isDivisibleBy8_string(String binary) {

        // must have at least 4 bits for 8 or more
        if (binary.length() < 4) {
            return false;
        }
        return binary.endsWith("000");
    }

    /*
     *  ================ [Expected Approach] Using bitwise operation  =====================
     *
     *  A decimal number is divisible by 8 if its last 3 bits are 0in its binary representation.
     *  So doing an AND with 111(7) == 0, it is divisible by 8
     *          num & 111 == 0 it is divisible by 8
     *
     *
     */
    private static boolean decimalNumberDivisibleBy8(long num) {
        return (num & 7) == 0;
    }
}
