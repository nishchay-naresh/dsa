package com.nishchay.ds.stack.a01basic;

public class StackArrayImplementation {

    public static void main(String[] args) {
        myStack st = new myStack(4);

        System.out.println("Is stack empty: " + (st.isEmpty() ? "Yes" : "No"));
        System.out.println("Top element: " + st.peek());

        // pushing elements
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        System.out.println("Top element: " + st.peek());

        System.out.println("Popped: " + st.pop());
        System.out.println("Top element: " + st.peek());

        // checking if stack is empty
        System.out.println("Is stack empty: " + (st.isEmpty() ? "Yes" : "No"));

        // checking if stack is full
        System.out.println("Is stack full: " + (st.isFull() ? "Yes" : "No"));

        st.push(10);
        System.out.println("Is stack full: " + (st.isFull() ? "Yes" : "No"));
    }


    static class myStack {

        // array to store elements
        private final int[] arr;

        // maximum size of stack
        private final int capacity;

        // index of a top element
        private int top;

        // constructor
        public myStack(int cap) {
            capacity = cap;
            arr = new int[capacity];
            top = -1;
        }

        // push operation
        public void push(int x) {
            if (isFull()) {
                System.out.println("Stack Overflow");
                return;
            }
            arr[++top] = x;
        }

        // pop operation
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            return arr[top--];
        }

        // peek (or top) operation
        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return arr[top];
        }

        // check if stack is empty
        public boolean isEmpty() {
            return top == -1;
        }

        // check if stack is full
        public boolean isFull() {
            return top == capacity - 1;
        }

        /*
        * this will be the time and space complexity for all the method
        * Time Complexity: O(1)
        * Auxiliary Space: O(1)
        * */
    }

}
