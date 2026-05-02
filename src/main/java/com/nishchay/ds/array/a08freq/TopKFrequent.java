package com.nishchay.ds.array.a08freq;


import java.util.*;

/*
 * ==================================== Top K Frequent in an Array ======================================
 *
 * Given an array arr[] and a positive integer k, Find the top k elements which have the highest frequency in the array.
 * Note: If more than one element has same frequency then priorities the larger element over the smaller one.
 *
 * You may return the answer in any order.
 *
 * Examples:
 *				Input: arr[] = [3, 1, 4, 4, 5, 2, 6, 1], k = 2
 *				Output: [4, 1]
 *				Explanation: Frequency of 4 is 2 and frequency of 1 is 2, these two have the maximum frequency.
 *
 *				Input: arr[] = [7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9], k = 4
 *				Output: [5, 11, 7, 10]
 *				Explanation: freq map : {5->3, 11->2, 7->2, 10->1, 9->1, 8->1, 2->1}, get the first 4 no
 *              Frequency of 5 is 3, frequency of 11 is 2, frequency of 7 is 2, and frequency of rest is 1  but 10 is largest .
 *
 * write a method something like this :     int[] nthMostOccurrence(int[], int k){ }
 *
 * https://www.geeksforgeeks.org/dsa/find-k-numbers-occurrences-given-array/
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * */
public class TopKFrequent {

    public static void main(String[] args) {

        int[] arr;
        int k;
        int[] topK;

        arr = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        k = 2;
        topK = topKFreq_sort(arr, k);
        System.out.println("topK = " + Arrays.toString(topK)); // [4, 1]

        arr = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        k = 4;
        topK = topKFreq_sort(arr, k);
        System.out.println("topK = " + Arrays.toString(topK)); // [5, 11, 7, 10]

        System.out.println("----------------------------------------------------");
        arr = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        k = 2;
        topK = topKFreq_heap(arr, k);
        System.out.println("topK = " + Arrays.toString(topK)); // [4, 1]

        arr = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        k = 4;
        topK = topKFreq_heap(arr, k);
        System.out.println("topK = " + Arrays.toString(topK)); // [5, 11, 7, 10]

        System.out.println("----------------------------------------------------");
        arr = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        k = 2;
        topK = topKFreq_bucket(arr, k);
        System.out.println("topK = " + Arrays.toString(topK)); // [4, 1]

        arr = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        k = 4;
        topK = topKFreq_bucket(arr, k);
        System.out.println("topK = " + Arrays.toString(topK)); // [5, 11, 7, 10]
    }


    /*
     * ================ [Naive Approach] Using Hash map and Sorting - O(n + d log d) Time and O(d) Space  =====================
     *
     * You are asked: “Give me the k elements that appear most frequently”
     * So naturally, 2 steps come to mind:
     * Step 1: Count frequency - construct a freqMap from this int[]
     * Now the problem becomes: “Find top k elements from this frequency map”
     *
     *  The idea is to use a hashmap to store each element along with its frequency,
     *  After building the frequency map, sort the elements in decreasing order of their frequency.
     *  To find the top k elements, simply take the first k elements from the sorted list.
     *
     * Time complexity: O(n + d log d), where n is the size of the array and d is the count of distinct elements in the array.
     * Space complexity: O(d), where d is the count of distinct elements in the array.
     *
     * */
    private static int[] topKFreq_sort(int[] arr, int k) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int curr : arr) {
            freq.put(curr, freq.getOrDefault(curr, 0) + 1);
        }
        ArrayList<Map.Entry<Integer, Integer>> freqList = new ArrayList<>(freq.entrySet());

        Comparator<Map.Entry<Integer, Integer>> valueThenKeyComparator =
                Comparator.comparing(Map.Entry<Integer, Integer>::getValue, Comparator.reverseOrder())
                        .thenComparing(Map.Entry<Integer, Integer>::getKey, Comparator.reverseOrder());

        freqList.sort(valueThenKeyComparator);

        Iterator<Map.Entry<Integer, Integer>> iterator = freqList.iterator();
        int[] result = new int[k];
        for (int i = 0; i < k && iterator.hasNext(); i++) {
            result[i] = iterator.next().getKey();
        }
        return result;
    }


    /*
     * ================ [Expected Approach 1] Using Hash map and Min Heap - O(n + d log d) Time and O(d) Space  =====================
     *
     *	The idea is to use a hashmap to store each element and its frequency.
     *	Then, use a priority queue (min-heap) to store only key, but based on its freq, so that the element with the smallest frequency is on top.
     *	Iterate through the hashmap and push each pair into the heap, and if the heap size exceeds k, remove the top element.
     *	After processing all elements, the heap will contain the k most frequent elements.
     *	Finally, extract these elements from the heap and store them in the result array.
     *
     *  if k < (very smaller) n => then we are gaining advantage here in complexity
     *
     *  Here we can't guarantee for the order of result - it's all about having topK in any order
     *
     * Time complexity: O(n + d log d), where n is the size of array.
     * Space complexity: O(d), where d is the count of distinct elements in the array.
     * */
    private static int[] topKFreq_heap(int[] arr, int k) {

        // Step 1: Frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int curr : arr) {
            freqMap.put(curr, freqMap.getOrDefault(curr, 0) + 1);
        }

        // Step 2: Min Heap (based on frequency) => Comparator.comparingInt(freqMap::get)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> {
                    int freqCompare = freqMap.get(a) - freqMap.get(b);
                    if (freqCompare != 0) return freqCompare;
                    return a - b; // smaller element removed first → keeps larger
                }
        );

        // Step 3: Maintain top k elements
        for (int num : freqMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }

        // Step 4: Extract result
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll();
        }

        return result;
    }

    /*
     * ================ [Expected Approach 2] Using Bucket Sort/Counting sort - O(n) Time and O(n) Space  =====================
     *
     *	The idea is to utilize more space to improve the time complexity,
     *  We store the elements based on their frequencies. Like collecting them in different buckets based on their frequency
     *  We can use the frequency of each element as index of 2D array(like grouping key based on similar frequency)
     *  Where each index represents a list of elements of specific frequency
     *  By doing this, we reduce the need for complex sorting operations
     *  Instead, we can efficiently traverse the buckets from the highest frequency to lowest and collect the top k most frequent elements
     *
     *  Here we can't guarantee for the order of result - it's all about having topK in any order
     * We are returning answer in any order.
     *
     * Time complexity: O(n) Build map + O(n) Bucket fill + O(n) Traverse
     * Space complexity: O(n), where d is the count of distinct elements in the array.
     * */
    private static int[] topKFreq_bucket(int[] arr, int k) {

        // Count frequency of each element
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Find the maximum frequency - to define the size of 2D array
        int maxFreq = 0;
        for (int val : freq.values()) {
            maxFreq = Math.max(maxFreq, val);
        }

        // Create buckets based on frequencies, Each bucket index represents frequency
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= maxFreq; i++) {
            buckets.add(i, new ArrayList<>());
        }

        // Fill buckets
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        // collect top k frequent elements
        int[] result = new int[k];
        int r = 0;
        for (int i = maxFreq; i >= 1; i--) {
            List<Integer> list = buckets.get(i);
            list.sort(Collections.reverseOrder());
            for (int num : list) {
                result[r] = num;
                r++;
                if (r == k) {
                    return result;
                }
            }
        }
        return result;
    }
}
