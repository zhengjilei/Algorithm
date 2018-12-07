package stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

/**
 * Created by Ethan-Walker on 2018/7/21.
 * 双栈计算中缀表达式: 局限性很大 一般不这么做
 */
public class CalcMiddleExp {
    private Deque<String> opStack;
    private Deque<Double> numStack;

    private HashSet<String> opsSet = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

    private HashSet<String> numsSet = new HashSet<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

    private CalcMiddleExp() {
        opStack = new ArrayDeque<>();
        numStack = new ArrayDeque<>();
    }

    public boolean isOperator(String c) {
        if (opsSet.contains(c)) return true;
        return false;

    }

    public boolean isNumber(String s) {
        if (numsSet.contains(s)) return true;
        else return false;
    }

    public double cal(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            String s = String.valueOf(str.charAt(i));
            if (isOperator(s)) {
                opStack.offerLast(s);
            } else if (")".equals(s)) {
                tempCal();
            } else if (isNumber(s)) {
                numStack.offerLast(Double.valueOf(s));
            } else {
                continue;
            }
        }
        while (!opStack.isEmpty()) {
            tempCal();
        }

        return numStack.pollLast();
    }

    public void tempCal() {
        double b = numStack.pollLast();
        double a = numStack.pollLast();
        String op = opStack.pollLast();
        double result = 0;
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "/":
                result = a * 1.0 / b;
                break;
            case "*":
                result = a * b;
                break;
            default:
                break;
        }
        numStack.offerLast(result);
    }

    public static void main(String[] args) {
        CalcMiddleExp calcMiddleExp = new CalcMiddleExp();
        String str1 = " (1 + 2)- 4 * (3 / 2)";
        String str2 = "3 * 5 + 6";   // 计算错误，得到 3*(5+6)的结果，应写成 (3*5)+6    不同优先级的运算符同时出现，用括号来强调
        double d1 = calcMiddleExp.cal(str1);
        double d2 = calcMiddleExp.cal(str2);
        System.out.println(d1);
        System.out.println(d2);

    }
}
