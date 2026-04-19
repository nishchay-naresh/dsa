package com.nishchay.ds.heap;


/*
 *================================================ Heap Data Structure ==================================================
 *
 * A Heap is a specialized tree-based data structure that satisfies the heap property:
 *      Min Heap → for every node, Parent ≤ Children
 *      Max Heap → for every node, Parent ≥ Children
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
 *
 * 		Min Heap ✔
 * 		    2
 *
 * 		Min Heap ✔
 * 		        2
 * 		       / \
 * 		      3   4
 * 		     / \ / \
 * 		    5  6 7  8
 *
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
 *
 * 		Max Heap ✔
 * 		    8
 *
 * 		Max Heap ✔
 * 		        8
 * 		       / \
 * 		      7   6
 * 		     / \ / \
 * 		    5  4 3  2
 *
 *
 * there are two methods/opeartions in heap - heapify, buildHeap
 *
 *          void buildHeap(int[] arr) {
 *              int n = arr.length;
 *              for (int i = n/2 - 1; i >= 0; i--) {
 *                  heapifyDown(arr, i, n);
 *              }
 *          }
 * heapify(t entry) - operation to add and element in heap, do the shuffling among element to maintain the heap property again
 *
 *
 * In java heaps are usually implement using priority queues, where the smallest (or largest) element is always at the root of the tree.
 *
 *
 *⏱️ Time Complexity
 *      Insert	            O(log N)
 *      Remove (min/max)	O(log N)
 *      Peek (min/max)	    O(1)
 *
 *
 * https://www.geeksforgeeks.org/dsa/heap-data-structure/
 * https://www.geeksforgeeks.org/dsa/binary-heap/
 *
 * */
public class H01HeapDSImpl {

    public static void main(String[] args) {
        MinHeap h = new MinHeap(11);
        h.insertKey(3);
        h.insertKey(2);
        h.deleteKey(1);
        h.insertKey(15);
        h.insertKey(5);
        h.insertKey(4);
        h.insertKey(45);
        System.out.print(h.extractMin() + " ");
        System.out.print(h.getMin() + " ");

        h.decreaseKey(2, 1);
        System.out.print(h.getMin());

    }

    // A class for Min Heap
    static class MinHeap {

        // To store array of elements in heap
        private final int[] heapArray;

        // max size of the heap
        private final int capacity;

        // Current number of elements in the heap
        private int current_heap_size;

        // Constructor
        public MinHeap(int n) {
            capacity = n;
            heapArray = new int[capacity];
            current_heap_size = 0;
        }

        // Swapping using reference
        private void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }


        // Get the Parent index for the given index
        private int parent(int key) {
            return (key - 1) / 2;
        }

        // Get the Left Child index for the given index
        private int left(int key) {
            return 2 * key + 1;
        }

        // Get the Right Child index for the given index
        private int right(int key) {
            return 2 * key + 2;
        }


        // Inserts a new key
        public boolean insertKey(int key) {
            if (current_heap_size == capacity) {
                // heap is full
                return false;
            }

            // First insert the new key at the end
            int i = current_heap_size;
            heapArray[i] = key;
            current_heap_size++;

            // Fix the min heap property if it is violated
            while (i != 0 && heapArray[i] < heapArray[parent(i)]) {
                swap(heapArray, i, parent(i));
                i = parent(i);
            }
            return true;
        }

        // Decreases value of given key to new_val.
        // It is assumed that new_val is smaller than heapArray[key].
        public void decreaseKey(int key, int new_val) {
            heapArray[key] = new_val;

            while (key != 0 && heapArray[key] < heapArray[parent(key)]) {
                swap(heapArray, key, parent(key));
                key = parent(key);
            }
        }

        // Returns the minimum key (key at root) from min heap
        public int getMin() {
            return heapArray[0];
        }


        // Method to remove minimum element (or root) from min heap
        public int extractMin() {
            if (current_heap_size <= 0) {
                return Integer.MAX_VALUE;
            }

            if (current_heap_size == 1) {
                current_heap_size--;
                return heapArray[0];
            }

            // Store the minimum value,
            // and remove it from heap
            int root = heapArray[0];

            heapArray[0] = heapArray[current_heap_size - 1];
            current_heap_size--;
            MinHeapify(0);

            return root;
        }

        // This function deletes key at the
        // given index. It first reduced value
        // to minus infinite, then calls extractMin()
        public void deleteKey(int key) {
            decreaseKey(key, Integer.MIN_VALUE);
            extractMin();
        }

        // A recursive method to heapify a subtree with the root at given index
        // This method assumes that the subtrees are already heapified
        private void MinHeapify(int key) {
            int l = left(key);
            int r = right(key);

            int smallest = key;
            if (l < current_heap_size && heapArray[l] < heapArray[smallest]) {
                smallest = l;
            }
            if (r < current_heap_size && heapArray[r] < heapArray[smallest]) {
                smallest = r;
            }

            if (smallest != key) {
                swap(heapArray, key, smallest);
                MinHeapify(smallest);
            }
        }

        // Increases value of given key to new_val.
        // It is assumed that new_val is greater than heapArray[key].
        // Heapify from the given key
        public void increaseKey(int key, int new_val) {
            heapArray[key] = new_val;
            MinHeapify(key);
        }

        // Changes value on a key
        public void changeValueOnAKey(int key, int new_val) {
            if (heapArray[key] == new_val) {
                return;
            }
            if (heapArray[key] < new_val) {
                increaseKey(key, new_val);
            } else {
                decreaseKey(key, new_val);
            }
        }
    }
}
