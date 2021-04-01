package programmer_interview;

import org.junit.Test;

/**
 * 反转部分单向链表
 * created by Ethan-Walker on 2018/12/20
 */
public class Q015_ReversePartOfLL {

    public ListNode reversePart(ListNode head, int from, int to) {

        if (from > to) throw new RuntimeException(" from to 值不正确");
        if (from == to) return head;
        int length = getLength(head);
        if (length < to) {
            throw new RuntimeException("  to 值超过链表长度");
        }
        ListNode start = null, end = null;
        ListNode startPrev = null, endNext = null;
        ListNode node = head;

        int cnt = 0;
        while (node != null) {
            cnt++;
            if (cnt < from) {
                startPrev = node;
            } else if (cnt == from) {
                start = node;
            } else if (cnt == to) {
                end = node;
                endNext = node.next;
                break;
            }

            node = node.next;
        }

        ListNode p = endNext, q = start, r = null;
        while (q != endNext) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        if (startPrev == null) {
            // from 从开头开始
            return p;
        } else {
            startPrev.next = p;
            return head;
        }
    }

    public int getLength(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
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

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        print(a);
        ListNode result = reversePart(a, 1, 2);
        print(result);
    }

}
