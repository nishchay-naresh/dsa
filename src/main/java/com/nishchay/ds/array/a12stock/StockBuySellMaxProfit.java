package com.nishchay.ds.array.a12stock;

/*
 *	====================================== Stock Buy and Sell - Max one Transaction Allowed =================================================
 *
 * Given an array prices[] of length N, representing the prices of the stocks on different days.
 * The task is to find the maximum profit possible by buying and selling the stocks on different days when at most, one transaction is allowed.
 * Here one transaction means 1 buy + 1 Sell.
 *
 * Note: Stock must be bought before being sold.
 *
 * Examples:
 *				Input: prices[] = {7, 10, 1, 3, 6, 9, 2}
 *				Output: 8
 *				Explanation: Buy for price 1 and sell for price 9.
 *
 *				Input: prices[] = {7, 6, 4, 3, 1}
 *				Output: 0
 *				Explanation: Since the array is sorted in decreasing order, 0 profit can be made without making any transaction.
 *
 *				Input: prices[] = {1, 3, 6, 9, 11}
 *				Output: 10
 *				Explanation: Since the array is sorted in increasing order, we can make maximum profit by buying at price[0] and selling at price[n-1]
 *
 * https://java2blog.com/stock-buy-sell-to-maximize-profit/
 * https://www.geeksforgeeks.org/dsa/best-time-to-buy-and-sell-stock/
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * */
public class StockBuySellMaxProfit {

    public static void main(String[] args) {
        int[] prices;

        prices = new int[]{7, 10, 1, 3, 6, 9, 2};
        System.out.println("maxProfit_bruteforce(prices) = " + maxProfit_bruteforce(prices));   //8
        System.out.println("maxProfit_onePass(prices)    = " + maxProfit_onePass(prices));      //8

        prices = new int[]{14, 12, 70, 15, 99, 65, 21, 90};
        System.out.println("maxProfit_bruteforce(prices) = " + maxProfit_bruteforce(prices));   //87
        System.out.println("maxProfit_onePass(prices)    = " + maxProfit_onePass(prices));      //87

        prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println("maxProfit_bruteforce(prices) = " + maxProfit_bruteforce(prices));   //5
        System.out.println("maxProfit_onePass(prices)    = " + maxProfit_onePass(prices));      //5

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println("maxProfit_bruteforce(prices) = " + maxProfit_bruteforce(prices));   //0
        System.out.println("maxProfit_onePass(prices)    = " + maxProfit_onePass(prices));      //0
    }

    /*
     * ================ [Naive Approach] By exploring all possible pairs - O(n^2) Time and O(1) Space  =====================
     *
     *  The idea is to use two nested loops to explore all the possible ways to buy and sell stock.
     *  The outer loop decides the day to buy the stock, and the inner loop decides the day to sell the stock.
     *  The maximum difference between the selling price and buying price between every pair of days will be our answer.
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     */
    private static int maxProfit_bruteforce(int[] prices) {
        int n = prices.length;
        int profit = 0;

        // Explore all possible ways to buy and sell stock
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                profit = Math.max(profit, prices[j] - prices[i]);
            }
        }
        return profit;
    }

    /*
     * ================ [Expected Approach] One Traversal Solution - O(n) Time and O(1) Space  =====================
     *
     *  In order to maximize the profit, we need to minimize the cost price and maximize the selling price.
     *  So at every step, we keep track of the minimum buy price of stock encountered so far.
     *  For every price, we subtract with the minimum so far, and if we get more profit than previous profit, we update the profit.
     *
     *  Time Complexity     : O(n), as we are traversing the prices[] array only once.
     *  Space complexity    : O(1)
     */
    private static int maxProfit_onePass(int[] prices) {
        int minSoFar = prices[0];
        int profit = 0;

        // Update the minimum value seen so far if we see smaller
        for (int i = 1; i < prices.length; i++) {
            minSoFar = Math.min(minSoFar, prices[i]);

            // Update result if we get more profit
            profit = Math.max(profit, prices[i] - minSoFar);
        }
        return profit;
    }
}
