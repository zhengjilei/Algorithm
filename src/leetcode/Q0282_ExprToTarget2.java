package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 假设只可以添加的运算符是  +-
 * created by Ethan-Walker on 2019/1/15
 */
public class Q0282_ExprToTarget2 {

    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();
        search(0, "", num, 0, result);
        return result;
    }

    /**
     * 示例：给231869加运算符
     *
     * @param target
     * @param opStr  已经拼接的字符串 2+3-1
     * @param rest   剩余未处理的数字 869
     * @param curNum
     * @param result
     */
    public void search(int target, String opStr, String rest, long curNum, List<String> result) {
        if (rest.length() == 0) {
            if (target == curNum) {
                result.add(opStr);
            }
            return;
        }

        long num = 0;
        //  opStr 拼接计算下一个数字，下一个数字长度可能的取值
        for (int i = 1; i <= rest.length(); i++) {

            String numStr = rest.substring(0, i);
            num = Long.parseLong(numStr);
            if (opStr.length() == 0) {
                // 第一个数不用加符号
                search(target, numStr, rest.substring(i), num, result);
            } else {
                search(target, opStr + "+" + numStr, rest.substring(i), curNum + num, result);
                search(target, opStr + "-" + numStr, rest.substring(i), curNum - num, result);
            }

        }

    }

    @Test
    public void test() {
        System.out.println(addOperators("1111", 0));
        System.out.println(addOperators("2211", 0));
    }

}
