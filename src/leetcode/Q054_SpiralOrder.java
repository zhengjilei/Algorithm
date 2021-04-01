package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class Q054_SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        int tRow = 0, tCol = 0, dRow = matrix.length - 1, dCol = matrix[0].length - 1;

        while (tRow <= dRow && tCol <= dCol) {
            printEdge(res, matrix, tRow++, tCol++, dRow--, dCol--);
        }
        return res;
    }

    public void printEdge(List<Integer> res, int[][] matrix, int tRow, int tCol, int dRow, int dCol) {
        if (tRow == dRow) {
            // 只有一行
            for (; tCol <= dCol; tCol++) {
                res.add(matrix[tRow][tCol]);
            }
        } else if (tCol == dCol) {
            // 只有一列
            for (; tRow <= dRow; tRow++) {
                res.add(matrix[tRow][tCol]);
            }
        } else {
            int curRow = tRow;
            int curCol = tCol;

            // 先从左往右遍历行（最后一个下一次循环遍历）
            while (curCol != dCol) {
                res.add(matrix[curRow][curCol++]);
            }

            while (curRow != dRow) {
                res.add(matrix[curRow++][curCol]);
            }

            while (curCol != tCol) {
                res.add(matrix[curRow][curCol--]);
            }
            while (curRow != tRow) {
                res.add(matrix[curRow--][curCol]);
            }
        }
    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }

}
