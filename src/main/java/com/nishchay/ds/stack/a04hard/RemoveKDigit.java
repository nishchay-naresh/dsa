package com.nishchay.ds.stack.a04hard;

import java.util.Stack;

/*
 *	=========== Lowest Number by Removing k digits| Leetcode #402===========
 *
 * Given a string s consisting of digits and an integer k, remove k digits from the s to form the smallest possible number,
 *  maintaining the order of the remaining digits. The resulting number should not have leading zeros.
 * If the number is empty after removing digits, return "0".
 *
 * Examples :
 *				Input: s = "4325043", k = 3
 *				Output: 2043
 *				Explanation: Remove digits to get the smallest possible number while maintaining order. The result after removing 3 digits is 2043
 *
 *				Input: s = "765028321", k = 5
 *				Output: 221
 *				Explanation: Remove 5 digits to form the smallest possible number. The result is 0221 and since we are not supposed to keep leading 0 s, we get 221
 *
 * 				Input: str = "4325043", n = 3
 * 				Output: "2043"
 *
 * 				Input: str = "765028321", n = 5
 * 				Output: "0221"
 *
 * 				Input: str = "121198", n = 2
 * 				Output: "1118"
 *
 * https://www.geeksforgeeks.org/dsa/build-lowest-number-by-removing-n-digits-from-a-given-number/
 * https://takeuforward.org/data-structure/remove-k-digits
 * https://leetcode.com/problems/remove-k-digits/
 *
 * */
public class RemoveKDigit {

    public static void main(String[] args) {

        System.out.println("removeKDigits(\"765028321\", 5) - " + removeKDigits("765028321", 5));
        System.out.println("removeKDigits(\"372181\", 2) - " + removeKDigits("372181", 2));
        System.out.println("removeKDigits(\"1001\", 2) - " + removeKDigits("1001", 2));
        System.out.println("removeKDigits(\"1234\", 1) - " + removeKDigits("1234", 1));
        System.out.println("removeKDigits(\"14301620\", 4) - " + removeKDigits("14301620", 4));
        System.out.println("removeKDigits(\"1230987\", 2) - " + removeKDigits("1230987", 2)); //10987
        System.out.println("removeKDigits(\"1230987\", 3) - " + removeKDigits("1230987", 3)); //987

        String nums = "541892"; // Input number as string
        int k = 2; // Number of digits to remove
        String ans = removeKDigits(nums, k);
        System.out.println("The smallest possible integer after removing k digits is: " + ans);
    }

    /*
     * keep the smallest digit at start - it's a greedy approach
     *
     * Algorithm:
     * 	Use a stack to store digits of the resulting number
     * 	Iterate through each digit of the input string
     * 		While the stack is not empty, the current digit is smaller than the top of the stack, and k is greater than 0, pop from the stack and decrement k
     * 		Push the current digit onto the stack
     * 	If k is still greater than 0 after the iteration, remove digits from the end of the stack
     * 	Collect the remaining digits from the stack to form the result
     * 	Remove any leading zeroes from the result
     * 	If the result is empty after removing zeroes, return "0"
     *
     * Edge Cases:
     *	If k == length of the input string, return "0" as all digits will be removed
     *	If the result contains leading zeroes, remove them before returning
     *	If no digits were removed during iteration and k is still greater than 0, remove the last k digits from the stack
     *
     *  Time complexity: O(N)
     *  Space complexity: O(N)
     *
     * Function to find the smallest possible integer after removing k digits
     * */
    private static String removeKDigits(String nums, int k) {
        // Stack to store digits
        Stack<Character> st = new Stack<>();

        // Traverse the given string
        for (int i = 0; i < nums.length(); i++) {
            char currDigit = nums.charAt(i);

            // Pop last digits if a smaller currDigit is found and k > 0
            while (!st.isEmpty() && k > 0 && st.peek() > currDigit) {
                st.pop(); // Remove the last currDigit
                k--; // Decrement k by 1
            }

            // Push the current currDigit
            st.push(currDigit);
        }

        // If more digits can be removed
        while (k > 0) {
            st.pop(); // Pop the last added digits
            k--; // Decrement k by 1
        }

        // Handle edge case: if stack is empty
        if (st.isEmpty())
            return "0";

        // StringBuilder to store the result
        StringBuilder res = new StringBuilder();
        // Add digits from stack to result
        while (!st.isEmpty()) {
            res.append(st.pop());
        }

        // Trim the leading zeros
        while (res.length() > 0 && res.charAt(res.length() - 1) == '0') {
            res.deleteCharAt(res.length() - 1);
        }

        // Reverse the string to get the correct number
        res.reverse();

        // If result is empty, return "0"
        if (res.length() == 0) return "0";

        // Return the result as a string
        return res.toString();
    }
}
