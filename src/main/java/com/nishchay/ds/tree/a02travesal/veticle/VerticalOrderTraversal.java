package com.nishchay.ds.tree.a02travesal.veticle;

import com.nishchay.ds.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  ========================= Vertical Traversal of a Binary Tree =============================
 *
 * Given the root of a binary Tree, Find its vertical traversal starting from the leftmost level to the rightmost level.
 *
 * 	Note: If multiple nodes are at the same horizontal distance from the root and on the same level,
 * 		  They should be printed in the order they appear in a level-order traversal (top-to-bottom, left-to-right).
 *
 * 	Here, Horizontal distance is calculated from the root to a specific node by counting how many times we move left or right along the unique path from the root to that node.
 *
 * 	The formula for Horizontal distance from the root is given by:
 * 	Horizontal Distance = Number of right moves − Number of left moves in the path from the root to that node.
 *
 * Examples:
 *			Input :
 *					        1
 *					       / \
 *					      2   3
 *					     / \  / \
 *					    4   5 6   7
 *							 \ \   \
 *					         8  9   10
 *					        /
 *					      11
 *
 *			Output: [[4], [2], [1, 5, 6, 11], [3, 8, 9], [7], [10]]
 *          Explanation: The below image shows the horizontal distances used to print vertical traversal starting from the leftmost level to the rightmost level
 *
 *
 * vertical : -2, -1, 0, 1, 2
 * level    :  0, 1, 2, 3, 4
 *
 * <vertical, level >
 *
 * https://www.geeksforgeeks.org/dsa/vertical-order-traversal-of-binary-tree-using-map/
 */
public class VerticalOrderTraversal {

    public static void main(String[] args) {

        // Create binary tree
        //            1
        //          /   \
        //         2     3
        //        / \   / \
        //       4   5 6   7
        //            \  \   \
        //             8  9   10
        //            /
        //           11

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.right.right = new Node(8);
        root.right.left.right = new Node(9);
        root.right.right.right = new Node(10);
        root.left.right.right.left = new Node(11);

        ArrayList<ArrayList<Integer>> res = verticalOrder(root);

        System.out.print("[");
        for (int i = 0; i < res.size(); i++) {
            System.out.print("[");
            ArrayList<Integer> line = res.get(i);
            for (int j = 0; j < line.size(); j++) {
                System.out.print(line.get(j));
                if (j != line.size() - 1) System.out.print(", ");
            }
            System.out.print("]");
            if (i != res.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Finds the minimum and maximum horizontal distance in the tree
    static void findMinMax(Node node, int[] min, int[] max, int hd) {
        if (node == null) return;

        if (hd < min[0]) min[0] = hd;
        else if (hd > max[0]) max[0] = hd;

        // Recur for left and right subtrees
        findMinMax(node.left, min, max, hd - 1);
        findMinMax(node.right, min, max, hd + 1);
    }

    // Collects all nodes at a given vertical distance in level-order
    static void collectVerticalLine(Node root, int dist, ArrayList<Integer> line) {
        int hd = 0;

        // Queue for level-order traversal
        Queue<ArrayList<Object>> q = new LinkedList<>();
        ArrayList<Object> first = new ArrayList<>();
        first.add(root);
        first.add(hd);
        q.add(first);

        while (!q.isEmpty()) {
            ArrayList<Object> curr = q.poll();
            Node node = (Node) curr.get(0);
            hd = (int) curr.get(1);

            if (hd == dist) line.add(node.data);

            if (node.left != null) {
                ArrayList<Object> leftNode = new ArrayList<>();
                leftNode.add(node.left);
                leftNode.add(hd - 1);
                q.add(leftNode);
            }
            if (node.right != null) {
                ArrayList<Object> rightNode = new ArrayList<>();
                rightNode.add(node.right);
                rightNode.add(hd + 1);
                q.add(rightNode);
            }
        }
    }

    // Returns the vertical order traversal of the tree
    static ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        int[] min = {0}, max = {0};
        findMinMax(root, min, max, 0);

        for (int line = min[0]; line <= max[0]; line++) {
            ArrayList<Integer> verticalNodes = new ArrayList<>();
            collectVerticalLine(root, line, verticalNodes);
            res.add(verticalNodes);
        }
        return res;
    }


}
