package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        //考虑最高位进位
        ListNode newHead = null;
        ListNode tail = null;
        int sum = 0;
        int carry = 0;
        while (l1 != null || l2 != null) {
            sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            sum %= 10;

            if (newHead == null) {
                newHead = new ListNode(sum);
                tail = newHead;
            } else {
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
        }

        if (carry != 0) {
            tail.next = new ListNode(1);
        }

        return newHead;

    }
}
