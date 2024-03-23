package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/3
 */
public class Q0122_MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {

            if (prices[i] < prices[i - 1]) { // 只要有下跌了，就要考虑把前一天的高价卖出
                if (prices[i - 1] > min) { // 如果前一天的比前面买入时的大，则卖出可以获利
                    // 卖出可以获利
                    profit += (prices[i - 1] - min);
                }
                min = prices[i]; // 设置最小值 是当前值，即尝试买入改天的股票
            }
        }
        // 可能到最后一个是仍然上涨，但是还没有卖出
        if (prices[prices.length - 1] > min) {
            profit += (prices[prices.length - 1] - min);
        }
        return profit;
    }

    @Test
    public void test() {
        int a = 1;
        a += 3 * 3;
        System.out.println(a);

    }


}
