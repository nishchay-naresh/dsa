package com.nishchay.algo.greedy.easy;

import java.util.Arrays;

/*
 * =================================== Shortest Job First (or SJF) CPU Scheduling ==================================
 * Given a list of job durations representing the time it takes to complete each job.
 * Implement the Shortest Job First algorithm to find the average waiting time for these jobs.
 *
 * Examples:
 * Example 1:
 * Input:jobs = [3, 1, 4, 2, 5]
 * Output: 4
 * Explanation:
 * 			The first job that will be executed is of duration 1 and the waiting time for it will be 0.
 * 			After the first job, the next shortest job with a duration of 2 will be executed with a waiting time of 1.
 * 			Following the completion of the first two jobs, the next shortest job with a duration of 3 will be executed with a waiting time of 3 (1 + 2).
 * 			Then, the job with a duration of 4 will be executed with a waiting time of 6 (1 + 2 + 3).
 * 			Finally, the job with the longest duration of 5 will be executed with a waiting time of 10 (1 + 2 + 3 + 4).
 * 			Hence, the average waiting time is calculated as (0 + 1 + 3 + 6 + 10) / 5 = 20 / 5 = 4.
 *
 *
 * Example 2:
 * Input: jobs = [4, 3, 7, 1, 2]
 * Output: 4
 * Explanation:
 * 			The first job that will be executed is of duration 1, and the waiting time for it will be 0.
 * 			After the first job, the next shortest job with a duration of 2 will be executed with a waiting time of 1.
 * 			Following the completion of the first two jobs, the next shortest job with a duration of 3 will be executed with a waiting time of 3 (1 + 2).
 * 			Then, the job with a duration of 4 will be executed with a waiting time of 6 (1 + 2 + 3).
 * 			Finally, the job with the longest duration of 7 will be executed with a waiting time of 10 (1 + 2 + 3 + 4).
 * 			Hence, the average waiting time is calculated as (0 + 1 + 3 + 6 + 10) / 5 = 20 / 5 = 4.
 *
 * Example 3:
 * arr = [1,2,3,4]
 * Output: 2
 * Explanation: After sorting burst times by shortest job policy, calculated average waiting time is 2.
 *
 *
 * https://www.geeksforgeeks.org/operating-systems/shortest-job-first-or-sjf-cpu-scheduling/
 * https://takeuforward.org/Greedy/shortest-job-first-or-sjf-cpu-scheduling
 * */
class G02ShortestJobFirst {

    public static void main(String[] args) {

        int[] jobs = new int[]{3, 1, 4, 2, 5};
        System.out.print("Jobs = " + Arrays.toString(jobs));
        System.out.println("Average waiting time = " + calculateAverageWaitTime(jobs));

        jobs = new int[]{4, 3, 7, 1, 2};
        System.out.print("Jobs = " + Arrays.toString(jobs));
        System.out.println("Average waiting time = " + calculateAverageWaitTime(jobs));

        jobs = new int[]{1, 2, 3, 4};
        System.out.print("Jobs = " + Arrays.toString(jobs));
        System.out.println("Average waiting time = " + calculateAverageWaitTime(jobs));
    }

    /*
     * ======================== [Expected Approach] Use greedy approach, Using Sorting  =============================
     *
     * 	Sort the jobs in ascending order based on their durations.
     * 	The jobs array will now contain the job durations arranged from shortest to longest.
     *
     * 	Initialize variables
     * 		waitTime to 0 (waiting time for that particular job) and
     * 		totalTime (total waiting time for all jobs).
     * 	Iterate through each job in the sorted array.
     * 		For each job, its waiting time is the sum of total time taken by all previous jobs.
     * 		Update the total time taken by adding the duration of the current job to the total waiting time.
     * 	After iterating through each job in the array, the average waiting is total waiting time divided by the number of jobs ie. length of the jobs array.
     *
     *  Time Complexity: O(N) where N is the length of the input array.
     *  Space Complexity: O(1)
     */
    private static float calculateAverageWaitTime(int[] jobs) {
        Arrays.sort(jobs);

        float waitTime = 0;  // stores cumulative waiting time
        int totalTime = 0;   // tracks elapsed execution time
        int n = jobs.length; // number of jobs

        // Iterate through each job
        for (int job : jobs) {
            waitTime = waitTime + totalTime;    // add current total time to waiting time
            totalTime = totalTime + job;        // execute current job
        }
        // return the average waiting time
        return waitTime / n;
    }
}