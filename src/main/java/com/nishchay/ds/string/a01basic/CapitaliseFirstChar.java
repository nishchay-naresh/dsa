package com.nishchay.ds.string.a01basic;

/*
 *  ======================== Capitalize the first character of each word in a string ================================
 *
 * Capitalize the first character of a string without using built-in String utility methods
 *
 *	Sample Input:
 *	               this is india and it is my country
 *	Sample Output:
 *	               This Is India And It Is My Country
 *
 * */
public class CapitaliseFirstChar {

    public static void main(String[] args) {
        String input = "i love java programming language";
        System.out.println("capitalizeWords - " + capitalizeWords(input));
        input = "this is india and it is my country";
        System.out.println("capitalizeWords - " + capitalizeWords(input));

    }

    /*
     *	Characters in Java are internally numbers (ASCII/Unicode).
     *
     *	'a' → 97
     *	'A' → 65
     *	Difference = 32
     *	So: To convert lowercase → uppercase → subtract 32
     *
     * Traverse the string character by character
     *	Capitalize:
     *		First character of string
     *		Any character after a space
     *	Convert manually using ASCII:
     *		'a' → 'A' by subtracting 32
     *		chars[i] = (char)(chars[i] - 32);
     *
     * */
    public static String capitalizeWords(String str) {
        if (str == null || str.length() == 0)
            return str;

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            // Check if it's start of word
            if (i == 0 || chars[i - 1] == ' ') {
                // Check if lowercase
                if (chars[i] >= 'a' && chars[i] <= 'z') {
                    chars[i] = (char)(chars[i] - 32);
                }
            }
        }

        return new String(chars);
    }
}
