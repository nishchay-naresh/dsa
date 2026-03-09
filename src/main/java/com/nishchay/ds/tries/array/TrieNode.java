package com.nishchay.ds.tries.array;

public class TrieNode {
    TrieNode[] arr;
    boolean isEnd;

    // Initialize your data structure here.
    public TrieNode() {
        this.arr = new TrieNode[26];
    }

}