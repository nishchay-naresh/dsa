package com.nishchay.ds.tree.a02traversal.vertical;


/*
 * ==================== Bottom View of a Binary Tree ======================================
 *
 * Given the root of a binary tree, find the bottom view of the tree as a list of node values in order of increasing horizontal distance(HD).
 * The root has HD = 0.
 *
 * The left child decreases HD by 1, and the right child increases HD by 1.
 * If multiple nodes share the same HD and depth, select the node that appears later in level-order traversal.
 * Return the values of these nodes from the leftmost to the rightmost horizontal distance.
 *
 * Examples:
 *			Input :
 *			        1
 *			     /     \
 *			    2       3
 *			 /  \         \
 *			4    5         6
 *
 *
 *          Output: [4, 2, 5, 3, 6]
 *          Explanation: The Green colored nodes represents the top view in the below Binary tree.
 *
 *			Input :
 *		        20
 *		       /  \
 *		      8    22
 *		     / \   / \
 *		    5   3 4  25
 *		       / \     \
 *		     10  14    28
 *          Output: [5, 10, 4, 28, 25]
 *          Explanation: Here, 14 and 28 both have horizontal distance = 1 from root, but we are taking 28 in our answer as 28 appears later in the level order traversal.
 *
 *  https://www.geeksforgeeks.org/dsa/print-nodes-top-view-binary-tree/
 *
 */

import com.nishchay.ds.tree.Node;

import java.util.*;

public class BottomView {

    public static void main(String[] args) {

        // Create a sample binary tree
        //     10
        //    / \
        //   20   30
        //  / \  /  \
        // 40  60 90 100

        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.right = new Node(60);
        root.right.left = new Node(90);
        root.right.right = new Node(100);

        List<Integer> result = topView(root);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    /*
     *  ================ [Expected Approach] Recursive Approach - O(n) Time and O(h) Space =====================
     *
     *  The idea is similar to Vertical Order Traversal. Like vertical Order Traversal,
     *  we need to put nodes of the same horizontal distance together.
     *  We just do a level order traversal (bfs) instead of dfs
     *  so that the topmost node at a horizontal distance is visited before any other node of the same horizontal distance below it.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     * Function to check if two roots are mirror images
     * */
    static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Map to store the first node at each
        // horizontal distance (hd)
        Map<Integer, Integer> topNodes = new TreeMap<>();

        // Queue to store nodes along with their
        // horizontal distance
        Queue<Object[]> q = new LinkedList<>();

        q.add(new Object[]{root, 0});

        while (!q.isEmpty()) {

            Object[] nodeHd = q.poll();

            // Current node
            Node node = (Node) nodeHd[0];

            // Current horizontal distance
            int hd = (int) nodeHd[1];

            // If this horizontal distance is seen for the first
            // time, store the node
            if (!topNodes.containsKey(hd)) {
                topNodes.put(hd, node.data);
            }

            // Add left child to the queue with horizontal
            // distance - 1
            if (node.left != null) {
                q.add(new Object[]{node.left, hd - 1});
            }

            // Add right child to the queue with
            // horizontal distance + 1
            if (node.right != null) {
                q.add(new Object[]{node.right, hd + 1});
            }
        }

        // Extract the nodes from the map in sorted order
        // of their horizontal distances
        for (Map.Entry<Integer, Integer> entry : topNodes.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

}
