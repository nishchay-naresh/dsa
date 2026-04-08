package com.nishchay.ds.string.a05freq;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/*
 *	====================================== Most frequent word in an array of strings =================================================
 *
 * Given an array of words arr[],  The task is to find the most occurring word in arr[].
 *
 * Examples:
 *				Input : arr[] = {"geeks", "for", "geeks", "a",
 *				                        "portal", "to", "learn", "can",
 *				                        "be", "computer", "science",
 *				                        "zoom", "yup", "fire", "in",
 *				                        "be", "data", "geeks"}
 *				Output : geeks
 *				Explanation : "geeks" is the most frequent word in the given array occurring 3 times
 *
 *				Input:  arr[] = {"hello", "world"}
 *				Output: world
 *
 * https://www.geeksforgeeks.org/dsa/frequent-word-array-strings/
 * https://leetcode.com/problems/most-common-word/description/
 * */
public class MostFrequentWord {

    public static void main(String[] args) {
        mostFrequentStrEx();
        mostFrequentStrExcludeBanedEx();
        nMostFrequentCharEx();
    }



    private static void nMostFrequentCharEx() {
        System.out.println("nMostFrequentChar(\"bananassss\", 3) - " + nMostFrequentChar("bananassss", 3));
        System.out.println("nMostFrequentChar(\"bananassss\", 2) - " + nMostFrequentChar("bananassss", 2));
        System.out.println("nMostFrequentChar(\"java perl rep\", 1) - " + nMostFrequentChar("java perl rep", 1));
    }

    private static void mostFrequentStrEx() {
        String[] strArray;

        strArray = new String[]{"geeks", "for", "geeks", "a",
                "portal", "to", "learn", "can", "be",
                "computer", "science", "zoom", "yup",
                "fire", "in", "be", "data", "geeks"};

        System.out.println("Most frequent word is - " + mostFrequentString(strArray));

        strArray = new String[]{"hello", "world"};
        System.out.println("Most frequent word is - " + mostFrequentString(strArray));
    }


    /*
     * ================ [Efficient Approach] Using HashMap – O(2*n) Time and O(n) Space =====================
     *
     *  First traversal array and record the frequency of each String in HashMap
     *  Second traversal this frequency map to get the most frequent string
     *
     *  Time Complexity     = O(2*n) = O(n)
     *  Space complexity    = O(n)
     */
    private static String mostFrequentString(String[] strArray) {

        Map<String, Integer> feqMap = F00StringFrequencyUtility.getFrequencyMap(strArray);

        String result = "";
        int max  = 0;
        for (Map.Entry<String, Integer> currEntry : feqMap.entrySet()) {
            // Check for word having highest frequency
            if (currEntry.getValue() > max ) {
                max  = currEntry.getValue();
                result = currEntry.getKey();
            }
        }
        return result;
    }

    private static void mostFrequentStrExcludeBanedEx() {

        String paragraph;
        String[] banned;

        paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        banned = new String[]{"hit"};
        System.out.println("mostCommonWord(-,-) = " + mostCommonWord(paragraph, banned));

        paragraph = "a.";
        banned = new String[]{};
        System.out.println("mostCommonWord(-,-) = " + mostCommonWord(paragraph, banned));
    }



    /*
     *	-	Convert paragraph to lowercase and Replace punctuation with spaces
     *	-	Split into words
     *	-	Store banned words in a Set
     *	-	Count frequencies using a Map
     *	-	Return the word with the highest count
     * */
    private static String mostCommonWord(String paragraph, String[] banned) {

        // Step 1: store banned words
        Set<String> bannedSet = new HashSet<>();
        for (String b : banned) {
            bannedSet.add(b.toLowerCase());
        }

        // Step 2: clean paragraph (remove punctuation)
        paragraph = paragraph.toLowerCase().replaceAll("[^a-z]", " ");

        // Step 3: count word frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : paragraph.split("\\s+")) {
            if (word.isEmpty() || bannedSet.contains(word)) {
                continue;
            }
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Step 4: find most frequent word
        String result = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    private static char nMostFrequentChar(String str, int n) {

        Map<Character, Long> counter = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (counter.size() < n) {
            throw new IllegalArgumentException("Not enough different characters.");
        }

        return counter.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .get(n - 1);

    }

}
