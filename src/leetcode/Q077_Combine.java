package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q077_Combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int[] seq = new int[k];
        find(res, 1, n, seq, 0);
        return res;
    }

    public void find(List<List<Integer>> res, int num, int max, int[] seq, int index) {
        if (index == seq.length) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < index; i++) {
                list.add(seq[i]);
            }
            res.add(list);
            return;
        }
        for (int i = num; i <= max; i++) {
            seq[index] = i;
            find(res, i + 1, max, seq, index + 1);
        }
    }
}
