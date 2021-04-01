package linkedlist;

public class DoubleLinkedList {

    private Node head; // 附加头结点

    public DoubleLinkedList() {
        head = new Node();
        head.next = head;
        head.prev = head;
    }

    public boolean insertHead(int val) {
        Node insertNode = new Node(val);

        Node headNext = head.next;
        insertNode.next = headNext;
        head.next = insertNode;

        headNext.prev = insertNode;
        insertNode.prev = head;

        return true;
    }

    public boolean insertTail(int val) {
        Node p = head;
        while (p.next != head) {
            p = p.next;
        }
        // p.next = head

        Node insertNode = new Node(val);
        insertNode.next = head;
        p.next = insertNode;

        head.prev = insertNode;
        insertNode.prev = p;
        return true;

    }

    public boolean insertIndex(int val, int index) {
        if (index < 0) return false;
        Node insertNode = new Node(val);
        Node p = head; // 待插入位置的节点 的前一个节点
        while (index > 0) {
            if (p.next == head) {
                return false;// 说明 index 超过范围
            } else {
                index--;
                p = p.next;
            }
        }

        insertNode.next = p.next;
        p.next = insertNode;

        insertNode.prev = p;
        insertNode.next.prev = insertNode;
        return true;
    }

    public boolean removeIndex(int index) {
        if (index < 0) return false;

        Node p = head; // 待删除节点的前一个节点
        while (index > 0) {
            if (p.next == head) {
                return false; // 索引超过范围
            } else {
                index--;
                p = p.next;
            }
        }
        //p 指向待删除节点的前一个节点
        Node q = p.next; // q 为待删除节点
        Node r = q.next;

        p.next = r;
        r.prev = p;
        return true;
    }

    private class Node {
        int val;
        Node next;
        Node prev;

        public Node() {
            this.next = null;
            this.prev = null;
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    public void print() {
        Node p = head.next;
        while (p != head) {
            System.out.printf("%d -> ", p.val);
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        DoubleLinkedList dll = new DoubleLinkedList();

        for (int i = 0; i < 5; i++) {
            dll.insertHead(i);
        }
        for (int i = 5; i < 10; i++) {
            dll.insertTail(i);
        }
        dll.print();

        dll.removeIndex(3);
        dll.removeIndex(8);
        dll.removeIndex(0);
        dll.print();
        dll.removeIndex(10);
        dll.print();


    }
}
