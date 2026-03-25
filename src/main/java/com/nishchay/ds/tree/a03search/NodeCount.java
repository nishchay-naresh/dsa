package com.nishchay.ds.tree.a03search;

import com.nishchay.ds.tree.Node;

public class NodeCount {


    public static void main(String[] args) {

        // Building the sample tree:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4  5  6  7

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("Total Nodes     : " + nodeCount(root));
        System.out.println("Leaf Nodes      : " + nodeDegree0(root));
        System.out.println("Half Nodes      : " + nodeDegree1(root));
        System.out.println("Full Nodes      : " + nodeDegree2(root));
    }

    /*
     * Give an algorithm for finding the size of binary tree.
     * Returns the total number of nodes in this binary tree (include the root in the count).
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     * */
    private static int nodeCount(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    /*
     * Give an algorithm for finding total number of degree 2 nodes (full node)
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     * */
    private static int nodeDegree2(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.right != null) {
            return 1;
        }

        return nodeDegree2(root.left) + nodeDegree2(root.right);
    }

    /*
     *Give an algorithm for finding total number of degree 1 nodes (half node)
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     * */
    private static int nodeDegree1(Node root) {
        if (root == null) {
            return 0;
        }
        if ((root.left != null && root.right == null) || (root.left == null && root.right != null)) {
            return 1;
        }
        return nodeDegree1(root.left) + nodeDegree1(root.right);
    }

    /*
     * Give an algorithm for finding total number of degree 0 nodes (leave node)
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     * */
    private static int nodeDegree0(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return nodeDegree0(root.left) + nodeDegree0(root.right);
    }
}
