package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/21
 */
public class Q010_PatternMatch {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        return match(s.toCharArray(), 0, p.toCharArray(), 0);
    }

    public boolean match(char[] s, int sStart, char[] p, int pStart) {
        if (sStart == s.length && pStart == p.length) return true;
        if (sStart < s.length && pStart == p.length) return false; // 模式串已经匹配完，但字符串还未匹配完

        if (pStart + 1 < p.length && p[pStart + 1] == '*') {
            // 第二位是 *

            // s[i] 是否能和模式字符串 *前面的字符匹配, 即是否可能用到 *
            if (sStart < s.length && (s[sStart] == p[pStart] || p[pStart] == '.')) {
                return match(s, sStart, p, pStart + 2)  // * 匹配 0 个元素
                        || match(s, sStart + 1, p, pStart + 2)// 只匹配当前一个, 注意模式串移动 2位
                        || match(s, sStart + 1, p, pStart);  // 继续匹配
            } else {
                return match(s, sStart, p, pStart + 2);
            }

        } else {
            // 模式串第二位不是 * , 只要比较当前位即可
            if (sStart >= s.length) return false; // 字符串实际上已经比较完，但是模式串还没有匹配完

            if (p[pStart] == '.' || p[pStart] == s[sStart]) return match(s, sStart + 1, p, pStart + 1);
            else return false;

        }
    }

    @Test
    public void test() {
        System.out.println(isMatch("aaa", ".*"));

    }

}
