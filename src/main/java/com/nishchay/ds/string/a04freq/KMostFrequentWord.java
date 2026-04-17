package com.nishchay.ds.string.a04freq;

import java.util.*;

/*
 * ==================================== 692 Top K Frequent Words ======================================
 *
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 * Examples:
 *					Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 *					Output: ["i","love"]
 *					Explanation: "i" and "love" are the two most frequent words.
 *					Note that "i" comes before "love" due to a lower alphabetical order.
 *
 *					Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 *					Output: ["the","is","sunny","day"]
 *					Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *                  with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 *
 * Refer - com.nishchay.ds.array.a08freq.TopKFrequent.java
 *
 * https://www.geeksforgeeks.org/dsa/find-the-k-most-frequent-words-from-a-file/
 * https://leetcode.com/problems/top-k-frequent-words/description/
 * */
public class KMostFrequentWord {


    public static void main(String[] args) {
        String input = "car, bus, car, jeep, cycle, bike, train, bus, truck, jeep, car, jeep, cycle, train, car, bike, bus, cycle";
        int k = 2;
        List<String> frequentWord = kMostFrequentWord(input.split(",\\s*"), k);
        System.out.println("frequentWord = " + frequentWord);


        String[] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        k = 4;
        frequentWord = kMostFrequentWord(words, k);
        System.out.println("frequentWord = " + frequentWord);

        words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        k = 2;
        frequentWord = kMostFrequentWord(words, k);
        System.out.println("frequentWord = " + frequentWord);
    }


    /*
     * String input = "car, bus, car, jeep, cycle, bike, train, bus, truck, jeep, car, jeep, cycle, train, car, bike, bus, cycle";
     * freqMap  =  {car=4, bus=3, jeep=3, cycle=3, bike=2, train=2, truck=1}
     *
     * most frequent word
     * n=4, car
     * n=3, bus
     * n=3, jeep
     * n=3, cycle
     *
     *  if there are multiple words with same freq, then consider the first one
     *  listOfKeys = [car, bus, jeep, cycle, bike, train, truck]
     *
     *
     *  String[] words = new String[]{"the","day","is","sunny","the","the","the","sunny","is","is"};
     *  freqMap = {the=4, is=3, sunny=2, day=1}
     *
     *  Most frequent word = [the, is, sunny, day]
     *
     * Time Complexity : O(n + n Log k) where n is the number of words in the file
     * */
    private static List<String> kMostFrequentWord(String[] words, int k) {

        // Step 2: Frequency Map
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Step 3: Min Heap (based on frequency)
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparing(Map.Entry<String, Integer>::getValue)
                        .thenComparing(Comparator.comparing(Map.Entry<String, Integer>::getKey, Comparator.reverseOrder()))
        );

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 4: Extract result
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        Collections.reverse(result);
        return result;
    }
}
