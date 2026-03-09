package com.nishchay.ds.tries.map;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    // empty char
    private TrieNode root;

    //  Initialize your data structure here.
    public Trie() {
        root = new TrieNode();
    }

    /*
     * 1. Set a current node as a root node
     * 2. Set the current letter as the first letter of the word
     * 3. If the current node has already an existing reference to the current letter, then set current node to that referenced node.
     *  Otherwise, create a new node, set the letter equal to the current letter, and also initialize current node to this new node
     * 4. Repeat step 3 until the key is traversed
     * 5. set the isLeaf flat to true, when end char is reached.
     */

    //  Inserts a word into the trie.
    public void insert(String word) {

        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            //set leaf node
            if (i == word.length() - 1)
                t.isLeaf = true;
        }
    }

    /*
     *	1. Get children of the root
     *	2. Iterate through each character of the String
     *	3. Check whether that character is already a part of a sub-trie. If it isn't present anywhere in the trie, then stop the search and return false
     *	4. Repeat the second and the third step until there isn't any character left in the String. If the end of the String is reached, return true
     */

    //  Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);

        if (t != null && t.isLeaf)
            return true;
        else
            return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (searchNode(prefix) == null)
            return false;
        else
            return true;
    }

    public TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }
        return t;
    }

}