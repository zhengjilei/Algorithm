package 最长公共子序列;

import java.util.Scanner;

/**
 * created by Ethan-Walker on 2019/1/4
 */
public class HDU1159 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String a, b;
        while (sc.hasNext()) {
            a = sc.next();
            b = sc.next();

            System.out.println(lcs(a.toCharArray(), b.toCharArray()));
        }
    }

    public static int lcs(char[] a, char[] b) {
        int[][] dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[a.length][b.length];
    }

    public static int lcs2(char[] a, char[] b) {
        int[] dp = new int[b.length + 1];
        int prev = 0, tmp;
        for (int i = 1; i <= a.length; i++) {
            prev = dp[0];
            for (int j = 1; j <= b.length; j++) {
                tmp = dp[j];
                if (a[i - 1] == b[j - 1]) dp[j] = prev + 1;
                else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = tmp;
            }
        }
        return dp[b.length];
    }

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
}
