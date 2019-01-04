package leetcode;


import org.junit.Test;

import java.util.Arrays;

/**
 * 最长公共子串（最长公共连续子序列）
 * created by Ethan-Walker on 2019/1/4
 */
public class Q718_LCST {

    public int findLength(int[] a, int[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return 0;
        return lcst(a, b);

    }

    public int lcst(int[] str1, int[] str2) {

        int[][] dp = new int[str1.length + 1][str2.length + 1];
        int maxLength = 0;
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > maxLength) maxLength = dp[i][j];
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return maxLength;
    }

    /**
     * 空间压缩至 str2.length
     *
     * @param str1
     * @param str2
     * @return
     */
    public int lcst2(int[] str1, int[] str2) {
        int[] dp = new int[str2.length + 1];
        int prev = 0, tmp;
        int maxLength = 0;
        for (int i = 1; i <= str1.length; i++) {
            prev = dp[0];
            for (int j = 1; j <= str2.length; j++) {
                tmp = dp[j];
                if (str1[i - 1] == str2[j - 1]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = 0; // 这里不能省略 0 赋值，否则会使得dp[j] 引用了上一行的数据，出错
                }
                prev = tmp;
                if (dp[j] > maxLength) maxLength = dp[j];
            }
        }
        return maxLength;

    }

    @Test
    public void testA() {
        int[] a = {1, 0, 0, 0, 1};
        int[] b = {1, 0, 0, 1, 1};
        System.out.println(lcst2(a, b));
    }

    public int lcst3(int[] str1, int[] str2) {
        int val;
        int x, y;
        int maxLength = 0;
        // 从每一列第一个元素开始
        for (int j = 0; j < str2.length; j++) {
            x = 0;
            y = j;
            val = 0;
            while (x < str1.length && y < str2.length) {
                if (str1[x] == str2[y]) {
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
        for (int i = 1; i < str1.length; i++) {
            x = i;
            y = 0;
            val = 0;
            while (x < str1.length && y < str2.length) {
                if (str1[x] == str2[y]) {
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

    @Test
    public void test() {
        int[] a = {0, 1, 1, 1, 1};
        int[] b = {1, 0, 1, 0, 1};
        System.out.println(findLength(a, b));
    }
}
