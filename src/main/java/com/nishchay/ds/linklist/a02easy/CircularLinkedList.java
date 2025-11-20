package com.nishchay.ds.linklist.a02easy;


/*
 *  ======================= Traversal of Circular Linked List====================
 *
 *  starting at the head node and repeatedly printing the value of the current node while moving to its next node.
 *  Continue this process until we return back to the head node.
 *
 * https://www.geeksforgeeks.org/dsa/traversal-of-circular-linked-list/
 *
 * */

import com.nishchay.ds.linklist.Node;

public class CircularLinkedList {

    public static void main(String[] args) {
        // Create a hard-coded linked list
        // 11 -> 2 -> 56 -> 12
        Node head = new Node(11);
        head.next = new Node(2);
        head.next.next = new Node(56);
        head.next.next.next = new Node(12);
        head.next.next.next.next = head;

        printList(head);
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
    private static void printList(Node head) {
        // return if list is empty
        if (head == null)
            return;

        Node curr = head;
        do {
            System.out.print(curr.data + " ");
            curr = curr.next;
        } while (curr != head);
        System.out.println();
    }
}
