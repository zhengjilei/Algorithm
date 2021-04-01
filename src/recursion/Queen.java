package recursion;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/2/24
 */
public class Queen {
    int count = 0;

    public void queen(int n, int rowIndex, int[] path) {
        if (rowIndex == n) {
            count++;
            System.out.println(Arrays.toString(path));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(rowIndex, i, path)) {
                path[rowIndex] = i;
                queen(n, rowIndex + 1, path);
            }
        }

    }

    // 在行 rowIndex 上尝试放置在列 j 上，判断是否合理
    public boolean isValid(int rowIndex, int j, int[] path) {
        for (int k = 0; k < rowIndex; k++) {
            if (path[k] == j || Math.abs(k - rowIndex) == Math.abs(j - path[k])) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        queen(8, 0, new int[8]);
        System.out.println(count);
    }
}
