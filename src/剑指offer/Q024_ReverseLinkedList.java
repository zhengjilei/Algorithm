package 剑指offer;

/**
 * created by Ethan-Walker on 2018/12/6
 */
public class Q024_ReverseLinkedList {

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null, current = head, next;

        while (current != null) { // 将 current 指向上一个节点
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
