package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/7
 */
public class Q066_ConvertToAlpha {


    /**
     * dp[i] 表示 str[0..i] 最多的转换种类
     * 1. dp[0]
     * str[0] != 0 ,dp[0] = 1;
     * str[0] == 0, dp[0] = 0
     * 2. dp[i]
     * (1)str[i] 单独作为一个字母
     * str[i]!=0 ,dp[i] 可单独作为一个字母，这种情况下累计种类数+dp[i-1]
     * ==0 ，str[i]不可单独作为一个字母，种类数+0
     * (2)str[i-1,i] 构成一个字母
     * 若 str[i-1]==1/2 且 str[i]<=6，满足条件，种类数 + dp[i-2]
     * 否则，不可构成一个字母，种类数+0
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * <p>
     * dp[i] 依赖于 dp[i-1] dp[i-2]
     *
     * @param str
     * @return
     */
    public int process(String str) {
        if (str == null || str.length() == 0) return 0;

        int[] dp = new int[str.length()];
        if (str.charAt(0) != '0') dp[0] = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                // str[i] 单独作为一个字母
                dp[i] = dp[i - 1];
            }
            if (str.charAt(i) <= '6' && (str.charAt(i - 1) == '1' || str.charAt(i - 1) == '2')) {
                if (i >= 2) {
                    // str[i-1 i] 可以构成一个 字母
                    dp[i] += dp[i - 2];
                } else {
                    // dp[1]
                    dp[i]++;
                }
            }
        }
        return dp[str.length() - 1];
    }


    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param str
     * @return
     */
    public int process2(String str) {
        if (str == null || str.length() == 0) return 0;
        int a, b, c;
        a = b = c = 0;
        if (str.charAt(0) != '0') a = 1;
        if (str.length() == 1) {
            return a;
        }
        b = a;
        for (int i = 1; i < str.length(); i++) {
            c = 0;
            if (str.charAt(i) != '0') {
                c += b; // + dp[i-1]
            }
            if (str.charAt(i) <= '6' && (str.charAt(i - 1) == '1' || str.charAt(i - 1) == '2')) {
                if (i >= 2) {
                    c += a;
                } else {
                    c += 1;
                }
            }
            a = b;
            b = c;
        }
        return c;
    }

    @Test
    public void test() {

        System.out.println(process("1111"));
        System.out.println(process2("1111"));
        System.out.println(process3("1111"));

        System.out.println("----------------");
        System.out.println(process("01"));
        System.out.println(process2("01"));
        System.out.println(process3("01"));
        System.out.println("----------------");

        System.out.println(process("10"));
        System.out.println(process2("10"));
        System.out.println(process3("10"));

        System.out.println("----------------");
        System.out.println(process("12121121212122"));
        System.out.println(process2("12121121212122"));
        System.out.println(process3("12121121212122"));


    }


    public int process3(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chs = str.toCharArray();
        int cur = chs[chs.length - 1] == '0' ? 0 : 1;
        int next = 1, tmp = 0;
        for (int i = chs.length - 2; i >= 0; i--) {
            if (chs[i] == '0') {
                next = cur;
                cur = 0;
            } else {
                tmp = cur;
                if ((chs[i] - '0') * 10 + chs[i + 1] - '0' < 27) {
                    cur += next;
                }
                next = tmp;
            }
        }
        return cur;
    }
}
