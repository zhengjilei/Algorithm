package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/4
 */
public class Q0946_StackOutputSeq {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (popped == null || pushed == null || popped.length != popped.length) return false;
        if (popped.length == 0 && popped.length == 0) return true;
        int i = 0, j = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 将所有的输入元素全部压完
        while (i < pushed.length) {
            if (!stack.isEmpty() && stack.peek() == popped[j]) {
                j++;
                stack.pop();
                continue;
            }

            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                stack.push(pushed[i]);
                i++;
            }
        }
        // 压完之后，继续和输出序列弹出比较
        while (!stack.isEmpty()) {
            if (stack.pop() == popped[j]) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }
}
