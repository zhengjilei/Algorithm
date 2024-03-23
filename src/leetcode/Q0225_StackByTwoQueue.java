package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q0225_StackByTwoQueue {

    ArrayDeque<Integer> queue1;
    ArrayDeque<Integer> queue2;

    /**
     * Initialize your data structure here.
     */
    public Q0225_StackByTwoQueue() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        ArrayDeque<Integer> q = queue1.isEmpty() ? queue2 : queue1;
        q.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        if (empty()) {
            throw new RuntimeException("队列为空");
        }

        ArrayDeque<Integer> q, r;// q 指向非空队列, r 指向空队列
        q = queue1.isEmpty() ? queue2 : queue1;
        r = queue1.isEmpty() ? queue1 : queue2;

        int res = -1;
        while (!q.isEmpty()) {
            res = q.poll();
            if (!q.isEmpty()) {
                r.offer(res);
            } else {
                break;
            }
        }
        return res;


    }

    /**
     * Get the top element.
     */
    public int top() {
        if (empty())
            throw new RuntimeException("队列为空");
        ArrayDeque<Integer> q, r;// q 指向非空队列, r 指向空队列
        q = queue1.isEmpty() ? queue2 : queue1;
        r = queue1.isEmpty() ? queue1 : queue2;

        int res = -1;
        while (!q.isEmpty()) {
            res = q.poll();
            r.offer(res);
        }
        return res;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        if (queue1.isEmpty() && queue2.isEmpty()) return true;
        return false;
    }
}
