package leetcode;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class Q059_GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        int num = 1;
        int tRow = 0, tCol = 0, dRow = n - 1, dCol = n - 1;

        while (tRow <= dRow && tCol <= dCol) {

            if (tRow == dRow) {
                // 只有一行了
                for (int i = tCol; i <= dCol; i++) {
                    m[tRow][i] = num++;
                }
            } else if (tCol == dCol) {
                for (int i = tRow; i <= dRow; i++) {
                    m[i][tCol] = num++;
                }
            } else {
                int curRow = tRow;
                int curCol = tCol;

                while (curCol != dCol) {
                    m[curRow][curCol++] = num++;
                }
                while (curRow != dRow) {
                    m[curRow++][curCol] = num++;
                }

                while (curCol != tCol) {
                    m[curRow][curCol--] = num++;
                }
                while (curRow != tRow) {
                    m[curRow--][curCol] = num++;
                }
            }
            tRow++;
            tCol++;
            dRow--;
            dCol--;
        }
        return m;
    }

}
