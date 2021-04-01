package sort.insert;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/3/26.
 */

public class InsertSort {

    public static void main(String[] args) {
        Random random = new Random();
        int length = random.nextInt(100);
        System.out.println(length);
        int[] a = new int[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(200);
        }
        System.out.println(Arrays.toString(a));
        System.out.println();
        binary_insert_sort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 直接插入排序
     * 稳定
     * O(n^2)
     */
    public static void insertSort(int[] a) {
        int i, j, insert;
        for (i = 1; i < a.length; i++) {
            insert = a[i];
            for (j = i - 1; j >= 0 && insert < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = insert;
        }
    }

    /**
     * 希尔排序
     * 不稳定
     * O(n^1.3~n^2)
     */
    public static void shellSort(int[] a) {
        int gap = a.length, x, i, j, insert;
        while (gap != 0) {
            gap = gap / 2;        // 间隔从大到小
            for (x = 0; x < gap; x++) {
                // 分成 gap 组，每组进行直接插入排序， x 为每组的首位
                for (i = x + gap; i < a.length; i++) {
                    insert = a[i];
                    for (j = i - gap; j >= 0 && insert < a[j]; j -= gap) {
                        a[j + gap] = a[j];
                    }
                    a[j + gap] = insert;
                }
            }
        }
    }

    /**
     * 折半插入排序
     * 稳定
     * O(nlogn)
     *
     * @param a
     * @param length
     */
    public static void binary_insert_sort(int a[], int length) {
        for (int i = 1; i < length; i++) {
            int insert = a[i];
            int index = binary_search_insert_index(a, 0, i - 1, insert);
            for (int j = i - 1; j >= index; j--) {
                a[j + 1] = a[j];
            }
            a[index] = insert;
        }
    }

    //找到比 key 小且索引值最大的元素，设所在位置 index，插入位置 index+1  找不到返回0
    public static int binary_search_insert_index(int a[], int l, int r, int key) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (key < a[mid]) {
                r = mid - 1;
            } else {
                // key=a[mid] 归为这一类，保证稳定排序（相等元素，保证位置不变化）
                l = mid + 1;
            }
        }
        return l;
    }
}
