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

    public static int median3(int[] a, int left, int right) {
        int mid = (left + right) / 2, minIndex = left;
        if (a[mid] < a[minIndex]) minIndex = mid;
        if (a[right] < a[minIndex]) minIndex = right; // k指向三者中最小的元素
        if (minIndex != left) swap(a, minIndex, left); // 将最小元素换到 left
        if (mid != right && a[mid] < a[right])
            swap(a, mid, right); // 将中间元素 pivot 换到数组末尾

        return a[right];
    }

    public static int partition(int[] a, int left, int right) {
        int pivotValue = median3(a, left, right);
        int i = left, j = right; // 必须将 j 初始化为 right， 而不是 right-1
        while (i < j) {
            while (i < j && a[i] <= pivotValue) i++;
            while (i < j && a[j] >= pivotValue) j--;
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
        int[] a = new int[]{75, 70, 80, 59, 54, 96, 12, 78, 29, 35, 11, 69, 56, 36, 79, 45, 69, 30, 27, 47, 17, 74};
        quickSort(a, 0, 21);
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
