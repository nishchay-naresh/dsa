package com.nishchay.ds.tree.a01traversal;


import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01basic.TreeUtils;

import java.util.ArrayList;

/*
 *  =================================== Morris traversal for Inorder =================================
 *
 * Given the root of a binary tree, Find its inorder traversal using Morris Traversal, i.e., without using recursion or a stack.
 *
 * Examples:
 *			Input :
 *					        1
 *					     /     \
 *					   2        3
 *					 /  \
 *					4    5
 *
 *
 *          Output: [4, 2, 5, 1, 3]
 *			Explanation: Inorder traversal (Left->Root->Right) of the tree is 4, 2, 5, 1, 3.
 *
 *			Input :
 *						        8
 *						       / \
 *						      1   5
 *						     /   / \
 *						    7   10  6
 *						   /     \
 *						 10       6
 *
 *           Output: [1, 7, 10, 8, 6, 10, 5, 6]
 *           Explanation: Inorder traversal (Left->Root->Right) of the tree is 1, 7, 10, 8, 6, 10, 5, 6.
 *
 * https://www.geeksforgeeks.org/dsa/inorder-tree-traversal-without-recursion-and-without-stack/
 *
 * */
public class MorrisTraversal {

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

        System.out.print("Inorder   : ");
        TreeTraversalRecursive.inorder(root);

        System.out.print("\nMorris    : " +  morris(root));


        System.out.println("\n...............................");
        // Representation of input binary tree:
        //           1
        //          / \
        //         2   3
        //        / \
        //       4   5
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.print("Inorder   : ");
        TreeTraversalRecursive.inorder(root);

        System.out.print("\nMorris    : " +  morris(root));

    }

    /*
     *  ================ Standard algorithm =====================
     *
     * Approach:
     * We want to traverse the tree and come back to the root after finishing the left subtree,
     * but without a stack. Morris Traversal achieves this by temporarily modifying the tree:
     *
     * 	-	For each node, check if it has a left child.
     * 	-	If it does not have a left child, visit it and move to the right child.
     * 	-	If it has a left child, find the inorder predecessor (rightmost node in the left subtree).
     * 			Make the current node as the right child of its inorder predecessor (temporary link).
     * 			Move to the left child.
     * 	-	When you encounter a temporary link again, it means the left subtree is fully visited:
     * 			Remove the temporary link.
     * 			Visit the current node.
     * 			Move to the right child.
     *
     *
     * Algorithm :
     *
     *	curr = root
     *	while (curr != null)
     *	    if (curr->left == null)
     *	        // print curr
     *	        curr = curr->right
     *	    else
     *	        find Inorder Predecessor (IP)
     *	        if (IP->right == null)
     *	            IP->right = curr // create thread
     *	            curr = curr->left
     *	        else
     *	            IP->right = null // delete thread
     *	            // print curr
     *	            curr = curr->right
     *
     * Time Complexity: O(n), if we take a closer look, we can notice that every edge of the tree is traversed at most three times.
     * Auxiliary Space: O(1)
     * */
    private static ArrayList<Integer> morris(Node root) {
        ArrayList<Integer> traversal = new ArrayList<>();
        Node curr = root;

        while (curr != null) {
            if (curr.left == null) {

                // If no left child, visit this node and go right
                traversal.add(curr.data);
                curr = curr.right;
            } else {

                // Find the inorder predecessor of curr
                Node prev = curr.left;
                while (prev.right != null &&
                        prev.right != curr) {
                    prev = prev.right;
                }

                // Make curr the right child of its inorder predecessor
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    // Revert the changes made in the tree structure
                    prev.right = null;
                    traversal.add(curr.data);
                    curr = curr.right;
                }
            }
        }
        return traversal;
    }

}
