package sort.quicksort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/5/14.
 * 快速排序三路划分（解决两路划分情况下 太多相同元素导致算法复杂度变成 O(n^2)
 */
public class QuickSortTri {
    public static void main(String[] args) {
        for (int k = 0; k < 1000; k++) {
            Random random = new Random();
            int length = random.nextInt(10000);
            int[] a = new int[length];
            int[] b = new int[length];
            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(10000);
            }
            quickSort(a, 0, length - 1);

            System.out.println(SortJudge.judge(a));
        }

    }

    public static void quickSort(int[] a, int l, int r) {
        if (l < r) {
            int[] result = partition(a, l, r);
            quickSort(a, l, result[0] - 1);
            quickSort(a, result[1] + 1, r);
        }
    }

    /**
     * 三路划分
     * 返回数组:   ...|  ... | ...  返回两个元素，将数组划分成三部分
     *
     * @param a
     * @param l
     * @param r
     * @return
     */
    public static int[] partition(int[] a, int l, int r) {
        Random random = new Random();
        int pivotPos = random.nextInt(r - l + 1) + l;
        int pivot = a[pivotPos];
        swap(a, pivotPos, r);
        int k = l, p = r;
        for (int i = l; i < p; ) {
            if (a[i] < pivot) swap(a, i++, k++);
            else if (a[i] == pivot) swap(a, i, p--);
            // 这里 i 不能++, 因为可能交换后位置i 处仍与pivot 相等，再交换
            else i++;
        }
        // 结果:  l~k <pivot, k+1~p >pivot, p+1~r =pivot

        int min = Math.min(p - k, r - p + 1);  // >pivot 的数目p-k、 =pivot 的数目r-p+1 选较小的那个
        // 交换 使得 =pivot 的数交换到中间
        for (int j = 0; j < min; j++) {
            swap(a, k + j, r - j);
        }

        int[] result = new int[2];
        result[0] = k;
        result[1] = r - p + k;
        return result;
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    public void testA() {
        int[] a = new int[]{1, 0, 4, 6, 2, 4, 6, 2, 2};
        quickSort(a, 0, 8);
        System.out.println(Arrays.toString(a));
    }


}
