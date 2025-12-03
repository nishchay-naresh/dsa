package com.nishchay.ds.tree.a01traversal;


import com.nishchay.ds.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *  ========================= Level Order Traversal (Breadth First Search) of Binary Tree =============================
 *  BFS (Breadth-First Search) is a tree traversal algorithm that visits nodes level by level, starting from the root node.
 *
 *	Given a root of the Binary Tree, find its Level Order Traversal.
 *	Level Order Traversal technique is a method to traverse a Tree such that all nodes present in the same level are traversed completely before traversing the next level.
 *
 * Examples:
 *			input :
 *
 *				      5
 *				     / \
 *				   12   13
 *				   /  \    \
 *				  7    14   2
 *				 / \  /  \  / \
 *				17 23 27 3  8  11
 *
 *			output :
 *				Output: [[5], [12, 13], [7, 14, 2], [17, 23, 27, 3, 8, 11]]
 *				Explanation:  	Start with the root - [5]
 *								Level 1: [12, 13]
 *								Level 2: [7, 14, 2]
 *								Level 3: [17, 23, 27, 3, 8, 11]
 *
 *      2. BFS Traversals: Visit nodes level by level.
 * 	        Level-Order: Processes nodes from top to bottom (used in shortest path algorithms).
 * 	        Zig-Zag Traversal: Alternates left-to-right and right-to-left at each level (used in hierarchical structures).
 *
 *          Implementation approach:
 *              BFS uses a queue data structure to maintain nodes at each level, ensuring that nodes at higher levels are visited moving to lower levels.
 *
 *  https://takeuforward.org/data-structure/level-order-traversal-of-a-binary-tree/
 *  https://www.geeksforgeeks.org/dsa/level-order-tree-traversal/
 */
public class BFSTraversal {

    public static void main(String[] args) {
        // Representation of input binary tree:
        //		      5
        //		     / \
        //		   12   13
        //		   /  \    \
        //		  7    14   2
        //		 / \  /  \  / \
        //		17 23 27 3  8  11

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

        List<List<Integer>> bfsTraversal = levelOrder(root);
        System.out.println("-------- BFS Traversal -----------");
        printBFS(bfsTraversal);
        System.out.println("...................................");
        levelOrderTraversal(root);
    }

    public static  void printBFS(List<List<Integer>> bfsTraversal) {
        for (List<Integer> levelNodes : bfsTraversal) {
            for (Integer nodesData : levelNodes) {
                System.out.print(nodesData + " ");
            }
            System.out.println();
        }
    }

    /*
     *
     * Level order traversal is defined as follows:
     * 	-	Visit the root.
     * 	-	While traversing level l, keep all the elements at level l+1 in queue.
     * 	-	Go to the next level and visit all the nodes at that level.
     * 	-	Repeat this until all levels are completed.
     *
     * */
    public static List<List<Integer>> levelOrder(Node root) {
        // Create a list of lists to store levels
        List<List<Integer>> traversal = new ArrayList<>();
        if (root == null) {
            return traversal;
        }

        // Create a queue to store nodes for level-order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node currNode;
        while (!queue.isEmpty()) {
            // Get the size of the current level
            int size = queue.size();
            // Create a list to store nodes at the current level
            List<Integer> levelNodes = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                currNode = queue.remove();
                levelNodes.add(currNode.data);

                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
            traversal.add(levelNodes);
        }
        return traversal;
    }


    // BFS - using level order traversal
    public static void levelOrderTraversal(Node root) {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.remove();
            System.out.print(tmp.data + ", ");
            if (tmp.left != null)
                queue.add(tmp.left);
            if (tmp.right != null)
                queue.add(tmp.right);
        }
    }
}
