package 程序员代码面试指南;

/**
 * 删除链表中等于 num  的值，可能有多个 num，全部删除
 * created by Ethan-Walker on 2018/12/24
 */
public class Q023_DeleteNode {

    public ListNode delete(ListNode head, int num) {

        if (head == null) return null;


        ListNode newNode = head;

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
