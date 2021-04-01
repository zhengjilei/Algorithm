package 剑指offer;

import org.junit.Test;

/**
 * 字符串和正则表达式匹配
 * created by Ethan-Walker on 2018/12/5
 */
public class Q019_PatternMatch {


    /**
     * 正确解法
     * . 匹配一个字符
     * * 匹配 *前面的字符 0..个
     * <p>
     * <p>
     * 匹配 s[sStart] 和 p[pStart]
     *
     * @param s
     * @param p
     * @return
     */
    public boolean match(char[] s, char[] p, int sStart, int pStart) {
        if (sStart == s.length && pStart == p.length) {
            return true;
        }
        if (sStart != s.length && pStart == p.length) { //字符串未匹配完，但是正则表达式串已经匹配完
            return false;
        }
        //  sStart == s.length && pStart != p.length   不确定，示例 "ab" 和 "ab.*" 匹配， "ab" 和 "abcd" 不匹配

        int p2 = pStart + 1;
        if (p2 < p.length && p[p2] == '*') {
            // 第二位为 *
            // 要先判断 sStart , 因为 sStart 可能指向结束位置，s 已经被匹配完
            if (sStart < s.length && (s[sStart] == p[pStart] || p[pStart] == '.')) {
                // 可能匹配 * 前面的字符
                return match(s, p, sStart + 1, pStart) //  匹配当前元素，继续匹配下一个
                        || match(s, p, sStart + 1, pStart + 2) // 匹配到 当前元素为止
                        || match(s, p, sStart, pStart + 2);   // 匹配 0 个
            } else {
                // 不能匹配 * 前面的字符，因为 字符串s 已经被匹配完 或者 s[sStart] 和 p[pStart] 无法匹配
                return match(s, p, sStart, pStart + 2);
            }
        } else {
            //pattern 第二位不等于 *,  故匹配单个字符

            if (sStart >= s.length) { // 例如 a  ab*a
                return false;
            }
            if (p[pStart] == '.' || s[sStart] == p[pStart]) {
                return match(s, p, sStart + 1, pStart + 1);
            } else {
                return false;
            }
        }
    }

    /**
     * 题意理解错误  原题中 * 表示*前面的字符可以出现任意次（包括0）
     * . 匹配一个字符
     * * 匹配 0..多个字符
     * * 有三种情况：
     * (1) 匹配0个字符
     * (2)匹配当前一个字符
     * (3) 匹配了当前一个字符，继续试探匹配后面的字符（还是三种情况）
     * @param s
     * @param pattern
     * @return
     */
    public boolean match2(char[] s, char[] pattern, int sStart, int pStart) {
        if (sStart == s.length && pStart == pattern.length)
            return true;
        if (sStart != s.length && pStart == pattern.length || sStart == s.length && pStart != pattern.length) {
            return false;
        }

        if (pattern[pStart] == '.') {
            return match2(s, pattern, sStart + 1, pStart + 1);
        } else if (pattern[pStart] == '*') {
            return match2(s, pattern, sStart + 1, pStart + 1) // * 只匹配到当前字符为止
                    || match2(s, pattern, sStart + 1, pStart)  // * 匹配当前字符，继续匹配下一个字符
                    || match2(s, pattern, sStart, pStart + 1);     // * 一个字符也不匹配

        } else {
            if (pattern[pStart] == s[sStart]) {
                return match2(s, pattern, sStart + 1, pStart + 1);
            } else {
                return false;
            }
        }
    }

    @Test
    public void testMatch() {
        String s = "a";
        String p = "ab*a";
        System.out.println(match(s.toCharArray(), p.toCharArray(), 0, 0));
    }
}
