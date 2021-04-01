package stackqueue;

/**
 * 链表实现双端队列
 * created by Ethan-Walker on 2018/12/22
 */
public class LinkedListDeque {

    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node() {
            this.value = null;
            this.next = null;
            this.prev = null;
        }
    }
}
