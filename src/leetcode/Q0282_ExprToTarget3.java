package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 在ExprToTarget2 的基础上，讨论运算符包括 * 时，会发生什么？
 * 如果只包含 + - ，运算符等级一样，就从左到右正常计算即可 1+23-11
 * 如果包含 * ,按照原来的思路如果添加的符号位 1-2*3 会导致计算结果为 (1-2) *3 = -3
 * 如何解决？
 * <p>
 * created by Ethan-Walker on 2019/1/15
 */
public class Q0282_ExprToTarget3 {


    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();
        search(target, "", num, 0, 0, result);
        return result;
    }

    /**
     * 示例：给231869加运算符
     *
     * @param target
     * @param opStr      已经拼接的字符串 2+3*1
     * @param preOpedNum 上一个被opStr 拼接的数字(如果上一轮中被 + ,传入的是原数，如果上一轮中被 - ,传入相反数=>
     *                   目的是当前轮如果是*, 用 curSum - preOpedNum 可以还原被错优先级误计算的一位数)
     * @param rest       剩余未处理的数字 869
     * @param curNum
     */
    public void search(int target, String opStr, String rest, long curNum, long preOpedNum, List<String> result) {
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

            if (numStr.length() > 1 && numStr.charAt(0) == '0') { // 被拼接数字是 0xx开头的，视为不合法
                return;
            }

            num = Long.parseLong(numStr);

            if (opStr.length() == 0) {
                // 第一个数不用加符号
                search(target, numStr, rest.substring(i), num, num, result); // 注意这里传的 preOpedNum 是 num, 首先第一个数是正数，其次，应对 1*2+3-4 的情况
            } else {

                search(target, opStr + "+" + numStr, rest.substring(i), curNum + num, num, result);  //  num        1-2*3
                search(target, opStr + "-" + numStr, rest.substring(i), curNum - num, -num, result); //  -num       1-2*3
                // 如果opStr 和当前num 相乘, 要将 curNum - preNum 还原至被运算优先级错计算前的数
                // 新拼接值：还原值+ 被错优先级拼接的数字* 当前拼接的数字：(curNum - preOpedNum) + preOpedNum * num
                // 注意在 * 中传这一次被拼接的数字：不是num，而是 preOperNum*num, 将其视为一个整体 应对多个乘号累计的情况 1-2-3*4*5
                search(target, opStr + "*" + numStr, rest.substring(i), (curNum - preOpedNum) + preOpedNum * num, preOpedNum * num, result);
            }

        }
    }


    @Test
    public void test() {
        System.out.println(addOperators("123", 6));
        System.out.println(addOperators("105", 5));
        System.out.println(addOperators("00", 0));
        System.out.println(addOperators("3456237490", 9191));
    }
}
