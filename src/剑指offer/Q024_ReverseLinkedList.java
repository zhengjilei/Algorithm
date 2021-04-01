package 剑指offer;

/**
 * 反转链表
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


    /**
     * 递归实现反转链表
     *
     * @return
     */
    public ListNode reverseRecur(ListNode node) {
        if (node == null || node.next == null) return node;

        ListNode head = reverseRecur(node.next); // 逆转 node  node.next, 并返回新链表的头结点
        node.next.next = node;
        node.next = null;
        return head;
    }
}
