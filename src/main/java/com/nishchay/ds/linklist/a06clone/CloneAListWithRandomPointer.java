package com.nishchay.ds.linklist.a06clone;

import java.util.HashMap;

/*
 *  ==================== Clone a Linked List with Random Pointer ======================================
 *
 * Given a head of linked list where each node has two links: next pointer pointing to the next node and random pointer to any random node in the list.
 *  Create a clone of this linked list.
 *
 *
 * https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/
 * https://www.geeksforgeeks.org/dsa/a-linked-list-with-next-and-arbit-pointer/
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 */
public class CloneAListWithRandomPointer {

    public static void main(String[] args) {
        cloneListUsingMapExec();
        System.out.println("-------------------------------------------------");
        cloneListExec();
    }

    private static void cloneListUsingMapExec() {

        // Example linked list: 7 -> 14 -> 21 -> 28
        Node head = new Node(7);
        head.next = new Node(14);
        head.next.next = new Node(21);
        head.next.next.next = new Node(28);

        // Assigning random pointers
        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next.next;
        head.next.next.next.random = head.next;

        System.out.println("Original linked list:");
        printClonedLinkedList(head);

        // Clone the linked list
        Node clonedList = cloneListUsingMap(head);

        System.out.println("Cloned linked list:");
        printClonedLinkedList(clonedList);
    }

    private static void cloneListExec() {

        // Creating a linked list with a random pointer
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head.next;

        // Print the original list
        System.out.println("Original linked list:");
        printClonedLinkedList(head);

        Node clonedList = cloneList(head);

        System.out.println("Cloned linked list:");
        printClonedLinkedList(clonedList);
    }

    /*
     *
     *  The main challenge here is to assign the random pointer, because in the convention way of cloning (traversing left to right)
     *  Node which needs to be pointed by random pointer might not exist
     *
     *  ================ [Naive Approach] Using Hashing - O(n) Time and O(n) Space =====================
     *  Algorithm / Intuition :
     *      To tackle this problem of - Node which needs to be pointed by random pointer might not exist
     *
     *      1. We traverse the original list first and will create a copied node for each original node.
     *      2. We will use a map to establish a relationship between original nodes and their copied nodes
     *      3. We will traverse the original list one more time to
     *              establish the connections (next and random pointers) between the copied nodes by following the connections of the original nodes.
     *      4. In the end, return the head of the copied list obtained from the map.
     *
     *
     * Time Complexity  : 2 traverse of given list          = O(n) + O(n) = O(2n)
     * Space Complexity : n for hashMap + n for cloned list = O(n) + O(n) = O(2n)
     *
     * This hashMap is the extra thing we are using - can we remove this further optimize this solution
     *
     */
    public static Node cloneListUsingMap(Node head) {
        if (head == null) {
            return null;
        }
        // HashMap to map original nodes <-> to their corresponding copied nodes
        HashMap<Node, Node> map = new HashMap<>();
        Node newNode;

        // Step 1: Create copies of each node and store them in the map
        for (Node p = head; p != null; p = p.next) {
            newNode = new Node(p.data);
            map.put(p, newNode);
        }

        Node copyNode;
        // Step 3: establish the connections (next and random pointers) of the copied nodes using the map
        for (Node originalNode = head; originalNode != null; originalNode = originalNode.next) {
            copyNode = map.get(originalNode);
            copyNode.next = map.get(originalNode.next);
            copyNode.random = map.get(originalNode.random);
        }

        // Return the head of the copied list from the map
        return map.get(head);
    }

    /*
     *
     *  ================ [Optimize Approach ] Using Hashing - O(n) Time and O(n) Space =====================
     *
     *  Algorithm / Intuition :
     *      1. Insert the copy node in between
     *      2. Connect the random pointer
     *      3. Connect the next pointer, also break the in between relation in the original list
     *
     *
     * Time Complexity  : 2 traverse of given list = O(n) + O(n) = O(2n)
     * Space Complexity : n for cloned list = O(n) = O(n)
     */
    public static Node cloneList(Node head) {

        if (head == null) {
            return null;
        }

        Node copyNode;
        // Create new nodes and keep them in between
        Node curr = head;
        while (curr != null) {
            copyNode = new Node(curr.data);
            copyNode.next = curr.next;
            curr.next = copyNode;
            curr = curr.next.next;
        }

        // Connect the random pointer of new nodes
        curr = head;
        while (curr != null) {
            copyNode = curr.next;
            if (curr.random != null) {
                copyNode.random = curr.random.next;
            }else{
                copyNode.random = null;
            }
            curr = curr.next.next;
        }

        // Separate the new nodes from the original nodes
        curr = head;
        Node clonedHead = head.next;
        Node clone = clonedHead;
        while (clone.next != null) {

            // Update the next nodes of the original node and cloned node
            curr.next = curr.next.next;
            clone.next = clone.next.next;

            // Move pointers of the original and cloned list to their next nodes
            curr = curr.next;
            clone = clone.next;
        }
        curr.next = null;
        clone.next = null;

        return clonedHead;
    }

    // Function to print the cloned linked list
    public static void printClonedLinkedList(Node head) {
        while (head != null) {
            System.out.print("Data: " + head.data);
            if (head.random != null) {
                System.out.print(", Random: " + head.random.data);
            } else {
                System.out.print(", Random: nullptr");
            }
            System.out.println();
            // Move to the next node in the list
            head = head.next;
        }
    }

    static class Node {
        // Data stored in the node
        int data;
        // Pointer to the next node
        Node next;
        // Pointer to a random node in the list
        Node random;

        // Constructors for Node class
        Node() {
            this.data = 0;
            this.next = null;
            this.random = null;
        }

        Node(int x) {
            this.data = x;
            this.next = null;
            this.random = null;
        }

        Node(int x, Node nextNode, Node randomNode) {
            this.data = x;
            this.next = nextNode;
            this.random = randomNode;
        }
    }
}

