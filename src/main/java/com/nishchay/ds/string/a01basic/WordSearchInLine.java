package com.nishchay.ds.string.a01basic;

import java.util.*;

/*
 *	================================== Whole-Word Search Across Multiple Lines ==================================
 *
 * You are given multiple lines of text and a search key.
 * You must return the line that contains the search key as a whole word (not as part of another word).
 *
 * Implement a word search in over multiple lines of text and return the matching line
 * Word search should look for an absolute word occurrence
 *
 * Examples:
 *              Str1 = "this is a test20"
 *              Str2 = "testing for content indexing"
 *              Str3 = "testing for search text in test"
 *              Strn = "……"
 *              Search key = "test" (whole word search) returns str1
 *
 * */
public class WordSearchInLine {

    public static void main(String[] args) {

        String[] lines = {
                "this is a test for 20 min",
                "testing for content indexing",
                "testing for search text in test",
                "Intest one should answer best of his ability"
        };

        String searchKey = "test";

        System.out.println(findLineWithWord(lines, searchKey));
    }

    /*
     *  Using String.equals() for search
     *
     *	-	Go through each line
     *	-	Split the line into words
     *	-	Compare each word with the search key
     *	-	If equal → return that line
     * */
    private static String findLineWithWord(String[] lines, String searchKey) {

        for (String line : lines) {

            // Split line into words (non-letter characters as separators)
            String[] words = line.split("[^a-zA-Z]+");

            for (String word : words) {
                if (word.equals(searchKey)) {
                    return line; // whole word found
                }
            }
        }
        return "Not Found";
    }


}
