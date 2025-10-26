package com.nishchay.math;

import java.util.ArrayList;

/*
 *  ========= Find all factors of a Positive Number =========
 *
 * Given a positive integer n, find all the distinct divisors of n.
 *
 * Examples:
 *	Input: n = 10
 *	Output: [1, 2, 5, 10]
 *	Explanation: 1, 2, 5 and 10 are the divisors of 10.
 *
 *	Input: n = 100
 *	Output: [1, 2, 4, 5, 10, 20, 25, 50, 100]
 *	Explanation: 1, 2, 4, 5, 10, 20, 25, 50 and 100 are divisors of 100.
 *
 * https://www.geeksforgeeks.org/dsa/find-all-factors-of-a-natural-number/
 * */
public class AllFactors {

    public static void main(String[] args) {
        int number = 100;
        System.out.println("divisors of " +  number + " are = " + printDivisors(number));
        System.out.println("divisors of " +  number + " are = " + getDivisors(number));
    }

    // [Naive Approach] Iterating till n - O(n) Time and O(1) Space
    private static ArrayList<Integer> printDivisors(int n) {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }

    /*
     *  [Expected Approach] Finding all factors in pairs - O(sqrt(n)) Time and O(1) Space
     * If we look careful, all the divisors of a number appear in pairs.
     * For example, if n = 100, then the divisor pairs are: (1, 100), (2, 50), (4, 25), (5, 20), (10, 10).
     *
     * We need to be careful in cases like (10, 10)
     * i.e., when both divisors in a pair are equal (which happens when n is a perfect square). In such cases, we should include that divisor only once.
     * Using this fact, we can optimize our program significantly.
     * */
    private static ArrayList<Integer> getDivisors(int n) {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                // If divisors are equal, add only once
                if (n / i == i) {
                    divisors.add(i);
                }
                // Otherwise add both
                else {
                    divisors.add(i);
                    divisors.add(n / i);
                }
            }
        }
        return divisors;
    }
}
