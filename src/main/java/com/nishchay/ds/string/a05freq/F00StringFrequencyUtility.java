package com.nishchay.ds.string.a05freq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class F00StringFrequencyUtility {

    // Count the frequency of each character in a String
    static Map<Character, Integer> getFrequencyMap(String word) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char currChar : word.toCharArray()) {
            freqMap.put(currChar, freqMap.getOrDefault(currChar, 0) + 1);
        }
        return freqMap;
    }

    // java 8 feature to get the frequency map - HashMap
    public static Map<Character, Long> getFrequencyMapStream(String word) {
        Map<Character, Long> freqMap = word.chars()
                .mapToObj(i -> Character.toLowerCase(Character.valueOf((char) i))) //convert to lowercase & then to Character object
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return freqMap;
    }

    // java 8 feature to get the frequency map - LinkedHashMap
    static Map<Character, Long> getOrderedFrequencyMapStream(String word) {
        Map<Character, Long> freqLinkedHashMap = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        return freqLinkedHashMap;
    }

    // Get the frequency of each integer in an integer array
    public static Map<Integer, Integer> getFrequencyMap(int[] intArray) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : intArray) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap;
    }

    // Get the frequency of each string in a String array - using hashMap methods
    static Map<String, Integer> getFrequencyMap(String[] strArray) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String currStr : strArray) {
            freqMap.put(currStr, freqMap.getOrDefault(currStr, 0) + 1);
        }
        return freqMap;
    }

    /*
     *  java 8 feature to get the frequency map - HashMap
     *  Get the frequency of each string in a String array - using groupingBy
     *
     * Collectors.groupingBy(-) 		- return Map<String, List<String>> groups similar value in same list
     * Collectors.groupingBy(-,-) 	    - return Map<String, Long> of HashMap
     * Collectors.groupingBy(-,-,-) 	- return Map<String, Long> of LinkedHashMap
     *
     * */
    public static Map<String, Long> getFrequencyMapStream(String[] strArray) {
        Map<String, Long> freqMap = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return freqMap;
    }

    // java 8 feature to get the frequency map - LinkedHashMap
    static Map<String, Long> getOrderedFrequencyMapStream(String[] strArray) {
        Map<String, Long> freqLinkedHashMap = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        return freqLinkedHashMap;
    }

    /*
     *   grouping function to return Map<String, Integer> instead of Map<String,Long>
     *   all 3 ways will give you an ordered frequency map in the form of linkedHashMap
     *
     * https://stackoverflow.com/questions/55353829/java-8-grouping-function-to-return-mapstring-integer-instead-of-mapstring-lo
     * */
    static Map<String, Integer> getFrequencyMapStream_intCount(String[] strArray) {

        Map<String, Integer> freqMap;

        freqMap =
                Arrays.stream(strArray)
                        .collect(Collectors.groupingBy(Function.identity(),
                                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                                )
                        );

        // can perform a conversion of Long to Integer after the counting like
        freqMap = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,
                        Collectors.collectingAndThen(Collectors.counting(), e -> e.intValue())));

        // you may also count using an int value type in the first place
        freqMap = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,
                        Collectors.summingInt(word -> 1)));

        // This is summing a one for each word. You can use the same approach with the toMap collector
        Map<String, Integer> map = Arrays.stream(strArray)
                .collect(Collectors.toMap(Function.identity(), word -> 1, Integer::sum));

        return freqMap;
    }
}
