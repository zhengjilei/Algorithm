package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/26
 */
public class Q085_ValidBracket {

    public boolean isValidBracket(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();


        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
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

    @Test
    public void test(){
        System.out.println(isValidBracket("()()"));
        System.out.println(isValidBracket("()("));
        System.out.println(isValidBracket(")"));
        System.out.println(isValidBracket(")("));
        System.out.println(isValidBracket("(())"));
        System.out.println(isValidBracket("())"));
        System.out.println(isValidBracket("())("));
    }
}
