package 剑指offer;

import java.util.Arrays;

/**
 * n 皇后问题
 * created by Ethan-Walker on 2018/12/8
 */
public class Q038B_Queen {

    public int count = 0;

    /**
     * 当前探索到 第 k 行了
     * path[i] = j 表示第 i 行皇后放置在第 j 列
     *
     * @param n
     */
    public void queen(int n, int k, int[] path) {
        if (k == n) {
            System.out.println(Arrays.toString(path));
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            // 探测每一列
            if (judge(k, i, path)) {
                path[k] = i;
                queen(n, k + 1, path);
            }
        }
    }

    /**
     * 尝试将第 row 行的皇后放在第 col 列上
     *
     * @return
     */
    public boolean judge(int row, int col, int[] path) {
        for (int i = 0; i < row; i++) {
            if (path[i] == col || row - i == path[i] - col || row - i == col - path[i]) {
                // 在同列或者对角线上
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q038B_Queen queen = new Q038B_Queen();
        int n = 8;
        int[] path = new int[n];
        queen.queen(8, 0, path);
        System.out.println(queen.count);
    }

}
