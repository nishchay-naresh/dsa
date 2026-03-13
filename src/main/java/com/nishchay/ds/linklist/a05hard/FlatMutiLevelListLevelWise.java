package com.nishchay.ds.linklist.a05hard;


import java.util.LinkedList;
import java.util.Queue;

/*
 *  ==================== Flatten a multi-level linked list (Depth wise) ======================================
 *  Flat a muti-level singly linked list, level wise
 *
 *  Given a linked list where in addition to the next pointer, each node has a child pointer, which may or may not point to a separate list.
 *  These child lists may have one or more children of their own to produce a multilevel linked list.
 *  Given the head of the first level of the list. The task is to flatten the list so that all the nodes appear in a single-level linked list.
 *  Flatten the list in a way that all nodes at the first level should come first, then nodes of the second level, and so on.
 *
 * Examples:
 *			input :
 *			1 -> 2 -> 3 -> 4 -> null
 *				 |         |
 *				 5 ->6     9 -> 10
 *				 |
 *				 11
 *
 *			output :
 *			1-->2-->3-->4-->5-->6-->9-->10-->11
 *
 *          input :
 *			10 -> 5 -> 12 -> 7 -> 11 -> null
 *			 |		         |
 *			 4 -> 20 -> 13  17
 *			      |     |
 *			      11    16
 *
 *          output :
 *			        10-->5-->12-->7-->11-->4-->20-->13-->17-->11-->16-->null
 *
 *
 *  Asked in PayPal
 *
 *  https://www.geeksforgeeks.org/dsa/flatten-a-multi-level-linked-list-set-2-depth-wise/
 *  https://www.naukri.com/code360/problem-details/flatten-the-multi-level-linked-list_839810
 *
 */
public class FlatMutiLevelListLevelWise {

    public static void main(String[] args) {

        // Create a hard coded multi-linked list.
        //	10 -> 5 -> 12 -> 7 -> 11 -> null
        //	 |		         |
        //	 4 -> 20 -> 13  17
        //	      |     |
        //	      11    16

        Node1 head = new Node1(10);
        head.next = new Node1(5);
        head.next.next = new Node1(12);
        head.next.next.next = new Node1(7);
        head.next.next.next.next = new Node1(11);
        head.next.next.next.next.next = null;

        head.down = new Node1(4);
        head.down.next = new Node1(20);
        head.down.next.next = new Node1(13);
        head.down.next.down = new Node1(11);
        head.down.next.next.down = new Node1(16);

        head.next.next.next.down = new Node1(17);

        flatten(head);
        printList(head);                //  10-->5-->12-->7-->11-->4-->20-->13-->17-->11-->16-->null

        System.out.println("--------------------------------------");
        // Create a hard coded multi-linked list.
        // 5 -> 10 -> 19 -> 28
        // |           |
        // 7           22
        // |           |
        // 8           50
        // |
        // 30

//        head = new Node1(5);
//        head.down = new Node1(7);
//        head.down.down = new Node1(8);
//        head.down.down.down = new Node1(30);
//        head.next = new Node1(10);
//        head.next.next = new Node1(19);
//        head.next.next.down = new Node1(22);
//        head.next.next.down.down = new Node1(50);
//        head.next.next.next = new Node1(28);
//        flatten(head);
//        printList(head);

    }

    static void printList(Node1 head) {
        Node1 curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /*
     * ================ [Alternate Approach] Using Stack - O(n) Time and O(n) Space  =====================
     *
     *  You must flatten it level-wise (like BFS):
     *      First all nodes of level-1
     *      Then all children (level-2)
     *      Then all grandchildren (level-3)
     *                          … and so on.
     * Best Approach: Use a Queue → BFS (Level Order Traversal)
     * Why?
     *      Because BFS ensures: 1st level → 2nd → 3rd → …
     *      Queue stores child lists to be flattened later.
     *
     * Intuition / Logic :
     *              Step 1: Tail pointer
     *              Step 2: Traverse level-1 list
     * 	                    If a node has a child → store it in queue
     *              Step 3: When end of level-1 is reached
     * 	                    When reaching the last node for the current level - we extract the next list from queue
     *              Step 4: Continue processing this appended list
     *                      Same logic:
     * 	                        -   Process nodes normally
     * 	                        -   If node has child → push into queue
     * 	                        -   When reach end → attach next from queue
     *
     *  Time Complexity     :  O(N), Total work = visiting every node once → O(N)
     *  Space complexity    :  O(W), Where W = maximum number of child lists waiting in the queue at any time.
     */
    public static void flatten(Node1 head) {

        if (head == null) {
            return;
        }

        Queue<Node1> queue = new LinkedList<>();

        // We will build the result list using a tail pointer
        // means we will keep adding level wise node in this tail pointer and keep moving it
        Node1 tail = head;

        // Move tail to the end of top-level list
        while (tail.next != null) {
            tail = tail.next;
        }
        Node1 curr = head;
        // Traverse level-1 using curr
        while (curr != null) {

            // If curr has a child, add a child list to queue
            if (curr.down != null) {
                queue.add(curr.down);
            }

            // If next is null, and queue is not empty,
            // append the next level list to tail
            Node1 popNode;
            if (curr.next == null && !queue.isEmpty()) {
                popNode = queue.poll();  // attach a child list
                tail.next = popNode;
                // move tail to the end of the appended list
                while (popNode.next != null){
                    popNode = popNode.next;
                }
                tail = popNode;
            }
            curr = curr.next;
        }
    }

    static class Node1 {
        int data;
        Node1 next, down;

        Node1(int x) {
            data = x;
            next = down = null;
        }
    }
}


