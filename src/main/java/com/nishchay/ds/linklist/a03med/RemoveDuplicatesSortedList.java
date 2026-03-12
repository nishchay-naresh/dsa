package com.nishchay.ds.linklist.a03med;

/*
 *  ======================= Remove duplicates from a sorted linked list ====================
 *
 *  Given the head of a linked list, reverse the list and return the new head.
 *
 * Examples:
 *          Input:  head -> 11->11->11->21->43->43->60 -> null
 *                              X   X            X
 *          Output: head -> 11->21->43->60 -> null
 *
 *          Input    : head -> 5->10->10->20 -> null
 *          Output   : head -> 5->10->20 -> null    (After removing duplicate elements)
 *
 *
 * https://www.geeksforgeeks.org/dsa/remove-duplicates-from-a-sorted-linked-list/
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 * */

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

public class RemoveDuplicatesSortedList {

    public static void main(String[] args) {
        solutionExecution();
        System.out.println("--------------------------------------------------------------");
        solutionExecution_2pointer();
    }

    private static void solutionExecution() {
        Node head = Utils.createList(new int[]{11, 11, 11, 13, 13, 13, 20});
        // head -> 11->11->11->13->13>13->20 -> null
        Utils.printList(head);
        head = removeDuplicates(head);
        System.out.println("Linked list after duplicate removal :");
        Utils.printList(head);

        head = Utils.createList(new int[]{1, 1, 1, 2, 3, 3, 4, 4});
        // head -> 1 -> 1 -> 1 -> 2 -> 3 -> 3 -> 4
        Utils.printList(head);
        head = removeDuplicates(head);
        System.out.println("Linked list after duplicate removal :");
        Utils.printList(head);
    }

    private static void solutionExecution_2pointer() {
        Node head = Utils.createList(new int[]{11, 11, 11, 13, 13, 13, 20});
        // head -> 11->11->11->13->13>13->20 -> null
        Utils.printList(head);
        head = removeDuplicates_2pointer(head);
        System.out.println("Linked list after duplicate removal :");
        Utils.printList(head);

        head = Utils.createList(new int[]{1, 1, 1, 2, 3, 3, 4});
        // head -> 1 -> 1 -> 1 -> 2 -> 3 -> 3 -> 4
        Utils.printList(head);
        head = removeDuplicates_2pointer(head);
        System.out.println("Linked list after duplicate removal :");
        Utils.printList(head);
    }

    /*
     *  ================ [Expected Approach] By Changing Next Pointer – O(n) Time and O(1) Space =====================
     *
     * The idea is to traverse the linked list and for each node,
     *   if the next node has the same data, - delete the duplicate node.
     *
     * Steps:
     *      Traverse the linked list starting from the head node till the second last node
     *          compare each node with the next node.
     *          If the data in the next node is the same as the curr node,
     *              delete the duplicate node by adjusting a pointer to the next node or null
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     */
    private static Node removeDuplicates(Node head) {

        Node currNext;
        Node curr = head;
        while (curr.next != null) {
            currNext = curr.next;
            // Check if the next value is the same as curr
            if (curr.data == currNext.data) {
                // delete/skip currNext node
                curr.next = currNext.next;
            }else{
                curr = curr.next;
            }
        }
        return head;
    }

    /*
     *  refer this class - com.nishchay.algo.twopointer.a01easy.RemoveDuplicatesInSortedArray.removeDuplicates_2pointers(int[] )
     *
     * ================ [Optimal Approach] 2 pointer approach  =====================
     *
     *  1st pointer - to track the unique elements
     *  2nd pointer - to scan all elements of list (2nd node to last node)
     *
     * head node - first node will always be unique, means it can't be duplicate
     *
     *  Traverse the list from 2nd node to last node, check each node
     *      checks if its equivalent to unique.data
     *              keep moving curr pointer,
     *      else
     *              add curr node next to unique
     *              move unique pointer
     *
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     */
    private static Node removeDuplicates_2pointer(Node head) {

        Node unique = head;         // the first node will always be unique, means it can't be duplicate
        Node curr = unique.next;    // traversing from 2nd node
        while (curr != null) {
            if (unique.data != curr.data) {
                // adding unique node to unique pointer
                unique.next = curr;
                unique = unique.next;
            }
            curr = curr.next;
        }
        return head;
    }

}
