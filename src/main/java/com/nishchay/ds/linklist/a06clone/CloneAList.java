package com.nishchay.ds.linklist.a06clone;

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

public class CloneAList {

    public static void main(String[] args) {
        Node head = Utils.createList(new int[]{10, 20, 30, 40, 50, 60});
        printNodes(head);
        Node head1 = createCopy(head);
        printNodes(head1);
    }

    private static Node createCopy(Node head) {
        if (head == null) {
            return null;
        }

        Node dummy = new Node(-1);
        Node q = dummy;
        Node newNode;
        for (Node p = head; p != null; p = p.next) {
            newNode = new Node(p.data);
            q.next = newNode;
            q = q.next;
        }
        return dummy.next;
    }


    // Helper function to print a given linked list node wise
    public static void printNodes(Node head) {
        String delim = "head => ";
        for (Node p = head; p != null; p = p.next) {
            System.out.print(delim + p);
            delim = " -> ";
        }
        System.out.println(" -> null");
    }
}
