package bytedance;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/2/12
 */
public class Q148_SortLinkedList {

    public ListNode sortList(ListNode head) {
        return mergeSort(head);

    }

    public ListNode mergeSort(ListNode l) {

        if (l == null || l.next == null) return l;
        ListNode midNode = getMidNode(l);

        ListNode right = mergeSort(midNode.next);
        midNode.next = null;
        ListNode left = mergeSort(l);

        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        if (left == null) return right;
        else if (right == null) return left;

        ListNode res = null;
        if (left.val <= right.val) {
            res = left;
            left = left.next;
        } else {
            res = right;
            right = right.next;
        }
        ListNode prev = res;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                prev.next = left;
                prev = left;
                left = left.next;
            } else {
                prev.next = right;
                prev = right;
                right = right.next;
            }
        }
        while (left != null) {
            prev.next = left;
            prev = left;
            left = left.next;
        }
        while (right != null) {
            prev.next = right;
            prev = right;
            right = right.next;
        }
        return res;
    }


    public ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
