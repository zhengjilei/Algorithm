package 背包01;

import java.util.Scanner;

public class Hdu2602 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int N = sc.nextInt();
            int V = sc.nextInt();
            int[] volume = new int[N + 1];
            int[] value = new int[N + 1];
            for (int j = 1; j <= N; j++) {
                value[j] = sc.nextInt();
            }
            for (int j = 1; j <= N; j++) {
                volume[j] = sc.nextInt();
            }
            System.out.println(maxValue2(N, V, volume, value));
        }
    }

    public static long maxValue2(int N, int V, int[] volume, int[] value) {
        long[] dp = new long[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= volume[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - volume[i]] + value[i]);
            }
        }
        return dp[V];

    }

    public static long maxValue(int N, int V, int[] volume, int[] value) {
        long[][] dp = new long[N + 1][V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if (volume[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - volume[i]] + value[i]);
                }
            }
        }
        return dp[N][V];
    }
}
