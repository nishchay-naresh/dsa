package com.nishchay.ds.linklist.a05hard;



/*
 *  ======================= Flattening a Linked List  (Depth wise) ====================
 *
 *  Given a linked list containing n head nodes where every node in the linked list contains two pointers:
 *      Next points to the next node in the list.
 *      Bottom pointer to a sub-linked list where the current node is the head. Each of the sub-linked lists nodes and the head
 * Nodes are sorted in ascending order based on their data.
 * We need to flatten the linked list so that all the nodes appear in a single level while maintaining the sorted order.
 *
 * Examples:
 *          Input:   head -> 3 -> 2 -> 1 -> 4 -> 5 -> null
 *                 	        |	|     |	   |	|
 *			                X	10    7    9	6
 *				                |     |    |    |
 *    				            X     11   X	8
 *				                      |		    |
 *				                      12	    X
 *                                    |
 *                                    X
 *
 *
 * X - null
 *          Output:  1 2 3 4 5 6 7 8 9 10 11 12
 *                  head -> 1 -> null
 *                          |
 *                          2 -> null
 *                          |
 *                          3 -> null
 *                          |
 *                          4 -> null
 *                          |
 *                          5 -> null
 *                          |
 *                          6 -> null
 *                          |
 *                          7 -> null
 *                          |
 *                          8 -> null
 *                          |
 *                          9 -> null
 *                          |
 *                          10 -> null
 *                          |
 *                          11 -> null
 *                          |
 *                          12 -> null
 *
 *
 * https://takeuforward.org/data-structure/flattening-a-linked-list/
 * https://www.geeksforgeeks.org/dsa/flatten-a-linked-list-with-next-and-child-pointers/
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/
 *
 * */

import java.util.ArrayList;
import java.util.Collections;

public class FlatteningAListSorted {


    public static void main(String[] args) {
        flattenLinkedListExec();
        System.out.println("------------------------------------");
        flattenLinkedListRecursionEx();
    }

    private static void flattenLinkedListExec() {

        // Create a linked list with child pointers
        Node head = new Node(5);
        head.child = new Node(14);

        head.next = new Node(10);
        head.next.child = new Node(4);

        head.next.next = new Node(12);
        head.next.next.child = new Node(20);
        head.next.next.child.child = new Node(13);

        head.next.next.next = new Node(7);
        head.next.next.next.child = new Node(17);

        // Print the original linked list structure
        System.out.println("Original linked list:");
        printListNextAndChildPointer(head, 0);

        // Flatten the linked list
        // and print the flattened list
        Node flattened = flattenLinkedList(head);
        System.out.println("\nFlattened linked list: ");
        printListChildPointerOnly(flattened);
    }

    private static void flattenLinkedListRecursionEx() {
        // Create a linked list with child pointers
        Node head = new Node(5);
        head.child = new Node(14);

        head.next = new Node(10);
        head.next.child = new Node(4);

        head.next.next = new Node(12);
        head.next.next.child = new Node(20);
        head.next.next.child.child = new Node(13);

        head.next.next.next = new Node(7);
        head.next.next.next.child = new Node(17);

        // Print the original linked list structure
        System.out.println("Original linked list:");
        printListNextAndChildPointer(head, 0);

        // Flatten the linked list
        // and print the flattened list
        Node flattened = flattenLinkedListRecursion(head);
        System.out.println("\nFlattened linked list: ");
        printListChildPointerOnly(flattened);
    }

    // Print the linked list by traversing through child pointers
    private static void printListChildPointerOnly(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.child;
        }
        System.out.println();
    }

    // Print the linked list in a grid-like structure
    static void printListNextAndChildPointer(Node head, int depth) {
        while (head != null) {
            System.out.print(head.data);

            // If a child exists, recursively
            // print it with indentation
            if (head.child != null) {
                System.out.print(" -> ");
                printListNextAndChildPointer(head.child, depth + 1);
            }

            // Add vertical bars
            // for each level in the grid
            if (head.next != null) {
                System.out.println();
                for (int i = 0; i < depth; ++i) {
                    System.out.print("| ");
                }
            }
            head = head.next;
        }
    }

    /*
     * ================ [Naive/Bruteforce Approach]  two loop , array sorting, crating a list from array  =====================
     *  N - length
     *  M - height
     *
     *  Algorithm
     *  Step 1: Traverse the given list and extract all the data to an array.
     *          Initialise an empty array to store the data extracted during the traversal.
     *          Two loops:
     *                  for (Node nextPtr = head; till null; nextPtr = nextPtr.next)
     *                      for (Node childPtr = nextPtr till null; childPtr = childPtr.child)
     *                          keep adding all node data to array
     *
     *  Step 2: Sort the array to arrange its collected node data in ascending order.
     *  Step 3: Create a new linked list from the sorted array and return the flattened linked list.
     *
     *
     *  Time Complexity     : O(N X M) + O( N X M log N X M) - sorting
     *  Space complexity    : O(N X M)
     */
    private static Node flattenLinkedList(Node head) {
        ArrayList<Integer> arr = new ArrayList<>();
        Node nextPtr = head;
        while (nextPtr != null) {
            // Traverse through the child nodes of each head node
            Node childPtr = nextPtr;
            while (childPtr != null) {
                // Store each node's data in the ArrayList
                arr.add(childPtr.data);
                childPtr = childPtr.child;
            }
            nextPtr = nextPtr.next;
        }

        // Sort the ArrayList containing node values in ascending order
        Collections.sort(arr);

        // Convert the sorted ArrayList back to a linked list
        return convertArrToLinkedList(arr);
    }

    // Function to convert an ArrayList to a linked list
    static Node convertArrToLinkedList(ArrayList<Integer> arr) {

        Node dummyNode = new Node(-1);
        Node p = dummyNode;
        Node newNode;

        for (int i = 0; i < arr.size(); i++) {
            newNode = new Node(arr.get(i));
            p.child = newNode;
            p = p.child;
        }
        return dummyNode.child;
    }


    /*
     * ================ [Optimal Approach] using the sorted list feature and applying merging  of two sorted list concept  =====================
     *  N - length
     *  M - height
     *
     *  Algorithm / Intuition
     *  The time and space complexity of the previous approach can be optimized as
     *  We have not yet leveraged the given property that the child linked lists are sorted.
     *  We can eliminate the additional space and time complexity generated by sorting by using these sorted vertical linked lists.
     *
     *  Instead of collecting all node values into an array and then sorting them,
     *  We can merge these pre-sorted lists directly during the traversal,
     *  eliminating the need for additional sorting steps.
     *  This merge operation can be performed efficiently in place without allocating extra space for the combined linked list.
     *
     *
     *  Time Complexity     : O(N X M) + O( N X M log N X M) - sorting
     *  Space complexity    : O(N X M)
     */
    // Flattens a linked list with child pointers
    public static Node flattenLinkedListRecursion(Node head) {
        // If head is null or there
        // is no next node, return head
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively flatten the
        // rest of the linked list
        Node mergedHead = flattenLinkedListRecursion(head.next);
        head = merge(head, mergedHead);
        return head;
    }

    // Merges two linked lists in a particular order based on the data value
    public static Node merge(Node list1, Node list2) {
        Node dummyNode = new Node(-1);
        Node res = dummyNode;

        // Merge the lists based on data values
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                res.child = list1;
                res = list1;
                list1 = list1.child;
            } else {
                res.child = list2;
                res = list2;
                list2 = list2.child;
            }
            res.next = null;
        }

        // Connect the remaining elements if any
        if (list1 != null) {
            res.child = list1;
        } else {
            res.child = list2;
        }

        // Break the last node's link to prevent cycles
        if (dummyNode.child != null) {
            dummyNode.child.next = null;
        }

        return dummyNode.child;
    }

    static class Node {
        int data;
        Node next;
        Node child;

        // Constructors to initialize the
        // data, next, and child pointers
        Node() {
            data = 0;
            next = null;
            child = null;
        }

        Node(int x) {
            data = x;
            next = null;
            child = null;
        }

        Node(int x, Node nextNode, Node childNode) {
            data = x;
            next = nextNode;
            child = childNode;
        }
    }
}

