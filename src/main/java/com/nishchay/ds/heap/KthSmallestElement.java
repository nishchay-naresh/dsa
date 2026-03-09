package com.nishchay.ds.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 *============== K’th Smallest/Largest Element in Unsorted Array ====================
 *
 *
 * Given an array and a number k where k is smaller than the size of the array.
 * We need to find the k’th the smallest element in the given array. It is given that all array elements are distinct.
 *
 *	Example 1
 *		Input: arr[] = {7, 10, 4, 3, 20, 15}
 *		k = 3
 *		Output: 7
 *
 *	Example 2
 *		Input: arr[] = {7, 10, 4, 3, 20, 15}
 *		k = 4
 *		Output: 10
 *
 *
 * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
 * https://www.techiedelight.com/find-kth-smallest-element-array/
 * */
public class KthSmallestElement {

    public static void main(String[] args){

        int[] arr;
        int k;

        arr = new int[] { 12, 3, 5, 7, 19 };
        k = 2;
        System.out.println("K'th smallest element is " + kthSmallest(arr, k)); // 5

        arr = new int[] { 7, 10, 4, 3, 20, 15 };
        k = 3;
        System.out.println("K'th smallest element is " + kthSmallest(arr, k)); // 7

        k = 4;
        System.out.println("K'th smallest element is " + kthSmallest(arr, k)); // 10
    }


    /*
     *
     * Time complexity - O(n logk)
     * Space complexity - O(k)
    * */
    private static int kthSmallest(int[] arr, int k) {

        int res = 0;

        // maxHeap, bcus max among k smallest element
        // similarly minHeap for largest element
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for(int e : arr){
            maxHeap.offer(e);
            if(maxHeap.size() > k ){
                maxHeap.poll();
            }
        }

        if(maxHeap.peek() != null)
            res = maxHeap.peek();
        return res;
    }

}
