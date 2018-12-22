package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/22
 */
public class Q016_Josephus {


    /**
     * 循环链表解决
     *
     * @param head
     * @param m
     * @return
     */
    public ListNode josephus(ListNode head, int m) {
        if (head == null) return head;

        ListNode node = head, prev = null;
        if (m == 1) {
            prev = getPrev(head);
        }

        int cnt = 1;
        while (node.next != node) {
            cnt = 1;
            while (cnt < m) {
                prev = node;
                node = node.next;
                cnt++;
            }
            // node 就是待删除节点
            prev.next = node.next;
            node = node.next;  // 下一次数 1 的节点
        }
        return node;

    }

    public ListNode getPrev(ListNode head) {
        ListNode prev = head;
        while (prev.next != head) {
            prev = prev.next;
        }
        return prev;
    }

    public ListNode josephus2(ListNode head, int m) {

        int n = getLength(head);
        if (n == 0 || m < 1) return null;
        int last = 0;

        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }

        // 求得的last 为要删除的节点在链表中的索引，从0开始计数

        while (last > 0) {
            head = head.next;
        }
        return head;
    }

    public int getLength(ListNode head) {
        if (head == null) return 0;
        ListNode node = head.next;
        int count = 1;
        while (node != head) {
            count++;
            node = node.next;
        }
        return count;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
//        node1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node1;

        System.out.println(josephus(node1, 9));
    }

}
