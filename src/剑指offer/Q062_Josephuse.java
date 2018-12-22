package 剑指offer;

import org.junit.Test;

/**
 * 约瑟夫环
 *
 * <p>
 * created by Ethan-Walker on 2018/12/15
 */
public class Q062_Josephuse {

    /**
     * 方法一：循环链表法
     * 时间复杂度 O(nm)  n 为链表长度, m 为删除第 m 个数字
     * 空间复杂度 O(n)
     *
     * @param n
     * @param m
     * @return
     */
    public int last1Number1(int n, int m) {
        if (n < 1 || m < 1) return -1;

        CircleLinkedList list = new CircleLinkedList();
        for (int i = 0; i < n; i++) {
            list.insert(i);
        }
        System.out.print("删除前链表: ");
        list.print();

        ListNode node = list.head;
        while (list.length > 1) {
            node = list.remove(node, m);
        }

        System.out.print("删除后链表: ");
        list.print();
        return node.val;
    }


    /**
     * 公式法
     * f(n,m) 表示从下标依次为 0,1,..,n-1 的n个元素中删除第 m 个数，最后剩下的数的下标
     * f(n,m) = (f(n-1,m)+ m)) %n
     * m 不变，n 从1 到 n
     * t[1] = 0 (只有一个元素，返回下标位置0)
     * t[2] = t[1]+m  %2
     * t[3] = t[2]+m  %3
     *
     * @param n
     * @param m
     * @return
     */
    public int last1Number2(int n, int m) {
        if (n < 1 || m < 1) return -1;

        int last = 0;
        for (int i = 2; i <= n; i++) { // 依次求 t[i], 循环 n-1 趟，去掉 n-1 个数
            last = (last + m) % i;
        }
        return last;
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();

        System.out.println(last1Number1(4000, 997));
        ;
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(last1Number2(4000, 997));
        System.out.println(System.currentTimeMillis() - start);

    }
}

class CircleLinkedList {
    ListNode head; // 无附加结点
    ListNode tail; // tail 指向链尾元素
    int length;

    public void insert(int val) {
        if (length == 0) {
            head = new ListNode(val);
            tail = head;
            tail.next = head;
        } else {
            ListNode node = new ListNode(val);
            tail.next = node;
            tail = node;
            tail.next = head;
        }
        length++;
    }

    /**
     * 从节点 node 开始数，删除第 index 个节点
     * 返回删除节点的下一个节点
     *
     * @param index
     */
    public ListNode remove(ListNode node, int index) {
        if (index <= 0) return null;
        if (length > 1) { // 链表长度小于等于 1 时 不删除
            if (index > 1) {
                for (int i = 1; i < index - 1; i++) {
                    node = node.next;
                }
                if (node.next == head) {
                    head = head.next;
                }
                // node 指向待删除节点的前趋节点
                node.next = node.next.next;
                length--;

                return node.next;
            } else {
                // index=1 删除当前 node 节点
                if (node == head) {
                    head = head.next;
                }
                // 找到 node  的 前趋节点
                ListNode n = node.next;
                while (n.next != node) {
                    n = n.next;
                }
                n.next = node.next;
                length--;
                return n.next;
            }
        }
        return null;
    }

    public void print() {
        if (head == null) return;
        ListNode node = head;
        System.out.print(node.val);
        node = node.next;
        while (node != head) {
            System.out.print("->" + node.val);
            node = node.next;
        }
        System.out.println();
    }

}
