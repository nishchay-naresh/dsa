package com.nishchay.ds.linklist.a02easy;

/*
 *  ======================= Reverse a Linked List ====================
 *
 *  Given the head of a linked list, reverse the list and return the new head.
 *     Input    : head -> 1 -> 2 -> 3 -> 4 -> 5 -> null
 *     Output   : head -> 5 -> 4 -> 3 -> 2 -> 1 -> null
 *
 *
 * https://www.geeksforgeeks.org/dsa/reverse-a-linked-list/
 *
 * */

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

public class ReverseAList {

    public static void main(String[] args) {
        Node head = Utils.createList();
        Utils.printList(head);
        System.out.println("--------- Reversed list -----------");
        head = reverse(head);
        Utils.printList(head);
    }

    /*
     *  ================ [Optimize/Expected Approach] Using Iterative Method - O(n) Time and O(1) Space =====================
     *
     * The idea is to reverse the linked list by changing the direction of links using three pointers: prev, curr, and next.
     *     At each step, point the current node to its previous node and then move all three pointers forward until the list is fully reversed.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     */
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }
}
