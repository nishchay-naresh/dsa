package com.nishchay.math.a01bigners;

/*
 *  sort 3 integers using Math.max or Math.min and without using inbuilt sort methods or operators like (>, <).
 *
 * Given three integers, print them in sorted order without using if condition.
 *	Example 1:
 *	Input  : a = 3, b = 2, c = 9
 *	Output : 2 3 9
 *
 *	Example 2:
 *	Input  : a = 4, b = 1, c = 9
 *	Output : 1 4 9
 *
 * https://www.geeksforgeeks.org/sort-3-integers-without-using-condition-using-max-function/
 * */
public class Sort3NoWithoutConditionOp {

    public static void main(String[] args) {

        int a = 4, b = 1, c = 9;
        printSorted(a, b, c);

        a = 20;
        b = 30;
        c = 10;
        printSorted(a, b, c);
    }

    private static void printSorted(int a, int b, int c) {

        int max = Math.max(a, Math.max(b, c));
        int min = -Math.max(-a, Math.max(-b, -c));

        int mid = (a + b + c) - (max + min);

        System.out.println(min + " " + mid + " " + max);
    }


}
