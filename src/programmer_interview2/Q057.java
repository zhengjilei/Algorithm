package programmer_interview2;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class Q057 {
    public int minCount(int[] value, int aim) {

        int[][] dp = new int[value.length][aim + 1];

        for (int j = 1; j <= aim; j++) {
            if (j % value[0] == 0) {
                dp[0][j] = j / value[0];
            } else {
                dp[0][j] = Integer.MAX_VALUE;
            }

        }

        for (int i = 1; i < value.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= value[i] && dp[i][j - value[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - value[i]] + 1);
                }
            }
        }
        return dp[value.length - 1][aim] == Integer.MAX_VALUE ? -1 : dp[value.length - 1][aim];
    }


    public int minCount2(int[] value, int aim) {
        int[] dp = new int[aim + 1];
        for (int j = 1; j <= aim; j++) {
            if (j % value[0] == 0) {
                dp[j] = j / value[0];
            } else {
                dp[j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 1; i < value.length; i++) {
            for (int j = 1; j <= aim; j++) {
                if (j >= value[i] && dp[j - value[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - value[i]] + 1);
                }
            }
        }
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }

    @Test
    public void test() {
        int[] arr = {5, 2, 3};
        int n = 12;
        for (int i = 0; i < 100; i++) {
            if (minCount(arr, i) != minCount2(arr, i)) {
            System.out.println(i);
            }
        }
//        System.out.println(minCount(arr, n));
//        System.out.println(minCount2(arr, n));
    }

}
