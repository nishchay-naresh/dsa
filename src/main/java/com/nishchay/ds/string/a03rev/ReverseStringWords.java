package com.nishchay.ds.string.a03rev;

/*
 * Reverse each word of a String  - each word should be reversed in a string
 *       input  = I like this program very much
 *       output = I ekil siht margorp yrev hcum
 *
 * */
public class ReverseStringWords {

    public static void main(String[] args) {

        String str = "I like this program very much";   // much very program this like I
        System.out.println("Original String   = " + str);

        System.out.println("reverseWords(str) = " + reverseWords(str));
    }

    public static String reverseWords(String input){
        String[] words = input.trim().split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String reversedWord = ReverseString.stringReverseArray(word);
            result.append(reversedWord).append(" ");
        }
        return result.toString();
    }
}
