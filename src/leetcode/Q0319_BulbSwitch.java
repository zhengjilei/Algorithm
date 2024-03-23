package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q0319_BulbSwitch {


    /**
     * 最简单解法：超时
     * 遍历n轮
     * 时间复杂度 ： O(n) < n/2 + n/3 + n/4 ... + 1 < O(n^2)
     *
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        boolean[] bulbs = new boolean[n];
        // false 认为是开的

        // 第2轮
        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j < n; j += i) {
                bulbs[j] = !bulbs[j];
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!bulbs[i]) count++;
        }
        return count;
    }

    /**
     * 第 i 个灯泡被反转的次数等于 i 的因子数目(包括 1和 i)
     * 反转奇数次是亮，偶数次是暗
     * 故判断第 i 个灯泡是亮是暗，就判断i的因子个数是奇数还是偶数
     * @param n
     * @return
     */
    public int bulbSwitch2(int n) {

        int count = 0;
        for (int i = 0; i < n; i++) {
            // 第 i+1 个灯泡 执行 n 轮之后
            int yinziCount = getYinzi(i + 1);
            if ((yinziCount & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    public int getYinzi(int n) {
        if (n == 1 || n == 2) return n;
        int count = 2;
        int i = 0;
        for (i = 2; i * i <= n; i++) {
            if (n % i == 0) count += 2;
            if (i * i == n) {
                count--;
            }
        }

        return count;
    }


    // 最终思路，上面已经将问题转化成求 1~n 中因子个数为奇数的总数？
    // 什么情况下一个数的因子个数为奇数？ 当且仅当该数是 平方数时候（不为平方数，任意一个因子对应着另外一个因子）
    // 故问题转化成 1~n 个数中有多少个平方数？   sqrt(n)

    public int bulbSwitch3(int n) {
        return (int) Math.sqrt(n);
    }

    @Test
    public void test() {
        System.out.println(bulbSwitch2(6));
    }
}
