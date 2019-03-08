package stackqueue;

import java.util.Arrays;

/**
 * 静态顺序栈
 * <p>
 * created by Ethan-Walker on 2019/1/15
 */

public class ArrayStack {
    int[] item;
    int top;

    public ArrayStack(int maxCount) {
        this.item = new int[maxCount];
        this.top = 0;
    }

    boolean push(int val) {
        if (top == item.length) {
            item = Arrays.copyOf(item, item.length * 2);
        }
        item[top++] = val;
        return true;
    }

    int pop() {
        if (top == 0)
            throw new RuntimeException("栈为空");
        return item[top--];
    }

    int getTop() {
        if (top == 0)
            throw new RuntimeException("栈为空");
        return item[top];
    }

    int getSize() {
        return top;
    }

    boolean isEmpty() {
        return top == 0;
    }
}
