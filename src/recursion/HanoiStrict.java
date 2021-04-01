package recursion;

import org.junit.Test;

/**
 * Created by Ethan-Walker on 2018/7/22.
 * 添加限制条件，盘只能在相邻柱子之间移动, A 上的盘子不能直接移动到 C ，必须经过B -> C
 */
public class HanoiStrict {

    public static long hanoiStrict(int n) {
        long[] a = new long[n + 1];
        a[0] = 0;
        a[1] = 2;
        for (int i = 2; i <= n; i++) {
            a[i] = 3 * a[i - 1] + 2;
        }
        return a[n];
    }

    public static void showStep(int n, String A, String B, String C) {
        if (n == 1) {
            System.out.println("move top disk from " + A + " to " + B);
            System.out.println("move top disk from " + B + " to " + C);
        } else {
            // 将 A 上的 n-1 个盘子通过 B 放入 C
            showStep(n - 1, A, B, C);

            System.out.println("move top disk from " + A + " to " + B);

            // 将 C上的 n -1 个盘子通过 B 放入 A
            showStep(n - 1, C, B, A);
            // 将B 上的 1 个盘子移入 C
            System.out.println("move from top disk from " + B + " to " + C);

            // 将 A 上的 n-1 个盘子通过 B 放入 C
            showStep(n - 1, A, B, C);
        }
    }

    @Test
    public void test(){
        System.out.println(hanoiStrict(3));
    }
}
