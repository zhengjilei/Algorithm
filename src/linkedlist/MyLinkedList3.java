package linkedlist;

public class MyLinkedList3 {

    private Node head;
    private int size;


    public MyLinkedList3() {
        this.head = new Node();
        this.size = 0;
    }

    /**
     * 头插法
     *
     * @return
     */
    public boolean insertHead(int val) {
        Node n = new Node(val);
        n.next = head.next;
        head.next = n;
        return true;
    }

    public boolean removeHead() {
        if (head.next != null) {
            head.next = head.next.next;
        }
        return true;
    }

    public boolean insertIndex(int val, int index) {
        if (index < 0) return false;
        Node insertNode = new Node(val);
        Node p = head;  // p 指向插入位置的节点的前一个节点, 如果 p == null  说明插入位置超过范围
        while (index > 0) {
            // 指向 index 次
            if (p != null) {
                p = p.next;
                index--;
            } else break;
        }
        if (p == null) return false;
        insertNode.next = p.next;
        p.next = insertNode;
        size++;
        return true;
    }

    public boolean removeIndex(int index) {
        if (index < 0) return false;
        Node t = head;
        Node p = head, q = head.next;  // p 指向删除位置元素的前一个节点，q 指向删除位置的元素
        while (index > 0) {
            if (q != null) {
                p = q;
                q = q.next;
                index--;
            } else
                break;
        }
        if (q == null) return false;// index 指向的节点为空

        p.next = q.next;
        return true;
    }

    /**
     * 尾插法
     *
     * @param val
     * @return
     */
    public boolean insertTail(int val) {
        Node t = head;
        while (t.next != null) {
            t = t.next;
        }
        t.next = new Node(val);
        return true;
    }

    public void print() {
        Node n = head.next;
        while (n != null) {
            System.out.printf("%d -> ", n.val);
            n = n.next;
        }
        System.out.println();
    }

    class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        MyLinkedList3 linkedList = new MyLinkedList3();
        linkedList.removeIndex(0);
        linkedList.removeIndex(1);
        for (int i = 0; i < 5; i++) {
            linkedList.insertHead(i);
        }
        linkedList.removeIndex(1);

        linkedList.print();
        linkedList.removeIndex(0);
        linkedList.print();

        linkedList.insertIndex(11, 3);
        linkedList.insertIndex(22, 0);
        linkedList.insertIndex(33, 33);
        linkedList.print();

        linkedList.removeIndex(10);
        linkedList.print();

        linkedList.insertTail(44);
        linkedList.insertTail(55);
        //22 -> 4 -> 3 -> 2 -> 11 -> 1 -> 0 -> 33 -> 44 -> 55 ->
        linkedList.print();
    }

}
