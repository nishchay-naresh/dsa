package com.nishchay.ds.tree.a03search;

import com.nishchay.ds.tree.Node;

/*
 *  ========================= Find the parent of a node in the given binary tree =============================
 *	Given a Binary Tree and a node, the task is to find the parent of the given node in the tree.
 *  Return -1 if the given node is the root node.
 *
 *  Note: In a binary tree, a parent node of a given node is the node that is directly connected above the given node.
 *
 * Examples:
 *			Input : key = 3, root =
 *
 *					        1
 *					     /     \
 *					   7        3
 *					 /  \         \
 *					4    5         6
 *
 *			Output: 1
 *          Explanation: Parent of the target node i.e. 3 is 1
 *
 *
 *  https://www.geeksforgeeks.org/dsa/find-the-parent-of-a-node-in-the-given-binary-tree/
 *
 */
public class SearchParent {


    public static void main(String[] args) {

        // Representation of the given tree
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        int key = 3;

        int parent = findParent(root, key, -1);
        System.out.println(parent);

        System.out.println("findParent(root, 3, -1) - " + findParent(root, 3, -1));
        System.out.println("findParent(root, 9, -1) - " + findParent(root, 9, -1));
    }


    /*
     * Recursive function to find the parent key node
     *
     *
     * Time Complexity: O(n), where n is the number of nodes in the binary tree.
     * Auxiliary Space: O(h), where h is height of binary tree.
     * */
    private  static int findParent(Node root, int key, int parent) {

        if (root == null) {
            return -1;
        }

        // If current node is the key, return its parent
        if (root.data == key) {
            return parent;
        }

        int leftSearch = findParent(root.left, key, root.data);
        if (leftSearch != -1) {
            return leftSearch;
        }

        // Recursively search in right subtree
        return findParent(root.right, key, root.data);
    }
}
