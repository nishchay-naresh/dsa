package com.nishchay.ds.linklist.a03med;

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

import java.util.HashSet;
import java.util.Set;

/*
 *  ======================= Intersection point of two Linked Lists ====================
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
 * https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
 * https://www.geeksforgeeks.org/dsa/write-a-function-to-get-the-intersection-point-of-two-linked-lists/
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 * https://youtu.be/0JHQ26NQcPk?si=DQaCCNGHsS_wwOZZ
 * */
public class IntersectionPointTwoList {

    public static void main(String[] args) {

        getIntersectionNodeSetExecution();
        getIntersectionNodeExecution();

    }

    private static void getIntersectionNodeSetExecution() {

        // creation of first list: 10 -> 15 -> 30
        Node head1 = new Node(10);
        head1.next = new Node(15);
        head1.next.next = new Node(30);

        // creation of second list: 3 -> 6 -> 9 -> 15 -> 30
        Node head2 = new Node(3);
        head2.next = new Node(6);
        head2.next.next = new Node(9);

        // 15 is the intersection point
        head2.next.next.next = head1.next;
        Node resultNode = getIntersectionNodeSet(head1, head2);
        System.out.println("intersecting node :" + resultNode);
    }

    private static void getIntersectionNodeExecution() {

        // creation of first list: 10 -> 15 -> 30
        Node head1 = new Node(10);
        head1.next = new Node(15);
        head1.next.next = new Node(30);

        // creation of second list: 3 -> 6 -> 9 -> 15 -> 30
        Node head2 = new Node(3);
        head2.next = new Node(6);
        head2.next.next = new Node(9);

        // 15 is the intersection point
        head2.next.next.next = head1.next;

        Node resultNode = getIntersectionNode(head1, head2);
        System.out.println("intersecting node :" + resultNode);
    }

    /*
     *
     * ================ [Better Approach] Using Hashing - O(m + n) Time and O(m) Space =====================
     *
     *
     * traverse the first list and add all the nodes of a list to hashSet
     *  Again, traverse the second list and check each note presence in hashSet
     *      if node exists in hashSet
     *          return it -- we get the intersecting node
     *      else
     *          return null;
     *
     *  Time Complexity     : O(n + m)
     *  Space complexity    : O(m)
     */
    private static Node getIntersectionNodeSet(Node headA, Node headB) {

        if (headA == null) return headA;
        if (headB == null) return headB;

        Set<Node> nodeAddress = new HashSet<>();

        while (headA != null) {
            nodeAddress.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (nodeAddress.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }

    /*
     * ================ [Expected Approach] Using Two Pointer Technique - O(m + n) Time and O(1) Space =====================
     *
     * [Efficient Approach] Using Iterative Merge - O(n+m) Time and O(1) Space
     *
     * Get the length for both of the list
     *  Traverse in longer list till both the list have same number of nodes
     * now traverse in both of the lists simultaneously find the intersecting node
     *
     *  Time Complexity     : O(n + m)
     *  Space complexity    : O(1)
     */
    private static Node getIntersectionNode(Node headA, Node headB) {

        int lenA = Utils.getLength(headA);
        int lenB = Utils.getLength(headB);

        while (lenA > lenB) {
            lenA--;
            headA = headA.next;
        }

        while (lenB > lenA) {
            lenB--;
            headB = headB.next;
        }

        // Now both heads are at same distance from intersection
        // Start moving them both until they meet
        while(headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    //
    /*
     * ================ [Expected Approach - 3] Intersection Detection using Floyd’s Cycle-Finding Algorithm - O(m + n) Time and O(1) Space =====================
     *
     * FIXME - do this with practice from here
     *  https://www.geeksforgeeks.org/dsa/write-a-function-to-get-the-intersection-point-of-two-linked-lists/
     *
     *  Time Complexity     : O(n + m)
     *  Space complexity    : O(1)
     */

}
