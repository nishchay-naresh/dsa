package com.nishchay.ds.tree.a01easy;

import com.nishchay.ds.tree.Node;

/*
 *  ========================= Lowest Common Ancestor in a Binary Tree =============================
 *
 * Given the root of a Binary Tree with unique values and two node values n1 and n2, find the Lowest Common Ancestor (LCA).
 * LCA is the deepest node that has both n1 and n2 as descendants.
 *
 * Note: Both node values are always present in the Binary Tree.
 *
 * Examples:
 *			Input : n1=4,n2=5
 *			        1
 *			     /     \
 *			   2        3
 *			 /  \      /  \
 *			4    5    6    7
 *
 *          Output: 2
 *          Explanation: As shown below, LCA of 4 and 5 is 2.
 *
 *
 * https://takeuforward.org/data-structure/lowest-common-ancestor-for-two-given-nodes/
 * https://www.geeksforgeeks.org/dsa/lowest-common-ancestor-binary-tree-set-1/
 */
public class LCA {

    public static void main(String[] args) {

        // Creating a sample symmetric binary tree
        //             3
        //           /   \
        //          5     1
        //         / \   / \
        //        6  2  0   8

        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        Node n1 = root.left;       // Node with value 5
        Node n2 = root.right;      // Node with value 1

        Node lca = lca(root, n1, n2);
        System.out.println("Lowest Common Ancestor: " + lca.data);


        // construct the binary tree
        //             1
        //           /   \
        //          2     3
        //         / \   / \
        //        4  5  6   7
        //             /
        //            8
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(8);

        n1 = root.right.right;     // 7
        n2 = root.right.left.left; // 8
        lca = lca(root, n1, n2);
        System.out.println("lca " + - lca.data);   // 3

        n1 = root.left.right;           // 5
        n2 = root.right.left.left;      // 8
        lca = lca(root, n1, n2);
        System.out.println("lca " + - lca.data);  // 1
    }

    /*
     *  ================ [Expected Approach] Using Single Traversal - O(n) Time and O(h) Space =====================
     *
     *  The idea is to traverse the tree starting from the root.
     *  If any of the given keys (n1 and n2) matches with the root,
     *  then the root is LCA (assuming that both keys are present).
     *  If the root doesn't match with any of the keys, we go for the left and right subtree.
     *  The node which has one key present in its left subtree and the other key present in the right subtree is the LCA,
     *  else if, both keys lie in the left subtree, then the left subtree has LCA, else the LCA lies in the right subtree.
     *
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(h), where h is height of binary tree due to recursive stack space
     * */
    private static Node lca(Node root, Node n1, Node n2) {

        if (root == null || root == n1 || root == n2)
            return root;

        Node leftLca = lca(root.left, n1, n2);
        Node rightLca = lca(root.right, n1, n2);

        // Result
        if (leftLca == null)
            return rightLca;
        if (rightLca == null)
            return leftLca;
        return root; // Both sides returned non-null, this is the LCA
    }

}
