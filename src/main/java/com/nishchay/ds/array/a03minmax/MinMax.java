package com.nishchay.ds.array.a03minmax;

import com.nishchay.ds.array.ArrayUtils;

import java.util.Arrays;

public class MinMax {

    public static void main(String[] args) {

        System.out.println("--------------------Find the smallest and largest element in unsorted array – O(n) Time ----------------------");
        int[] intArray = {2, 4, 7, 6, 5, 1, 3, 10};
        System.out.printf("%s array is having smallest element = %d", Arrays.toString(intArray), ArrayUtils.findMin(intArray));
        System.out.printf("%s array is having largest element = %d", Arrays.toString(intArray), ArrayUtils.findMax(intArray));

        intArray = new int[]{3, 13, 14, 7, -3, 15, 9, 21, 10};
        System.out.printf("%s array is having smallest element = %d", Arrays.toString(intArray), ArrayUtils.findMin(intArray));
        System.out.printf("%s array is having largest element = %d", Arrays.toString(intArray), ArrayUtils.findMax(intArray));

    }
}
