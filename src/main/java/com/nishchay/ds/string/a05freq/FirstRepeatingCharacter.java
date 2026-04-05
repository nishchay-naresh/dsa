package com.nishchay.ds.string.a05freq;

import java.util.HashSet;
import java.util.Set;

/*
 *	================================== Find the Earliest Repeating Character ==================================
 *	================================== Find the first repeated character in a string ==================================
 *
 * Given a string S of length n, the task is to find the earliest repeated character in it.
 * The earliest repeated character means, the character that occurs more than once and whose second occurrence has the smallest index.
 *
 * Given a string, Write a code to find the first repeated character in it.
 *
 * Examples:
 *		            Input: ch = “geeksforgeeks”
 *		            Output: e
 *		            Explanation: e is the first element that repeats
 *
 *		            Input: str = “hello geeks”
 *		            Output: l
 *		            Explanation:l is the first element that repeats
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-the-first-repeated-character-in-a-string/
 * https://javarevisited.blogspot.com/2021/10/how-to-find-first-recurring-character.html?m=1
 * */
public class FirstRepeatingCharacter {

    public static void main(String[] args) {

        System.out.println("firstRepeating(\"geeksforgeeks\") - " +  firstRepeating("geeksforgeeks"));  // e
        System.out.println("firstRepeating(\"hello geeks\")   - " +  firstRepeating("hello geeks"));    // l
        System.out.println("firstRepeating(\"racecars\") - " +  firstRepeating("racecars"));        // c
        System.out.println("firstRepeating(\"ZEBRA\") - " +  firstRepeating("ZEBRA"));              // $

        doTestsPass();
    }



    /*
     * ================ [Naive Approach] Using Two Nested Loops - O(n2) time and O(1) auxiliary space =====================
     * Applying 2 nested loop
     *
     *        for (int i = 0; i < n; i++) {
     *            for (int j = 0; j < i; j++) {
     *                if (s.charAt(i) == s.charAt(j)) {
     *                    return s.charAt(i);
     *                }
     *            }
     *        }
     * ================ [Efficient Approach] Using hashSet – O(n) Time and O(n) Space =====================
     *
     *  Logic - Using HashSet to track already seen characters
     *
     *  Traversal string and try to add each char in HashSet
     *  HashSet will help you to identify the 2nd occurrence of char
     *  First repeated character is returned immediately
     *
     *  Time Complexity     = O(n)
     *  Space complexity    = O(n)
     */
    private static char firstRepeating(String str) {
        Set<Character> set = new HashSet<>();
        for (char curr : str.toCharArray()){
            if(set.contains(curr)){
                return curr;
            }
            set.add(curr);
        }
        return '$';
    }

    /**
     * boolean doTestsPass()
     * Returns true if all tests pass. Otherwise returns false.
     */
    private static void doTestsPass() {

        String[] inputs = {"apple", "pen" ,"racecars", "ababdc", "simplest", "ABCDA", "KBCDYB", "ZONDO", "ZEBRA"};
        char[] outputs = {'p','\0', 'c', 'a', 's', 'A', 'B', 'O', '\0'};

        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {
            result = result && firstRepeating(inputs[i]) == outputs[i];
            if (!result)
                System.out.println("Test failed for: " + inputs[i]);
            else
                System.out.println("Test passed for: " + inputs[i]);
        }
    }
}