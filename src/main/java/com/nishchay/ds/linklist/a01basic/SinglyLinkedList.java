package com.nishchay.ds.linklist.a01basic;

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

import static com.nishchay.ds.linklist.Utils.*;

/*
 * Java class to solve various small problems on a singly linked list
 *
 * */
class SinglyLinkedList {

    public static void main(String[] args) {

        createPrintListEx();
        countNodeEx();
        insertEx();
        deleteEx();
        printFromBackEx();
        printFrontAndBackEx();
        isPalindromeEx();
        searchNodeEx();
        nthNodeFromStartEx();
        nthNodeFromLastEx();
    }

    private static void createPrintListEx() {
        Node head = createList();
        printList(head);
    }

    private static void countNodeEx() {
        Node head = createList(new int[]{10, 20, 30, 40, 50, 60});
        printList(head);
        System.out.println("Length of list :" + getLength(head));
    }

    // insert in - beginning(before the head), end(after the tail) and middle (random locating, after some node based on value)
    private static void insertEx() {

        Node head = Utils.createList();

        printList(head);

        System.out.println("--------------insert in beginning----------------------");
        Node newNode = new Node(99);
        newNode.next = head;
        head = newNode;
        printList(head);

        System.out.println("--------------insert at end ----------------------");
        head = Utils.createList();

        if (head == null) { // If the list is empty6
            head = newNode;
            return;
        }

        // position p to the last node
        Node p = head;
        while (p.next != null)
            p = p.next;

        newNode.next = p.next;
        p.next = newNode;
        printList(head);

        System.out.println("--------------insert in middle : after 40 ----------------------");
        head = Utils.createList();
        if (head == null) {  // List is empty, can't insert after a key
            return;
        }
        int key = 40;

        // position p to node having value 40
        Node q = head;
        while (q.data != key && q.next != null) {
            q = q.next;
        }

        if (q == null) { // Key not found
            System.out.println(key + " doesn't exist");
            return;
        }
        newNode.next = q.next;
        q.next = newNode;
        printList(head);
    }

    private static void deleteEx() {
        Node head = Utils.createList();
        printList(head);
        System.out.println("-------------- deleting last node ----------------------");
        head = removeLastNode(head);
        printList(head);
    }

    private static Node removeLastNode(Node head) {

        // Handle Edge Cases: Empty list, Single node list
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }

        // Find the second last node
        Node secondLast = head;
        while (secondLast.next.next != null) {
            secondLast = secondLast.next;
        }

        // Delete the last node by making
        // secondLast point to null
        secondLast.next = null;

        return head;
    }


    private static void printFromBackEx() {
        Node head = Utils.createList();
        printList(head);

        System.out.println("List printing from back :");
        printFromBack(head);
    }


    private static void printFrontAndBackEx() {
        // odd list
        Node head = Utils.createList();
        printList(head);
        System.out.println("Front & Back traversal :");
        printFrontAndBack(head);

        System.out.println("\n----------------------------------------");

        // even list
        head = createList(new int[]{10, 20, 30, 40, 50, 60});
        printList(head);
        System.out.println("Front & Back traversal :");
        printFrontAndBack(head);
    }

    // prints content of a singly linked list from start & end respectively
    private static void printFrontAndBack(Node head) {
        Node start, p, end;
        start = head;

        // get end node
        for (p = head; p.next != null; p = p.next) ;
        end = p;

        String delim = "head => ";
        while (start != end && start.next != end) {
            System.out.print(delim + start.data + " -> " + end.data);
            delim = " -> ";

            // move start forward
            start = start.next;

            // move end backward
            for (p = head; p.next != end; p = p.next) ;
            end = p;
        }
        if (start == end) {
            System.out.print(delim + start.data + " -> null");
        } else if (start.next == end) {
            System.out.print(delim + start.data + delim + end.data + " -> null");
        }
    }

    private static void isPalindromeEx() {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(30);
        head.next.next.next.next = new Node(20);
        head.next.next.next.next.next = new Node(10);
        // head -> 10->20->30->30->20->10 , even length
        printList(head);
        System.out.println("Current list is palindrome - " + isPalindrome(head));

        System.out.println("--------------------------------------");

        head = createList(new int[]{10, 20, 50, 20, 10});
        // head -> 10->20->50->20->10 , odd length
        printList(head);
        System.out.println("Current list is palindrome - " + isPalindrome(head));
    }

    // Function to check if a linked list is palindrome
    private static boolean isPalindrome(Node head) {

        if (head == null) {
            return false;
        }
        Node start, p, end;
        start = head;

        // get end node
        for (p = head; p.next != null; p = p.next) ;
        end = p;

        while (start != end && start.next != end) {

            if (start.data != end.data) {
                return false;
            }
            // move start forward
            start = start.next;

            // move end backward
            for (p = head; p.next != end; p = p.next) ;
            end = p;
        }
        return true;
    }

    private static void searchNodeEx() {
        Node head = Utils.createList();
        printList(head);

        int key = 8;
        System.out.printf("Search key = %d, Found at = %d%n", key, searchNode(head, key));

        key = 50;
        System.out.printf("Search key = %d, Found at = %d%n", key, searchNode(head, key));

        key = 30;
        System.out.printf("Search key = %d, Found at = %d%n", key, searchNode(head, key));

        key = 10;
        System.out.printf("Search key = %d, Found at = %d%n", key, searchNode(head, key));
    }

    // search a key in a singly linked list, return node position else -1
    private static int searchNode(Node head, int key) {
        if (key == head.data) {
            return 1;
        }

        Node p = head;
        int cnt = 1;
        while (p != null && p.data != key) {
            p = p.next;
            cnt++;
        }
        if (p != null) {
            return cnt; // found
        }
        return -1; // not found
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

        System.out.println("-------------------------------------------------------------");
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

    // method to get nth node from the last, return node else null
    private static Node nthNodeFromLast(Node head, int n) {
        if (null == head || n < 1)
            return null;

        Node firstPtr = head;
        Node secondPtr = head;

        // skip n node from start
        for (int i = 1; i <= n; i++) {
            if (firstPtr == null)
                return null;
            firstPtr = firstPtr.next;
        }

        while (firstPtr != null) {
            firstPtr = firstPtr.next;
            secondPtr = secondPtr.next;
        }

        return secondPtr;
    }

    // prints content of a singly linked list from end
    private static void printFromBack(Node head) {
        Node p, end;
        // get end node
        for (p = head; p.next != null; p = p.next) ;
        end = p;

        String delim = "head => ";
        while (head != end) {
            System.out.print(delim + end.data);
            delim = " -> ";

            for (p = head; p.next != end; p = p.next) ;
            end = p;
        }
        System.out.print(delim + end.data);
        System.out.println(" -> null");
    }
}