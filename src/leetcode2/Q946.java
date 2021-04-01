package leetcode2;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int i = 0, j = 0;
        while (i < pushed.length) {
            if (!stack.isEmpty() && popped[j] == stack.peek()) {
                stack.pop();
                j++;
                continue;
            }

            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                stack.push(pushed[i++]);
            }
        }

        while (j < pushed.length) {
            if (stack.pop() != popped[j]) return false;
            j++;
        }

        return true;
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || popped.length != pushed.length) return false;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int i = 0, j = 0;
        while (i < pushed.length) {
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }

            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                stack.push(pushed[i++]);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != popped[j]) return false;
            j++;
        }
        return true;

    }
}
