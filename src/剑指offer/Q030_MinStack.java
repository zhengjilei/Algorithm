package 剑指offer;

import java.util.ArrayDeque;

/**
 * 定义栈的数据结构，实现一个min 函数能获取栈中的最小值，要求min 时间复杂度为 O(1)
 * created by Ethan-Walker on 2018/12/7
 */
public class Q030_MinStack {

    ArrayDeque<Integer> dataStack;
    ArrayDeque<Integer> minStack;


    public Q030_MinStack() {
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

    public void pop() {
        if (!isEmpty()) {
            minStack.pop();
            dataStack.pop();
        } else {
            throw new RuntimeException("pop() 执行异常：栈为空"); // 运行时异常
        }

    }

    public int min() {
        if (!isEmpty()) {
            return minStack.peek();
        } else {
            throw new RuntimeException("min() 执行异常: 栈为空");
        }
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
    }


    public static void main(String[] args) {
        Q030_MinStack stack = new Q030_MinStack();
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(1);

        System.out.println(stack.min());
        stack.pop();
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
    }
}
