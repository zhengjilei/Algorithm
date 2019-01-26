package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/26
 */
public class Q020_ValidBracket {

    // 多个不同的括号，需要用栈
    // 只有一种括号，可以通过计数方式
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    stack.push(s.charAt(i));
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(')
                        return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[')
                        return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{')
                        return false;
                    break;
                default:
                    break;
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
