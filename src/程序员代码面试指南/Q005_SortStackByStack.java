package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

/**
 * 用一个栈实现另一个栈的排序
 * created by Ethan-Walker on 2018/12/16
 */
public class Q005_SortStackByStack {


    /**
     * 思路1：
     * 辅助排序栈：从栈顶到栈底  大->小
     * <p>
     * 数据栈中每弹出一个元素，准备插入排序栈中，通过不断弹出（压到数据栈，统计数量）
     * 找到待插入位置插入，并将之前弹出压入到 数据栈中的 n 个元素压回恢复
     *
     * @param dataStack
     * @return
     */
    public ArrayDeque<Integer> sortByStack(ArrayDeque<Integer> dataStack) {
        ArrayDeque<Integer> sortStack = new ArrayDeque<>(); // 存放已经排好序的元素,从栈顶到栈底
        if (dataStack == null || dataStack.isEmpty()) return sortStack;
        while (!dataStack.isEmpty()) {
            int value = dataStack.pop();
            if (sortStack.isEmpty()) {
                sortStack.push(value);
            } else {
                // 插入排序，将待插入值 value 插入到合适位置
                int count = 0;
                while (!sortStack.isEmpty() && value < sortStack.peek()) {
                    // value 小于栈顶元素
                    dataStack.push(sortStack.pop());
                    count++;
                }
                // 将 value 插入正确位置
                sortStack.push(value);
                // 恢复之前弹出的元素
                while (count > 0) {
                    sortStack.push(dataStack.pop());
                    count--;
                }
            }
        }
        return sortStack;
    }

    /**
     * 最佳
     * 对上面的思路1 进行改进，借鉴了思路2
     *
     * @param dataStack
     * @return
     */
    public ArrayDeque<Integer> sortByStackImproved(ArrayDeque<Integer> dataStack) {
        ArrayDeque<Integer> sortStack = new ArrayDeque<>();
        if (dataStack.isEmpty() || dataStack.size() == 0) return sortStack;
        while (!dataStack.isEmpty()) {
            int value = dataStack.pop();
            while (!sortStack.isEmpty() && value < sortStack.peek()) {
                dataStack.push(sortStack.pop());
            }
            sortStack.push(value);
        }
        return sortStack;
    }

    /**
     * 思路2:
     * 辅助排序栈：从栈顶到栈底 小->大
     *
     * @param dataStack
     * @return
     */
    public ArrayDeque<Integer> sortByStack2(ArrayDeque<Integer> dataStack) {
        ArrayDeque<Integer> sortStack = new ArrayDeque<>();
        if (dataStack.isEmpty() || dataStack.size() == 0) return sortStack;
        while (!dataStack.isEmpty()) {
            int value = dataStack.pop();
            while (!sortStack.isEmpty() && value > sortStack.peek()) {
                dataStack.push(sortStack.pop());
            }
            sortStack.push(value); // 插入到正确位置
        }
        while (!sortStack.isEmpty()) {
            dataStack.push(sortStack.pop());
        }
        return dataStack;
    }

    @Test
    public void test() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Random random = new Random();
        int n = random.nextInt(10) + 10;
        for (int i = 0; i < n; i++) {
            stack.push(random.nextInt(20));
        }
        ArrayDeque<Integer> stack2 = stack.clone();
        ArrayDeque<Integer> stack3 = stack.clone();

        System.out.println(stack);
        System.out.println(sortByStack(stack));
        System.out.println(sortByStackImproved(stack2));
        System.out.println(sortByStack2(stack3));
    }
}
