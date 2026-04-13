package com.nishchay.ds.string.a05anagram;

import java.util.*;
import java.util.stream.Collectors;

/*
 *	================================== Group Anagrams Together ==================================
 *
 * Given an array of words arr[], the task is to group strings that are anagrams.
 * An anagram is a word or phrase formed by rearranging the letters of another, using all the original letters exactly once.
 *
 * Examples:
 *				Input: arr[] = ["act", "god", "cat", "dog", "tac"]
 *				Output: [["act", "cat", "tac"], ["god", "dog"]]
 *				Explanation: There are 2 groups of anagrams "god", "dog" make group 1. "Act", "cat", "tac" make group 2.
 *
 *				Input: arr[] = ["listen", "silent", "enlist", "abc", "cab", "bac", "rat", "tar", "art"]
 *				Output: [["abc", "cab", "bac"], ["listen", "silent", "enlist"],["rat", "tar", "art"]]
 *				Explanation:
 *				    Group 1: "abc", "bac" and "cab" are anagrams.
 *				    Group 2: "listen", "silent" and "enlist" are anagrams.
 *				    Group 3: "rat", "tar" and "art" are anagrams.
 *
 *				Input: arr[] = ["eat", "tea", "tan", "ate", "nat", "bat]
 *				Output: [["bat], ["not", "tan"], ["ate", "eat", "tea"]]
 *
 *				Input: arr[] = ["keen", "cars", "knee", "scar", "a", "silent", "listen"]
 *				Output: [["keen", "knee"], ["cars", "scar"], ["silent" "listen"], ["a"]]
 *
 *
 *	What interviewers want to test with this question
 *	- Your understanding of HashMap and how to design a unique key
 *	- Your ability to convert a string into a signature for grouping
 *	- Your knowledge of string manipulation and character sorting
 *	- Your capability to write a clean, optimized solution
 *	- Your understanding of time complexity and why certain approaches are better
 *
 * https://www.geeksforgeeks.org/dsa/given-a-sequence-of-words-print-all-anagrams-together/
 * https://leetcode.com/problems/group-anagrams/description/
 * https://github.com/nikoo28/java-solutions/blob/master/src/main/java/leetcode/medium/GroupAnagrams.java
 * */

public class GroupAnagrams {

    public static void main(String[] args) {

        String[] strArray;

        strArray = new String[]{"act", "god", "cat", "dog", "tac"};
        System.out.println("Anagram groups = " + groupAnagramsByFrequency(strArray));
        System.out.println("Anagram groups = " + groupAnagramsBySorting(strArray));

        strArray = new String[]  {"listen", "silent", "enlist", "abc", "cab", "bac", "rat", "tar", "art"};
        System.out.println("Anagram groups = " + groupAnagramsByFrequency(strArray));
        System.out.println("Anagram groups = " + groupAnagramsBySorting(strArray));

        strArray = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Anagram groups = " + groupAnagramsByFrequency(strArray));

        strArray = new String[]{"keen", "cars", "knee", "scar", "a", "silent", "listen"};
        System.out.println("Anagram groups = " + groupAnagramsByFrequency(strArray));

        strArray = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Anagram groups = " + groupAnagramsByStream(strArray));
    }


    /*
     * ================ [Naive Approach] Using sorted words as keys -  Time and O(n*k) Space  =====================
     * The idea is that if we sort two strings which are anagrams of each other, then the sorted strings will always be the same.
     * So, we can maintain a hash map with the sorted strings as keys and the index of the anagram group in the result array as the value.
     *
     *  Time Complexity     : O(n * k log(k))
     *  Space complexity    : O(n*k)
     */
    private static List<List<String>> groupAnagramsBySorting(String[] strArray) {

        if (strArray == null || strArray.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> stringAnagramsMap = new HashMap<>();
        for (String curr : strArray) {
            char[] arr = curr.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if (!stringAnagramsMap.containsKey(key)){
                stringAnagramsMap.put(key, new ArrayList<>());
            }
            stringAnagramsMap.get(key).add(curr);
        }
        return new ArrayList<>(stringAnagramsMap.values());
    }

    /*
     * ================ [Expected Approach] Using Frequency as keys - O(n*k) Time and O(n*k) Space  =====================
     *
     *  The idea is that if two strings are anagrams of each other, then the frequency of all characters in both strings will always be the same.
     *  Ex - "keen", "knee" - its frequency string => e2k1n1
     *  we will use this frequency string as a key to group all the words in a List<String> in a Map<String, List<String>>
     *
     *
     *  Time Complexity     : O(n*k)
     *  Space complexity    : O(n*k)
     */
    private static List<List<String>> groupAnagramsByFrequency(String[] strArray) {

        // Check for empty inputs
        if (strArray == null || strArray.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> frequencyStringsMap = new HashMap<>();
        for (String curr : strArray) {
            String frequencyString = getFrequencyString(curr);
            frequencyStringsMap.computeIfAbsent(frequencyString, k -> new ArrayList<>()).add(curr);
        }
        return new ArrayList<>(frequencyStringsMap.values());
    }

    public static String getFrequencyString(String str) {

        int[] freq = new int[26];

        for (char c : str.toCharArray()) {
            freq[c - 'a']++;
        }
        // excluding the char in key -> even more optimized forming the key only based on a frequency array
        //String key = Arrays.toString(freq);

        // Start creating the frequency string
        StringBuilder frequencyString = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                frequencyString.append((char)('a' + i)).append(freq[i]);
            }
        }
        return frequencyString.toString();
    }

    /*
     * Asked in Accolite
     *
     * Write code to group anagrams together ( one using Stream API and one without)
     * Input : ["eat", "tea", "tan", "ate", "nat", "bat"]
     * Output : ["bat"], ["nat", "tan"],["eat", "ate", "tea"]
     *
     * Both versions use the same logic: sort characters in each word → use as a map key.
     *
     * */
    private static List<List<String>> groupAnagramsByStream(String[] strArray) {
        List<String> words = Arrays.asList(strArray);

/*
        Map<String, List<String>> sortedStrKeyMap = words.stream()
                .collect(Collectors.groupingBy(word -> {
                    char[] chars = word.toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                }));
        return new ArrayList<>(sortedStrKeyMap.values());
*/

        Map<String, List<String>> freqStrKeyMap = words.stream()
                .collect(Collectors.groupingBy(word -> getFrequencyString(word)));

        return new ArrayList<>(freqStrKeyMap.values());
    }
}


