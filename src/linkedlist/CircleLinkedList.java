package linkedlist;

import edu.princeton.cs.algs4.In;

/**
 * Created by lenovo on 2018/7/22.
 * 循环链表 解决约瑟夫问题
 */
public class CircleLinkedList<T> {
    public class Node<T> {
        T value;
        Node<T> next;
    }

    private Node first;  // 附加头结点
    private Node last;   // 尾结点，指向实际元素

    public CircleLinkedList() {
        first = new Node<T>();
        first.next = first;
        last = null;
    }

    public boolean insertHead(T val) {
        Node insertNode = new Node();
        insertNode.value = val;

        insertNode.next = first.next;
        first.next = insertNode;
        if (last == null) {
            last = insertNode;
        }
        return true;
    }

    public boolean insertTail(T val) {
        Node insertNode = new Node();
        insertNode.value = val;

        insertNode.next = first;
        if (last == null) {
            first.next = insertNode;
            last = insertNode;
        } else {
            last.next = insertNode;
            last = insertNode;
        }
        return true;
    }

    public void print() {
        Node p = first.next;
        while (p != first) { //  p == first 说明链表为空 或者循环结束
            System.out.printf("%d -> ", p.value);
            p = p.next;
        }
        System.out.println();
    }

    /*
        public static void main(String[] args) {
            CircleLinkedList<Integer> circle = new CircleLinkedList<>();
            for (int i = 0; i < 6; i++) {
                circle.insertHead(i);
            }
            for (int j = 12; j > 8; j--) {
                circle.insertTail(j);
            }
            circle.print();
        }*/

    /**
     * 约瑟夫环问题
     * @param args
     */
    public static void main(String[] args) {
        CircleLinkedList<Integer> circle = new CircleLinkedList<>();
        int n = 8;
        for (int i = 1; i <= n; i++) {
            circle.insertTail(i);
        }
        circle.print();
        CircleLinkedList<Integer>.Node<Integer> node = circle.new Node<Integer>();
        node = circle.first;
        CircleLinkedList<Integer>.Node<Integer> prev = circle.first;
        int m = 5;

        for (int i = 1; i < n; i++) {
            // 循环n-1 次，直到剩下最后一个节点
            for (int j = 1; j <= m; j++) {
                prev = node;
                node = node.next;
                // 注意附加头结点, 循环的时候遇到附加头结点，忽略并再前进一步
                if (node == circle.first) {
                    prev = node;
                    node = node.next;
                }
            }
            prev.next = node.next;
        }
        circle.print();
    }

}
