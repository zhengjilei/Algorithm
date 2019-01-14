package 程序员代码面试指南;


import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q082_Reverse {

    /**
     * 方法一：用Java 的split() 方法
     *
     * @param str
     * @return
     */
    public String reverse(String str) {
        String[] s = str.split(" ");
        for (int i = 0; i < s.length / 2; i++) {
            swap(s, i, s.length - 1 - i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s[0]);
        for (int i = 1; i < s.length; i++) {
            sb.append(" " + s[i]);
        }
        return sb.toString();
    }

    public void swap(String[] s, int i, int j) {
        String temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    @Test
    public void test() {
        String str = "dog loves pig";
        System.out.println(reverse(str));

        String str2 = "I'm a student.";
        System.out.println(reverse(str2));


        char[] chs1 = "dog loves pig".toCharArray();
        reverse(chs1);

        char[] chs2 = "I'm a student.".toCharArray();
        reverse(chs2);

        System.out.println(Arrays.toString(chs1));
        System.out.println(Arrays.toString(chs2));
    }

    /**
     * 方法二: 先整体逆序，再局部逆序
     *
     * @param chs
     */
    public void reverse(char[] chs) {
        for (int i = 0; i < chs.length / 2; i++) {
            swap(chs, i, chs.length - i - 1);
        }

        int start = 0, end = 0;
        while (end < chs.length) {
            while (end < chs.length && chs[end] != ' ') {
                end++;
            }
            // 对 start, end-1 进行逆序
            int len = end - start;
            int sum = end - 1 + start; // 关于中点对称的两个下标之和
            for (int i = start; i < start + len / 2; i++) {
                swap(chs, i, sum - i);
            }
            start = ++end;
        }
    }

    void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }

    // 补充题目
    public void rotateStr(char[] chs, int size) {
        if (chs == null || chs.length <= 1 || size <= 0 || size >= chs.length) return;
        // 先整体逆序，再将前左右半区各自逆序

        for (int i = 0; i < chs.length / 2; i++) {
            swap(chs, i, chs.length - i - 1);
        }

        // 逆转左半区
        int leftLen = chs.length - size;
        for (int i = 0; i < leftLen / 2; i++) {
            swap(chs, i, leftLen - i - 1);
        }

        // 逆转右半区, leftLen  为右半区起点
        int sum = chs.length - 1 + leftLen;
        int limit = size / 2 + leftLen;
        for (int i = leftLen; i < limit; i++) {
            swap(chs, i, sum - i);
        }

    }


    @Test
    public void test2() {
        char[] chs = {'A', 'B', 'C', 'D', 'E'};
        rotateStr(chs, 3);
        System.out.println(Arrays.toString(chs));

    }

}
