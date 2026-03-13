package com.nishchay.ds.linklist.a05hard;


import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;
import com.nishchay.ds.linklist.a03med.MergeTwoSortedList;

/*
 *  ============================== leetcode -23 Merge K sorted linked lists ======================================
 * Given an array of k sorted linked-lists of different sizes, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list
 *
 * Examples:
 *			Input:
 *					head1 -> 2 -> 4 -> 6 -> NULL
 *					head2 -> 1 -> 5 -> NULL
 *					head3 -> 1 -> 1 -> 3 -> 7 -> NULL
 *					head4 -> 8 -> NULL
 *			Output :
 *                  head -> 1 -> 1 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> NULL
 *
 *
 *  https://leetcode.com/problems/merge-k-sorted-lists/description/
 *  https://www.geeksforgeeks.org/dsa/merge-k-sorted-linked-lists/
 *
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        ex1();
        ex2();
    }

    private static void ex1() {
        // Create linked lists
        Node head1 = new Node(2, new Node(4, new Node(6)));
        Node head2 = new Node(1, new Node(5));
        Node head3 = new Node(1, new Node(1, new Node(3, new Node(7))));
        Node head4 = new Node(8);

        // Populate the list vector
        Node[] lists = new Node[4];
        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;
        lists[3] = head4;


        Node head = mergeKLists(lists);
        Utils.printList(head);
        // head -> 1 -> 1 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> NULL
    }

    private static void ex2() {

        // Create linked lists
        Node head1 = new Node(1, new Node(3, new Node(5, new Node(7))));
        Node head2 = new Node(2, new Node(4, new Node(6, new Node(8))));
        Node head3 = new Node(0, new Node(9, new Node(10, new Node(11))));

        // Populate the lists in array
        Node[] lists = new Node[3];
        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;

        Node head = mergeKLists(lists);
        Utils.printList(head);
        // head ->  0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> NULL

        Node l1 = new Node(1);
        l1.next = new Node(4);
        l1.next.next = new Node(5);

        Node l2 = new Node(1);
        l2.next = new Node(3);
        l2.next.next = new Node(4);

        Node l3 = new Node(2);
        l3.next = new Node(6);

        // Populate the lists in array
        lists = new Node[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        head = mergeKLists(lists);
        Utils.printList(head);
        // head -> 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6 -> null
    }

    /*
     *	----------- Intuition ----------------
     *	Exactly like Merge Sort - Instead of merging lists one-by-one
     *	we:
     *		Split K lists into two halves
     *		Recursively merge each half
     *		Merge the two results
     *
     *  	Time Complexity     : O(N log K), N = total nodes, K = number of list
     *  	Space complexity    : O(log K), Recursive stack
     *
     *	-------------------------------
     *	Sequential Merge - Merge lists one-by-one.
     *
     *  	Time Complexity     : O(NK), N = total nodes, K = number of list
     *  	Space complexity    : O(1)
     *
     * */
    private static Node mergeKLists(Node[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private static Node mergeKListsHelper(Node[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        if (start + 1 == end) {
            return MergeTwoSortedList.mergeSortedList(lists[start], lists[end]);
        }

        int mid = start + (end - start) / 2;
        Node left = mergeKListsHelper(lists, start, mid);
        Node right = mergeKListsHelper(lists, mid + 1, end);
        return MergeTwoSortedList.mergeSortedList(left, right);
    }

}


