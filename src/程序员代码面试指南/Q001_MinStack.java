package 程序员代码面试指南;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/12/16
 */
public class Q001_MinStack {

    ArrayDeque<Integer> dataStack;
    ArrayDeque<Integer> minStack;

    public Q001_MinStack() {
        this.dataStack = new ArrayDeque<>();
        this.minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        dataStack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public int pop() {
        if (this.dataStack.isEmpty()) {
            throw new RuntimeException("stack is empty!");
        }
        minStack.pop();
        return dataStack.pop();
    }

    public int getMin() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("stack is empty!");
        }
        return minStack.peek();
    }


}
