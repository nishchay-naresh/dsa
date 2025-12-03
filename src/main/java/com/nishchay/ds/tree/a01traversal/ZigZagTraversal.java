package com.nishchay.ds.tree.a01traversal;


import com.nishchay.ds.tree.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 *  ========================= ZigZag Tree Traversal of a Binary Tree =============================
 *
 *  Zig-Zag Traversal: Alternates left-to-right and right-to-left at each level (used in hierarchical structures).
 *
 *  Given the root of a binary tree, perform a zigzag (spiral) level order traversal.
 *  For odd-numbered levels, traverse the nodes from left to right and for even-numbered levels, traverse the nodes from right to left.
 *
 * Examples:
 *			Input :
 *
 * L -> R		      5
 *				     / \
 *				   12   13          L <- R
 *				   /  \    \
 * L -> R   	  7    14   2
 *				 / \  /  \  / \
 *				17 23 27 3  8  11   L <- R
 *
 *			Output : [[5], [13, 12], [7, 14, 2], [11, 8, 3, 27, 23, 17]]
 *			Explanation:  	Start with the root
 *                              Level 0: [5]                    L -> R
 *								Level 1: [13, 12]               R -> L
 *								Level 2: [7, 14, 2]             L -> R
 *								Level 3: [11, 8, 3, 27, 23, 17] R -> L
 *
 *  https://takeuforward.org/data-structure/zig-zag-traversal-of-binary-tree/
 *  https://www.geeksforgeeks.org/dsa/zigzag-tree-traversal/
 */
public class ZigZagTraversal {

    public static void main(String[] args) {
        ex1();
        ex2();
        ex3();
    }

    private static void ex1() {
        // Representation of input binary tree:
        //		      5
        //		     / \
        //		   12   13
        //		   /  \    \
        //		  7    14   2
        //		 / \  /  \  / \
        //		17 23 27 3  8  11
        //
        // Output: [[5], [13, 12], [7, 14, 2], [11, 8, 3, 27, 23, 17]]

        Node root = new Node(5);
        root.left = new Node(12);
        root.right = new Node(13);

        root.left.left = new Node(7);
        root.left.right = new Node(14);

        root.right.right = new Node(2);

        root.left.left.left = new Node(17);
        root.left.left.right = new Node(23);

        root.left.right.left = new Node(27);
        root.left.right.right = new Node(3);

        root.right.right.left = new Node(8);
        root.right.right.right = new Node(11);

        List<List<Integer>> zigZagTraversal = zigZagTraversal(root);
        System.out.println("-------- ZigZag Traversal -----------");
        printZigZag(zigZagTraversal);
    }

    private static void ex2() {
        // Create a input binary tree
        //         20
        //       /   \
        //      8     22
        //    /  \     \
        //   4   12    11
        //      /  \
        //     10   14
        //
        // Output: [[20], [22, 8], [4, 12, 11], [14, 10]]

        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.right.right = new Node(11);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);

        List<List<Integer>> zigZagTraversal = zigZagTraversal(root);
        System.out.println("-------- ZigZag Traversal -----------");
        printZigZag(zigZagTraversal);
    }

    private static void ex3() {

        // Create binary tree:
        //        1
        //      /   \
        //     2     3
        //    / \     \
        //   4   5     6
        //
        // Output: [[1], [3, 2], [4, 5, 6]]
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        List<List<Integer>> zigZagTraversal = zigZagTraversal(root);
        System.out.println("-------- ZigZag Traversal -----------");
        printZigZag(zigZagTraversal);
    }


    private static void printZigZag(List<List<Integer>> zigZagTraversal) {
        for (List<Integer> levelNodes : zigZagTraversal) {
            for (Integer nodesData : levelNodes) {
                System.out.print(nodesData + " ");
            }
            System.out.println();
        }
    }

    /*
     *  ================ [Optimize/Expected Approach]  Using Deque - O(n) Time and O(n) Space  =====================
     *
     * 	The idea is to use a deque to store nodes level by level and alternate the direction of traversal at each level.
     *      For a level traversed left to right, nodes are popped from the front, and their children are added to the back in left → right order.
     *      For a level traversed right to left, nodes are popped from the back, and their children are added to the front in right → left order.
     *
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     * */
    private static List<List<Integer>> zigZagTraversal(Node root) {
        List<List<Integer>> traversal = new ArrayList<>();
        if (root == null)
            return traversal;

        // Create a deque to store nodes for level-order traversal
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        boolean leftToRight = true;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> levelNodes = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                // nodes are popped from the front, and their children are added to the back
                if (leftToRight) {
                    Node curr = deque.removeFirst();
                    levelNodes.add(curr.data);

                    if (curr.left != null) deque.addLast(curr.left);
                    if (curr.right != null) deque.addLast(curr.right);

                }
                // nodes are popped from the back, and their children are added to the front
                else {
                    Node curr = deque.removeLast();
                    levelNodes.add(curr.data);

                    if (curr.right != null) deque.addFirst(curr.right);
                    if (curr.left != null) deque.addFirst(curr.left);
                }
            }
            traversal.add(levelNodes);
            leftToRight = !leftToRight;
        }
        return traversal;
    }
}
