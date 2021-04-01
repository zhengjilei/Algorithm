package bytedance;


import programmer_interview.ListNode;

/**
 * created by Ethan-Walker on 2018/12/28
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode prev = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
