package com.nishchay.math;

/*
 *  ========= Check Armstrong Numbers =========
 *
 * A positive integer of n digits is called an Armstrong number of order n (order is the number of digits) if
 *
 * 	abcd... = pow(a,n) + pow(b,n) + pow(c,n) + pow(d,n) + ....
 * 	Here a, b, c and d are digits of input number abcd.....
 *
 * Examples
 * 	Input: n = 153
 * 	Output: true
 * 	Explanation: 153 is an Armstrong number, 1^3 + 5^3 + 3^3 = 153
 *
 * 	Input: n = 9474
 * 	Output: true
 * 	Explanation: 9^4 + 4^4  + 7^4 + 4^4  = 6561 + 256 + 2401 + 256 = 9474
 *
 * 	Input: n = 123
 * 	Output: false
 * 	Explanation: 1^3 + 2^3 + 3^3 = 1 + 8 + 27 = 36
 * */
public class ArmstrongNumberCheck {

    public static void main(String[] args) {
        int number = 153;
        System.out.printf("%d is Armstrong number = %b", number, isArmstrong(number));
        number = 9474;
        System.out.printf("\n%d is Armstrong number = %b", number, isArmstrong(number));
        number = 123;
        System.out.printf("\n%d is Armstrong number = %b", number, isArmstrong(number));
    }

    /*
     * 1. The idea is to first count the number of digits (or find the order). Let the number of digits be n.
     * 2. For every digit r in input number x, compute rn.
     * 3. If the sum of all such values is equal to x, then return true, else false.
     * */
    private static boolean isArmstrong(int n) {
        int original = n;
        int sum = 0;
        int digits = length(n);

        while (n > 0) {
            int digit = n % 10;
            sum += (int) Math.pow(digit, digits);
            n /= 10;
        }
        return sum == original;
    }

    // Function to count number of digits
    private static int length(int n) {
        int length = 0;
        while (n != 0) {
            length++;
            n = n / 10;
        }
        return length;
    }

}
