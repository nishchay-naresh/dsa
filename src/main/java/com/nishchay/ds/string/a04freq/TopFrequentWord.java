package com.nishchay.ds.string.a04freq;

import java.util.*;


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
public class TopFrequentWord {

    public static void main(String[] args) {
        mostFrequentStrEx();
        mostFrequentStrExcludeBanedEx();
    }


    private static void mostFrequentStrEx() {
        String[] strArray;

        strArray = new String[]{"geeks", "for", "geeks", "a",
                "portal", "to", "learn", "can", "be",
                "computer", "science", "zoom", "yup",
                "fire", "in", "be", "data", "geeks"};

        System.out.println("Most frequent word is - " + mostFrequent(strArray));

        strArray = new String[]{"hello", "world"};
        System.out.println("Most frequent word is - " + mostFrequent(strArray));
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
    private static String mostFrequent(String[] arr) {

        Map<String, Integer> freqMap = new HashMap<>();

        // Step 1: Count frequency
        for (String word : arr) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        // Step 2: Find max with tie-breaking
        String result = "";
        int maxFreq = 0;

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();

            if (freq > maxFreq) {
                maxFreq = freq;
                result = word;
            }
            // Tie-breaking: lexicographically larger word
            else if (freq == maxFreq && word.compareTo(result) > 0) {
                result = word;
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
}
