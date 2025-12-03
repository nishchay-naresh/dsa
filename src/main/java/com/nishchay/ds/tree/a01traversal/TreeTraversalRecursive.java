package com.nishchay.ds.tree.a01traversal;


import com.nishchay.ds.tree.Node;
import com.nishchay.ds.tree.a01basic.TreeUtils;
/*
 *  =================================== Tree Traversal Methods =================================
 *
 * Tree traversal is categorized into Depth-First Search (DFS) and Breadth-First Search (BFS):
 *      1. DFS Traversals:
 *          It starts from the root and explores as deeply as possible along each branch, visiting nodes until it reaches a leaf node.
 *          It then backtracks to the most recent unexplored node and continues until all nodes are visited.
 *          The order in which we visit a node determines if that traversal would be preorder, inorder and postorder.
 *
 *          Explore one branch fully before backtracking.
 * 	            In-Order    : Left -> Root -> Right (retrieves BST elements in sorted order).
 * 	            Pre-Order   : Root -> Left -> Right (used for tree reconstruction).
 *              Post-Order  : Left -> Right -> Root (helps in deleting or evaluating expressions).
 *
 *          Implementation approach:
 *              DFS uses recursion or a stack to traverse deeper levels of the tree before visiting nodes at the same level.
 *
 *      2. BFS Traversals: Visit nodes level by level.
 * 	        Level-Order: Processes nodes from top to bottom (used in shortest path algorithms).
 * 	        Zig-Zag Traversal: Alternates left-to-right and right-to-left at each level (used in hierarchical structures).
 *
 *          Implementation approach:
 *              BFS uses a queue data structure to maintain nodes at each level, ensuring that nodes at higher levels are visited moving to lower levels.
 *
 *
 * https://www.geeksforgeeks.org/dsa/properties-of-binary-tree/
 * https://takeuforward.org/binary-tree/binary-tree-traversal-inorder-preorder-postorder/
 * */
public class TreeTraversalRecursive {

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

        System.out.print("Preorder    : ");
        preorder(root);

        System.out.print("\nInorder     : ");
        inorder(root);

        System.out.print("\nPostorder   : ");
        postorder(root);
    }

    public static void preorder(Node root) {
        if (root == null){
            return;
        }

        System.out.print(root.data + ", ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root) {
        if (root == null){
            return;
        }

        inorder(root.left);
        System.out.print(root.data + ", ");
        inorder(root.right);
    }

    public static void postorder(Node root) {
        if (root == null){
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + ", ");
    }

}
