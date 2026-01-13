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
 * ------------------------------------------------------------------------------
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
 *       Evict   -   addToHead(), deleteNode()
 *
 * */
public class LRUCacheOld<K, V> {

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
    private Node<K, V> head, tail;

    public LRUCacheOld(int capacity) {
        this.cacheSize = capacity;
        map = new HashMap<>(cacheSize);
        head = tail = null;
    }

    /*This method will make passed node as head*/
    private void addToHead(Node<K, V> newNode) {
        newNode.next = head;
        newNode.prev = null;

        if (head != null)
            head.prev = newNode;

        head = newNode;

        if (tail == null)
            tail = head;
    }

    // This method works in O(1)
    public V get(K key) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            deleteNode(node);
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
            deleteNode(oldEntry);
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
        if (map.size() >= cacheSize) {
            // remove from tail - oldest entry
            map.remove(tail.key);
            deleteNode(tail);
        }
    }

    /* This method will delete a node from doubly link list takes node as in input */
    private void deleteNode(Node<K, V> deletingNode) {

        // first node - from head
        if (deletingNode.prev == null) {
            head = deletingNode.next;
            deletingNode.next.prev = null;
            // last node - from tail
        } else if (deletingNode.next == null) {
            tail = deletingNode.prev;
            deletingNode.prev.next = null;
            // deleting from middle
        } else {
            deletingNode.next.prev = deletingNode.prev;
            deletingNode.prev.next = deletingNode.next;
        }

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