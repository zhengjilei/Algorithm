package 最长公共子序列;

import java.util.Arrays;
import java.util.Scanner;

/**
 * dp[i][j]= m 表示 str1[0...i] 和 str[0...j] 最长公共子序列的长度为 m
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

    // 考虑边界
    public int longestCommonSeq(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        for (int col = 1; col < s2.length(); col++) {
            if (dp[0][col - 1] == 0 && s1.charAt(0) == s2.charAt(col)) {
                dp[0][col] = 1;
            } else {
                dp[0][col] = dp[0][col - 1];
            }
        }

        for (int row = 1; row < s1.length(); row++) {
            if (dp[row - 1][0] == 0 && s2.charAt(0) == s1.charAt(row)) {
                dp[row][0] = 1;
            } else {
                dp[row][0] = dp[row - 1][0];
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
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
     * 需要两个辅助变量 prev save存储dp[i-1][j-1]的值
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
        // prev 保存上一行的 dp[i-1][j-1] , 计算 dp[j]时，先保存 save = dp[j] ,计算之后 将 save的值赋给 prev
        // prev 存储上一行的 dp[i-1][j-1] 不被覆盖
        for (int i = 1; i <= a.length; i++) {
            prev = dp[0]; // 保留上一行的 dp[0] ，计算 dp[1]可能需要用到
            for (int j = 1; j <= b.length; j++) {
                int save = dp[j]; // 计算 dp[j]之前保存下来，用于下一趟计算
                if (a[i - 1] == b[j - 1]) dp[j] = prev + 1; // 注意：这里是prev
                else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = save; // 保存更新之前的 dp[j]
            }
        }
        return dp[b.length];
    }

    /**
     * 时间复杂度: O(m*n) m 是序列 a 的长度，n 是序列 b的长度
     * 空间复杂度: min{m,n}
     *
     * @return
     */
    public static int lcs3(char[] s1, char[] s2) {
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


