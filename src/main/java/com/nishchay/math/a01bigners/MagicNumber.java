package com.nishchay.math.a01bigners;


/*
 * Write a Java program or function which checks whether given number is a magic number or not.
 *
 * What Is Magic Number?
 * Magic number is a number which gives sum exactly 1 when its digits are recursively added.
 *  For example,
 * 	1252 is a magic number. 1252 -> 1 + 2 + 5 + 2 = 10 -> 1 + 0 = 1
 *  163 is a magic number. 163 -> 1+6+3 = 10 -> 1 + 0 = 1
 * 	1748 is not a magic number. 1748 -> 1 + 7 + 4 + 8 = 20 -> 2 + 0 = 2
 * */
public class MagicNumber {


    public static void main(String[] args) {

        isMagicNumber(1252);
        isMagicNumber(1748);

    }

    private static void isMagicNumber(int inputNumber) {

        int copyOfInputNumber = inputNumber;
        int sum = 0;

        //Until we get single digit sum, add digits of inputNumber recursively.
        while (sum > 9 || inputNumber > 0) {
            //If inputNumber is 0,
            //then assign sum to inputNumber and 0 to sum
            if (inputNumber == 0) {
                inputNumber = sum;
                sum = 0;
            }

            int lastDigit = inputNumber % 10;
            sum = sum + lastDigit;
            inputNumber = inputNumber / 10;
        }

        //If sum is 1, then given inputNumber is a magic number
        if (sum == 1) {
            System.out.println(copyOfInputNumber + " is a magic number");
        } else {
            System.out.println(copyOfInputNumber + " is not a magic number");
        }

    }
}
