package leetcode2;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class Q0064 {

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        dp[0][0] = grid[0][0];
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }

    public int minSum(int[][] grid) {
        int[] dp = new int[grid[0].length];

        dp[0] = grid[0][0];
        for (int j = 1; j < grid[0].length; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < grid[0].length; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[grid[0].length - 1];
    }


    public int minSum2(int[][] grid) {
        int min, max;
        boolean rowFirst = true;

        if (grid[0].length <= grid.length) {
            min = grid[0].length;
            max = grid.length;
        } else {
            rowFirst = false;
            min = grid.length;
            max = grid[0].length;
        }
        int[] dp = new int[min];
        dp[0] = grid[0][0];

        for (int j = 1; j < min; j++) {
            dp[j] = dp[j - 1] + (rowFirst ? grid[0][j] : grid[j][0]);
        }

        for (int i = 1; i < max; i++) {
            dp[0] = dp[0] + (rowFirst ? grid[i][0] : grid[0][i]);
            for (int j = 1; j < min; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + (rowFirst ? grid[i][j] : grid[j][i]);
            }
        }
        return dp[min - 1];
    }

    @Test
    public void test() {
        minSum2(new int[][]{{9, 1, 4, 8}});
    }

}
