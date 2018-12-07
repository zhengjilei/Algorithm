package tecent1;

import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/3/25.
 */
public class Dp {
    private static int n;  // 组合目标
    private static int[][] r = null;  // r[i][j] 表示组合目标为 i, 用前 j 种硬币去组合 (前 0,1,2 种硬币值对应于 1,2 4)
    private static int depth;  // 需要前 depth 类硬币

    private static void init() {
        depth = (int) (Math.log(n) / Math.log(2)); // 需要的 硬币种类为 前 depth 种
        r = new int[n + 1][depth + 1];
        for (int j = 0; j < depth + 1; j++) {
            r[0][j] = 1;
            r[1][j] = 1;
        }
        for (int i = 3; i < n; i++) {
            r[i][0] = 0;
        }
        r[2][0] = 1;
        r[1][0] = 1;
    }

    private static void dp() {
        init();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < depth + 1; j++) {
                int sum = 0;
                // r[i][j] = r[i][j-1]+r[i-2^j *1][j-1] + r[i-2^j*2][j-1]
                for (int num = 0; num <= 2; num++) {
                    int rest = i - (int) (num * Math.pow(2, j));
                    if (rest >= 0) {
                        sum += r[rest][j - 1];
                    }
                }
                r[i][j] = sum;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        long start = System.currentTimeMillis();
        dp();
        System.out.println(r[n][depth]);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间: " + (end - start));
        //  组合目标:  10000 100000 1000000     10000000
        //  结果:       205  713    1287        9469
        //  时间：      45    562   7063
    }
}
