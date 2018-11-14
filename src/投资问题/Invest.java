package 投资问题;

/**
 * Created by EthanWalker on 2017/12/3.
 */
public class Invest {

    // f[i][j] 表示 向第 i+1 个项目 投资 j 万元获得的利润
    private static int[][] f = new int[][]{{0, 11, 12, 13, 14, 15}, {0, 0, 5, 10, 15, 20}, {0, 2, 10, 30, 32, 40}, {0, 20, 21, 22, 23, 24}};
    private static int[][] profit;  // profit[i][j] 表示向前 i+1 个项目一共投资 j 万元, 能获得的最大利润

    /**
     * 根据投资的金额计算最大投资利润
     *
     * @param investMoney 投资金额
     * @return 最大利润
     */
    public static int maxProfit(int investMoney) {
        profit = new int[f.length][investMoney + 1];

        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j <= investMoney; j++) {
                // 投资 j 万元给 前 i+1 个项目
                if (i == 0) {
                    profit[i][j] = f[i][j];
                    continue;
                }
                // 向第 i+1 个项目投资 x 万元， 计算出最大值
                int max = Integer.MIN_VALUE;

                for (int x = 0; x <= j; x++) {
                    int temp = profit[i - 1][j - x] + f[i][x];
                    if (temp > max) {
                        max = temp;
                    }
                }
                profit[i][j] = max;
            }
        }

        return profit[f.length - 1][investMoney];

    }

    public static void main(String[] args) {
        int profit = maxProfit(5);
        System.out.println(profit);
    }
}
