package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lenovo on 2018/3/26.
 */
public class SelectSort {
    /**
     * 选择排序: 遍历选择最小的数 放在第1位、第二位
     * O(n^2)
     * 不稳定
     */
    public static void selectSort(int[] a) {
        int min, minIndex, i, j;
        for (i = 0; i < a.length - 1; i++) {
            min = a[i];     // min 记录最小值
            minIndex = i;  // minIndex 记录最小值的位置
            for (j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(i, minIndex, a);
            }
        }
    }

    public static void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int n = random.nextInt(20) + 20;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        selectSort(a);
        System.out.println(Arrays.toString(a));

    }
}
