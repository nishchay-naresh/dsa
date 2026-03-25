package com.nishchay.ds.tree.a03search;

import com.nishchay.ds.tree.Node;

import java.util.Stack;

/*
 *  ========================= All Leaves of a Binary Tree - Print in Order =============================
 *	Given a binary tree, we need to print all leaf nodes of the given binary tree from left to right.
 *  That is, the nodes should be printed in the order they appear from left to right in the given tree.
 *
 *
 * Examples:
 *			Input : root =
 *			        1
 *			     /     \
 *			   2        3
 *			 /        /  \
 *			4        5    8
 *         			/  \  /  \
 *        		   6   7  9  10
 *
 *			Output : 4 6 7 9 10
 *          Corner Cases :  For a tree with single node, the output should be the single node. And if root is null (empty tree), the output should be empty.
 *
 *
 *  https://www.geeksforgeeks.org/dsa/print-leaf-nodes-left-right-binary-tree/
 *
 */
public class GetLeafNodeIterative {

    public static void main(String[] args) {

        //		        1
        //		     /     \
        //		   2        3
        //		 /        /  \
        //		4        5    8
        //	    		/  \  /  \
        //	    	   6   7  9  10

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(8);
        root.right.left.left = new Node(6);
        root.right.left.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);

        System.out.print("\nprintLeafNodes(root) - ");
        printLeafNodes(root);                              // 4 6 7 9 10

        System.out.print("\nprintLeafNodesIterative(root) - ");
        printLeafNodesIterative(root);                     // 4 6 7 9 10
    }


    /*
     * Function to print leaf nodes from left to right
     *
     * Using DFS :
     * 	The idea to do this is similar to DFS algorithm.
     *	Below is a step by step algorithm to do this:
     *		Check if the given node is null. If null, then return from the function.
     *		Check if it is a leaf node. If the node is a leaf node, then print its data.
     *		If in the above step,
     *          If left child exists, check for leaf recursively
     *          If right child exists, check for leaf recursively
     *
     * Time Complexity: O(n), where n is the number of nodes in the binary tree.
     * Auxiliary Space: O(h), where h is height of binary tree.
     *
     * */

    private static void printLeafNodes(Node root) {

        if (root == null)
            return;

        // If node is leaf node, print its data
        if (root.left == null && root.right == null) {
            System.out.print(root.data + " ");
            return;
        }

        if (root.left != null)
            printLeafNodes(root.left);

        if (root.right != null)
            printLeafNodes(root.right);
    }

    /*
     *  Function to print leaf nodes from left to right
     *  Implement iterative preorder traversal
     *
     * Algo:
     * 	Create an empty stack 'stack' and push the root node to stack.
     * 	Do the following while stack is not empty.
     * 		Pop an item from the stack
     * 		If the node is a leaf node then print it.
     * 		Else:
     * 			If the right node is not NULL
     * 				push the right node into the stack
     * 			If the left node is not NULL
     * 				push the left node into the stack
     *
     * Time Complexity: O(n), where n is the number of nodes in the binary tree.
     * Auxiliary Space: O(n)
     *
     * */

    static void printLeafNodesIterative(Node root) {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();

            if (root.left == null && root.right == null)
                System.out.print(root.data + " ");

            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
    }

}
