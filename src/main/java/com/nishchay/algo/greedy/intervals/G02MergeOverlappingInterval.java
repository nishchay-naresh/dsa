package com.nishchay.algo.greedy.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/*
 *  ========================================= Merge Overlapping Intervals ==============================================
 * Given an array of time intervals where arr[i] = [starti, endi],
 * Our task is to merge all the overlapping intervals into one and output the result which should have only mutually exclusive intervals.
 *
 * Examples:
 *				Input: arr[] = [[1, 3], [2, 4], [6, 8], [9, 10]]
 *				Output: [[1, 4], [6, 8], [9, 10]]
 *				Explanation: In the given intervals, we have only two overlapping intervals [1, 3] and [2, 4].
 *							 Therefore, we will merge these two and return [[1, 4]], [6, 8], [9, 10]].
 *
 *				Input: arr[] = [[7, 8], [1, 5], [2, 4], [4, 6]]
 *				Output: [[1, 6], [7, 8]]
 *				Explanation: We will merge the overlapping intervals [[1, 5], [2, 4], [4, 6]] into a single interval [1, 6].
 *
 * https://www.geeksforgeeks.org/dsa/merging-intervals/
 * https://takeuforward.org/data-structure/merge-overlapping-sub-intervals
 * */
public class G02MergeOverlappingInterval {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{7, 8}, {1, 5}, {2, 4}, {4, 6}};
        ArrayList<int[]> mergedIntervals = mergeOverlap(intervals);
        for (int[] interval : mergedIntervals)
            System.out.println(Arrays.toString(interval));

        System.out.println("---------------------------------------");
        intervals = new int[][]{{1, 3}, {2, 4}, {6, 8}, {9, 10}};
        mergedIntervals = mergeOverlap(intervals);
        for (int[] interval : mergedIntervals)
            System.out.println(Arrays.toString(interval));
    }


    /*
     * ============ [Expected Approach] Checking Last Merged Interval – O(n*log(n)) Time and O(n) Space  ==============
     *
     *  The intuition is to first sort the intervals based on their starting points.
     *  This allows us to easily identify overlapping intervals by comparing each interval with the last merged interval.
     *
     *	Sort the intervals based on their starting points. This ensures overlapping intervals come together.
     *	Initialize an empty list to store the final merged intervals.
     *		If the list is empty or the current interval starts after the last one ends,
     *			it means there is no overlap, so just add it to the list.
     *		If the current interval starts before or exactly at the end of the last one,
     *			it means there is overlap. So, combine both by extending the end of the last one to the further end of the two.
     *	Keep doing this until all intervals have been checked. The final list will now contain only non-overlapping, merged intervals.
     *
     *
     *  Time Complexity     : O(n*log(n))
     *  Space complexity    : O(n)
     */
    private static ArrayList<int[]> mergeOverlap(int[][] intervals) {
        // Sort intervals based on start values
        // Comparator<int[]> startTimeComparator = Comparator.comparingInt(a -> a[0]);
        Comparator<int[]> startTimeComparator =
                Comparator.comparingInt((int[] arr) -> arr[0])
                        .thenComparing((int[] arr) -> arr[1], Comparator.reverseOrder()); // tie-breaker: arr[1] descending

        Arrays.sort(intervals, startTimeComparator);

        // List to store merged intervals
        ArrayList<int[]> merged = new ArrayList<>();
        int[] first = intervals[0];
        merged.add(new int[]{first[0], first[1]});

        for (int i = 1; i < intervals.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            int[] curr = intervals[i];

            // If current interval overlaps with the last merged interval, merge them
            if (curr[0] <= last[1])
                last[1] = Math.max(last[1], curr[1]);
            else // else simply add the current interval
                merged.add(new int[]{curr[0], curr[1]});
        }
        return merged;
    }
}
