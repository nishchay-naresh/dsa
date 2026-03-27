package com.nishchay.ds.tree.a06hard;

import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01traversal.BFSTraversal;

import java.util.HashMap;
import java.util.List;


/*
 *  ==================== Binary Tree from Inorder and Postorder traversals ======================================
 *
 *  FIXME - do this in better way, although working
 */
public class BuildTreeFromInorderPostorder {

    public static void main(String[] args) {

        // preorder VLR     - 1, 2, 4, 5, 3, 6, 7
        // postorder LRV    - 4, 5, 2, 6, 7, 3, 1

        int[] inorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};
        Node root = buildTree(inorder, postorder);

        List<List<Integer>> bfsTraversal = BFSTraversal.levelOrder(root);
        System.out.println("bfsTraversal = " + bfsTraversal);
    }


    private static  Node buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;

        HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        Node root = buildTreeInorderPostorder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inorderMap);
        return root;
    }

    private static Node buildTreeInorderPostorder(int[] inorder, int is, int ie,
                                     int[] postorder, int ps, int pe,
                                     HashMap<Integer, Integer> hm) {
        if (ps > pe || is > ie) return null;

        Node root = new Node(postorder[pe]);

        int inRoot = hm.get(postorder[pe]);
        int numsLeft = inRoot - is;

        root.left = buildTreeInorderPostorder(inorder, is, inRoot - 1,
                postorder, ps, ps + numsLeft - 1,
                hm);
        root.right = buildTreeInorderPostorder(inorder, inRoot + 1, ie,
                postorder, ps + numsLeft, pe - 1,
                hm);

        return root;
    }


}
