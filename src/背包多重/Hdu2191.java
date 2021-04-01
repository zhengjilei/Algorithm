package 背包多重;

import java.util.Scanner;

public class Hdu2191 {
    private static final int MAX_SIZE = 1000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] valueTransfer = new int[MAX_SIZE];
        int[] weightTransfer = new int[MAX_SIZE];

        for (int i = 0; i < t; i++) {
            int balance = sc.nextInt(); // 余额
            int size = sc.nextInt();
            int[] weight = new int[size];
            int[] value = new int[size];
            int[] num = new int[size];
            int count = 0;
            for (int j = 0; j < size; j++) {
                value[j] = sc.nextInt();
                weight[j] = sc.nextInt();
                num[j] = sc.nextInt();
                for (int k = 1; k <= num[j]; k <<= 1) {
                    valueTransfer[count] = k * value[j];
                    weightTransfer[count++] = k * weight[j];
                    num[j] -= k;
                }
                if (num[j] != 0) {
                    valueTransfer[count] = num[j] * value[j];
                    weightTransfer[count++] = num[j] * weight[j];
                }
            }
            System.out.println(maxValue(count, balance, valueTransfer, weightTransfer));
        }
    }

    public static long maxValue(int kind, int balance, int[] value, int[] weight) {
        int[] dp = new int[balance + 1];
        for (int i = 0; i < kind; i++) {
            for (int j = balance; j >= value[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - value[i]] + weight[i]);
            }
        }
        return dp[balance];
    }


    /**
     * 求最大重量， 第i种物品的数量进行枚举
     */
    public static long maxValue2(int size, int money, int[] weight, int[] value, int[] num) {
        int[][] dp = new int[size + 1][money + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = value[i]; j <= money; j++) {
                int count = Math.min(num[i], j / value[i]); // 第i种物品最多放的个数
                for (int k = 0; k <= count; k++) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - k * value[i]] + k * weight[i]);
                }
            }
        }
        return dp[size][money];
    }
}
