package leetcode;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q221_MaxMatrix {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;

        int max = 0;
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                }
            }
        }

        return max*max;
    }
}
