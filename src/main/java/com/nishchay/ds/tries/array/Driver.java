package com.nishchay.ds.tries.array;

public class Driver {

    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("apple");
        trie.search("apple");   // returns true
        System.out.println(" trie.search(\"app\") - " +  trie.search("app"));// returns false

        trie.startsWith("app"); // returns true
        System.out.println("trie.startsWith(\"app\") - " + trie.startsWith("app"));
        System.out.println("trie.startsWith(\"agg\") - " + trie.startsWith("agg"));

        trie.insert("app");
        trie.search("app");

    }

}
