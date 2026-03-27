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
     * =========== Iterative Postorder (with 1 stack) - O(n) time and O(n) space ============
     * =========== [Expected Approach 3] Tracking Last Visited Node - O(n) Time and O(n) Space ===========
     *          Inorder : Left  -> Right -> Root
     *
     * It first pushes nodes onto the stack as it moves down the left side of the tree.
     * Once it reaches a leaf, it starts processing the nodes by checking if the right child has been visited.
     * If not, it traverses the right subtree; otherwise, it processes the node and adds it to the result.
     *
     * https://www.geeksforgeeks.org/dsa/iterative-postorder-traversal-using-stack/
     * */
    private static List<Integer> postorderIterative_1stack(Node root) {
        ArrayList<Integer> postorder = new ArrayList<>();
        if (root == null) {
            return postorder;
        }

        Stack<Node> stk = new Stack<>();

        Node lastVisited = null;
        Node curr = root;
        while (!stk.isEmpty() || curr != null) {
            if (curr != null) {
                stk.push(curr);
                curr = curr.left;
            }
            else {
                Node peekNode = stk.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    // visit the top node
                    postorder.add(peekNode.data);
                    lastVisited = stk.pop();
                }
            }
        }
        return postorder;
    }
}
