package 剑指offer;

/**
 * created by Ethan-Walker on 2018/12/11
 */
public class Q047_MaxValue {

    /**
     * 动态规划
     * 空间复杂度: O(row*col)
     * 时间复杂度: O(row*col)
     *
     * @param value
     * @return
     */
    public int getMaxValue(int[][] value) {
        int row = value.length;
        int col = value[0].length;

        int targetX = row - 1, targetY = col - 1;

        int[][] dp = new int[row][col];

        dp[0][0] = value[0][0];
        // 计算边缘情况
        for (int y = 1; y < col; y++) {
            dp[0][y] = dp[0][y - 1] + value[0][y];
        }

        for (int x = 1; x < row; x++) {
            dp[x][0] = dp[x - 1][0] + value[x][0];
        }

        for (int x = 1; x < row; x++) {
            for (int y = 1; y < col; y++) {
                dp[x][y] = Math.max(dp[x - 1][y], dp[x][y - 1]) + value[x][y];
            }
        }
        return dp[targetX][targetY];
    }


    /**
     * 分析 dp[i][j] = max(dp[i-1][j],dp[i][j-1]) +value[i][j]
     * dp[j] 仍然表示从0,0 到 i,j 的最大价值，只不过这里省略了 i，采取覆盖的方式
     * dp[i][j] 只依赖于左边的数值 dp[i][j-1] 和 上面的数值 dp[i-1][j]
     * <p>
     * 每计算一行 dp[j] ，从左往右计算， dp[j] = max{dp[j-1],dp[j]}
     * max{dp[j-1],dp[j]}中的 dp[j-1] 相当于 dp[i][j-1] 已经被上一步更新成第 i 行对应的 j-1 列的值
     * dp[j] 相当于上一行的 dp[i-1][j] 值
     *
     *
     *  时间复杂度: O(row*col)
     *  空间复杂度: O(col)
     * @param value
     * @return
     */
    public int getMaxValue2(int[][] value) {

        int row = value.length;
        int col = value[0].length;
        int[] dp = new int[col];
        int left = 0, up = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                left = 0;
                up = 0;
                if (i > 0) {
                    up = dp[j];
                }
                if (j > 0) {
                    left = dp[j - 1];
                }
                dp[j] = Math.max(left, up) + value[i][j];
            }
        }
        return dp[col - 1];
    }
}
