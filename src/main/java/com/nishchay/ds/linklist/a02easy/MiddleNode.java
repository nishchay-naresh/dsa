package com.nishchay.ds.linklist.a02easy;


/*
 *  ======================= Middle Node in a Linked List ====================
 *
 *  Given the head of singly linked list, find middle node of the linked list.
 *      If the number of nodes is odd, return the middle node.
 *      If the number of nodes is even, there are two middle nodes, so return the second middle node.
 *
 * https://www.geeksforgeeks.org/dsa/write-a-c-function-to-print-the-middle-of-the-linked-list/
 *
 * */

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

public class MiddleNode {

    public static void main(String[] args) {
        // odd list
        Node head = Utils.createList();
        Utils.printList(head);
        System.out.println("The middle element is = " + getMiddleNode(head));

        // even list
        head = Utils.createList(new int[]{10, 20, 30, 40, 50, 60});
        Utils.printList(head);
        System.out.println("The middle element is = " + getMiddleNode(head));
    }

    /*
     *  ================ [Optimize/Expected Approach] Hare and Tortoise Algorithm - O(n) Time and O(1) Space =====================
     *
     * We can use the Tortoise and Hare algorithm to find the middle of the linked list.
     *      Initialize both slow and fast pointers at the head.
     *      Move slow by one step and fast by two steps each iteration.
     *      When fast reaches the end (or null), slow will be at the middle.
     *      For even nodes, slow automatically ends at the second middle.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     */
    private static int getMiddleNode(Node head) {
        Node slowPointer, fastPointer;

        slowPointer = fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        assert slowPointer != null;
        return slowPointer.data;
    }
}
