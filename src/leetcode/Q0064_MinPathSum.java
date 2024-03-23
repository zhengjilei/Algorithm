package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/2
 */
public class Q0064_MinPathSum {

    /**
     * dp[i][j] =  grid[i][j] +min{dp[i-1][j],dp[i][j-1]}
     * <p>
     * 压缩成列的长度
     * 按行遍历
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            dp[0] = grid[i][0] + dp[0]; // 一定不要忘记, 由上一行到达
            for (int j = 1; j < col; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j - 1], dp[j]);
            }
        }
        return dp[col - 1];
    }

    /**
     * row >= col, 压缩成列的长度，按行遍历
     * row < col,压缩成行的长度，按列遍历
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        boolean rowFirst = row >= col ? true : false;
        int minSize = Math.min(row, col);
        int maxSize = Math.max(row, col);

        int[] dp = new int[minSize];
        dp[0] = grid[0][0];
        for (int j = 1; j < minSize; j++) {
            dp[j] = dp[j - 1] + (rowFirst ? grid[0][j] : grid[j][0]);
        }
        for (int i = 1; i < maxSize; i++) {
            dp[0] = dp[0] + (rowFirst ? grid[i][0] : grid[0][i]);
            for (int j = 1; j < minSize; j++) {
                dp[j] = (rowFirst ? grid[i][j] : grid[j][i]) + Math.min(dp[j], dp[j - 1]);
            }
        }
        return dp[minSize - 1];
    }

    @Test
    public void test() {
        int[][] a = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(a));
    }
}
