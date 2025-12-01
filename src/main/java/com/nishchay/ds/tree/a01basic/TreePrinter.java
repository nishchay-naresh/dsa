package com.nishchay.ds.tree.a01basic;

import com.nishchay.ds.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class TreePrinter {

       public static void printTree1(Node root) {
        int h = TreeUtils.height(root);
        int maxNodes = (int) Math.pow(2, h) - 1;
        int maxWidth = maxNodes * 4; // Adjust width per node spacing

        List<Node> level = new ArrayList<>();
        level.add(root);

        for (int depth = 1; depth <= h; depth++) {
            int nodesInLevel = (int) Math.pow(2, depth - 1);
            int spaceBetween = maxWidth / nodesInLevel;
            int leadingSpace = spaceBetween / 2;

            // Print node values
            printSpaces(leadingSpace);
            List<Node> next = new ArrayList<>();
            for (Node node : level) {
                if (node != null) {
                    System.out.printf("%2s", node.data);
                    next.add(node.left);
                    next.add(node.right);
                } else {
                    System.out.print("  ");
                    next.add(null);
                    next.add(null);
                }
                printSpaces(spaceBetween - 2);
            }
            System.out.println();

            // Print branches
            if (depth < h) {
                int branchLines = spaceBetween / 4;
                for (int i = 1; i <= branchLines; i++) {
                    printSpaces(leadingSpace - i);
                    for (Node node : level) {
                        if (node != null) {
                            System.out.print(node.left != null ? "/" : " ");
                            printSpaces(i * 2 - 1);
                            System.out.print(node.right != null ? "\\" : " ");
                        } else {
                            printSpaces(2 * i + 1);
                        }
                        printSpaces(spaceBetween - 2 * i);
                    }
                    System.out.println();
                }
            }

            level = next;
        }
    }

    private static void printSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }

}
