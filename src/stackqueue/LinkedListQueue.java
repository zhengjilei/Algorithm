package stackqueue;

public class LinkedListQueue<T> {

    private int count; // 队列中元素数

    private Node<T> head;  // 链首元素,无附加头结点

    private Node<T> tail;  // 链尾元素,只有一个元素时，链首链尾指向同一个元素，没有元素时，都指向null

    public LinkedListQueue() {
        this.count = 0;
        head = null;
        tail = null;
    }

    // 添加到链尾
    public void offer(T t) {
        Node node = new Node();
        node.value = t;
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        count++;
    }

    // 删除并返回链首元素
    public T poll() {
        if (!isEmpty()) {
            T t = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            count--;
            return t;
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }


    private class Node<T> {
        T value;
        Node<T> next;

        public Node() {
            this.value = null;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.offer("<" + i + ">");
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

}
