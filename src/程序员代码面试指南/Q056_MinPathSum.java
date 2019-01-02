package 程序员代码面试指南;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/1
 */
public class Q056_MinPathSum {

    /**
     * 时间复杂度: O(m*n)
     * 空间复杂度: O(m*n)
     * m>=1 n>=1
     * dp[m][n] = arr[m][n] + min(dp[m-1][n],dp[m][n-1] )
     */

    public int minPath(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        dp[0][0] = arr[0][0];
        // 处理边角行、边角列
        for (int j = 1; j < arr[0].length; j++) {
            dp[0][j] = arr[0][j] + dp[0][j - 1];
        }
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = arr[i][0] + dp[i - 1][0];
        }
        // i/j 从 1 开始
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[arr.length - 1][arr[0].length - 1];

    }


    /**
     * dp[m][n] 依赖于 dp[m-1][n] 和 dp[m][n-1]
     * 每次计算一行，只保留一行的dp数据，进行迭代
     * 压缩空间至  O(col)
     *
     * @return
     */
    public int minPath2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        // 初始化第一行
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            dp[0] = grid[i][0] + dp[0]; // 一定不要忘记, 第一个元素由上一行到达
            for (int j = 1; j < col; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j - 1], dp[j]);
            }
        }
        return dp[col - 1];
    }

    /**
     * 每次计算一列，只保留一列的数据
     * 压缩空间至 O(row)
     *
     * @param arr
     * @return
     */
    public int minPath3(int[][] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0][0];
        // 先计算第 1 列的数据
        for (int i = 1; i < arr.length; i++) {
            dp[i] = arr[i][0] + dp[i - 1];
        }
        // 纵向遍历
        for (int j = 1; j < arr[0].length; j++) {
            dp[0] = dp[0] + arr[0][j];
            for (int i = 1; i < arr.length; i++) {
                dp[i] = arr[i][j] + Math.min(dp[i], dp[i - 1]);
            }
        }
        return dp[arr.length - 1];
    }

    /**
     * row >= col, 压缩成列的长度，按行遍历
     * row < col,压缩成行的长度，按列遍历
     * 空间复杂度： min{row,col}
     *
     * @param arr
     * @return
     */
    public int minPath4(int[][] arr) {
        int row = arr.length, col = arr[0].length;
        int minSize = Math.min(row, col);
        int maxSize = Math.max(row, col);

        int[] dp = new int[minSize];
        dp[0] = arr[0][0];
        boolean rowFirst = maxSize == col; // 列数更多，按行遍历
        for (int i = 1; i < minSize; i++) {
            dp[i] = dp[i - 1] + (rowFirst ? arr[0][i] : arr[i][0]);
        }
        // 行大于列，按列遍历; 列大于行，按行遍历
        // 以 maxSize = row ,按行遍历示例
        for (int i = 1; i < maxSize; i++) {
            dp[0] = dp[0] + (rowFirst ? arr[i][0] : arr[0][i]);
            for (int j = 1; j < minSize; j++) {
                dp[j] = (rowFirst ? arr[i][j] : arr[j][i]) + Math.min(dp[i], dp[i - 1]);
            }
        }
        return dp[minSize - 1];
    }

    /**
     * 求最小路径, 依赖二维数组的 dp
     *
     * @param arr
     */
    public void printMinPath(int[][] arr, int[][] dp) {

        ArrayDeque<String> stack = new ArrayDeque<>();
        int i = arr.length, j = arr[0].length;
        int min = dp[i][j];

        stack.push("(" + i + "," + j + ")");
        while (i != 0 || j != 0) {
            if (i > 0 && j > 0) {
                if (dp[i][j] == arr[i][j] + dp[i - 1][j]) {
                    i = i - 1;
                } else {
                    j = j - 1;
                }
            } else if (i == 0) {
                // j!=0
                j = j - 1;
            } else {
                // i!=0 j==0
                i = i - 1;
            }
            stack.push("(" + i + "," + j + ")");
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

}
