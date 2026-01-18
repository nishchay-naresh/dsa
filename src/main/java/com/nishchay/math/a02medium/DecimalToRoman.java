package com.nishchay.math.a02medium;


/*
 * Converting Decimal Number lying between 1 and 3999 to Roman Numerals
 *
 * Given an integer, convert it into its equivalent Roman numeral representation.
 *
 * Step 1 : How Roman number system works
 * 1	5	10	50	100	500	1000
 * I	V	X	L	C	D	M
 *
 * 1	4	5	9	10	40	50	90	100	400	500	900	1000
 * I	IV	V	IX	X	XL	L	XC	C	CD	D	CM	M
 *
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9. Because 1 is the lowest multiplication of 5 & 10
 * X can be placed before L (50) and C (100) to make 40 and 90. Because 10 is the lowest multiplication of 50 & 100
 * C can be placed before D (500) and M (1000) to make 400 and 900. Because 100 is the lowest multiplication of 500 & 1000
 *
 *
 * Step 2 : The conversion logic : Decimal to Roman
 * → Always try dividing in multiples of highest possible notation.
 * → If it’s not possible with current multiple, move to the next lower multiple.
 * 1 <- 10 <- 1,00 <- 1,000 <- 1,00,000
 * NOTE: Whenever we divide the multiple, we need to reduce our no.
 *
 * 1	4	5	9	10	40	50	90	100	 400	500	 900	1000
 * I	IV	V	IX	X	XL  L	XC	C	 CD	    D	 CM	    M	<-------
 *
 * Ex
 * Decimal :  2,856 → 1,856 → 856 → 356 → 256 → 156 → 56 → 6 → 1 → 0
 * Roman :  MMDCCCLVI
 *
 *
 *
 * https://leetcode.com/problems/integer-to-roman/description/
 * https://www.geeksforgeeks.org/dsa/converting-decimal-number-lying-between-1-to-3999-to-roman-numerals/
 * */
public class DecimalToRoman {

    public static void main(String[] args) {

        int[] inputs = {2856, 58, 1994, 1234, 12};
        String[] outputs = {"MMDCCCLVI", "LVIII", "MCMXCIV", "MCCXXXIV", "XII"};

        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {
            result = result && toRoman(inputs[i]).equals(outputs[i]);
            if (!result)
                System.out.println("Test failed for: " + inputs[i]);
            else
                System.out.println("Test passed for: " + inputs[i]);
        }
    }


    /*
     * Time Complexity : O(n)2
     * Space Complexity: O(n)
     * */
    private static String toRoman(int number) {

        final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        }

        StringBuilder roman = new StringBuilder();

        // looping right to left
        for (int i = 0; i < VALUES.length; i++) {
            while (number >= VALUES[i]) {
                number -= VALUES[i];
                roman.append(SYMBOLS[i]);
            }
        }
        return roman.toString();
    }
}
