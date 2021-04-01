package 剑指offer;

import org.junit.Test;

/**
 * 二十六进制
 * created by Ethan-Walker on 2018/12/4
 */
public class Q015A_TwentySix {


    public int cal(String s) {
        int n = s.trim().length();
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += ((int) Math.pow(26, n - i - 1)) * (s.charAt(i) - 'A' + 1);
        }
        return result;
    }

    @Test
    public void testCal() {
        System.out.println(cal("A"));
        System.out.println(cal("AB"));
        System.out.println(cal("AA"));
        System.out.println(cal("D"));
        System.out.println(cal("ZZ"));
        System.out.println(cal("XZ"));

    }
}
