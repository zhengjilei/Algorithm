package 矩阵;

import java.util.Arrays;

public class Matrix {
    /**
     * 对称矩阵(方阵)压缩
     * a[i][j] = a[j][i]
     * 只存储下三角
     *
     * @param a
     * @return
     */
    public static int[] symmetry(int[][] a) {
        int n = a.length;
        int[] b = new int[n * (n + 1) / 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int index = (1 + i) * i / 2 + j; // 第i行前 有 1,2,...,i 个元素 ，第 j列前有j 个元素
                b[index] = a[i][j];
            }
        }
        return b;
    }

    /**
     * 方阵 三对角线压缩
     * a00 a01 **  **  **  **
     * a10 a11 a12
     * **  a21 a22 a23
     * **  **  a32 a33 a34
     * **  **  **  a43 a44 a45
     * **  **  **  **  a54 a55
     *
     * @param a
     * @return
     */
    public static int[] tripleSymmetry(int[][] a) {
        int n = a.length;
        int[] b = new int[3 * (n - 2) + 4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.abs(j - i) == 1 || Math.abs(j - i) == 0) {
                    // 在三对角线上
                    int index = i * 3 - 1 + j - i + 1;// 第 i 行前有 3*i-1 个元素,第j列前有 j-i+1 个元素
                    b[index] = a[i][j];
                }

            }
        }
        return b;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3, 4},
                {2, 7, 5, 6},
                {3, 5, 6, 7},
                {4, 6, 7, 9}
        };
        int[][] b = new int[][]{
                {1, 2, 0, 0, 0},
                {3, 4, 5, 0, 0},
                {0, 6, 7, 8, 0},
                {0, 0, 9, 10, 11},
                {0, 0, 0, 12, 13}
        };
        System.out.println(Arrays.toString(symmetry(a)));
        System.out.println(Arrays.toString(tripleSymmetry(b)));

    }


}
