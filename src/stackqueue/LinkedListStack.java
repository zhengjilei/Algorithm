package stackqueue;

/**
 * 链式栈 ：头插法
 * Created by Ethan-Walker on 2018/7/21.
 */
public class LinkedListStack<T> {

    private int nowNum;  // 当前栈中节点数量

    private Node head;

    public LinkedListStack() {
        nowNum = 0;
        head = new Node();  // 头结点为空
    }

    /**
     * 头插法
     */
    public void push(T t) {
        Node<T> node = new Node<>();
        node.item = t;
        node.next = head.next;
        head.next = node;
        nowNum++;
    }

    /**
     * 删除并返回第一个结点
     */
    public T pop() {
        if (!isEmpty()) {
            Node<T> node = head.next;
            head.next = node.next;
            nowNum--;
            return node.item;
        } else {
            return null;
        }
    }

    public int size() {
        return nowNum;
    }

    public boolean isEmpty() {
        return nowNum == 0;
    }

    private class Node<T> {
        T item;
        Node next;

        public Node() {
            this.item = null;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        for (int i = 0; i < 20; i++) {
            stack.push("<" + i + ">");
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
