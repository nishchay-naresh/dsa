package com.nishchay.ds.linklist.a02easy;


/*
 *  ======================= Detect loop in a Linked List ====================
 *
 *  Given the head of a singly linked list, determine whether the list contains a cycle.
 *
 *  A cycle exists if, while traversing the list through next pointers,
 *  you encounter a node that has already been visited instead of eventually reaching null ptr.
 *
 *  Input: head: 1 -> 3 -> 4
 *                   |--<-|
 *  Output: true
 *
 * Input: head: 1 -> 8 -> 3 -> 4 -> NULL
 * Output: false
 *
 *
 * https://www.geeksforgeeks.org/dsa/detect-loop-in-a-linked-list/
 *
 * */

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

import static com.nishchay.ds.linklist.Utils.printList;

public class DetectLoop {

    public static void main(String[] args) {
        Node head = Utils.createList();
        printList(head);
        System.out.printf("%s%n", isLoopExists(head) ? "Loop found" : "Loop not found");

        System.out.println("-----------------------------------");

        // created a link list with loop
        head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);
        // loop
        head.next.next.next.next.next = head.next;
        /*
         *  head => 10 -> 20 -> 30 -> 40 -> 50
         *                 |----------------|
         * */
        System.out.printf("%s%n", isLoopExists(head) ? "Loop found" : "Loop not found");
    }

    /*
     *  ================ [Naive Approach] Using HashSet - O(n) Time and O(n) Space =====================
     *
     * The idea is to keep adding nodes in the Hashset while traversing
     *   whenever a node is encountered that is already present in the hashset (which indicates there's a cycle (loop) in the list)
     *      return true
     *  if the node is NULL(which indicates done with the list traversal), represents the end of Linked List,
     *      return false
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     *
     *
     *  ================ [Expected Approach] Using Floyd's Cycle-Finding Algorithm - O(n) Time and O(1) Space =====================
     *
     *  The idea is to use Floyd's Cycle-Finding Algorithm to find a loop in a linked list.
     *  It uses two pointers slow and fast, fast pointer move two steps ahead and slow pointer move one step ahead at a time.
     *
     *  Floyd’s Tortoise and Hare Algorithm (Cycle Detection Algorithm).
     *
     *  Slow & Fast pointer approach:
     *	    slow and fast met before reaching null, this means that the list contains a cycle
     *
     *
     *     +--------+-----------+----------+
     *     | Hare   | Tortoise  | Distance |
     *     +--------+-----------+----------+
     *     |   2    |     1     |    9     |
     *     |   4    |     2     |    8     |
     *     |   6    |     3     |    7     |
     *     |   8    |     4     |    6     |
     *     |  10    |     5     |    5     |
     *     |   2    |     6     |    4     |
     *     |   4    |     7     |    3     |
     *     |   6    |     8     |    2     |
     *     |   8    |     9     |    1     |
     *     |  10    |    10     |    0     |
     *     +--------+-----------+----------+
     *
     * Time complexity: O(n).
     * Auxiliary Space: O(1).
     *
     * https://www.codingninjas.com/blog/2021/09/24/detect-a-loop-in-a-linked-list/?amp=1
     *
     * */
    private static boolean isLoopExists(Node head) {

        if (head == null || head.next == null) {
            return false;
        }

        Node slowPtr, fastPtr;
        slowPtr = fastPtr = head;
        while (slowPtr != null && fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;         // moves one step
            fastPtr = fastPtr.next.next;    // moves two steps

            if (slowPtr == fastPtr) {
                return true;                // cycle detected
            }
        }
        return false;                       // no detected
    }

}
