package com.nishchay.ds.tree.a04height;


import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01basic.TreeUtils;

/*
 *  ==================== Balanced Binary Tree or Not ======================================
 *
 *	Given the root of a binary tree, determine if it is height-balanced.
 *  A binary tree is considered height-balanced
 *       if the absolute difference in heights of the left and right subtrees is at most 1 for every node in the tree.
 *
 *      Balanced Binary Tree = for every node, Math.abs(leftHeight - rightHeight) <= 1
 *
 * Examples:
 *			Input :
 *				      10
 *				     / \
 *				   20   30
 *				   /  \
 *				 40    60
 *          Output: true
 *          Explanation: The height difference between the left and right subtrees at all nodes is at most 1. Hence, the tree is balanced.
 *
 *			Input :
 *				      1
 *				     / \
 *				   2    3
 *				  /
 *				 4
 *              /
 *             5
 *
 *         Output: false
 *         Explanation: The height difference between the left and right subtrees at node 2 is 2, which exceeds 1. Hence, the tree is not balanced.
 *
 *  https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/
 *  https://www.naukri.com/code360/problem-details/flatten-the-multi-level-linked-list_839810
 *  https://leetcode.com/problems/balanced-binary-tree/description/
 */
public class CheckBalancedBT {

    public static void main(String[] args) {
        positiveUseCase();
        negativeUseCase();
    }

    private static void positiveUseCase() {
        // Representation of input BST:
        //            10
        //           / \
        //          20  30
        //         /  \
        //       40    60
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.right = new Node(60);

        System.out.println(isBalanced2Pass(root) ? "true" : "false");
        System.out.println(isBalanced(root) ? "true" : "false");
    }

    private static void negativeUseCase() {
        Node root;

        // Representation of input BST:
        //
        //				      1
        //				     / \
        //				   2    3
        //				  /
        //				 4
        //             /
        //            5

        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.left.left = new Node(5);


        System.out.println(isBalanced2Pass(root) ? "true" : "false");
        System.out.println(isBalanced(root) ? "true" : "false");

        // Representation of input BST:
        //
        //				      1
        //				     / \
        //				   2    3
        //               /  \
        //              4    5
        //                    \
        //                     6
        //                      \
        //                       7

        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(6);
        root.left.right.right.right = new Node(7);

        System.out.println(isBalanced2Pass(root) ? "true" : "false");
        System.out.println(isBalanced(root) ? "true" : "false");
    }

    /*
     *  ================ [Naive/Bruteforce Approach] Recursive Algorithm - two traversal - O(n^2) Time and O(1) Space =====================
     *
     * Algorithm:
     * Step 1: Check if the root is null. If so, return true as an empty tree is balanced.
     * Step 2: calculate the height of the left and right subtrees and compare them. If their absolute height difference is greater than 1, return false.
     * Step 3: If their absolute height difference is less than or equal to 1, recursively call the same method for left and right children as well.
     * Step 4: If any of the conditions in Step 2 and Step 3 fail, return false.
     *
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     * Here we are visiting the tree 2 times, 1 to check isBalanced(), 2nd to find the height
     * optimizing this, while finding the height checking the tree for height-balanced as well
     * */
    private static boolean isBalanced2Pass(Node root) {
        if (root == null) {
            return true;
        }

        int leftHeight = TreeUtils.height(root.left);
        int rightHeight = TreeUtils.height(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1)
            return false;

        // Recursively check the left and right subtrees
        return isBalanced(root.left) && isBalanced(root.right);
    }


    /*
     *  ================ [Optimize/Expected Approach] Recursive Algorithm - single traversal - O(n) Time and O(h) Space  =====================
     *  Here we optimizing the previous solution, while finding the height checking the tree for height-balanced as well
     *
     * While finding the height, we will modify it like
     *      - height() function will return height if it is a height-balanced tree, else -1;
     *
     *  now the isBalanced() need to check that the returned height shd be >= 1
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */
    private static boolean isBalanced(Node root) {
        return getHeightIfBalanced(root) >= 0;
    }

    /*
     * Function that returns
     *      - the height of the tree if the tree is balanced
     *      - Otherwise it returns -1
     * */
    private static int getHeightIfBalanced(Node root) {
        if (root == null)
            return 0;

        int leftHeight = getHeightIfBalanced(root.left);
        if (leftHeight == -1)
            return -1;
        int rightHeight = getHeightIfBalanced(root.right);
        if (rightHeight == -1)
            return -1;
        // If the absolute difference of their heights is greater than 1
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }

}
