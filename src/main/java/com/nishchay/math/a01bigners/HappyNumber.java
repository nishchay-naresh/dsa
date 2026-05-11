package com.nishchay.math.a01bigners;


import java.util.HashSet;
import java.util.Set;

/*
 * ================================== Happy Number =======================================
 *
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 * 		Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * 		Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * 		Those numbers for which this process ends in 1 are happy.
 *
 * Return true if n is a happy number, and false if not.
 *
 * Examples:
 *				Input: n = 19
 *				Output: True
 *						19 is Happy Number,
 *						1^2 + 9^2 = 82
 *						8^2 + 2^2 = 68
 *						6^2 + 8^2 = 100
 *						1^2 + 0^2 + 0^2 = 1
 *						As we reached to 1, 19 is a Happy Number.
 *
 *				Input: n = 7
 *				Output: True
 *						19 is Happy Number,
 *						7^2 = 49
 *						4^2 + 9^2 = 97
 *						9^2 + 7^2 = 130
 *						1^2 + 3^2 + 0^2 = 10
 *						1^2 + 0^2 = 1
 *						As we reached to 1, 7 is a Happy Number.
 *
 *				Input: n = 20
 *				Output: False
 *
 *				Input: n = 4
 *				Output: False
 *
 * First Few Happy Numbers: 1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100.
 *
 * https://www.geeksforgeeks.org/dsa/happy-number/
 * https://leetcode.com/problems/happy-number/description/
 * */
public class HappyNumber {

    public static void main(String[] args) {

        System.out.println("isHappyNumber(17) - " + isHappyNumber(17));
        System.out.println("isHappyNumber(9) - " + isHappyNumber(9));
        System.out.println("isHappyNumber(20) - " + isHappyNumber(20));
        System.out.println("isHappyNumber(4) - " + isHappyNumber(4));
        System.out.println("isHappyNumber(19) - " + isHappyNumber(19));
        System.out.println("isHappyNumber(31) - " + isHappyNumber(31));
        System.out.println("isHappyNumber(49) - " + isHappyNumber(49));
        System.out.println("isHappyNumber(79) - " + isHappyNumber(79));
        System.out.println("isHappyNumber(97) - " + isHappyNumber(97));

        System.out.println("isHappyNumber_fastAndSlowPtr(97) - " + isHappyNumber_fastAndSlowPtr(97));
        System.out.println("isHappyNumber_fastAndSlowPtr(13) - " + isHappyNumber_fastAndSlowPtr(13));
        System.out.println("isHappyNumber_fastAndSlowPtr(17) - " + isHappyNumber_fastAndSlowPtr(17));
    }

    /*
     * If we reach to same number which we have processed in the past.
     * We can conclude, it's not a HappyNumber
     *
     * Sequence for 58:
     * 5^2 + 8^2 = 25 + 64 = 89
     * 8^2 + 9^2 = 64 + 81 = 145
     * 1^2 + 4^2 + 5^2 = 1 + 16 + 25 = 42
     * 4^2 + 2^2 = 16 + 4 = 20
     * 2^2 + 0^2 = 4
     * 4^2 = 16
     * 1^2 + 6^2 = 37
     * 3^2 + 7^2 = 9 + 49 = 58 (Cycle repeats)
     *
     * Time Complexity: O(n*log(n)).
     * Auxiliary Space: O(n) since using extra set for storage
     *
     * */
    private static boolean isHappyNumber(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            n = digitSquareSum(n);
            if (n == 1)
                return true;
            if (set.contains(n))
                return false;
            set.add(n);
        }
    }

    // Utility method to return sum of square of digit of n
    private static int digitSquareSum(int n) {
        int num = 0;
        while (n != 0) {
            int digit = n % 10;
            num = num + (digit * digit);
            n = n / 10;
        }
        return num;
    }

    /*
     * We can solve this problem without using extra space and that technique can be used in some other similar problems also.
     * If we treat every number as a node and replacement by square sum digit as a link,
     * then this problem is same as finding a loop in a linklist :
     *
     * Time Complexity: O(n*log(n)).
     * Auxiliary Space: O(1).
     * */
    private static boolean isHappyNumber_fastAndSlowPtr(int n) {
        int slow, fast;

        //  initialize slow and fast by n
        slow = fast = n;
        do {
            //  move slow number by one iteration
            slow = digitSquareSum(slow);

            //  move fast number by two iteration
            fast = digitSquareSum(digitSquareSum(fast));

        } while (slow != fast);

        //  if both number meet at 1, then return true
        return (slow == 1);
    }

}
