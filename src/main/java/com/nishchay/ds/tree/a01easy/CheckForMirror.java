package com.nishchay.ds.tree.a01easy;

import com.nishchay.ds.tree.Node;

/*
 *  ========================= Check if two trees are Mirror =============================
 *
 * Given two Binary Trees, the task is to check if two trees are mirror of each other or not.
 * For two trees ‘a’ and ‘b’ to be mirror images, the following three conditions must be true:
 *  -   Their root node’s key must be the same
 *  -   Left subtree of ‘a’ and right subtree of ‘b’ are mirror.
 *  -   Right subtree of ‘a’ and left subtree of ‘b’ are mirror.
 *
 *
 * https://www.geeksforgeeks.org/dsa/check-if-two-trees-are-mirror/
 */
public class CheckForMirror {

    public static void main(String[] args) {

        // Representation of input binary tree 1
        //        1
        //       / \
        //      3   2
        //          / \
        //         5   4
        Node root1 = new Node(1);
        root1.left = new Node(3);
        root1.right = new Node(2);
        root1.right.left = new Node(5);
        root1.right.right = new Node(4);

        // Representation of input binary tree 2 (mirror)
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.left.right = new Node(5);

        if (areMirrors(root1, root2)) {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }

    /*
     *  ================ [Expected Approach] Recursive Approach - O(n) Time and O(h) Space =====================
     *
     *  The idea is to check if two binary trees are mirrors of each other by comparing their structure and node values.
     *  We recursively verify if the root nodes of both trees have the same value,
     *  then check if the left subtree is a mirror of the right subtree.
     *  If both trees are empty, they are considered mirrors;
     *  if one is empty and the other is not, they are not mirrors.
     *  This approach ensures that the trees are symmetric with respect to their root.
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(h)
     * Function to check if two roots are mirror images
     * */
    static boolean areMirrors(Node root1, Node root2) {

        // If both roots are empty, they are mirrors
        if (root1 == null && root2 == null) {
            return true;
        }

        // If only one root is empty, they are not mirrors
        if (root1 == null || root2 == null) {
            return false;
        }

        // Check if the subtrees are mirrors
        return (root1.data == root2.data) &&
                areMirrors(root1.left, root2.right) &&
                areMirrors(root1.right, root2.left);
    }
}
