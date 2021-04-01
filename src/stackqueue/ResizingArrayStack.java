package stackqueue;

import java.util.Iterator;

/**
 * Created by Ethan-Walker on 2018/7/21.
 */
public class ResizingArrayStack<T> implements Iterable<T> {

    private int top;        // 栈顶指针，新元素待插入位置，也是当前栈中元素个数

    private int maxCount; // 当前数组最大容纳量

    private T[] items;

    public ResizingArrayStack(int length) {
        items = (T[]) new Object[length];
        maxCount = length;
        top = 0;
    }

    public int size() {
        return top;
    }

    public boolean push(T t) {
        if (top == maxCount) {
            resize(2 * maxCount);
        }
        items[top++] = t;
        return true;
    }

    public void resize(int length) {
        T[] maxItem = (T[]) new Object[length];
        for (int i = 0; i < maxCount; i++) {
            maxItem[i] = items[i];
        }
        maxCount = length;
        items = maxItem;
    }

    public T pop() {
        if (top == 0) {
            return null;
        }
        return items[--top];
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator<>();
    }

    class ReverseIterator<T> implements Iterator {

        int i = top;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Object next() {
            return items[--i];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>(20);
        for (int i = 0; i < 30; i++) {
            stack.push("<" + i + ">");
        }
        for (String str : stack) {
            System.out.print(str + " -> ");
        }
        System.out.println();

        for (int i = 31; i < 60; i++) {
            stack.push("<" + i + ">");
        }
        for (String str : stack) {
            System.out.print(str + " -> ");
        }
        System.out.println();
    }
}
