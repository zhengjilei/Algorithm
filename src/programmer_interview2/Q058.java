package programmer_interview2;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q058 {

    public int coins(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        return getKind(arr, 0, aim);
    }

    public int getKind(int[] arr, int index, int target) {
        if (target < 0) return 0;
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        } else {
            int res = 0;
            for (int k = 0; k * arr[index] <= target; k++) {
                res += getKind(arr, index + 1, target - k * arr[index]);
            }
            return res;
        }
    }

    //
    public int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int[][] save = new int[arr.length + 1][aim + 1];
        return getKind2(arr, 0, aim, save);
    }

    public int getKind2(int[] arr, int index, int target, int[][] save) {
        if (target < 0) return 0;
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        } else {
            int res = 0;

            for (int k = 0; k * arr[index] <= target; k++) {
                if (save[index + 1][target - k * arr[index]] != 0) {
                    res += save[index + 1][target - k * arr[index]];
                } else
                    res += getKind2(arr, index + 1, target - k * arr[index], save);
            }
            save[index][target] = res;
            return res;
        }
    }


    public int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int[][] dp = new int[arr.length][aim + 1];
        // 凑出 0 的方法数为 1
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j * arr[0] <= aim; j++) {
            dp[0][j * arr[0]] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= arr[i]) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
            }
        }
        return dp[arr.length - 1][aim];
    }

    @Test
    public void test() {
        int[] a = new int[]{5, 10, 25, 1};
        System.out.println(coins(a, 0) + " vs " + coins2(a, 0));
        System.out.println(coins(a, 15) + " vs " + coins2(a, 15));

        int[] b = new int[]{3, 5};
        System.out.println(coins(b, 2) + " vs " + coins2(b, 2));
    }
}
