package com.nishchay.zdesign.lru;

import java.util.HashMap;

/*
 *
 *   Design an LRU Cache that supports get(key) and put(key, value) operations in O(1) time
 *   with automatic eviction of the least recently used item when capacity is exceeded.
 *	    Methods:
 *			get(key): Return the value (if present), else null.
 *			put(key, value): Insert/update a key-value pair. If the cache exceeds capacity, remove the least recently used item.
 *
 *	Requirements:
 *		Use Java 8 or above.
 *		Ensure thread safety for concurrent access.
 *		Avoid using java.util.LinkedHashMap’s access-order mode.
 *		Capacity is fixed and set via constructor.
 *
 * Follow-Up Discussion Topics:
 *      How would you make this cache eviction asynchronous?
 *      Can you extend this to support time-based expiry?
 *      How would you scale this across multiple JVMs?
 *
 *
 * https://www.geeksforgeeks.org/system-design/lru-cache-implementation/
 * https://www.geeksforgeeks.org/dsa/lru-cache-implementation-using-double-linked-lists/
 * https://leetcode.com/problems/lru-cache/description/
 * https://www.baeldung.com/java-lru-cache
 * */


/*
 *
 * [Naive Approach - 1] LRU Cache using an Array of Nodes - O(n) Time and O(n) Space
 * [Naive Approach - 2] LRU Cache using Singly Linked List - O(n) Time and O(n) Space
 * [Expected Approach] LRU Cache using Doubly Linked List and Hashing- O(1) Time and O(1) Space
 *
 * Supported operations :
 *   - Add       -> external
 *   - Lookup    -> external
 *   - Evict     -> internal
 *
 *  => for Add, Lookup => HashMap
 *  => for evict => doubly link list with a head and tail pointer
 *
 *  Class level methods :
 *       Add     -   put()
 *       Lookup  -   get()
 *       Evict   -   addToHead(), deleteNode() - remove from tail - oldest entry
 *
 * HashMap already supporting get() & put() at - O(1) level, to maintain the access order in cache we need to have some other mechanism
 * To support cache eviction access based we need to maintain the element access order in a doubly linked list
 *
 * To implement an LRU cache we should use two data structures:
 *         ◦ a hashMap & -> get () & put() at O(1)
 *         ◦ a doubly linked list. -> to maintain the accessing order  at O(1)
 *
 * To have a simple code for node addition and deletion in doubly linked list , going ahead with dummey node appraoch for head and tail
 *               -1      -->     -1  --> x
 *     x <--     -1      <--     -1
 *                |               |
 *               head            tail
 *
 * Add node     -> we will keep adding element next to head,
 * delete node  -> delete a node based on a node pointer, keep intact head and tail pointer
 *
* */
public class LRUCache<K, V> {

    static class Node<K, V> {

        final K key;
        V value;
        Node<K, V> next, prev;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = prev = null;
        }

        @Override
        public String toString() {
            return key + "->" + value;
        }
    }

    private final HashMap<K, Node<K, V>> map;
    private final int cacheSize;
    private final Node<K, V> head;
    private final Node<K, V> tail;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public LRUCache(int capacity) {
        this.cacheSize = capacity;
        this.map = new HashMap<>(cacheSize);
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    // This method works in O(1)
    public V get(K key) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        return null;
    }

    // This method works in O(1)
    public void put(K key, V value) {
        if (map.containsKey(key)) {
            // update the old value
            Node<K, V> oldEntry = map.get(key);
            oldEntry.value = value; // updating value in node is enough for update, no need to do map.put(updated value) again
            removeNode(oldEntry);
            addToHead(oldEntry);
        } else {
            // insert as a new value
            Node<K, V> newNode = new Node<>(key, value);
            evictIfNeeded();
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    private void evictIfNeeded() {
        if (map.size() > cacheSize) {
            // remove from tail - oldest entry
            Node<K, V> nodeToDelete = tail.prev;
            map.remove(nodeToDelete.key);
            removeNode(nodeToDelete);
        }
    }

    /*
    *  Add a node right after the head (most recently used position)
    *  LRU -> end of DLL, at tail
    *  MRU -> start of DLL, at head
    * */
    private void addToHead(Node<K, V> newNode) {
        Node<K, V> nextNode = head.next;
        head.next = newNode;
        newNode.next = nextNode;
        newNode.prev = head;
        nextNode.prev = newNode;
    }

    // Remove a node from the list
    private void removeNode(Node<K, V> node) {
        Node<K, V> prevNode = node.prev;
        Node<K, V> nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("LRUCache{");

        String delim = " head => ";
        for (Node<K, V> p = head; p != null; p = p.next) {
            sb.append(delim);
            sb.append(p);
            delim = ", ";

        }
        sb.append(" }");
        return sb.toString();
    }
}

