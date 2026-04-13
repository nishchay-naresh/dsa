package com.nishchay.ds.string.a03dups;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/*
 * ========================== Remove duplicates from a string ====================================
 * Given a string s which may contain lowercase and uppercase characters.
 * The task is to remove all duplicate characters from the string and find the resultant string.
 * Note: The order of remaining characters in the output should be the same as in the original string.
 *
 * Examples:
 *			Input: s = geeksforgeeks
 *			Output: geksfor
 *			Explanation: After removing duplicate characters such as e, k, g, s, we have string as "geksfor".
 *
 *			Input: s = HappyNewYear
 *			Output: HapyNewYr
 *			Explanation: After removing duplicate characters such as p, e, a, we have string as "HapyNewYr".
 *
 *			Input: s = "bcabc"
 *			Output: "abc"
 *
 *			Input: s = "cbacdcbc"
 *			Output: "acdb"
 *
 * https://www.geeksforgeeks.org/dsa/remove-duplicates-from-a-given-string/
 *
 * Been asked in RBC bank
 * Take a string input, remove all duplicate character from string
 * */
public class RemoveDuplicateCharsFromAString {

    public static void main(String[] args) {
        System.out.println("input = bcabc, output = " + removeAllDuplicate("bcabc")); //abc
        System.out.println("input = cbacdcbc, output = " + removeAllDuplicate("cbacdcbc")); //acdb
        System.out.println("input = geeksforgeeks, output = " + removeAllDuplicate("geeksforgeeks")); //geksfor
        System.out.println("input = HappyNewYear, output = " + removeAllDuplicate("HapyNewYr")); //HapyNewYr
    }

    /*
     * ================ [Expected Approach] Using Hashing - O(n) Time and O(n) Space =====================
     *
     * The idea is to use Hashing. Using Hash to identify and eliminate the duplicates
     *
     * ------------- combining both of the loop to one --------------
     *       Set<Character> set = new HashSet<>();
     *		for (char ch : s.toCharArray()) {
     *      	if (!set.contains(ch)) {
     *          	sb.append(ch);
     *          	set.add(ch);
     *      	}
     *      }
     *
     * Time complexity: O(n)
     * Auxiliary space: O(n)
     * */
    public static String removeAllDuplicate(String input){

        // need to have LinkedHashSet ot maintain the order
        Set<Character> set = new LinkedHashSet<>();
        for(char curr : input.toCharArray()){
            set.add(curr);
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : set) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String removeAllDuplicate_stream(String input){

        return input.chars()                          // IntStream
                .mapToObj(c -> (char) c)          // Stream<Character>
                .distinct()                           // remove duplicates
                .map(String::valueOf)                 // Stream<String>
                .collect(Collectors.joining());       // String

        /*
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
        */
    }

}
