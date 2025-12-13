package com.nishchay.algo.greedy;


import java.util.Arrays;


/*
 *  ======================= Print Maximum Meetings in One Room ====================
 *
 * Given two arrays, deadline[] and profit[],
 *   where deadline[i] is the last time unit by which the i-th job must be completed, and
 *   profit[i] is the profit earned from completing it.
 * Each job takes 1 unit time, and only one job can be scheduled at a time.
 *
 * A job earns profit only if finished within its deadline. Find the number of jobs completed and maximum profit.
 * So Maxm day can be the job with maxm deadline in the input
 *
 * Examples:
 *				Input: deadline[] = [4, 1, 1, 1], profit[] = [20, 10, 40, 30]
 *				Output: [2, 60]
 *				Explanation: Job 1 (profit 20, deadline 4) can be scheduled.
 *				Among the three jobs with deadline 1, only one fits, so we pick the highest profit (40). Hence, 2 jobs with total profit = 60.
 *
 *				Input: deadline[] = [2, 1, 2, 1, 1], profit[] = [100, 19, 27, 25, 15]
 *				Output: [2, 127]
 *				Explanation: Picking the job with profit 100 (deadline 2) and the job with profit 27 (deadline 2);
 *				they can occupy the two available slots before deadline 2. Thus 2 jobs are scheduled for a maximum total profit of 127.
 *
 *
 * https://www.geeksforgeeks.org/dsa/job-sequencing-problem/
 * https://takeuforward.org/data-structure/job-sequencing-problem/
 *
 * */
class JobSequencingProblem {

    public static void main(String[] args) {

        int n = 4;

        // Define the edges (source, destination, weight)
        Job[] arr = new Job[]{
                new Job() {{
                    id = 1;
                    deadline = 4;
                    profit = 20;
                }},
                new Job() {{
                    id = 2;
                    deadline = 1;
                    profit = 10;
                }},
                new Job() {{
                    id = 3;
                    deadline = 1;
                    profit = 40;
                }},
                new Job() {{
                    id = 4;
                    deadline = 1;
                    profit = 30;
                }}
        };

        // Call the JobScheduling function
        Pair<Integer, Integer> ans = JobScheduling(arr, n);

        // Output the result
        System.out.println(ans.getPart1() + " " + ans.getPart2());

    }

    /*
     * ================ [Approach 1] Use greedy approach  =====================
     *
     * The idea is to use greedy approach to always choose the meeting whose
     * 	- start time is greater than the end time of the previously selected meeting and
     *  - end time is the smallest among all the remaining meetings.
     *    This is because, smaller the end time, sooner the meeting will end and the meeting room will become available for the next meeting.
     *
     * So, we can sort the meetings according to their end time(pick the first arrival in case of collision)
     *  so that we always choose the meeting which has minimum end time.
     *
     *
     *  Time Complexity: O(N log N) + O(N * M), O(N log N) for sorting the jobs in decreasing order of profit. O(N * M) since we are iterating through all N jobs and for every job, we are checking from the last deadline, say M deadlines in the worst case.
     *  Space Complexity: O(M), for an array that keeps track of which day each job is performed if M is the maximum deadline available.
     *
     * Function to find the maximum profit and the number of jobs done
     */
    private static Pair<Integer, Integer> JobScheduling(Job[] arr, int n) {

        // Sort the jobs by profit in descending order
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);


        int maxi = arr[0].deadline; // first job always going to execute
        // Find the maximum deadline among all jobs
        for (int i = 1; i < n; i++) {
            // Find the latest deadline
            maxi = Math.max(maxi, arr[i].deadline);
        }

        // Create an array to store the slots for the jobs
        int[] slot = new int[maxi + 1];
        // Initialize all slots as unoccupied
        Arrays.fill(slot, -1);

        int countJobs = 0, jobProfit = 0;

        // Try to assign jobs to the slots
        for (int i = 0; i < n; i++) {

            // Find a slot for the current job, starting from the job's deadline
            for (int j = arr[i].deadline; j > 0; j--) {
                // If the slot is available
                if (slot[j] == -1) {
                    // Assign the job to the slot
                    slot[j] = i;
                    // Increment the job count
                    countJobs++;
                    // Add the profit of the job
                    jobProfit += arr[i].profit;
                    break;
                }
            }
        }

        // Return the number of jobs done and total profit
        return new Pair<>(countJobs, jobProfit);
    }

    // A class representing a Job with id, deadline, and profit
    static class Job {
        // Job Id
        int id;
        // Deadline of job
        int deadline;
        // Profit if job is completed before or on deadline
        int profit;
    }

    static class Pair<K, V> {
        private final K part1;
        private final V part2;

        public Pair(K part1, V part2) {
            this.part1 = part1;
            this.part2 = part2;
        }

        public K getPart1() {
            return part1;
        }

        public V getPart2() {
            return part2;
        }

    }
}

