package 最长公共子串;

import org.junit.Test;

/**
 * longest common substring
 * 最长公共子串
 * created by Ethan-Walker on 2019/1/4
 */
public class LCST {

    /**
     * dp[i][j] 表示以 str1[i] str2[j] 为结尾的最长公共子串长度
     * dp[i][j]=
     * 1. str1[i]==str2[j]  dp[i-1][j-1]+1
     * 2. str1[i]!=str2[j]  0
     *
     * @param str1
     * @param str2
     * @return
     */
    public int lcst(String str1, String str2) {

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int maxLength = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }  // else 部分可省略，默认值就是0

                if (dp[i][j] > maxLength) maxLength = dp[i][j];
            }
        }
        return maxLength;
    }

    /**
     * 空间压缩至 str2.length
     *
     * @param str1
     * @param str2
     * @return
     */
    public int lcst2(String str1, String str2) {
        int[] dp = new int[str2.length() + 1];
        int maxLength = 0;
        int prev = 0, tmp;
        for (int i = 1; i <= str1.length(); i++) {
            prev = dp[0];
            for (int j = 1; j <= str2.length(); j++) {
                tmp = dp[j];
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = 0; // 不能省略，否则该行对应的 dp[j] 是上一行的值，而不是0
                }
                if (dp[j] > maxLength) maxLength = dp[j];
                prev = dp[j];
            }
        }
        return maxLength;
    }


    /**
     * dp[i][j] 只依赖 dp[i-1][j-1]
     * 空间压缩至 O(1)
     * <p>
     * 按照 dp[i][j] -> dp[i+1][[j+1] -> dp[i+2][j+2] 方向计算
     *
     * @param str1
     * @param str2
     * @return
     */
    public int lcst3(String str1, String str2) {
        int x, y, val;
        int maxLength = 0;
        // 从每一列第一个元素开始
        for (int j = 1; j <= str2.length(); j++) {
            x = 1;
            y = j;
            val = 0;
            while (x <= str1.length() && y <= str2.length()) {
                if (str1.charAt(x - 1) == str2.charAt(y - 1)) {
                    val = val + 1;
                    if (val > maxLength) maxLength = val;
                } else {
                    val = 0;
                }
                x++;
                y++;
            }
        }
        // 从每一行第一个元素开始
        for (int i = 1; i <= str1.length(); i++) {
            x = i;
            y = 1;
            val = 0;
            while (x <= str1.length() && y <= str2.length()) {
                if (str1.charAt(x - 1) == str2.charAt(y - 1)) {
                    val = val + 1;
                    if (val > maxLength) maxLength = val;
                } else {
                    val = 0;
                }
                x++;
                y++;
            }
        }
        return maxLength;
    }

    /**
     * 返回最长公共子串
     *
     * @param str1
     * @param str2
     * @return
     */
    public String getLcst(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int maxLength = 0;
        int maxLenEndIndexI = 0;
        // 由于子串是连续的，最大子串长度也已经知道，故找出子串结束的位置即可（可以是在 str1上的结束位置，也可以是str2上的）

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }  // else 部分可省略，默认值就是0

                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                    maxLenEndIndexI = i - 1; // 在 str1 上的结束位置
                }
            }
        }
        return str1.substring(maxLenEndIndexI - maxLength + 1, maxLenEndIndexI + 1);
    }

    /**
     * 多个字符串的最长公共子串
     * 思路: 前两个求出最长公共子串，子串然后和第三个求最长公共子串
     *
     * @param strs
     * @return
     */
    public int lcst(String[] strs) {

        String a = strs[0], b = strs[1];
        String lcst = getLcst(a, b);
        for (int i = 2; i < strs.length; i++) {
            lcst = getLcst(lcst, strs[i]);
        }
        return lcst.length();
    }

    @Test
    public void test() {
        System.out.println(getLcst("abcde", "bebcd"));
    }


}
