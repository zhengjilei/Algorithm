package leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2019/2/24
 */
public class Q0138_CopyComplexList {

    public Node copyRandomList(Node head) {

        if (head == null) return null;

        Node t = head.next;
        Node newHead = new Node(head.val, null, null);
        HashMap<Node, Node> map = new HashMap<>();
        map.put(head, newHead);


        Node newTail = newHead;

        while (t != null) {
            Node node = new Node(t.val, null, null);
            map.put(t, node);
            newTail.next = node;
            newTail = node;
            t = t.next;
        }
        t = head;
        Node newT = newHead;
        while (t != null) {
            newT.random = map.get(t.random);
            t = t.next;
            newT = newT.next;
        }

        return newHead;
    }

    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node p = head, q = null;
        while (p != null) {
            q = new Node(p.val, p.next, null);
            p.next = q;
            p = q.next;
        }

        p = head;
        q = p.next;
        while (p != null) {
            if (p.random != null) {
                q.random = p.random.next;
            }
            p = q.next;
            if (q.next != null) {
                q = q.next.next;
            }
        }


        // 拆分成两个链表，奇数作为一个链表，偶数作为另一个

        Node newHead = head.next;
        p = head;
        while (p != null) {
            q = p.next;
            p.next = q.next;
            p = p.next;
            if (q.next != null) {
                q.next = q.next.next;
                q = q.next;
            }
        }

        return newHead;

    }

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }


    @Test
    public void test() {

        Node n1 = new Node(1, null, null);
        Node n2 = new Node(2, null, null);
        Node n3 = new Node(3, null, null);
        Node n4 = new Node(4, null, null);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        n3.random = n2;
        n4.random = n1;

        copyRandomList2(n1);
    }

}
