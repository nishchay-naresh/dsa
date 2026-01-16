package com.nishchay.math.a01bigners;

/*
 *  ============================ Lucky Number =============================
 *
 *	The sequence of natural numbers or subset of integers that we get after removing second, third, fourth, fifth, and so on
 *  number respectively from the sequence.
 *  By applying the process there still remains some numbers indefinitely in the sequence such numbers are known as lucky numbers.
 *
 *
 *	Examples :
 *
 *		1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, ….
 *		Let's remove every second number (2, 4, 6, 8, 10, ……) from the above sequence, we get:
 *		1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25,
 *		Let's remove every third number (5, 11, 17, 23, ……) from the above sequence, we get:
 *		1, 3, 7, 9, 13, 15, 19, 21, 25
 *      remove every fourth number
 *      1, 3, 7, 13, 15, 19, 25
 *      remove every fifth number
 *      1, 3, 7, 13, 19, 25
 *
 * https://www.geeksforgeeks.org/lucky-numbers/
 * */
public class LuckyNumber {

    public static void main(String[] args) {


//        System.out.println("isLucky(9) - " + isLucky(9)); // false
//        System.out.println("isLucky(13) - " + isLucky(13)); // true
        System.out.println("isLucky(21) - " + isLucky(25)); // true

    }


    /*
     * np = 6, counter=2
     *	pos-	1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
     *	nos-	1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
     * After removing every 2nd element : np = 8, counter=3
     *	pos-	1, 2, 3, 4, 5,  6,  7,  8
     *	nos-	1, 3, 5, 7, 9, 11, 13, 15
     * After removing every 3rd element : np = 6, counter=4
     *	pos-	1, 2, 3, 4, 5,  6,
     *	nos-	1, 3, 7, 9, 13, 15
     * After removing every 3rd element : np = 5, counter=5
     *	pos-	1, 2, 3,  4, 5,
     *	nos-	1, 3, 7, 13, 15
     *
     * final = 1,3,7,13..
     *          nextPosition = n - n/counter;
     *
     * 16-16/2 = 8
     * 8-8/3 = 6
     * 6-6/4 = 5
     * (6<5) 5-5/6 = 5
     * */
    private static int counter = 2;

    private static boolean isLucky(int n) {
        if (counter > n)
            return true;
        if (n % counter == 0)
            return false;

        /*calculate next position of input no.*/
        int nextPosition = n - (n / counter);

        counter++;
        return isLucky(nextPosition);
    }

}
