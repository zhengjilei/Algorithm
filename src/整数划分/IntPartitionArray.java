package 整数划分;

import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/19.
 */
public class IntPartitionArray {

/*
    public static int array(int n) {
        int[][] a = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                a[i][j] = 0;
            }
        }
        for (int i = 1; i <= n; i++) {
            a[i][1] = 1;
            a[1][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1 || i == 1) a[i][j] = 1;
                else if (i == j) {
                    a[i][j] = 1 + a[i][i - 1];
                } else if (i < j) {
                    a[i][j] = a[i][i];
                } else if (i > j) {
                    a[i][j] = a[i - j][j] + a[i][j - 1];
                }
            }
        }
        return a[n][n];
    }
*/


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] a = new int[121][121];

        for (int i = 0; i <= 120; i++) {
            for (int j = 0; j <= 120; j++) {
                a[i][j] = 0;
            }
        }
        for (int i = 1; i <= 120; i++) {
            a[i][1] = 1;
            a[1][i] = 1;
        }
        for (int i = 1; i <= 120; i++) {
            for (int j = 1; j <= 120; j++) {
                if (j == 1 || i == 1) a[i][j] = 1;
                else if (i == j) {
                    a[i][j] = 1 + a[i][i - 1];
                } else if (i < j) {
                    a[i][j] = a[i][i];
                } else if (i > j) {
                    a[i][j] = a[i - j][j] + a[i][j - 1];
                }
            }
        }
        while (scanner.hasNext()) {
            int i = scanner.nextInt();
//            long begin = System.currentTimeMillis();
            int array =a[i][i];
//            long end = System.currentTimeMillis();
            System.out.println(array);
//            System.out.println("数组计算的结果: " + array);
//            System.out.println("花费的时间: " + (end - begin) + " 毫秒");
        }
    }
}
