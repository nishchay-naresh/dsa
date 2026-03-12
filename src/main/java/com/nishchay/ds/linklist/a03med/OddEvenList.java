package com.nishchay.ds.linklist.a03med;

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

/*
 *  ======================= Odd Even Linked Lists ====================
 *
 *  Given the heads of two non-empty linked lists representing two non-negative integers.
 *  The digits are stored in reverse order, and each of their nodes contains a single digit.
 *  Add the two numbers and return the sum as a linked list.
 *
 * Examples:
 * 			Input: head = [1,2,3,4,5]
 *							head1 -> 1 -> 2 -> 3 -> 4 -> 5 -> null
 * 			Output: head = [1,3,5,2,4]
 *							headr -> 2 -> 4 -> 1 -> 3 -> 5  -> null
 *
 * 			Input: head = [2,1,3,5,6,4,7]
 *							head1 -> 2 -> 1 -> 3 -> 5 -> 6 -> 4 -> 7 -> null
 * 			Output: [2,3,6,7,1,5,4]
 *							headr -> 2 -> 6 -> 4 -> 1 -> 3 -> 5 -> 7-> null
 *
 *			Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> Null
 *			Output: 2 -> 4 -> 6 -> 1 -> 3 -> 5 -> Null
 *			Explanation :
 *			Odd Nodes in LinkedList are 1,3,5 and
 *			Even Nodes in LinkedList are 2,4,6
 *			In Modified LinkedList all even Nodes comes before
 *			all Odd Nodes. So the modified linkedList looks like
 *			2 -> 4 -> 6 -> 1 -> 3 -> 5 -> Null. Order of even and odd Nodes is
 *			maintained in modified LinkedList.
 *
 *			Example 2:
 *			Input: 1 -> 3 -> 5 -> Null
 *			Output: 1 -> 3 -> 5 -> Null
 *			Explantion: As there are no Even Nodes in LinkedList,
 *			The Modified LinkedList is the same as the original linkedList.
 *
 *			Example 3:
 *			Input: 2 -> 4 -> 6 -> 8 -> Null
 *			Output: 2 -> 4 -> 6 -> 8 -> Null
 *			Explanation: As there are no Odd Nodes in LinkedList,
 *			The Modified LinkedList is same as Original LinkedList.
 *
 * https://www.geeksforgeeks.org/dsa/segregate-even-and-odd-elements-in-a-linked-list/
 * https://takeuforward.org/data-structure/segregate-even-and-odd-nodes-in-linkedlist
 * https://leetcode.com/problems/odd-even-linked-list/description/
 * */
public class OddEvenList {

    public static void main(String[] args) {

        Node head = Utils.createList(new int[]{1, 2, 3, 4, 5, 6});
        Node resultHead = segregateToOddEVen(head);
        System.out.println("Updated list :");
        Utils.printList1(resultHead);                          // 2 -> 4 -> 6 -> 1 -> 3 -> 5 -> null

        head = Utils.createList(new int[]{2, 1, 3, 5, 6, 4, 7});
        resultHead = segregateToOddEVen(head);
        System.out.println("Updated list :");
        Utils.printList1(resultHead);                          // 2 -> 6 -> 4 -> 1 -> 3 -> 5 -> 7-> null
    }

    /*
     * ================ [Expected Approach] Two Pointer Add  =====================
     *
     * Intuition :
     *	    The Intuition is to Split the LinkedList into two parts.
     *      One Contains all the Even Nodes and the other contains all the Odd Ends.
     *	    After obtaining two linkedLists we attach odd LinkedList at the end of the Even LinkedList.
     *
     *	    To split the LinkedList take two dummy Nodes which act as the Heads of the odd and Even LinkedList.
     *      Traverse the original LinkedList.
     *		    While traversing if the Node is odd remove the Node from the original LinkedList and add to odd LinkedList. Similarly for the Even Node too.
     *
     *	    After traversing, we obtain two LinkedList with all odd and all Even Nodes.
     *      Attach odd LinkedList at the end of Even LinkedList.
     *      As we are appending nodes to Odd and Even LinkedLists one by one the order of Nodes is undisturbed.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static Node segregateToOddEVen(Node head) {

        Node oddDummy = new Node(-1);
        Node evenDummy = new Node(-1);
        Node odd = oddDummy;
        Node even = evenDummy;

        Node newNode;
        int val;

        Node p = head;
        while (p != null) {
            val = p.data;
            newNode = new Node(val);

            if (val % 2 == 0) {
                even.next = newNode;
                even = even.next;
            } else {
                odd.next = newNode;
                odd = odd.next;
            }
            p = p.next;
        }
        // Appending Odd LinkedList at end of EvenLinkedList
        // reaching end of even list
        p = evenDummy.next;
        while (p.next != null) {
            p = p.next;
        }
        p.next = oddDummy.next;
        return evenDummy.next;
    }

}
