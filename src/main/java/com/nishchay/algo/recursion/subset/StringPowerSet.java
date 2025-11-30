package com.nishchay.algo.recursion.subset;

/*
 *	===========Recursive program to generate power set===========
 * Given a set represented as a string, write a recursive code to print all subsets of it.
 * The subsets can be printed in any order.
 *
 * Examples:
 *
 *			Input :  set = "abc"
 *			Output : "". "a", "b", "c", "ab", "ac", "bc", "abc"
 *
 *			Input : set = "abcd"
 *			Output : "" "a" "ab" "abc" "abcd" "abd" "ac" "acd"
 *			         "ad" "b" "bc" "bcd" "bd" "c" "cd" "d"
 *
 *
 * https://www.geeksforgeeks.org/recursive-program-to-generate-power-set/
 *
 * */
public class StringPowerSet {

    public static void main(String[] args) {

        String ip = "abc";
        int index = 0;
        String op = "";
        powerSet(ip, index, op);

    }


    private static void powerSet(String input, int index, String output) {
        int n = input.length();

        // base case
        if (index == n) {
            System.out.println(output);
            return;
        }

        // Two cases for every character
        // (i) We consider the character as part of current subset
        // (ii) We do not consider current character as part of current subset
        powerSet(input, index + 1, output + input.charAt(index));
        powerSet(input, index + 1, output);

    }

}

/*
 * io/p =>
 *	a
 *	ab
 *	abc
 *	ac
 *	b
 *	bc
 *	c
 *
 * */