package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/1/15
 */
public class Q241_CalExpr {

    public List<Integer> diffWaysToCompute(String input) {
        String[] str = parse(input);
        return cal(str, 0, str.length - 1);
    }

    public String[] parse(String str) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            if (isDigit(str.charAt(i))) {
                int j = i + 1;
                while (j < str.length() && isDigit(str.charAt(j))) {
                    j++;
                }
                list.add(str.substring(i, j));
                i = j;
            } else {
                list.add(str.substring(i, i + 1));
                i++;
            }
        }
        String[] result = new String[list.size()];
        for (i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;

    }

    /**
     * 递归划分，依次选取一个操作符的位置，划分成左右两部分，分别计算左右两部分所有可能的值
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public List<Integer> cal(String[] str, int start, int end) {
        List<Integer> result = new ArrayList<>();
        if (start == end) { // 只有一个数
            result.add(parseInt(str[start]));
            return result;
        }
        for (int i = start + 1; i < end; i += 2) {
            List<Integer> left = cal(str, start, i - 1);
            List<Integer> right = cal(str, i + 1, end);
            for (Integer l : left) {
                for (Integer r : right) {
                    result.add(cal(l, r, str[i].charAt(0)));
                }
            }
        }
        return result;
    }

    /**
     * 正数str转换成正数
     *
     * @param str
     * @return
     */
    public int parseInt(String str) {
        int num = 0;
        int index = 0;
        while (index < str.length()) {
            num = num * 10 + str.charAt(index) - '0';
            index++;
        }
        return num;

    }

    public boolean isDigit(char c) {
        if (c >= '0' && c <= '9') return true;
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


    @Test
    public void test() {
        List<Integer> res = diffWaysToCompute("2-1-1");
        System.out.println(res);
    }
}
