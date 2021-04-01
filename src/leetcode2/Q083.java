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

    public ListNode deleteDuplicates2(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;

        ListNode cur = pHead;
        ListNode follow;
        ListNode head = pHead, tail = null;
        while (cur != null) {
            follow = cur.next;
            while (follow != null && follow.val == cur.val)
                follow = follow.next;
            // follow 指向第一个比 cur 大的节点 或者 null

            if (tail == null) {
                tail = cur;
            } else {
                tail.next = cur;
                tail = tail.next;
            }
            cur = follow;
        }
        // 不用判断 tail 是否为空
        tail.next = null; // 断开和后面的联系，防止 follow 已经为 null 了
        return head;
    }


    public ListNode deleteDuplicates3(ListNode pHead) {

        ListNode newHead = null, prevTail = null;

        ListNode cur = pHead, follow;

        while (cur != null) {
            follow = cur.next;
            while (follow != null && follow.val == cur.val) follow = follow.next;

            if (cur.next == follow) {
                // cur 不是重复节点
                if (prevTail == null) {
                    newHead = cur;
                } else {
                    prevTail.next = cur;
                }
                prevTail = cur;
            }

            cur = follow; // follow 指向第一个不等于  cur 的节点

        }
        if (prevTail != null) {
            prevTail.next = null;
        }

        return newHead;
    }

}
