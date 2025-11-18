package com.nishchay.ds.linklist;

public class Node {

    // not making private, keeping it public because accessing it directly with reference outside of pkg
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
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
