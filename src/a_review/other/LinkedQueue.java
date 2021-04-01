package a_review.other;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class LinkedQueue {


    Node head;  // 辅助头结点
    Node tail;  // 尾节点

    int count;

    public LinkedQueue() {
        this.head = new Node(-1);
        this.tail = this.head;
        this.count = 0;
    }


    void offer(int val) {
        tail.next = new Node(val);
        tail = tail.next;
        count++;
    }

    int poll() {
        if (count == 0)
            throw new RuntimeException("队列为空");
        Node t = head.next;
        if (t == tail) {   // 注意：如果只有一个值，意味着 tail 被删除了，让 tail 指向head
            tail = head;
        }
        head.next = t.next;
        count--;
        return t.val;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int peek() {
        if (count == 0)
            throw new RuntimeException("队列为空");

        return head.next.val;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
