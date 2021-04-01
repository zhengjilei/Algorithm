package 背包完全;

import java.util.Scanner;

/**
 * 求最小价值，故dp数组初始值赋值为 最大值
 * 注意：要求能恰好重量等于容器容量，故需要判断
 */
public class Hdu1114 {
    public static long minValue(int N, int W, int[] weight, int[] value) {
        //重量限定，返回最小价值
        long[] dp = new long[W + 1];
        for (int i = 1; i <= W; i++) dp[i] = Integer.MAX_VALUE; // dp[0]=0 其他均初始化为最大值

        for (int i = 0; i < N; i++) {
            for (int j = weight[i]; j <= W; j++) {
                dp[j] = Math.min(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        if (dp[W] >= Integer.MAX_VALUE) {
            return -1;
        }
        return dp[W];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int n = sc.nextInt();
            int[] weight = new int[n];
            int[] value = new int[n];
            for (int j = 0; j < n; j++) {
                value[j] = sc.nextInt();
                weight[j] = sc.nextInt();
            }
            long result = minValue(n, b - a, weight, value);
            if (result == -1) {
                System.out.println("This is impossible.");
            } else {
                System.out.println("The minimum amount of money in the piggy-bank is " + result + ".");
            }
        }
    }
}
