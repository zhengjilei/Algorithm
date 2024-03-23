package leetcode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q0025_ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prevTail = null, cur = head, next;
        int i = 0;
        while (cur != null) {
            // 从 cur 找k个节点
            next = cur;
            for (i = 0; i < k && next != null; i++) {
                next = next.next;
            }
            if (i != k) {
                break;
            }
            // next 指向下一趟反转的起点
            // 反转从 cur  .. -> next(不包括 next)，前趋设置为 next, 因为反转之后尾节点应该指向 next
            ListNode p = next, q = cur, r;
            while (q != next) {
                r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            if (prevTail != null) {
                prevTail.next = p;
            } else {
                head = p;
            }
            prevTail = cur;
            cur = next;
        }

        return head;
    }
}
