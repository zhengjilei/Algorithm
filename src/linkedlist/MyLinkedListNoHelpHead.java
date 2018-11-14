package linkedlist;

/**
 * 不带附加头结点的链表
 */
public class MyLinkedListNoHelpHead {
    class Node {
        int value;
        Node next;

        public Node(int val) {
            this.value = val;
            this.next = null;
        }
    }

    int size;
    Node head;

    public MyLinkedListNoHelpHead() {
        head = null;
        size = 0;
    }

    /**
     * 插入到头结点
     *
     * @param val
     * @return
     */
    public boolean insertHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        return true;
    }

    public boolean removeHead() {
        if (head != null) {
            head = head.next;
        }
        return true;
    }

    /**
     * index &lt; size 插入到对应的位置, insert &gt; size 插入到链表末尾
     * @param val
     * @param index
     * @return
     */
    public boolean insert(int val, int index) {
        if (index < 0) return false;
        Node insertNode = new Node(val);
        if (head == null) {
            head = insertNode;
            return true;
        }
        if (index == 0) {
            insertNode.next = head.next;
            head = insertNode;
            return true;
        }
        Node t = head;
        for (int i = 1; i < index; i++) {
            if (t.next == null) {
                break;
            }
            t = t.next;
        }
        insertNode.next = t.next;
        t.next = insertNode;
        size++;
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
