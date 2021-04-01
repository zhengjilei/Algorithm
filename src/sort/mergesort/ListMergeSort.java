package sort.mergesort;

import leetcode.ListNode;

/**
 * 链表的归并排序
 * 空间复杂度: O(1)
 * <p>
 * created by Ethan-Walker on 2018/12/25
 */
public class ListMergeSort {

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) return head; // 只有一个节点就不要划分了，防止划分后，一个链表尾空，合并时出错

        ListNode mid = getMidNode(head);

        ListNode head2 = sortList(mid.next);   // 先对后半段排序，防止设置 mid.next = null 后，导致找不到后半部分

        mid.next = null;   // 使得前一半单独成链
        ListNode head1 = sortList(head);

        return merge(head1, head2);
    }

    /**
     * 合并两个链表，返回新链表的头结点
     *
     * @param head1
     * @param head2
     * @return
     */
    private ListNode merge(ListNode pHead, ListNode qHead) {

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


    /**
     * 快慢指针，返回链表中间节点
     * 偶数个：返回中间节点的前一个
     *
     * @param head
     * @return
     */
    public ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fast = head.next, slow = head;// 偶数个，结束时 slow 指向中间节点的前一个
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }

}
