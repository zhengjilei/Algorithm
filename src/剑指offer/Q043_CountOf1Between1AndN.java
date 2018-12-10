package 剑指offer;

/**
 * 输入一个整数 n(n>=1)
 * 统计1~n 之间所有的整数中出现数字 1 的数量之和
 * created by Ethan-Walker on 2018/12/10
 */
public class Q043_CountOf1Between1AndN {

    /**
     * 最简单的算法：
     * 循环遍历每一个数字，对每个数字求其中 1 的个数
     *
     * @param n
     * @return
     */
    public int countOf1Between1AndN(int n) {
        if (n < 1) return 0;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += count(i);
        }
        return sum;
    }

    /**
     * 计算数字 num 中1 的个数
     *
     * @param num
     * @return
     */
    public int count(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                count++;
            }
            num /= 10;
        }
        return count;
    }

    /**
     * https://blog.csdn.net/huzhigenlaohu/article/details/51779365
     * @param n
     * @return
     */
    public int countOf1Between1AndN2(int n) {
        int t, a, b, count = 0;
        for (int k = 1; k <= n; k *= 10) {
            a = n / k;
            b = n % k;
            count += (a + 8) / 10 * k + ((a % 10 == 1) ? b + 1 : 0);
        }
        return count;
    }

}
