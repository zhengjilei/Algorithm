package 背包01;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 01 背包
 * Created by EthanWalker on 2017/12/3.
 */
public class Backpack {


    private static int[] weight = new int[]{2, 2, 6, 5, 4};
    private static int[] value = new int[]{6, 3, 5, 4, 6};

    private static int capacity = 10;  // 背包容量

    private static int[][] m = new int[5][capacity + 1]; // m[i][j] 表示 从前 i+1 个物品中挑选, 填入容量为 j 的背包中, 能得到的最大价值

    public static int maxValue() {
        for (int j = 0; j <= capacity; j++) {
            if (j < weight[0]) {
                m[0][j] = 0;
            } else {
                m[0][j] = value[0];
            }
        }
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j < weight[i]) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(m[i - 1][j], m[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        return m[weight.length - 1][capacity];
    }

    private static int[] track = new int[weight.length];

    public static void traceback(int max) {

        int count = 0;
        int j = capacity;
        for (int i = weight.length - 1; i >= 1; i--) {
            if (m[i][j] != m[i - 1][j]) {
                track[count++] = i + 1;
                j = j-weight[i];
            }
        }
        if(j>weight[0]){
            track[count++] = 1;
        }
        System.out.println("选出的物品次序为: ");
        for(int i=count-1;i>=0;i--){
            System.out.print(track[i]+" , ");
        }

    }

    public static void main(String[] args) {
        int maxValue = maxValue();
        System.out.println("最大价值为: " + maxValue);
        traceback(maxValue);
    }
}
