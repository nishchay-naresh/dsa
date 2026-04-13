package com.nishchay.ds.string.a06med;


import java.util.Stack;

/*
 *	======================= Decode String ================================
 *
 * Given an encoded string, return its decoded string.
 * Decode a string recursively encoded as count followed by substring
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 *
 * Examples:
 *				Input: s = "3[b2[ca]]"
 *				Output: bcacabcacabcaca
 *				Explanation:
 *				Inner substring "2[ca]" breakdown into "caca".
 *				Now , new string becomes "3[bcaca]"
 *				Similarly "3[bcaca]" becomes "bcacabcacabcaca" which is final result.
 *
 *				Input: s = "3[ab]"
 *				Output: ababab
 *				Explanation: The substring "ab" is repeated 3 times, giving "ababab".
 *
 *	            Input: s = "3[a]2[bc]"
 *	            Output: "aaabcbc"
 *
 *	            Input: s = "3[a2[c]]"
 *	            Output: "accaccacc"
 *
 *	            Input: s = "2[abc]3[cd]ef"
 *	            Output: "abcabccdcdcdef"
 *
 * Constraints:
 *	1 <= s.length <= 30
 *	s consists of lowercase English letters, digits, and square brackets '[]'.
 *	S is guaranteed to be a valid input.
 *	All the integers in s are in the range [1, 300].
 *
 *
 * https://www.geeksforgeeks.org/dsa/decode-string-recursively-encoded-count-followed-substring/
 * https://leetcode.com/problems/decode-string/description/
 * */
public class DecodeString {

    public static void main(String[] args) {

        doPassTests();

        System.out.println("result - " + decodedString_2Stack("3[a]2[bc]"));

    }

    private static void doPassTests() {
        String[] inputs = {"3[a]2[bc]", "3[a2[c]]", "2[abc]3[cd]ef", "2[ab]", "2[ab]a", "3[b2[ca]]"};
        String[] outputs = {"aaabcbc", "accaccacc", "abcabccdcdcdef", "abab", "ababa", "bcacabcacabcaca"};


        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {
            result = result && decodedString_WithoutStack(inputs[i]).equals(outputs[i]);
            if (!result)
                System.out.println("Test failed for: " + inputs[i]);
            else
                System.out.println("Test passed for: " + inputs[i]);
        }
    }


    /*
     * ==== [Approach 1] Using Two Stacks - O(n) Time and O(n) Space ====
     * The idea is to use two stacks, one for integers and another for characters.
     * sudo code
     *
     * create numberStack, stringStack
     * iterate through the string
     * 	// if we get a number
     * 		push the number in numberStack
     * 	// if we get anything else than a ']'
     * 		push the character in stringStack
     * 	// if we get a '['
     * 		start popping from stringStack till '['
     * 		pop from numberStack and create a repeated string
     * 		push the repeated string in stringStack
     *
     * go through the stringStack again
     * 	create the result string
     *
     * */
    private static String decodedString_2Stack(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();
        String temp = "";
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int cnt = 0;

            // If Digit, convert it into a number and push it into the integer stack.
            if (Character.isDigit(s.charAt(i))) {
                while (Character.isDigit(s.charAt(i))) {
                    cnt = cnt * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                numStack.push(cnt);
            }

            // If closing bracket ']' is encountered
            else if (s.charAt(i) == ']') {
                temp = "";

                cnt = numStack.pop();

                // Pop elements till the opening bracket '[' is found
                // in the character stack.
                while (charStack.peek() != '[') {
                    temp = charStack.pop() + temp;
                }
                charStack.pop(); // Remove '['

                // Repeating the popped string 'temp' count number of times.
                StringBuilder repeated = new StringBuilder();
                for (int j = 0; j < cnt; j++) {
                    repeated.append(temp);
                }

                // Push it into the character stack.
                for (int j = 0; j < repeated.length(); j++) {
                    charStack.push(repeated.charAt(j));
                }
            } else {
                charStack.push(s.charAt(i));
            }
        }

        // Pop all the elements, make a string, and return.
        while (!charStack.isEmpty()) {
            res.insert(0, charStack.pop());
        }

        return res.toString();
    }

    /*
     * ==== [Approach 2] Without Using Stack - O(n) Time and O(n) Space ====
     * The idea is to use two stacks, one for integers and another for characters.
     * sudo code
     *
     * iterate through the string
     *   Whenever a closing bracket is encountered,
     *   we can extract the substring enclosed within the corresponding opening bracket,
     *   and the number of times it needs to be repeated,
     *   and append the resulting string to the current result.
     *
     * */
    private static String decodedString_WithoutStack(String input) {

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            // If the current character is not a closing
            // bracket, append it to the result string.
            if (input.charAt(i) != ']') {
                res.append(input.charAt(i));
            }

            // If the current character is a closing bracket
            else {

                // Create a temporary string to store
                // the substring within the corresponding
                // opening bracket.
                StringBuilder temp = new StringBuilder();
                while (res.length() > 0 && res.charAt(res.length() - 1) != '[') {
                    temp.insert(0, res.charAt(res.length() - 1));
                    res.deleteCharAt(res.length() - 1);
                }

                // Remove the opening bracket from the result string.
                res.deleteCharAt(res.length() - 1);

                // Extract the preceding number and convert it to an integer.
                StringBuilder num = new StringBuilder();
                while (res.length() > 0 && Character.isDigit(res.charAt(res.length() - 1))) {
                    num.insert(0, res.charAt(res.length() - 1));
                    res.deleteCharAt(res.length() - 1);
                }
                int p = Integer.parseInt(num.toString());

                // Append the substring to the result
                // string, repeat it to the required number
                // of times.
                for (int j = 0; j < p; j++) {
                    res.append(temp.toString());
                }
            }
        }

        return res.toString();
    }

}
