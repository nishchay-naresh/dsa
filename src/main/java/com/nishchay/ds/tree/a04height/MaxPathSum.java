package com.nishchay.ds.tree.a04height;


import com.nishchay.ds.tree.Node;

/*
 *  ==================== Maximum Path Sum in a Binary Tree ======================================
 *
 * Given the root of the binary tree, Find the maximum path sum. The path may start and end at any node in the tree.
 *
 * Examples:
 *			Input :
 *			        10
 *			     /     \
 *			   2        10
 *			 /  \         \
 *			20    1       -25
 *         			      /  \
 *        			     3    4
 *
 *         Output: 42
 *         Explanation: Out of all the paths possible in the Binary Tree,  20 -> 2 -> 10 -> 10 has the greatest sum ie. 42.
 *                      20 -> 2 -> 10 -> 10 = 42
 *
 *			Input :
 *			       -10
 *			      /    \
 *			     9      20
 *			 	       /  \
 *				      15    7
 *
 *         Output: 42
 *         Explanation: Out of all the paths possible in the Binary Tree, 15 -> 20 -> 7 has the greatest sum ie. 42.
 *                      15 -> 20 -> 7 = 42
 *			Input :
 *			       -17
 *			     /     \
 *			   11       4
 *			 /  \      /
 *			20   -2   10
 *
 *         Output: 31
 *         Explanation: Out of all the paths possible in the Binary Tree, 20 -> 11 has the greatest sum ie. 31.
 *                      20 -> 11 = 31
 *
 *
 * https://www.geeksforgeeks.org/dsa/find-maximum-path-sum-in-a-binary-tree/
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 *
 */
public class MaxPathSum {

    public static void main(String[] args) {

        // Representation of input binary tree:
        //            10
        //           /  \
        //          2    10
        //         / \     \
        //        20  1    -25
        //                 /  \
        //                3     4
        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(4);
        System.out.println(findMaxSum(root));

        // Representation of input binary tree:
        //		       -10
        //		      /    \
        //		     9      20
        //		 	  	   /  \
        //			 	  15    7

        root = new Node(-10);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        System.out.println(findMaxSum(root));
    }

    /*
     *  ================ [Optimize/Expected Approach] Using Single Traversal(recursive) - O(n) Time and O(h) Space  =====================
     *
     * 	We can solve the maximum path sum problem using recursively traversing the tree and treating each node as a possible turning point.
     * 	At every node, we calculate two things:
     * 		The maximum path sum that passes through the current node (left + right + node value). This is used to update our global maximum.
     * 		The maximum path sum from the current node down one side (either left or right), which we return to the parent call.
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */
    private static int findMaxSum(Node root) {
        int[] maxPathSum = new int[1];

        findMaxSumRec(root, maxPathSum);

        return maxPathSum[0];
    }

    private static int findMaxSumRec(Node root, int[] maxSum) {

        if (root == null)
            return 0;

        // Calculate maximum path sums for left and right subtrees
        int left = Math.max(0, findMaxSumRec(root.left, maxSum));
        int right = Math.max(0, findMaxSumRec(root.right, maxSum));

        // Update 'maxSum' with the maximum path
        // sum passing through the current node
        maxSum[0] = Math.max(maxSum[0], left + right + root.data);

        return root.data + Math.max(left, right);
    }

}
