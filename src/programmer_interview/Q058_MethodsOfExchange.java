package programmer_interview;

/**
 * created by Ethan-Walker on 2019/1/3
 */
public class Q058_MethodsOfExchange {

    // 价格为 0 时，凑出的方法数为 1

    /**
     * 暴力递归
     * 注意 当 price =0 时，返回 1
     * 时间复杂度 非常高，和 currency[] 中的面值有关,
     * 最差情况下将 price 分成 price 次递归
     * 时间复杂度最差 O(price^n)  n为 currency.length
     * 空间复杂度: O(n)
     *
     * @param currency
     * @param price
     * @return
     */
    public int count(int[] currency, int price) {
        if (currency == null || currency.length == 0 || price < 0)
            return 0;
        return recur(currency, 0, price);
    }

    public int recur(int[] arr, int index, int price) {
        if (index == arr.length) {
            return price == 0 ? 1 : 0;
        }
        int count = 0;
        for (int i = 0; i * arr[index] <= price; i++) {
            // 货币 arr[index] 选择的数量
            count += recur(arr, index + 1, price - i * arr[index]);
        }
        return count;
    }

    /**
     * 记忆化搜索，暴力递归的问题在于每次递归得到的值都没有保存下来
     * map[i][j] 保存每次递归的结果，表示 从a[i]~ 开始的所有货币种类，凑出价格 j的方法数
     * <p>
     * m[i][j] = 0 表示没有计算过， m[i][j] = -1 表示计算过，但是结果为 0
     * 时间复杂度: O(n*price^2)
     * 空间复杂度: O(n*price)
     */

    public int count2(int[] currency, int price) {
        if (currency == null || currency.length == 0 || price < 0)
            return 0;
        int[][] map = new int[currency.length][price + 1];
        for (int i = 0; i < currency.length; i++) {
            map[i][0] = 1;
        }
        return recur2(currency, 0, price, map);
    }

    public int recur2(int[] currency, int index, int price, int[][] map) {
        if (index == currency.length) {
            return price == 0 ? 1 : 0;
        }

        int count = 0;
        int t = 0;
        for (int i = 0; i * currency[index] <= price; i++) {
            t = map[i + 1][price - i * currency[index]];
            if (t != 0) {
                count += t == -1 ? 0 : t; // t==-1 说明已经计算过，但是值为 0 ；为什么不赋值为0呢，因为初始值为0，表示没有计算过
            } else {
                count += recur2(currency, index + 1, price - i * currency[index], map);
            }
        }
        map[index][price] = count == 0 ? -1 : count;
        return count;
    }


    /**
     * 动态规划
     * dp[i][j] 表示从 a[0]~a[i] 这些货币中凑出价格为 j 的方法数
     * dp[i][j] = dp[i-1][j] + dp[i-1][j-1*currency[i]] + dp[i-1]dp[j-2*currency[i]]+...
     * <p>
     * 时间复杂度: O(n*price ^2) 最内部循环<=price
     * 空间复杂度: O(n*price)
     *
     * @param currency
     * @param price
     * @return
     */
    public int count3(int[] currency, int price) {

        int[][] dp = new int[currency.length][price + 1];
        for (int i = 0; i < currency.length; i++) dp[i][0] = 1;    // 价格为 0 时，凑出的方法数为 1
        for (int j = 0; j <= price; j += currency[0]) dp[0][j] = 1; // 凡是 currency[0] 的倍数 都是1种

        for (int i = 1; i < currency.length; i++) {
            for (int j = 1; j <= price; j++) {
                // currency[i] 选的数量
                for (int k = 0; k * currency[i] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * currency[i]];
                }
            }
        }
        return dp[currency.length - 1][price];
    }

    /**
     * 动态规划优化
     * dp[i][j] = dp[i-1][j] + dp[i-1][j-1*currency[i]] + dp[i-1]dp[j-2*currency[i]]+...
     * 对等号右边的除了第一项的所有项进行分析，之和等于 dp[i][j-currency[i]]
     * 即 dp[i][j] = dp[i-1][j] + dp[i][j-currency[i]]
     * 依赖同列上一行的值 和同行左边的值
     * <p>
     * dp[j] = dp[j] + dp[j-currency[i]]
     * <p>
     * 压缩空间至 O(price+1)
     * <p>
     * 时间复杂度: O(n*price)
     * 空间复杂度: O(price)
     *
     * @param currency
     * @param price
     * @return
     */
    public int count4(int[] currency, int price) {
        if (currency == null || currency.length == 0 || price < 0) return 0;

        int[] dp = new int[price + 1];
        // 第一行，表示货币只有 currency[0]
        for (int j = 0; j <= price; j += currency[0]) {
            dp[j] = 1;
        }
        for (int i = 1; i < currency.length; i++) {
            for (int j = 0; j <= price; j++) {
                // j=0 时,dp[j] = dp[j] = 1
                if (j - currency[i] >= 0) {
                    dp[j] += dp[j - currency[i]];
                }
                // else  dp[j] = dp[j] 等于上一行数据
            }
        }
        return dp[price];
    }


}
