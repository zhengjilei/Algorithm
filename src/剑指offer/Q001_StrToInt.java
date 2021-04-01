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
     */
    public int strToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int index = 0;
        boolean positive = true;
        if (s.charAt(0) == '-') {
            positive = false;
            index++;
        } else if (s.charAt(0) == '+') {
            index++;
        } else if (!isDigit(s.charAt(0))) {
            return 0;
        }

        int minDiv = Integer.MIN_VALUE / 10;
        int minRemain = Integer.MIN_VALUE % 10;
        int sum = 0, cur = 0;
        char c = 0;
        for (int i = index; i < s.length(); i++) {
            c = s.charAt(i);
            if (!isDigit(c)) return 0;
            cur = '0' - c;
            if (sum < minDiv || (sum == minDiv && cur < minRemain)) return 0;
            sum = sum * 10 + cur;
        }

        if (positive) {
            if (sum == Integer.MIN_VALUE) return 0;
            return -sum;
        }
        return sum;

    }


    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    @Test
    public void testStrToInt() {

        System.out.println();
        strToInt("1");
//            strToInt("ad32");
//            strToInt("12a");
        strToInt("-2132");
        strToInt("2147483647");
//            strToInt("2147483648");
        strToInt("-2147483648");
        strToInt("-2147483649");


    }

    @Test
    public void test() {
        int t = Integer.MIN_VALUE;
        System.out.println(t - 1);  // 最小值溢出

        int m = Integer.MAX_VALUE;
        System.out.println(m + 1);// 最大值溢出
    }

}