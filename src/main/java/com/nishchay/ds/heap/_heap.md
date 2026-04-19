

A Heap is a specialized tree-based data structure that satisfies the heap property:
1. Min Heap → for every node, Parent ≤ Children
2. Max Heap → for every node, Parent ≥ Children

Min–heap Property - in a min-heap, the root node always has the smallest value, keeps data in ascending order

 		Min Heap ✔
 		    2
 		   / \
 		  3   4
 		 / \
 		5   7


 		Min Heap ✔
 		    2

 		Min Heap ✔
 		        2
 		       / \
 		      3   4
 		     / \ / \
 		    5  6 7  8


Max–heap Property - in a max-heap, the root node always has the largest value, keeps data in descending order

 		Max Heap ✔
 		    8
 		   / \
 		  7   6
 		 / \
 		5   4


 		Max Heap ✔
 		    8

 		Max Heap ✔
 		        8
 		       / \
 		      7   6
 		     / \ / \
 		    5  4 3  2


### What does a Heap do?

A heap is designed for one core purpose:

⚡ Quickly access/remove the smallest or largest element

⏱️ Time Complexity
* Insert	O(log N)
* Remove (min/max)	O(log N)
* Peek (min/max)	O(1)

“Heap only guarantees partial ordering, so extracting elements directly won’t give sorted order."
To return ranked results, I either:
*    Sort the K elements → O(K log K), or
*    Poll from heap → same complexity but avoids extra comparator.

### When Should You Use Heap?

Use a heap when:
1. You need Top K elements
      -  Top 10 players
      -  Top 100 products
2. You need frequent min/max access
3. Streaming data problems
   -  Median of a stream
   -  Kth largest element


### When NOT to use Heap
Need full sorted order → use TreeSet / sorting
Need fast updates by key → heap is weak here


###  1. Heap Representation (Quick Recap)

A heap is stored as an array, not pointers.

      For index i:

      Left child  = 2*i + 1  
      Right child = 2*i + 2  
      Parent      = (i - 1) / 2

###  2. What is Heapify?
💡 Definition - Heapify is the process of fixing a violation of the heap property at a given node.
 
Complexity -  O(log N)


🔍 When do we need heapify?
   -  After removing root
   -  After changing a value
   -  While building heap

