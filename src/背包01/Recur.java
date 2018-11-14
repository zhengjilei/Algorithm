package 背包01;

/**
 * 递归判断
 * 设有一个背包的容量为 s, 现有 n 件物品，体积分别是 w[i],能否从 n 件物品中选择若干件使得背包恰好装满
 */
public class Recur {
    public static boolean dp(int S, int N, int[] w) {
        // dp[i][j] 表示在容量为 j 的限制下，前i件物品最多放置的容量
        int[][] dp = new int[N + 1][S + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= S; j++) {
                if (w[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + w[i]);
                }
            }
        }
        if (dp[N][S] == S) return true;
        return false;
    }


    public static boolean recur(int s, int n, int[] w) {
        if (s == 0) return true;
        if (s < 0 || (s > 0 && n < 1)) return false;
        if (s > 0 && n >= 1) {
            boolean flag = recur(s, n - 1, w); // 假设该物体不在
            if (flag) return flag;           // 已经找到了结果
            else return recur(s - w[n], n - 1, w);     // 没找到，假设该物体在
        }
        return false;

    }

    public static void main(String[] args) {
        int n = 4;
        int[] w = new int[]{0, 1, 3, 4, 9};
        int s = 17;
        System.out.println(recur(s, n, w));
        System.out.println(dp(s, n, w));

    }
}
