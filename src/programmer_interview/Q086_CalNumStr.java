package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * // TODO: 2019/2/1 未想出思路
 * created by Ethan-Walker on 2019/2/1
 */
public class Q086_CalNumStr {


    public int cal(String str) {

        String[] strs = parse(str);
        int res = 0;
        ArrayDeque<String> opStack = new ArrayDeque<>();
        ArrayDeque<Integer> numStack = new ArrayDeque<>();

        HashMap<String, Integer> opPriority = new HashMap<>();
        opPriority.put("+", 1);
        opPriority.put("-", 1);
        opPriority.put("*", 2);
        opPriority.put("/", 2);
        opPriority.put("(", 3);
        String tmp;
        for (int i = 0; i < strs.length; i++) {
            if (isDigit(strs[i])) {
                numStack.push(Integer.valueOf(strs[i]));
            } else if (strs[i].equals(")")) {
                while (opStack != null) {
                    String pop = opStack.pop();
                    if (opStack.peek().equals("(")) {
                        opStack.pop();
                        break;
                    } else {
                        int num = numStack.pop();
                        int num2 = numStack.pop();
                        String op = opStack.pop();
                        numStack.push(cal(num2, num, op));
                    }
                }
            } else {
                // 普通操作符
                while (!opStack.isEmpty() && opPriority.get(opStack) > opPriority.get(strs[i])) {

                }
            }
        }

        while (!opStack.isEmpty()) {

        }
        return 0;
    }

    public int cal(int num1, int num2, String op) {
        if (op.equals("+")) {
            return num1 + num2;
        } else if (op.equals("-")) {
            return num1 - num2;
        } else if (op.equals("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }

    public boolean isDigit(String s) {
        if (isDigit(s.charAt(0)) && (s.length() > 1 && s.charAt(0) == '0' && isDigit(s.charAt(1)))) {
            return true;
        }
        return false;
    }

    public boolean isDigit(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }

    public String[] parse(String str) {
        ArrayList<String> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        boolean nextIsNum = true;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9'
                    || (nextIsNum && str.charAt(i) == '-')) { // 负数
                sb.append(str.charAt(i));
                nextIsNum = false; // 数字后面希望跟的是操作符
            } else {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
                if (str.charAt(i) != ' ' && str.charAt(i) != '\t') { // 空格忽略
                    if (str.charAt(i) == ')') {
                        nextIsNum = false; // ) 后应该跟的是 数字
                    } else {
                        nextIsNum = true; //
                    }
                    list.add(String.valueOf(str.charAt(i)));

                }
            }
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
        }
        System.out.println(list);
        return list.toArray(new String[0]);
    }

    @Test
    public void test() {
        parse("48*((70-65)-43)+8*1");
        parse("48*((70-65)-(-43))+8*1");
        parse("3+1*4");
        parse("-3+(1*4)");
    }
}
