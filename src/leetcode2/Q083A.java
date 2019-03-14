package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q083A {
    public ListNode deleteDuplicates(ListNode pHead) {

        if (pHead == null || pHead.next == null) return pHead;
        ListNode prev = pHead, cur = pHead.next, next;
        while (cur != null) {
            while (cur != null && cur.val == prev.val) {
                cur = cur.next;
            }
            // cur 指向第一个不等于 prev 的节点
            prev.next = cur;

            prev = cur;
        }
        return pHead;
    }
}
