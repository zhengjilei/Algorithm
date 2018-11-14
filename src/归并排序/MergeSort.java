package 归并排序;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/19.
 */
public class MergeSort {

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

        for(int i= 0;i<num;i++,rEnd--){
            arr[rEnd] = temp[rEnd];
        }
    }

    public static void mSort(int[] arr, int[] temp, int start, int end) {

        int mid;
        if (start < end) {
            mid = (start + end) / 2;
            mSort(arr, temp, start, mid);
            mSort(arr, temp, mid + 1, end);
            merge(arr, temp, start, mid + 1, end);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int count = scanner.nextInt(); // 元素个数
            int[] arr = new int[count];
            for (int i = 0; i < count; i++) {
                arr[i] = scanner.nextInt();
            }
            int[] temp = new int[count];
            mSort(arr, temp, 0, count - 1);
            System.out.println(Arrays.toString(arr));
        }
    }
}

