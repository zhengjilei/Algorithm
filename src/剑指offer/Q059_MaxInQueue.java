package 剑指offer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 找队列中的最大值
 * <p>
 * created by Ethan-Walker on 2018/12/14
 */
public class Q059_MaxInQueue {

    /**
     * 方法一：双栈实现队列，栈O(1)时间内获得最大值
     * <p>
     * 分析：之前实现过O(1)时间内得到栈中最小值（最大值同理）(dataStack,minStack)
     * 队列可以通过双栈实现（一个栈专用于压入元素，一个专用于弹出元素）
     * 则找队列中的最大值转换成找栈中的最大值
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || num.length == 0 || size <= 0 || size > num.length) {
            return list;
        }
        MQueue queue = new MQueue();
        // 先压 size - 1 个元素
        int t = 0;
        for (; t < size - 1; t++) {
            queue.offer(num[t]);
        }

        for (; t < num.length; t++) {
            queue.offer(num[t]);
            list.add(queue.getMax());
            queue.poll();
        }

        return list;
    }


    /**
     * 双端队列，存放的是索引
     *
     * @param num
     * @param size
     * @return
     */
    public int[] maxInWindows2(int[] array, int size) {

        if (array == null || size > array.length) return null;
        if (array.length == 0) return new int[0];

        int resultLength = array.length - size + 1;
        int[] result = new int[resultLength];
        int resultIndex = 0;

        ArrayDeque<Integer> deque = new ArrayDeque<>();// 双端队列，存放当前窗口中的最大值索引

        for (int index = 0; index < array.length; index++) {
            // 弹出不可能是窗口中最大的元素
            while (!deque.isEmpty() && array[index] >= array[deque.peekLast()]) { //等于可取可不取
                deque.pollLast();
            }
            deque.offerLast(index);

            // 判断窗口中最大值是否需要弹出
            if (index - deque.peekFirst() == size) {
                deque.pollFirst();
            }
            // 窗口中满 size 个元素时，需要获取最大值
            if (index >= size - 1) {
                result[resultIndex++] = array[deque.peekFirst()];

            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 2, 4, 2, 6, 2, 5, 1};
        System.out.println(maxInWindows2(a, 3));
    }
}

class MQueue {
    MStack pushStack = new MStack();
    MStack popStack = new MStack();


    void offer(int val) {
        pushStack.push(val);
    }

    int poll() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    int getMax() {
        int max = Integer.MIN_VALUE, t;
        if (!popStack.isEmpty()) {
            t = popStack.getMax();
            if (t > max) {
                max = t;
            }
        }
        if (!pushStack.isEmpty()) {
            t = pushStack.getMax();
            if (t > max) {
                max = t;
            }
        }
        return max;
    }

    boolean isEmpty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}


class MStack {
    ArrayDeque<Integer> dataStack = new ArrayDeque<>();
    ArrayDeque<Integer> maxStack = new ArrayDeque<>();


    void push(int val) {
        dataStack.push(val);
        if (maxStack.isEmpty() || val > maxStack.peek()) {
            maxStack.push(val);
        } else {
            maxStack.push(maxStack.peek());
        }
    }

    int pop() {
        maxStack.pop();
        return dataStack.pop();
    }

    int getMax() {
        return maxStack.peek();
    }

    boolean isEmpty() {
        return dataStack.isEmpty();
    }

    int size() {
        return dataStack.size();
    }
}