package stackqueue;

public class LinkedListQueue<T> {

    private int count; // 队列中元素数

    private Node header;

    private Node tail;  // 链尾元素

    public LinkedListQueue() {
        this.count = 0;
        header = new Node();
        tail = header;
    }

    // 添加到链尾
    public void offer(T t) {
        Node node = new Node();
        node.value = t;
        tail.next = node;
        tail = node;
        count++;
    }

    // 删除并返回链首元素
    public T poll() {
        if (!isEmpty()) {
            Node<T> node = header.next;
            T t = node.value;
            header.next = node.next;
            count--;
            return t;
        }
        return null;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private class Node<T> {
        T value;
        Node next;

        public Node() {
            this.value = null;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListQueue<String> queue =new LinkedListQueue<>();
        for(int i=0;i<20;i++){
            queue.offer("<"+i+">");
        }
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

}
