package com.nishchay.ds.tree.a01easy;

import com.nishchay.ds.tree.Node;

/*
 *  ========================= Check if two trees are Mirror =============================
 *
 * Given the root of a binary tree, determine whether it is symmetric around root, i.e., check if the binary tree is a mirror image of itself.
 *
 *
 * https://www.geeksforgeeks.org/dsa/symmetric-tree-tree-which-is-mirror-image-of-itself/
 */
public class SymmetricTree {

    public static void main(String[] args) {

        // Creating a sample symmetric binary tree
        //       10
        //       / \
        //      5   5
        //     /     \
        //    2       2
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(5);
        root.left.left = new Node(2);
        root.right.right = new Node(2);

        if (isSymmetric(root))
            System.out.println("true");
        else
            System.out.println("false");
    }

    /*
     *  ================ [Expected Approach] Recursive Approach - O(n) Time and O(h) Space =====================
     *
     *  The idea is to recursively compare the left and right subtrees of the root. For the tree to be symmetric,
     *  the root values of the left and right subtrees must match, and their corresponding children must also be mirrors.
     *
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(h), where h is height of binary tree due to recursive stack space
     *
     * */

    private static boolean isSymmetric(Node root) {
        if (root == null)
            return true;

        return isMirror(root.left, root.right);
    }

    // Recursive helper function to check two subtrees are mirror image
    private static boolean isMirror(Node root1, Node root2) {

        if (root1 == null && root2 == null)
            return true;

        // One of them is null, so they aren't mirror images
        if (root1 == null || root2 == null || root1.data != root2.data)
            return false;

        // Check if the subtrees are mirrors
        return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }


}
