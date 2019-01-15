package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/15
 */
public class Q150_RPN {


    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        int a = 0, b = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (isOp(tokens[i])) {
                b = numStack.pop();
                a = numStack.pop();
                numStack.push(cal(a, b, tokens[i].charAt(0)));
            } else {
                // 整数
                numStack.push(parseStrToInt(tokens[i]));
            }
        }
        return numStack.pop();
    }

    public int parseStrToInt(String str) {
        str = str.trim();
        boolean positive = true;
        int index = 0;
        if (str.charAt(0) == '-') {
            positive = false;
            index++;
        } else if (str.charAt(0) == '+') {
            index++;
        }

        int result = 0;
        int c = 0;
        Integer minq = Integer.MIN_VALUE / 10;
        Integer minr = Integer.MIN_VALUE % 10;
        for (; index < str.length(); index++) {
            c = '0' - str.charAt(index);
            if (result < minq || (result == minq && c < minr)) {
                throw new RuntimeException("整数越界");
            }
            result = result * 10 + c; // result 总是负数
        }
        if (positive && result == Integer.MIN_VALUE) {
            throw new RuntimeException("整数越界");
        }
        return positive ? -result : result;
    }

    public boolean isOp(String c) {
        if (c.length() > 1) return false;
        if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
            return true;
        }
        return false;
    }

    public int cal(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }
}
