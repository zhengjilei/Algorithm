package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q0142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        // 判断是否有环
        ListNode p = head, q = head;
        while (q != null && q.next != null) {
            q = q.next.next;
            p = p.next;
            if (q == p) {
                break;
            }
        }
        if (q == null || q.next == null) return null;

        //计算环的长度
        int len = 1;
        while (q.next != p) {
            q = q.next;
            len++;
        }

        // p q 重新指向 head, 让 q 先走 len 步
        p = q = head;
        while (len > 0) {
            q = q.next;
            len--;
        }

        while (q != p) {
            q = q.next;
            p = p.next;
        }
        return q;

    }
}
