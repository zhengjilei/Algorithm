package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q072 {

    public int minDistance(String s1, String s2) {
        if (s1 == null || s2 == null) return -1;
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len == 0) return s2Len; // 添加 s2Len 个数
        if (s2Len == 0) return s1Len; // 删除 s1Len 个数

        int[][] dp = new int[s1Len + 1][s2Len + 1];
        for (int j = 0; j <= s2Len; j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i <= s1Len; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i - 1][j] + 1 < dp[i][j]) dp[i][j] = dp[i - 1][j] + 1;
                if (dp[i][j - 1] + 1 < dp[i][j]) dp[i][j] = dp[i][j - 1] + 1;
            }
        }
        return dp[s1Len][s2Len];
    }

    // s1 编辑成 s2 == s2 编辑成 s1 的最小距离
    public int minDistance2(String s1, String s2) {
        if (s1 == null || s2 == null) return -1;
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len == 0) return s2Len; // 添加 s2Len 个数
        if (s2Len == 0) return s1Len; // 删除 s1Len 个数

        if (s1Len < s2Len) {
            String t = s1;
            s1 = s2;
            s2 = t;
            s1Len = s1.length();
            s2Len = s2.length();
        }
        // s1 指向长字符串
        int[] dp = new int[s2Len + 1];
        for (int j = 0; j <= s2Len; j++) {
            dp[j] = j;
        }
        int prev, cur, num;
        for (int i = 1; i <= s1Len; i++) {
            prev = dp[0];
            dp[0] = i; // dp[i][0] = i
            for (int j = 1; j <= s2Len; j++) {
                cur = dp[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    num = prev;
                } else {
                    num = prev + 1;
                }
                if (dp[j] + 1 < num) num = dp[j] + 1;
                if (dp[j - 1] + 1 < num) num = dp[j - 1] + 1;
                prev = cur;
                dp[j] = num;
            }
        }
        return dp[s2Len];
    }
}
