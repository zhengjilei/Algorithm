package 程序员代码面试指南;

import org.junit.Test;

/**
 * 删除中间节点
 * created by Ethan-Walker on 2018/12/20
 */
public class Q013_DeleteMidNode {

    /**
     * 偶数个节点时，删除中间两个节点的第一个
     *
     * @param node
     * @return
     */
    public ListNode deleteMidNode(ListNode node) {
        if (node == null) return null;
        ListNode fast = node.next, slow = node;

        ListNode slowPrev = null; // 保存 slow 节点的前一个节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slowPrev = slow;
            slow = slow.next;
        }
        // 奇数个节点，slow 指向中间
        // 偶数个节点, slow 指向中间两个节点的前一个

        if (slowPrev == null) {
            // 删除的是链表头节点
            return slow.next;
        }

        slowPrev.next = slow.next;
        return node;
    }


    public ListNode getMidNode(ListNode node) {
        if (node == null) return null;
        ListNode fast = node, slow = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 奇数个节点，slow 指向中间
        // 偶数个节点, slow 指向中间两个节点的后一个

        return slow;
    }

    public ListNode getMidNode2(ListNode node) {
        if (node == null) return null;
        ListNode fast = node.next, slow = node; // fast 初始时 先走一步

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 奇数个节点，slow 指向中间
        // 偶数个节点, slow 指向中间两个节点的前一个

        return slow;
    }

    public void print(ListNode node) {
        if (node == null) {
            System.out.println("链表为空");
            return;
        }
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();
    }

    @Test
    public void test() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

//        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.next = e;
        ListNode listNode = deleteMidNode(a);
        print(listNode);

    }
}
