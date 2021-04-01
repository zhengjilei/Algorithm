package stackqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 队列应用： 打印二项式系数
 */
public class QueueApply {


    /**
     * 打印 (a+b)^n 的系数
     * 队列实现
     *
     * @param n
     */
    void printByQueue(int n) {

        ArrayQueue<Integer> queue = new ArrayQueue<>(n + 2); // 第 1 行压入  1  1 0 三个元素
        queue.offer(1);
        queue.offer(1);

        Integer s = 0, t;
        for (int i = 1; i <= n; i++) {
            queue.offer(0);
            for (int j = 1; j <= i + 2; j++) {
                // 处理第i行 i+2 个元素 ,包括0
                t = queue.poll();
                queue.offer(t + s);
                s = t;
            }
        }

        while (!queue.isEmpty()) {
            System.out.printf("%d ", queue.poll());
        }
        System.out.println();
    }

    /**
     * n
     * 1 1 0 0 0      1
     * 1 2 1 0 0      2
     * 1 3 3 1 0      3
     * 1 4 6 4 1      4
     *
     * @param n
     */
    void print(int n) {
        int[][] eof = new int[n][n + 1];
        eof[0][0] = eof[0][1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i + 1; j++) {
                if (j == 0) eof[i][j] = 1;
                else eof[i][j] = eof[i - 1][j - 1] + eof[i - 1][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.printf("%-5d", eof[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        QueueApply queueApply = new QueueApply();
        queueApply.printByQueue(3);
        queueApply.printByQueue(4);
        queueApply.printByQueue(5);
        queueApply.printByQueue(6);
        queueApply.printByQueue(7);
        queueApply.printByQueue(8);
        queueApply.printByQueue(9);
        queueApply.printByQueue(10);
    }

}
