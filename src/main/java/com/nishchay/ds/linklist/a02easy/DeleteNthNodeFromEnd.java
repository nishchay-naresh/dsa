package com.nishchay.ds.linklist.a02easy;


/*
 *  ======================= Delete Nth node from the end  ====================
 *
 *  Given a linked list and an integer N, the task is to delete the Nth node from the end of the given linked list.
 *
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
 * https://takeuforward.org/data-structure/remove-n-th-node-from-the-end-of-a-linked-list/
 * https://www.geeksforgeeks.org/dsa/write-a-function-to-get-nth-node-in-a-linked-list/
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 * */

import com.nishchay.ds.linklist.Node;

import static com.nishchay.ds.linklist.Utils.createList;
import static com.nishchay.ds.linklist.Utils.printList;

public class DeleteNthNodeFromEnd {

    public static void main(String[] args) {
        Node head = createList(new int[]{1, 10, 30, 14});
        printList(head);
        head = deleteNthNodeFromEnd(head, 2);
        printList(head);
        System.out.println("-----------------------------------");
        head = createList(new int[]{1, 2, 3, 4, 5});
        printList(head);
        head = deleteNthNodeFromEnd(head, 4);
        printList(head);
    }

    /*
     * ================ [Expected Approach] Using Two Pointer Technique and One Pass - O(m) Time and O(1) Space =====================
     *
     *  Since we need to delete the nth node, so we need to reach n-1th node
     *  then delete the secondPtr.next node
     *
     *  So only change we need to do in code - GetNthNode#nthNodeFromLast(-), we need to run the first loop + 1 time
     *
     *
     *  The idea is to maintain two pointers - firstPtr, secondPtr
     *  Now move firstPtr to the Nth node from the head to ensure that the distance between firstPtr and secondPtr is N.
     *  Now, move both the pointers simultaneously until firstPtr reaches the last node.
     *  Since the distance between main_ptr and ref_ptr is N.
     *  So when firstPtr reach null (cross the last node), secondPtr will reach Nth node from the end of Linked List.
     *  Return value of the node pointed by secondPtr.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(1)
     *
     * method to get nth node from the last, return node else null
     */
    private static Node deleteNthNodeFromEnd(Node head, int n) {
        if (null == head || n < 1)
            return null;

        Node firstPtr = head;
        Node secondPtr = head;

        // skip n+1 node from start
        for (int i = 0; i <= n; i++) {
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

        // secondPtr is now at node before target → delete target node
        secondPtr.next = secondPtr.next.next;

        return head;
    }
}
