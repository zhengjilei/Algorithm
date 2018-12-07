package stackqueue;

import java.util.ArrayDeque;

/**
 * 给定入栈序列、出栈序列，判断出栈序列是否可能由 入栈序列得到
 * created by Ethan-Walker on 2018/12/7
 */
public class JudgeStackOutputSeq {

    public boolean judge(int[] seq, int[] output) {
        int i = 0, j = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 压入所有的元素
        while (i < output.length) {
            if (!stack.isEmpty() && stack.peek() == output[j]) {
                // 栈顶元素和待匹配元素 匹配
                j++;
                stack.pop();
                continue;
            }
            if (seq[i] == output[j]) {  //直接匹配，省去入栈出栈操作了
                i++;
                j++;
            } else {
                stack.push(seq[i]);
                i++;
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() == output[j]) {
                j++;
            } else
                return false;
        }
        return true;

    }
}
