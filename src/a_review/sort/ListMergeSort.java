package a_review.sort;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class ListMergeSort {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode midNode = getMidNode(head);

        ListNode rightHead = sortList(midNode.next);

        midNode.next = null;
        ListNode leftHead = sortList(head);

        return merge(leftHead, rightHead);

    }

    public ListNode merge(ListNode pHead, ListNode qHead) {
        if (pHead == null) return qHead;
        if (qHead == null) return pHead;

        ListNode newHead = null, tail = null, cur = null;
        ListNode p = pHead, q = qHead;
        if (p.val <= q.val) {
            newHead = p;
            p = p.next;
        } else {
            newHead = q;
            q = q.next;
        }

        tail = newHead;
        tail.next = null;
        while (p != null && q != null) {
            if (p.val < q.val) {
                tail.next = p;
                p = p.next;
            } else {
                tail.next = q;
                q = q.next;
            }
            tail = tail.next;
//            tail.next = null; // 断开和后面的节点的链接,实际上不需要断开
        }
        if (p != null) tail.next = p;
        if (q != null) tail.next = q;
        return newHead;
    }

    public ListNode getMidNode(ListNode head) {
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
