package com.nishchay.ds.linklist.a03med;

import com.nishchay.ds.linklist.Node;
import com.nishchay.ds.linklist.Utils;

/*
 *  ======================= Add two numbers represented as Linked Lists ====================
 *
 *  Given the heads of two non-empty linked lists representing two non-negative integers.
 *  The digits are stored in reverse order, and each of their nodes contains a single digit.
 *  Add the two numbers and return the sum as a linked list.
 *
 * Examples:
 *			num1  = 243, num2 = 564
 *			l1 = [2,4,3]
 *			l2 = [5,6,4]
 *			Result: sum = 807; L = [7,0,8]
 *			Explanation: Since the digits are stored in reverse order, reverse the numbers first to get the or original number and then add them as → 342 + 465 = 807.
 *
 *			head1 -> 2 -> 4 -> 3 -> null
 *			head2 -> 5 -> 6 -> 4 -> null
 *			-----------------------------
 *			sumH  -> 7 -> 0 -> 8 -> null
 *
 *			l1 = [9, 9, 9, 9, 9, 9, 9]
 *          l2 = [9, 9, 9, 9]
 *			Result = [8, 9, 9, 9, 0, 0, 0, 1]
 *			Explanation: Since the digits are stored in reverse order, reverse the numbers first to get the original number and then add them as → 9999999 + 9999 = 8999001.
 *
 *			head1 -> 9 -> 9 -> 9 -> 9 -> 9 -> 9 -> 9 -> null
 *			head2 -> 9 -> 9 -> 9 -> 9 -> null
 *			--------------------------------------------------
 *			sumH  -> 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1 -> null
 *
 * 			l1 = [7, 5, 9, 4, 6]
 * 			l2 = [8, 4]
 * 			Result = [5, 0, 0, 5, 6]
 *
 *			head1 -> 7 -> 5 -> 9 -> 4 -> 6 -> null
 *			head2 -> 8 -> 4 -> null
 *			---------------------------------------
 *			sumH  -> 5 -> 0 -> 0 -> 5 -> 6 -> null
 *
 *			l1 = [0]
 *			l2 = [0]
 *			Result = [0]
 *
 *			head1 -> 0 -> null
 *			head2 -> 0 -> null
 *			---------------------------------------
 *			sumH  -> 0 -> null
 *
 *
 * https://www.interviewbit.com/blog/add-two-numbers-represented-by-linked-lists/
 * https://takeuforward.org/data-structure/add-two-numbers-represented-as-linked-lists/
 * https://leetcode.com/problems/add-two-numbers/description/
 * */
public class AddTwoNumbers {

    public static void main(String[] args) {

        Node head1 = Utils.createList(new int[]{2, 4, 3});
        Node head2 = Utils.createList(new int[]{5, 6, 4});
        Node resultHead = addTwoNumbers(head1, head2);
        System.out.println("Added number list :");
        Utils.printList(resultHead);                          // [7, 0, 8]

        head1 = Utils.createList(new int[]{9, 9, 9, 9, 9, 9, 9});
        head2 = Utils.createList(new int[]{9, 9, 9, 9});
        resultHead = addTwoNumbers(head1, head2);
        System.out.println("Added number list :");
        Utils.printList(resultHead);                          // [8, 9, 9, 9, 0, 0, 0, 1]

        head1 = Utils.createList(new int[]{7, 5, 9, 4, 6});
        head2 = Utils.createList(new int[]{8, 4});
        resultHead = addTwoNumbers(head1, head2);
        System.out.println("Added number list :");
        Utils.printList(resultHead);                          // [5, 0, 0, 5, 6]

        head1 = Utils.createList(new int[]{0});
        head2 = Utils.createList(new int[]{0});
        resultHead = addTwoNumbers(head1, head2);
        System.out.println("Added number list :");
        Utils.printList(resultHead);                          // [0]
    }

    /*
     * ================================ Dummy Node =================================
     *
     * Another common trick is using dummy nodes.
     * This one’s a clean code technique that makes your implementation simpler and less error-prone.
     * A dummy node is just a placeholder node that you insert before the real head of the list.
     * It eliminates messy special cases like:
     *      -   Whenever we are creating a new list as a solution or modifying the existing head
     *      -   “What if we delete the head?”
     *      -   “What if insertion happens at the very beginning?”
     *      -   “Do we need separate logic when head changes?”
     *
     *
     * ================ [Expected Approach] Two Pointer Add  =====================
     * [Efficient Approach] Using Iterative Merge - O(n+m) Time and O(1) Space
     *
     *  Create a dummy node which is the head of new linked list.
     *	Create a node temp, initialise it with dummy.
     *	Initialise carry to 0.
     *	Loop through lists l1 and l2 until you reach both ends, and until carry is present.
     *		Set sum=l1.val+ l2.val + carry.
     *		Update carry=sum/10.
     *		Create a new node with the digit value of (sum%10) and set it to temp node's next, then advance temp node to next.
     *		Advance both l1 and l2.
     *	Return dummy's next node.
     *
     *  Time Complexity     : O(n + m)
     *  Space complexity    : O(n + m)
     */
    private static Node addTwoNumbers(Node head1, Node head2) {

        Node p, q, r;
        p = head1;
        q = head2;

        // create a dummy node to simplify the adding process
        Node dummy = new Node(-1);
        r = dummy;

        int sum;
        int carry = 0;
        while (p != null || q != null || carry == 1) {
            sum = 0;
            if(p != null) {
                sum = sum + p.data;
                p = p.next;
            }

            if(q != null) {
                sum = sum + q.data;
                q = q.next;
            }

            sum = sum + carry;
            carry = sum / 10;
            Node newNode = new  Node(sum % 10);
            r.next = newNode;
            r = r.next;
        }

        return dummy.next;
    }
}
