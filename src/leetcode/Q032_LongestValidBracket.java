package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/26
 */
public class Q032_LongestValidBracket {

    /**
     * dp[i]=k表示以s[i]结尾的最长有效括号子串长度为 k
     * dp[0] = 0
     * <p>
     * 求dp[i]
     * 当 s[i]==')':
     * dp[i-1] == k ,表示以 s[i-1] 结尾的最长有效子串长度为 k, 即 s[i-k, i-k , ..., i-1] 是以s[i-1]的最长有效子串
     *
     * 计算以 s[i] 结尾的最长有效子串长度，判断s[i-k-1] 是否是 (, 是则和 s[i] 匹配，以s[i]结尾的最长有效子串
     *              dp[i] = dp[i-1]+2
     * <p>
     * 当将s[i-k-1] 加入最长有效子串中时，能将前面的 s[x... i-k-2] 并入最长子串
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) return 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int max = 0;
        int preIndex = 0;
        for (int i = 1; i < dp.length; i++) {
            if (s.charAt(i) == ')') {
                preIndex = i - 1 - dp[i - 1];
                if (preIndex >= 0 && s.charAt(preIndex) == '(') {
                    dp[i] = dp[i - 1] + 2; // ) 和以 str[i-1] 结尾的最长有效括号子串前面一个字符(匹配

                    if (preIndex - 1 > 0) { // s[...,preIndex-1] [preIndex, i]  可能链接成一个更大的最长有效括号子串
                        dp[i] += dp[preIndex - 1];
                    }
                }
            }

            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(longestValidParentheses("()())"));
        System.out.println(longestValidParentheses(")())"));
        System.out.println(longestValidParentheses("(())"));
        System.out.println(longestValidParentheses("((())"));
        System.out.println(longestValidParentheses("((()()())"));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses("()(())"));
    }
}
