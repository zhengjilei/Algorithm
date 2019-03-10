package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q005_LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int[][] dp = new int[s.length()][s.length()];

        int start = 0;
        int end = 0;
        int maxLen = 1;
        // 长度为 1
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        // 长度为 2
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 0;
            if (dp[i][i + 1] > maxLen) {
                start = i;
                end = i + 1;
                maxLen = 2;
            }
        }

        int r;
        // 长度 >=3
        for (int len = 3; len <= s.length(); len++) {

            for (int l = 0; l <= s.length() - len; l++) {
                r = l + len - 1;
                if (dp[l + 1][r - 1] > 0 && s.charAt(l) == s.charAt(r)) {
                    // 判断子串是否是 回文子串，子串不是的话 s[l] == s[r] 也没用
                    dp[l][r] = dp[l + 1][r - 1] + 2;

                    if (dp[l][r] > maxLen) {
                        start = l;
                        end = r;
                        maxLen = len;
                    }
                } else {
                    dp[l][r] = 0;
                }

            }
        }
        return s.substring(start, end + 1);
    }

    @Test
    public void test() {
        System.out.println(longestPalindrome("abcda"));
    }
}
