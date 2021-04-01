package 排序;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by EthanWalker on 2017/12/6.
 */
public class Sort2 {

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] a) {
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j + 1] < a[j]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        int i, j, tmp;
        for (i = 1; i < a.length; i++) {
            tmp = a[i];
            for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = tmp;
        }
    }


    /**
     * 选取中位数做 pivot
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int median3(int[] a, int left, int right) {
        int mid = (left + right) / 2;
        if (a[left] > a[mid]) swap(a, mid, left);
        if (a[left] > a[right]) swap(a, left, right);
        if (a[mid] > a[right]) swap(a, mid, right);
        // a[left]<=a[mid]<=a[right]

        //选取中位数  A[mid] 为 pivot
        swap(a, mid, right - 1);  //将 pivot 藏到倒数第二位（最后一位一定比 pivot 大）
        return a[right - 1];
    }

    /**
     * 快排
     *
     * @param a
     * @param left
     * @param right
     */
    public static void quickSort(int[] a, int left, int right) {

        if (left < right) {
            int pivot = median3(a, left, right);
            int l = left - 1;
            int r = right - 1;
            for (; ; ) {

                while (a[++l] < pivot) {
                }
                if (r == 0) break;
                while (a[--r] > pivot) {
                }
                if (l < r) {
                    swap(a, l, r);
                } else {
                    break;
                }
            }
            if (right - 1 >= 0) {
                swap(a, l, right - 1); // 将中位数 pivot 交换到 l 位置
            }

            quickSort(a, left, l - 1);
            quickSort(a, l + 1, right);

        }
    }

    /**
     * 交换数组中两个数
     *
     * @param a
     * @param m
     * @param n
     */
    public static void swap(int[] a, int m, int n) {
        int t = a[m];
        a[m] = a[n];
        a[n] = t;
    }


    /**
     * 合并两个数组
     *
     * @param arr
     * @param temp
     * @param lStart
     * @param rStart
     * @param rEnd
     */
    public static void merge(int[] arr, int[] temp, int lStart, int rStart, int rEnd) {
        int lEnd = rStart - 1;
        int num = rEnd - lStart + 1; // 处理数据的总数
        int t = lStart;

        while (lStart <= lEnd && rStart <= rEnd) {
            if (arr[lStart] < arr[rStart]) {
                temp[t++] = arr[lStart++];
            } else {
                temp[t++] = arr[rStart++];
            }
        }
        while (lStart <= lEnd) {
            temp[t++] = arr[lStart++];
        }
        while (rStart <= rEnd) {
            temp[t++] = arr[rStart++];
        }

        for (int i = 0; i < num; i++, rEnd--) {
            arr[rEnd] = temp[rEnd];
        }
    }

    /**
     * 归并排序
     *
     * @param arr
     * @param temp
     * @param start
     * @param end
     */
    public static void mSort(int[] arr, int[] temp, int start, int end) {
        int mid;
        if (start < end) {
            mid = (start + end) / 2;
            mSort(arr, temp, start, mid);
            mSort(arr, temp, mid + 1, end);
            merge(arr, temp, start, mid + 1, end);
        }
    }


}
