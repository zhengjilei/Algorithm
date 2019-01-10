package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/1/9
 */
public class Q051_Queen {
    public int queen(int n) {
        int[] pos = new int[n];
        List<List<String>> result = new ArrayList<>();
        return search(pos, 0, result);
    }

    public int search(int[] pos, int row, List<List<String>> list) {
        if (row == pos.length) {
            char[] chs = null;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pos.length; i++) {
                sb.append(".");
            }
            chs = sb.toString().toCharArray();
            List<String> ll = new ArrayList<>();
            for (int i = 0; i < pos.length; i++) {
                char[] chars = Arrays.copyOf(chs, chs.length);
                chars[pos[i]] = 'Q';
                ll.add(new String(chars));
            }
            list.add(ll);
            return 1;
        }
        int res = 0;
        for (int col = 0; col < pos.length; col++) {
            if (isValid(pos, row, col)) {
                pos[row] = col;
                res += search(pos, row + 1, list);
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

}
