package leetcode2;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q0005 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int size = s.length();
        int start = 0, end = 0;// 最长回文子串的起始位置
        int max = 1;

        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i + 1 < size; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 0;
            if (dp[i][i + 1] > max) {
                start = i;
                end = i + 1;
                max = 2;
            }
        }

        for (int len = 3; len <= size; len++) {
            for (int i = 0; i + len - 1 < size; i++) {
                // 求dp[i][i+len-1]
                int r = i + len - 1;
                // dp[i+1][r-1] >0 才能说明子串是回文子串
                if (dp[i + 1][r - 1] > 0 && s.charAt(i) == s.charAt(r)) {
                    dp[i][r] = dp[i + 1][r - 1] + 2;
                    if (dp[i][r] > max) {
                        max = dp[i][r];
                        start = i;
                        end = r;
                    }
                } // else dp[i][r]=0
            }
        }
        return s.substring(start, end + 1);
    }

    @Test
    public void test() {
        System.out.println(longestPalindrome("ccc"));
        System.out.println(longestPalindrome("abcda"));
    }
}
