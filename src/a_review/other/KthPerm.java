package a_review.other;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class KthPerm {
    public String getPermutation(int n, int k) {
        int avg = 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            list.add(i + 1);
            avg *= (i + 1);
        }
        list.add(n);

        StringBuilder sb = new StringBuilder();
        recur(list, sb, 0, avg, k - 1, n);
        return sb.toString();
    }

    public void recur(List<Integer> list, StringBuilder sb, int index, int avg, int k, int total) {
        int t = k / avg;
        sb.append(list.get(t));

        if (index == total - 1) {
            return;
        }
        list.remove(t);

        k %= avg;
        avg /= list.size();

        recur(list, sb, index + 1, avg, k, total);
    }

}
