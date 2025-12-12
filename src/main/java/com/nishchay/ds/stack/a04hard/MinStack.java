package com.nishchay.ds.stack.a04hard;

import java.util.Stack;

/*
 *	=========== Design a stack that supports getMin() in O(1) time and O(1) extra space ===========
 *
 * Design a SpecialStack that supports push(x), pop(), peek(), and getMin() in O(1) time.
 *      push(x) → add element x
 *      pop() → remove top element
 *      peek() → return top element without removing; -1 if empty
 *      getMin() → return minimum element; -1 if empty
 * All operations run in O(1).
 *
 *	Design a Data Structure SpecialStack that supports all the stack operations like push(), pop(), isEmpty(), isFull()
 *  and an additional operation getMin() which should return minimum element from the SpecialStack.
 *  All these operations of SpecialStack must be O(1) time and O(1) extra space.
 *
 * 	Examples:
 *				Input: operations[] = [push(2), push(3), peek(), pop(), getMin(), push(1), getMin()]
 *				Output: [3, 2, 1]
 *				Explanation:
 *				push(2): Stack is [2]
 *				push(3): Stack is [2, 3]
 *				peek(): Top element is 3
 *				pop(): Removes 3, stack is [2]
 *				getMin(): Minimum element is 2
 *				push(1): Stack is [2, 1]
 *				getMin(): Minimum element is 1
 *
 *				Input: operations[] = [push(10), getMin(), push(5), getMin(), pop()]
 *				Output: [10, 5]
 *				Explanation:
 *				push(10): Stack is [10]
 *				getMin(): Minimum element is 10
 *				push(5): Stack is [10, 5]
 *				getMin(): Minimum element is 5
 *				pop(): Removes 5, stack is [10]
 *
 * https://www.youtube.com/watch?v=QMlDCR9xyd8
 * https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
 * */
public class MinStack {

    public static void main(String[] args) {

        myStackPairEx();
        specialStackEx();
    }


    private static void myStackPairEx() {
        MyStackPair stack = new MyStackPair();
        stack.push(12);
        stack.push(15);
        stack.push(10);
        System.out.println(stack.getMin());         //10
        stack.pop();
        System.out.println(stack.getMin());         //12
        System.out.println(stack.peek());           //15

        stack.push(9);
        System.out.println(stack.getMin());         //9
    }

    private static void specialStackEx() {
        SpecialStack st = new SpecialStack();
        st.push(3);
        st.push(5);
        System.out.println(st.getMin());    // 3
        st.push(2);
        st.push(1);
        System.out.println(st.getMin());    // 1
        st.pop();
        System.out.println(st.getMin());    // 2
        st.pop();
        System.out.println(st.peek());      // 5
        System.out.println(st.getMin());    // 3

        System.out.println("..................");

        st = new SpecialStack();
        st.push(2);
        st.push(3);
        System.out.println(st.peek() + " ");
        System.out.println(st.getMin() + " ");
        st.pop();
        System.out.println(st.getMin() + " ");
        st.push(1);
        System.out.println(st.getMin() + " ");
    }

    /*
     * ================ [Approach 2] Using a Pair in Stack - O(1) Time and O(n) Space  =====================
     *
     *  This approach uses a stack where each element is stored as a pair:
     *          the element itself and the minimum value up to that point.
     * When an element is pushed, the minimum is updated.
     * When an element is poped, only need to remove the entry from top
     *
     *  The getMin() function directly accesses the minimum value from the top of the stack in constant time,
     *  ensuring that both push(), pop(), and getMin() operations are O(1).
     *  This approach efficiently tracks the minimum value without needing to traverse the stack.
     *
     * Time Complexity: O(1) for all operations (push, pop, top, getMin) as they involve constant time operations on the stack.
     * Space Complexity: O(n) where n is the number of elements in the stack, as we store pairs of values (element and minimum) in the stack.
     *                  O(2*n) =  O(n)
     *
     */
    static class MyStackPair {
        private final Stack<int[]> st;

        public MyStackPair() {
            st = new Stack<>();
        }

        // Add an element to the top of Stack
        public void push(int x) {
            int newMin = st.isEmpty() ? x : Math.min(x, st.peek()[1]);
            st.push(new int[]{x, newMin});
        }

        // Remove the top element from the Stack
        public void pop() {
            if (!st.isEmpty()) {
                st.pop();
            }
        }

        // Returns top element of the Stack
        public int peek() {
            return st.isEmpty() ? -1 : st.peek()[0];
        }


        // Finds minimum element of Stack
        public int getMin() {
            return st.isEmpty() ? -1 : st.peek()[1];
        }
    }

    /*
     * ================ [Approach 2] Without Extra Space- O(1) Time and O(1) Space  =====================
     * Since now we need to improve upon - space part, so we can't store the prevMin, only we have a minValue at any point of time
     * so when we are pushing / poping a value, because of which we need to modify the minValue to prevMin ( which we are not storing)
     * so need to derive a formula - between top element and prev element to store the prev value
     *
     * we only do when (currValue < minValue)
     *  val' = 2 * currValue - prevMin (maskedValue = 2 X minValue - prevMin), here currValue is the minValue value
     *  so here in val' = we are binding the prevMin & newMin
     *
     *  push(currValue){
     *      if(st.empty()){
     *          st.push(currValue)
     *          minValue = currValue
     *      }
     *      if(currValue < minValue){
     *          maskedValue = 2 X currValue - minValue
     *          st.push(maskedValue)
     *          minValue = currValue
     *      }else{
     *          st.push(currValue)
     *      }
     * }
     *  pop(){
     *      if(st.top() < minValue){
     *          minValue = 2 X minValue - st.top()
     *      }
     *      st.pop()
     * }
     *
     * while pushing it easy to get the newMin, its complex to get newMin while poping
     * new min = 2 * curr min - diff(which is been stored)
     *
     * caveat - not storing original value in stack, storing  = 2 * x - minEle
     *
     *  Time Complexity     : O(1)
     *  Space complexity    : O(1)
     */
    static class SpecialStack {
        private final Stack<Integer> stack;
        private Integer minValue;

        SpecialStack() {
            stack = new Stack<>();
            minValue = -1;
        }

        // Add an element to the top of stack
        public void push(int x) {
            if (stack.isEmpty()) {
                minValue = x;
                stack.push(x);
            }
            // If new number is less than minEle
            else if (x < minValue) {
                stack.push(2 * x - minValue);
                minValue = x;
            } else {
                stack.push(x);
            }
        }

        // Remove the top element from the stack
        public void pop() {
            if (stack.isEmpty())
                return;

            int top = stack.pop();

            // Minimum will change if min element is removed
            if (top < minValue) {
                minValue = 2 * minValue - top;
            }
        }

        // Return top element of the stack
        public int peek() {
            if (stack.isEmpty())
                return -1;

            int top = stack.peek();

            // If minEle > top, minEle stores value of top
            return (minValue > top) ? minValue : top;
        }

        // Return minimum element of the stack
        public int getMin() {
            if (stack.isEmpty())
                return -1;

            // variable minEle stores the minimum element
            return minValue;
        }
    }

}
/*
 * O/P =>

 * 1
 * 2
 * 5
 * 3
 * ..................
 * 3
 * 2
 * 2
 * 1
 * */