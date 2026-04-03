package com.nishchay.ds.string.a05freq;

/*
 *	====================================== Check if the Sentence Is Pangram =================================================
 *
 * Given a string s, the task is to check if it is Pangram or not.
 * A pangram is a sentence containing all letters of the English Alphabet.
 *
 * Examples:
 *				Input: s = "The quick brown fox jumps over the lazy dog"
 *				Output: true
 *				Explanation: The input string contains all characters from ‘a’ to ‘z’.
 *
 *				Input: s = "The quick brown fox jumps over the dog"
 *				Output: false
 *				Explanation: The input string does not contain all characters from ‘a’ to ‘z’, as ‘l’, ‘z’, ‘y’ are missing
 *
 *				Input: s = "Farmer jack realized that big yellow quilts were expensive"
 *				Output: true
 *				Explanation:The input string contains all characters from ‘a’ to ‘z’.
 *
 * https://www.geeksforgeeks.org/dsa/pangram-checking/
 * https://leetcode.com/problems/check-if-the-sentence-is-pangram/description/
 * https://www.baeldung.com/java-string-contains-all-letters
 * */
public class CheckPangram {

    public static void main(String[] args) {

        String input;
        input = "Farmer jack realized that big yellow quilts were expensive";
        System.out.println(input + ", isPangram = " + isPangram(input));
        System.out.println(input + ", isPangram = " + isPangram_stream(input));

        input = "The quick brown fox jumps over the lazy dog";
        System.out.println(input + ", isPangram = " + isPangram(input));
        System.out.println(input + ", isPangram = " + isPangram_stream(input));

        input = "The quick brown fox jumps over the crazy dog";
        System.out.println(input + ", isPangram = " + isPangram(input));
        System.out.println(input + ", isPangram = " + isPangram_stream(input));

        input = "java is a programming language";
        System.out.println(input + ", isPangram = " + isPangram(input));
        System.out.println(input + ", isPangram = " + isPangram_stream(input));
    }

    /*
     * ================ [Expected Approach] Using Visited Array - O(n) Time and O(MAX_CHAR) Space =====================
     *
     *  The idea is to create a visited array of size MAX_CHAR to mark whether a character is present in the string or not.
     *  Now, iterate through all the characters of the string and mark them as visited.
     *  Lowercase and Uppercase are considered the same.
     *  After iterating through all the characters, check whether all the characters are marked in visited array or not. If not then return false else return true.
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(26)
     */
    private static boolean isPangram(String input) {
        if(input.length() < 26){
            return false;
        }

        int index = 0;
        boolean[] visited = new boolean[26];

        for (int i = 0; i < input.length(); i++) {
            if ('a' <= input.charAt(i) && input.charAt(i) <= 'z') {
                index = input.charAt(i) - 'a';
            } else if ('A' <= input.charAt(i) && input.charAt(i) <= 'Z') {
                index = input.charAt(i) - 'A';
            }
            visited[index] = true;
        }

        for (int i = 0; i < 26; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPangram_stream(String input) {
        long c = input.toLowerCase().chars()
                .filter(ch -> ch >= 'a' && ch <= 'z')
                .distinct()
                .count();
        return c == 26;
    }

}
