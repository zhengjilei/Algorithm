package leetcode;

/**
 * word1 转换成 word2 所使用的最少操作数 。
 * 1. 编辑
 * 2. 删除
 * 3. 替换
 * created by Ethan-Walker on 2019/1/4
 */
public class Q072_EditDistance {


    /**
     * dp[i][j] 含义: 将 str1[0..i-1] 变成 str2[0..j-1] 的最少操作数
     * 1. 处理边界:空字符串
     * 空字符串 编辑成 str2[0..j]
     * dp[0][j] = j;
     * str1[0..i] 编辑成 空字符串
     * dp[i][0] = i;
     * 2. 求 dp[i][j]
     * (1) str1[i-1]==str2[j-1] ，将 str1[0..i-2] 编辑成 str2[0..j-2] 即可  =>  dp[i-1][j-1]
     *     str1[i-1]!=str2[j-1] , 将 str1[0..i-2] 编辑成 str2[0..j-2], 再替换 str1[i-1] -> str2[j-1]   => dp[i-1][j-1]+1
     * (2) 删除 str1[i-1], 将 str1[0..i-2] 编辑成 str2[0..j-1]  =>  dp[i-1][j]+1
     * (3) 将 str1[0..i-1] 编辑成 str2[0..j-2], 再插入 str2[j-1]  => dp[i][j-1] +1
     *
     * @return
     */
    public int minDistance(String str1, String str2) {

        if (str1 == null || str2 == null) return 0;
        int s1Len = str1.length();
        int s2Len = str2.length();

        if (s1Len == 0) return str2.length();
        if (s2Len == 0) return str1.length();

        int[][] dp = new int[s1Len + 1][s2Len + 1];
        for (int j = 0; j <= s2Len; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= s1Len; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
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


    public int minDistance2(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        if (str1.length() == 0) return str2.length();
        else if (str2.length() == 0) return str1.length();

        int[] dp = new int[str2.length() + 1];
        for (int j = 0; j <= str2.length(); j++) {
            dp[j] = j;
        }
        int prev , tmp;
        int num;
        for (int i = 1; i <= str1.length(); i++) {
            prev = dp[0];  // 保存上一行 dp[0]
            dp[0] = i; // 计算当前行 dp[0] ，将 str1[0..i-1] 依次删除 转换成 空字符串 需要 i 步
            for (int j = 1; j <= str2.length(); j++) {
                tmp = dp[j];

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    num = prev;
                } else {
                    num = prev + 1;
                }
                if (dp[j] + 1 < num) num = dp[j] + 1;
                if (dp[j - 1] + 1 < num) num = dp[j - 1] + 1;
                dp[j] = num;
                prev = tmp;
            }
        }
        return dp[str2.length()];
    }
}
