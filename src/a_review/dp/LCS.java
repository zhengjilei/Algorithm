package a_review.dp;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class LCS {
    public static int lcs(char[] s1, char[] s2) {
        if (s1 == null || s2 == null) return 0;
        if (s1.length == 0 || s2.length == 0) return 0;
        int s1Len = s1.length;
        int s2Len = s2.length;
        int[][] dp = new int[s1Len + 1][s2Len + 1];

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 输出其中一个 最长公共子序列
        char[] seq = new char[dp[s1Len][s2Len]];
        int seqIndex = dp[s1Len][s2Len] - 1;
        int i = s1Len, j = s2Len;
        while (dp[i][j] > 0) {
            if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                seq[seqIndex--] = s1[i - 1];
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(Arrays.toString(seq));
        return dp[s1Len][s2Len];
    }

    public static int lcs2(char[] s1, char[] s2) {
        if (s1 == null || s2 == null) return 0;
        if (s1.length == 0 || s2.length == 0) return 0;
        int s1Len = s1.length;
        int s2Len = s2.length;
        if (s1Len < s2Len) {
            char[] tmp = s1;
            s1 = s2;
            s2 = tmp;
            s1Len = s1.length;
            s2Len = s2.length;
        }

        int[] dp = new int[s2Len + 1];
        int prev, cur;
        for (int i = 1; i <= s1Len; i++) {
            prev = dp[0];
            for (int j = 1; j <= s2Len; j++) {
                cur = dp[j];
                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = cur;
            }
        }
        return dp[s2Len];
    }


    public static void main(String[] args) {
        int aLength = 7;
        int bLength = 6;
        char[] a = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] b = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};


        System.out.println(lcs(a, b));

        System.out.println(lcs2(a, b));
//
//        int maxLength2 = lcs3(a, b);
//        System.out.println(maxLength2);
//
//        int maxLength = lcs4(a, b);
//        System.out.println(maxLength);
//
//        System.out.println(lcs5(a, b));
    }

}
