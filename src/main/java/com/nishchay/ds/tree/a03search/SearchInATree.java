package com.nishchay.ds.tree.a03search;


import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01basic.TreeUtils;

import java.util.Stack;

/*
 *  ========================= Search a node in Binary Tree =============================
 *	Given a Binary tree and a key. The task is to search and check if the given key exists in the binary tree or not.
 *
 * Examples:
 *			Input : key = 3, root =
 *
 *				      5
 *				     / \
 *				   12   13
 *				   /  \    \
 *				  7    14   2
 *				 / \  /  \  / \
 *				17 23 27 3  8  11
 *
 *			Output: True
 *
 *			Input: key = 40, root =
 *					        0
 *					     /     \
 *					   1        2
 *					 /  \      /  \
 *					3    4    5    6
 *				  /     /  \
 *				 7     8    9
 *
 *			Output: False
 *
 *  https://www.geeksforgeeks.org/dsa/get-level-of-a-node-in-a-binary-tree/
 *
 *  ========================= Searching in Binary Search Tree (BST) =============================
 *
 *	Given the root of a Binary Search Tree and a value key, find if key is present in the BST or not.
 *  Note: The key may or may not be present in the BST.
 *
 * Examples:
 *			Input : key = 7, root =
 *				      6
 *				     / \
 *				    2   8
 *				       /  \
 *				      7    9
 *          Output: true
 *          Explanation: 7 is present in the BST.
 *
 *			Input: key = 14, root =
 *				      16
 *				     / \
 *				   12   18
 *				   /   /  \
 *				  10  17   19
 *
 *          Output: false
 *          Explanation: 14 is not present in the BST.
 *
 *  https://www.geeksforgeeks.org/dsa/get-level-of-a-node-in-a-binary-tree/
 *
 *  ========================= Level of a Node in Binary Tree =============================
 *
 *	Given a Binary Tree and a key, the task is to find the level of key in the Binary Tree.
 *
 * Examples:
 *			Input : key = 7, root =
 *				      6
 *				     / \
 *				    2   8
 *				       /  \
 *				      7    9
 *          Output: 3
 *          Explanation: The level of the key in above binary tree is 3.
 *
 *			Input: key = 10, root =
 *				      6
 *				     / \
 *				    2   8
 *				       /  \
 *				      7    9
 *          Output: -1
 *          Explanation: The level of the key in the above binary tree is 3.
 *
 * https://www.geeksforgeeks.org/dsa/get-level-of-a-node-in-a-binary-tree/
 *
 */
public class SearchInATree {

    public static void main(String[] args) {
        binaryTreeSearchEx();
        System.out.println("------------------------------------");
        bstSearchEx();
        System.out.println("------------------------------------");
        getLevelEx();
    }


    private static void binaryTreeSearchEx() {
        // Binary tree
        //          0
        //        /  \
        //       1    2
        //      / \   / \
        //     3   4 5   6
        //    /   / \
        //   7   8   9
        Node root = new Node(0);
        root.left = new Node(1);
        root.left.left = new Node(3);
        root.left.left.left = new Node(7);
        root.left.right = new Node(4);
        root.left.right.left = new Node(8);
        root.left.right.right = new Node(9);
        root.right = new Node(2);
        root.right.left = new Node(5);
        root.right.right = new Node(6);

        System.out.println("ifNodeExists(root, 4) - " + treeSearch(root, 4));
        System.out.println("ifNodeExists(root, 15) - " + treeSearch(root, 15));

        //	        1
        //	     /     \
        //	   2        3
        //	 /  \      /  \
        //	4    5    6    7
        root = TreeUtils.createTree();
        System.out.println("treeSearchIterative(root, 5) - " + treeSearchIterative(root, 5));
        System.out.println("treeSearchIterative(root, 9) - " + treeSearchIterative(root, 9));
    }

    private static void bstSearchEx() {

        // Creating BST
        //    6
        //   / \
        //  2   8
        //     / \
        //    7   9

        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        System.out.println("bstSearch(root, 7) - " + bstSearch(root, 7));
        System.out.println("bstSearch(root, 15) - " + bstSearch(root, 15));
    }

    private static void getLevelEx() {

        // Creating BST
        //    6
        //   / \
        //  2   8
        //     / \
        //    7   9

        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        System.out.println("getLevel(root, 7) - " + getLevel(root, 7, 1));
        System.out.println("getLevel(root, 15) - " + getLevel(root, 15, 1));

        // Creating a sample binary tree:
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("getLevel(root, 7) - " + getLevel(root, 5, 1));
        System.out.println("getLevel(root, 15) - " + getLevel(root, 9, 1));
    }

    /*
     * Function to traverse the tree in preorder and check if the given node exists in it
     *
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Auxiliary Space: O(h), where h is the height of the tree.
     * */
    private static boolean treeSearch(Node root, int key) {
        if (root == null)
            return false;

        if (root.data == key)
            return true;

        boolean foundInLeftSubtree = treeSearch(root.left, key);
        // node found, no need to look further
        if (foundInLeftSubtree)
            return true;

        return treeSearch(root.right, key);
    }

    /*
     * Iterative function to searching an element in binary tree
     * Implement iterative preorder traversal
     *
     * Algo :
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

    private static boolean treeSearchIterative(Node root, int key) {
        if (root == null) {
            return false;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node currNode;
        while (!stack.empty()) {
            currNode = stack.pop();
            if (key == currNode.data) {
                return true;
            }
            // Push right first so left is processed first
            if (currNode.right != null) {
                stack.push(currNode.right);
            }
            if (currNode.left != null) {
                stack.push(currNode.left);
            }
        }
        return false;
    }

    /*
     * We will follow the properties of BST:
     *   - left subtree will smaller than the root value
     *   - right subtree will greater than the root value
     *
     * BST Search algo:
     * We compare the value to be searched with the value of the root.
     * 	    If it's equal we are done with the search.
     * 	    If it's smaller we know that we need to go to the left subtree.
     * 	    If it's greater we search in the right subtree.
     * Repeat the above step till no more traversal is possible
     * If at any iteration,
     *	    key is found, return True
     *	    If the node is null, return False
     *
     * Time complexity: O(h), where h is the height of the BST.
     * Auxiliary Space: O(h) This is because of the space needed to store the recursion stack.
     * */
    private static boolean bstSearch(Node root, int key) {

        if (root == null)
            return false;

        if (root.data == key)
            return true;

        if (key < root.data)
            return bstSearch(root.left, key);

        return bstSearch(root.right, key);
    }


    /*
     * Recursive function to find the level of the target key
     *
     *
     * Time Complexity: O(n), where n is the number of nodes in the binary tree.
     * Auxiliary Space: O(h), where h is height of binary tree.
     * */
    private static int getLevel(Node root, int target, int level) {
        if (root == null) {
            return -1;
        }

        if (root.data == target) {
            return level;
        }

        // Recursively call for left and right subtrees
        int leftLevel = getLevel(root.left, target, level + 1);
        if (leftLevel != -1) {
            return leftLevel;
        }

        return getLevel(root.right, target, level + 1);
    }


}
