package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/9
 */
public class Q071_Queen {


    public int queen(int n) {
        int[] pos = new int[n];
        return search(pos, 0);
    }

    public int search(int[] pos, int row) {
        if (row == pos.length) return 1;
        int res = 0;
        for (int col = 0; col < pos.length; col++) {
            if (isValid(pos, row, col)) {
                pos[row] = col;
                res += search(pos, row + 1);
            }
        }
        return res;
    }

    /**
     * 在行 row 上尝试放在列 col 上，判断是否合理
     *
     * @param pos
     * @param row
     * @param col
     * @return
     */
    public boolean isValid(int[] pos, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (pos[i] == col || Math.abs(i - row) == Math.abs(pos[i] - col)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(queen(10));
    }
}
