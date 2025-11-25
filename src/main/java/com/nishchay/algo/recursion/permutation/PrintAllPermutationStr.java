package com.nishchay.algo.recursion.permutation;

/*
 * Java program to print all permutations of a given string.
 * Print all permutations of a given string
 * Str - ABC
 * it's permutations - ABC, ACB, BAC, BCA, CAB, CBA
 *
 * https://www.techiedelight.com/generate-permutations-string-java-recursive-iterative/
 * https://www.baeldung.com/cs/array-generate-all-permutations
 * https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * */
public class PrintAllPermutationStr {

    public static void main(String[] args) {

        strPermuteEx();
        System.out.println("................");
        strPermute1Ex();
    }

    private static void strPermuteEx() {
        // String str = "ABC";
        String str = "ABCD";
        int len = str.length();
        strPermute(str, 0, len - 1);
    }

    private static void strPermute1Ex() {
        String str = "ABCD";
        permutations("", str);
    }


    /**
     * permutation function
     *
     * @param str   string to calculate permutation for
     * @param start starting index
     * @param end   end index
     *              <p>
     *              Time Complexity: O(n*n!)
     */
    private static void strPermute(String str, int start, int end) {
        if (start == end)
            System.out.println(str);
        else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i); // first swap -> to find combination.
                strPermute(str, start + 1, end);
                str = swap(str, start, i); //swap back for backtracking
                // since we are modifying the actual string. So we need to put it back after printing the desired combination
            }
        }
    }

    private static String swap(String str, int i, int j) {
        char[] charArray = str.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    /*
     * Here’s another Java implementation that doesn’t convert the string into a character array.
     * Recursive function to generate all permutations of a string
     * */
    private static void permutations(String candidate, String remaining) {

        if (remaining.isEmpty()) {
            System.out.println(candidate);
        }

        for (int i = 0; i < remaining.length(); i++) {
            String newCandidate = candidate + remaining.charAt(i);

            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);

            permutations(newCandidate, newRemaining);
        }
    }


}
/*
 * O/P =>
 *
 * ABCD
 * ABDC
 * ACBD
 * ACDB
 * ADCB
 * ADBC
 * BACD
 * BADC
 * BCAD
 * BCDA
 * BDCA
 * BDAC
 * CBAD
 * CBDA
 * CABD
 * CADB
 * CDAB
 * CDBA
 * DBCA
 * DBAC
 * DCBA
 * DCAB
 * DACB
 * DABC
 * */