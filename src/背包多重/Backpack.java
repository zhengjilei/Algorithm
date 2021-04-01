package 背包多重;

/**
 * 有N种物品和一个容量为V的背包。第i种物品最多有n[i]件可用，每件费用是c[i]，价值是w[i]。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 */
public class Backpack {
    private static int[] volume = new int[]{2, 4};
    private static int[] value = new int[]{100, 100};

    private static int[] num = new int[]{4, 2};
    private static int capacity = 8;  // 背包容量
    private static int size = 2;  // 物品种数

    private static final int MAX_SIZE = 100; // 转换后的物品种数（每个物品只有一件）

    public static long maxValue() {
        int[] dp = new int[capacity + 1];
        int[] volumeTransfer = new int[MAX_SIZE];
        int[] valueTransfer = new int[MAX_SIZE];

        int count = 0; // 拆分后的物品种数
        for (int i = 0; i < size; i++) {
            // 将第 i 种物品拆分
            // 2进制拆分
            for (int j = 1; j <= num[i]; j <<= 1) {
                volumeTransfer[count] = j * volume[i];
                valueTransfer[count++] = j * value[i];
                num[i] -= j;
            }
            if (num[i] != 0) {
                volumeTransfer[count] = num[i] * volume[i];
                valueTransfer[count++] = num[i] * value[i];
            }
        }

        // 按照 01 背包问题解决
        for (int i = 0; i < count; i++) {
            for (int j = capacity; j >= volumeTransfer[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - volumeTransfer[i]] + valueTransfer[i]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        System.out.println(maxValue());
    }


}
