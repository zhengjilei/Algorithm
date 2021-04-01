package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/27
 */
public class Q583_DeleteStr {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        int lcsLen = longestCommonSeq(word1, word2);

        return word1.length() - lcsLen + word2.length() - lcsLen;
    }

    public int longestCommonSeq(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        for (int col = 1; col < s2.length(); col++) {
            if (dp[0][col - 1] == 0 && s1.charAt(0) == s2.charAt(col)) {
                dp[0][col] = 1;
            } else {
                dp[0][col] = dp[0][col - 1];
            }
        }

        for (int row = 1; row < s1.length(); row++) {
            if (dp[row - 1][0] == 0 && s2.charAt(0) == s1.charAt(row)) {
                dp[row][0] = 1;
            } else {
                dp[row][0] = dp[row - 1][0];
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }


    public int longestCommonSeq2(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public int longestCommonSeq3(String s1, String s2) {
        int[] dp = new int[s2.length() + 1];
        int prev, tmp;

        for (int i = 1; i <= s1.length(); i++) {
            prev = dp[0];
            for (int j = 1; j <= s2.length(); j++) {
                tmp = dp[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                prev = tmp;
            }
        }
        return dp[s2.length() - 1];
    }

    public int longestCommonSeq4(String s1, String s2) {
        int[] dp = new int[s1.length() + 1];
        int prev, tmp;
        for (int i = 1; i <= s2.length(); i++) {
            prev = dp[0];
            for (int j = 1; j <= s1.length(); j++) {
                tmp = dp[j];
                if (s1.charAt(j - 1) == s2.charAt(i - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = tmp;
            }
        }
        return dp[s1.length()];
    }

    @Test
    public void test() {
        System.out.println(minDistance("a", "bgfafr"));
        System.out.println(minDistance("bdf", "abcd"));
    }
}
