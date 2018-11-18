package sort.quicksort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * 取中位数法
 * Created by lenovo on 2018/3/26.
 */
public class QuickSort {

    public static int median3(int[] a, int left, int right) {
        int mid = (left + right) / 2, k = left;
        if (a[mid] < a[k]) k = mid;
        if (a[right] < a[k]) k = right; // k指向三者中最小的元素
        if (k != left) swap(a, k, left); // 将最小元素换到 left
        if (mid != right && a[mid] < a[right]) swap(a, mid, right); // 将中间元素 pivot 换到数组末尾

        return a[right];
    }

    public static int partition(int[] a, int left, int right) {
        int pivotValue = median3(a, left, right);
        int i = left, j = right;
        while (i < j) {
            while (i < j && a[i] <= pivotValue) i++;
            while (i < j && a[j] >= pivotValue) j--;
            if (i < j) {
                swap(a, i, j);
            }
        }
        swap(a, i, right); // 恢复pivot 的正确位置，i指向第一个比 pivot 大的数， 故可以交换到 pivot 的右边
        return i;
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(a, left, right);
            quickSort(a, left, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, right);
        }
    }

    public static void swap(int[] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
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
        System.out.println(SortJudge.judge(b));
    }

}
