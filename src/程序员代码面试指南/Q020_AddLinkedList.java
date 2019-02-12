package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 链表 9->3->7    6->3 相加得到
 * 1->0->0->0
 * created by Ethan-Walker on 2018/12/23
 */
public class Q020_AddLinkedList {


    /**
     * 反转链表
     * 细节： 进位，最高位进1
     * 进位可用标志位表示，也可 val/10
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode add(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return null;
        }

        // 反转两个链表
        list1 = reverse(list1);
        list2 = reverse(list2);

        ListNode newListHead = null, prev = null;
        int up = 0; // 是否进位
        int val = 0;
        while (list1 != null || list2 != null) {
            val = up;
            if (list1 != null) {
                val += list1.val;
                list1 = list1.next;
            }
            if (list2 != null) {
                val += list2.val;
                list2 = list2.next;

            }

            up = val / 10; // 1 （表示有进位）或者 0
            ListNode node = new ListNode(val % 10);
            if (newListHead == null) {
                newListHead = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
        }

        // 最高位进1
        if (up == 1) {
            prev.next = new ListNode(1);
        }

        newListHead = reverse(newListHead);

        return newListHead;

    }

    public ListNode reverse(ListNode node) {

        ListNode prev = null, next = null;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }


    /**
     * 用栈解决
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode add2(ListNode list1, ListNode list2) {

        ArrayDeque<Integer> stack1 = new ArrayDeque<>();
        ArrayDeque<Integer> stack2 = new ArrayDeque<>();

        ArrayDeque<Integer> result = new ArrayDeque<>();

        while (list1 != null) {
            stack1.push(list1.val);
            list1 = list1.next;
        }
        while (list2 != null) {
            stack2.push(list2.val);
            list2 = list2.next;
        }

        int up = 0;
        int val = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            val = up;
            if (!stack1.isEmpty()) {
                val += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                val += stack2.pop();
            }

            up = val / 10;

            result.push(val % 10);
        }

        if (up == 1) {
            result.push(1);
        }

        ListNode resultHead = new ListNode(result.pop()), node = null;

        ListNode prev = resultHead;
        while (!result.isEmpty()) {
            node = new ListNode(result.pop());
            prev.next = node;
            prev = node;
        }
        return resultHead;
    }

    @Test
    public void test() {
        ListNode a1 = new ListNode(9);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(7);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(6);
        ListNode b2 = new ListNode(3);
        b1.next = b2;


        ListNode result = add2(a1, b1);
        ListUtil.print(result);

    }

}
