package a_review.sort2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class ListInsertSort {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode insertNode = head.next;
        head.next = null;
        while (insertNode != null) {
            ListNode insertNext = insertNode.next;
            insertNode.next = null;

            ListNode prev = null, bigger = head;
            while (bigger != null && bigger.val <= insertNode.val) {
                prev = bigger;
                bigger = bigger.next;
            }
            if (prev == null) { // 头结点就比 insertNode 大
                insertNode.next = head;
                head = insertNode;
            } else {
                insertNode.next = bigger;
                prev.next = insertNode;
            }

            insertNode = insertNext;
        }
        return head;
    }
}
