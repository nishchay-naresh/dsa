package com.nishchay.bit.a01basics;

public class APIConverter {

    public static void main(String[] args) {

        // Decimal integer to a Binary string
        int decimalNumber = 42;
        String binaryString = Integer.toBinaryString(decimalNumber);
        System.out.println("Decimal - " + decimalNumber);
        System.out.println("Binary - " + binaryString); // 101010

        String binaryString1 = "01001101";
        int decimalNumber1 = Integer.parseInt(binaryString1, 2);
        System.out.println("Decimal - " + decimalNumber1); // 77
    }
}
