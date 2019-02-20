package a_review.sort;

import leetcode.ListNode;
import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class ListInsertSort {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;


        ListNode insertNode = head.next, nextInsertNode;
        ListNode prevHead = head;  // 已经排好序的部分

        // 已经排好序的部分一定要和 后面断开
        prevHead.next = null;

        while (insertNode != null) {
            nextInsertNode = insertNode.next; // 保存下一个插入节点
            insertNode.next = null;     // 将当前插入节点和后面的链表断开

            // 寻找第一个大于等于 insertNode 值的节点t 和其前趋节点 prevT
            ListNode t = prevHead, prevT = null;
            while (t != null && t.val <= insertNode.val) {
                prevT = t;
                t = t.next;
            }

            if (prevT == null) { // prevT 表明第一个节点就比 insertNode 大，insertNode 应插入到第一个节点
                insertNode.next = prevHead;
                prevHead = insertNode; // 更新 prevHead
            } else {
                // t 指向第一个大于等于 insertNode 的节点，或者 指向 Null
                insertNode.next = t;
                prevT.next = insertNode;
            }
            insertNode = nextInsertNode;
        }
        return prevHead;
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode head = sortList(l1);

        print(head);
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
}
