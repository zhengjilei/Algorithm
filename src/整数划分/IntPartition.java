package 整数划分;

import org.junit.Test;

import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/19.
 */
public class IntPartition {

    /**
     * n = m1+m2+...+mi
     * max(m1,m2,...,mi)<=m
     *
     * @param n
     * @param m
     * @return
     */
    public static int digui(int n, int m) {
        if (n == 0 || m == 0) return 0;
        if (n == 1 || m == 1) return 1;
        if (n == m) return 1 + digui(n, n - 1);
        if (n < m) return digui(n, n);
        // n>m 时
        return digui(n - m, m) + digui(n, m - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int i = scanner.nextInt();
            if (i == -1) {
                break;
            }
            long begin = System.currentTimeMillis();
            int digui = digui(i, i);   //递归超时
            long end = System.currentTimeMillis();

            System.out.println("递归的结果： " + digui);
            System.out.println("花费的时间: " + (end - begin) + " 毫秒");
        }
    }


}
