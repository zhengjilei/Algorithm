package 快速排序;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/19.
 */
public class QuickSort {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int count = scanner.nextInt(); // 元素个数
            int[] arr = new int[count];
            for (int i = 0; i < count; i++) {
                arr[i] = scanner.nextInt();
            }
            int[] temp = new int[count];
            quickSort(arr, 0, count - 1);
            System.out.println(Arrays.toString(arr));
        }
    }
//-212 -32 -10 0  7 11 32 100 432 1324
    public static int median3(int[] a, int left, int right) {
        int mid = (left + right) / 2;
        if (a[right] < a[mid]) swap(a, mid, right);     // a[mid] <= a[right]
        if (a[left] > a[mid]) swap(a, mid, left);        // a[left] < a[mid]
        if (a[mid] > a[right]) swap(a, mid, right);      // a[left]<=a[mid]<=a[right]

        //选取中位数  A[mid] 为 pivot
        swap(a, mid, right - 1);  //将 pivot 藏到倒数第二位（最后一位一定比 pivot 大）
        return a[right - 1];
    }

    public static void quickSort(int[] a, int left, int right) {

        if (left < right) {
            int pivot = median3(a, left, right);
            int l = left - 1;
            int r = right - 1;
            for (; ; ) {
                while (a[++l] < pivot) {
                }
                while (a[--r] > pivot) {
                }
                if (l < r) {
                    swap(a, l, r);
                } else {
                    break;
                }
            }

            swap(a, l, right - 1); // 将中位数 pivot 交换到 l 位置

            quickSort(a, left, l - 1);
            quickSort(a, l + 1, right);

        }
    }


    public static void swap(int[] a, int m, int n) {
        int t = a[m];
        a[m] = a[n];
        a[n] = t;
    }
}
