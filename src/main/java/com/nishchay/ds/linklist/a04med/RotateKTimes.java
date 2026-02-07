package com.nishchay.ds.linklist.a04med;


/*
 *  ======================= Rotate a Linked List ====================
 *
 * Given the head of a singly linked list and an integer k, rotate the list to the left by k positions and return the updated head.
 *
 * Examples:
 *			Input : Head -> 10 -> 20 -> 30 -> 40 -> 50 -> null, k=4
 *			Output: Head -> 50 -> 10 -> 20 -> 30 -> 40 -> null
 *
 *			Input : Head -> 10 -> 20 -> 30 -> 40 -> null, k=6
 *			Output: Head -> 30 -> 40 -> 10 -> 20 -> null
 *
 * https://www.geeksforgeeks.org/dsa/rotate-a-linked-list/
 * https://leetcode.com/problems/rotate-list/description/
 *
 * */

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

public class RotateKTimes {

    public static void main(String[] args) {

        approach1();
        System.out.println("-----------------------------------------");
        approach2();

    }

    private static void approach1() {
        Node head = Utils.createList(new int[]{10, 20, 30, 40, 50});
        Utils.printList(head);

        int k = 2;
        System.out.println("After " + k + " node rotation");
        Node resultHead = rotateByMovingOneNodeKTimes(head, k);
        Utils.printList(resultHead);
    }

    private static void approach2() {
        Node head = Utils.createList(new int[]{10, 20, 30, 40, 50});
        Utils.printList(head);
        int k = 2;
        System.out.println("After " + k + " node rotation");
        Node resultHead = rotateByMakingListCircular(head, k);
        Utils.printList(resultHead);
        System.out.println("-----------------------------------------");

        head = Utils.createList(new int[]{10, 20, 30, 40});
        Utils.printList(head);
        k = 4;
        System.out.println("After " + k + " node rotation");
        resultHead = rotateByMakingListCircular(head, k);
        Utils.printList(resultHead);
        System.out.println("-----------------------------------------");

        head = Utils.createList(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90});
        Utils.printList(head);
        k = 37;
        System.out.println("After " + k + " node rotation");
        resultHead = rotateByMakingListCircular(head, k);
        Utils.printList(resultHead);
    }

    /*
     *  ================  [Naive Approach] Shifting head node to the end k times - O(n × k) Time and O(1) Space =====================
     *
     * To rotate a linked list to the left k places, we can repeatedly move the head node to the end of the linked list k times.
     *
     *
     * Time Complexity  : O(n*k)
     * Space Complexity : O(1)
     */
    private static Node rotateByMovingOneNodeKTimes(Node head, int k) {
        if (k == 0 || head == null)
            return head;

        // move the head node to the end of the linked list - doing this k time
        for (int i = 0; i < k; ++i) {
            Node tail = head;
            while (tail.next != null){
                tail = tail.next;
            }

            // move the first node to the last
            tail.next = head;
            tail = tail.next;
            head = head.next;
            tail.next = null;
        }
        return head;
    }


    /*
     *  ================ [Expected Approach] By changing pointer of kth node - O(n) Time and O(1) Space =====================
     *
     * 1. The idea is to first convert the linked list to circular linked list - by updating the next pointer of last node to the head of linked list.
     * 2. Locate the new lastNode
     *      Then, traverse to the kth node and update the head of the linked list to the (k+1)th node.
     * 3. Finally, break the loop by updating the next pointer of kth node to NULL.
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     */
    private static Node rotateByMakingListCircular(Node head, int k) {

        if (k == 0 || head == null)
            return head;

        // find the length of linked list
        int length = 0;
        Node tail = head;
        while (tail.next != null) {
            length ++;
            tail = tail.next;
        }
        // since breaking the loop on last node - to keep the tail pointer on last node
        // explicit counting last node
        length = length + 1;

        // modulo k with length of a linked list to handle large values of k (k > length)
        k = k % length;
        if (k == 0){
            return head;
        }

        // convert the linked list to a circular linked list
        tail.next = head;

        // traverse the linked list to find the kth node
        tail = head;
        for (int i = 1; i < k; i++)
            tail = tail.next;

        // update the (k + 1)th node as the new head
        head = tail.next;

        // break the loop by updating next pointer of kth node
        tail.next = null;
        return head;
    }
}
