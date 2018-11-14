package sort.quicksort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lenovo on 2018/3/26.
 * 栈溢出
 */
public class QuickSort {

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
            quickSort(a, 0, length - 1);
//          System.out.println(Arrays.toString(a));

            System.out.println(SortJudge.judge(a));
        }
    }

    public static void swap(int[] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;

       /* a[left]= a[left]^a[right];
        a[right] =a[left]^a[right];
        a[left] = a[left]^a[right];*/
    }

    public static int median3(int[] a, int left, int right) {
        int mid = (left + right) / 2;

        if (a[left] > a[mid]) swap(a, left, mid);
        if (a[mid] > a[right]) swap(a, mid, right);
        // 最大的数，此时在 right 位置上
        if (a[left] > a[mid]) swap(a, left, mid);

        swap(a, mid, right - 1);
        return a[right - 1];
    }

    public static void quickSort(int[] a, int left, int right) {

        if (left < right) {
            int pivot = median3(a, left, right);
            int i = left, j = right;
            while (i < j) {
                while (i < right && a[i] <= pivot) i++;
                while (j > left && a[j] >= pivot) j--;
                if (i < j) {
                    swap(a, i, j);
                }
            }
            swap(a, i, right - 1);
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);

        }
    }

    @Test
    public void testA() {
        int[] a = new int[]{1, 0, 4, 6, 2, 4, 6, 2, 2};
        quickSort(a, 0, 8);
        System.out.println(Arrays.toString(a));
    }

}
