package 最长公共子序列;

import java.util.Arrays;
import java.util.Scanner;

/**
 * dp[i][j]= m 表示str1[0...i] 和 str[0...j] 最长公共子序列的长度为 m
 * <p>
 * Created by EthanWalker on 2017/11/28.
 */
public class LCS {

    private static char[] a;
    private static char[] b;
    private static int[][] dp;

    /**
     * 递归求 a、b 序列的最长公共子序列
     * 备忘录递归
     *
     * @param i 数组 a 的长度
     * @param j 数组 b 的长度
     * @return
     */
    public static int lcs(int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (i == 0 || j == 0) {
            dp[i][j] = 0;
            return 0;
        }
        if (i == 1 && j == 1) {
            if (a[0] == b[0]) {
                dp[i][j] = 1;
                return 1;
            } else
                return 0;
        }
        if (a[i - 1] == b[j - 1]) {
            dp[i][j] = lcs(i - 1, j - 1) + 1;
        } else {
            dp[i][j] = Math.max(lcs(i - 1, j), lcs(i, j - 1));
        }
        return dp[i][j];

    }

    /**
     * 动态规划迭代实现
     *
     * @return
     */
    public static int lcs1(char[] a1, char[] a2) {
        // 多耗费一点空间，减少临界边的单独设置
        int[][] dp = new int[a1.length + 1][a2.length + 1];
        for (int i = 1; i <= a1.length; i++) {
            for (int j = 1; j <= a2.length; j++) {
                if (a1[i - 1] == a2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[a1.length][a2.length];
    }

    /**
     * 压缩空间至 O(col)
     * 由于 dp[i][j] 依赖  dp[i-1][j-1] +1  dp[i-1][j] dp[i][j-1]
     * 可能依赖左上角的值（考虑是否可以从右到左计算），且同时可能依赖左边的值（必须从左往右计算）
     * <p>
     * 需要一个辅助数组存储上一行的值
     * <p>
     * 时间复杂度: O(row*col) row 是序列 a 的长度，col 是序列 b的长度
     * 空间复杂度: O(col)
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcs2(char[] a, char[] b) {
        int[] dp = new int[b.length + 1];
        int prev;
        // save 的赋值速度总是比 dp 慢一列，为的就是计算 dp[j]时，
        // save[j-1] 存储上一行的 dp[i-1][j-1] 不被覆盖
        for (int i = 1; i <= a.length; i++) {
            prev = dp[0]; // 保留上一行的 dp[0] ，计算 dp[1]可能需要用到
            for (int j = 1; j <= b.length; j++) {
                int tmp = dp[j]; // 上一行该列的值，保存下来，用于下一趟计算
                if (a[i - 1] == b[j - 1]) dp[j] = prev + 1; // 注意：这里是prev
                else {
                    dp[j] = Math.max(dp[j], dp[j - 1]); // 注意这里是 dp[j-1] 而不是 save[j-1]，依赖左边的值，最新更新的值
                }
                prev = tmp; // 保存更新之前的 dp[j]
            }
        }
        return dp[b.length];
    }

    /**
     * 时间复杂度: O(m*n) m 是序列 a 的长度，n 是序列 b的长度
     * 空间复杂度: min{m,n}
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcs3(char[] a, char[] b) {
        int minSize = a.length < b.length ? a.length : b.length;
        int[] dp = new int[minSize + 1];
        int maxSize = 0;
        boolean bLess = false;
        if (minSize == b.length) {
            bLess = true;
            maxSize = a.length;
        } else {
            maxSize = b.length;
        }
        int aIndex = 0, bIndex = 0;
        int prev = 0, tmp;
        for (int i = 1; i <= maxSize; i++) {
            prev = dp[0];
            for (int j = 1; j <= minSize; j++) {
                tmp = dp[j];
                if (bLess) {
                    aIndex = i - 1;
                    bIndex = j - 1;
                } else {
                    aIndex = j - 1;
                    bIndex = i - 1;
                }
                if (a[aIndex] == b[bIndex]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                prev = tmp;
            }
        }
        return dp[minSize];
    }

    /**
     * 返回最长公共子序列中的其中一个
     *
     * @param a
     * @param b
     * @return
     */
    public static int[] lcsSeq(char[] a, char[] b) {
        int[][] dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int maxLength = dp[a.length][b.length];
        int[] seq = new int[maxLength];
        int seqIndex = maxLength - 1;

        int i = a.length, j = b.length;
        while (seqIndex >= 0) {
            if (a[i - 1] == b[j - 1] && dp[i - 1][j - 1] + 1 == dp[i][j]) {
                seq[seqIndex--] = a[i - 1];
                i--;
                j--;
            } else if (dp[i - 1][j] == dp[i][j]) {
                i--;
            } else {
                j--;
            }
        }
        return seq;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aLength = 7;
        int bLength = 6;
        a = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        b = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};

        dp = new int[a.length + 1][b.length + 1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                dp[i][j] = 0;
            }
        }
        int maxLength = lcs(7, 6);
        System.out.println(maxLength);

        int maxLength2 = lcs1(a, b);
        System.out.println(maxLength2);

        System.out.println(Arrays.toString(lcsSeq(a, b)));
    }

}
/*
        7 6
        A B C B D A B
        B D C A B A
*/


