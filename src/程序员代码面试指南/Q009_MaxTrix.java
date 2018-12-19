package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 最大子矩阵大小
 * created by Ethan-Walker on 2018/12/19
 */
public class Q009_MaxTrix {


    public int calMaxMatrix(int[][] a) {
        int row = a.length;
        int col = a[0].length;

        int[] leftFirstMinIndex = new int[col];
        int[] rightFirstMinIndex = new int[col];

        int max = 0;

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int[] height = new int[col];
        for (int i = 0; i < row; i++) {
            // 计算第 i 行各列的累计高度
            for (int j = 0; j < col; j++) {
                height[j] = a[i][j] == 0 ? 0 : height[j] + 1;
            }

            // 求各点左边第一个比其小的位置
            for (int j = 0; j < col; j++) {
                while (!stack.isEmpty() && height[j] <= height[stack.peek()]) { // 等于号必须得取
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    leftFirstMinIndex[j] = stack.peek();
                } else {
                    // 栈为空
                    leftFirstMinIndex[j] = -1;
                }
                stack.push(j);
            }
            stack.clear();


            // 求各点右边第一个比其小的位置
            for (int j = col - 1; j >= 0; j--) {
                while (!stack.isEmpty() && height[j] <= height[stack.peek()]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    rightFirstMinIndex[j] = stack.peek();
                } else {
                    rightFirstMinIndex[j] = col;
                }
                stack.push(j);
            }
            stack.clear();

            int width = 0;
            int size = 0;
            for (int j = 0; j < col; j++) {
                width = rightFirstMinIndex[j] - leftFirstMinIndex[j] - 1;
                size = width * height[j];
                if (size > max) {
                    max = size;
                }
            }

        }
        return max;


    }

    @Test
    public void test() {
        int[][] a = {{0}, {0}, {1}};
        System.out.println(calMaxMatrix(a));
    }
}
