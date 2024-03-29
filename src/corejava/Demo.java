package corejava;

import java.io.Console;
import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        for (int[] row : a) {
            for (int ele : row) {
                System.out.printf("%4d", ele);
            }
            System.out.println();
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.deepToString(a));

        // 二维数组第一行和第二行互换
        int[] t = a[0];
        a[0] = a[1];
        a[1] = t;
        System.out.println(Arrays.deepToString(a));

//        Employee e = new Employee();
    }
}
