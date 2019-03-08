package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/6
 */
public class Q083 {
    public ListNode deleteDuplicates(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;

        ListNode prev = pHead, next = pHead.next;
        while (next != null) {
            while (next != null && next.val == prev.val) {
                next = next.next;
            }
            prev.next = next;
            prev = next;
            if (next != null) {
                next = next.next;
            }
        }
        return pHead;
    }
}
