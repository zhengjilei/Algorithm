package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class Q718 {
    public int findLength(int[] a, int[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return 0;
        int[][] dp = new int[a.length + 1][b.length + 1];

        int maxLen = 0;
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                    }
                }
            }
        }
        return maxLen;
    }

    public int findLength2(int[] a, int[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return 0;

        if (a.length < b.length) {
            int[] tmp = a;
            a = b;
            b = tmp;
        }

        int[] dp = new int[b.length + 1];
        int max = 0;
        int prev, cur;
        for (int i = 1; i <= a.length; i++) {
            prev = dp[0];
            for (int j = 1; j <= b.length; j++) {
                cur = dp[j];
                if (a[i - 1] == b[j - 1]) {
                    dp[j] = prev + 1;
                    if (dp[j] > max) {
                        max = dp[j];
                    }
                } else {
                    dp[j] = 0;
                }
                prev = cur;
            }
        }
        return max;
    }


}
