package 剑指offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 输入一个整数 n, 输出从 1,2,3, 到   n位整数的最大值
 * created by Ethan-Walker on 2018/12/4
 */
public class Q017_Print1ToN {


    /**
     * 字符串 + 1处理
     *
     * @param n
     */
    void print(int n) {

        if (n <= 0) return;
        String str = "1";
        System.out.println(str);
        int cnt = (int) Math.pow(10, n);

        // 还有 10^n - 2 个数
        for (int i = 0; i < cnt - 2; i++) {
            str = cnt(str);
            System.out.println(str);
        }

    }

    /**
     * 字符串加 1
     *
     * @param s
     * @return
     */
    public String cnt(String s) {

        char[] chs = s.toCharArray();
        int end = chs.length - 1;

        while (end >= 0) {
            if (chs[end] < '9') {
                chs[end]++;
                break;
            } else {
                chs[end] = '0';
                end--;
            }
        }
        if (end == -1) {
            // 说明是  99..9
            StringBuilder sb = new StringBuilder("1");
            sb.append(chs);
            return sb.toString();
        }
        return new String(chs);
    }


    void print2(int n) {
        if (n <= 0) return;
        char[] chs = new char[n];
        recur(chs, 0);
    }

    /**
     * 回溯法
     * 设定 index 位置上的值
     *
     * @param chs
     * @param index
     */
    void recur(char[] chs, int index) {
        if (index == chs.length) {
            out(chs);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            chs[index] = (char) (i + '0'); //设定 index 位置上的值
            recur(chs, index + 1);
            // 不需要恢复，因为是顺序设置，下一次设置会覆盖掉之前的值
        }
    }

    public void out(char[] chs) {
        int i = 0;
        for (i = 0; i < chs.length; i++) {
            if (chs[i] != '0') {
                break;
            }
        }
        if (i < chs.length) {
            System.out.println(new String(chs, i, chs.length - i));
        }
    }

    @Test
    public void test() {
        print2(0);
    }
}
