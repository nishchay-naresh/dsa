package com.nishchay.ds.string.a04freq;

import java.util.Map;

public class F01StringFrequency {
    public static void main(String[] args) {
        wordFrequency();
        getFrequencyOfAWord();
        removeDuplicateWords();
    }

    private static void wordFrequency() {
        String input = "Nothing is as easy as it looks but it looks is easy";
        String[] strArray = input.split(" ");

        System.out.println("input = " + input);

        Map<String, Integer> freqMap = F00StringFrequencyUtility.getFrequencyMap(strArray);
        System.out.println("simple feqMap = " + freqMap);

        freqMap = F00StringFrequencyUtility.getFrequencyMapStream_intCount(strArray);
        System.out.println("ordered feqMap = " + freqMap);

        Map<String, Long> freqMap1 = F00StringFrequencyUtility.getFrequencyMapStream(strArray);
        System.out.println("Map<String, Long> freqMap1  =  " + freqMap1);

        Map<String, Long> orderedFreqMap = F00StringFrequencyUtility.getOrderedFrequencyMapStream(strArray);
        System.out.println("orderedFreqMap = \t" + orderedFreqMap);
    }

    /*
     * A string sentence is comma separated find the frequency of the given word in the string.
     *
     * */
    private static void getFrequencyOfAWord() {

        String mainStr = "car, bus, car, jeep, cycle, bike, train, bus, truck, jeep, car, jeep, cycle, truck, train, car, bike, bus, cycle";
        String word = "car";

        Map<String, Long> freqMap = F00StringFrequencyUtility.getFrequencyMapStream(mainStr.split(", "));
        System.out.println("freqMap  =  " + freqMap);
        System.out.println("Frequency Of Word : " + word + " -> " + freqMap.get(word));
    }

    /*
     * String[] strArr = {"car", "bus", "car", "train", "cycle", "bus", "train", "bus", "car"}
     * o/p => bus, car, cycle, train
     *
     * */
    private static void removeDuplicateWords() {
        String[] strArr = {"car", "bus", "car", "train", "cycle", "bus", "train", "bus", "car"};
        Map<String, Long> freqMap = F00StringFrequencyUtility.getFrequencyMapStream(strArr);
        System.out.println("unique words = " + freqMap.keySet());
    }

}

/*
 * O/P =>
 *	input = Nothing is as easy as it looks but it looks easy
 *	Map<String, Integer> feqMap = {but=1, as=2, looks=2, is=1, it=2, easy=2, Nothing=1}
 *	Map<String, Long> freqMap1  =  {but=1, looks=2, as=2, is=1, it=2, easy=2, Nothing=1}
 *	orderedFreqMap = 	{Nothing=1, is=1, as=2, easy=2, it=2, looks=2, but=1}
 * */