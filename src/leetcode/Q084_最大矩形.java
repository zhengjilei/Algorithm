package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/12/19
 */
public class Q084_最大矩形 {
    public int largestRectangleArea(int[] heights) {
        int length = heights.length;

        int[] lFirstMinIndex = new int[length];
        int[] rFirstMinIndex = new int[length];

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                lFirstMinIndex[i] = stack.peek();
            } else {
                lFirstMinIndex[i] = -1;
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                rFirstMinIndex[i] = stack.peek();
            } else {
                rFirstMinIndex[i] = length;
            }
            stack.push(i);
        }

        int max = 0;
        int width = 0;
        int size = 0;
        for (int i = 0; i < length; i++) {
            width = rFirstMinIndex[i] - lFirstMinIndex[i] - 1;
            size = width * heights[i];
            if (size > max) {
                max = size;
            }
        }
        return max;

    }

    @Test
    public void test() {
        System.out.println(largestRectangleArea(new int[]{10}));
    }
}
