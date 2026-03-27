package com.nishchay.ds.tree.a06hard;

import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01traversal.BFSTraversal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 *  ==================== Binary Tree from Inorder and Preorder traversals ======================================
 *
 *  Given preorder and inorder traversal of a tree, construct the binary tree.
 *  Note:
 *  You may assume that duplicates do not exist in the tree.
 *  Example:
 * 				preorder = [3, 9, 20, 15, 7]  <Root, Left, Right>
 * 				inorder = [9, 3, 15, 20, 7] <Left, Root, Right>
 *
 *              Return the following binary tree: [3,9,20,null,null,15,7]
 *
 *                          	        3
 *                          	     /     \
 *                          	   9        20
 *                          	           /  \
 *                          	          15    7
 *
 * 				preorder = [40, 20, 50, 10, 60, 30]
 * 				inorder = [40, 50, 20, 60, 30, 10]
 *
 *              Return the following binary tree: [10, 20, 30, 40, 50, 60, null]
 *
 * 					        	        10
 * 					        	     /     \
 * 					        	   20       30
 * 					     	 	  /  \      /
 * 					     		40   50   60
 *
 *  https://www.geeksforgeeks.org/dsa/construct-tree-from-given-inorder-and-preorder-traversal/
 *  https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *  FIXME - do this in better way
 */
public class BuildTreeFromInorderPreorder {

    public static void main(String[] args) {
//        int[] inorder = {3, 1, 4, 0, 5, 2};
//        int[] preorder = {0, 1, 3, 4, 2, 5};


        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        // inorder NVR      - 4, 2, 5, 1, 6, 3, 7
        // preorder VLR     - 1, 2, 4, 5, 3, 6, 7

        Node root = buildTreeInorderPreorder(inorder, preorder);

        List<List<Integer>> bfsTraversal = BFSTraversal.levelOrder(root);
        System.out.println("bfsTraversal = " + bfsTraversal);
    }


    private static Node buildTreeInorderPreorder(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        Node root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderMap);
        return root;
    }

    private static Node buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {

        if(preStart > preEnd || inStart > inEnd)
            return null;

        Node root = new Node(preorder[preStart]);

        int inRoot = inMap.get(root.data);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }


}
