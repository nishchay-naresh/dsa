package com.nishchay.ds.linklist;

public class Utils {

    // creating a singly linked list (10-50)
    public static Node createList() {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        head.next.next.next.next.next = null;
        return head;
    }

    /*
    * create a singly linked list from an array int[]
    *
    *  Cons - You need special handling for the first element (arr[0])
    * */
    public static Node createListSimple(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node head = new Node(arr[0]);
        Node current = head;

        // Iterate through the rest of the array to create subsequent nodes
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    /*
    *   ========= Dummy Node Approach ============
    *  Create a dummy node that is not part of the actual list.
    *  Build the list starting from dummy.next.
    *  No special case needed for the first element.
    *  Code is easier to maintain.
    * */
    public static Node createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node dummy = new Node(-1);
        Node p = dummy;
        Node newNode;
        for (int i = 0; i < arr.length; i++) {
            newNode = new Node(arr[i]);
            p.next = newNode;
            p = p.next;
        }
        return dummy.next;
    }

    // Helper function to print a given linked list
    public static void printList(Node head) {
        System.out.println("Link List Content :");
        String delim = "head => ";
        for (Node p = head; p != null; p = p.next) {
            System.out.print(delim + p.data);
            delim = " -> ";
        }
        System.out.println(" -> null");
    }

    public static void printList1(Node head) {
        String delim = "head => ";
        for (Node p = head; p != null; p = p.next) {
            System.out.print(delim + p.data);
            delim = " -> ";
        }
        System.out.println(" -> null");
    }

    public static int getLength(Node head) {
        int size = 0;
        Node curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

}
