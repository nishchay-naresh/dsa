package com.nishchay.ds.string.a02reverse;

/*
 * Input: “This|| pandemic ;/? will ?? be // over;; soon//?/”
 * Output: “sihT|| cimednap ;/? lliw ?? eb // revo;; noos//?/“
 * */
public class ReverseWordsExcludeDelimiter {

    public static void main(String[] args) {

        String mainString = "This|| pandemic ;/? will ?? be // over;; soon//?/";
        System.out.println("mainString    = " + mainString);
        System.out.println("reverseString = " + reverseWords(mainString));
    }

    private static String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (isLetter(ch)) {
                // collect letters
                word.append(ch);
            } else {
                // non-letter found → flush reversed word
                result.append(word.reverse());
                word.setLength(0);

                // append special character as it is
                result.append(ch);
            }
        }

        // flush last word if exists
        result.append(word.reverse());

        return result.toString();
    }

    /*
    *
    * Similar method is there in Char class - Character.isLetter(ch)
    * */
    private static boolean isLetter(char c) {
        //  0-9 && a-z &&A-Z
        if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }
}
