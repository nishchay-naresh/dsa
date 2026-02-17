package com.nishchay.ds.tree.a02travesal;

import com.nishchay.ds.tree.Node;

import java.util.ArrayList;

/*
 * ==================== Right View of a Binary Tree ======================================
 *
 * Given the root of a binary tree, find the top view of the tree.
 * The top view of a binary tree represents the set of nodes visible when the tree is viewed from above.
 *
 * Examples:
 *			Input :
 *				       1
 *				      / \
 *				     2   3
 *				    /
 *				   4
 *				     \
 *				      5
 *
 *          Output: Output:  [1, 3, 4, 5]
 *          Explanation: The Green colored nodes (1, 3, 4, 5) represents the Right view in the below Binary tree.
 *
 *
 * https://www.geeksforgeeks.org/dsa/print-right-view-binary-tree-2/
 * https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
 *
 */

public class RightView {

    public static void main(String[] args) {

        // Representation of the input tree:
        //         1
        //        / \
        //       2   3
        //           / \
        //          4   5
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        ArrayList<Integer> result = rightView(root);
        System.out.println("RightView = " + result);   // 1 3 5
    }

    /*
     *  ================ [Approach - 1] Using Depth-first search (DFS) - O(n) Time and O(n) Space =====================
     *  Right view
     *  Preorder traversal - Root -> Left -> Right
     *  Reverse Preorder traversal - Root -> Right -> Left, bcus interested in Right view
     *
     *  The idea is to use DFS and Keep track of current level. For every level of the binary tree, the first node we see from the left side is part of the right view.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     *
     * Function which return right view of binary tree
     * */
    private static ArrayList<Integer> rightView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        recRightView(root, 0, result);
        return result;
    }

    // Recursive function to find left view
    private static void recRightView(Node root, int level, ArrayList<Integer> result) {
        if (root == null) return;

        // first node of current level
        if (level == result.size()) {
            result.add(root.data);
        }

        recRightView(root.right, level + 1, result);
        recRightView(root.left, level + 1, result);
    }
}
