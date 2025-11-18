package com.nishchay.ds.linklist.a02easy;

/*
 *  ======================= Get Nth node from the end of a Linked List ====================
 *
 *  Given a Linked List of M nodes and a number N, find the value at the Nth node from the end of the Linked List.
 *  If there is no Nth node from the end, print -1.
 *
 * Examples :
 *			Input: 2 -> 3 -> 1 -> 7 -> NULL, N = 1
 *			Output: 2 3 1
 *			Explanation: The created linked list is: 2 3 1 7
 *			The linked list after deletion is: 2 3 1
 *
 *			Input: 1 -> 2 -> 3 -> 4 -> NULL, N = 4
 *			Output: 2 3 4
 *			Explanation: The created linked list is: 1 2 3 4
 *			The linked list after deletion is: 2 3 4
 *
 *			Input: 5 -> 3 -> 8 -> 6 -> NULL, N = 2
 *			Output: 5 3 6
 *			Explanation: The created linked list is: 5 3 8 6
 *			The linked list after deletion is: 5 3 6
 *
 *
 * https://www.geeksforgeeks.org/dsa/write-a-function-to-get-nth-node-in-a-linked-list/
 * https://www.geeksforgeeks.org/dsa/nth-node-from-the-end-of-a-linked-list/
 * */

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

import static com.nishchay.ds.linklist.Utils.createList;
import static com.nishchay.ds.linklist.Utils.printList;

public class GetNthNode {

    public static void main(String[] args) {
        nthNodeFromStartEx();
        System.out.println("----------------------------------------------");
        nthNodeFromLastEx();
    }

    /*
     *
     * Function to get Nth Node from Start
     *
     * Input:  1->10->30->14,  N = 2
     * Output: 10
     * Explanation: The node value at N 2 is 10
     *
     * Input:  1->32->12->10->30->14->100,  N = 6
     * Output: 14
     * Explanation: The node value at N 6 is 14
     *
     * Input:  1->32->12->10->30->14->100,  N = 8
     * Output: -1
     * Explanation: No such node exists at N = 8.
     *
     * */
    private static void nthNodeFromStartEx() {
        Node head = createList(new int[]{1, 10, 30, 14});
        // head -> 1->10->30->14
        printList(head);
        int index = 2;
        System.out.println(index + "th node in list - " + nthNodeFromStart(head, index));

        System.out.println(".............................");
        head = createList(new int[]{1, 32, 12, 10, 30, 14, 100});
        // head -> 1->32->12->10->30->14->100
        printList(head);

        index = 6;
        System.out.println(index + "th node in list - " + nthNodeFromStart(head, index));

        index = 8;
        System.out.println(index + "th node in list - " + nthNodeFromStart(head, index));
    }

    private static int nthNodeFromStart(Node head, int n) {
        if (head == null || n < 1)
            return -1;

        // if n equal to 1 return node.data
        if (n == 1)
            return head.data;

        Node curr = head;
        while (n > 1 && curr != null) {
            curr = curr.next;
            n--;
        }
        if (curr != null)
            return curr.data;

        return -1;
    }


    /*
     *
     * Function to get Nth Node from end
     *
     * Input:  1 -> 2 -> 3 -> 4, N = 3
     * Output: 2
     * Explanation: Node 2 is the third node from the end of the linked list.
     *
     * Input:  35 -> 15 -> 4 -> 20, N = 4
     * Output: 35
     * Explanation: Node 35 is the fourth node from the end of the linked list.
     *
     * */
    private static void nthNodeFromLastEx() {
        Node head = Utils.createList();
        printList(head);

        int n = 2;
        System.out.printf("%d th node from last = %s%n", n, nthNodeFromLast(head, n));
        n = 4;
        System.out.printf("%d th node from last = %s%n", n, nthNodeFromLast(head, n));

        // edge cases
        n = 1;
        System.out.printf("%d th node from last = %s%n", n, nthNodeFromLast(head, n));
        n = 5;
        System.out.printf("%d th node from last = %s%n", n, nthNodeFromLast(head, n));

        // failure cases
        n = 0;
        System.out.printf("%d th node from last = %s%n", n, nthNodeFromLast(head, n));
        n = 8;
        System.out.printf("%d th node from last = %s%n", n, nthNodeFromLast(head, n));
    }

    /*
     * ================ [Expected Approach] Using Two Pointer Technique and One Pass - O(m) Time and O(1) Space =====================
     *
     *  The idea is to maintain two pointers - firstPtr, secondPtr
     *  Now move firstPtr to the Nth node from the head to ensure that the distance between firstPtr and secondPtr is N.
     *  Now, move both the pointers simultaneously until firstPtr reaches the last node.
     *  Since the distance between main_ptr and ref_ptr is N, so when firstPtr reach the null (cross the last node),
     *  secondPtr will reach Nth node from the end of Linked List. Return the value of the node pointed by secondPtr.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * method to get nth node from the last, return node else null
     */
    private static Node nthNodeFromLast(Node head, int n) {
        if (null == head || n < 1)
            return null;

        Node firstPtr = head;
        Node secondPtr = head;

        // skip n node from start
        for (int i = 1; i <= n; i++) {
            firstPtr = firstPtr.next;
            if (firstPtr == null){
                return null;
            }
        }

        // Move both pointers until firstPtr reaches the end
        while (firstPtr != null) {
            firstPtr = firstPtr.next;
            secondPtr = secondPtr.next;
        }

        return secondPtr;
    }

}
