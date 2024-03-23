package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q0234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow 指向中间节点
        ListNode prev = null, next;
        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        boolean flag = true;
        // 右半部分,prev 指向右半部分头结点
        ListNode right = prev;
        ListNode left = head;
        while (right != null) {
            if (right.val != left.val) {
                flag = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        //  反转右半部分， 从 右半部分头结点 prev 开始
        ListNode cur = prev;
        prev = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return flag;
    }


}
