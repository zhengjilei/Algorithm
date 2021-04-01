package a_review.interview.egien;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class InsertList {

    public ListNode insert(ListNode head, int val) {
        if (head == null) {
            return new ListNode(val);
        }
        ListNode insertNode = new ListNode(val);

        ListNode prev = null, cur = head;
        while (cur != null && cur.val <= val) {
            prev = cur;
            cur = cur.next;
        }
        insertNode.next = cur;
        if (prev == null) {
            return insertNode;
        } else {
            prev.next = insertNode;
            return head;
        }
    }

}
