package com.nishchay.ds.array;


/*
*
* this class is having basic operations/functionalities of the array as a data structure
*
* */
public class ArrayUtils {

    /*
     * =========== Linear/Sequential Search ============
     *
     * just start either left/right side of the array, compare each element with the key, until you done with the entire collection
     *  if found
     *      return index
     *  else
     *      return -1;
     *
     *  Time complexity - O(n)
     * */
/*
    public static int linearSearch(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            // Return the index if key is found
            if (arr[i] == key)
                return i;
        }
        return -1;
    }
*/

    /*
     *  ============================= Binary Search ====================================
     *  Binary Search is a searching algorithm that operates on a sorted data
     *  It's repeatedly dividing data into halves to find a target value in logarithmic time O(log N).
     *
     *	1. Divide the search space into two halves by finding the middle index "mid".
     *	2. Compare the middle element of the search space with the key.
     *		If the key is found at middle element, the process is terminated.
     *		If the key is not found at middle element, choose which half will be used as the next search space.
     *			-> If the key is smaller than the middle element, then the left side is used for next search.
     *			-> If the key is larger than the middle element, then the right side is used for next search.
     *	3. This process is continued until the key is found or the total search space is exhausted.
     *
     *  Time complexity - O(log n)
     * */
/*
    public static int binarySearchIterative(int[] sortedArray, int key) {

        int left, right, mid;
        left = 0;
        right = sortedArray.length - 1;

        while (left <= right) {

            mid = (left + right) / 2;

            if (sortedArray[mid] == key) {
                return mid;
            } else if (sortedArray[mid] < key) { //  go right
                left = mid + 1;
            } else if (sortedArray[mid] > key) {//  go left
                right = mid - 1;
            }
        }
        return -1;
    }

    // The time complexity of this algorithm is O(2^n)
    public static int binarySearchRecursive(int[] array, int left, int right, int key) {

        if (right < left) {
            return -1;
        }
        int mid = (left + right) / 2;

        if (key == array[mid])
            return mid;
        else if (key < array[mid])
            return binarySearchRecursive(array, left, mid - 1, key);

        return binarySearchRecursive(array, mid + 1, right, key);
    }
*/

    // Iterative Approach - O(n) Time and O(1) Space
    public static int findMax(int[] arr) {
        int max = arr[0]; // assuming it's max value

        // Now traverse array from 1 ro n-1
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    public static int findMin(int[] arr) {
        int min = arr[0];

        // Now traverse array from 1 ro n-1
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }


}
