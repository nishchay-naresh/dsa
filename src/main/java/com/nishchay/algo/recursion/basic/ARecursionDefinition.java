package com.nishchay.algo.recursion.basic;

/*
 * Recursion is a technique to solve big problems by breaking them into smaller, similar problems.
 *
 * Recursion
 * 	    when a function calls itself
 * 	    until a specific condition is met
 *
 * Ex :
 *      factorial(n) = n * factorial(n-1)
 *
 * What is Tail Recursion
 *  Tail recursion is defined as a recursive function in which the recursive call is the last statement that is executed by the function.
 *  So basically nothing is left to execute after the recursion call.
 *
 * */
public class ARecursionDefinition {

    public static void main(String[] args) {
        System.out.println("factorial(5)  = " + factorial(5));
        System.out.println("-----------------------------------");
        System.out.println("sumOfFirstN(5, 0) = " + sumOfFirstN(5, 0));
        System.out.println("sumOfFirstN(5)    = " + sumOfFirstN(5));
        System.out.println("sumOfFirstN(5, 2) = " + sumOfFirstN(5, 2));
    }

    // function to find - factorial of a number
    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // function to find - sum of first n numbers
    private static int sumOfFirstN(int n) {
        if (n == 0) {
            return 0;
        }
        return n + sumOfFirstN(n - 1);
    }

    // Here passing sum as method argument
    private static int sumOfFirstN(int n, int sum) {
        if (n == 0) {
            return 0;
        }
        return sum + n + sumOfFirstN(n - 1);
    }

}
