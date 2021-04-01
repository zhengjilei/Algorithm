package programmer_interview;

import java.util.ArrayDeque;

/**
 * 双栈实现队列
 * created by Ethan-Walker on 2018/12/16
 */
public class Q002_QueueByStack {
    ArrayDeque<Integer> pushStack;
    ArrayDeque<Integer> popStack;


    public Q002_QueueByStack() {
        this.pushStack = new ArrayDeque<>();
        this.popStack = new ArrayDeque<>();
    }


    public void offer(int val) {
        pushStack.push(val);
    }


    public int poll() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new RuntimeException("queue is empty!");
        }

        if (!popStack.isEmpty()) {
            return popStack.pop();
        }
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new RuntimeException("queue is empty!");
        }

        if (!popStack.isEmpty()) {
            return popStack.peek();
        }
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
        return popStack.peek();
    }
}
