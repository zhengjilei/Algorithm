package sort.mergesort;

import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lenovo on 2018/3/30.
 */
public class MergeSort {

    private static int[] temp;
    private static int[] arr;

    public static void main(String[] args) {
        Random random = new Random();
        int length = random.nextInt(20) + 30;
        System.out.println(length);
        arr = new int[length];
        temp = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
        mergeSort(0, length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(SortJudge.judge(arr));
    }

    public static void mergeSort(int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(l, mid);
            mergeSort(mid + 1, r);
            merge(l, mid, r);
        }
    }

    public static void merge(int l, int lEnd, int rEnd) {
        int lStart = l;
        int rStart = lEnd + 1;
        int cnt = l;
        while (lStart <= lEnd && rStart <= rEnd) {
            if (arr[lStart] <= arr[rStart]) temp[cnt++] = arr[lStart++];
            else temp[cnt++] = arr[rStart++];
        }
        while (lStart <= lEnd) {
            temp[cnt++] = arr[lStart++];
        }
        while (rStart <= rEnd) {
            temp[cnt++] = arr[rStart++];
        }

        for (int i = l; i <= rEnd; i++) {
            arr[i] = temp[i];
        }

    }
}
