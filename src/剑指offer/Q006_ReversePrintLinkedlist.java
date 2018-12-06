package 剑指offer;

import java.util.ArrayDeque;

/**
 * 反向打印单向链表
 * created by Ethan-Walker on 2018/12/2
 */
public class Q006_ReversePrintLinkedlist {


    /**
     * 带有附加头结点的链表
     * <p>
     * 方法一：若能修改原链表，可以先反转原链表，然后顺序输出
     *
     * @param head
     */
    public void reversePrint(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode p = null, q = head.next, r;

        while (q.next != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        head.next = q;

        while (q != null) {
            System.out.print(q.val + " <= ");
            q = q.next;
        }
    }


    /**
     * 栈实现单向链表反向输出
     *
     * @param head
     */
    public void stackPrint(ListNode head) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        if (head == null) return;
        ListNode p = head.next;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public void recurPrint(ListNode n) {
        if (n == null) return;
        if (n.next != null) {
            recurPrint(n.next);
        }

        System.out.print(n.val);
    }


}
