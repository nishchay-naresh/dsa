package com.nishchay.algo.recursion.pointer2;


/*
 *  ============================= Reverse an Array using Recursion ======================================
 *
 *  Write recursive function to reverse string
 *
 *
 * https://www.geeksforgeeks.org/dsa/reverse-a-string-using-recursion/
 */

public class StringReverse {

    public static void main(String[] args) {
        usingString();
        usingStrBuilder();
    }

    private static void usingString() {
        String str = "india";
        char[] charArray = str.toCharArray();
        stringReverse(charArray, 0, str.length() - 1);
        String revStr = String.valueOf(charArray);
        System.out.println("Original String - " + str);
        System.out.println("Reversed String - " + revStr);
    }

    // String Reverse Using Recursion
    private static void stringReverse(char[] str, int i, int j) {
        if (i < j) {
            char t = str[i];
            str[i] = str[j];
            str[j] = t;
            stringReverse(str, ++i, --j);
        }
    }

    private static void usingStrBuilder() {
        StringBuilder str = new StringBuilder("Geeks for Geeks");
        System.out.println("Original String - " + str);
        stringReverse(str, 0, str.length() - 1);
        System.out.println("Reversed String - " + str);
    }

    static void stringReverse(StringBuilder str, int start, int end) {
        if (start >= end)
            return;
        char temp = str.charAt(start);
        str.setCharAt(start, str.charAt(end));
        str.setCharAt(end, temp);
        stringReverse(str, start + 1, end - 1);
    }

}
