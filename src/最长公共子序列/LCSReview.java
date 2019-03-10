package 最长公共子序列;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class LCSReview {


    public int lcs1(char[] s1, char[] s2) {
        if (s1 == null || s2 == null || s1.length == 0 || s2.length == 0) return 0;
        int[][] dp = new int[s1.length + 1][s2.length + 1];

        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }


        int maxLen = dp[s1.length][s2.length];
        char[] seq = new char[maxLen];
        int seqIndex = maxLen - 1;

        int i = s1.length, j = s2.length;
        while (seqIndex >= 0) {
            if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                seq[seqIndex--] = s1[i - 1];
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(Arrays.toString(seq));
        return dp[s1.length][s2.length];
    }

    public int lcs2(char[] s1, char[] s2) {
        if (s1 == null || s2 == null || s1.length == 0 || s2.length == 0) return 0;
        int[][] dp = new int[s1.length][s2.length];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int j = 1; j < s2.length; j++) {
            if (dp[0][j - 1] == 1) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = s1[0] == s2[j] ? 1 : 0;
            }
        }
        for (int i = 1; i < s1.length; i++) {
            if (dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = s1[i] == s2[0] ? 1 : 0;
            }
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[s1.length - 1][s2.length - 1];
    }

    // 压缩成列的 长度
    public int lcs3(char[] s1, char[] s2) {
        if (s1 == null || s2 == null || s1.length == 0 || s2.length == 0) return 0;
        int[] dp = new int[s2.length];
        dp[0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < s2.length; i++) {
            if (dp[i - 1] == 1) {
                dp[i] = 1;
            } else {
                dp[i] = s1[0] == s2[i] ? 1 : 0;
            }
        }
        int prev, save;

        for (int i = 1; i < s1.length; i++) {
            dp[0] = dp[i - 1] == 1 ? 1 : (s1[i] == s2[0] ? 1 : 0);
            prev = dp[0];
            for (int j = 1; j < s2.length; j++) {
                save = dp[j];
                if (s1[i] == s2[j]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = save;
            }
        }

        return dp[s2.length - 1];
    }

    // 压缩成列的长度
    public int lcs4(char[] s1, char[] s2) {
        if (s1 == null || s2 == null || s1.length == 0 || s2.length == 0) return 0;
        int[] dp = new int[s2.length + 1];

        int prev, save;
        for (int i = 1; i <= s1.length; i++) {
            prev = dp[0];
            for (int j = 1; j <= s2.length; j++) {
                save = dp[j];
                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                prev = save;
            }
        }

        return dp[s2.length];
    }


    public int lcs5(char[] s1, char[] s2) {
        if (s1 == null || s2 == null || s1.length == 0 || s2.length == 0) return 0;
        boolean colLess = true;
        int minSize = s2.length, maxSize = s1.length;
        if (maxSize < minSize) {
            maxSize = s2.length;
            minSize = s1.length;
            colLess = false;
        }

        int[] dp = new int[minSize + 1];

        int prev, save;
        for (int i = 1; i <= maxSize; i++) {
            prev = dp[0];
            for (int j = 1; j <= minSize; j++) {
                save = dp[j];
                if (colLess) {
                    if (s1[i - 1] == s2[j - 1]) {
                        dp[j] = prev + 1;
                    } else {
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }
                } else {
                    if (s1[j - 1] == s2[i - 1]) {
                        dp[j] = prev + 1;
                    } else {
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }
                }
                prev = save;
            }
        }

        return dp[minSize];

    }

    @Test
    public void test() {
        int aLength = 7;
        int bLength = 6;
        char[] a = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] b = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};


        System.out.println(lcs1(a, b));

        int maxLength3 = lcs2(a, b);
        System.out.println(maxLength3);

        int maxLength2 = lcs3(a, b);
        System.out.println(maxLength2);

        int maxLength = lcs4(a, b);
        System.out.println(maxLength);

        System.out.println(lcs5(a, b));


    }
}
