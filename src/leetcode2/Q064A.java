package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q064A {
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];

        dp[0][0] = grid[0][0];
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public int minPathSum2(int[][] grid) {
        int row = grid.length, col = grid[0].length;

        int min = col, max = row;
        boolean topToDown = true;
        if (min > max) {
            max = col;
            min = row;
            topToDown = false;
        }

        int[] dp = new int[min];
        dp[0] = grid[0][0];

        for (int j = 1; j < min; j++) {
            dp[j] = dp[j - 1] + (topToDown ? grid[0][j] : grid[j][0]);
        }

        for (int i = 1; i < max; i++) {
            dp[0] = dp[0] + (topToDown ? grid[i][0] : grid[0][i]);
            for (int j = 1; j < min; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + (topToDown ? grid[i][j] : grid[j][i]);
            }
        }
        return dp[min - 1];

    }


}
