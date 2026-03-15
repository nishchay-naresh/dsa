package com.nishchay.ds.stack.a03nge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * ====================  496. Next Greater Element I =======================
 *
 * The next greater element of some element x in an array is the first greater element to the right of x in the same array.
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 * Return an array ans of nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 * 	Example 1:
 * 				Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * 				Output: [-1,3,-1]
 * 				Explanation: The next greater element for each value of nums1 is as follows:
 * 				- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * 				- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * 				- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * 	Example 2:
 * 				Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * 				Output: [3,-1]
 * 				Explanation: The next greater element for each value of nums1 is as follows:
 * 				- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * 				- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 * https://leetcode.com/problems/next-greater-element-i/description/
 * */
public class NextGreaterElement1 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        System.out.println("result array - " + Arrays.toString(nextGreaterElementSubset(nums1, nums2)));

        nums1 = new int[]{2, 4};
        nums2 = new int[]{1, 2, 3, 4};
        System.out.println("result array - " + Arrays.toString(nextGreaterElementSubset(nums1, nums2)));
    }


    /*
     *  ================ [Expected Approach] Using Map - O(n) Time and O(n) Space  =====================
     *
     *	1.  Traverse nums2 and get NGE array for nums2
     *      store the NSE in a map, by mapping it against of each element ngeMap<a[i], its NGE>
     *  2.  Traverse nums1, get its element NGE from ngeMap
     *
     *  Time Complexity     : O(n)
     *  Space complexity    : O(n)
     */
    private static int[] nextGreaterElementSubset(int[] nums1, int[] nums2) {
        Map<Integer, Integer> ngeMap = getNGEMap(nums2);
        int n = nums1.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = ngeMap.get(nums1[i]);
        }
        return result;
    }

    private static Map<Integer, Integer> getNGEMap(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> ngeMap = new HashMap<>();
        Stack<Integer> stk = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peek() <= arr[i]) {
                stk.pop();
            }
            if (stk.isEmpty()) {
                ngeMap.put(arr[i], -1);
            } else {
                ngeMap.put(arr[i], stk.peek());
            }
            stk.push(arr[i]);
        }
        return ngeMap;
    }

}
