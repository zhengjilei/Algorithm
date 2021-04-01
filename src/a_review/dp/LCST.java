package a_review.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 最长公共子串（子数组）
 * created by Ethan-Walker on 2019/3/19
 */
public class LCST {
    public int findLength(int[] a, int[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return 0;

        int[][] dp = new int[a.length + 1][b.length + 1];
        int max = 0;
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                }
                // else dp[i][j] = 0
            }
        }

        // 得到其中一个最长子串
        int[] res = new int[max];
        int resIndex = max - 1;
        int i = a.length, j = b.length;
        while (resIndex >= 0) {
            if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                res[resIndex--] = a[i - 1];
                i--;
                j--;
            } else {
                j--;
            }
        }
        System.out.println(Arrays.toString(res));
        return max;
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


    @Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 2, 1};
        int[] b = new int[]{3, 2, 1, 4, 7};
        System.out.println(findLength(a, b));

    }
}
