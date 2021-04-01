package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/9
 */
public class Q234_Palindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while (slow != null) {
            stack.push(slow.val);
            slow = slow.next;
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != head.val) return false;
            head = head.next;
        }
        return true;
    }


    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head;

        ListNode leftTail = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            leftTail = slow;
            slow = slow.next;
        }
        // 逆序后半部分
        ListNode prev = null, next = null;
        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        // prev 指向右半部分链尾（新的链头）
        boolean result = true;
        ListNode rightHead = prev;
        while (prev != null && prev.val == head.val) {
            prev = prev.next;
            head = head.next;
        }
        if (prev != null) result = false;

        // 恢复右半部分链表
        prev = null;
        while (rightHead != null) {
            next = rightHead.next;
            rightHead.next = prev;
            prev = rightHead;
            rightHead = next;
        }
        leftTail.next = prev;
        return result;
    }

}
