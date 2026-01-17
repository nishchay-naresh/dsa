package com.nishchay.math.a01easy;

import java.util.Arrays;

/*
* Check Anagram of an integer string
*
* What is an Anagram?
* An anagram is when two words or phrases are made using the same characters,
* with the same frequency, but arranged in a different order.
*
* */
public class CheckAnagram {

    public static void main(String[] args) {

        System.out.println("isAnagram(4593112,3211954)      - " + isAnagram(4593112,3211954));
        System.out.println("isAnagram(2045,2014)            - " + isAnagram(2045,2014));
        System.out.println("isAnagram(412546,641452)        - " + isAnagram(412546,641452));
        System.out.println("isAnagram(703,370)              - " + isAnagram(703,370));
        System.out.println("isAnagram(22233,23232)          - " + isAnagram(22233,23232));
        System.out.println("is2Anagram(11111100,11111111)   - " + isAnagram(11111100,11111111));

    }

    /*
    * Compute the frequency of each digit in number - in a frequency array
    * Then compare this frequency array
    *
    * */
    private static boolean isAnagram(int n1, int n2) {

        int[] freqN1 = getFreqArray(n1);
        int[] freqN2 = getFreqArray(n2);

        return Arrays.equals(freqN1,freqN2);
    }

    private static int[] getFreqArray(int num){

        final int TEN = 10;
        int[] freq =  new int[TEN];

        int digit;
        while(num > 0){
            digit = num % TEN;
            freq[digit] ++;
            num = num / TEN;
        }
        return freq;
    }
}
