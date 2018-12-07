package recursion;

import java.math.BigInteger;

/**
 * Created by Ethan-Walker on 2018/7/22.
 */
public class Hanoi {

    /**
     * 计算 n 个盘子从 A -> C 需要多少步
     *
     * @param n
     * @return
     */
    public static long hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    public static BigInteger hanoiIteration(int n) {
        BigInteger[] a = new BigInteger[n + 1];
        a[0] = BigInteger.valueOf(0);
        a[1] = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            a[i] = a[i - 1].multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(1));
        }
        return a[n];
    }

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


    public static void main(String[] args) {
//        long count = hanoi(40);
        BigInteger count1 = hanoiIteration(5);

        BigInteger count2 = hanoiIteration(64);
//        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);

        showStep(5, "A", "B", "C");
    }
}
