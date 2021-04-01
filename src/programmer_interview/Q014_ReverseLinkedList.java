package programmer_interview;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/20
 */
public class Q014_ReverseLinkedList {

    /**
     * 反转单向链表
     *
     * @param head
     * @return
     */
    public ListNode reverseSingle(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = null, q = head, r = null;
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
    }

    /**
     * 反转双向链表
     *
     * @param head
     * @return
     */
    public DualListNode reverseDual(DualListNode head) {

        if (head == null || head.next == null) return head;
        DualListNode p = null, q = head, r = null;
        while (q != null) {
            // 处理节点 q.next 和 p.prev
            r = q.next;
            q.next = p;
            if (p != null) {
                p.prev = q;
            }
            p = q;
            q = r;
        }
        // 最后一个节点的 prev 未处理
        p.prev = null;
        return p;
    }

    public void print(DualListNode node) {
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
        DualListNode a = new DualListNode(1);
        DualListNode b = new DualListNode(2);
        DualListNode c = new DualListNode(3);
        DualListNode d = new DualListNode(4);
        DualListNode e = new DualListNode(5);

//        a.next = b; b.prev = a;
//        b.next = c; c.prev = b;
//        c.next = d; d.prev = c;
//        d.next = e; e.prev = d;

        DualListNode dual = reverseDual(a);
        print(dual);
        dual = reverseDual(dual);
        print(dual);
    }

}
