package programmer_interview;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/7
 */
public class Q065_DragonGame {

    /**
     * 到达每一个位置之前就应该保证当前血量大于等于 1
     * <p>
     * dp[i][j] 表示（到达 i,j 之前） 即将 经过点(i,j) ，且最终能够到达 (row-1,col-1) ，此时所具备的最少血量
     * 是到达点 (i,j) 之前的走完前一步的剩余血量，由于每一步走完都要>=1 ，所以 dp[i][j]>=1
     * <p>
     * dp[row-1][col-1] 表示即将经过终点 (row-1,col-1) 最终到达终点，所需的最少血量
     * dp[row-1][col-1] + maze[row-1][col-1] >=1
     * => dp[row-1][col-1] >= 1-maze[row-1][col-1]  ①
     * 又因为 dp[row-1][col-1] 是上一步走完之后剩下的血量，故限制 dp[row-1][col-1]>=1 ②
     * 由①② 可得
     * dp[row-1][col-1] =
     * 1. 1-maze[row-1][col-1]  (结果大于等于1时)
     * 2. 1 （上面结果小于等于1时）
     * <p>
     * 同理，根据dp[i][j] 推导 dp[i-1][j]
     * dp[i-1][j] + maze[i-1][j] == dp[i][j]
     * => d[i-1][j] = dp[i][j] - maze[i-1][j]
     * 同时限制 dp[i-1][j] >=1
     * 故 dp[i-1][j] =
     * 1. dp[i][j] - maze[i-1][j] （>=1时）
     * 2. 1 （上面结果等于1 时）
     * <p>
     * 注意，如果maze={{100}},结果应该是 1，即到达每一个位置之前就应该保证血量大于等于 1
     *
     * @param maze
     * @return
     */
    public int calculateMinimumHP(int[][] maze) {
        if (maze == null) return 0;
        int row = maze.length, col = maze[0].length;

        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = 1 - maze[row - 1][col - 1] >= 1 ? 1 - maze[row - 1][col - 1] : 1;
        int t;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {

                if (i != 0) {
                    // 计算dp[i-1][j]
                    t = dp[i][j] - maze[i - 1][j];
                    if (t < 1) t = 1;  // dp[i-1][j] 要始终大于等于 1
                    dp[i - 1][j] = t; // 计算上一行 不需要判断，因为是第一次计算
                }
                if (j != 0) {
                    // 计算dp[i][j-1]
                    t = dp[i][j] - maze[i][j - 1];
                    if (t < 1) t = 1;
                    if (dp[i][j - 1] == 0 || t < dp[i][j - 1]) {
                        // dp[i-1][j] == 0 说明尚未计算过
                        // !=0 说明 dp[i-1][j] 曾经通过 dp[i-1][j+1] 计算过，两种方向计算选择最小值
                        dp[i][j - 1] = t;
                    }
                }
            }
        }


        return dp[0][0];
    }


    public int calculateMinimumHP2(int[][] maze) {
        if (maze == null) return 0;
        int row = maze.length, col = maze[0].length;

        int[] dp = new int[col];
        dp[col - 1] = 1 - maze[row - 1][col - 1];
        if (dp[col - 1] < 1) {
            dp[col - 1] = 1;
        }

        int t = 0;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                // 先计算 dp[j-1]
                if (j != 0) {
                    t = dp[j] - maze[i][j - 1];
                    if (t < 1) t = 1;
                    if (dp[j - 1] == 0 || t < dp[j - 1]) {
                        dp[j - 1] = t;
                    }
                }
                if (i != 0) {
                    t = dp[j] - maze[i - 1][j];
                    if (t < 1) t = 1;
//                    if (dp[j] == 0 || t < dp[j]) {  必须删除该条件，因为上一行的 dp[j] 是第一次计算
//                    且dp[j] 此时是下一行的值，并不是想要比较的上一行值
                    dp[j] = t;
//                 }
                }
            }
        }
        return dp[0];

    }

    @Test
    public void test() {
        int[][] a = {
                {-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(calculateMinimumHP(a));
        System.out.println(calculateMinimumHP2(a));
    }

    public int calculateMinimumHP3(int[][] maze) {
        if (maze == null) return 0;
        int row = maze.length, col = maze[0].length;
        int minSize = row < col ? row : col;
        int maxSize = row > col ? row : col;
        int[] dp = new int[minSize];
        boolean byRow = maxSize == row;

        int t = 1 - maze[row - 1][col - 1];
        if (t >= 1) {
            dp[minSize - 1] = t;
        } else {
            dp[minSize - 1] = 1;
        }
        for (int i = maxSize - 1; i >= 0; i--) {
            for (int j = minSize - 1; j >= 0; j--) {
                // 先计算 d[i][j-1], 再计算dp[i-1][j]
                // 必须先计算dp[j-1] 不然会覆盖掉 dp[j]
                if (j != 0) {
                    t = dp[j] - (byRow ? maze[i][j - 1] : maze[j - 1][i]);
                    if (t < 1) t = 1;
                    if (dp[j - 1] == 0 || t < dp[j - 1]) {
                        dp[j - 1] = t;
                    }
                }
                // 计算 dp[i-1][j]
                if (i != 0) {
                    t = dp[j] - (byRow ? maze[i - 1][j] : maze[j][i - 1]);
                    if (t < 1) t = 1;
                    dp[j] = t;
                }
            }
        }
        return dp[0];
    }

}