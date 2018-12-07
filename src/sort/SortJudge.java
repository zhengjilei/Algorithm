package sort;

import java.util.Arrays;

/**
 * Created by Ethan-Walker on 2018/5/14.
 */
public class SortJudge {
    public static boolean judge(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);

        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;

    }
}
