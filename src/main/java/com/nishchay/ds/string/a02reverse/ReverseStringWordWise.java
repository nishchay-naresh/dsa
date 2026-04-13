package com.nishchay.ds.string.a02reverse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * ========================== string reverse word wise ==================================
 *
 * Reverse a String word wise - reverse the string without reversing its individual words
 * Reverse the order of words, not characters.
 *
 * Examples:
 *				Input: s = "the sky is blue"
 *				Output: "blue is sky the"
 *
 *				Input: s = "  hello world  "
 *				Output: "world hello"
 *				Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 *				Input: s = "a good   example"
 *				Output: "example good a"
 *				Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *              Input: "I like this program very much"
 *              Output: "much very program this like I"
 *
 *              Input: "I love Java"
 *              Output: "Java love I"
 *
 * https://leetcode.com/problems/reverse-words-in-a-string/description/
 * */
public class ReverseStringWordWise {

    public static void main(String[] args) {

        String str = "I like this program very much";   // much very program this like I
        System.out.println("Original String = " + str);

        System.out.println("reverseWords_api(str) = " + reverseWords_api(str));
        System.out.println("reverseWords_java8(str) = " + reverseWords_java8(str));
        System.out.println("reverseWords_array(str) = " + reverseWords_array(str));
    }

    // Using Java API (BEST & SIMPLE)
    public static String reverseWords_api(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i != 0)
                result.append(" ");
        }
        return result.toString();
    }

    // Using Java API (BEST & SIMPLE)
    public static String reverseWords_java8(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(words);
        // Convert array/list of strings into a string
        return String.join(" ", words);
    }

    /*
     * String reverse by array reverse logic
     * string -> string[] -> array reverse -> string[] -> string
     *
     * what is this - "\\s+"
     *      This is a regular expression (regex).
     *      \s - Any whitespace character (space " ", tab \t, new line \n)
     *      +  - One or more occurrence
     *      \\ - extra \ is to suppress the special meaning of \
     *
     * */
    private static String reverseWords_array(String s) {

        if (s == null) {
            return null;
        }
        // cleaning leading and trailing spaces and remove extra spaces in between words
        s = s.trim().replaceAll("\\s+", " ");
        String[] words = s.split(" ");
        int size = words.length;

        if (size < 2) {
            return s;
        }

        for (int i = 0, j = size - 1; i < j; i++, j--) {
            // swap ith & jth element
            String t = words[i];
            words[i] = words[j];
            words[j] = t;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(words[i]);
            if (i != size - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
