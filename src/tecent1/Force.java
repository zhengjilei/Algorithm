package tecent1;

import java.util.Scanner;

/**
 * Created by lenovo on 2018/3/24.
 * 腾讯有一道机试题：
 * 大概意思是：
 * 小Q非常富有，拥有非常多的硬币，小Q的拥有的硬币是有规律的，对于所有的非负整数K,小Q恰好> 各有两个数值为2^k，的硬币，所以小Q拥有的硬币是1，1，2，2，4，4……，小Q卖东西需要支付元钱，请问小Q想知道有多少种组合方案。
 * 输入：一个n (1<=n<=10^18),代表要付的钱
 * 输出：表示小Q可以拼凑的方案数目
 */
public class Force {
    private static int countViolence = 0;
    private static int countBacktrack = 0;
    private static int countDp = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int k = (int) Math.floor(Math.log(n) / Math.log(2));

        long start1 = System.currentTimeMillis();
        int count1 = violence(n, k);
        System.out.println(count1);
        long end1 = System.currentTimeMillis();
        System.out.println("暴力法消耗: " + (end1 - start1) + "毫秒");

        //  组合目标:  10000 100000 1000000     10000000
        //  结果:       205  713    1287        9469
        //  时间：      387  12484
    }

    /**
     * 暴力 (递归)
     */
    public static int violence(long n, int k) {
        recursive(n, 0, 0, k);
        return countViolence;
    }

    public static void recursive(long total, long now, int index, int k) {
        if (total == now) {
            countViolence++;
            return;
        }
        if (now > total || index > k) return;

        recursive(total, now, index + 1, k);
        recursive(total, (long) (now + Math.pow(2, index)), index + 1, k);
        recursive(total, (long) (now + 2 * Math.pow(2, index)), index + 1, k);
    }

}
