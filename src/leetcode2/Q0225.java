package leetcode2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Q0225 {

    Deque<Integer> q1;
    Deque<Integer> q2;

    /**
     * Initialize your data structure here.
     */
    public Q0225() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        if (!q2.isEmpty()) {
            q2.offer(x);
        } else {
            q1.offer(x);
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        if (q1.isEmpty() && q2.isEmpty()) {
            throw new RuntimeException("栈中元素为空");
        } else {
            Deque<Integer> empty = q1;
            Deque<Integer> notEmpty = q2;
            if (!q1.isEmpty()) {
                empty = q2;
                notEmpty = q1;
            }
            while (notEmpty.size() > 1) {
                empty.offer(notEmpty.poll());
            }
            return notEmpty.poll();
        }

    }

    /**
     * Get the top element.
     */
    public int top() {
        if (q1.isEmpty() && q2.isEmpty()) {
            throw new RuntimeException("栈中元素为空");
        } else {
            Deque<Integer> empty = q1;
            Deque<Integer> notEmpty = q2;
            if (!q1.isEmpty()) {
                empty = q2;
                notEmpty = q1;
            }
            while (notEmpty.size() > 1) {
                empty.offer(notEmpty.poll());
            }
            int res = notEmpty.poll();
            empty.offer(res);
            return res;
        }
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
