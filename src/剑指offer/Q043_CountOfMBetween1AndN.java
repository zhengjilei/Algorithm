package 剑指offer;

/**
 * 计算1~n 所有的数字中 m 出现的次数，其中 m的范围是 [1,9]
 * created by Ethan-Walker on 2018/12/11
 */
public class Q043_CountOfMBetween1AndN {


    public int countOf1Between1AndN(int n, int m) {
        int sum = 0;
        int times = 0;
        for (int k = 10; k <= n * 10; k *= 10) {
            int a = n / k, b = n % k;
            times = k / 10;
            sum += a * times;

            int t = b / times;
            if (t > m) {
                sum += times;
            } else if (t == m) {
                sum += (b % times + 1);
            }
        }
        return sum;
    }

    /**
     * 试图将 k == times ，即将 k 缩小 10倍
     *
     * @param n
     * @param m
     * @return
     */
    public int countOf1Between1AndN2(int n, int m) {
        int sum = 0, a, b;
        for (int k = 1; k <= n; k *= 10) {
            a = n / k; // 比之前的 a 多了一位，结尾多了一个 原先b的最高位
            b = n % k; // 比之前的 b 少了最高位，成了原先 b 的低位
            sum += a / 10 * k; // a/10 *k 等效于之前的 a*times, 这里的 a/10 等效于之前的 a，k等于times
            if (a % 10 > m) { // a%10 即为 原先 b 的最高位
                sum += k;
            } else if (a % 10 == 1) {
                sum += b + 1; // b 为原先b的低位（除最高位以外的其他位）
            }
        }
        return sum;
    }
}
