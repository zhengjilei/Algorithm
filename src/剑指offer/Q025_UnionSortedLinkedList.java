package 剑指offer;

/**
 * 合并两个递增排序的链表
 * created by Ethan-Walker on 2018/12/6
 */
public class Q025_UnionSortedLinkedList {

    public ListNode union(ListNode a, ListNode b) {
        // 其中一个链表为空, 返回非空的链表
        if (a == null || b == null) return a == null ? b : a;
        ListNode head = null;
        ListNode tail = null;
        if (a.val <= b.val) {
            head = a;
            a = a.next;
        } else {
            head = b;
            b = b.next;
        }
        tail = head; // 从指向新链表的尾结点
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;

        }

        while (a != null) {
            tail.next = a;
            tail = tail.next;
            a = a.next;
        }

        while (b != null) {
            tail.next = b;
            tail = tail.next;
            b = b.next;
        }
        tail.next = null;
        return head;
    }

    /**
     * 递归合并
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode merge(ListNode a, ListNode b) {

        if (a == null || b == null) return a == null ? b : a;

        ListNode head = null;
        if (a.val <= b.val) {
            head = a;
            a = a.next;
        } else {
            head = b;
            b = b.next;
        }
        head.next = merge(a, b);
        return head;
    }
}
