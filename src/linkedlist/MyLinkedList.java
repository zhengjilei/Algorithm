package linkedlist;

import java.util.ArrayDeque;

/**
 * Created by lenovo on 2018/4/2.
 * 带附加头结点的链表
 */
public class MyLinkedList<T> {


    private Node<T> head;
    private int size;

    public MyLinkedList() {
        this.head = new Node<>(null);  // 链表头不放数据
    }

    public Node<T> getHead() {
        return head;
    }

    public boolean insert(T data) {
        Node tmp = head;
        while (true) {
            if (tmp.next == null) {
                tmp.next = new Node(data);
                size++;
                break;
            }
            tmp = tmp.next;
        }
        return true;
    }

    public boolean delete(T data) {
        Node tmp = head.next;

        if (tmp != null && tmp.data.equals(data)) {
            head.next = tmp.next;
            size--;
            return true;
        }
        while (tmp != null) {
            Node previous = tmp;
            tmp = tmp.next;
            Node next = null;

            if (tmp != null) {
                next = tmp.next;
                if (tmp.data.equals(data)) {
                    previous.next = next;
                    size--;
                    return true;
                }

            } else {
                return false;
            }
        }
        return false;
    }

    public boolean update(T olddata, T newdata) {
        Node tmp = head.next;
        while (tmp != null) {
            if (tmp.data.equals(olddata)) {
                tmp.data = newdata;
                return true;
            }
            tmp = tmp.next;
        }
        return true;

    }

    public boolean query(T data) {
        Node tmp = head.next;
        while (tmp != null) {
            if (tmp.data.equals(data)) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }


    public void deleteDuplicateNodes() {
        Node outer = head.next;
        while (outer != null) {
            Node inner = outer;
            Node cur = inner.next;
            while (cur != null) {
                if (outer.data == cur.data) {
                    inner.next = cur.next;
                    cur = cur.next;
                    size--;
                } else {
                    inner = cur;
                    cur = cur.next;
                }
            }
            outer = outer.next;
        }
    }

    /**
     * 反转链表
     */
    public void reverseLinkedList() {
        Node q = head.next;
        Node p = null, r = null;
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        head.next = p;
    }

    public void print() {
        Node cur = head.next;
        while (cur != null) {
            System.out.print(cur.data + "->");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 未知链表长度：获取链表倒数第 k 个节点(双指针法)
     *
     * @param k
     * @return
     */
    public Node<T> getEndK(int k) {

        Node p = head.next;
        Node q = p;
        if (p == null || k <= 0) return null;
        for (int i = 0; i < k; i++) {// p 和 q 相差 k 步
            if (q == null) return null; // 没有倒数第 K 个节点
            else q = q.next;
        }
        while (q != null) {
            q = q.next;
            p = p.next;
        }
        return p;
    }

    public Node getEndK2(int k) {
        if (head == null || k <= 0) return null;
        Node p = head.next, q = head.next;
        if (p == null) return null;

        int i = 0;
        while (i < k && q != null) { // q 向前走 k 步
            q = q.next;
            i++;
        }
        if (i < k) return null; // 总数小于 k 个
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    /**
     * 获取链表中间节点，双数，打印中间两个节点
     * 双指针法，一快一慢
     *
     * @param
     */
    public void printMidNode() {
        Node first = head;

        Node second = head;
        if (second.next == null) {
            // 空链表
            print();
            return;
        }

        // head  1 2 3 4 5
        //  first =1 second = 2
        // first = 2 second = 4
        // first = 3 second = null;


        // head 1 2 3 4
        // first =1 second = 2
        // first =2 second=  4
        while (true) {
            first = first.next;  //2 3 4
            second = second.next.next;//3 5
            if (second != null && second.next == null) {
                // 偶数
                System.out.println(first.data + " & " + first.next.data);
                return;
            } else if (second == null) {
                // 奇数
                System.out.println(first.data);
                return;
            }
        }
    }

    /**
     * 判断单链表是否有环（双指针法 ,  n%节点数 = 2*n%节点数 ， n为迭代次数 ，如果有环，最多迭代次数 == 节点数时，就会相遇)
     *
     * @return
     */
    public boolean hasLoop() {

        Node p = head, q = head.next;
        while (p != q) {
            if (q == null || q.next == null) return false;
            p = p.next;
            q = q.next.next;
        }
        return true;
    }


    /**
     * 利用栈反向打印单向链表
     */
    public void reversePrint() {
        ArrayDeque stack = new ArrayDeque<>();
        if (head == null) return;
        Node p = head.next;
        while (p != null) {
            stack.push(p.data);
            p = p.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    /**
     * 递归反向打印单向链表
     *
     * @param n
     */
    public void reversePrint2(Node n) {
        if (n == null) return;
        if (n.next != null) {
            reversePrint2(n.next);
        }
        System.out.print(n.data+" ");
    }

    public void reversePrint2() {
        if (head == null) return;
        reversePrint2(head.next);
        System.out.println();
    }

    class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            if (this == null) return "null";
            else {
                return this.data + "";
            }
        }
    }
}
