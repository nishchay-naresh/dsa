package com.nishchay.math;

/*
 * ========= Check if a number is Palindrome =========
 *
 * Given an integer n, determine whether it is a palindrome number or not.
 * A number is called a palindrome if it reads the same from forward and backward.
 *
 * Examples:
 * 	Input: n = 12321
 * 	Output: True
 * 	Explanation: 12321 is a palindrome number because it reads same  forward and backward.
 *
 * 	Input: n = 1234
 * 	Output:  False
 * 	Explanation: 1234 is not a palindrome number because it does not read the same forward and backward.
 * */
public class NumberPalindrome {

    public static void main(String[] args) {
        int number = 12321;
        if (isPalindrome(number)) {
            System.out.println(number + " is a palindrome.");
        } else {
            System.out.println(number + " is not a palindrome.");
        }
    }

    /*
    *  Time Complexity : O(d) , where d = log₁₀(n), we can ignore 10, so can be called as, log n
    *  Auxiliary space: O(1)
    * */
    private static boolean isPalindrome(int n) {
        int original = n;
        int reversed = 0;

        while (n > 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n = n / 10;
        }

        return original == reversed;
    }
}