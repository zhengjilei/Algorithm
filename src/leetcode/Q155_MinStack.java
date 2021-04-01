package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/2/23
 */
public class Q155_MinStack {

    private ArrayDeque<Integer> dataStack = new ArrayDeque<>();
    private ArrayDeque<Integer> minStack = new ArrayDeque<>();

    /**
     * initialize your data structure here.
     */
    public Q155_MinStack() {
    }

    public void push(int x) {
        dataStack.push(x);
        if (minStack.isEmpty() || x <= minStack.peekLast()) { // =也要压
            minStack.push(x);
        }
    }

    public void pop() {
        Integer pop = dataStack.pop();
        if (pop == minStack.peekLast()) {
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peekLast();
    }

    public int getMin() {
        return minStack.peekLast();
    }

}
