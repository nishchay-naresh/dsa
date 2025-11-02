package com.nishchay.math;

/*
 * ====================== Check for Prime =======================
 *
 * Given a positive integer, check if the number is prime or not.
 * Prime is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 * Examples of the first few prime numbers are {2, 3, 5, ...}
 *
 * Examples :
 * 		Input:  n = 11
 * 		Output: true
 *
 * 		Input:  n = 15
 * 		Output: false
 *
 * 		Input:  n = 1
 * 		Output: false
 * */
public class CheckPrime {

    public static void main(String[] args) {
        int n = 15;
        System.out.printf("%d is even = %b", n, isPrime(n));
        n = 44;
        System.out.printf("\n%d is even = %b", n, isPrime(n));
        n = 11;
        System.out.printf("\n%d is even = %b", n, isPrime(n));

        n = 43;
        System.out.printf("\n%d is even = %b", n, isPrimeFast(n));
        n = 51;
        System.out.printf("\n%d is even = %b", n, isPrimeFast(n));
        n = 53;
        System.out.printf("\n%d is even = %b", n, isPrimeFast(n));
    }

    /*
     * We can do the following optimizations: Instead of checking till n, we can check till √n
     * because a larger factor of n must be a multiple of a smaller factor that has been already checked.
     *
     *     Time Complexity: O(√n)
     *     Auxiliary Space: O(1)
     * */
    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;

        // Check from 2 to square root of n : i <= Math.sqrt(n) or  i * i <= n
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }

    /*
     * ==================== 6k ± 1 approach =========================
     *
     * Every prime number greater than 3 is of the form 6k ± 1, where k is an integer.
     * Examples: 5 = 6×1–1, 7 = 6×1+1, 11 = 6×2–1, 13 = 6×2+1
     * So in the loop we will check the divisibility of the number not against of all the numbers,
     * instead against of numbers in this format of : 6k - 1 or 6k + 1
     *  6k - 1 : 5, 11, 17, 23, ...
     *  6k + 1 : 7, 13, 19, 25, ...,
     *
     *
     * Time Complexity: Roughly O(√n / 3) → simplified to O(√n) (because we skip 2/3 of the numbers).
     *
     * Why this is efficient:
     *  - Handles small numbers separately.
     *  - Skips even and multiples of 3 early.
     *  - Then checks only numbers of form 6k ± 1 up to √n — making it fast and efficient.
     *
     *
         for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

     * */
    private static boolean isPrimeFast(int n) {
        if (n == 2 || n == 3)
            return true;

        // Filter out obvious non-primes
        if (n <= 1 || n % 2 == 0 || n % 3 == 0)
            return false;

        // checking for 6k ± 1 pattern
        for (int i = 6; i * i <= n; i = i + 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0)
                return false;
        }
        return true;
    }
}
