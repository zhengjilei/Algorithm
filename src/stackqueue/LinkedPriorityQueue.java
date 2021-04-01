package stackqueue;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 */
public class LinkedPriorityQueue implements Iterable {

    private Node header;
    private Node tail;
    private int size;

    LinkedPriorityQueue() {
        header = tail = new Node(0);
        size = 0;
    }


    public void offer(int val) {
        Node temp = header.next, p = header;
        while (temp != null && val > temp.value) {
            p = temp;
            temp = temp.next;
        }
        if (temp == null) {
            // 插入链表尾部
            offerLast(val);
            return;
        }
        //val > temp.value 插入 p和 temp 之间
        Node node = new Node(val);
        node.next = temp;
        p.next = node;
        size++;
    }

    private void offerLast(int val) {
        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }

    public int poll() {
        // 返回并删除第一个节点
        Node p = header.next;
        int val;
        if (p != null) {
            header.next = p.next;
            val = p.value;
            size--;
            return val;
        }
        return -1;
    }

    public static void main(String[] args) {
        LinkedPriorityQueue pq = new LinkedPriorityQueue();
        for (int i = 0; i < 20; i++) {
            pq.offer((int) (Math.random() * 100)); // [0,100)之间的整数
        }
        Iterator iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%d ", iterator.next());
        }
        System.out.println();
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator {

        int count = size;
        Node t = header;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Integer next() {
            t = t.next;
            if (t != null) {
                count--;
                return t.value;
            }
            return -1;
        }
    }

    class Node {
        int value;
        Node next;

        public Node(int val) {
            this.value = val;
        }
    }
}
