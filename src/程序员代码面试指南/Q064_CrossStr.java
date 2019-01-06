package 程序员代码面试指南;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/1/6
 */
public class Q064_CrossStr {

    /**
     * 构建 dp[str1.length()+1][str2.length()+1]
     * dp[i][j]=true/false 表示 aim[0..i+j-1] 能否由 str1[0..i-1] 和 str2[0..j-1] 交错组成
     * <p>
     * 1. dp[0][j] , 表示 str1 是空字符串，aim 能由 str2 组成的前提是 aim 的前 j 个和 str2 前j个字符完全相等
     * 2. dp[i][0] , 同理，aim 能由str1 组成的前提是 aim 和 str1 前 i 个字符完全相等
     * 3. dp[i][j]  如果 aim[0..i+j-1] 能由 str1[0..i-1] 和 str2[0..j-1] 交替组成
     * 即 dp[i][j] == true, 则aim[i+j-1] 必定是 str1[i-1] 或者 str2[j-1]
     * 如果是 str1[i-1] ，则aim[0..i+j-2] 必定可以由 str1[0..i-2] 和 str2[0..j-1] 组成（即 dp[i-1][j] = true）
     * 如果是 str2[j-1]，同理 dp[i][j-1] ==true
     * 上述两种情况满足一种即可
     *
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public boolean isCross(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) return false;
        if (str1.length() + str2.length() != aim.length()) return false;
//        char[] chs1 = str1.toCharArray();
//        char[] chs2 = str2.toCharArray(); 不需要转换成 char[],String 类中有 value[] 存储，访问是随机访问，不会影响效率

        boolean[][] dp = new boolean[str1.length() + 1][str2.length() + 1];


        dp[0][0] = true; // aim 是空字符串，可以由空字符 str1 、str2 组成
        for (int j = 1; j <= str2.length(); j++) {
            if (str2.charAt(j - 1) != aim.charAt(j - 1)) { // 注意是 j-1, 不是 j
                break;  // 跳出，后面全为默认值 false
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length(); i++) {
            if (str1.charAt(i - 1) != aim.charAt(i - 1)) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if ((dp[i - 1][j] && str1.charAt(i - 1) == aim.charAt(i + j - 1))
                        || (dp[i][j - 1] && str2.charAt(j - 1) == aim.charAt(i + j - 1))) {
                    dp[i][j] = true;
                }
                // 默认为 false
            }
        }
        for (int i = 0; i <= str1.length(); i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[str1.length()][str2.length()];
    }


    /**
     * 空间压缩至 str2.length
     *
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public boolean isCross2(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) return false;
        if (str1.length() + str2.length() != aim.length()) return false;
        boolean[] dp = new boolean[str2.length() + 1];
        dp[0] = true;
        for (int j = 1; j <= str2.length(); j++) {
            if (str2.charAt(j - 1) != aim.charAt(j - 1)) {
                break;
            }
            dp[j] = true;
        }

        for (int i = 1; i <= str1.length(); i++) {
            // 计算 dp[i][0] ，判断 str1[0..i-1] 和 aim[0..i-1] 完全相等
            if (dp[0] && str1.charAt(i - 1) == aim.charAt(i - 1)) {
                // dp[0] 保持为 true
            } else {
                dp[0] = false;
            }
            for (int j = 1; j <= str2.length(); j++) {
                if ((dp[j] && str1.charAt(i - 1) == aim.charAt(i + j - 1))
                        || (dp[j - 1] && str2.charAt(j - 1) == aim.charAt(i + j - 1))) {
                    dp[j] = true;
                } else {
                    dp[j] = false; // 压缩空间时，不能考虑默认值省略分支赋值
                }
            }
        }
        return dp[str2.length()];

    }


    /**
     * 空间压缩至 O(min{str1.length,str2.length})
     *
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public boolean isCross3(String str1, String str2, String aim) {

        if (str1 == null || str2 == null || aim == null) return false;
        if (str1.length() + str2.length() != aim.length()) return false;

        int minLen = str1.length() < str2.length() ? str1.length() : str2.length();

        String minStr = str1, maxStr = str2;
        if (minLen == str2.length()) {
            minStr = str2;
            maxStr = str1;
        }

        boolean[] dp = new boolean[minLen + 1];
        dp[0] = true;
        for (int i = 1; i <= minLen; i++) {
            if (minStr.charAt(i - 1) != aim.charAt(i - 1)) {
                break;
            }
            dp[i] = true;
        }

        for (int i = 1; i <= maxStr.length(); i++) {
            if (dp[0] && maxStr.charAt(i - 1) == aim.charAt(i - 1)) {
                // 保持 dp[0] = true
            } else {
                dp[0] = false;
            }
            for (int j = 1; j <= minLen; j++) {
                if ((dp[j] && maxStr.charAt(i - 1) == aim.charAt(i + j - 1))
                        || (dp[j - 1] && minStr.charAt(j - 1) == aim.charAt(i + j - 1))) {
                    dp[j] = true;
                } else {
                    dp[j] = false;
                }
            }
        }
        return dp[minLen];

    }

    @Test
    public void test() {
        String a = "aabcc";
        String b = "dbbca";
        String c = "aadbbcbcac";

        System.out.println(isCross(a, b, c));
    }
}
