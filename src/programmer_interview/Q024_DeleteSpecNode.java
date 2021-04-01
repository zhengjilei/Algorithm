package programmer_interview;

/**
 * 删除链表中所有等于 num  的值，可能有多个 num，全部删除
 * created by Ethan-Walker on 2018/12/25
 */
public class Q024_DeleteSpecNode {

    public ListNode delete(ListNode head, int num) {

        if (head == null) return null;

        ListNode newNode = head;

        // 找到第一个不等于 num 的节点，将其设为新的头结点
        while (newNode != null && newNode.val == num) {
            newNode = newNode.next;
        }
        if (newNode == null) return null;

        head = newNode;
        ListNode node = head.next, prev = head;

        while (node != null) {
            if (node.val == num) {
                prev.next = node.next;
            } else {
                prev = node;
            }
            node = node.next;
        }
        return head;

    }
}
