package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 思路： 数字的长度为 n 个，则参与运算的运算符共 3^n，全排列求出所有可能的运算符组合
 * 然后对每种运算符组合求值
 * 错误：将所有参与的数字认为都在 0-9 之间
 * <p>
 * created by Ethan-Walker on 2019/1/15
 */
public class Q0282_ExprToTarget {
    public List<String> addOperators(String num, int target) {

        int opLength = num.length() - 1;
        char[] chs = new char[opLength];
        List<String> ops = new ArrayList<>();
        perm(opLength, 0, chs, ops);

        ArrayList<String> result = new ArrayList<>();

        for (String cs : ops) {
            if (cal(num, cs.toCharArray()) == target) {
                result.add(merge(num, cs));
            }
        }
        return result;

    }

    public String merge(String num, String cs) {
        char[] chs = new char[num.length() + cs.length()];
        int j = 0;
        int cnt = 0;
        for (int i = 0; i < num.length(); i++) {
            chs[cnt++] = num.charAt(i);
            if (j < cs.length()) {
                chs[cnt++] = cs.charAt(j++);
            }
        }
        return new String(chs);

    }

    /**
     * 一共3个位置，每个位置都可能放  + - * , 求出所有的全排列
     *
     * @param n
     * @return
     */
    public void perm(int n, int i, char[] chs, List<String> list) {
        if (i == n) {
            list.add(new String(chs));
            return;
        }
        chs[i] = '+';
        perm(n, i + 1, chs, list);
        chs[i] = '-';
        perm(n, i + 1, chs, list);
        chs[i] = '*';
        perm(n, i + 1, chs, list);
    }

    /**
     * 计算表达式，数字全是 0-9
     *
     * @param expr
     * @return
     */
    public int cal(String expr, char[] ops) {
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        ArrayDeque<Character> opStack = new ArrayDeque<>();

        HashMap<Character, Integer> priority = new HashMap<>(); // 运算符优先级
        priority.put('*', 1);
        priority.put('+', 0);
        priority.put('-', 0);

        int j = 0, a, b;
        char c;
        for (int i = 0; i < expr.length(); i++) {
            numStack.push(expr.charAt(i) - '0'); // 数字直接压栈
            if (j < ops.length) {
                while (!opStack.isEmpty() && priority.get(ops[j]) <= priority.get(opStack.peek())) {
                    b = numStack.pop();
                    a = numStack.pop();
                    numStack.push(cal(a, b, opStack.pop()));
                }
                opStack.push(ops[j++]);
            }
        }
        while (!opStack.isEmpty()) {
            b = numStack.pop();
            a = numStack.pop();
            numStack.push(cal(a, b, opStack.pop()));
        }
        return numStack.pop();

    }

    @Test
    public void test() {
        String str = "00";
//        char[] chs = {'-', '+'};
//        char[] chs2 = {'+', '+'};
//        char[] chs3 = {'*', '-'};
//        char[] chs4 = {'-', '*'};
//        System.out.println(cal(str, chs));
//        System.out.println(cal(str, chs2));
//        System.out.println(cal(str, chs3));
//        System.out.println(cal(str, chs4));

        List<String> strings = addOperators(str, 0);
        System.out.println(strings);
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
