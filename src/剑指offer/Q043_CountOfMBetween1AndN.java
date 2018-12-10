package 剑指offer;

/**
 * 计算1~n 所有的数字中 m 出现的次数，其中 m的范围是 [1,9]
 * created by Ethan-Walker on 2018/12/11
 */
public class Q043_CountOfMBetween1AndN {



    public int countOf1Between1AndN2(int n, int m) {
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
}
