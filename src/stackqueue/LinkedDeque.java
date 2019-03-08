package stackqueue;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class LinkedDeque {

    Node head;  // 辅助节点
    Node tail;

    int count;

    public LinkedDeque() {
        this.head = new Node(-1);
        this.tail = head;
        this.count = 0;
    }

    public void offerLast(int val) {
        Node n = new Node(val);
        tail.next = n;
        n.prev = tail;
        tail = tail.next;
        count++;
    }

    public void offerFirst(int val) {
        Node n = new Node(val);
        n.next = head.next;
        if (head.next != null) {
            head.next.prev = n;
        }
        head.next = n;
        n.prev = head;

        if (tail == head) {  //
            tail = n;
        }
        count++;
    }


    public int pollLast() {
        if (count == 0)
            throw new RuntimeException("队列为空");
        int res = tail.val;
        tail = tail.prev;

        tail.next.prev = null;
        tail.next = null;
        count--;
        return res;
    }

    public int pollFirst() {
        if (count == 0) {
            throw new RuntimeException("队列为空");
        }

        Node poll = head.next;
        head.next = poll.next;
        if (poll.next != null) {
            poll.next.prev = head;
        }
        poll.next = null;
        poll.prev = null;
        count--;
        return poll.val;
    }

    public int peekFirst() {
        if (count == 0) {
            throw new RuntimeException("队列为空");
        }
        int val = head.next.val;
        return val;
    }

    public int peekLast() {
        if (count == 0) {
            throw new RuntimeException("队列为空");
        }
        return tail.val;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }


    @Test
    public void test() {
        LinkedDeque deque = new LinkedDeque();
        for (int i = 0; i < 10; i++) {
            deque.offerLast(i);
        }
        for (int j = 10; j < 15; j++) {
            deque.offerFirst(j);
        }
        System.out.println(deque.pollLast());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());

        System.out.println(deque.pollLast());
        while (!deque.isEmpty()) {
            System.out.print(deque.pollFirst() + " , ");
        }
    }
}
