package programmer_interview;

import java.util.ArrayDeque;

/**
 * 逆序一个栈，只允许使用递归，不允许使用辅助数据结构
 * created by Ethan-Walker on 2018/12/16
 */
public class Q003_ReverseStack {


    /**
     * 获取栈底元素，并只弹出栈底元素，保留其他元素位置/顺序不动
     *
     * @param stack
     * @return
     */
    public int getAndRemoveLastElementInStack(ArrayDeque<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;// result 是栈底元素，已经弹出，直接返回
        } else {
            // result 不是栈底元素

            // 对剩余的栈元素，进行递归直到栈底元素被弹出返回
            int last = getAndRemoveLastElementInStack(stack);
            stack.push(result);  // 恢复 result 到栈顶
            return last;
        }
    }

    public void reverse(ArrayDeque<Integer> stack) {
        if (!stack.isEmpty()) {
            int bottom = getAndRemoveLastElementInStack(stack);
            reverse(stack); // 递归，依次弹出栈底元素
            stack.push(bottom);
        }
    }

}
