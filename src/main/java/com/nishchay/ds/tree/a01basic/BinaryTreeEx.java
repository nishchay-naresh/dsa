package com.nishchay.ds.tree.a01basic;

import com.nishchay.ds.tree.Node;

public class BinaryTreeEx {
    public static void main(String[] args) {

        Node root = TreeUtils.createTree();
        TreePrinter.printTree1(root);
        System.out.println("---------------------------------");
        root = TreeUtils.createTree4();
        TreePrinter.printTree1(root);
    }
}
