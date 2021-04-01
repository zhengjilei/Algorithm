package leetcode2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Q232 {

    Deque<Integer> pushStack;
    Deque<Integer> popStack;

    /**
     * Initialize your data structure here.
     */
    public Q232() {
        pushStack = new ArrayDeque<>();
        popStack = new ArrayDeque<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        pushStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (!popStack.isEmpty()) {
            return popStack.pop();
        } else {
            while (pushStack.size() > 1) {
                popStack.push(pushStack.pop());
            }
            return pushStack.pop();
        }
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!popStack.isEmpty()) {
            return popStack.peek();
        } else {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
            return popStack.peek();
        }
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}
