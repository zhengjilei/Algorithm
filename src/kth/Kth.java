package kth;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 寻找第 k 小的数
 * 时间复杂度 : O(n)
 * 如果是第 k 大的数（逆序划分，左边是比 pivot 大的数）
 * Created by EthanWalker on 2017/11/20.
 */
public class Kth {


    public static int kthNonRecur(int[] a, int k) {
        int pivotIndex;
        int left = 0, right = a.length - 1;
        while (true) {
            pivotIndex = randomPartition(a, left, right);
            if (pivotIndex + 1 == k) {
                return a[pivotIndex];
            } else if (pivotIndex + 1 > k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

    }

    /**
     * 在a[left]~a[right] 中选择第 K小的数
     * 复杂度 O(n)
     */
    public static int kth(int[] a, int left, int right, int k) {
        int pivotIndex = randomPartition(a, left, right);
        int leftMidCount = pivotIndex - left + 1; // 左边数量 + pivot 的总数
        if (leftMidCount == k) {
            // 随机数恰好是 第 k 小的数
            return a[pivotIndex];
        } else if (leftMidCount > k) {
            // 第 k 小的数在随机数的左边
            return kth(a, left, pivotIndex - 1, k);
        } else {
            // 第 k 小的数在随机数的右边
            return kth(a, pivotIndex + 1, right, k - leftMidCount);
        }
    }

    /**
     * 划分，选出数组中的随机数， 并调整数组的顺序,Left <= pivot <= Right
     */
    public static int randomPartition(int[] a, int left, int right) {
        int pivotIndex = (int) (Math.random() * (right - left + 1) + left);
        swap(a, pivotIndex, right);  // 将选出的随机数 pivot 调到 数组末尾

        int i = left, j = right; // 注意 j 必须初始化为 right 而不是 right-1 否则会出错
        while (i < j) {
            while (i < j && a[i] <= a[right]) i++;
            while (i < j && a[j] >= a[right]) j--;
            if (i < j) {
                swap(a, i, j);
            }
        }
        swap(a, i, right);      // 将选中的 pivot 调到 正确的位置
        return i;
    }

    public static void swap(int[] a, int m, int n) {
        int tmp = a[m];
        a[m] = a[n];
        a[n] = tmp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int n = random.nextInt(10) + 10;  // [10,20) 个元素
        System.out.println("n=" + n);
        int[] a = new int[n];
        int k = random.nextInt(n) + 1;
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(20);
        }
        System.out.println(Arrays.toString(a));
        System.out.println("k = " + k);
        int result = kth(a, k, 0, n - 1);
        System.out.println("结果为: " + result);

        Arrays.sort(a);
        System.out.println(a[k - 1]);

    }

}
//        10 8
//        43 213 32 1 2 43 65 89 54 87


//12012 3 945 965 66 232 65 7 8 898 56 878 170 13 5