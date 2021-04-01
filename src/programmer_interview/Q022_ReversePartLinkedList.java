package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 单链表每k个节点逆序
 * created by Ethan-Walker on 2018/12/23
 */
public class Q022_ReversePartLinkedList {


    /**
     * 用双端队列辅助，每 k 个节点反向弹出
     * 最后剩下的 不满k个节点，顺序弹出
     * 时间复杂度: O(n)
     * 空间复杂度: O(k)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseK1(ListNode head, int k) {

        if (head == null || head.next == null || k <= 1) return head;

        ArrayDeque<ListNode> deque = new ArrayDeque<>();

        ListNode node = head, next = null;
        ListNode newHead = null, prev = null;
        while (node != null) {
            deque.offerLast(node); // 按照栈的方式添加

            next = node.next;
            node.next = null; // 断掉原先的链表下一个节点

            if (deque.size() == k) {
                if (newHead == null) {
                    newHead = deque.pollLast();
                    prev = newHead;
                }
                while (!deque.isEmpty()) {
                    prev.next = deque.pollLast();
                    prev = prev.next;
                }
            }
            node = next;
        }

        // 多出的节点（相对栈）反向弹，弹出链尾，而不是链头
        if (!deque.isEmpty()) {
            if (newHead == null) {
                newHead = deque.pollFirst();
                prev = newHead;
            }
            while (!deque.isEmpty()) {
                prev.next = deque.pollFirst();
                prev = prev.next;
            }
        }


        return newHead;
    }

    @Test
    public void test() {
        ListNode a0 = new ListNode(0);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);

        a0.next = a1;
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = a7;

        ListNode listNode = reverseK2(a0, 3);
        ListUtil.print(listNode);

    }


    public ListNode reverseK2(ListNode head, int k) {

        if (head == null || head.next == null || k <= 1) return head;
        ListNode prev = null, start = head, next = null;
        int count = 1;
        ListNode cur = head;
        while (cur != null) {
            next = cur.next; // 保存当前节点的下一个节点
            if (count == k) {
                start = prev == null ? head : prev.next;
                head = prev == null ? cur : head;
                reversePart(prev, start, cur, next);
                prev = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    public void reversePart(ListNode prev, ListNode start, ListNode end, ListNode endRight) {
        ListNode p = null, q = start, r = null;
        while (q != endRight) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }

        if (prev != null) prev.next = end;
        start.next = endRight;
    }

}
