package 组合;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 从 n 个字符中选出 m 个字符，输出所有的集合
 */
public class Cnm {

    /**
     * recur(m,n,chs, selected,index) 从 chs 的前 n 个(0~n-1)中选 m 个
     * 已选的存在了 selected 中, 当前要选第 index 个元素
     *
     * @param m
     * @param n
     * @param chs
     */
    public static void recur(int m, int n, char[] chs, char[] selected, int index) {
        if (m < 0 || n < 0 || m > n || index < 0) return;
        if (m == 0) {
            System.out.println(Arrays.toString(selected));
            return;
        }
        if (m == n) {
            for (int i = 0; i < n; i++) {
                selected[index++] = chs[i];
            }
            System.out.println(Arrays.toString(selected));
            return;
        } else {
            // m<n

            // chs[n-1]不选
            recur(m, n - 1, chs, selected, index);


            // chs[n-1]选
            selected[index] = chs[n - 1];
            recur(m - 1, n - 1, chs, selected, index + 1);
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
            return cnm(m, n - 1) + cnm(m - 1, n - 1); // 第 n 个不取  或者 第 n 个取
        }
    }


    public static long cnm3(int m, int n) {
        long[][] cnm = new long[n + 1][n + 1];
        // 初始化 c[k][1] c[k][k]

        for (int i = 1; i <= n; i++) {
            cnm[i][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            cnm[i][1] = i;
        }

        //c[i][j] j<i
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                cnm[i][j] = cnm[i - 1][j] + cnm[i - 1][j - 1];
            }
        }
        return cnm[n][m];
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
        char[] chs = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        int m = 4;
        char[] selected = new char[m];
//        recur(m, chs.length, chs, selected, 0);

//        System.out.println(cnm(2, 4));
        System.out.println(cnm(10, 25));
        m = 20;
        int n = 90;
        System.out.println(cnm2(m, n));
        System.out.println(cnm3(m, n));
        System.out.println(Long.MAX_VALUE);
    }

}
