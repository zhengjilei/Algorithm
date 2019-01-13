package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/13
 */
public class Q077_Replace {

    /**
     * 方法一：kmp 匹配
     * <p>
     * 缺点： from 作为 pattern, 无重复串，kmp 不能加速查找
     *
     * @param str
     * @param from
     * @param to
     * @return
     */
    public String replace(String str, String from, String to) {
        int searchResult = -1, searchStart = 0;
        int[] next = getNext(from);
        char[] chs = str.toCharArray();
        do {
            searchResult = kmp(str, from, searchStart, next);
            if (searchResult == -1) break;
            searchStart = searchResult + from.length();

            for (int i = searchResult; i < searchStart; i++) {
                chs[i] = 0;
            }

        } while (searchStart < str.length());

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < str.length()) {
            if (chs[index] == 0) {
                while (index < str.length() && chs[index] == 0) {
                    index++;
                }
                sb.append(to);
            } else {
                sb.append(chs[index]);
                index++;
            }
        }
        return sb.toString();
    }

    /**
     * 从 str 的start 开始查找 pattern
     *
     * @param str
     * @param pattern
     * @param start
     * @return
     */
    public int kmp(String str, String pattern, int start, int[] next) {

        int i = start, j = 0;

        while (i < str.length() && j < pattern.length()) {
            if (j == -1 || str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) return i - j;

        return -1;
    }

    public int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0, k = -1;
        next[j] = k;
        while (j < pattern.length() - 1) {
            // 计算next[j+1]
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }


    public String replace2(String str, String from, String to) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int j = 0;
        char[] chs = str.toCharArray();
        while (index < str.length()) {
            if (chs[index] == from.charAt(j)) {
                while (index < str.length() && j < from.length() && chs[index] == from.charAt(j)) {
                    index++;
                    j++;
                }
                if (j == from.length()) {
                    int i = 1;
                    while (i <= j) {
                        chs[index - i] = 0;
                        i++;
                    }
                }// 即便不匹配，也不需要回退
            } else {
                index++;
            }
            j = 0;
        }
        int i = 0;
        while (i < chs.length) {
            if (chs[i] == 0) {
                while (i < chs.length && chs[i] == 0) i++;
                sb.append(to);
            } else {
                sb.append(chs[i]);
                i++;
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(replace("123abc", "abc", "4567"));
        System.out.println(replace("123", "abc", "4567"));
        System.out.println(replace("123abcabc", "abc", "X"));
        System.out.println(replace("123abcabcdabc", "abc", "X"));
        System.out.println(replace("12abcabca4", "abc", "X"));
        System.out.println("---------------------------------");
        System.out.println(replace2("123abc", "abc", "4567"));
        System.out.println(replace2("123", "abc", "4567"));
        System.out.println(replace2("123abcabc", "abc", "X"));
        System.out.println(replace2("123abcabcdabc", "abc", "X"));
        System.out.println(replace2("12abcabca4", "abc", "X"));

    }
}
