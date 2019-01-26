package leetcode;

import java.util.ArrayDeque;

/**
 * http://www.cnblogs.com/grandyang/p/7617017.html
 * created by Ethan-Walker on 2019/1/26
 */
public class Q678_ValidBracketStr {
    /**
     * 两个栈: 左括号栈、* 号栈
     * 遍历每一个字符
     * 1. ( 下标压左括号栈
     * 2. * 下标压 * 栈
     * 3. )
     * (1) 判断(栈是否为空,不为空，弹出 (
     * (2) 判断* 栈是否为空，不为空,弹出 *
     * (3) 都为空，不匹配
     * <p>
     * 遍历完之后
     * 1.如果( 为空，剩余的 * 可以全部当成空字符
     * 2.( 不为空，将 * 看成是 )
     * 将(栈顶和 * 栈依次进行匹配，如果(栈顶下标比*栈顶下标大，不能匹配
     * ( 栈全部匹配完，匹配成功
     * <p>
     * 测试:
     * (*)
     * ((*)
     * (*))
     * (**
     * ((**))
     * *(()
     *
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        ArrayDeque<Integer> stack1 = new ArrayDeque<>();
        ArrayDeque<Integer> stack2 = new ArrayDeque<>();


        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    stack1.push(i);
                    break;
                case '*':
                    stack2.push(i);
                    break;
                case ')':
                    if (!stack1.isEmpty()) {
                        stack1.pop();
                    } else if (!stack2.isEmpty()) {
                        stack2.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        // ( 和 * 进行匹配
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.pop() > stack2.pop()) {
                return false;
            }
        }
        if (stack1.isEmpty()) return true;
        return false;
    }

}
