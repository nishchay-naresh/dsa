package com.nishchay.algo.greedy.easy;

import java.util.Arrays;

/*
 * ============================================== Lemonade Change ===========================================
 * You are selling bus tickets, each costing 5 coins.
 * Passengers are standing in a queue, and they are represented by an array arr[],
 * Where each element denotes the note a passenger uses to pay (either 5, 10, or 20).
 *
 * You must serve the passengers in the given order.
 * For each passenger, you need to provide the correct change so that the net payment equals 5 coins.
 * Determine whether it is possible to serve all passengers in the queue without running out of change.
 *
 * Note: You start with no coins at all.
 *
 *
 * Given an array representing a queue of customers and the value of bills they hold,
 * Determine if it is possible to provide correct change to each customer.
 * Customers can only pay with 5$, 10$ or 20$ bills, and we initially do not have any change at hand.
 * Return true, if it is possible to provide correct change for each customer otherwise return false.
 *
 * Examples:
 *				Input: arr[] = [5, 10, 5, 20]
 *				Output: true
 *				Explanation:
 *				            First pays 5 → no change.
 *				            Second pays 10 → give back 5.
 *				            Third pays 5 → no change.
 *				            Fourth pays 20 → give back 10 + 5.
 *
 *				Input: arr[] = [10, 5, 20]
 *				Output: false
 *				Explanation: First passenger pays with 10 but no 5 is available for change → cannot serve.
 *
 *				Input: arr[] = [5, 5, 5, 10, 20]
 *				Output: true
 *				Explanation:
 *				            First 3 customer pays 5 → no change.
 *				            Fourth pays 10 → give back 5.
 *				            Fifth pays 20 → give back 10 + 5.
 *
 *              Input: arr[] = [5, 5, 10, 10, 20]
 *				Output: false
 *
 *
 * https://takeuforward.org/Greedy/lemonade-change
 * https://www.geeksforgeeks.org/dsa/lemonade-stand-change-challenge/
 * https://leetcode.com/problems/lemonade-change/description/
 * */
class G02LemonadeChange {

    public static void main(String[] args) {

        int[] bills = new int[]{5, 5, 5, 10, 20};
        System.out.print("Queue of customers : " + Arrays.toString(bills));
        System.out.println("Is it possible to provide change for all customers? - " + lemonadeChange(bills));

        bills = new int[]{5, 5, 10, 10, 20};
        System.out.print("Queue of customers : " + Arrays.toString(bills));
        System.out.println("Is it possible to provide change for all customers? - " + lemonadeChange(bills));

        bills = new int[]{5, 10, 5, 20};
        System.out.print("Queue of customers : " + Arrays.toString(bills));
        System.out.println("Is it possible to provide change for all customers? - " + lemonadeChange(bills));

        bills = new int[]{10, 5, 20};
        System.out.print("Queue of customers : " + Arrays.toString(bills));
        System.out.println("Is it possible to provide change for all customers? - " + lemonadeChange(bills));
    }

    /*
     * ==================================== [Expected Approach] Use greedy approach  ===================================
     * ============= [Approach] Using Sorting and Two Pointer - O(nlogn + mlogm) Time and O(1) Space  =================
     *
     * The idea is to assign the smallest cookie to each child as per their kids such that the child gets satisfied.
     * So, we can sort both kids[] and cookie[] and start assigning the smallest cookies to the children with the smallest kids.
     *
     *	1.	Sort both the kids and cookie array.
     *			By sorting, we are able to pair the smallest cookies with the least greedy children, maximizing overall content.
     *	2.	Use two pointers, l and r to iterate through the cookie and kids arrays.
     *			These pointers represent the positions of the smallest available cookie and the least greedy child.
     *
     *	3.	We iterate through the arrays, checking if the current cookie can satisfy the current child’s kids. Ie. cookie[l] >= kids[r].
     *			If the current cookie can satisfy the current child’s kids, we move to the next child.
     *			Regardless of whether a child is satisfied or not, we move to the next cookie.
     *	4.	At the end, the value of r, represents the number of children that were satisfied as we increment it each time a child’s kids is satisfied.
     *		We return this value as the total number of satisfied children.
     *
     *
     *  Time Complexity     : O(n)
     *  Auxiliary Space     : O(1)
     */
    private static boolean lemonadeChange(int[] bills) {
        int five = 0; // Counter for $5 coin
        int ten = 0;  // Counter for $10 coin

        for (int bill : bills) {
            if (bill == 5) {
                five++; // accept $5 (no change needed)
            } else if (bill == 10) {
                if (five > 0) {
                    five--; // give one $5 as change
                    ten++;  // accept $10
                } else {
                    return false; // cannot give change
                }
            } else { // bill == 20
                if (five > 0 && ten > 0) {
                    five--; //  give one $5 as change
                    ten--;  //  give one $10 as change
                } else if (five >= 3) {
                    five -= 3; // give three $5 as change
                } else {
                    return false; // cannot give change
                }
            }
        }
        return true; // successfully gave change to all customers
    }
}