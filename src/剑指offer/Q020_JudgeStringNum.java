package 剑指offer;

import org.junit.Test;

/**
 * 给定字符串，判断是否是数值
 * 正确： +100, 5e2 5E2 -123 3.13234 -1E-6
 * 错误:  12e 1a3.14 1.2.3   +-5  12e+5.4
 * created by Ethan-Walker on 2018/12/5
 */
public class Q020_JudgeStringNum {

    enum State {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
        // SEVEN 为可终止状态
    }

    public boolean judge(String s) {
        State state = State.ONE;
        int index = 0;
        char ch;
        while (index < s.length()) {
            ch = s.charAt(index);
            switch (state) {
                case ONE:
                    if (isNumber(ch) || ch == '+' || ch == '-') {
                        state = State.TWO;
                    } else if (ch == '.') {
                        state = State.THREE;
                    } else {
                        return false;
                    }
                    break;
                case TWO:
                    if (ch == '.') {
                        state = State.THREE;
                    } else if (isNumber(ch)) {

                    } else if (ch == 'E' || ch == 'e') {
                        state = State.FIVE;
                    } else {
                        return false;
                    }
                    break;
                case THREE:
                    if (isNumber(ch)) {
                        state = State.FOUR;
                    } else {
                        return false;
                    }
                    break;
                case FOUR:
                    if (isNumber(ch)) {

                    } else if (ch == 'E' || ch == 'e') {
                        state = State.FIVE;
                    } else {
                        return false;
                    }
                    break;
                case FIVE:
                    if (isNumber(ch)) {
                        state = State.SEVEN;
                    } else if (ch == '+' || ch == '-') {
                        state = State.SIX;
                    } else {
                        return false;
                    }
                    break;
                case SIX:
                    if (isNumber(ch)) {
                        state = State.SEVEN;
                    } else {
                        return false;
                    }
                    break;
                case SEVEN:
                    if (isNumber(ch)) {

                    } else {
                        return false;
                    }
                default:
                    break;
            }
            index++;

        }
        if (index == s.length() && isTerminateState(state))
            return true;
        return false;
    }

    public boolean isTerminateState(State s) {
        if (s == State.TWO || s == State.FOUR || s == State.SEVEN) {
            return true;
        }
        return false;
    }

    public boolean isNumber(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }


    @Test
    public void test() {
        System.out.println(judge("+100"));
        System.out.println(judge("5e2"));
        System.out.println(judge("-123"));
        System.out.println(judge("3.1232"));
        System.out.println(judge("-1E-16"));

        System.out.println(judge("12e"));
        System.out.println(judge("1.2.3"));
        System.out.println(judge("1a3.14"));
        System.out.println(judge("1.2.3"));
        System.out.println(judge("+-5"));
        System.out.println(judge("12e+5.4"));

        System.out.println(judge("-.4"));
        System.out.println(judge(".4"));
        System.out.println(judge(""));
        System.out.println(judge("."));
        System.out.println(judge("1."));
    }

}
