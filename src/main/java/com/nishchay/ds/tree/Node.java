package com.nishchay.ds.tree;

public class Node {

    // not making private, keeping it public because accessing it directly with reference outside of pkg
    public int data;
    public Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

/*
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
*/

    @Override
    public String toString() {
        return "Node[data=" + data + ", addr=" +
                Integer.toHexString(System.identityHashCode(this)) +
                "]";
    }

}
