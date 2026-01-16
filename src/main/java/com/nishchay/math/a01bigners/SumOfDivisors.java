package com.nishchay.math.a01bigners;

public class SumOfDivisors {


    public static void main(String[] args) {
        int divisorSum = divisor_sum(6);
        System.out.println("divisorSum = " + divisorSum);
    }

    public static int divisor_sum(int n) {
        if (n < 1) {
            return 0;
        }

        int sumOfDivisors = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                sumOfDivisors = sumOfDivisors + i;
            }
        }

        return sumOfDivisors;
    }

}


   

