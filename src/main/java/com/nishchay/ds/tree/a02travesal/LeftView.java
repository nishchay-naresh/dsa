package com.nishchay.ds.tree.a02travesal;

import com.nishchay.ds.tree.Node;

import java.util.*;

/*
 * ==================== Left View of a Binary Tree ======================================
 *
 * Given the root of a binary tree, find the top view of the tree.
 * The top view of a binary tree represents the set of nodes visible when the tree is viewed from above.
 *
 * Examples:
 *			Input :
 *				       1
 *				      / \
 *				     2   3
 *				    / \   \
 *				   4   5   6
 *				          /
 *				         7
 *
 *          Output: Output: [1, 2, 4, 7]
 *          Explanation: From the left side of the tree, only the nodes 1, 2, 4 and 7 are visible.
 *
 *
 * https://www.geeksforgeeks.org/dsa/print-left-view-binary-tree/
 * https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
 *
 */
public class LeftView {

    public static void main(String[] args) {

        // Create binary tree
        //    1
        //   / \
        //  2   3
        //     /
        //    4
        //     \
        //      5

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.left.right = new Node(5);

        ArrayList<Integer> lView = leftView(root);
        System.out.println("LeftView = " + lView);   // 1, 2, 4, 5
    }

    /*
     *  ================ [Approach - 1] Using Depth-first search (DFS) - O(n) Time and O(n) Space =====================
     *
     *  The idea is to use DFS and Keep track of current level. For every level of the binary tree, the first node we see from the left side is part of the left view.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     *
     * Function which return left view of binary tree
     * */
    private static ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        recLeftView(root, 0, result);
        return result;
    }

    // Recursive function to find left view
    private static void recLeftView(Node root, int level, ArrayList<Integer> result) {
        if (root == null) return;

        // first node of current level
        if (level == result.size()) {
            result.add(root.data);
        }

        recLeftView(root.left, level + 1, result);
        recLeftView(root.right, level + 1, result);
    }
}
