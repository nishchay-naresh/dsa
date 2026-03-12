package com.nishchay.ds.linklist.a03med;


import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;
import com.nishchay.ds.linklist.a02easy.ReverseAList;

/*
 *
 *
 *	Algorithm:
 *	1. 	Create two linked lists that will represent above two numbers.
 *	2.	Reverse both link list.
 *	3.	Add two node values (Each node is being represented as single digit)  starting from heads of two link list.
 *	4.	If sum is of above two node values is more than 10, then forward the carry.
 *	5.	Reverse the result, so that we will get actual sum of numbers.
 *
 *
 *
 * https://www.geeksforgeeks.org/sum-of-two-linked-lists/
 * https://www.interviewbit.com/blog/add-two-numbers-represented-by-linked-lists/
 * https://www.techiedelight.com/add-two-linked-lists-without-using-extra-space/
 *
 *
 * o/p =>
 *	head => 5 -> 7 -> 3 -> 4 -> null
 *	head => 	 9 -> 4 -> 6 -> null
 *	----------------------------------
 *	head => 6 -> 6 -> 8 -> 0 -> null
 *
 *	head => 7 -> 5 -> 9 -> 4 -> 6 -> null
 *	head => 			   8 -> 4 -> null
 *	--------------------------------------
 *	head => 7 -> 6 -> 0 -> 3 -> 0 -> null
 *
 *
 *	head => 	 5 -> 6 -> 3 -> null
 *	head => 	 8 -> 4 -> 2 -> null
 *	----------------------------------
 *	head => 1 -> 4 -> 0 -> 5 -> null
 *
 *
 * */
public class ListReverseThenAdd {

    public static void main(String[] args) {

        addTwoListEx();

    }


    private static void addTwoListEx() {

        int no1 = 563;
        int no2 = 842;

        Node list1 = createList(no1);
        Utils.printList(list1);
        Node list2 = createList(no2);
        Utils.printList(list2);

        Node sum = addLists(list1, list2);
        Utils.printList(sum);
    }


    // Function to create a list from a number
    public static Node createList(int number) {
        Node head = null;
        while (number != 0) {
            head = new Node(number % 10, head);
            number = number / 10;
        }
        return head;
    }

    public static Node addLists(Node X, Node Y) {
        // reverse `X` and `Y` to access elements from the end
        X = ReverseAList.reverse(X);
        Y = ReverseAList.reverse(Y);

        return ReverseAList.reverse(add(X, Y));
    }

    public static Node add(Node list1, Node list2) {
        Node head = null;

        // stores the last seen node
        Node prevNode = null;

        // initialize carry with 0
        int carry = 0;

        // run till both lists are empty
        while (list1 != null || list2 != null) {
            // sum is list1's data + list2's data + carry (if any)
            int sum = 0;
            if (list1 != null) {
                sum += list1.data;
            }
            if (list2 != null) {
                sum += list2.data;
            }
            sum += carry;

            // if the sum of a 2–digit number, reduce it and update carry
            carry = sum / 10;
            sum = sum % 10;

            // create a new node with the calculated sum
            Node node = new Node(sum, null);

            // if the output list is empty
            if (head == null) {
                // update `prev` and `head` to point to the new node
                prevNode = node;
                head = node;
            } else {
                // add the new node to the output list
                prevNode.next = node;

                // update the previous node to point to the new node
                prevNode = node;
            }

            // advance `list1` and `list2` for the next iteration of the loop
            if (list1 != null) {
                list1 = list1.next;
            }

            if (list2 != null) {
                list2 = list2.next;
            }
        }

        if (carry != 0) {
            prevNode.next = new Node(carry, prevNode.next);
        }

        return head;
    }
}


