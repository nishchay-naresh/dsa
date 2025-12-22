package com.nishchay.ds.array.a11water;

/*
 *
 *		[5,6,7,12,2,10,17,5,3] compute which bar store max water
 *
 *
 *		                                    |
 *		                                    |
 *		                                    |
 *		                                    |
 *		                                    |
 *		                  |                 |
 *		                  |                 |
 *		                  |           |     |
 *		                  |           |     |
 *		                  |           |     |
 *		            |     |           |     |
 *		      |     |     |           |     |     |
 *		|     |     |     |           |     |     |
 *		|     |     |     |           |     |     |
 *		|     |     |     |           |     |     |      |
 *		|     |     |     |     |     |     |     |	     |
 *		|     |     |     |     |     |     |     |	     |
 *		5     6     7     12    2    10    17     5		 3
 *
 *   Output: 2
 *
 * */

import java.util.Arrays;

public class TrappingRainWaterBarWithMaxWater {

    public static void main(String[] args) {

        int[] arr = new int[]{5, 6, 7, 12, 2, 10, 17, 5, 3};
        System.out.print(Arrays.toString(arr));
        System.out.print(getBarWithMaxWater(arr)); //  Bar - 2 ,	 Have maxWater - 10

        System.out.println("\n------------------------------------");
        arr = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.print(Arrays.toString(arr));
        System.out.print(getBarWithMaxWater(arr)); //  Bar - 0 ,	 Have maxWater - 2

    }


    private static int getBarWithMaxWater(int[] arr) {
        int maxWaterBar = 0;
        int maxWaterHoldByABar = 0;
        int len = arr.length;

        // For every element of the array except first and last element
        for (int i = 1; i < len - 1; i++) {

            // Find maximum element on its left
            int left = arr[i];
            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }

            // Find maximum element on its right
            int right = arr[i];
            for (int j = i + 1; j < len; j++) {
                right = Math.max(right, arr[j]);
            }

            int ithWater = Math.min(left, right) - arr[i];
            if(0 != ithWater && ithWater > maxWaterHoldByABar){
                maxWaterHoldByABar = ithWater;
                maxWaterBar = arr[i];
            }
        }
        System.out.printf(" Bar - %d ,have maxWater - %d ", maxWaterBar, maxWaterHoldByABar);
        return maxWaterBar;
    }

}

