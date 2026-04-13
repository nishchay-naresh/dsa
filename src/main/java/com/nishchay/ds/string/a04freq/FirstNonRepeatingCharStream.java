package com.nishchay.ds.string.a04freq;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 *	====================================== First Unique(non-repeated) Character in a String =================================================
 *
 * Given a string s of lowercase English letters, the task is to find the first non-repeating character.
 * If there is no such character, return '$'.
 *
 * Examples:
 *				Input: s = "geeksforgeeks"
 *				Output: 'f'
 *				Explanation: 'f' is the first character in the string which does not repeat.
 *
 *				Input: s = "racecar"
 *				Output: 'e'
 *				Explanation: 'e' is the only character in the string which does not repeat.
 *
 *				Input: "aabbccc"
 *				Output: '$'
 *				Explanation: All the characters in the given string are repeated.
 *
 *              Input: "swiss"
 *				Output: 'w'
 *
 *              Input: "simplest"
 *				Output: 'i'
 *
 *              Input: "employee"
 *				Output: 'm'
 *
 * */

public class FirstNonRepeatingCharStream {

    public static void main(String[] args) {

        System.out.println("findFirstUniqueChar(geeksforgeeks)  = " + findFirstUniqueChar("geeksforgeeks"));
        System.out.println("findFirstUniqueChar(racecar)        = " + findFirstUniqueChar("racecar"));
        System.out.println("findFirstUniqueChar(aabbccc)        = " + findFirstUniqueChar("aabbccc"));
        System.out.println("findFirstUniqueChar(swiss)          = " + findFirstUniqueChar("swiss"));
        System.out.println("findFirstUniqueChar(simplest)       = " + findFirstUniqueChar("simplest"));
        System.out.println("findFirstUniqueChar(employee)       = " + findFirstUniqueChar("employee"));

        System.out.println("--------------------------------------------------");
        System.out.println(removeDuplicateChars("banana"));      // ban
    }

    /*
     * ================ frequency based approach, java8 groupBy() apply =====================
     *
     *  -------------- Intuition ----------------
     * We need:
     *      Count frequency of each character
     *      Preserve order (important ❗)
     *      Return first char with frequency = 1
     *
     * Key idea: use LinkedHashMap (maintains insertion order)
     *
     *
     *  Time Complexity     = O(n)
     *  Space complexity    = O(n)
     */
    private static char findFirstUniqueChar(String input) {

        Map<Character, Long> freqMap = input.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        Character firstUniqueChar = freqMap.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().orElse('$');

        return firstUniqueChar;
    }

    /*
     *  Removing duplicate chars keeping only one copy
     *  String  ->  List<Character> -> String
     * */
    private static String removeDuplicateChars(String input) {
        // String to List<Character>
        List<Character> list = input.chars()
                .mapToObj(c -> (char) c) // Convert IntStream to Stream<Character>
                .filter(c -> !Character.isWhitespace(c))
                .distinct()
                .collect(Collectors.toList());

        // List<Character> to String
        String result = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        return result;
    }
}
