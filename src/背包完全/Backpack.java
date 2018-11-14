package 背包完全;

/**
 * 有N种物品和一个容量为V的背包，每种物品都有无限件可用。第i种物品的费用是c[i]，价值是w[i]。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 * Created by EthanWalker on 2017/12/3.
 */
public class Backpack {


    private static int[] volume = new int[]{0, 1, 2, 5, 6, 3, 4};
    private static int[] value = new int[]{0, 2, 6, 15, 18, 9, 12};

    private static int capacity = 13;  // 背包容量
    private static int size = 6;  // 物品种数

    public static int maxValue() {
        int[][] m = new int[size + 1][capacity + 1]; // m[i][j] 表示 从前 i+1 种物品中挑选, 填入容量为 j 的背包中, 能得到的最大价值

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < volume[i]) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(m[i - 1][j], m[i][j - volume[i]] + value[i]); // 注意：这里是 m[i][j-volume[i]]
                }
            }
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= capacity; j++) {
                System.out.printf("%6d", m[i][j]);
            }
            System.out.println();
        }
        print(m);
        return m[size][capacity];
    }

    // 一维数组处理
    public static int maxValue2() {
        int[] m = new int[capacity + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = volume[i]; j <= capacity; j++) {
                m[j] = Math.max(m[j], m[j - volume[i]] + value[i]);
            }
        }
        return m[capacity];
    }

    // 输出背包中放入的物品以及每种物品的个数
    public static void print(int[][] dp) {
        int j = capacity;
        int[] count = new int[size + 1];
        for (int i = size; i >= 1; i--) {
            if (dp[i][j] > dp[i - 1][j]) {
                // 说明第 i 种物品存在
                while (volume[i] <= j && dp[i][j] - value[i] == dp[i][j - volume[i]]) {
                    j = j - volume[i];
                    count[i]++;
                }
            }
        }
        System.out.print("各物品的个数： ");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%5d", count[i]);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int maxValue = maxValue();
        System.out.println("最大价值为: " + maxValue);
        System.out.println(maxValue2());
    }
}
