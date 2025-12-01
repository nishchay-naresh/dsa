package com.nishchay.ds.tree.a01basic;


import com.nishchay.ds.tree.Node;

public class TreeUtils {


    /*
     *
     * Creating below tree
     *
     *			        1
     *			     /     \
     *			   2        3
     *			 /  \      /  \
     *			4    5    6    7
     *
     * */
    public static Node createTree() {
        Node root;
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        return root;
    }

    /*
     *
     * Creating below tree
     *
     *			        1
     *			     /     \
     *			   2        3
     *			 /  \      /  \
     *			4    5    6    7
     *         /  \  /  \
     *        8   9  10  11
     *
     *
     * */
    public static Node createTree4() {
        Node root;
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);

        return root;
    }

    /*
     * ==================== Maximum Depth or Height of a Binary Tree ======================
     * Given the root of a binary tree, find the maximum depth of the tree.
     * The maximum depth or height of the tree is the number of edges in the tree from the root to the deepest node.
     *
     * Below code used recursion and backtracking to solve this problem
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     *
     * https://www.geeksforgeeks.org/dsa/find-the-maximum-depth-or-height-of-a-tree/
     * */
    public static int height(Node root) {
        if (root == null)
            return 0;

        int lh = height(root.left);
        int rh = height(root.right);
        return 1 + Math.max(lh, rh);
    }
}
