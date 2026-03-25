package com.nishchay.ds.tree.a04height;


import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01basic.TreeUtils;

/*
 *  ==================== Diameter of a Binary Tree ======================================
 *
 * Given the root of a binary tree, Find the diameter of the tree.
 * The diameter of a tree is defined as the number of edges on the longest path between any two nodes.
 *
 *   Diameter of a tree:
 *          - longest path between any two nodes
 *          - path does not need to pass vai root
 *
 *
 * Examples:
 *		    Input :
 *				      10
 *				     / \
 *				   20   30
 *
 *          Output: 2
 *          Explanation: The longest path has 2 edges (20 -> 10 -> 30).
 *
 *		    Input :
 *				      1
 *				     / \
 *				   2    3
 *				  /
 *				 4
 *              /
 *             5
 *
 *           Output: 4
 *           Explanation: The longest path has 2 edges (5 -> 4 -> 2 -> 1 -> 3). And the path is not passing vai root
 *
 *			Input :
 *				      1
 *				     / \
 *				   2    3
 *				       / \
 *				      4   7
 *                   /     \
 *                  5       8
 *                 /       /
 *                6       9
 *         Output: 4
 *         Explanation: The longest path has 6 edges (6 -> 5 -> 4 -> 3 -> 7 -> 8 -> 9).
 *
 * https://www.geeksforgeeks.org/dsa/diameter-of-a-binary-tree/
 *
 */
public class DiameterOfABinaryTree {

    public static void main(String[] args) {

        // Constructed binary tree is
        //          5
        //        /   \
        //       8     6
        //      / \   /
        //     3   7 9
        Node root = new Node(5);
        root.left = new Node(8);
        root.right = new Node(6);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.left = new Node(9);

        System.out.println(diameter2pass(root));    // 4
        System.out.println(diameter(root));         // 4

    }

    /*
     *  ================ [Naive/Bruteforce Approach] By Calculating Height For Each Node - O(n2) Time and O(h) Space =====================
     *
     * Just try to take the longest path(height) for every node
     * For every node the longest path passing through it - height(node.left) + height(node.left)
     *
     * The idea is to recursively traverse the tree.
     *  For each node, find the height of left subtree and right subtree and maintain the maximum diameter (sum height of left subtree + height of right subtree).
     *
     *
     *  Time Complexity     : O(n^2)
     *  Space complexity    : O(1)
     *
     * Here we are visiting the tree 2 times, 1 to check isBalanced(), 2nd to find the height
     * optimizing this, while finding the height checking the tree for height-balanced as well
     * */
    private static int diameter2pass(Node root) {
        if (root == null)
            return 0;

        // Get the height of left and right sub-trees
        int lheight = TreeUtils.height(root.left);
        int rheight = TreeUtils.height(root.right);

        // Get the diameter of left and right sub-trees
        int ldiameter = diameter2pass(root.left);
        int rdiameter = diameter2pass(root.right);

        return Math.max(lheight + rheight, Math.max(ldiameter, rdiameter));
    }

    /*
     *  ================ [Optimize/Expected Approach] Using Single Traversal(recursive) - O(n) Time and O(h) Space  =====================
     * Here we optimizing the previous solution
     * The idea is to calculate the diameter efficiently without recomputing the heights of the left and right subtrees for every node.
     * Using recursion, we compute both the height and the diameter in a single traversal.
     * For each node, the longest path passing through it is the sum of the heights of its left and right subtrees.
     * Among all the subtrees, maintain the maximum diameter to represent the longest path in the entire tree.
     *
     *
     *  now the isBalanced() need to check that the returned height shd be >= 1
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * */
    private static int diameter(Node root) {
        int[] maxDiameter = new int[1];
        maxDiameter[0] = Integer.MIN_VALUE;
        diameterRecur(root, maxDiameter);
        return maxDiameter[0];
    }

    // Recursive function to calculate height and update diameter
    private static int diameterRecur(Node root, int[] diameter) {
        if (root == null)
            return 0;

        int lHeight = diameterRecur(root.left, diameter);
        int rHeight = diameterRecur(root.right, diameter);

        // Update the global max diameter if this node gives a longer path
        diameter[0] = Math.max(diameter[0], lHeight + rHeight);

        // Return height of current subtree
        return 1 + Math.max(lHeight, rHeight);
    }
}
