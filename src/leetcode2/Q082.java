package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/6
 */
public class Q082 {

    public ListNode deleteDuplicates(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode newHead = null, prev = null, cur = pHead;
        ListNode follow;
        while (cur != null) {
            follow = cur.next;
            while (follow != null && follow.val == cur.val) {
                follow = follow.next;
            }
            if (cur.next == follow) {
                // cur 不是重复元素
                if (newHead == null) {
                    newHead = cur;
                } else {
                    prev.next = cur;
                }
                prev = cur;
            } else {
                // cur 是重复元素
                if (prev != null) {
                    prev.next = follow;
                }
            }
            cur = follow;
        }
        if (prev != null) {
            prev.next = null;
        }
        return newHead;
    }
}
