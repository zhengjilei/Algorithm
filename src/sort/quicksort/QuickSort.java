package sort.quicksort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * 取中位数法
 * Created by Ethan-Walker on 2018/3/26.
 */
public class QuickSort {

    // 返回pivotIndex
    public static int median3(int[] a, int left, int right) {
        int mid = (left + right) / 2, minIndex = left;
        if (a[mid] < a[minIndex]) minIndex = mid;
        if (a[right] < a[minIndex]) minIndex = right; // minIndex 指向三者中最小的元素
        if (minIndex != left) swap(a, minIndex, left); // 将最小元素换到 left
        if (mid != right && a[right] < a[mid])
            swap(a, mid, right);
        return mid;
    }

    public static int partition(int[] a, int left, int right) {
        int pivotIndex = median3(a, left, right);
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, right);
        int i = left, j = right; // 必须将 j 初始化为 right， 而不是 right-1
        while (i < j) {
            while (i < j && a[i] <= pivot) i++;
            while (i < j && a[j] >= pivot) j--;
            if (i < j) {
                swap(a, i, j);
                // 这里不能有 i++;j--;
                // 例如 取中位数后得到 21 13 9 15   （15是pivot）
                // 21 和 9 需要交换位置, i++, j-- 之后得到i=j=1 跳出循环
                // i 指向的位置数值不一定大于 pivot=15，交换 13 15 得到错误划分
            }
        }
        // 恢复pivot 的正确位置，i指向第一个比 pivot 大的数， 故可以交换到 pivot 的右边
        swap(a, i, right);
        return i;
    }

    public static void quickSort(int[] a) {
        if (a == null || a.length <= 1) return;
        quickSort(a, 0, a.length - 1);
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


        for (int k = 0; k < 10000; k++) {
            Random random = new Random();
            int length = random.nextInt(2000);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(2000);
            }
            quickSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }

}
