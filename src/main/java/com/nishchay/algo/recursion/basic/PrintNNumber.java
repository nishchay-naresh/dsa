package com.nishchay.algo.recursion.basic;

/*
 *  Print 1,2,3,4…10 (no loop, no array) using recursion
 *
 *  https://www.geeksforgeeks.org/dsa/print-1-to-n-without-using-loops/
 *  https://www.geeksforgeeks.org/dsa/print-n-to-1-without-loop/
 * */
public class PrintNNumber {

    public static void main(String[] args) {

        int n = 10;
        System.out.println("\n------------printing  1 to n -------------");
        printNFrom1(n);
        System.out.println("\n------------printing  n to 1 -------------");
        printNFromN(n);
    }

    /*
     * recursive solution
     * printing 1 to n
     *
     *  10 -> 9 -> 8->......1 first calling all recursive calls, then while returning printing it
     *  print(1)-> print(2)-> print(3)->......10
     * */
    private static void printNFrom1(int n) {
        if (n == 0) {
            return;
        }
        printNFrom1(n - 1);
        System.out.print(n + " ");
    }

    /*
     * recursive solution
     * printing  n to 1
     * 1 -> 2 -> 3 ....10
     * 10 print(10)-> 9 print(9)-> 8 print(8)->......1
     * */
    private static void printNFromN(int n) {
        if (n == 0) {
            return;
        }
        System.out.print(n + " ");
        printNFromN(n - 1);
    }

}
