package com.nishchay.ds.tree.a01traversal;


import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01basic.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 *  =================================== Iterative Postorder Traversal | Set 1 (Using Two Stacks) =================================
 * Given a binary tree, the task is to find the postorder traversal of the tree without using recursion.
 * Write Iterative code for postorder traversal using stack
 *
 *  Postorder Traversal: Left -> Right -> Root
 *
 *          	        1
 *          	     /     \
 *          	   2        3
 *          	 /  \      /  \
 *          	4    5    6    7
 *
 *        Postorder (Left, Right, Root) - 4, 5, 2, 6, 7, 3, 1
 *
 *        	        1
 *        	     /     \
 *        	   2        3
 *        	 /  \      /
 *        	4    5    6
 *        			   \
 *        				7
 *        				 \
 *        				  8
 *
 *         Postorder (Left, Right, Root) - 4, 5, 2, 8, 7, 6, 3, 1
 *
 *
 * https://www.geeksforgeeks.org/dsa/iterative-postorder-traversal/
 * https://takeuforward.org/plus/dsa/problems/postorder-traversal?tab=editorial&approach=iterative-approach
 *
 * */
public class TreeTraversalPostOrderIterative {

    public static void main(String[] args) {

        // Create binary tree
        //	        1
        //	     /     \
        //	   2        3
        //	 /  \      /  \
        //	4    5    6    7

        // Postorder (Left, Right, Root) - 4, 5, 2, 6, 7, 3, 1

        Node root = TreeUtils.createTree();

        List<Integer> postorder = postorderIterative_2stack(root);
        System.out.println("Postorder Traversal  : " + postorder);

        postorder = postorderIterative_1stack(root);
        System.out.println("Postorder Traversal  : " + postorder);
    }

    /*
     * =========== Iterative Postorder (with 2 stack) - O(n) time and O(n) space ============
     * A key observation is that if we traverse the tree in Root → Right → Left order (a modified preorder), the result will be a reverse of postorder.
     * The idea is to push reverse Postorder traversal to a stack and then reverse the stack at the end; we get the desired postorder sequence.
     *
     *  Stack1  - node to be processed
     *  Stack2  - get the modified preorder (Root, Right, Left) traversal here
     *
     * So, we can do something like iterative preorder traversal with the following differences:
     *      a) Instead of printing an item, we push it to a stack.  - Stack1
     *      b) We push the left subtree before the right subtree.   - Stack2
     *
     * Algorithm:
     * 		1. Push root to first stack.
     * 		2. Loop until first stack is not empty
     * 			- Pop a node from first stack and push it to second stack
     *			- Push left and right children of the popped node to first stack
     *      3.  Print the contents of the second stack
     *
     *  Time complexity: O(n), since the algorithm processes each node exactly twice (once when pushed to s1 and once when popped from s2), where n is the number of nodes.
     *  Auxiliary space: O(n), due to the two stacks, each holding up to n nodes at different points in the traversal.
     * */
    public static List<Integer> postorderIterative_2stack(Node root) {
        ArrayList<Integer> postorder = new ArrayList<>();
        if (root == null) {
            return postorder;
        }

        Stack<Node> stk1 = new Stack<>();
        Stack<Node> stk2 = new Stack<>();
        stk1.push(root);
        Node curr;

        // Run while the first stack is not empty
        while (!stk1.isEmpty()) {

            // Pop from stk1 and push it to stk2
            curr = stk1.pop();
            stk2.push(curr);

            // Push left and right children of the popped node
            if (curr.left != null) {
                stk1.push(curr.left);
            }
            if (curr.right != null) {
                stk1.push(curr.right);
            }
        }

        // Collect all elements from the second stack
        while (!stk2.isEmpty()) {
            curr = stk2.pop();
            postorder.add(curr.data);
        }
        return postorder;
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
     *  Until visit all node
     * while (currNode != null || !stack.isEmpty()) {
     *
     *     while (currNode != null) {
     *         push and go left
     *     }
     *     pop and visit
     *     go right
     * }
     *
     * https://www.geeksforgeeks.org/dsa/iterative-postorder-traversal-using-stack/
     * */
    private static List<Integer> postorderIterative_1stack(Node root) {
        ArrayList<Integer> postorder = new ArrayList<>();
        if (root == null) {
            return postorder;
        }

        Stack<Node> stk = new Stack<>();

        // Step 2.1: Process until root becomes null
        while (root != null || !stk.isEmpty()) {

            // Move to the leftmost node and push right child and root
            while (root != null) {
                if (root.right != null) {
                    stk.push(root.right);
                }
                stk.push(root);
                root = root.left;
            }

            // Step 2.2: Pop an item from the stack
            root = stk.pop();

            // Step 2.2a: If the popped node has a right child
            // and the right child is on the top of the stack
            if (!stk.isEmpty() && root.right != null
                    && stk.peek() == root.right) {
                stk.pop();
                stk.push(root);
                root = root.right;
            } else {

                // Step 2.2b: Else, print the node's
                // data and set root as null
                postorder.add(root.data);
                root = null;
            }
        }

        return postorder;
    }
}
