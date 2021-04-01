package programmer_interview;

import org.junit.Test;

/**
 * 最小货币数
 * created by Ethan-Walker on 2019/1/2
 */
public class Q057_MinCountOfCurrency {

    /**
     * 总价为 price, 问最少需要多少个货币
     * <p>
     * dp[i][j] = min{dp[i-1][j-k*currency[i]] + k}  k>=0
     * dp[i][j] = min{dp[i-1][j], min{dp[i-1][j-currency[i]-(k-1)*currency[i]]+k-1+1}}
     * 右边 min{dp[i-1][j-currency[i]-y*currency[i]] +y}(y>=0)  等价于 dp[i-1][j-currency[i]]
     * 故
     * dp[i][j] = min{dp[i-1][j] , dp[i][j-currency[i]]+1}
     * 注意: dp[i][j-currency[i]] 有可能凑不出来(最大值)，故会导致溢出，计算之前先判断是否是最大值
     * <p>
     * 时间复杂度: O(n*m) n 为货币的种类，m 为总价
     * 空间复杂度: O(n*m)
     *
     * @param currency 货币的种类，每种货币不限定数量
     * @return
     */
    public int minCount(int[] currency, int price) {

        int[][] dp = new int[currency.length][price + 1];
        // dp[i][j] 表示在前a[0]~a[i] 种货币中，凑出总价为 j 的最小货币数(凑不出来，设置为max)
        Integer max = Integer.MAX_VALUE;
        // dp[i][0] 全部为 0 ，总价为 0, 需要 0 张货币

        // 价格 j 如果凑不出来 ,dp[i][j] 应设置为 max, 不然求 min 时会出错
        for (int j = 1; j <= price; j++) {
            dp[0][j] = j % currency[0] == 0 ? j / currency[0] : max;
        }
        int leftPrice = 0, min;

        for (int i = 1; i < currency.length; i++) {
            for (int j = 1; j <= price; j++) {
                if (j - currency[i] >= 0 && dp[i][j - currency[i]] != max) { // dp[i][j-currency[i]] 有可能凑不出来(最大值)，故会导致溢出，
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - currency[i]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[currency.length - 1][price] != max ? dp[currency.length - 1][price] : -1;
    }


    /**
     * 思路一样，空间压缩
     * dp[i][j] = min{dp[i-1][j], dp[i][j-currency[i]] +1}
     * <p>
     * 空间复杂度: O(m) m 为价格，按行遍历
     * <p>
     * 转变之后:
     * dp[j] = min{dp[j],dp[j-currency[i]]+1}
     * <p>
     * 能按列遍历，使复杂度为O(n)吗？
     * 可以：但很麻烦
     * 按行遍历时dp[j]=dp[j-currency[i]] 依赖左边的值，currency[i] 刚好和 i 对应，直接取即可
     * 而按列遍历时，currency[i]不确定，意味着依赖前面若干列的值，前面若干列的值都得保存
     * <p>
     * 为什么之前的可以用按列来压缩？
     * 因为 dp[i][j] = min{dp[i-1][j] ,dp[i][j-1]}
     * 不管是按行 按列 都只依赖上一行 或者是 上一列的，不会跨越多列
     *
     * @param currency
     * @param price
     * @return
     */
    public int minCount2(int[] currency, int price) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[price + 1];
        dp[0] = 0;
        for (int i = 1; i <= price; i++) {
            dp[i] = i % currency[0] == 0 ? i / currency[0] : max;
        }

        for (int i = 1; i < currency.length; i++) {
            for (int j = 1; j <= price; j++) {
                if (j - currency[i] >= 0 && dp[j - currency[i]] != max) {
                    dp[j] = Math.min(dp[j], dp[j - currency[i]] + 1);
                }
                // else  dp[j] 不变，等于上一行的数据
            }
        }
        return dp[price] != max ? dp[price] : -1;
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
