package com.nishchay.ds.string.a02reverse;

/*
 * Reverse a String character wise - each character should be reversed in a string
 *       str = MALAYALAM, Output = MALAYALAM
 *       str = india, Output = aidin
 *
 * */
public class ReverseString {

    public static void main(String[] args) {

        String str = "india";
        System.out.println("Original String = " + str + ", Reverse String  = " + stringReverseArray(str));
        System.out.println("Original String = " + str + ", Reverse String  = " + stringReverseUsingSB(str));

        str = "MALAYALAM";
        System.out.println("Original String = " + str + ", Reverse String  = " + stringReverseArray(str));
        System.out.println("Original String = " + str + ", Reverse String  = " + stringReverseUsingSB(str));
    }

    /*
    * String reverse by array reverse logic
    * string -> char[] -> array reverse -> char[] -> string
    * */
    static String stringReverseArray(String str) {

        int len = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
        }

        // Converting char[] back to String
        return new String(chars);
    }

    /*
    * String reverse using java api - using StringBuilder
    * str.reverse();   // ❌ String has NO reverse() method
    *
    * original string -> StringBuilder -> using StringBuilder reverse() method -> string
    * */
    private static String stringReverseUsingSB(String str) {
      return new StringBuilder(str).reverse().toString();
    }
}
