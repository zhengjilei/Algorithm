package a_review.sort2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class ListMergeSort {


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = getMid(head);
        ListNode rightStart = mid.next;
        mid.next = null;

        ListNode rightHead = sortList(rightStart);
        ListNode leftHead = sortList(head);
        ListNode res = merge(leftHead, rightHead);
        return res;
    }

    private ListNode merge(ListNode leftHead, ListNode rightHead) {
        if (leftHead == null) return rightHead;
        if (rightHead == null) return leftHead;

        ListNode newHead = null;
        if (leftHead.val <= rightHead.val) {
            newHead = leftHead;
            leftHead = leftHead.next;
        } else {
            newHead = rightHead;
            rightHead = rightHead.next;
        }
        ListNode prev = newHead;
        while (leftHead != null && rightHead != null) {
            if (leftHead.val <= rightHead.val) {
                prev.next = leftHead;
                leftHead = leftHead.next;
            } else {
                prev.next = rightHead;
                rightHead = rightHead.next;
            }
            prev = prev.next;
        }
        if (leftHead != null) {
            prev.next = leftHead;
        }
        if (rightHead != null) {
            prev.next = rightHead;
        }
        return newHead;
    }

    public ListNode getMid(ListNode head) {
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
