package queen;

import org.junit.Test;

public class Queen2 {


    /**
     * 递归实现（回溯、dfs）
     * n 皇后   n 行 n列
     * <p>
     * 时间复杂度: O(n!)
     * 空间复杂度: O(n)
     * <p>
     * 从第 row 行开始，满足N皇后条件的种数
     *
     * @param row
     * @param n
     * @param a   a[i] = k  表示第 i 行元素放在第 k 列
     */
    public int search(int row, int n, int[] a) {
        if (row == n) {
//            System.out.println(Arrays.toString(a));
            return 1;
        }
        int result = 0;
        for (int col = 0; col < n; col++) {
            // n列
            if (isValid(row, col, a)) {
                a[row] = col;
                result += search(row + 1, n, a);
            }
        }
        return result;
    }

    public boolean isValid(int row, int col, int[] a) {
        for (int i = 0; i < row; i++) {
            // 不同列 且 不同对角线
            if (col == a[i] || (Math.abs(row - i) == Math.abs(col - a[i]))) {
                return false;
            }
        }
        return true;
    }

    public int queen(int n) {
        int[] pos = new int[n]; // pos[i] = k  表示第 i 行元素放在第 k 列
        for (int i = 0; i < n; i++)
            pos[i] = -1;
        return search(0, n, pos);
    }

    @Test
    public void test() {
        int n = 8;
        System.out.println(queen(n));

    }
}
