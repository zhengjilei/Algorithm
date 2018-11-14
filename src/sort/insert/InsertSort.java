package sort.insert;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lenovo on 2018/3/26.
 * 直接插入排序
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
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int insert = a[i];
            while (j >= 0 && insert < a[j]) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = insert;
        }
    }

    /**
     * 希尔排序
         */
    public static void shellSort(int[] a) {
        int gap = a.length;
        while (gap != 0) {
            gap = gap / 2;        // 间隔从大到小
            for (int x = 0; x < a.length; x += gap) {  // 分成 若干组，每组进行直接插入排序， x为每组的首位
                for (int i = x + gap; i < a.length; i++) {
                    int j = i - gap;
                    while (j >= 0 && a[i] < a[j]) {
                        a[j + gap] = a[j];
                        j -= gap;
                    }
                    a[j + gap] = a[i];
                }
            }
        }
    }

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
            }
            else {
                l = mid + 1;
            }
        }
        return l;
    }
}
