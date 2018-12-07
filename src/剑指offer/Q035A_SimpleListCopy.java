package 剑指offer;

import org.junit.Test;

/**
 * 简单链表复制
 * created by Ethan-Walker on 2018/12/7
 */
public class Q035A_SimpleListCopy {


    public ListNode copy(ListNode root) {
        if (root == null) return null;
        ListNode head = new ListNode(root.val);
        ListNode node = null, prev = head;
        ListNode tmp = root.next;
        while (tmp != null) {
            node = new ListNode(tmp.val);
            prev.next = node;
            prev = node;
            tmp = tmp.next;
        }
        prev.next = null;
        return head;
    }

    @Test
    public void test() {
        ListNode root = new ListNode(10);
        ListNode node = root;
        node.next = new ListNode(12);
        node = node.next;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(1);
        node = node.next;
        node.next = new ListNode(43);
        node = node.next;

        ListNode head = copy(root);
        while (head != null) {
            System.out.printf("%5d", head.val);
            head = head.next;
        }
        System.out.println();
    }

}
