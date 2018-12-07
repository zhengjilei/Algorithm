package tecent1;

import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/3/25.
 * 回溯法解决
 */
public class Backtrack {
    private static int count = 0;
    private static int[] p = null;
    private static long n;

    public static void init() {
        int depth = (int) (Math.log(n) / Math.log(2));
        p = new int[depth + 1];
    }

    public static void backtrack(int depth) {
        if (depth >= p.length) return;
        for (int i = 0; i <= 2; i++) {
            // 每种面值的硬币 都有 0/1/2 三种选择
            p[depth] = i;
            int judge = judge();
            if (judge == 1) count++;
            else if (judge == 2) backtrack(depth + 1);
        }
        p[depth] = 0;
    }

    /**
     * 判断当前 选择是否刚好满足
     * @return
     */
    private static int judge() {
        long sum = 0;
        for (int i = 0; i < p.length; i++) {
            sum = sum + (long) (p[i] * Math.pow(2, i));
        }
        if (sum == n) return 1;     // 恰好
        else if (sum < n) return 2;  // 可选
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        init();
        long start = System.currentTimeMillis();
        backtrack(0);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间: " + (end - start));
        System.out.println(count);

        //  组合目标:  10000 100000 1000000     10000000
        //  结果:       205  713    1287        9469
        //  时间：      13477
    }


}
