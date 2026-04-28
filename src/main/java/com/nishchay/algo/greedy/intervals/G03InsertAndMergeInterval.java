package com.nishchay.algo.greedy.intervals;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *  ================================ Insert and Merge Interval ============================================
 * Given a set of non-overlapping intervals[][] where intervals[i] = [starti , endi] represent the start and the end of the ith event
 * And intervals is sorted in ascending order by starti and a new interval.
 * Insert the interval at the correct position such that after insertion, the intervals remain sorted.
 * If the insertion results in overlapping intervals, then merge the overlapping intervals.
 * Assume that the set of non-overlapping intervals is sorted based on start time.
 *
 * Examples:
 *				Input: intervals[][] = [[1, 3], [4, 5], [6, 7], [8, 10]], newInterval[] = [5, 6]
 *				Output: [[1, 3], [4, 7], [8, 10]]
 *				Explanation: The intervals [4, 5] and [6, 7] are overlapping with [5, 6]. So, they are merged into one interval [4, 7].
 *
 *				Input: intervals[][] = [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]], newInterval[]  = [4, 9]
 *				Output: [[1, 2], [3, 10], [12, 16]]
 *				Explanation: The intervals [ [3, 5], [6, 7], [8, 10] ] are overlapping with [4, 9]. So, they are merged into one interval [3, 10].
 *
 *				Example 1:
 *				Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *				Output: [[1,5],[6,9]]
 *
 *				Example 2:
 *				Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *				Output: [[1,2],[3,10],[12,16]]
 *				Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 *
 * Been Asked in Xebia/tesco
 *				 1.	Existing intervals: [[1, 2], [3, 5], [6, 8]]
 *					New interval: [4, 7]
 *					Updated intervals: [[1, 2], [3, 8]]
 *				-------------------------------------------------------------------
 *				2.	Existing intervals: [[1, 3], [5, 7], [10, 12]]
 *					New interval: [8, 9]
 *					Updated intervals: [[1, 3], [5, 7], [8, 9], [10, 12]]
 *
 *
 * https://www.geeksforgeeks.org/dsa/insert-in-sorted-and-non-overlapping-interval-array/
 * https://leetcode.com/problems/insert-interval/description/
 * */
public class G03InsertAndMergeInterval {

    public static void main(String[] args) {

        int[][] intervals = new int[][]{{1, 3}, {4, 5}, {6, 7}, {8, 10}};
        int[] newInterval = new int[]{5, 6};

        ArrayList<int[]> updatedIntervals = insertInterval(intervals, newInterval);
        for (int[] interval : updatedIntervals) {
            System.out.println(Arrays.toString(interval));
        }

        System.out.println("---------------------------------------");
        intervals = new int[][]{{1, 2}, {3, 4}, {5, 7}, {8, 10}, {12, 16}};
        newInterval = new int[]{6, 8};
        updatedIntervals = insertInterval(intervals, newInterval);
        for (int[] interval : updatedIntervals) {
            System.out.println(Arrays.toString(interval));
        }

        System.out.println("---------------------------------------");
        intervals = new int[][]{{1, 2}, {3, 4}, {7, 8}, {9, 11}, {12, 16}};
        newInterval = new int[]{5, 6};
        updatedIntervals = insertInterval(intervals, newInterval);
        for (int[] interval : updatedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }

    /*
     * ============ [Expected Approach] Contiguous Interval Merging - O(n) Time and O(n) Space  ==============
     *  When we add a new interval, it may overlap with some contiguous intervals in the array.
     *  The overlapping intervals can be found in a contiguous subarray because the intervals array is already sorted.
     *  To remove overlapping we find these overlapping interval's subarray and merge them with new interval, to form a single merged interval.
     *
     *				Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     *				Output: [[1,2],[3,10],[12,16]]
     *				Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     *
     * Left side - Non overlapping interval's  => while(intervals[i][1] < newInterval[0])
     *      keep copy then to result
     *
     * Middle part - Overlapping interval's  =>  we will reach her only when we meet the above condition
     *      No we keep merging them, and form new intervals => while(intervals[i][0] <= newInterval[1])
     *
     * Right side - Non overlapping interval's  => no condition required, after merging we left with non-verlapping interval's
     *      keep copy then to result
     *
     *
     *  Time Complexity     : O(n))
     *  Space complexity    : O(n)
     */
    private static ArrayList<int[]> insertInterval(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> updatedIntervals = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add all intervals that come before the new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            updatedIntervals.add(intervals[i]);
            i++;
        }

        // Merge all overlapping intervals with the new interval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        updatedIntervals.add(newInterval);

        // Add all the remaining intervals
        while (i < n) {
            updatedIntervals.add(intervals[i]);
            i++;
        }

        // Return the result as a List<int[]>
        return updatedIntervals;
    }

}
