package com.nishchay.ds.string.a02reverse;

/*
 * ========================== Palindrome by Front Insertion ==================================
 *
 *	Given a string str of length N, consisting of only lowercase English letters
 *	The task is to return the count of minimum characters to be added at front to make the string a palindrome.
 *
 *	For example, for the given string “deed”, the string is already a palindrome,
 *	 thus, minimum characters needed are 0.
 *
 *	Similarly, for the given string “aabaaca”,
 *	 the minimum characters needed are 2 i.e. ‘a’ and ‘c’ which makes the string “acaabaaca” palindrome.
 *
 * Examples:
 *				Input: s = "abc"
 *				Output: 2
 *				Explanation: We can make above string palindrome as "cbabc", by adding 'b' and 'c' at front.
 *
 *				Input: s = "aacecaaaa"
 *				Output: 2
 *				Explanation: We can make above string palindrome as "aaaacecaaaa" by adding two a's at front of string.
 *
 *
 * https://www.geeksforgeeks.org/dsa/minimum-characters-added-front-make-string-palindrome/
 *
 * TODO
 *  https://leetcode.com/problems/shortest-palindrome/description/
 *  https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
 * */
public class PalindromeByFrontInsertion {

    public static void main(String[] args) {

        System.out.println("aabaa       = " + addCharToMakeItPalindrome("aabaa"));
        System.out.println("aabaaca     = " + addCharToMakeItPalindrome("aabaaca"));
        System.out.println("adcbaaca    = " + addCharToMakeItPalindrome("adcbaaca"));
        System.out.println("aacecaaaa   = " + addCharToMakeItPalindrome("aacecaaaa"));
        System.out.println("abc         = " + addCharToMakeItPalindrome("abc"));
    }


    private static int addCharToMakeItPalindrome(String input) {
        int count = 0;
        int j = input.length() - 1;
        while (j >= 0) {
            if (isPalindrome(input.toCharArray(), 0, j)) {
                break;
            } else {
                j--;
                count++;
            }
        }
        return count;
    }

    private static boolean isPalindrome(char[] charArray, int i, int j) {

        while (i < j && charArray[i] == charArray[j]) {
            i++;
            j--;
        }
        return i >= j;
    }

}
