package 组合;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 从 n 个字符中选出 m 个字符，输出所有的集合
 */
public class Cnm {

    /**
     * recur(m,n,chs,sb) 从 chs 的前 n 个中选 m 个，已选的存在了 sb 中
     *
     * @param m
     * @param n
     * @param chs
     * @param sb
     */
    public static void recur(int m, int n, char[] chs, StringBuilder sb) {
        if (m < 0 || n < 0 || m > n) return;
        if (m == 0 && sb.length() > 0) {
            System.out.println(sb.toString());
            return;
        }
        if (m == n) {
            for (int i = 0; i < n; i++) {
                sb.append(chs[i]);
            }
            System.out.println(sb.toString());
            return;
        } else {
            // m<n

            // chs[n-1]不选
            recur(m, n - 1, chs, new StringBuilder(sb));

            // chs[n-1]选
            recur(m - 1, n - 1, chs, new StringBuilder(sb).append(chs[n - 1]));
        }
    }

    /**
     * 求 C(m,n) n个数中取m个，所有的情况数
     * C(m,n)  = C(m,n-1)+C
     *
     * @param m
     * @param n
     * @return
     */
    public static long cnm(int m, int n) {
        if (m > n) return -1;
        if (m == n || m == 0) {
            return 1;
        } else {
            return cnm(m, n - 1) + cnm(m - 1, n - 1);
        }
    }

    public static BigInteger cnm2(int m, int n) {
        // n*..*(n-m+1)/ m!
        if (m > (n / 2)) m = n - m;
        BigInteger top = BigInteger.valueOf(1), bottom = BigInteger.valueOf(1);
        for (int i = n; i >= n - m + 1; i--) {
            top = top.multiply(BigInteger.valueOf(i));
        }
        for (int i = m; i >= 1; i--) {
            bottom = bottom.multiply(BigInteger.valueOf(i));
        }
        return top.divide(bottom);
    }

    public static void main(String[] args) {
//        recur(3, 5, new char[]{'a', 'b', 'c', 'd', 'e'}, new StringBuilder());
//        System.out.println(cnm(2, 4));
//        System.out.println(cnm(20, 35));
        System.out.println(cnm2(50, 1000));
    }

}
