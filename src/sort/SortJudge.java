package sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

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


    @Test
    public void test() {

        for (int k = 0; k < 10000; k++) {
            Random random = new Random();
            int length = random.nextInt(2000);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(2000);
            }
//            shellSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }
}
