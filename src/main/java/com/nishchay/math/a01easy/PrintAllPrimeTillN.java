package com.nishchay.math.a01easy;

public class PrintAllPrimeTillN {

    public static void main(String[] args) {
        int n = 7;
        printPrime(n);
    }

    // Function to print primes
    private static void printPrime(int n){
        for (int i = 2; i <= n; i++)
            if (CheckPrime.isPrime(i))
                System.out.print(i + " ");

    }

}


