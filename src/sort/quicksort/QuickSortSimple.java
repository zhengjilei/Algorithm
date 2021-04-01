package sort.quicksort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/5/14.
 * pivot 选择第一个元素
 * 排序结果错误
 */
public class QuickSortSimple {

    public static void main(String[] args) {
        for (int k = 0; k < 1000; k++) {
            Random random = new Random();
            int length = random.nextInt(10000);
//            System.out.println(length);
            int[] a = new int[length];
            int[] b = new int[length];
            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(10000);
            }
//          System.out.println(Arrays.toString(a));
//          System.out.println();
            quickSort3(a, 0, length - 1);
//          System.out.println(Arrays.toString(a));

            System.out.println(SortJudge.judge(a));
        }

    }


    /**
     * 以第一个元素作为基准
     * 不稳定
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivotPos = partition(arr, startIndex, endIndex);
            quickSort(arr, startIndex, pivotPos - 1);
            quickSort(arr, pivotPos + 1, endIndex);
        }
    }

    public static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int i = startIndex, j = endIndex;
        // 如果 i = startIndex+1 有个坑, 当startIndex+1 = endIndex时，此时 i=j，跳过 while
        // 直接交换 startIndex   j=endIndex，导致结果错误，解决设置 i=startIndex

        while (i < j) {
            while (i < endIndex && arr[i] <= pivot) i++;
            while (j > startIndex && arr[j] >= pivot) j--;

            // arr[i]<=pivot 一定要加 = ，防止 a[i] =pivot a[j] = pivot,i<j 陷入死循环

            // 三种情况
            // arr[i] > pivot  arr[j] < pivot
            // i = endIndex ,j = endIndex   pivot 最大
            // i= startIndex+1 , j = startIndex  pivot 最小

            if (i < j) {
                swap(arr, i, j);
            }

        }
        // 交换 a[startIndex] a[j]
        swap(arr, startIndex, j);

        // j 为 pivot 所在位置
        return j;
    }

    /**
     * 以最右元素作为基准
     *
     * @param a
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int partition2(int[] a, int startIndex, int endIndex) {
        int pivot = a[endIndex];
        int i = startIndex, j = endIndex;
        while (i < j) {
            while (i < j  && a[i] <= pivot) i++;
            while (i < j  && a[j] >= pivot) j--;
            if (i < j) {
                swap(a, i, j);
            }
        }
        // pivot 位置在索引 i 处
        swap(a, endIndex, i);
        return i;
    }

    /**
     * @param a
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort2(int[] a, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivot = partition2(a, startIndex, endIndex);
            quickSort2(a, startIndex, pivot - 1);
            quickSort2(a, pivot + 1, endIndex);
        }
    }

    public static int partition3(int[] a, int startIndex, int endIndex) {
        Random random = new Random();
        int pivotIndex = random.nextInt(endIndex - startIndex + 1) + startIndex;
        int pivot = a[pivotIndex];
        int i = startIndex, j = endIndex;
        swap(a, startIndex, pivotIndex);

        while (i < j) {
            while (i < j && a[i] <= pivot) i++;
            while (i < j && a[j] >= pivot) j--;
            if (i < j) {
                swap(a, i, j);
            }
        }
        swap(a, startIndex, j);
        return j;
    }

    /**
     * 以随机元素作为基准
     * 不稳定
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort3(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivotPos = partition3(arr, startIndex, endIndex);
            quickSort(arr, startIndex, pivotPos - 1);
            quickSort(arr, pivotPos + 1, endIndex);
        }

    }

    static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    @Test
    public void testA() {
        int[] a = new int[]{1, 0, 4, 6, 2, 4, 6, 2, 2};
        quickSort(a, 0, 8);
        System.out.println(Arrays.toString(a));

        Random r = new Random();
        int n = r.nextInt(20) + 20;
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = r.nextInt(100);
        }
        System.out.println(Arrays.toString(b));
        quickSort(b, 0, b.length - 1);
        System.out.println(Arrays.toString(b));
    }



/*
[1, 0, 4, 6, 2, 4, 6, 2, 2]
[0, 1, 2, 2, 4, 2, 4, 6, 6]
*/
}
