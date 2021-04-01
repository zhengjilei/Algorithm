package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * created by Ethan-Walker on 2019/2/12
 */
public class Q445_TwoNumAdd {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return method1(l1, l2);
    }

    // 反转链表
    public ListNode method1(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        if (l1 == null || l2 == null) return l1 != null ? l1 : l2;
        int carry = 0;
        int sum = l1.val + l2.val;

        if (sum >= 10) {
            carry = 1;
            sum %= 10;
        }
        l1 = l1.next;
        l2 = l2.next;
        ListNode res = new ListNode(sum);
        ListNode prev = res;
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
            prev.next = new ListNode(sum % 10);
            prev = prev.next;
        }
        if (carry == 1) {
            prev.next = new ListNode(1);
        }
        res = reverse(res);
        return res;

    }

    public ListNode reverse(ListNode l) {
        if (l == null || l.next == null) return l;
        ListNode prev = null, cur = l, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // 不反转链表，用栈解决
    public ListNode method2(ListNode l1, ListNode l2) {

        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        Deque<Integer> resStack = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int sum = 0, carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            sum = carry;
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            resStack.push(sum % 10);
            carry = sum / 10;
        }

        if (carry == 1) {
            resStack.push(1);
        }
        ListNode resList = null;
        if (!resStack.isEmpty()) {
            resList = new ListNode(resStack.pop());

            ListNode prev = resList;
            while (!resStack.isEmpty()) {
                prev.next = new ListNode(resStack.pop());
                prev = prev.next;
            }
        }
        return resList;

    }
}
