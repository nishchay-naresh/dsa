package com.nishchay.ds.string.a07imp;

import java.util.Arrays;
import java.util.Comparator;

/*
 *	====================================== 443. String Compression =================================================
 *
 * Merge then Sort Two String
 *      sort based on length,
 *      if length is same go for alphabet based sorting
 *
 * Examples:
 *              Input:
 *                      String sentence1 = "apple mango banana";
 *                      String sentence2 = "kiwi cherry grape papaya";
 *              Output:
 *                      kiwi apple grape mango banana cherry papaya
 *
 * clean string + sorting + comparator problem
 *
 * been asked in Accuver consulting
 * */
public class StringMergeAndSort {

    public static void main(String[] args) {

        String sentence1 = "apple mango banana";
        String sentence2 = "kiwi cherry grape papaya";

        String result = mergeAndSort(sentence1, sentence2);
        System.out.println(result);
    }

    /*
     *
     *	You need to:
     *		1.	Merge both strings
     *		2.	Split into words
     *		3.	Sort using:
     *				Primary → word length
     *				Secondary → alphabetical order
     *		4.	Join back into a sentence
     *
     *        //  Comparator logic
     *        Comparator<String> lengthComparator = (String a, String b) -> {
     *            if (a.length() != b.length()) {
     *                return Integer.compare(a.length(), b.length()); // sort by length
     *            }
     *            return a.compareTo(b); // lexicographical
     *        };
     *
     * */
    private static  String mergeAndSort(String s1, String s2) {

        String merged = s1 + " " + s2;
        String[] words = merged.split(" ");

        // Step 3: Sort using custom comparator
        Comparator<String> lengthComparator =
                Comparator.comparingInt(String::length)
                        .thenComparing(String::compareTo);
        Arrays.sort(words, lengthComparator);

        // Step 4: Join back
        return String.join(" ", words);
    }
}
