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

        // head 后面断开，保证已排好序的链尾是 null.
        // 例如 1->4 ->3 , 将 4 插入，如果1后面不设为 null, 会导致 4的插入位置遍历到 3 后面
        head.next = null;

        while (insertNode != null) {
            nextInsertNode = insertNode.next; // 保存下一个插入节点

            // 寻找第一个大于 insertNode 值的节点t 和其前趋节点 prev
            ListNode t = head, prev = null;
            while (t != null && t.val <= insertNode.val) {
                prev = t;
                t = t.next;
            }

            if (prev == null) { // prevT ==null 表明第一个节点就比 insertNode 大，insertNode 应插入到第一个节点
                insertNode.next = head;
                head = insertNode; // 更新 prevHead
            } else {
                // t 指向第一个大于等于 insertNode 的节点，或者 指向 Null
                insertNode.next = t;
                prev.next = insertNode;
            }
            insertNode = nextInsertNode;
        }
        return head;
    }


    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode insert = head.next;
        head.next = null;
        ListNode cur, prev, nextInsert;
        while (insert != null) {
            nextInsert = insert.next;

            cur = head;
            prev = null;
            while (cur != null && cur.val <= insert.val) {
                prev = cur;
                cur = cur.next;
            }
            insert.next = cur;
            if (prev == null) {
                head = insert;
            } else {
                prev.next = insert;
            }
            insert = nextInsert;
        }

        return head;

    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
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
