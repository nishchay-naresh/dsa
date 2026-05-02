package com.nishchay.algo.twopointer.a05medium;

import java.util.Arrays;

/*
 *	====================================== 443. String Compression =================================================
 *
 * Given an array of character chars, compress it using the following algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of the array.
 *
 * You must write an algorithm that uses only constant extra space.
 *
 * Note: The characters in the array beyond the returned length do not matter and should be ignored.
 *
 * Examples:
 *				Input: chars = ["a","a","b","b","c","c","c"]
 *				Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 *				Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 *
 *				Input: chars = ["a"]
 *				Output: Return 1, and the first character of the input array should be: ["a"]
 *				Explanation: The only group is "a", which remains uncompressed since it's a single character.
 *
 *				Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *				Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 *				Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 *
 * https://leetcode.com/problems/string-compression/
 * https://www.geeksforgeeks.org/dsa/valid-compressed-string/
 * */
public class StringCompression {

    public static void main(String[] args) {

        char[] chars;

        chars = new char[] {'a','a','b','b','c','c','c'};
        System.out.println(Arrays.toString(chars) + ", compressed length = " + compress(chars));

        chars = new char[] {'a'};
        System.out.println(Arrays.toString(chars) + ", compressed length = " + compress(chars));

        chars = new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(Arrays.toString(chars) + ", compressed length = " + compress(chars));
    }


    /*
     * This is a classic two-pointer + in-place write problem.
     *
     *	------ Intuition --------
     *
     *	You need to:
     *		-	Traverse the array
     *		-	Identify groups of consecutive characters
     *		-	Write compressed result back into same array
     *
     *	Key constraints:
     *		-	No extra space (O(1))
     *		-	Modify input array in-place
     *
     *	Core Idea, Use two pointers:
     *		i → read pointer (iterate through array)
     *		write → write pointer (where to store result)
     *		For each group:
     *			Count how many times character repeats
     *			Write: character & count (if > 1)
     *
     *	------- Dry Run ---------
     *
     *	Input: [a, a, b, b, c, c, c]
     *	Steps:
     *			aa → a2
     *			bb → b2
     *			ccc → c3
     *	Output array becomes: [a, 2, b, 2, c, 3]
     *	Return length = 6
     *
     * Time complexity - O(n)
     * Space complexity - O(1)
     * */
     private static int compress(char[] chars) {
        int n = chars.length;
        int write = 0;  // position to write compressed result
        int i = 0;      // read pointer

        while (i < n) {
            char current = chars[i];
            int count = 0;

            // count occurrences of current character
            while (i < n && chars[i] == current) {
                i++;
                count++;
            }

            // write the character
            chars[write++] = current;

            // write count if > 1
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char c : countStr.toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        return write;
    }
}
