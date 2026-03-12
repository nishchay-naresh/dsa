package com.nishchay.ds.linklist.a03med;

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

/*
 *  ======================= Merge two sorted linked lists ====================
 *
 *  Given the heads of two sorted linked lists, merge them into a single sorted linked list and return the head of the merged list.
 *
 * Examples:
 *          Input   head1 -> 5 -> 10 -> 15 -> 40 -> null
 *                  head2 -> 2 -> 3 > 20 -> null
 *          Output  head3 -> 2 -> 3 -> 5 -> 10 -> 15 -> 20 -> 40 -> null
 *
 *          Input   head1 -> 1 -> 1 -> null
 *                  head2 -> 2 -> 4 -> null
 *          Output  head3 -> 1 -> 1 -> 2 -> 4 -> null
 *
 *
 * https://www.geeksforgeeks.org/dsa/merge-two-sorted-linked-lists/
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * */
public class MergeTwoSortedList {

    public static void main(String[] args) {

        Node head1 = Utils.createList(new int[]{5, 10, 15, 40});
        Node head2 = Utils.createList(new int[]{2, 3, 20});
        Node resultHead = mergeSortedList(head1, head2);
        System.out.println("Merged list :");
        Utils.printList(resultHead);

        head1 = Utils.createList(new int[]{1, 1});
        head2 = Utils.createList(new int[]{2, 4});
        resultHead = mergeSortedList(head1, head2);
        System.out.println("Merged list :");
        Utils.printList(resultHead);
    }

    /*
     * ================ [Expected Approach] Two Pointer Merge - O(n+m) Time and O(1) Space =====================
     *
     * Merging of two sorted lists to third one in sorted order
     * travels in both of the lists simultaneously
     * Compare the nodes from both lists and append the smaller node to the merged list.
     * Once one list is fully traversed, the remaining nodes from the other list are appended.
     *
     *  Time Complexity     : O(n + m)
     *  Space complexity    : O(n + m)
     */
    public static Node mergeSortedList(Node head1, Node head2) {

        Node l1, l2, r;
        l1 = head1;
        l2 = head2;

        // create a dummy node to simplify the merging process
        Node dummy = new Node(-1);
        r = dummy;

        // merge elements in sorted order
        while (l1 != null && l2 != null) {

            if (l1.data < l2.data) {
                r.next = l1;
                l1 = l1.next;
            } else {
                r.next = l2;
                l2 = l2.next;
            }
            r = r.next;
        }

        // copy the remaining elements from which is not finished yet
        r.next = l1 != null ? l1 : l2;

        return dummy.next;
    }

}
