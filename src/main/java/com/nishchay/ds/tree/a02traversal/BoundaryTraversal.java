package com.nishchay.ds.tree.a02traversal;


import com.nishchay.ds.tree.Node;

import java.util.ArrayList;
import java.util.List;

/*
 *  ========================= Boundary Traversal of binary tree =============================
 *
 * Given the root of a binary tree, Find its boundary traversal in anti-clockwise order, starting from the root.
 *
 * The boundary of a binary tree consists of the following parts:
 *      Left Boundary: The nodes on the left edge of the tree, excluding the leaf nodes.
 *      Leaf Nodes: All the leaf nodes from left to right.
 *      Right Boundary: The nodes on the right edge of the tree, excluding the leaf nodes, traversed in bottom-up order.
 *
 * Note:
 *  If the root does not have a left subtree or right subtree, then the root itself is considered part of the respective boundary.
 *  Each node in the boundary should appear only once in the traversal.
 *
 * Examples:
 *			Input :
 *			        1
 *			     /     \
 *			   2        3
 *			 /  \      /  \
 *			4    5    6    7
 *         	   /  \
 *            8    9
 *
 *			Output : [1, 2, 4, 8, 9, 6, 7, 3]
 *			Explanation:  	Start with the root
 *                              leftBoundary    : [1, 2]
 *                              leafNodes       : [4, 8, 9, 6, 7]
 *                              rightBoundary   : [3]
 *
 * https://www.geeksforgeeks.org/dsa/boundary-traversal-of-binary-tree/
 */
public class BoundaryTraversal {

    public static void main(String[] args) {
        ex1();
    }

    private static void ex1() {

        // Input Binary tree
        //            1
        //         /     \
        //       2         3
        //     /   \     /   \
        //    4     5   6     7
        //         / \
        //        8   9

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.right.left = new Node(8);
        root.left.right.right = new Node(9);

        List<Integer> boundary = boundaryTraversal(root);
        System.out.println("Boundary Traversal = " + boundary);
    }


    /*
     *  ================ [Naive/Bruteforce Approach] Using Recursion - O(n) Time and O(h) Space =====================
     *
     * Algorithm:
     *	The boundary traversal of a binary tree is done in three steps.
     *		1. traverse the left boundary
     *			starting from the traversal’s left child and moving downward, excluding any leaf nodes.
     *
     *		2.	traverse the leaf nodes
     *			we collect all the leaf nodes of the tree from left to right using recursion.
     *
     *		3.	traverse the right boundary
     *			starting from the traversal’s right child and moving downward, again excluding leaf nodes
     *			but the collected nodes are added in reverse order
     *
     *	By combining the left boundary, leaf nodes, and right boundary, we obtain the complete anti-clockwise boundary traversal of the tree.
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(h)
     *
     * */
    // Function to find Boundary Traversal of Binary Tree
    private static ArrayList<Integer> boundaryTraversal(Node traversal) {
        ArrayList<Integer> res = new ArrayList<>();

        if (traversal == null)
            return res;

        // Add traversal data if it's not a leaf
        if (!isLeaf(traversal))
            res.add(traversal.data);

        // Collect left boundary
        collectLeft(traversal.left, res);

        // Collect leaf nodes
        collectLeaves(traversal, res);

        // Collect right boundary
        collectRight(traversal.right, res);

        return res;
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    // Function to collect left boundary nodes (top-down order)
    private static void collectLeft(Node root, ArrayList<Integer> leftBoundary) {

        // exclude leaf node
        if (root == null || isLeaf(root))
            return;

        leftBoundary.add(root.data);
        if (root.left != null)
            collectLeft(root.left, leftBoundary);

        else if (root.right != null)
            collectLeft(root.right, leftBoundary);
    }

    // Function to collect all leaf nodes
    private static void collectLeaves(Node root, ArrayList<Integer> leafNodes) {
        if (root == null)
            return;

        // Add leaf nodes
        if (isLeaf(root)) {
            leafNodes.add(root.data);
            return;
        }

        collectLeaves(root.left, leafNodes);
        collectLeaves(root.right, leafNodes);
    }

    // Function to collect right boundary nodes (bottom-up order)
    private static void collectRight(Node root, ArrayList<Integer> rightBoundary) {

        // exclude leaf nodes
        if (root == null || isLeaf(root))
            return;

        if (root.right != null)
            collectRight(root.right, rightBoundary);

        else if (root.left != null)
            collectRight(root.left, rightBoundary);

        rightBoundary.add(root.data);
    }

}
