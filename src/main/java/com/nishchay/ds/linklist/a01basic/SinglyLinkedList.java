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

        if (head == null) { // If the list is empty
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
    * ======== backward traversal in a list ==============
    *  printing a singly linked list from the end
    * */
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