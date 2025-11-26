package com.nishchay.algo.recursion.pointer2;


/*
 *  ============================= Check for Palindrome Using Recursion ======================================
 *
 *  Given a string s, check if it is a palindrome using recursion.
 *  A palindrome is a word, phrase, or sequence that reads the same backward as forward.
 *
 * Examples:
 * 			Input: s = "abba"
 * 			Output: true
 * 			Explanation: The first and last characters match, and the middle substring "bb" is also a palindrome, so the whole string is a palindrome.
 *
 * 			Input: s = "abc"
 * 			Output: false
 * 			Explanation: The first and last characters don’t match (a ≠ c), so the string is not a palindrome.
 *
 *			Palindrome string: malayalam, ehcache, madam
 *
 * https://www.geeksforgeeks.org/dsa/recursive-function-check-string-palindrome/
 */

public class CheckPalindrome {


    public static void main(String[] args) {
        String s = "abba";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome("abc"));
        System.out.println(isPalindrome("malayalam"));
        System.out.println(isPalindrome("ehcache"));
        System.out.println(isPalindrome("madam"));
    }

    static boolean isPalindrome(String s) {
        return isPalindromeRec(s, 0, s.length() - 1);
    }

    static boolean isPalindromeRec(String s, int left, int right) {

        // Base case
        if (left >= right)
            return true;

        // If mismatch found
        if (s.charAt(left) != s.charAt(right))
            return false;

        // Recursive call with narrowed range
        return isPalindromeRec(s, left + 1, right - 1);
    }
}
