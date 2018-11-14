package 背包01;

public class Backpack1 {


    /**
     * 时间、空间复杂度 O(N*V)
     *
     * @param N
     * @param V
     * @param volume
     * @param value
     * @return
     */
    public static double maxValue(int N, int V, int[] volume, double[] value) {

        double[][] dp = new double[N + 1][V + 1]; // dp[i][j] 表示前 i个物品放在体积为 j 的背包中，最大的物品价值

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if (volume[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - volume[i]] + value[i]);

                }
            }
        }
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                System.out.printf("%8.2f", dp[i][j]);
            }
            System.out.println();
        }
        backtrack(N, V, dp, dp[N][V], volume);
        return dp[N][V];
    }

    private static void backtrack(int N, int V, double[][] dp, double maxValue, int[] volume) {
        boolean[] inBags = new boolean[N + 1];
        for (int i = N; i >= 1; i--) {
            if (dp[i][V] > dp[i - 1][V]) {
                // 说明第 i 个物品加入到背包中了
                inBags[i] = true;
                V -= volume[i]; // 减去物品 i 的体积，得到剩余包中的物品总体积
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.print(inBags[i] + "\t");
        }
        System.out.println();
    }


    // 一维数组处理
    public static double maxValue2(int N, int V, int[] volume, double[] value) {
        double[] max = new double[V + 1]; // maxValue[i] 表示体积为 i 时可取得的最大值

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= volume[i]; j--) {
                max[j] = Math.max(max[j], max[j - volume[i]] + value[i]);
            }
        }
        return max[V];
    }


    public static void main(String[] args) {
        int[] capacity = new int[]{0, 2, 2, 6, 5, 4};
        double[] price = new double[]{0, 6, 3, 5, 4, 6};

        System.out.println(maxValue(5, 10, capacity, price));
        System.out.println(maxValue2(5, 10, capacity, price));

    }
}
