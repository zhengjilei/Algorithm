package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class Q718 {
    public int findLength(int[] a, int[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return 0;
        int[][] dp = new int[a.length + 1][b.length + 1];

        int maxLen = 0;
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                    }
                }
            }
        }
        return maxLen;
    }

    public int findLength2(int[] a, int[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return 0;
        int maxSize = a.length, minSize = b.length;
        boolean colLess = true;
        if (maxSize < minSize) {
            maxSize = b.length;
            minSize = a.length;
            colLess = false;
        }

        int[] dp = new int[minSize + 1];
        int prev, save;
        int maxLen = 0;
        int aIndex, bIndex;
        for (int i = 1; i <= maxSize; i++) {
            prev = dp[0] = 0;
            for (int j = 1; j <= minSize; j++) {
                save = dp[j];
                if (colLess) {
                    aIndex = i - 1;
                    bIndex = j - 1;
                } else {
                    aIndex = j - 1;
                    bIndex = i - 1;
                }
                if (a[aIndex] == b[bIndex]) {
                    dp[j] = prev + 1;
                    if (dp[j] > maxLen) {
                        maxLen = dp[j];
                    }
                } else {
                    dp[j] = 0;
                }
                prev = save;

            }
        }


        return maxLen;
    }


}
