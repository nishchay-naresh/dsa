package com.nishchay.algo.recursion.basic;

/*
 *	Fibonacci numbers, commonly denoted Fn, form a sequence, called the Fibonacci sequence.
 *	Such that each number is the sum of the two preceding ones, starting from 0 and 1.
 *	That is,
 *
 *	        f(0) = 0, f(1) = 1,
 *	        and
 *	        f(n )= f(n-1) + f(n-2)
 *	        for n > 1
 *
 *	The beginning of the sequence is thus:
 *	0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, ...
 *------------------------------------------------------------------------------------------
 *  n 	=	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	...
 * xn 	=	0	1	1	2	3	5	8	13	21	34	55	89	144	233	377	...
 *
 * https://www.geeksforgeeks.org/java/java-fibonacci-series/
 * */
public class Fibonacci {

    public static void main(String[] args) {

        printFibonacciSeriesExec();
        System.out.println("\n=====================================================================");
        getNthTermInFibonacci();

    }

    private static void printFibonacciSeriesExec() {
        // printing fibonacci series till n
        // since loop is stating from 0, so n = n + 1 in n
        int n = 8 + 1; // 21
        printFibonacciIterative(n);
        System.out.println("\n------------------------------");
        printFibonacciRecursion(true, 0, 1, n);
        System.out.println("\n------------------------------");
        for(int i = 0; i < n; i++){
            System.out.print("  " + fibonacciRecursion(i));
        }
    }

    private static void getNthTermInFibonacci() {

        System.out.println("fibonacciRecursion(8)  - " + fibonacciRecursion(8));    // 21
        System.out.println("fibonacciRecursion(9)  - " + fibonacciRecursion(9));    // 34
        System.out.println("fibonacciRecursion(12) - " + fibonacciRecursion(12));   // 144
        System.out.println("fibonacciRecursion(20) - " + fibonacciRecursion(20));   // 6765
        System.out.println("fibonacciRecursion(22) - " + fibonacciRecursion(22));   // 17711
        System.out.println("fibonacciRecursion(40) - " + fibonacciRecursion(40));   // 102334155
    }

    /**
     * print fibonacci series using iteration
     * Time Complexity: O(N)
     * Auxiliary Space: O(1)
     * */
    private static void printFibonacciIterative(int n) {
        int t1, t2, t3;
        t1 = 0;
        t2 = 1;
        System.out.print("Series -  " + t1 + " " + t2);
        for (int i = 0; i < n - 2; i++) {
            t3 = t1 + t2;
            System.out.print(" " + t3);
            t1 = t2;
            t2 = t3;
        }
    }

    /**
     * Fibonacci series using recursion
     * */
    private static void printFibonacciRecursion(boolean isStart, int a, int b, int n) {

        int c;
        if (isStart && n >= 2) {
            System.out.print("Series -  " + a + " " + b);
            n = n - 2;
        }
        if (n > 0) {
            c = a + b;
            System.out.print(" " + c);
            a = b;
            b = c;
            n = n - 1;

            printFibonacciRecursion(false, a, b, n);
        }
    }

    /*
    private static int fibonacciRecursion1(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        return fibonacciRecursion1(n - 1) + fibonacciRecursion1(n - 2);
    }
    */


    private static int fibonacciRecursion(int n) {
        if (n <= 1)
            return n;
        else
            return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }



}
