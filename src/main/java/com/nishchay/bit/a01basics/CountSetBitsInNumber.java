package com.nishchay.bit.a01basics;

/*
 *	====================================== Count set bits in an integer =================================================
 *
 *	Given a positive number n count the set bit .
 *
 *	Examples:
 *		    Input : n = 6
 *		    Output : 2
 *		    Binary representation of 6 is 110 and has 2 set bits
 *
 *		    Input : n = 13
 *		    Output : 3
 *		    Binary representation of 13 is 1101 and has 3 set bits
 *
 * https://www.geeksforgeeks.org/dsa/count-set-bits-in-an-integer/
* */
public class CountSetBitsInNumber {

    public static void main(String[] args) {

        System.out.println("countSetBits(9)  - " + countSetBits_countingEachBits(9)); //2
        System.out.println("countSetBits(6)  - " + countSetBits_countingEachBits(6)); //2
        System.out.println("countSetBits(13) - " + countSetBits_countingEachBits(13)); //3
        System.out.println("countSetBits(15) - " + countSetBits_countingEachBits(15)); //4

        System.out.println("countSetBits(9)  - " + countSetBits_countingOneSetBits(9)); //2
        System.out.println("countSetBits(6)  - " + countSetBits_countingOneSetBits(6)); //2
        System.out.println("countSetBits(13) - " + countSetBits_countingOneSetBits(13)); //3
        System.out.println("countSetBits(15) - " + countSetBits_countingOneSetBits(15)); //4

        // Using java library
        System.out.println("countSetBits(9)  - " + Integer.bitCount(9));    //2
        System.out.println("countSetBits(13) - " + Integer.bitCount(13));   //3
    }


    /*
     * ================ [Naive Approach] - One by One Counting =====================
     *
     * This approach counts the set bits by checking each bit from right to left.
     *  It uses n & 1 to check if the least significant bit is 1, increments the count if so,
     *  then right-shifts the number to check the next bit.
     *  This repeats until n becomes 0, returning the total number of set bits.
     *
     * 1.	Look at the last bit (0 or 1)
     * 2.	Add it to count
     * 3.	Remove the last bit
     * 4.	Repeat until nothing is left
     *
     *  Time Complexity     = O(log n)
     *  Space complexity    = O(1)
     */
    private static int countSetBits_countingEachBits(int n) {
        int count = 0;
        int currBit;
        while (n > 0) {
            currBit = n & 1;
            count = currBit + count;
            n = n >> 1; // divide by 2
        }
        return count;
    }

    /*
     * ================== [Expected Approach 1] - Brian Kernighan's Algorithm =====================
     *
     * Brian Kernighan’s algorithm counts set bits efficiently by clearing them one by one.
     *      The key operation is n = n & (n - 1), which removes the least significant set bit from n.
     *      Each time this is done, it means one set bit has been counted and removed.
     *      The process repeats until n becomes 0, and the total number of times the loop runs gives the count of set bits.
     *
     *  Time Complexity     = O(log n)
     *  Space complexity    = O(1)
     */
    private static int countSetBits_countingOneSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

}
