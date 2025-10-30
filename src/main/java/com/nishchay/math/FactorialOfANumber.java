package com.nishchay.math;


/*
 * ========= Factorial of a Number =========
 *
 * 	Examples:
 * 		Input: n = 5
 * 		Output: 120
 * 		Explanation: 5! = 5 * 4 * 3 * 2 * 1 = 120
 *
 * 		Input: n = 4
 * 		Output: 24
 * 		Explanation: 4! = 4 * 3 * 2 * 1 = 24
 * */
public class FactorialOfANumber {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(factorial(n));
        n= 6;
        System.out.println(factorialRecursive(n));
    }

    //  Iterative Solution O(n) Time and O(1) Space
    private static int factorial(int n) {
        int res = 1, i;
        for (i = 2; i <= n; i++)
            res *= i;
        return res;
    }

    /*
     *  Recursive Solution O(n) Time and O(n) Space
     *  Time: O(n) — The function makes n recursive calls.
     *  Space: O(n) — Due to the call stack used in recursion.
     *
     * */
    private static int factorialRecursive(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
}
