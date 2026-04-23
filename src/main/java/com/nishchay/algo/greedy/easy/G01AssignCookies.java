package com.nishchay.algo.greedy.easy;

import java.util.Arrays;

/*
 * ============================================== Assign Maximum Cookies ========================================
 *
 * Given two arrays, greed[] and cookie[] such that
 * greed[i] denotes the minimum cookie size wanted by ith child and
 * cookie[i] denotes the size of ith cookie
 * we have to find the maximum number of children that can be satisfied by assigning them cookies, with each child getting at most 1 cookie.
 * Note: A child will be satisfied if he is assigned a cookie of size at least equal to his greed.
 * In other words, the ith child will be satisfied with jth cookie only if greed[i] <= cookie[j].
 *
 * Examples:
 * 				Input: Input: greed[] = [1, 10, 3], cookie[] = [1, 2, 3]
 * 				Output: 2
 * 				Explanation: We can only assign  cookie to the first child and third child.
 *
 * 				Input: greed[] = [10,100], cookie[] = [1, 2]
 * 				Output: 0
 * 				Explanation: We can not assign cookies to children.
 *
 *              Input: greed[] = [1, 5, 3, 3, 4], cookie[] = [4, 2, 1, 2, 1, 3]
 *              Output: 3
 * 				Explanation:
 *                  The child with greed factor 1 can be satisfied with the cookie of size 1.
 *                  One child with greed factor 3 can be satisfied with the cookie of size 3.
 *                  One child with greed factor 4 can be satisfied with the cookie of size 4. Hence, the output is 3.
 *
 *
 * https://takeuforward.org/Greedy/assign-cookies
 * https://www.geeksforgeeks.org/dsa/assign-cookies/
 * https://leetcode.com/problems/assign-cookies/description/
 * */
class G01AssignCookies {

    public static void main(String[] args) {

        int[] greed = new int[]{1, 5, 3, 3, 4};
        int[] cookieSize = new int[]{4, 2, 1, 2, 1, 3};

        System.out.println("Array Representing Greed: " + Arrays.toString(greed));
        System.out.println("Array Representing Cookie Size: " + Arrays.toString(cookieSize));
        System.out.println("No. of kids assigned cookies: " + findSatisfiedChildrenCount(greed, cookieSize));
        System.out.println("------------------------------------------------------------------");

        greed = new int[]{1, 10, 3};
        cookieSize = new int[]{1, 2, 3};
        System.out.println("Array Representing Greed: " + Arrays.toString(greed));
        System.out.println("Array Representing Cookie Size: " + Arrays.toString(cookieSize));
        System.out.println("No. of kids assigned cookies: " + findSatisfiedChildrenCount(greed, cookieSize));
        System.out.println("------------------------------------------------------------------");


        greed = new int[]{10, 100};
        cookieSize = new int[]{1, 2};
        System.out.println("Array Representing Greed: " + Arrays.toString(greed));
        System.out.println("Array Representing Cookie Size: " + Arrays.toString(cookieSize));
        System.out.println("No. of kids assigned cookies: " + findSatisfiedChildrenCount(greed, cookieSize));
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
     *  Time Complexity     : O(n log n + m log m + m)
     *  Auxiliary Space     : O(1)
     */
    private static int findSatisfiedChildrenCount(int[] kids, int[] cookie) {
        int n = kids.length;
        int m = cookie.length;

        // Sort kids and cookie array
        Arrays.sort(kids);
        Arrays.sort(cookie);

        int c = 0; // Pointer for cookies - we always finish this array
        int k = 0; // Pointer for kids/children

        // Assign cookies greedily
        while (c < m && k < n) {
            if (kids[k] <= cookie[c]) {
                k++; // current child satisfied
            }
            c++; // always move to next cookie
        }
        return k;
    }
}