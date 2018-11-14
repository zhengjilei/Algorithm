package queen;

public class Queen2 {

    static int count = 0;

    /**
     * 递归实现（回溯、dfs）
     * n 皇后   n 行 n列
     *
     * @param row
     * @param n
     * @param a   a[i] = k  表示第 i 行元素放在第 k 列
     */
    public static void recur(int row, int n, int[] a) {
        if (row == n) {
            for (int i = 0; i < n; i++) {
                System.out.printf("%d ", a[i]);
            }
            System.out.println();
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            // n列
            if (isValid(row, col, a)) {
                a[row] = col;
                recur(row + 1, n, a);
            }
        }
    }

    public static boolean isValid(int row, int col, int[] a) {
        for (int i = 0; i < row; i++) {
            // 不同列 且 不同对角线
            if (col == a[i] || (Math.abs(row - i) == Math.abs(col - a[i]))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int n = 8;
        int[] a = new int[n];  // a[i] = k  表示第 i 行元素放在第 k 列
        for (int i = 0; i < n; i++) a[i] = -1;
        recur(0, n, a);
        System.out.println(count);
    }
}
