package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q147 {

    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode insert = head.next;
        ListNode prev, nextInsert;
        ListNode t;
        head.next = null;

        while (insert != null) {
            nextInsert = insert.next;

            prev = null;
            t = head;
            while (t != null && t.val <= insert.val) {
                prev = t;
                t = t.next;
            }
            if (prev == null) {
                insert.next = head;
                head = insert;
            } else {
                insert.next = t;
                prev.next = insert;
            }
            insert = nextInsert;
        }

        return head;

    }
}
