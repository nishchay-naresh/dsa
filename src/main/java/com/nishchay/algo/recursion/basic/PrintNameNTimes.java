package com.nishchay.algo.recursion.basic;

/*
 * Given an integer n, write a recursive function to print your name n times.
 *
 * Examples:
 * 			Input: n = 3
 * 			Output: Java Java Java
 * 			Explanation: Name is printed 3 times.
 *
 * 			Input: n = 1
 * 			Output: Java
 * 			Explanation: Name is printed once.
 *
 *
 *  https://takeuforward.org/recursion/print-name-n-times-using-recursion/
 * */

public class PrintNameNTimes {

    public static void main(String[] args) {
        int n = 5;
        String name = "Java";

        printName(name, n);
        printName(name, 0, n);
    }

    private static void printName(String name, int n) {
        if (n == 0){
            return;
        }
        System.out.println(name);
        printName(name, --n);
    }

    private static void printName(String name, int count, int N) {
        if (count == N)
            return;
        System.out.println(name);
        printName(name, count + 1, N);
    }
}
