package bytedance;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/2/13
 */
public class Q142_CircleLinkedList {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head;

        boolean hasCircle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCircle = true;
                break;
            }
        }
        if (!hasCircle) return null;

        int circleLen = 1;
        fast = fast.next;
        while (fast != slow) {
            fast = fast.next;
            circleLen++;
        }

        fast = slow = head;
        while (circleLen > 0) {
            fast = fast.next;
            circleLen--;
        }
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


}
