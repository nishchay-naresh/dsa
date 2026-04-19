package com.nishchay.ds.heap;


import java.util.Collections;
import java.util.PriorityQueue;

/*
 *===================================== PriorityQueue in Java =====================================
 *
 * A PriorityQueue in Java is a queue where elements are ordered based on their priority, rather than the order of insertion.
 * By default, it uses natural ordering (min-heap), but a custom comparator can be used to define different priorities.
 *
 *	-	Elements are processed based on priority rather than insertion order.
 *	-	Supports standard queue operations like add(), poll(), and peek().
 *	-	Automatically grows as elements are added.
 *	-	Uses a heap data structure internally to ensure efficient insertion and removal of the highest-priority element.
 *
 * Heaps are usually used to implement priority queues, where the smallest (or largest) element is always at the root of the tree.
 *
 * Min–heap Property - in a min-heap, the root node always has the smallest value, keeps data in ascending order
 *
 * 		Min Heap ✔
 * 		    2
 * 		   / \
 * 		  3   4
 * 		 / \
 * 		5   7
 *
 * Max–heap Property - in a max-heap, the root node always has the largest value, keeps data in descending order
 *
 * 		Max Heap ✔
 * 		    8
 * 		   / \
 * 		  7   6
 * 		 / \
 * 		5   4
 *
 * =========== Hierarchy of PriorityQueue ===============
 *
 * Iterable(I)
 * 		^
 * 		|
 * Collection(I)
 * 		^
 * 		|
 *   Queue(I)
 * 		^
 * 		|
 * AbstractQueue(AC)
 * 		^
 * 		|
 * PriorityQueue(C)
 *
 * ======================= Internal data structure of priority queue =====================================
 * Java PriorityQueue is implemented using an array-backed binary min-heap,
 * where insertion and deletion take O(log n) time and the minimum element is always at the root.
 *
 * PriorityQueue is implemented using a binary heap, specifically a min-heap by default
 *
 *	Binary Heap
 *		-	Backed by a resizable array (Object[])
 *		-	Represents a complete binary tree
 *		-	Maintains the heap property
 *
 *	Default behavior
 *		-	Min-Heap → smallest element has the highest priority (at index 0)
 *		-	Can be converted to Max-Heap using a custom Comparator
 *
 * Internal Representation (Array-based Heap)

 * Heap array: [10, 20, 30, 40, 50]
 *
 * Tree form:
 *         10
 *       /    \
 *     20      30
 *    /  \
 *  40   50
 *
 * For an element at index i:
 *		Relationship			Formula
 * 		Parent					(i - 1) / 2
 * 		Left child				2*i + 1
 * 		Right child				2*i + 2
 *
 * add() / offer() → siftUp 	,Time Complexity: O(log n)
 * poll() 			→ siftDown	,Time Complexity: O(log n)
 * peek()	-> Returns queue[0]	,Time Complexity: O(1)
 *
 * Default Min-Heap
 *          PriorityQueue<Integer> pq = new PriorityQueue<>();
 *
 * Max-Heap using Comparator
 *          PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
 *
 *
 * https://www.geeksforgeeks.org/java/priority-queue-in-java/
 *
 * */
public class H01PriorityQueueEx {

    public static void main(String[] args) {
        heapAPIMethods();
        minHeapEx();
        maxHeapEx();
    }

    private static void heapAPIMethods() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(112);
        pq.offer(102);
        pq.offer(108);
        pq.offer(105);
        pq.offer(118);

        System.out.println("pq.size() = " + pq.size());

        System.out.println("pq.peek() = " + pq.peek()); // 102
        System.out.println("pq.size() = " + pq.size());

        System.out.println("pq.poll() = " + pq.poll()); // 102
        System.out.println("pq.size() = " + pq.size());

        // iterating a pq
        while(!pq.isEmpty()){
            System.out.print(pq.poll() + ", ");         // 105, 108, 112, 118,
        }
    }

    /*
     *  Min–heap Property - in a min-heap, the root node always has the smallest value, keeps data in ascending order
     *	Max–heap Property - in a max-heap, the root node always has the largest value, keeps data in descending order
     * */
    private static void minHeapEx() {

        // Priority Queue Min Type
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(3);
        minHeap.add(10);
        minHeap.add(7);
        minHeap.add(2);

        System.out.println("minHeap = " + minHeap); // minHeap =[2, 3, 7, 10]
        // Print the head of the queue
        System.out.println("Min of Queue: " + minHeap.peek()); // 2
    }

    private static void maxHeapEx() {

        // Priority Queue Min Type
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(3);
        maxHeap.add(10);
        maxHeap.add(7);
        maxHeap.add(2);

        System.out.println("maxHeap = " + maxHeap);             // [10, 3, 7, 2]
        // Print the head of the queue
        System.out.println("Max of Queue: " + maxHeap.peek());  // 10
    }
}
