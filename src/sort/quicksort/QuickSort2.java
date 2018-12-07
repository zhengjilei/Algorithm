package sort.quicksort;

import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/5/14.
 */
public class QuickSort2 {
    public static void main(String[] args) {
        for (int k = 0; k < 10000; k++) {
            Random random = new Random();
            int length = random.nextInt(300);
//            System.out.println(length);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(200);
            }
//        System.out.println(Arrays.toString(a));
            quickSort(a, 0, length - 1);
            boolean judge = SortJudge.judge(a);
            if(!judge){
                System.out.println(judge);
            }
//        System.out.println(Arrays.toString(a));
        }

    }

    static int partition(int a[], int left, int right)///7行代码
    {
        int i = left - 1;///初始化一定要赋值好。
        for (int j = left; j <= right - 1; j++) {
            if (a[j] < a[right]) {///把right这个作为轴了。
                i++;///这个i坐标左边的值就是比a[right]小的。
                swap(a, i, j);///必须交换一下。
            }
        }
        swap(a, i + 1, right);///最后把i+1和right交换，这样轴就是i+1了必须是保证i+1上当初就是作为标杆的a[right]啊。
        return i + 1;
    }

    static void quickSort(int a[], int left, int right) {
        if (left < right) {
            int q = partition(a, left, right);
            quickSort(a, left, q - 1);
            quickSort(a, q + 1, right);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
