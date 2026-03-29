package com.nishchay.ds.string.a01basic;

/*
 *	Input Format:
 *	     The first line contains a string.
 *       The second line contains another string.
 *  The strings consist of only lowercase english letters.
 *
 *	Output Format:
 *	There are three lines of output:
 *	    1.  For the first line, sum the lengths of chars.
 *      2.  For the second line, write Yes if the first String is lexicographically greater than the second String otherwise print No instead.
 *      3.  For the third line, capitalize the first letter in both the first String and the second String and print them on a single line, separated by a space.
 *
 *	Sample Input:
 *	                hello
 *	                java
 *	Sample Output:
 *	                9
 *	                No
 *	                Hello Java
 *
 * */
public class CapitaliseFirstChar_StringUtils {

    public static void main(String[] args) {
        processInputString("good", "morning");
        System.out.println("......................");
        processInputString("spring", "java");
    }

    /*
     * str1.compareTo(str2) return int
     *       0   = str1==str2
     *       +1  = str1>str2
     *       -1  = str1<str2
     *
     * */
    private static void processInputString(String str1, String str2) {
        int combinedLength = str1.length() + str2.length();

        String stringComp = str1.compareTo(str2) > 0 ? "Yes" : "No";

        // capitalize the first letter
        String output1 = str1.substring(0, 1).toUpperCase() + str1.substring(1);
        String output2 = str2.substring(0, 1).toUpperCase() + str2.substring(1);

        System.out.println(combinedLength);
        System.out.println(stringComp);
        System.out.println(output1 + " " + output2);
    }
}
