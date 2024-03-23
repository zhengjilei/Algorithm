package leetcode;

import java.util.Stack;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q0232_QueueByTwoStack {

    Stack<Integer> offerStack;
    Stack<Integer> pollStack;

    /**
     * Initialize your data structure here.
     */
    public Q0232_QueueByTwoStack() {
        offerStack = new Stack<>();
        pollStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        offerStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (!pollStack.isEmpty()) {
            return pollStack.pop();
        } else {
            while (!offerStack.isEmpty()) {
                pollStack.push(offerStack.pop());
            }
            return pollStack.pop();
        }
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!pollStack.isEmpty()) {
            return pollStack.peek();
        } else {
            while (!offerStack.isEmpty()) {
                pollStack.push(offerStack.pop());
            }
            return pollStack.peek();
        }
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        if (pollStack.isEmpty() && offerStack.isEmpty())
            return true;
        return false;
    }
}
