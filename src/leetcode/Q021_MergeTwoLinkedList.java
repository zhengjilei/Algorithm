package leetcode;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class Q021_MergeTwoLinkedList {

    public ListNode mergeTwoLists(ListNode pHead, ListNode qHead) {
        if (pHead == null) return qHead;
        if (qHead == null) return pHead;

        ListNode newHead = null, prev = null, cur = null;
        ListNode p = pHead, q = qHead;
        if (p.val <= q.val) {
            newHead = p;
            p = p.next;
        } else {
            newHead = q;
            q = q.next;
        }

        prev = newHead;
        prev.next = null;
        while (p != null && q != null) {
            if (p.val < q.val) {
                prev.next = p;
                p = p.next;
            } else {
                prev.next = q;
                q = q.next;
            }
            prev = prev.next;
            prev.next = null; // 断开和后面的节点的链接
        }
        if (p != null) prev.next = p;
        if (q != null) prev.next = q;
        return newHead;
    }
}
