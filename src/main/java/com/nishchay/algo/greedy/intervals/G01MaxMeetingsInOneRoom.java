package com.nishchay.algo.greedy.intervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 *  ============================================== Print Maximum Meetings in One Room ========================================
 *
 *  Given n meetings in the form of start[] and end[]
 *  where start[i] is the start time of ith meeting and end[i] is the end time of ith meeting.
 *  The task is to print the meeting number of all the meetings which can be held in a single room such that total number of meetings held is maximized.
 *  The meeting room can have only one meeting at a particular time.
 *
 *  We need to pick the meetings in such a away  = meeting count should be maximum
 *
 * Note: The start time of one chosen meeting can't be equal to the end time of any other chosen meeting.
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
 * https://takeuforward.org/data-structure/n-meetings-in-one-room
 * https://www.geeksforgeeks.org/dsa/find-maximum-meetings-in-one-room/
 *
 * TODO - https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 *
 * */
class G01MaxMeetingsInOneRoom {

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
     * ==================================== [Expected Approach] Use greedy approach  ===================================
     *
     * We need to schedule as many meetings as possible in a single room without overlaps.
     * The key observation is that once we choose a meeting that ends earliest,
     * This naturally leads to a greedy approach
     * The greedy choice is to always select the meeting that finishes earliest among the available ones.
     *
     *	-	Sort meetings by end time so that the ones that finish earliest are considered first.
     *	-	Select the first meeting in the sorted list and mark its end time as the last occupied time.
     *	-	Iterate through remaining meetings, and for each, check if its start time is strictly greater than the last occupied end time.
     *			If condition holds, include that meeting in the schedule and update the last occupied end time to this meeting’s end time.
     *	-	Continue until all meetings are checked, resulting in the maximum possible non-overlapping meetings in the room.
     *
     *
     *		Start = [0, 1, 3, 5, 5, 8]
     *		End   = [5, 2, 4, 7, 9, 9]
     *
     *		Sorted by End Time
     *
     *		Start = [1, 3, 0, 5, 8, 5]
     *		End   = [2, 4, 5, 7, 9, 9]
     *
     *
     *		Meeting | Start | End | Last End | Decision          | Selected Meeting
     *		-----------------------------------------------------------------------
     *		M1      |   1   |  2  |    -     | Select            | [1]
     *		M2      |   3   |  4  |    2     | Select (3 >= 2)   | [1, 2]
     *		M3      |   0   |  5  |    4     | Skip (0 < 4)      | [1, 2]
     *		M4      |   5   |  7  |    4     | Select (5 >= 4)   | [1, 2, 4]
     *		M5      |   8   |  9  |    7     | Select (8 >= 7)   | [1, 2, 4, 5]
     *		M6      |   5   |  9  |    9     | Skip (5 < 9)      | [1, 2, 4, 5]
     *
     *
     *		Therefore, the selected meetings are: [1, 2, 4, 5]
     *
     *  Time Complexity     : O(n * log n) as we are sorting the meetings according to end time.
     *                      : O(n) + O(n log n) +  O(n) = O(3n log n) = O(n log n)
     *  Auxiliary Space     : O(n) for creating a list of meetings to sort the meetings.
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
/*
         Comparator<Meeting> endTimeComparator =
                    Comparator.comparingInt((Meeting m) -> m.endTime)   // primary: endTime ascending
                        .thenComparing((Meeting m) -> m.startTime, Comparator.reverseOrder()); // tie-breaker: startTime descending
*/
        @Override
        public int compare(Meeting m1, Meeting m2) {
            if (m1.endTime != m2.endTime) {
                return Integer.compare(m1.endTime, m2.endTime); // ascending endTime
            } else {
                return Integer.compare(m2.startTime, m1.startTime); // descending startTime
            }
        }
    }

}