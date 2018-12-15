package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 给定一个英文句子，反转所有的单词的顺序，单词内部不进行反转
 * created by Ethan-Walker on 2018/12/14
 */
public class Q058_ReverseSentence {

    /**
     * 使用 Java 提供的 api
     *
     * @param str
     * @return
     */
    public String reverseSentence(String str) {
        String[] s = str.split(" ");
        if (s == null || s.length <= 1) {
            return str;
        }

        for (int i = 0; i < s.length / 2; i++) {
            swap(s, i, s.length - 1 - i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (i == 0) sb.append(s[i]);
            else {
                sb.append(" ").append(s[i]);
            }
        }
        return sb.toString();
    }

    public void swap(String[] s, int i, int j) {
        String t = s[i];
        s[i] = s[j];
        s[j] = t;
    }


    public String reverseSentence2(String str) {
        char[] chs = str.toCharArray();
        // 反转整个字符串
        reversePart(chs, 0, chs.length - 1);

        int start = 0, end = 0;
        while (start < chs.length) {
            while (end < chs.length && chs[end] != ' ') {
                end++;
            }
            reversePart(chs, start, end - 1);
            start = end + 1;
            end = start;
        }

        return new String(chs);
    }

    private void reversePart(char[] chs, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (end + start + 1) / 2;
        for (int i = start, j = 0; i < mid; i++, j++) {
            swap(chs, i, end - j);
        }
        return;
    }


    public void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }

    @Test
    public void test() {
        System.out.println(reverseSentence2("  "));
    }
}
