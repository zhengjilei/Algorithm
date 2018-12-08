package 全排列;

import java.util.Arrays;

public class Permutation {


    // 递归的缺点： 1. 不支持重复元素的全排列  2. 只有第二种递归支持字典序

    /**
     * 求数组 a 从 a[start] 到 a[end] 的全排列
     * 每次选中一个元素放在位置为 end 上，然后递归排列 a[start] a[end-1]
     * <p>
     * 不支持重复元素，不支持字典序
     */
    public static void recur(int[] a, int start, int end) {
        if (start == end) {
            System.out.println(Arrays.toString(a));
            return;
        }
        // 将 a[i] 排到末尾，然后对 a[start] ~ a[end-1] 求全排列
        for (int i = start; i <= end; i++) {
            swap(a, i, end);
            recur(a, start, end - 1);
            swap(a, i, end);
        }

    }


    /**
     * <p>
     * 求数组 a 从 a[start] 到 a[end] 的全排列
     * 每次选中一个元素放在位置为 start 上，然后递归排列 a[start+1] a[end]
     * <p>
     * 支持字典序 （要求初始序列为字典序）
     */
    public static void recur2(int[] a, int start, int end) {
        if (start == end) {
            System.out.println(Arrays.toString(a));
            return;
        }
        for (int i = start; i <= end; i++) {
            if (i != start && a[i] == a[start]) {
                // 不同位置的重复元素不进行交换
                continue;
            }
            swap(a, i, start);
            recur2(a, start + 1, end); // 递归排列
            swap(a, i, start);
        }
    }

    /**
     * 字典序输出全排列
     * 支持重复元素的字典序
     */
    public static void lexicalPermutation(int[] a) {
        int i = 0, n = a.length;
        int count = 1;
        System.out.println(Arrays.toString(a)); // 初始序列
        while (true) {
            int index = -1;
            // 从右往左找出第一个 a[j]<a[j+1]
            for (int j = n - 2; j >= 0; j--) {
                if (a[j] < a[j + 1]) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                int min = a[index + 1];
                int minIndex = index + 1;
                // 从 a[j+1] 开始寻找比 a[j] 大且最小的值，和位置
                for (int j = minIndex + 1; j < n; j++) {
                    if (a[j] > a[index] && a[j] < min) {
                        min = a[j];
                        minIndex = j;
                    }
                }
                // 交换 a[j] 和 a[mindIndex]
                swap(a, index, minIndex);
                // 对 a[j+1] 开始的元素进行排序
                Arrays.sort(a, index + 1, n);
                System.out.println(Arrays.toString(a));
                count++;
            } else {
                break;
            }
        }
        System.out.println("count=" + count);
    }

    public static void swap(int[] a, int i, int j) {
        a[i] = a[i] + a[j] - (a[j] = a[i]);
    }

    /**
     * 字符全排列
     *
     * @param chs
     * @param start
     * @param end
     */
    public static void perm(char[] chs, int start, int end) {

        if (start == end) {
            System.out.println(Arrays.toString(chs));
        }
        for (int i = start; i <= end; i++) {
            swap(chs, i, start);
            perm(chs, start + 1, end);
            swap(chs, i, start);
        }

    }

    public static void swap(char[] chs, int a, int b) {
        char t = chs[a];
        chs[a] = chs[b];
        chs[b] = t;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 1, 3};  // 重复元素
        recur2(a, 0, a.length - 1);
//        lexicalPermutation(a);
    }
}
