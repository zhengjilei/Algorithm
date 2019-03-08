package a_review;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class LinkedStack {

    Node head; // 辅助头结点

    int count;

    public LinkedStack() {
        this.head = new Node(-1);
        count = 0;
    }

    boolean push(int val) {
        Node n = new Node(val);
        n.next = head.next;
        head.next = n;
        count++;
        return true;
    }


    int pop() {
        if (isEmpty())
            throw new RuntimeException("栈为空");
        Node p = head.next;
        head.next = p.next;
        count--;
        return p.val;
    }

    int size() {
        return count;
    }

    int getTop() {
        if (isEmpty())
            throw new RuntimeException("栈为空");
        return head.next.val;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
