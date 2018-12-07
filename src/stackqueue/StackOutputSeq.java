package stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 给定入栈序列，求所有可能的出栈序列
 * created by Ethan-Walker on 2018/12/7
 */
public class StackOutputSeq {


    static int[] seq;
    static int count;

    /**
     * stack
     *
     * @param stackSeq 是模拟入栈出栈结果，由 0 1 组成
     */
    public static void output(int[] stackSeq) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < seq.length; i++) {
            queue.offer(seq[i]);
        }

        for (int i = 0; i < stackSeq.length; i++) {
            if (stackSeq[i] == 1) {
                // 压栈
                stack.push(queue.poll());
            } else {
                // 出栈
                System.out.printf("%3d", stack.pop());
            }
        }
        System.out.println();
    }

    /**
     * 保证数组a的前 k 个元素中，1的个数总是大于等于 0 的个数
     *
     * @param a
     * @param index 当前要压入第 index 位置的数
     * @param left1 剩下的 1 总数
     * @param left0 剩下的 0 总数
     */
    public static void fun(int[] a, int index, int left1, int left0) {
        if (index == a.length) {
            count++;
            output(a);
            return;
        }
        if (left1 > 0) { //说明还剩下 1 ，故可以在index位置压 1
            a[index] = 1;
            fun(a, index + 1, left1 - 1, left0);
        }
        if (left1 < left0) { // 说明之前压入的1 比 0 多， 故index位置也可以压0
            a[index] = 0;
            fun(a, index + 1, left1, left0 - 1);
        }

    }

    public static void main(String[] args) {
        seq = new int[]{1, 2, 3, 4, 5, 6};
        int n = seq.length;
        int[] a = new int[2 * n];
        fun(a, 0, n, n);
        System.out.println(count);
    }

}
