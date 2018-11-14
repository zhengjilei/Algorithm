package 第K小数;

import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/20.
 */
public class Kth {

    // 复杂度 O(n)
    //选择第 K小的数   1,2,3,4,5   4 为第 4 小的数
    public static int kth(int[] a, int k, int left, int right) {
        if (k <= 0) return -1;
        int pivotRelativePos = randomPartition(a, left, right);

        if (pivotRelativePos + 1 == k) {
            // 随机数恰好是 第 k 小的数
            return a[pivotRelativePos + left];
        } else if (pivotRelativePos + 1 > k) {
            // 第 k 小的数在随机数的左边
            return kth(a, k, left, pivotRelativePos + left - 1);
        } else {
            // 第 k 小的数在随机数的右边
            return kth(a, (k - pivotRelativePos - 1), pivotRelativePos + left + 1, right);
        }
    }

    //划分，选出数组中的随机数， 并调整数组的顺序,Left< pivot < Right
    // 0 , n-1
    public static int randomPartition(int[] a, int left, int right) {
        int random = (int) (Math.random() * (right - left + 1) + left);
        swap(a, random, right);  // 将选出的随机数 pivot 调到 数组末尾
        int j = left - 1;
        // 一趟遍历，将比 随机数 pivot 小的调到左边
        for (int i = left; i < right; i++) {
            if (a[i] < a[right]) {
                swap(a, ++j, i);
            }
        }
        swap(a, ++j, right);  // 将选中的随机数 调到 正确的位置，保证 左边的数字比 random 小， 右边的数字比 random 大
        return j - left;  // 返回 pivot 的相对位置
    }

    public static void swap(int[] a, int m, int n) {
        int tmp = a[m];
        a[m] = a[n];
        a[n] = tmp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            int result = kth(a, k, 0, n - 1);
            System.out.println("结果为: " + result);
        }

    }

}
//        10 8
//        43 213 32 1 2 43 65 89 54 87


//12012 3 945 965 66 232 65 7 8 898 56 878 170 13 5