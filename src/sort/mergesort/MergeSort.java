package sort.mergesort;

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
        int length = random.nextInt(100);
        System.out.println(length);
        arr = new int[length];
        temp = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(200);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
        mergeSort(0, length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(l, mid);
            mergeSort(mid + 1, r);
            merge(l, mid, r);
        }
    }

    public static void merge(int leftStart, int leftEnd, int rightEnd) {
        int rightStart = leftEnd + 1;
        int t = leftStart;
        int count = rightEnd - leftStart + 1;
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (arr[leftStart] <= arr[rightStart]) temp[t++] = arr[leftStart++];
            else temp[t++] = arr[rightStart++];
        }
        while (leftStart <= leftEnd) {
            temp[t++] = arr[leftStart++];
        }
        while (rightStart <= rightEnd) {
            temp[t++] = arr[rightStart++];
        }

        for (int i = 0; i < count; i++, rightEnd--) {
            arr[rightEnd] = temp[rightEnd];
        }

    }
}
