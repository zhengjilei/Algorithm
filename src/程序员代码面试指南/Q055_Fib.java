package 程序员代码面试指南;

import org.junit.Test;

/**
 * Fib 数列：1 1 2 3 5
 * created by Ethan-Walker on 2019/1/1
 */
public class Q055_Fib {

    /**
     * 求第 n 个fib 数
     * 时间复杂度: O(2^n)
     * 空间复杂度: O(n)
     *
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        else return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n == 1 || n == 2) return 1;
        int a = 1, b = 1, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 矩阵乘法 O(logn)
     * (f[n],f[n-1]) = (f[2],f[1]) * ((1,1),(1,0))^(n-2)
     * <p>
     * f[n] = A^(n-2) 第一列之和
     * <p>
     * n>0
     *
     * @param n
     * @return
     */
    public int fib3(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        int[][] source = {{1, 1}, {1, 0}};
        source = pow(source, n - 2);

        // 结果等于第 1 列之和
        return source[0][0] + source[1][0];
    }


    /**
     * 求 a 的 b 次方  (b>=0)
     * 要求时间复杂度小于 O(b)
     */
    public int pow(int a, int b) {
        if (b == 0 && a != 0) return 1;
        if (b == 0 && a == 0) throw new RuntimeException(" can't figure out 0^0 ");
        int tmp = a;
        for (; b != 0; b >>= 1) {
            if ((b & 1) != 0) {
                a *= tmp;
            }
            tmp *= tmp;  // a^1  a^2 a^4 a^8 ...

        }
        return a;
    }


    /**
     * 求方阵的 n 次方
     * 类比上面
     *
     * @param matrix
     * @param n
     * @return
     */
    public int[][] pow(int[][] matrix, int n) {
        if (n == 1) return matrix;

        int[][] temp = matrix;
        int[][] result = new int[matrix.length][matrix[0].length];
        // 置为单位矩阵，相当于整数中的 1
        for (int i = 0; i < matrix.length; i++)
            result[i][i] = 1;

        if (n == 0) return result; // 返回单位矩阵

        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                result = multiMatrix(result, temp);
            }
            temp = multiMatrix(temp, temp);
        }
        return result;
    }

    /**
     * 矩阵相乘
     *
     * @param a
     * @param b
     * @return
     */
    public int[][] multiMatrix(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {          // a 的第 i 行乘 b 的各列
            for (int j = 0; j < b[0].length; j++) {   // a 的第 i 行乘上 b 的 第 j 列
                for (int k = 0; k < b.length; k++) {  // k 表示 b的行号，对应于 a 的列号
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;

    }

    @Test
    public void test() {
//        System.out.println(fib1(50));
        System.out.println(fib2(50));
        System.out.println(fib3(50));

    }


}
