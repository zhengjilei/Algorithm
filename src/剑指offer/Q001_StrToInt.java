package 剑指offer;

import org.junit.Test;

/**
 * 字符串转换成整数
 * created by Ethan-Walker on 2018/12/1
 */
public class Q001_StrToInt {

    /**
     * 1. 空指针
     * 2. 输入字符串中包含不合理字符
     * 3. + - 号
     * 4. 最大整数、最小整数溢出
     * 两种方法判断溢出：
     * 计算结果和符号位相反
     * 用一个更大范围的类型long 保存结果，判断值是否超过 int的边界即可
     * @param s
     * @return
     * @throws Exception
     */
    public int strToInt(String s) throws Exception {
        if (s == null || s.length() == 0) throw new Exception("s can't be null");
        int value = 0, i = 0;
        int sign = 1;
        if (s.charAt(0) == '-') {
            sign = -1;
            i = 1;
        } else if (s.charAt(0) == '+') {
            i = 1;
        }
        for (; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') throw new Exception(s + " is invalid");
            value = value * 10 + (s.charAt(i) - '0') * sign;
            if (value > 0 && sign < 0 || value < 0 && sign > 0) { // value 和 sign 不同号
                throw new Exception("数值越界");
            }
        }
        System.out.println(value);
        return value;
    }

    @Test
    public void testStrToInt() {

        try {
            strToInt("12");
//            strToInt(null);
            strToInt("1");
//            strToInt("ad32");
//            strToInt("12a");
            strToInt("-2132");
            strToInt("2147483647");
//            strToInt("2147483648");
            strToInt("-2147483648");
            strToInt("-2147483649");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void test() {
        int t = Integer.MIN_VALUE;
        System.out.println(t - 1);  // 最小值溢出

        int m = Integer.MAX_VALUE;
        System.out.println(m + 1);// 最大值溢出
    }

}