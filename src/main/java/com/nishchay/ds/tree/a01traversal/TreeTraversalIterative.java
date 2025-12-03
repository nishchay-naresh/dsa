package com.nishchay.ds.tree.a01traversal;


import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01basic.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 *  =================================== Tree Traversal Methods =================================
 *
 *          Explore one branch fully before backtracking.
 * 	            In-Order    : Left -> Root -> Right (retrieves BST elements in sorted order).
 * 	            Pre-Order   : Root -> Left -> Right (used for tree reconstruction).
 *              Post-Order  : Left -> Right -> Root (helps in deleting or evaluating expressions).
 *
 *          Implementation approach:
 *              DFS uses recursion or a stack to traverse deeper levels of the tree before visiting nodes at the same level.
 *
 *
 * https://www.geeksforgeeks.org/dsa/properties-of-binary-tree/
 * https://takeuforward.org/binary-tree/binary-tree-traversal-inorder-preorder-postorder/
 * */
public class TreeTraversalIterative {

    public static void main(String[] args) {

        // Create binary tree
        //	        1
        //	     /     \
        //	   2        3
        //	 /  \      /  \
        //	4    5    6    7

        // inorder NVR      - 4, 2, 5, 1, 6, 3, 7
        // preorder VLR     - 1, 2, 4, 5, 3, 6, 7
        // postorder LRV    - 4, 5, 2, 6, 7, 3, 1

        Node root = TreeUtils.createTree();

        List<Integer> preorder = preorderIterative(root);
        System.out.println("Preorder Traversal  : " + preorder);

        List<Integer> inorder = inorderIterative(root);
        System.out.println("Inorder Traversal   : " + inorder);
        System.out.println("Inorder Traversal   : " + inorderIterative(root));
    }

    /*
     * =========== Iterative Preorder (with stack) - O(n) time and O(n) space ============
     *              Use a stack - Root -> Left -> Right
     * Algorithm :
     * 		Create an empty stack and push root node to stack.
     * 		Do the following while stack is not empty.
     * 			Pop an item from the stack and print it.
     *			// Ideally we should have process the left child first then right
     *			// since we are using stack - LIFO, so left will be process first while we pop from queue
     * 			Push right child of a popped item to stack
     * 			Push left child of a popped item to stack
     *
     * https://takeuforward.org/data-structure/iterative-preorder-traversal-of-binary-tree
     * https://www.geeksforgeeks.org/dsa/iterative-preorder-traversal/
     *
     * */
    public static List<Integer> preorderIterative(Node root) {
        List<Integer> preorder = new ArrayList<>();

        if (root == null) {
            return preorder;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node currNode;
        while (!stack.empty()) {
            currNode = stack.pop();
            preorder.add(currNode.data);

            // Push right first so left is processed first
            if (currNode.right != null) {
                stack.push(currNode.right);
            }
            if (currNode.left != null) {
                stack.push(currNode.left);
            }
        }
        return preorder;
    }

    /*
     * =========== Iterative Inorder (with stack) - O(n) time and O(n) space ============
     *          Inorder : Left -> Root -> Right
     * Because recursion naturally uses a call stack, we simulate recursion using our own Stack<Node>
     *              Use a stack
     * Process:
     * 		Keep going left and push nodes into the stack.
     * 		When there is no left node:
     * 		    Pop from stack -> visit it.
     * 		    After visiting the node, move to its right subtree.
     *      Repeat until all nodes are processed.
     *
     *  until visit all node
     * while (currNode != null || !stack.isEmpty()) {
     *
     *     while (currNode != null) {
     *         push & go left
     *     }
     *
     *     pop & visit
     *     go right
     * }
     *
     * https://takeuforward.org/data-structure/inorder-traversal-of-binary-tree/
     * https://www.geeksforgeeks.org/dsa/inorder-tree-traversal-without-recursion/
     * */
    private static List<Integer> inorderIterative(Node root) {

        List<Integer> inorder = new ArrayList<>();
        if (root == null) {
            return inorder;
        }

        Stack<Node> stack = new Stack<>();
        Node currNode = root;
        while (currNode != null || !stack.isEmpty()) {
            // Go left as far as possible
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }

            currNode = stack.pop();
            inorder.add(currNode.data);
            currNode = currNode.right;
        }

        return inorder;
    }
}
