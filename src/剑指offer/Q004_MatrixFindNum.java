package 剑指offer;


import org.junit.Test;

/**
 * 二维数组中查找一个数
 * 数组中行递增、列递增
 * created by Ethan-Walker on 2018/12/1
 */
public class Q004_MatrixFindNum {


    /**
     * 依次对每一行进行二分查找
     * 缺点：只用到行递增的条件，没有充分利用条件
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(1)
     *
     * @param a
     * @param key
     * @return
     */
    public boolean binarySearch(int[][] a, int key) {

        int row = a.length;
        if (row == 0) return false; // 空数组

        int col = a[0].length;
        int start, mid, end;
        for (int i = 0; i < row; i++) {
            start = 0;
            end = col - 1;
            while (start <= end) {
                mid = (end - start) / 2 + start;
                if (key > a[i][mid]) {
                    start = mid + 1;
                } else if (key < a[i][mid]) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 充分利用行递增，列递增的特性
     * 每次取左下角/右上角的数作为比较对象，比较结束之后可以相应的排除掉整行/列
     * <p>
     * 时间复杂度: O(row+col)
     * 空间复杂度: O(1)
     *
     * @param a
     * @param key
     * @return
     */
    public boolean find(int[][] a, int key) {
        int row = a.length;
        if (row == 0) return false; // 空数组

        int col = a[0].length;
        int i = 0, j = col - 1; // 选右上角为比较对象
        while (i < row && j >= 0) {
            if (key == a[i][j]) {
                return true;
            } else if (key > a[i][j]) {
                // 排除掉行 i
                i++;
            } else {
                // 排除掉列 j
                j--;
            }
        }
        return false;
    }




    /**
     * 选择左下角的数作为比较对象
     *
     * @param a
     * @param key
     * @return
     */
    public boolean find2(int[][] a, int key) {
        int row = a.length;
        if (row == 0) return false; // 空数组

        int col = a[0].length;
        int i = row - 1, j = 0;
        while (i >= 0 && j < col) {
            if (key == a[i][j])
                return true;
            else if (key > a[i][j]) {
                //排除掉列
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
    /**
     * 1. 数组为空
     * 2. 查找元素不在数组中（大于最大值，小于最小值，介于中间）
     * 3. 查找元素在数组中（等于最大值/最小值/中间值）
     */
    @Test
    public void testBinarySearch() {
        int[][] a = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };

        int[][] b = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 9, 13}
        };

        int[][] c = new int[][]{};
        System.out.println(binarySearch(a, 10));
        System.out.println(binarySearch(a, 12));
        System.out.println(binarySearch(a, 16));
        System.out.println(binarySearch(a, 15));
        System.out.println(binarySearch(a, 0));
        System.out.println(binarySearch(a, 1));
        System.out.println("------------------");

        System.out.println(binarySearch(b, 11));
        System.out.println(binarySearch(b, 12));
        System.out.println(binarySearch(b, 14));
        System.out.println(binarySearch(b, 3));
        System.out.println("------------------");
        System.out.println(binarySearch(c, 1));

/*     true
        true
        false
        true
        false
        true
                ------------------
        false
        true
        false
        false
                ------------------
        false
 */
    }
    @Test
    public void testFind() {
        int[][] a = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };

        int[][] b = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 9, 13}
        };

        int[][] c = new int[][]{};
        System.out.println(find(a, 10));
        System.out.println(find(a, 12));
        System.out.println(find(a, 16));
        System.out.println(find(a, 15));
        System.out.println(find(a, 0));
        System.out.println(find(a, 1));
        System.out.println("------------------");

        System.out.println(find(b, 11));
        System.out.println(find(b, 12));
        System.out.println(find(b, 14));
        System.out.println(find(b, 3));
        System.out.println("------------------");
        System.out.println(find(c, 1));

    }
    @Test
    public void testFind2() {
        int[][] a = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };

        int[][] b = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 9, 13}
        };

        int[][] c = new int[][]{};
        System.out.println(find2(a, 10));
        System.out.println(find2(a, 12));
        System.out.println(find2(a, 16));
        System.out.println(find2(a, 15));
        System.out.println(find2(a, 0));
        System.out.println(find2(a, 1));
        System.out.println("------------------");

        System.out.println(find2(b, 11));
        System.out.println(find2(b, 12));
        System.out.println(find2(b, 14));
        System.out.println(find2(b, 3));
        System.out.println("------------------");
        System.out.println(find2(c, 1));

    }

}
