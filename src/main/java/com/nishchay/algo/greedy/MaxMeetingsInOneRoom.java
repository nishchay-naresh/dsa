package com.nishchay.algo.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/*
 *  ======================= Print Maximum Meetings in One Room ====================
 *
 *
 *  Given n meetings in the form of start[] and end[]
 *  where start[i] is the start time of ith meeting and end[i] is the end time of ith meeting.
 *  The task is to print the meeting number of all the meetings which can be held in a single room such that total number of meetings held is maximized.
 *  The meeting room can have only one meeting at a particular time.
 *
 * Note: The start time of one chosen meeting can't be equal to the end time of any other chosen meeting.
 *
 *
 * Examples:
 * 				Input: start[] = {1, 3, 0, 5, 8, 5},
 *                       end[] = {2, 4, 6, 7, 9, 9}
 * 				Output: 1 2 4 5
 * 				Explanation: We can attend the 1st meeting from (1 to 2),
 *                           then the 2nd meeting from (3 to 4), then the 4th meeting from (5 to 7), and the 5th meeting from (8 to 9).
 *
 * 				Input: start[] = {10, 12, 20}, end[] = {20, 25, 30}
 * 				Output: 1
 * 				Explanation: We can attend at most one meeting in a single meeting room.
 *
 *              Input: start[] = [1, 2], end[] = [100, 99]
 *              Output: 1
 *
 *
 * https://takeuforward.org/data-structure/n-meetings-in-one-room/
 * https://www.geeksforgeeks.org/dsa/find-maximum-meetings-in-one-room/
 *
 * */
class MaxMeetingsInOneRoom {

    public static void main(String[] args) {

        int[] start = new int[]{1, 3, 0, 5, 8, 5};
        int[] end = new int[]{2, 4, 6, 7, 9, 9};

        List<Integer> meetings = maxMeetings(start, end);
        for (int meeting : meetings) {
            System.out.print(meeting + " ");
        }
        System.out.println("\n--------------------------");
        start = new int[]{10, 12, 20};
        end = new int[]{20, 25, 30};
        meetings = maxMeetings(start, end);
        for (int meeting : meetings) {
            System.out.print(meeting + " ");
        }

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
     *  Time Complexity     : O(n * logn) as we are sorting the meetings according to end time.
     *  Auxiliary Space     : O(n) for creating a list of meetings to sort the meetings.
     *  Time Complexity     : O(n) + O(n log n) +  O(n) = O(3n log n) = O(n log n)
     */

    private static List<Integer> maxMeetings(int[] start, int[] end) {
        List<Integer> res = new ArrayList<>();
        int n = start.length;

        // Store details of all meetings in a list
        List<Meeting> meets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            meets.add(new Meeting(start[i], end[i], i + 1));
        }

        // Sort the meetings according to the ending time
        meets.sort(new MeetingComparator());

        // Initialize current time as -1
        int currTime = -1;
        for (Meeting meeting : meets) {

            // Check if the meeting room is free at the start time of the meeting
            if (meeting.startTime > currTime) {
                currTime = meeting.endTime;
                res.add(meeting.pos);
            }
        }

        return res;
    }

    static class Meeting {
        int startTime, endTime, pos;

        Meeting(int startTime, int endTime, int pos) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.pos = pos;
        }
    }

    // Custom comparator to compare meetings according to end time
    static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return Integer.compare(m1.endTime, m2.endTime);
        }
    }

}