package com.nishchay.ds.tree.a01basic;

import com.nishchay.ds.tree.Node;


public class MinMaxInTree {

    public static void main(String[] args) {

        //	        1
        //	     /     \
        //	   2        3
        //	 /  \      /  \
        //	4    5    6    7
        Node root = TreeUtils.createTree();
        MinMaxInTree ref = new MinMaxInTree();

        System.out.println("maxInBinaryTree(root) - " + ref.maxInBinaryTree(root));
        System.out.println("minInBinaryTree(root) - " + ref.minInBinaryTree(root));

        System.out.println("height(root) - " + TreeUtils.height(root));
    }

    /*
     * Give an algorithm for finding a maximum element in a binary tree.
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     * */
    public int maxInBinaryTree(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int leftMax = maxInBinaryTree(root.left);
        int rightMax = maxInBinaryTree(root.right);
        int maxValue = Math.max(leftMax, rightMax);
        maxValue = Math.max(maxValue, root.data);
        return maxValue;
    }

    /*
     * Give an algorithm for finding a minimum element in a binary tree.
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     * */
    public int minInBinaryTree(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int leftMin = minInBinaryTree(root.left);
        int rightMin = minInBinaryTree(root.right);
        int minValue = Math.min(leftMin, rightMin);
        minValue = Math.min(minValue, root.data);
        return minValue;
    }
}
