package 剑指offer;

import java.util.Arrays;

/**
 * 双栈实现队列
 * created by Ethan-Walker on 2018/12/2
 */
public class Q009_QueueByTwoStack<T> {

    MStack<T> pushStack;
    MStack<T> popStack;

    public Q009_QueueByTwoStack() {
        pushStack = new MStack<>();
        popStack = new MStack<>();
    }

    public void offer(T t) {
        // 插入队尾
        pushStack.push(t);
    }

    public T poll() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return (T) null;
        }
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        return popStack.pop();
    }

    public boolean isEmpty() {
        return popStack.isEmpty() && pushStack.isEmpty();
    }

    public static void main(String[] args) {
        Q009_QueueByTwoStack<String> queue = new Q009_QueueByTwoStack<>();
        System.out.println(queue.poll());
        queue.offer("abcd");
        queue.offer("fr");
        queue.offer("21");
        queue.offer("frg");
        queue.poll();

        queue.offer("12de");
        queue.offer("d21");

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }


    class MStack<T> {
        T[] items;
        int maxSize;
        int top; // top 指向待插入元素位置
        final int DEFAULT_SIZE = 3;

        public MStack() {
            this.maxSize = DEFAULT_SIZE;
            items = (T[]) new Object[maxSize];
            top = 0;
        }

        public void push(T t) {
            if (top == maxSize) {
                maxSize = 2 * maxSize + 1;
                items = Arrays.copyOf(items, maxSize); // 扩大数组
            }
            items[top++] = t;
            return;
        }

        public T pop() {
            if (top == 0) return (T) null;
            return items[--top];
        }

        public boolean isEmpty() {
            return top == 0;
        }
    }
}
