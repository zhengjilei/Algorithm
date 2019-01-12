package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/11
 */
public class Q073_SubstrSum {


    public int substrSum(String str) {
        if (str == null || str.length() == 0) return 0;
        int sum = 0;
        boolean positive = true; // true 表示正号，false 表示负号
        int lastNum = 0, cur = 0;
        for (int i = 0; i < str.length(); i++) {
            cur = str.charAt(i) - '0';
            if (cur >= 0 && cur <= 9) {
                lastNum = lastNum * 10 + (positive ? cur : -cur);
            } else {
                sum += lastNum;
                lastNum = 0;

                if (cur + '0' == '-') {
                    if (i >= 1 && str.charAt(i - 1) == '-') {
                        positive = !positive;
                    } else {
                        positive = false;
                    }
                } else {
                    positive = true;
                }
            }
        }
        sum += lastNum;
        return sum;
    }

    boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }


    /**
     *
     */
    @Test
    public void test() {
        String str1 = "A1.3";
        String str2 = "A-1BC--12";
        String str3 = "A1CD2e33";
        String str4 = "A-1B--2C--D6E";
        String str5 = "A-1B--2C--D---6-";

        System.out.println(substrSum(str1));
        System.out.println(substrSum(str2));
        System.out.println(substrSum(str3));
        System.out.println(substrSum(str4));
        System.out.println(substrSum(str5));
    }
}
