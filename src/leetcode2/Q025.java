package leetcode2;

import org.junit.Test;
import programmer_interview.ListNode;
import programmer_interview.ListUtil;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q025 {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode prevTail = null;
        ListNode cur = head, next;
        int i;
        ListNode p, q, r;
        while (cur != null) {
            next = cur.next;
            for (i = 1; i < k && next != null; i++) {
                next = next.next;
            }
            if (i != k) break;

            p = next;
            q = cur;
            while (q != next) {
                r = q.next;
                q.next = p;
                p = q;
                q = r;
            }

            if (prevTail == null) {
                head = p;
            } else {
                prevTail.next = p;
            }
            prevTail = cur;
            cur = next;
        }
        return head;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListUtil.print(l1);

        ListNode listNode = reverseKGroup(l1, 4);
        ListUtil.print(listNode);

    }
}
