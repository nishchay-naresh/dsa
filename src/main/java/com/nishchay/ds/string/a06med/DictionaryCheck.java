package com.nishchay.ds.string.a06med;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * ==================================== Coderbyte: "Word Split" Algorithm ====================================
 * ==================================== Word Break ====================================
 *
 * Have the function WordSplit(strArr) read the array off strings stored in strArr, which will contain 2 elements:
 * the first element will be a sequence of characters,
 * and the second element will be a long string of comma-seperated words, in alphabetical order, that represents a dictionary of some arbitrary length.
 *
 * For example, strArr can be: ["hellocat", "apple,bat,cat,goodbye,hello,yellow,why"].
 *
 * Your goal is to determine if the first element in the input can be split into two words, where both words in the dictionary that is provided in the second input.
 * In this example, the first element can be split into two words: hello and cat because both of those words are in the dictionary.
 *
 * Your program should return the two words that exist in the dictionary separated by a comma.
 * So for the example above, your program should return hello,cat.
 * There will only be one correct way to split the first element of characters into two words.
 *  If there is no way to split string into two words that exist in the dictionary, return the string not possible.
 *  The first element itself will never exist in the dictionary as a real word.
 *
 * Examples -1
 * 		Input: new String[] {"baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"}
 * 		Output: base, ball
 *
 * Examples -2
 * 		Input: new String[] {"abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"}
 * 		Output: abcg, efd
 *
 *Examples -3
 * 		Input: new String[] {"hellocat", "apple, bat,cat,goodbye,hello,yellow,why"}
 * 		Output: hello, cat
 *Examples -4
 * 		Input: new String[] {"helloworld", "h,he,hell,hello,w,word,wild,mate,quit,z, friend"}
 * 		Output: Not Possible
 *
 * https://dev.to/krtb/codetoday-word-split-algorithm-coderbyte-1gl
 * https://leetcode.com/problems/word-break/description/
 * */
public class DictionaryCheck {

    public static void main(String[] args) {

        String[] input = new String[]{"baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"};
        System.out.println("Output - " + splitWords(input)); // base, ball

        input = new String[]{"abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"};
        System.out.println("Output - " + splitWords(input)); // abcg, efd

        input = new String[]{"hellocat", "apple,bat,cat,goodbye,hello,yellow,why"};
        System.out.println("Output - " + splitWords(input)); // hello, cat

        input = new String[]{"helloworld", "h,he,hell,hello,w,word,wild,mate,quit,z,friend"};
        System.out.println("Output - " + splitWords(input)); // Not Possible
    }

    /*
     * Try all possible splits of the target string and check if both parts exist in the dictionary.
     *
     * Time complexity - O(n)
     * Space complexity - O(n)
     * */
    public static String splitWords(String[] input) {

        String target = input[0];
        String[] words = input[1].replace(" ", "").split(",");

        Set<String> dictionary = new HashSet<>(Arrays.asList(words));

        // Try all possible splits
        for (int i = 1; i < target.length(); i++) {

            String left = target.substring(0, i);
            String right = target.substring(i);

            if (dictionary.contains(left) && dictionary.contains(right)) {
                return left + ", " + right;
            }
        }
        return "Not Possible";
    }
}
