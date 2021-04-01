package 剑指offer;

/**
 * 股票的最大利润
 * created by Ethan-Walker on 2018/12/16
 */
public class Q063_MaxProfitInStocks {

    public int getMaxProfitInStocks(int[] price) {
        if (price == null || price.length < 2) return 0;
        int maxProfit = Integer.MIN_VALUE;

        int minValue = price[0];
        // 记录股票卖出点之前的最小值，卖出点和最小值的差值，就是当前股票卖出点的最大利润

        int t = 0;
        // i 为股票卖出点
        for (int i = 1; i < price.length; i++) {
            t = price[i] - minValue; // 卖出点和之前所有可能的买入点的最小值的差值
            if (t > maxProfit) {
                maxProfit = t;
            }
            if (price[i] < minValue) {
                minValue = price[t];
            }
        }
        return maxProfit;
    }


    /**
     * O(n^2)
     *
     * @param price
     * @return
     */
    public int simpleSolve(int[] price) {
        int maxProfit = Integer.MIN_VALUE;
        int n = price.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (price[j] - price[i] > maxProfit) {
                    maxProfit = price[j] - price[i];
                }
            }
        }
        return maxProfit;
    }

}
