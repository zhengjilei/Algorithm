package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q516_LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        if (s == null) return 0;
        int len = s.length();
        if (len <= 1) return len;

        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i <= len - 2; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1; // 注意：这里变成 1了
        }
        int r;
        for (int gap = 3; gap <= len; gap++) {
            for (int i = 0; i <= len - gap; i++) {
                r = i + gap - 1;
                if (s.charAt(i) == s.charAt(r)) {
                    dp[i][r] = dp[i + 1][r - 1] + 2;
                }
                dp[i][r] = Math.max(dp[i][r], Math.max(dp[i + 1][r], dp[i][r - 1]));
            }
        }
        return dp[0][len - 1];
    }

    @Test
    public void test() {
        System.out.println(longestPalindromeSubseq("abcdef"));
    }
}
