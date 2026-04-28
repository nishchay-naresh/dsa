package com.nishchay.algo.greedy.intervals;

import java.util.Arrays;

/*
 *  ============================================== Minimum Platforms Required ========================================
 * We are given two arrays that represent the arrival and departure times of trains that stop at the platform.
 * We need to find the minimum number of platforms needed at the railway station so that no train has to wait.
 *
 *
 * Given two arrays arr[] and dep[], that represent the arrival and departure time of i-th train respectively.
 * Find the minimum number of platforms required so that no train has to wait.
 * If the departure time of one train is the same as the arrival time of another train, both trains cannot use the same platform at that time.
 *
 * Note: Time intervals are in the 24-hour format (HHMM), where the first two characters represent hour (between 00 and 23)
 *  and the last two characters represent minutes (this will be <= 59 and >= 0).
 *  Leading zeros for hours less than 10 are optional (e.g., 0900 is the same as 900).
 *
 * Examples:
 *
 *				Input:  N=6,
 *				arr[] = {9:00, 9:45, 9:55, 11:00, 15:00, 18:00}
 *				dep[] = {9:20, 12:00, 11:30, 11:50, 19:00, 20:00}
 *				Output: 3
 *				Explanation: There are at-most three trains at a time. The train at 11:00 arrived but the trains which had arrived at 9:45 and 9:55 have still not departed.
 *
 *				Input : N=2,
 *				arr[]={10:20,12:00}
 *				dep[]={10:50,12:30}
 *				Output: 1
 *				Explanation : Before the arrival of the new train, the earlier train already departed. So, we don't require multiple platforms.
 *
 * https://www.geeksforgeeks.org/dsa/minimum-number-platforms-required-railwaybus-station/
 * https://takeuforward.org/data-structure/minimum-number-of-platforms-required-for-a-railway
 * */
class MinimumPlatformsRequired {

    public static void main(String[] args) {

        int[] arr = new int[]{900, 945, 955, 1100, 1500, 1800};
        int[] dep = new int[]{920, 1200, 1130, 1150, 1900, 2000};
        int n = arr.length;
        System.out.println("Minimum number of Platforms required " + countPlatforms(n, arr, dep)); // 3

        arr = new int[]{1000, 935, 1100};
        dep = new int[]{1200, 1240, 1130};
        n = arr.length;
        System.out.println("Minimum number of Platforms required " + countPlatforms(n, arr, dep)); // 3

        arr = new int[]{1020, 1200};
        dep = new int[]{1150, 1230};
        n = arr.length;
        System.out.println("Minimum number of Platforms required " + countPlatforms(n, arr, dep)); // 1
    }

    /*
     * ==================================== [Expected Approach] Use greedy approach  ===================================
     *
     * We need to consider each interval defined by an arrival and departure time, and check how many other intervals overlap with it.
     * This can be done using nested loops. Throughout the process, track the maximum number of overlapping intervals and return it at the end.
     *
     *  int[] arr = {900, 945, 955, 1100, 1500, 1800};
     *  int[] dep = {920, 1200, 1130, 1150, 1900, 2000};
     *
     * We can solve this by simply sorting it based on time (arrival & departure) based on clock time,
     * Then simply need to count arrival(+1) and departure(-1) and store the maximum count during this interval - that will be the no of platform
     * Sort based on time (tracking each event as per time)
     *      (A,900), (D,920), (A,945), (A,955), (A,1100), (D,1130),(D,1150), (D,1200), (A,1500), (D,1900), (A,1800), (D,2000)
     * count  1         0       1       2           3       2       1           0           1       0           1       0
     *
     * Don't want to have addition array to increase the space complexity, so we will sort them in exiting array
     *
     *
     *		Sort the list of arrival times and the list of departure times separately. Use two indices to walk through the sorted arrival and departure times.
     *		Start with one platform needed and track the maximum platforms required.
     *			If the next train arrives before or at the same time as the earliest departure, increase the platform count and move to the next arrival.
     *			If the next train arrives after the earliest departure, reduce the platform count and move to the next departure.
     *		Keep updating the maximum platform count during this process until all times are processed.
     *
     *  Time Complexity     : O(N*logN), we sort both the arrival and departure arrays.
     *  Auxiliary Space     : O(1), constant additional space is used.
     */
    private static int countPlatforms(int n, int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platforms = 1;
        int result = 1;
        int i = 1, j = 0;

        while (i < n && j < n) {
            // If next train arrives before current one departs
            if (arr[i] <= dep[j]) {
                // One more platform needed
                platforms++;
                i++;
            } else {
                // One train departs, platform freed
                platforms--;
                j++;
            }
            // Update maximum required platforms
            result = Math.max(result, platforms);
        }
        return result;
    }
}