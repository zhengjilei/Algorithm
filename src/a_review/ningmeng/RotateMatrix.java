package a_review.ningmeng;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class RotateMatrix {
    /**
     * 将n*n 矩阵顺时针旋转 90度
     * 要求空间复杂度: O(1)
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int tRow = 0, tCol = 0, dRow = n - 1, dCol = n - 1;

        while (tRow < dRow) {// 因为是 n 阶矩阵，故不存在 tRow == dRow 的情况
            rotate(matrix, tRow++, tCol++, dRow--, dCol--);
        }
    }

    public void rotate(int[][] m, int tRow, int tCol, int dRow, int dCol) {
        int times = dCol - tCol;
        int tmp;
        for (int i = 0; i < times; i++) {
            tmp = m[tRow][tCol + i];
            m[tRow][tCol + i] = m[dRow - i][tCol];
            m[dRow - i][tCol] = m[dRow][dCol - i];
            m[dRow][dCol - i] = m[tRow + i][dCol];
            m[tRow + i][dCol] = tmp;
        }
    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));

    }
}
