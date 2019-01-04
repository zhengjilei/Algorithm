package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2019/1/4
 */
public class Q063_MinEditCost {


    /**
     * 将str1 -> str2 的最小代价
     * <p>
     * <p>
     * dp[i][j] 表示将 str1[0...i-1] -> str2[0...j-1] 的最小代价
     * 1. str1==空字符串 -> str2 的代价
     * dp[0][0...j] = j*ic (挨个插入str中的字符)
     * <p>
     * str1-> str2==空字符串
     * dp[1..i][0] = i*dc
     * <p>
     * 2. 将str1[0..i-1] 编辑成 str2[0..j-1]
     * (1)先将 str1[i-1] 删除，然后将 str1[0..i-2] 编辑成 str2[0..j-1]              (dp[i-1][j]+dc)
     * (2)将str[0...i-1] 编辑成 str2[0..j-2], 然后插入 str2[j-1]                   (dp[i][j-1] +ic)
     * (3)str1[i-1]==str2[j-1],直接将 str1[0..i-2]编辑成str2[0..j-2]               (dp[i-1][j-1])
     * (4)str2[i-1]!=str2[j-1],将str1[0..i-2]编辑成str2[0..j-2],然后将 str1[i-1] 替换成 str2[j-1] (dp[i-1][j-1]+rc)
     *
     * @param str1
     * @param str2
     * @param ic   插入一个字符的代价
     * @param dc   删除代价
     * @param rc   替换代价
     * @return
     */
    public int minCost(String str1, String str2, int ic, int dc, int rc) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        // 计算将空字符串 编辑成 str2
        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j * ic;
        }
        // 将 str1 编辑成 空字符串
        for (int i = 1; i <= str1.length(); i++) {
            dp[i][0] = i * dc;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                if (dp[i - 1][j] + dc < dp[i][j]) dp[i][j] = dp[i - 1][j] + dc;
                if (dp[i][j - 1] + ic < dp[i][j]) dp[i][j] = dp[i][j - 1] + ic;

            }
        }
        return dp[str1.length()][str2.length()];
    }

    /**
     * 压缩空间至 O(str2.length())
     */
    public int minCost2(String str1, String str2, int ic, int dc, int rc) {
        int[] dp = new int[str2.length() + 1];
        // 将空字符串变成 str2
        for (int j = 0; j <= str2.length(); j++) {
            dp[j] = j * ic;
        }
        int num = 0;
        int prev = 0, tmp;

        for (int i = 1; i <= str1.length(); i++) {
            // 将 str1 变成空字符串
            prev = dp[0]; // 保存上一行的 dp[0]
            dp[0] = i * dc; // str1-> 空字符串，需要删除 i 个字符
            for (int j = 1; j <= str2.length(); j++) {
                tmp = dp[j];
                // 因为有多次比较，需要反复用到上一行的 dp[j], 故不能立即更新 dp[j], 先赋值给 num
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    num = prev;
                } else {
                    num = prev + rc;
                }
                if (dp[j] + dc < num) num = dp[j] + dc; // dp[i-1][j]
                if (dp[j - 1] + ic < num) num = dp[j - 1] + ic; // dp[i][j-1]

                dp[j] = num;
                prev = tmp;
            }
        }
        return dp[str2.length()];
    }

}
