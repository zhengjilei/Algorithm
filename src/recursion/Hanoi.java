package recursion;

import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by Ethan-Walker on 2018/7/22.
 */
public class Hanoi {

    /**
     * 计算 n 个盘子从 A -> C 需要多少步
     * 时间复杂度: O(2^n)
     * 空间复杂度: O(n)
     *
     * @param n
     * @return
     */
    public static long hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    /**
     * h[n] = 2*h[n-1]+1
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     *
     * @param n
     * @return
     */
    public static BigInteger hanoiIteration(int n) {
        BigInteger[] a = new BigInteger[n + 1];
        a[0] = BigInteger.valueOf(0);
        a[1] = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            a[i] = a[i - 1].multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(1));
        }
        return a[n];
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param n
     * @return
     */
    public static long hanoiStepCountIteration(int n) {
        int a = 0, b = 0;
        // 迭代 n 次
        for (int i = 1; i <= n; i++) {
            b = 2 * a + 1;
            a = b;
        }
        return b;
    }

    /**
     * h[n] = 2 * h[n-1]+1
     * h[n]+1 = 2*(h[n-1]+1)
     * 等比公式 h[n]+1 = 1(首项)* 2^n
     * h[n] = 2^n -1
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     *
     * @param n
     * @return
     */
    public static long hanoiStepCount(int n) {
        return (1 << n) - 1;
    }

    @Test
    public void test() {
        System.out.println(1 << 0); // 1(2^0==1)
    }

    /**
     * 递归显示步数
     *
     * @param n
     * @param A
     * @param B
     * @param C
     */
    public static void showStep(int n, String A, String B, String C) {
        if (n == 1) {
            System.out.println("move top disk from " + A + " to " + C);
        } else {
            // 将 A 顶端的 n-1 个盘子移到 B
            showStep(n - 1, A, C, B);
            // 将 A 底部的盘子移到 C
            System.out.println("move top disk from " + A + " to " + C);
            // 将 B 上的 n-1 个盘子移到 C
            showStep(n - 1, B, A, C);
        }
    }

    public static long showStep2(int n, String A, String B, String C) {
        if (n == 1) {
            System.out.println("move top disk from " + A + " to " + C);
            return 1;
        } else {
            long count = 0;
            // 将 A 顶端的 n-1 个盘子移到 B
            count += showStep2(n - 1, A, C, B);


            // 将 A 底部的盘子移到 C
            System.out.println("move top disk from " + A + " to " + C);
            count += 1;

            // 将 B 上的 n-1 个盘子移到 C
            count += showStep2(n - 1, B, A, C);

            return count;
        }
    }


    public static void main(String[] args) {
//      long count = hanoi(40);
        BigInteger count1 = hanoiIteration(5);

        BigInteger count2 = hanoiIteration(64);
//      System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);

        System.out.println(showStep2(5, "A", "B", "C"));
    }
}
