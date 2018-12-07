package sort;

/**
 * 冒泡排序
 * O(n^2)
 * 稳定
 * Created by Ethan-Walker on 2018/3/26.
 */
public class BubbleSort {
    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {   // i 作为 被排到最后一位的 数的个数
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(j, j + 1, a);
                }
            }
        }
    }

    //改进后的冒泡排序，tag：标记某趟冒泡是否发生了逆序和交换，若没有发生，则说明已经排序好，终止排序
    public static void improveBubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {   // i 作为 被排到最后一位的 数的个数
            int tag = 0;
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(j, j + 1, a);
                    tag = 1;
                }
            }
            if (tag == 0) return;
        }
    }

    public static void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
